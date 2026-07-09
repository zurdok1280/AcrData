package com.ml.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class SpotifySearchService {
	
	static Logger logger = LoggerFactory.getLogger(SpotifySearchService.class);

	@Value("${spotify.client.id}")
	private String cliendId;
	@Value("${spotify.secret.key}")
	private  String secretKey;
	@Autowired
	SpotifyEnricher spotifyEnricher;

	/**
	 * Busca canciones y artistas en Spotify y devuelve un JSON con nombre, artista,
	 * ID, URL e imagen.
	 */
	
	public JSONObject searchSpotifyAndEnrich(String query, String top) throws Exception {
	    // 1️⃣ Buscar en Spotify
	    JSONObject spotifyResult = searchSpotify(query, top);
	    // 2️⃣ Enriquecer con tu base de datos
	    JSONObject enriched = spotifyEnricher.enrichTracksWithMySongId(spotifyResult);
	    // 3️⃣ Regresar resultado final ya combinado
	    return enriched;
	}
	public JSONObject searchSpotify(String query, String top) throws Exception {
		String accessToken = getAccessToken();
		String encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8);
		// 🔍 Buscamos canciones y artistas
		String url = "https://api.spotify.com/v1/search?q=" + encodedQuery + "&type=track,artist&limit=" + top;
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url))
				.header("Authorization", "Bearer " + accessToken).build();
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		JSONObject json = new JSONObject(response.body());
		JSONObject resultadoFinal = new JSONObject();
		resultadoFinal.put("query", query);
		// 🎵 Procesar canciones
		JSONArray tracksArray = new JSONArray();
		if (json.has("tracks") && json.getJSONObject("tracks").has("items")) {
			JSONArray tracks = json.getJSONObject("tracks").getJSONArray("items");
			for (int i = 0; i < tracks.length(); i++) {
				JSONObject track = tracks.getJSONObject(i);
				JSONObject item = new JSONObject();

				item.put("type", "track");
				item.put("song_name", track.getString("name"));
				item.put("spotify_id", track.getString("id"));
				item.put("artist_name", track.getJSONArray("artists").getJSONObject(0).getString("name"));
				item.put("url", "https://open.spotify.com/track/" + track.getString("id"));
				item.put("duration_ms", track.getInt("duration_ms"));

				// 📸 Imagen del álbum (si existe)
				String imageUrl = "";
				try {
					JSONArray images = track.getJSONObject("album").getJSONArray("images");
					if (images.length() > 0) {
						imageUrl = images.getJSONObject(0).getString("url"); // toma la de mayor tamaño
					}
				} catch (Exception e) {
					imageUrl = "";
				}
				item.put("image_url", imageUrl);

				tracksArray.put(item);
			}
		}

		// 👤 Procesar artistas
		JSONArray artistsArray = new JSONArray();
		if (json.has("artists") && json.getJSONObject("artists").has("items")) {
			JSONArray artists = json.getJSONObject("artists").getJSONArray("items");
			for (int i = 0; i < artists.length(); i++) {
				JSONObject artist = artists.getJSONObject(i);
				JSONObject item = new JSONObject();

				item.put("type", "artist");
				item.put("artist_name", artist.getString("name"));
				item.put("spotify_id", artist.getString("id"));
				item.put("followers", artist.getJSONObject("followers").getInt("total"));
				item.put("genres", artist.getJSONArray("genres"));
				item.put("url", "https://open.spotify.com/artist/" + artist.getString("id"));

				// 📸 Imagen del artista (si existe)
				String imageUrl = "";
				try {
					JSONArray images = artist.getJSONArray("images");
					if (images.length() > 0) {
						imageUrl = images.getJSONObject(0).getString("url"); // primera suele ser la más grande
					}
				} catch (Exception e) {
					imageUrl = "";
				}
				item.put("image_url", imageUrl);

				artistsArray.put(item);
			}
		}

		resultadoFinal.put("tracks_count", tracksArray.length());
		resultadoFinal.put("artists_count", artistsArray.length());
		resultadoFinal.put("tracks", tracksArray);
		resultadoFinal.put("artists", artistsArray);

		return resultadoFinal;
	}

	/**
	 * Obtiene un token de acceso de Spotify (Client Credentials Flow).
	 */
	private String getAccessToken() throws IOException, InterruptedException {
		String auth = cliendId + ":" + secretKey;
		logger.info(auth);
		String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());

		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://accounts.spotify.com/api/token"))
				.header("Authorization", "Basic " + encodedAuth)
				.header("Content-Type", "application/x-www-form-urlencoded")
				.POST(HttpRequest.BodyPublishers.ofString("grant_type=client_credentials")).build();

		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		JSONObject json = new JSONObject(response.body());
		return json.getString("access_token");
	}

}
