package com.ml.repository;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;

import org.slf4j.LoggerFactory;
import org.json.JSONObject;
import org.json.JSONArray;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ml.model.report.ArtistDigital;
import com.ml.model.report.ChartDigital;
import com.ml.model.report.ChatUser;
import com.ml.model.report.City;
import com.ml.model.report.CityData;
import com.ml.model.report.Country;
import com.ml.model.report.Format;
import com.ml.model.report.Platform;
import com.ml.model.report.PlatformTop;
import com.ml.model.report.Song;
import com.ml.model.report.SongCities;
import com.ml.model.report.SongCountries;
import com.ml.model.report.SongDigital;
import com.ml.model.report.SongFormat;
import com.ml.model.report.SongPlaylist;
import com.ml.model.report.SongRecommendation;
import com.ml.model.report.SongScore;
import com.ml.model.report.SongStatsDigital;
import com.ml.model.report.SongTiktok;
import com.ml.model.report.StationChart;
import com.ml.model.report.TrendingSongs;

@Repository
public class ExecProcRepository {

	Logger logger = LoggerFactory.getLogger(ExecProcRepository.class);

	@Autowired
	private EntityManager entityManager;

	public List<StationChart> getStationsbySong(Long song) {
		StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("GET_SONG_CHART_DATA");
		storedProcedure.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
		// storedProcedure.registerStoredProcedureParameter( 2, Class.class,
		// ParameterMode.REF_CURSOR );

		storedProcedure.setParameter(1, Long.toString(song));
		storedProcedure.execute();
		@SuppressWarnings("unchecked")
		List<Object[]> results = storedProcedure.getResultList();

		List<StationChart> lstres = results.stream().map(result -> new StationChart(result[0].toString(),
				result[1].toString(), result[2].toString(), result[3].toString(), result[4].toString()))
				.collect(Collectors.toList());
		return lstres;

	}

	public SongDigital getDigitalbySong(Long song) {
		StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("GET_SONG_DIGITAL_DATA");
		storedProcedure.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
		// storedProcedure.registerStoredProcedureParameter( 2, Class.class,
		// ParameterMode.REF_CURSOR );

		storedProcedure.setParameter(1, Long.toString(song));
		storedProcedure.execute();
		@SuppressWarnings("unchecked")
		List<Object[]> results = storedProcedure.getResultList();

		SongDigital lstres = results
				.stream().map(result -> new SongDigital(result[0].toString(), result[1].toString(),
						result[2].toString(), result[3].toString(), result[4].toString()))
				.collect(Collectors.toList()).get(0);
		return lstres;

	}

	public List<ChartDigital> getDigitalChart(Integer format, Integer country, String CRG, Integer custom,
			Integer genre, Integer city, Integer radiooff) {
		StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("GET_CHART_DATA_DIGITAL");
		storedProcedure.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter(3, String.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter(4, Integer.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter(5, Integer.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter(6, Integer.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter(7, Integer.class, ParameterMode.IN);
		// storedProcedure.registerStoredProcedureParameter( 2, Class.class,
		// ParameterMode.REF_CURSOR );

		storedProcedure.setParameter(1, format);
		storedProcedure.setParameter(2, country);
		storedProcedure.setParameter(3, CRG);
		storedProcedure.setParameter(4, custom);
		storedProcedure.setParameter(5, genre);
		storedProcedure.setParameter(6, city);
		storedProcedure.setParameter(7, radiooff);
		storedProcedure.execute();
		@SuppressWarnings("unchecked")
		List<Object[]> results = storedProcedure.getResultList();

		List<ChartDigital> lstres = results.stream()
				.map(result -> new ChartDigital(result[0].toString(), result[1].toString(), result[2].toString(),
						result[3].toString(), result[4].toString(), result[5].toString(), result[6].toString(),
						result[7].toString(), result[8].toString(), result[9].toString(), result[10].toString(),
						result[11].toString(), result[12].toString(), result[13].toString(), result[14].toString(),
						result[15].toString(), result[16].toString(), result[17].toString(), result[18].toString(),
						result[22].toString(), result[23].toString(), result[20].toString(), result[24].toString(),
						result[29].toString(), result[30].toString(), result[31].toString(), result[28].toString()))
				.collect(Collectors.toList());
		return lstres;

	}

	public List<ChartDigital> getDigitalChart(Integer format, Integer country, String CRG, Integer custom,
			Integer genre, Integer city, Integer radiooff, Integer top) {
		StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("GET_CHART_DATA_DIGITAL");
		storedProcedure.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter(3, String.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter(4, Integer.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter(5, Integer.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter(6, Integer.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter(7, Integer.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter(8, Integer.class, ParameterMode.IN);
		// storedProcedure.registerStoredProcedureParameter( 2, Class.class,
		// ParameterMode.REF_CURSOR );

		storedProcedure.setParameter(1, format);
		storedProcedure.setParameter(2, country);
		storedProcedure.setParameter(3, CRG);
		storedProcedure.setParameter(4, custom);
		storedProcedure.setParameter(5, genre);
		storedProcedure.setParameter(6, city);
		storedProcedure.setParameter(7, radiooff);
		storedProcedure.setParameter(8, top);
		storedProcedure.execute();
		@SuppressWarnings("unchecked")
		List<Object[]> results = storedProcedure.getResultList();

		List<ChartDigital> lstres = results.stream()
				.map(result -> new ChartDigital(result[0].toString(), result[1].toString(), result[2].toString(),
						result[3].toString(), result[4].toString(), result[5].toString(), result[6].toString(),
						result[7].toString(), result[8].toString(), result[9].toString(), result[10].toString(),
						result[11].toString(), result[12].toString(), result[13].toString(), result[14].toString(),
						result[15].toString(), result[16].toString(), result[17].toString(), result[18].toString(),
						result[22].toString(), result[23].toString(), result[20].toString(), result[24].toString(),
						result[29].toString(), result[30].toString(), result[31].toString(), result[28].toString()))
				.collect(Collectors.toList());
		return lstres;

	}

	public List<Map<String, Object>> getDigitalChartVideo(Integer format, Integer country, String CRG, Integer custom,
			Integer genre, Integer city, Integer radiooff, Integer top) {

		StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("GET_CHART_DATA_DIGITAL_VIDEO");

		storedProcedure.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter(3, String.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter(4, Integer.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter(5, Integer.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter(6, Integer.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter(7, Integer.class, ParameterMode.IN);

		storedProcedure.setParameter(1, format);
		storedProcedure.setParameter(2, country);
		storedProcedure.setParameter(3, CRG);
		storedProcedure.setParameter(4, genre);
		storedProcedure.setParameter(5, city);
		storedProcedure.setParameter(6, radiooff);
		storedProcedure.setParameter(7, top);

		storedProcedure.execute();

		@SuppressWarnings("unchecked")
		List<Object[]> results = storedProcedure.getResultList();

		String[] columns = { "cs_song", "CRG", "song", "artists", "label", "spotify_streams_total",
				"tiktok_views_total", "youtube_video_views_total", "youtube_short_views_total", "shazams_total",
				"soundcloud_streams_total", "pan_streams", "audiencie_total", "spins_total", "score", "rk_total",
				"tw_spins", "tw_aud", "rk_songstats", "spotify_streams", "entid", "Item_length_sec", "avatar", "url",
				"spotify_id", "rk_audience", "rk_spins", "fk_track", "rk_lw", "movement", "lw_score", "spotifyartistid",
				"youtubeid", "start_time_sec", "end_time_sec" };

		return results.stream().map(row -> {
			Map<String, Object> item = new LinkedHashMap<>();

			for (int i = 0; i < columns.length; i++) {
				item.put(columns[i], row[i]);
			}
			return item;
		}).collect(Collectors.toList());
	}

	public SongStatsDigital getDigitalSong(Integer cs_song, Integer fmt, Integer country) {
		StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("GET_SONG_DATA");
		storedProcedure.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter(3, Integer.class, ParameterMode.IN);
		// storedProcedure.registerStoredProcedureParameter( 2, Class.class,
		// ParameterMode.REF_CURSOR );

		storedProcedure.setParameter(1, cs_song);
		storedProcedure.setParameter(2, fmt);
		storedProcedure.setParameter(3, country);
		storedProcedure.execute();
		@SuppressWarnings("unchecked")
		List<Object[]> results = storedProcedure.getResultList();

		SongStatsDigital lstres = results.stream()
				.map(result -> new SongStatsDigital(result[0].toString(), result[1].toString(), result[2].toString(),
						result[3].toString(), result[4].toString(), result[6].toString(), result[7].toString(),
						result[8].toString(), result[9].toString(), result[10].toString(), result[11].toString(),
						result[12].toString(), result[13].toString(), result[14].toString(), result[15].toString(),
						result[16].toString(), result[18].toString(), result[19].toString(), result[20].toString(),
						result[21].toString(), result[22].toString(), result[23].toString(), result[24].toString(),
						result[25].toString(), result[26].toString(), result[27].toString(), result[28].toString(),
						result[29].toString(), result[30].toString(), result[31].toString(), result[32].toString(),
						result[33].toString(), result[38].toString(), result[39].toString(), result[40].toString(),
						result[41].toString(), result[42].toString(), result[43].toString(), result[44].toString(),
						result[45].toString(), result[46].toString(), result[51].toString(), result[52].toString(),
						result[53].toString(), result[54].toString(), result[60].toString(), result[61].toString(),
						result[62].toString(), result[65].toString(), result[66].toString(), result[67].toString(),
						result[70].toString(), result[71].toString(), result[72].toString(), result[73].toString(),
						result[74].toString(), result[75].toString(), result[76].toString(), result[77].toString(),
						result[78].toString(), result[79].toString(), result[80].toString(), result[81].toString(),
						result[82].toString(), result[83].toString(), result[84].toString()

				)).collect(Collectors.toList()).get(0);
		return lstres;

	}

	public Song getDigitalSong(Integer cs_song) {
		StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("GET_SONG_REPORTING_DATA");
		storedProcedure.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
		// storedProcedure.registerStoredProcedureParameter( 2, Class.class,
		// ParameterMode.REF_CURSOR );

		storedProcedure.setParameter(1, cs_song);
		storedProcedure.execute();
		@SuppressWarnings("unchecked")
		List<Object[]> results = storedProcedure.getResultList();
		Song lstres = results.stream()
				.map(result -> new Song(result[0].toString(), result[5].toString(), result[5].toString(),
						result[1].toString(), result[2].toString(), result[3].toString(), result[6].toString()))
				.collect(Collectors.toList()).get(0);
		return lstres;

	}

	public SongScore getDigitalSongData(Integer cs_song, Integer country) {
		StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("GET_SONG_DIGITAL");
		storedProcedure.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN);
		// storedProcedure.registerStoredProcedureParameter( 2, Class.class,
		// ParameterMode.REF_CURSOR );

		storedProcedure.setParameter(1, cs_song);
		storedProcedure.setParameter(2, country);
		storedProcedure.execute();
		@SuppressWarnings("unchecked")
		List<Object[]> results = storedProcedure.getResultList();
		SongScore lstres = results.stream()
				.map(result -> new SongScore(result[0].toString(), result[1].toString(), result[2].toString(),
						result[3].toString(), result[4].toString(), result[5].toString(), result[6].toString()))
				.collect(Collectors.toList()).get(0);
		return lstres;

	}

	public List<SongFormat> getDigitalSongFormat(Integer cs_song, Integer country) {
		StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("GET_SONG_CHARTS_BY_FORMAT");
		storedProcedure.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN);
		// storedProcedure.registerStoredProcedureParameter( 2, Class.class,
		// ParameterMode.REF_CURSOR );

		storedProcedure.setParameter(1, cs_song);
		storedProcedure.setParameter(2, country);
		storedProcedure.execute();
		@SuppressWarnings("unchecked")
		List<Object[]> results = storedProcedure.getResultList();
		List<SongFormat> lstres = results.stream().map(result -> new SongFormat(result[0].toString(),
				result[1].toString(), result[2].toString(), result[3].toString(), result[4].toString()))
				.collect(Collectors.toList());
		return lstres;

	}

	public List<Country> getCountries() {
		StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("GET_COUNTRIES");
		// storedProcedure.registerStoredProcedureParameter( 1, Integer.class,
		// ParameterMode.IN);
		// storedProcedure.registerStoredProcedureParameter( 2, Class.class,
		// ParameterMode.REF_CURSOR );

		// storedProcedure.setParameter(1, cs_song);
		storedProcedure.execute();
		@SuppressWarnings("unchecked")
		List<Object[]> results = storedProcedure.getResultList();
		List<Country> lstres = results.stream()
				.map(result -> new Country(result[0].toString(), result[1].toString(), result[2].toString()))
				.collect(Collectors.toList());
		return lstres;

	}

	public List<Format> getFormats(Integer country) {
		StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("GET_FORMAT_BY_COUNTRY");
		storedProcedure.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
		// storedProcedure.registerStoredProcedureParameter( 2, Class.class,
		// ParameterMode.REF_CURSOR );

		storedProcedure.setParameter(1, country);
		storedProcedure.execute();
		@SuppressWarnings("unchecked")
		List<Object[]> results = storedProcedure.getResultList();
		List<Format> lstres = results.stream().map(result -> new Format(result[0].toString(), result[1].toString()))
				.collect(Collectors.toList());
		return lstres;

	}

	public List<TrendingSongs> getTrendingTop(Integer format, Integer country) {
		StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("GET_TRENDING_DIGITAL");
		storedProcedure.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN);
		// storedProcedure.registerStoredProcedureParameter( 2, Class.class,
		// ParameterMode.REF_CURSOR );

		storedProcedure.setParameter(1, format);
		storedProcedure.setParameter(2, country);
		storedProcedure.execute();
		@SuppressWarnings("unchecked")
		List<Object[]> results = storedProcedure.getResultList();

		List<TrendingSongs> lstres = results.stream()
				.map(result -> new TrendingSongs(result[0].toString(), result[1].toString(), result[2].toString(),
						result[3].toString(), result[4].toString(), result[5].toString(), result[6].toString(),
						result[7].toString(), result[8].toString(), "", "0"))
				.collect(Collectors.toList());
		return lstres;

	}

	public List<TrendingSongs> getTrendingDebut(Integer format, Integer country, String CRG, Integer custom,
			Integer genre, Integer city) {
		StoredProcedureQuery storedProcedure = entityManager
				.createStoredProcedureQuery("GET_TRENDING_DIGITAL_DEBUT_2025");
		storedProcedure.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter(3, String.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter(4, Integer.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter(5, Integer.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter(6, Integer.class, ParameterMode.IN);
		// storedProcedure.registerStoredProcedureParameter( 2, Class.class,
		// ParameterMode.REF_CURSOR );

		storedProcedure.setParameter(1, format);
		storedProcedure.setParameter(2, country);
		storedProcedure.setParameter(3, CRG);
		storedProcedure.setParameter(4, custom);
		storedProcedure.setParameter(5, genre);
		storedProcedure.setParameter(6, city);
		storedProcedure.execute();
		@SuppressWarnings("unchecked")
		List<Object[]> results = storedProcedure.getResultList();

		List<TrendingSongs> lstres = results.stream()
				.map(result -> new TrendingSongs(result[0].toString(), result[1].toString(), result[2].toString(),
						result[3].toString(), result[4].toString(), result[5].toString(), result[6].toString(),
						result[7].toString(), result[8].toString(), result[9].toString(), result[10].toString()))
				.collect(Collectors.toList());
		return lstres;

	}

	public List<ArtistDigital> getTopArtist(Integer format, Integer country, Integer city) {
		StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("GET_TOP_ARTIST");
		storedProcedure.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter(3, Integer.class, ParameterMode.IN);
		storedProcedure.setParameter(1, format);
		storedProcedure.setParameter(2, country);
		storedProcedure.setParameter(3, city);
		storedProcedure.execute();
		@SuppressWarnings("unchecked")
		List<Object[]> results = storedProcedure.getResultList();

		List<ArtistDigital> lstres = results.stream()
				.map(result -> new ArtistDigital(result[0].toString(), result[1].toString(), result[2].toString(),
						result[3].toString(), result[4].toString(), result[5].toString(), result[6].toString(),
						result[7].toString(), result[8].toString(), result[9].toString(), result[19].toString(),
						result[26].toString(), result[27].toString(), result[28].toString(), result[29].toString(),
						result[30].toString()))
				.collect(Collectors.toList());
		return lstres;
	}

	public List<Format> getFormatsInt() {
		StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("GET_FORMAT_INT");
		// storedProcedure.registerStoredProcedureParameter( 1, Integer.class,
		// ParameterMode.IN);
		// storedProcedure.registerStoredProcedureParameter( 2, Class.class,
		// ParameterMode.REF_CURSOR );

		// storedProcedure.setParameter(1, country);
		storedProcedure.execute();
		@SuppressWarnings("unchecked")
		List<Object[]> results = storedProcedure.getResultList();
		List<Format> lstres = results.stream().map(result -> new Format(result[0].toString(), result[1].toString()))
				.collect(Collectors.toList());
		return lstres;
	}

	public List<PlatformTop> getTopPlatform(String platform, Integer format, Integer country) {
		StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("GET_TOP_BY_PLATFORM");
		storedProcedure.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter(3, Integer.class, ParameterMode.IN);
		storedProcedure.setParameter(1, platform);
		storedProcedure.setParameter(2, format);
		storedProcedure.setParameter(3, country);
		storedProcedure.execute();
		@SuppressWarnings("unchecked")
		List<Object[]> results = storedProcedure.getResultList();

		List<PlatformTop> lstres = results.stream()
				.map(result -> new PlatformTop(result[0].toString(), result[1].toString(), result[2].toString(),
						result[3].toString(), result[4].toString(), result[5].toString(), result[6].toString(),
						result[7].toString(), result[8].toString()))
				.collect(Collectors.toList());
		return lstres;
	}

	public List<Platform> getPlatforms() {
		StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("GET_PLATFORM");
		// storedProcedure.registerStoredProcedureParameter( 1, Integer.class,
		// ParameterMode.IN);
		// storedProcedure.registerStoredProcedureParameter( 2, Class.class,
		// ParameterMode.REF_CURSOR );

		// storedProcedure.setParameter(1, country);
		storedProcedure.execute();
		@SuppressWarnings("unchecked")
		List<Object[]> results = storedProcedure.getResultList();
		List<Platform> lstres = results.stream().map(result -> new Platform(result[0].toString(), result[1].toString(),
				result[2].toString(), result[3].toString())).collect(Collectors.toList());
		return lstres;
	}

	public List<Song> getSearchSong(String search) {
		StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("GET_SEARCH_SONG");
		storedProcedure.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
		// storedProcedure.registerStoredProcedureParameter( 2, Class.class,
		// ParameterMode.REF_CURSOR );

		storedProcedure.setParameter(1, search);
		storedProcedure.execute();
		@SuppressWarnings("unchecked")
		List<Object[]> results = storedProcedure.getResultList();
		List<Song> lstres = results.stream()
				.map(result -> new Song(result[0].toString(), result[1].toString(), result[1].toString(),
						result[2].toString(), result[3].toString(), result[4].toString(), result[5].toString()))
				.collect(Collectors.toList());
		return lstres;

	}

	public List<CityData> getCityData(Integer cs_song, Integer country) {
		StoredProcedureQuery storedProcedure = entityManager
				.createStoredProcedureQuery("GET_SONG_DIGITAL_DATA_BY_CITY");
		storedProcedure.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN);
		storedProcedure.setParameter(1, cs_song);
		storedProcedure.setParameter(2, country);
		storedProcedure.execute();
		@SuppressWarnings("unchecked")
		List<Object[]> results = storedProcedure.getResultList();
		List<CityData> cities = results.stream()
				.map(result -> new CityData(result[0].toString(), result[1].toString(), result[2].toString(),
						result[3].toString(), result[4].toString(), result[5].toString(), result[9].toString(),
						result[10].toString(), result[11].toString()))
				.collect(Collectors.toList());
		return cities;

	}

	public List<City> getCities(String country, String CRG) {
		StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("GET_CITIES_BY_COUNTRY");
		storedProcedure.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
		storedProcedure.setParameter(1, country);
		storedProcedure.setParameter(2, CRG);
		storedProcedure.execute();
		@SuppressWarnings("unchecked")
		List<Object[]> results = storedProcedure.getResultList();
		List<City> cities = results.stream()
				.map(result -> new City(result[0].toString(), result[1].toString(), result[2].toString()))
				.collect(Collectors.toList());
		return cities;

	}

	public List<SongCities> getCitiesRadio(String id, String country) {
		StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("GET_CITIES_RADIO_BY_COUNTRY");
		storedProcedure.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
		storedProcedure.setParameter(1, id);
		storedProcedure.setParameter(2, country);
		storedProcedure.execute();
		@SuppressWarnings("unchecked")
		List<Object[]> results = storedProcedure.getResultList();
		List<SongCities> cities = results.stream()
				.map(result -> new SongCities(result[0].toString(), result[1].toString(), result[2].toString()))
				.collect(Collectors.toList());
		return cities;

	}

	public List<SongCountries> getCountriesRadio(String id) {
		StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("GET_COUNTRIES_RADIO");
		storedProcedure.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
		storedProcedure.setParameter(1, id);
		storedProcedure.execute();
		@SuppressWarnings("unchecked")
		List<Object[]> results = storedProcedure.getResultList();
		List<SongCountries> cities = results.stream().map(result -> new SongCountries(result[0].toString(),
				result[1].toString(), result[2].toString(), result[3].toString(), result[4].toString()))
				.collect(Collectors.toList());
		return cities;

	}

	public List<SongPlaylist> getSongPlaylist(String id, String type) {
		StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("GET_PLAYLISTS_SONG");
		storedProcedure.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
		storedProcedure.setParameter(1, id);
		storedProcedure.setParameter(2, type);
		storedProcedure.execute();
		@SuppressWarnings("unchecked")
		List<Object[]> results = storedProcedure.getResultList();
		List<SongPlaylist> plays = results.stream()
				.map(result -> new SongPlaylist(result[0].toString(), result[1].toString(), result[2].toString(),
						result[3].toString(), result[4].toString(), result[5].toString(), result[6].toString(),
						result[7].toString(), result[8].toString(), result[9].toString()))
				.collect(Collectors.toList());
		return plays;

	}

	public List<SongTiktok> getSongTiktok(String id) {
		StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("GET_TIKTOKS_SONG");
		storedProcedure.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
		storedProcedure.setParameter(1, id);
		storedProcedure.execute();
		@SuppressWarnings("unchecked")
		List<Object[]> results = storedProcedure.getResultList();
		List<SongTiktok> plays = results.stream()
				.map(result -> new SongTiktok(result[0].toString(), result[1].toString(), result[2].toString(),
						result[3].toString(), result[4].toString(), result[5].toString(), result[6].toString(),
						result[7].toString(), result[8].toString()))
				.collect(Collectors.toList());
		return plays;

	}

	public List<SongRecommendation> getSongRecommendation(String id) {
		StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("GET_SONG_DATA_RECOMMENDATION");
		storedProcedure.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
		storedProcedure.setParameter(1, id);
		storedProcedure.execute();
		@SuppressWarnings("unchecked")
		List<Object[]> results = storedProcedure.getResultList();
		List<SongRecommendation> plays = results.stream()
				.map(result -> new SongRecommendation(result[0].toString(), result[1].toString(), result[2].toString(),
						result[3].toString(), result[4].toString(), result[5].toString(), result[6].toString(),
						result[7].toString(), result[8].toString(), result[9].toString(), result[10].toString(),
						result[11].toString(), result[12].toString(), result[13].toString(), result[14].toString()))
				.collect(Collectors.toList());
		return plays;

	}

	public JSONObject getCsSong(String cs_song) {
		JSONObject resultJson = new JSONObject();
		try {
			// Llamar al procedimiento almacenado
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery("GET_CsSong");
			query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
			query.setParameter(1, cs_song);

			// Ejecutar
			query.execute();

			// Obtener el resultado (el SP devuelve un JSON en una sola columna)
			Object result = query.getSingleResult(); // normalmente String

			if (result != null) {
				resultJson = new JSONObject(result.toString());
			} else {
				resultJson.put("message", "No se encontró resultado");
			}

		} catch (Exception e) {
			resultJson.put("error", e.getMessage());
		}

		return resultJson;

	}

	public JSONObject getSpotifyId(String cs_song) {
		JSONObject resultJson = new JSONObject();
		try {
			// Llamar al procedimiento almacenado
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery("GET_SpotifyId");
			query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
			query.setParameter(1, cs_song);

			// Ejecutar
			query.execute();

			// Obtener el resultado (el SP devuelve un JSON en una sola columna)
			Object result = query.getSingleResult(); // normalmente String

			if (result != null) {
				resultJson = new JSONObject(result.toString());
			} else {
				resultJson.put("message", "No se encontró resultado");
			}

		} catch (Exception e) {
			resultJson.put("error", e.getMessage());
		}

		return resultJson;

	}

	public String getLastFriday() {
		LocalDate today = LocalDate.now();
		LocalDate lastFriday = today.with(TemporalAdjusters.previous(DayOfWeek.FRIDAY));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

		return lastFriday.format(formatter);
	}

	public JSONObject getDataArtist(String spotifyid) {
		JSONObject resultJson = new JSONObject();
		try {
			// Llamar al procedimiento almacenado
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery("GET_DATA_ARTIST");
			query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
			query.setParameter(1, spotifyid);

			// Ejecutar
			query.execute();

			// Obtener el resultado (el SP devuelve un JSON en una sola columna)
			Object result = query.getSingleResult(); // normalmente String

			if (result != null) {
				resultJson = new JSONObject(result.toString());
			} else {
				resultJson.put("message", "No se encontró resultado");
			}

		} catch (Exception e) {
			resultJson.put("error", e.getMessage());
		}

		return resultJson;

	}

	public JSONObject getDataArtistBySong(String cs_song) {
		JSONObject resultJson = new JSONObject();
		try {
			// Llamar al procedimiento almacenado
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery("GET_DATA_ARTIST_BY_SONG");
			query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
			query.setParameter(1, cs_song);

			// Ejecutar
			query.execute();

			// Obtener el resultado (el SP devuelve un JSON en una sola columna)
			Object result = query.getSingleResult(); // normalmente String

			if (result != null) {
				resultJson = new JSONObject(result.toString());
			} else {
				resultJson.put("message", "No se encontró resultado");
			}

		} catch (Exception e) {
			resultJson.put("error", e.getMessage());
		}

		return resultJson;

	}

	public JSONObject getDataSpotifyArtistsId(String cs_song) {
		JSONObject resultJson = new JSONObject();
		try {
			// Llamar al procedimiento almacenado
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery("GET_DATA_ARTIST_BY_SONG");
			query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
			query.setParameter(1, cs_song);

			// Ejecutar
			query.execute();

			// Obtener el resultado (el SP devuelve un JSON en una sola columna)
			Object result = query.getSingleResult(); // normalmente String

			if (result != null) {
				resultJson = new JSONObject(result.toString());
			} else {
				resultJson.put("message", "No se encontró resultado");
			}

		} catch (Exception e) {
			resultJson.put("error", e.getMessage());
		}

		return resultJson;

	}

	public JSONArray getVideos() {
		JSONArray resultArray = new JSONArray();
		try {
			// Llamar al procedimiento almacenado
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery("GET_LAST_VIDEOS");

			// Ejecutar
			query.execute();
			Object result = query.getSingleResult(); // JSON array como string

			if (result != null) {
				resultArray = new JSONArray(result.toString());
			}

		} catch (Exception e) {
			resultArray.put(new JSONObject().put("error", e.getMessage()));
		}

		return resultArray;

	}

	public JSONArray getTopDigital(Integer country) {
		JSONArray resultArray = new JSONArray();
		try {
			// Llamar al procedimiento almacenado
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery("GET_TOP_CHART_DATA_DIGITAL");
			query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
			query.setParameter(1, country);

			// Ejecutar
			query.execute();
			Object result = query.getSingleResult(); // JSON array como string

			if (result != null) {
				resultArray = new JSONArray(result.toString());
			}

		} catch (Exception e) {
			resultArray.put(new JSONObject().put("error", e.getMessage()));
		}

		return resultArray;

	}

	public JSONArray getTopRadio(Integer country, Integer format) {
		JSONArray resultArray = new JSONArray();
		try {
			// Llamar al procedimiento almacenado
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery("GET_TOP_CHART_DATA_RADIO");
			query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
			query.registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN);
			query.setParameter(1, country);
			query.setParameter(2, format);

			// Ejecutar
			query.execute();
			Object result = query.getSingleResult(); // JSON array como string

			if (result != null) {
				resultArray = new JSONArray(result.toString());
			}

		} catch (Exception e) {
			resultArray.put(new JSONObject().put("error", e.getMessage()));
		}

		return resultArray;

	}

	public JSONObject getTopRadioFormat(Integer format, Integer country) {
		JSONObject resultJson = new JSONObject();
		try {
			// Llamar al procedimiento almacenado
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery("GET_RADIO_FORMAT");
			query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
			query.registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN);
			query.setParameter(1, format);
			query.setParameter(2, country);

			// Ejecutar
			query.execute();

			// Obtener el resultado (el SP devuelve un JSON en una sola columna)
			Object result = query.getSingleResult(); // normalmente String

			if (result != null) {
				resultJson = new JSONObject(result.toString());
			} else {
				resultJson.put("message", "No se encontró resultado");
			}

		} catch (Exception e) {
			resultJson.put("error", e.getMessage());
		}

		return resultJson;

	}

	public JSONObject getTopFormat(Integer country, Integer option) {
		JSONObject resultJson = new JSONObject();
		try {
			// Llamar al procedimiento almacenado
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery("GET_TOP_FORMAT");
			query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
			query.registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN);
			query.setParameter(1, country);
			query.setParameter(2, option);

			// Ejecutar
			query.execute();

			// Obtener el resultado (el SP devuelve un JSON en una sola columna)
			Object result = query.getSingleResult(); // normalmente String

			if (result != null) {
				resultJson = new JSONObject(result.toString());
			} else {
				resultJson.put("message", "No se encontró resultado");
			}

		} catch (Exception e) {
			resultJson.put("error", e.getMessage());
		}

		return resultJson;
	}

	public JSONArray getSongsArtist(String spotifyid, Integer country) {
		JSONArray resultArray = new JSONArray();
		try {
			// Llamar al procedimiento almacenado
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery("GET_SONGS_ARTIST");
			query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
			query.registerStoredProcedureParameter(2, Integer.class, ParameterMode.IN);
			query.setParameter(1, spotifyid);
			query.setParameter(2, country);

			// Ejecutar
			query.execute();
			Object result = query.getSingleResult(); // JSON array como string

			if (result != null) {
				resultArray = new JSONArray(result.toString());
			}

		} catch (Exception e) {
			resultArray.put(new JSONObject().put("error", e.getMessage()));
		}

		return resultArray;

	}

	public JSONArray getSongsArtistCities(Integer country, String spotifyid) {
		JSONArray resultArray = new JSONArray();
		try {
			// Llamar al procedimiento almacenado
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery("GET_DATA_ARTIST_CITY");
			query.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
			query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
			query.setParameter(1, country);
			query.setParameter(2, spotifyid);

			// Ejecutar
			query.execute();
			Object result = query.getSingleResult(); // JSON array como string

			if (result != null) {
				resultArray = new JSONArray(result.toString());
			}

		} catch (Exception e) {
			resultArray.put(new JSONObject().put("error", e.getMessage()));
		}

		return resultArray;

	}

	public String getSongsArtistCitiesRelated(Integer country, String spotifyid) {
		try {
			Query query = entityManager
					.createNativeQuery("EXEC dbo.GET_ARTIST_CITY_RELATED @spotify_id  = ?, @country = ?");
			query.setParameter(1, spotifyid);
			query.setParameter(2, country);

			Object result = query.getSingleResult();

			return result != null ? result.toString() : "{}";

		} catch (Exception e) {
			return "{\"error\":\"" + e.getMessage() + "\"}";
		}
	}

	public List<ChatUser> getUserChat(String phone) {
		StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("GET_CHAT_USER");
		storedProcedure.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
		storedProcedure.setParameter(1, phone);
		storedProcedure.execute();
		@SuppressWarnings("unchecked")
		List<Object[]> results = storedProcedure.getResultList();
		List<ChatUser> users = results.stream().map(result -> new ChatUser(result[0].toString(), result[1].toString(),
				result[2].toString(), result[3].toString(), result[4].toString(), result[5].toString()))
				.collect(Collectors.toList());
		return users;

	}

	public void setLog(String phone, String message, String response) {
		StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("SEG_LOG_CHAT");
		storedProcedure.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter(3, String.class, ParameterMode.IN);
		storedProcedure.setParameter(1, phone);
		storedProcedure.setParameter(2, message);
		storedProcedure.setParameter(3, response);
		storedProcedure.execute();
	}

	public void setLogMessage(String phone, String contact, Integer country, Integer format, String message) {
		StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("SET_LOG_CHAT");
		storedProcedure.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter(3, Integer.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter(4, Integer.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter(5, String.class, ParameterMode.IN);
		storedProcedure.setParameter(1, phone);
		storedProcedure.setParameter(2, contact);
		storedProcedure.setParameter(3, country);
		storedProcedure.setParameter(4, format);
		storedProcedure.setParameter(5, message);
		storedProcedure.execute();
	}

	public void setWhatsContact(String phone, String nombre, String apellido, String rol, Integer tipo, String paises) {
		StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("SET_WHATS_CONTACT");
		storedProcedure.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter(3, String.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter(4, String.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter(5, Integer.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter(6, String.class, ParameterMode.IN);
		storedProcedure.setParameter(1, phone);
		storedProcedure.setParameter(2, nombre);
		storedProcedure.setParameter(3, apellido);
		storedProcedure.setParameter(4, rol);
		storedProcedure.setParameter(5, tipo);
		storedProcedure.setParameter(6, paises);
		storedProcedure.execute();
	}

	public void delWhatsContact(String phone) {
		StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("DEL_WHATS_CONTACT");
		storedProcedure.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
		storedProcedure.setParameter(1, phone);
		storedProcedure.execute();
	}

	public void setLogSong(Integer userid, String spotifyid, Boolean isArtist) {
		StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("SET_LOG_VIEW");
		storedProcedure.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter(3, Boolean.class, ParameterMode.IN);
		storedProcedure.setParameter(1, userid);
		storedProcedure.setParameter(2, spotifyid);
		storedProcedure.setParameter(3, isArtist);
		storedProcedure.execute();
	}

	public JSONObject getUserLog(String phone) {
		JSONObject resultJson = new JSONObject();
		try {
			// Llamar al procedimiento almacenado
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery("GET_CHAT_PHONE");
			query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
			query.setParameter(1, phone);

			// Ejecutar
			query.execute();

			// Obtener el resultado (el SP devuelve un JSON en una sola columna)
			Object result = query.getSingleResult(); // normalmente String

			if (result != null) {
				resultJson = new JSONObject(result.toString());
			} else {
				resultJson.put("message", "No se encontró resultado");
			}

		} catch (Exception e) {
			resultJson.put("error", e.getMessage());
		}

		return resultJson;

	}

	public JSONArray getContactByCountry(String country) {
		JSONArray resultArray = new JSONArray();

		try {
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery("GET_WHATS_CONTACT");

			query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
			query.setParameter(1, country);

			query.execute();

			// El SP SIEMPRE devuelve 1 fila con una columna (json_result)
			Object result = query.getSingleResult();

			if (result != null) {
				// Aquí el resultado es un String JSON
				resultArray = new JSONArray(result.toString());
			}

		} catch (Exception e) {
			JSONObject error = new JSONObject();
			error.put("error", e.getMessage());
			resultArray.put(error);
		}

		return resultArray;

	}

	public JSONArray getAllContacts() {
		JSONArray resultArray = new JSONArray();

		try {
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery("GETALL_WHATS_CONTACT");

			query.execute();

			// El SP SIEMPRE devuelve 1 fila con una columna (json_result)
			Object result = query.getSingleResult();

			if (result != null) {
				// Aquí el resultado es un String JSON
				resultArray = new JSONArray(result.toString());
			}

		} catch (Exception e) {
			JSONObject error = new JSONObject();
			error.put("error", e.getMessage());
			resultArray.put(error);
		}

		return resultArray;

	}

	public JSONArray getvsSongs(String cs_song1, String cs_song2) {
		JSONArray resultArray = new JSONArray();

		try {
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery("GET_VS_SONG");

			query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
			query.setParameter(1, cs_song1);
			query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
			query.setParameter(2, cs_song2);

			query.execute();

			// El SP SIEMPRE devuelve 1 fila con una columna (json_result)
			Object result = query.getSingleResult();

			if (result != null) {
				// Aquí el resultado es un String JSON
				resultArray = new JSONArray(result.toString());
			}

		} catch (Exception e) {
			JSONObject error = new JSONObject();
			error.put("error", e.getMessage());
			resultArray.put(error);
		}

		return resultArray;

	}

	public JSONArray getvsSongsPlaylists(String cs_song1, String cs_song2) {
		JSONArray resultArray = new JSONArray();

		try {
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery("GET_VS_SONG_PLAYLIST");

			query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
			query.setParameter(1, cs_song1);
			query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
			query.setParameter(2, cs_song2);

			query.execute();

			// El SP SIEMPRE devuelve 1 fila con una columna (json_result)
			Object result = query.getSingleResult();

			if (result != null) {
				// Aquí el resultado es un String JSON
				resultArray = new JSONArray(result.toString());
			}

		} catch (Exception e) {
			JSONObject error = new JSONObject();
			error.put("error", e.getMessage());
			resultArray.put(error);
		}

		return resultArray;

	}

	public JSONArray getvsSongsTiktoks(String cs_song1, String cs_song2) {
		JSONArray resultArray = new JSONArray();

		try {
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery("GET_VS_SONG_TIKTOKERS");

			query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
			query.setParameter(1, cs_song1);
			query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
			query.setParameter(2, cs_song2);

			query.execute();

			// El SP SIEMPRE devuelve 1 fila con una columna (json_result)
			Object result = query.getSingleResult();

			if (result != null) {
				// Aquí el resultado es un String JSON
				resultArray = new JSONArray(result.toString());
			}

		} catch (Exception e) {
			JSONObject error = new JSONObject();
			error.put("error", e.getMessage());
			resultArray.put(error);
		}

		return resultArray;

	}

	public JSONArray getScores(String fmt) {
		JSONArray resultArray = new JSONArray();

		try {
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery("GET_TOP_SONGS");

			query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
			query.setParameter(1, fmt);

			query.execute();

			// El SP SIEMPRE devuelve 1 fila con una columna (json_result)
			Object result = query.getSingleResult();

			if (result != null) {
				// Aquí el resultado es un String JSON
				resultArray = new JSONArray(result.toString());
			}

		} catch (Exception e) {
			JSONObject error = new JSONObject();
			error.put("error", e.getMessage());
			resultArray.put(error);
		}

		return resultArray;

	}

	public JSONArray getTiktokPics(String fmt) {
		JSONArray resultArray = new JSONArray();

		try {
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery("GET_TIKTOKERS_PICKS");

			query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
			query.setParameter(1, fmt);
			query.execute();

			// El SP SIEMPRE devuelve 1 fila con una columna (json_result)
			Object result = query.getSingleResult();

			if (result != null) {
				// Aquí el resultado es un String JSON
				resultArray = new JSONArray(result.toString());
			}

		} catch (Exception e) {
			JSONObject error = new JSONObject();
			error.put("error", e.getMessage());
			resultArray.put(error);
		}

		return resultArray;

	}

	public JSONArray getCuratorPics(String fmt, String type) {
		JSONArray resultArray = new JSONArray();

		try {
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery("GET_CURATORS_PICKS");

			query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
			query.setParameter(1, fmt);
			query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
			query.setParameter(2, type);

			query.execute();

			// El SP SIEMPRE devuelve 1 fila con una columna (json_result)
			Object result = query.getSingleResult();

			if (result != null) {
				// Aquí el resultado es un String JSON
				resultArray = new JSONArray(result.toString());
			}

		} catch (Exception e) {
			JSONObject error = new JSONObject();
			error.put("error", e.getMessage());
			resultArray.put(error);
		}

		return resultArray;

	}

	public JSONArray getPlaylistType() {
		JSONArray resultArray = new JSONArray();

		try {
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery("GET_PLAYLISTS_TYpe");

			query.execute();

			// El SP SIEMPRE devuelve 1 fila con una columna (json_result)
			Object result = query.getSingleResult();

			if (result != null) {
				// Aquí el resultado es un String JSON
				resultArray = new JSONArray(result.toString());
			}

		} catch (Exception e) {
			JSONObject error = new JSONObject();
			error.put("error", e.getMessage());
			resultArray.put(error);
		}

		return resultArray;

	}

	public JSONArray getRelatedArtist() {
		JSONArray resultArray = new JSONArray();

		try {
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery("GET_RELATED_ARTIST");

			query.execute();

			// El SP SIEMPRE devuelve 1 fila con una columna (json_result)
			Object result = query.getSingleResult();

			if (result != null) {
				// Aquí el resultado es un String JSON
				resultArray = new JSONArray(result.toString());
			}

		} catch (Exception e) {
			JSONObject error = new JSONObject();
			error.put("error", e.getMessage());
			resultArray.put(error);
		}

		return resultArray;

	}

	public JSONArray getArtistPlaylistRelated(String artistid, String type) {
		JSONArray resultArray = new JSONArray();

		try {
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery("GET_ARTIST_PLAYLIST_RELATED");

			query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
			query.setParameter(1, artistid);
			query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
			query.setParameter(2, type);

			query.execute();

			// El SP SIEMPRE devuelve 1 fila con una columna (json_result)
			Object result = query.getSingleResult();

			if (result != null) {
				// Aquí el resultado es un String JSON
				resultArray = new JSONArray(result.toString());
			}

		} catch (Exception e) {
			JSONObject error = new JSONObject();
			error.put("error", e.getMessage());
			resultArray.put(error);
		}

		return resultArray;

	}

	public JSONArray getArtistTiktokersRelated(String artistid) {
		JSONArray resultArray = new JSONArray();

		try {
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery("GET_ARTIST_TIKTOKER_RELATED");

			query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
			query.setParameter(1, artistid);

			query.execute();

			// El SP SIEMPRE devuelve 1 fila con una columna (json_result)
			Object result = query.getSingleResult();

			if (result != null) {
				// Aquí el resultado es un String JSON
				resultArray = new JSONArray(result.toString());
			}

		} catch (Exception e) {
			JSONObject error = new JSONObject();
			error.put("error", e.getMessage());
			resultArray.put(error);
		}

		return resultArray;

	}

	public String getArtistRelatedGraph(String artistid) {
		try {
			Query query = entityManager
					.createNativeQuery("EXEC dbo.ADMIN_GET_ARTIST_SIMILAR_GRAPH_JSON @spotify_id = ?");
			query.setParameter(1, artistid);

			Object result = query.getSingleResult();

			return result != null ? result.toString() : "{}";

		} catch (Exception e) {
			return "{\"error\":\"" + e.getMessage() + "\"}";
		}
	}

	public String getArtistRelatedGraphv2(String artistid) {
		try {
			Query query = entityManager
					.createNativeQuery("EXEC dbo.ADMIN_GET_ARTIST_SIMILAR_GRAPH_JSON_LEVEL3 @spotify_id = ?");
			query.setParameter(1, artistid);

			Object result = query.getSingleResult();

			return result != null ? result.toString() : "{}";

		} catch (Exception e) {
			return "{\"error\":\"" + e.getMessage() + "\"}";
		}
	}

	public String getSongHistoricalStreams(Integer cs_song) {
		try {
			Query query = entityManager.createNativeQuery("EXEC dbo.GET_TRACK_HISTORIC_STATS_LAST @cs_song = ?");
			query.setParameter(1, cs_song);

			Object result = query.getSingleResult();

			return result != null ? result.toString() : "{}";

		} catch (Exception e) {
			return "{\"error\":\"" + e.getMessage() + "\"}";
		}
	}
	
	public String getPlaylistData(Integer type,Integer offset,Integer page_size) {
		try {
			Query query = entityManager.createNativeQuery("EXEC dbo.GET_PLAYLISTS @playlist_type = ?,  @offset = ?,  @page_size = ?");
			query.setParameter(1, type);
			query.setParameter(2, offset);
			query.setParameter(3, page_size);

			Object result = query.getSingleResult();

			return result != null ? result.toString() : "{}";

		} catch (Exception e) {
			return "{\"error\":\"" + e.getMessage() + "\"}";
		}
	}
	
	public String getTiktokData(Integer genre,Integer offset,Integer page_size) {
		try {
			Query query = entityManager.createNativeQuery("EXEC dbo.GET_TIKTOK_USERS @genre = ?,  @offset = ?,  @page_size = ?");
			query.setParameter(1, genre);
			query.setParameter(2, offset);
			query.setParameter(3, page_size);

			Object result = query.getSingleResult();

			return result != null ? result.toString() : "{}";

		} catch (Exception e) {
			return "{\"error\":\"" + e.getMessage() + "\"}";
		}
	}

	public String getSongHistoricalStreamsWeekly(Integer cs_song, Integer country, Integer fmt) {
		try {
			Query query = entityManager
					.createNativeQuery("EXEC dbo.GET_TRACK_HISTORIC_STATS_WEEKLY @cs_song = ?, @country = ?, @fmt = ?");
			query.setParameter(1, cs_song);
			query.setParameter(2, country);
			query.setParameter(3, fmt);

			Object result = query.getSingleResult();

			return result != null ? result.toString() : "{}";

		} catch (Exception e) {
			return "{\"error\":\"" + e.getMessage() + "\"}";
		}
	}

	public String getArtistSongs(String artistid) {
		try {
			Query query = entityManager.createNativeQuery("EXEC dbo.GET_ARTIST_SONGS @spotify_id = ?");
			query.setParameter(1, artistid);

			Object result = query.getSingleResult();

			return result != null ? result.toString() : "{}";

		} catch (Exception e) {
			return "{\"error\":\"" + e.getMessage() + "\"}";
		}
	}

	public String getArtistContext(String artistid) {
		try {
			Query query = entityManager.createNativeQuery("EXEC dbo.GET_ARTIST_AI_CONTEXT_JSON_SMART @spotify_id = ?");
			query.setParameter(1, artistid);

			Object result = query.getSingleResult();

			return result != null ? result.toString() : "{}";

		} catch (Exception e) {
			return "{\"error\":\"" + e.getMessage() + "\"}";
		}
	}

	public String getArtistContextPhase(String artistid) {
		try {
			Query query = entityManager
					.createNativeQuery("EXEC dbo.GET_ARTIST_90_DAY_PLAN_CONTEXT_JSON_FAST @spotify_id = ?");
			query.setParameter(1, artistid);

			Object result = query.getSingleResult();

			return result != null ? result.toString() : "{}";

		} catch (Exception e) {
			return "{\"error\":\"" + e.getMessage() + "\"}";
		}
	}

	public JSONArray getArtistRadioRelated(String artistid, String countryid) {
		JSONArray resultArray = new JSONArray();

		try {
			StoredProcedureQuery query = entityManager.createStoredProcedureQuery("GET_ARTIST_STATIONS_RELATED");

			query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
			query.setParameter(1, artistid);
			query.registerStoredProcedureParameter(2, String.class, ParameterMode.IN);
			query.setParameter(2, countryid);
			query.execute();

			// El SP SIEMPRE devuelve 1 fila con una columna (json_result)
			Object result = query.getSingleResult();

			if (result != null) {
				// Aquí el resultado es un String JSON
				resultArray = new JSONArray(result.toString());
			}

		} catch (Exception e) {
			JSONObject error = new JSONObject();
			error.put("error", e.getMessage());
			resultArray.put(error);
		}

		return resultArray;

	}

	public String searchPlaylists(String search, Integer type, Integer offset, Integer pageSize) {
		try {
			Query query = entityManager.createNativeQuery(
				"EXEC dbo.GET_SEARCH_PLAYLISTS @search = ?, @type = ?, @offset = ?, @page_size = ?");
			query.setParameter(1, search);
			query.setParameter(2, type);
			query.setParameter(3, offset);
			query.setParameter(4, pageSize);
			Object result = query.getSingleResult();
			return result != null ? result.toString() : "{\"total_records\":0,\"playlists\":[]}";
		} catch (Exception e) {
			return "{\"error\":\"" + e.getMessage() + "\"}";
		}
	}

	public String searchTiktokUsers(String search, Integer genre, Integer offset, Integer pageSize) {
		try {
			Query query = entityManager.createNativeQuery(
				"EXEC dbo.GET_SEARCH_TIKTOK_USERS @search = ?, @genre = ?, @offset = ?, @page_size = ?");
			query.setParameter(1, search);
			query.setParameter(2, genre);
			query.setParameter(3, offset);
			query.setParameter(4, pageSize);
			Object result = query.getSingleResult();
			return result != null ? result.toString() : "{\"total_records\":0,\"tiktok_users\":[]}";
		} catch (Exception e) {
			return "{\"error\":\"" + e.getMessage() + "\"}";
		}
	}

}
