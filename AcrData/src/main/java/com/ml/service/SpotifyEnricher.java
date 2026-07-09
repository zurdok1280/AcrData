package com.ml.service;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class SpotifyEnricher {
	private final JdbcTemplate jdbc;

	public SpotifyEnricher(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}
	
	
	public JSONObject enrichTracksWithMySongId(JSONObject spotifyResult) {

	    JSONArray tracks = spotifyResult.optJSONArray("tracks");
	    if (tracks == null || tracks.length() == 0) return spotifyResult;

	    // 1) recolectar spotify ids
	    List<String> spotifyIds = new ArrayList<>();
	    for (int i = 0; i < tracks.length(); i++) {
	        JSONObject t = tracks.getJSONObject(i);
	        if ("track".equals(t.optString("type"))) {
	            String spId = t.optString("spotify_id");
	            if (spId != null && !spId.isBlank()) spotifyIds.add(spId);
	        }
	    }
	    spotifyIds = spotifyIds.stream().distinct().toList();

	    // 2) lookup batch por spotify_id (directo)
	    Map<String, SongInfo> bySpotifyId = findMySongIdsBySpotifyIds(spotifyIds);

	    // 3) lookup batch por spotify_id -> ISRC -> mySongId (solo para misses)
	    List<String> missingSpotifyIds = spotifyIds.stream()
	            .filter(id -> !bySpotifyId.containsKey(id))
	            .toList();

	    Map<String, SongInfo> byIsrc = missingSpotifyIds.isEmpty()
	            ? Map.of()
	            : findMySongBySpotifyIdViaIsrc(missingSpotifyIds);

	    // 4) aplicar + fallback
	    for (int i = 0; i < tracks.length(); i++) {

	        JSONObject t = tracks.getJSONObject(i);
	        if (!"track".equals(t.optString("type"))) continue;

	        String spId = t.optString("spotify_id");

	        SongInfo info = null;
	        String matchType = null;

	        // 4.1 spotify_id directo
	        info = bySpotifyId.get(spId);
	        if (info != null) {
	            matchType = "spotify_id";
	        }

	        // 4.2 ISRC (si no está por spotify_id directo)
	        if (info == null) {
	            info = byIsrc.get(spId);
	            if (info != null) {
	                matchType = "isrc_link";
	                t.put("isrc", info.isrc == null ? JSONObject.NULL : info.isrc);
	            }
	        }

	        // 4.3 fallback name + artist
	        if (info == null) {
	            String songNorm = norm(t.optString("song_name"));
	            String artistNorm = norm(t.optString("artist_name"));
	            int durationMs = t.has("duration_ms") ? t.optInt("duration_ms", -1) : -1;

	            if (!songNorm.isBlank() && !artistNorm.isBlank()) {
	                info = findMySongIdByNameArtistNorm(
	                        songNorm,
	                        artistNorm,
	                        durationMs > 0 ? durationMs : null
	                );
	                if (info != null) {
	                    matchType = (durationMs > 0 ? "name_artist_duration" : "name_artist");
	                }
	            }
	        }

	        // 5) escribir al JSON (una sola vez, con lo que haya quedado)
	        Long mySongId = (info == null ? null : info.mySongId);
	        int tocadas = (info == null ? 0 : info.tocadas);

	        t.put("my_song_id", mySongId == null ? JSONObject.NULL : mySongId);
	        t.put("tocadas", tocadas);
	        t.put("exists_in_db", mySongId != null);
	        t.put("match_type", matchType == null ? JSONObject.NULL : matchType);
	    }

	    // opcional: ordenar por tocadas desc (y ya con exists)
	    spotifyResult.put("tracks", sortByImportance(tracks));
	    spotifyResult.put("tracks_count_in_db", countExists(spotifyResult.getJSONArray("tracks")));

	    return spotifyResult;
	}

	/*public JSONObject enrichTracksWithMySongId(JSONObject spotifyResult) {
		JSONArray tracks = spotifyResult.optJSONArray("tracks");
		if (tracks == null || tracks.length() == 0)
			return spotifyResult;

		// 1) recolectar spotify ids
		List<String> spotifyIds = new ArrayList<>();
		for (int i = 0; i < tracks.length(); i++) {
			JSONObject t = tracks.getJSONObject(i);
			if ("track".equals(t.optString("type"))) {
				String spId = t.optString("spotify_id");
				if (spId != null && !spId.isBlank())
					spotifyIds.add(spId);
			}
		}
		spotifyIds = spotifyIds.stream().distinct().toList();

		// 2) lookup batch por spotify_id
		Map<String, SongInfo> myIdBySpotifyId = findMySongIdsBySpotifyIds(spotifyIds);

		// 3) aplicar + fallback por nombre/artista si no existe spotify_id en BD
		for (int i = 0; i < tracks.length(); i++) {
			JSONObject t = tracks.getJSONObject(i);
			if (!"track".equals(t.optString("type")))
				continue;

			String spId = t.optString("spotify_id");

			SongInfo info = myIdBySpotifyId.get(spId);

			Long mySongId = null;
			int tocadas = 0;

			if (info != null) {
				mySongId = info.mySongId;
				tocadas = info.tocadas;
			}

			t.put("my_song_id", mySongId == null ? JSONObject.NULL : mySongId);
			t.put("tocadas", tocadas);
			t.put("exists_in_db", mySongId != null);

			String matchType = null;

			if (mySongId != null) {
				matchType = "spotify_id";
			} else {
				// fallback name + artist (+duration si está)
				String songNorm = norm(t.optString("song_name"));
				String artistNorm = norm(t.optString("artist_name"));
				int durationMs = t.has("duration_ms") ? t.optInt("duration_ms", -1) : -1;

				if (!songNorm.isBlank() && !artistNorm.isBlank()) {
					info = findMySongIdByNameArtistNorm(songNorm, artistNorm, durationMs > 0 ? durationMs : null);
					if (info != null)
						matchType = (durationMs > 0 ? "name_artist_duration" : "name_artist");
				}
			}

			t.put("my_song_id", mySongId == null ? JSONObject.NULL : mySongId);
			t.put("exists_in_db", mySongId != null);
			t.put("match_type", matchType == null ? JSONObject.NULL : matchType);
		}

		// opcional: ordenar (primero los que existen)
		spotifyResult.put("tracks", sortByImportance(tracks));
		spotifyResult.put("tracks_count_in_db", countExists(spotifyResult.getJSONArray("tracks")));

		return spotifyResult;
	}*/

	private int countExists(JSONArray tracks) {
		int c = 0;
		for (int i = 0; i < tracks.length(); i++)
			if (tracks.getJSONObject(i).optBoolean("exists_in_db"))
				c++;
		return c;
	}

	private JSONArray sortByExists(JSONArray arr) {
		List<JSONObject> list = new ArrayList<>();
		for (int i = 0; i < arr.length(); i++)
			list.add(arr.getJSONObject(i));
		list.sort((a, b) -> Boolean.compare(b.optBoolean("exists_in_db"), a.optBoolean("exists_in_db"))); // true
																											// primero
		JSONArray out = new JSONArray();
		for (JSONObject o : list)
			out.put(o);
		return out;
	}

	private JSONArray sortByImportance(JSONArray arr) {
	    if (arr == null) return null;

	    List<JSONObject> list = new ArrayList<>();
	    for (int i = 0; i < arr.length(); i++) {
	        list.add(arr.getJSONObject(i));
	    }

	    list.sort((a, b) -> {
	        // 1) exists_in_db: true primero
	        boolean exA = a.optBoolean("exists_in_db", false);
	        boolean exB = b.optBoolean("exists_in_db", false);
	        if (exA != exB) {
	            return Boolean.compare(exB, exA); // true arriba
	        }

	        // 2) tocadas: DESC
	        int tA = a.optInt("tocadas", 0);
	        int tB = b.optInt("tocadas", 0);
	        if (tA != tB) {
	            return Integer.compare(tB, tA); // mayor primero
	        }

	        // 3) desempate: nombre
	        return a.optString("song_name", "").compareToIgnoreCase(b.optString("song_name", ""));
	    });

	    JSONArray out = new JSONArray();
	    for (JSONObject o : list) out.put(o);
	    return out;
	}

	// ======= BD =======

	private Map<String, SongInfo> findMySongIdsBySpotifyIds(List<String> spotifyIds) {
		if (spotifyIds == null || spotifyIds.isEmpty())
			return Map.of();

		String sql = "SELECT spotify_id, sa.cs_song my_song_id, isnull(spins,0) spins FROM Reporting.dbo.Spotify_Link sa left join (select sum(spins) spins,cs_song from Reporting.dbo.Song_weekGroup24_all group by cs_song) d on d.cs_song = sa.cs_song WHERE spotify_id IN ("
				+ placeholders(spotifyIds.size()) + ")";
		List<Map<String, Object>> rows = jdbc.queryForList(sql, spotifyIds.toArray());

		/*
		 * Map<String, Long> map = new HashMap<>(); for (Map<String, Object> r : rows) {
		 * map.put((String) r.get("spotify_id"), ((Number)
		 * r.get("my_song_id")).longValue()); } return map;
		 */

		Map<String, SongInfo> map = new HashMap<>();

		for (Map<String, Object> r : rows) {
			SongInfo info = new SongInfo(((Number) r.get("my_song_id")).longValue(),
					((Number) r.get("spins")).intValue(), null);
			map.put((String) r.get("spotify_id"), info);
		}

		return map;
	}

	private Map<String, SongInfo> findMySongBySpotifyIdViaIsrc(List<String> spotifyIds) {
		if (spotifyIds == null || spotifyIds.isEmpty())
			return Map.of();

		String sql = String.format("""
				    SELECT tl.external_id spotify_id,
				           s.cs_song AS my_song_id,
				           ISNULL(d.spins, 0) AS spins,
				           tl.isrc
				    FROM monitor_songstats.dbo.track_link tl
				    JOIN Reporting.dbo.VW_ISRC s ON s.isrc = tl.isrc   -- << ajusta tabla ISRC->cs_song
				    LEFT JOIN (
				        SELECT cs_song, SUM(spins) AS spins
				        FROM Reporting.dbo.Song_weekGroup24_all
				        GROUP BY cs_song
				    ) d ON d.cs_song = s.cs_song
				    WHERE tl.external_id IN (%s) and source = 'spotify'
				""", placeholders(spotifyIds.size()));
		
		List<Map<String, Object>> rows = jdbc.queryForList(sql, spotifyIds.toArray());
		
		Map<String, SongInfo> map = new HashMap<>();
		for (Map<String, Object> r : rows) {
			String spId = (String) r.get("spotify_id");
			Long myId = ((Number) r.get("my_song_id")).longValue();
			int tocadas = ((Number) r.get("spins")).intValue();
			String isrc = (String) r.get("isrc");
			map.put(spId, new SongInfo(myId, tocadas, isrc));
		}
		return map;
	}

	/**
	 * Recomendado si tienes song_name_norm y artist_name_norm en dbo.Songs. Si NO
	 * tienes duration_ms, pásalo null.
	 */
	private SongInfo findMySongIdByNameArtistNorm(String songNorm, String artistNorm, Integer durationMsOrNull) {
		String songLike = "%" + songNorm + "%";
		String artistLike = "%" + artistNorm + "%";
		if (durationMsOrNull == null) {
			String sql = """
					  SELECT TOP 1 sa.cs_song my_song_id, isnull(spins,0) spins
					  FROM Reporting.dbo.songsdata sa
					  inner join Reporting.dbo.artists a on a.id = sa.artist
					  left join (select sum(spins) spins,cs_song from Reporting.dbo.Song_weekGroup24_all group by cs_song) d on d.cs_song = sa.cs_song
					  left join Reporting.dbo.Spotify_Link sl on sl.cs_song = sa.cs_song
					  WHERE sa.song LIKE ?
					    AND a.artist LIKE ?
					""";
			List<SongInfo> list = jdbc.query(sql,
					(rs, i) -> new SongInfo(rs.getLong("my_song_id"), rs.getInt("tocadas"), null), songLike,
					artistLike);

			return list.isEmpty() ? null : list.get(0);
		} else {
			String sql = """
					  SELECT TOP 1 sa.cs_song my_song_id, isnull(spins,0) spins
					  FROM Reporting.dbo.songsdata sa
					  inner join Reporting.dbo.artists a on a.id = sa.artist
					  left join (select sum(spins) spins,cs_song from Reporting.dbo.Song_weekGroup24_all group by cs_song) d on d.cs_song = sa.cs_song
					  WHERE sa.song LIKE ?
					    AND a.artist LIKE ?
					    AND ABS(Item_length_sec - ?) <= 2000
					  ORDER BY ABS(Item_length_sec - ?)
					""";
			List<SongInfo> list = jdbc.query(sql,
					(rs, i) -> new SongInfo(rs.getLong("my_song_id"), rs.getInt("tocadas"), null), songLike, artistLike,
					durationMsOrNull, durationMsOrNull);

			return list.isEmpty() ? null : list.get(0);
		}
	}

	private static String placeholders(int n) {
		return String.join(",", Collections.nCopies(n, "?"));
	}

	// ======= Normalización =======

	public static String norm(String s) {
		if (s == null)
			return "";
		String x = s.toLowerCase().trim();
		x = Normalizer.normalize(x, Normalizer.Form.NFD).replaceAll("\\p{M}", ""); // quita acentos
		x = x.replaceAll("&", "and");
		x = x.replaceAll("[^a-z0-9\\s]", " ");
		x = x.replaceAll("\\s+", " ").trim();
		// opcional: ruido común
		x = x.replaceAll("\\b(remaster(ed)?|live|radio edit|edit|version)\\b", "").trim();
		x = x.replaceAll("\\s+", " ").trim();
		return x;
	}

	public class SongInfo {
		public Long mySongId;
		public int tocadas;
		public String isrc;

		public SongInfo(Long id, int tocadas, String isrc) {
			this.mySongId = id;
			this.tocadas = tocadas;
			this.isrc = isrc;
		}
	}

}
