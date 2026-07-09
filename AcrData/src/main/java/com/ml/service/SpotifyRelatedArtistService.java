package com.ml.service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Instant;
import java.util.Base64;

@Service
public class SpotifyRelatedArtistService {

	@Value("${spotify.client.id}")
	private String clientId;

	@Value("${spotify.secret.key}")
	private String secretKey;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private final HttpClient httpClient = HttpClient.newHttpClient();

	// Cache simple en memoria
	private volatile String cachedAccessToken;
	private volatile Instant tokenExpiresAt;

	private final Object tokenLock = new Object();

	/**
	 * Obtiene artistas relacionados desde Spotify
	 */
	public JSONObject getRelatedArtists(String artistSpotifyId) throws Exception {
		String accessToken = getAccessToken();

		String url = "https://api.spotify.com/v1/artists/" + artistSpotifyId + "/related-artists";

		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url))
				.header("Authorization", "Bearer " + accessToken).GET().build();

		HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

		if (response.statusCode() == 401) {
			// token vencido o inválido: limpiar cache y reintentar una vez
			invalidateTokenCache();

			accessToken = getAccessToken();

			request = HttpRequest.newBuilder().uri(URI.create(url)).header("Authorization", "Bearer " + accessToken)
					.GET().build();

			response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
		}

		if (response.statusCode() != 200) {
			throw new RuntimeException("Error consultando related artists en Spotify. status=" + response.statusCode()
					+ ", body=" + response.body());
		}

		return new JSONObject(response.body());
	}

	/**
	 * Consulta Spotify y guarda los relacionados usando el SP: [SET_RELATED_ARTIST]
	 * (@artist bigint,
	 * 
	 * @artist_spotify varchar(100),
	 * @related_artist bigint,
	 * @related_spotify varchar(100),
	 * @related_genres varchar(300),
	 * @related_followers int,
	 * @related_popularity int)
	 */
	public int saveRelatedArtists(long artistId, String artistSpotifyId) throws Exception {
		JSONObject json = getRelatedArtists(artistSpotifyId);

		if (!json.has("artists")) {
			return 0;
		}

		JSONArray artists = json.getJSONArray("artists");
		int inserted = 0;

		for (int i = 0; i < artists.length(); i++) {
			JSONObject artist = artists.getJSONObject(i);

			String relatedSpotify = artist.optString("id", "");
			if (relatedSpotify.isBlank()) {
				continue;
			}

			int followers = 0;
			if (artist.has("followers")) {
				followers = artist.getJSONObject("followers").optInt("total", 0);
			}

			int popularity = artist.optInt("popularity", 0);

			JSONArray genresArray = artist.optJSONArray("genres");
			String genres = genresArray != null ? genresArray.toString() : "";


			// varchar(300) en SP: por seguridad recortamos
			if (genres.length() > 300) {
				genres = genres.substring(0, 300);
			}

			jdbcTemplate.update("EXEC SET_RELATED_ARTIST ?, ?, ?, ?, ?, ?", artistId, artistSpotifyId,
					relatedSpotify, genres, followers, popularity);

			inserted++;
		}

		return inserted;
	}

	/**
	 * Procesa varios artistas
	 */
	public int saveRelatedArtistsForMany(java.util.List<ArtistInput> artistList) {
		int totalInserted = 0;

		for (ArtistInput item : artistList) {
			try {
				totalInserted += saveRelatedArtists(item.getArtistId(), item.getArtistSpotifyId());
			} catch (Exception e) {
				System.err.println("Error procesando artistId=" + item.getArtistId() + ", spotifyId="
						+ item.getArtistSpotifyId() + ", error=" + e.getMessage());
			}
		}

		return totalInserted;
	}

	/**
	 * Devuelve token usando cache
	 */
	private String getAccessToken() throws IOException, InterruptedException {
		Instant now = Instant.now();

		if (cachedAccessToken != null && tokenExpiresAt != null && now.isBefore(tokenExpiresAt)) {
			return cachedAccessToken;
		}

		synchronized (tokenLock) {
			now = Instant.now();

			if (cachedAccessToken != null && tokenExpiresAt != null && now.isBefore(tokenExpiresAt)) {
				return cachedAccessToken;
			}

			String auth = clientId + ":" + secretKey;
			String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());

			HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://accounts.spotify.com/api/token"))
					.header("Authorization", "Basic " + encodedAuth)
					.header("Content-Type", "application/x-www-form-urlencoded")
					.POST(HttpRequest.BodyPublishers.ofString("grant_type=client_credentials")).build();

			HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

			if (response.statusCode() != 200) {
				throw new RuntimeException("Error obteniendo token de Spotify. status=" + response.statusCode()
						+ ", body=" + response.body());
			}

			JSONObject json = new JSONObject(response.body());

			String accessToken = json.getString("access_token");
			int expiresIn = json.getInt("expires_in"); // normalmente 3600 segundos

			// margen de seguridad de 60 segundos
			Instant expiresAt = Instant.now().plusSeconds(Math.max(expiresIn - 60L, 60L));

			cachedAccessToken = accessToken;
			tokenExpiresAt = expiresAt;

			return cachedAccessToken;
		}
	}

	/**
	 * Limpia cache manualmente
	 */
	private void invalidateTokenCache() {
		synchronized (tokenLock) {
			cachedAccessToken = null;
			tokenExpiresAt = null;
		}
	}

	/**
	 * DTO simple para procesos por lote
	 */
	public static class ArtistInput {
		private long artistId;
		private String artistSpotifyId;

		public ArtistInput(long artistId, String artistSpotifyId) {
			this.artistId = artistId;
			this.artistSpotifyId = artistSpotifyId;
		}

		public long getArtistId() {
			return artistId;
		}

		public void setArtistId(long artistId) {
			this.artistId = artistId;
		}

		public String getArtistSpotifyId() {
			return artistSpotifyId;
		}

		public void setArtistSpotifyId(String artistSpotifyId) {
			this.artistSpotifyId = artistSpotifyId;
		}
	}
}
