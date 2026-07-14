package com.ml.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ml.model.report.ArtistDigital;
import com.ml.model.report.Chart;
import com.ml.model.report.ChartDigital;
import com.ml.model.report.City;
import com.ml.model.report.CityData;
import com.ml.model.report.Country;
import com.ml.model.report.Format;
import com.ml.model.report.MessageChat;
import com.ml.model.report.Platform;
import com.ml.model.report.PlatformTop;
import com.ml.model.report.Song;
import com.ml.model.report.SongCities;
import com.ml.model.report.SongCountries;
import com.ml.model.report.SongFormat;
import com.ml.model.report.SongPlaylist;
import com.ml.model.report.SongRecommendation;
import com.ml.model.report.SongScore;
import com.ml.model.report.SongStatsDigital;
import com.ml.model.report.SongTiktok;
import com.ml.model.report.TrendingSongs;
import com.ml.repository.ExecProcRepository;
import com.ml.service.ChartService;
import com.ml.service.OpenAiService;
import com.ml.service.OpenAiServiceV2;
import com.ml.service.SpotifyRelatedArtistService;
import com.ml.service.SpotifySearchService;

@RestController
@RequestMapping(value = "/api/report/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ReportController {

	Logger logger = LoggerFactory.getLogger(ReportController.class);
	@Autowired
	ChartService schart;
	@Autowired
	ExecProcRepository procexec;
	@Autowired
	SpotifySearchService spotifyapi;
	@Autowired
	SpotifyRelatedArtistService spotifyrelated;

	@Autowired
	private OpenAiService openAiService;

	@Autowired
	private OpenAiServiceV2 openAiServiceV2;

	@GetMapping(value = "getChart", consumes = MediaType.ALL_VALUE)
	public ResponseEntity<List<Chart>> getChart() {
		logger.info("Set Data at: " + LocalDateTime.now());
		List<Chart> charts = schart.getChart();

		return new ResponseEntity<List<Chart>>(charts, HttpStatus.CREATED);
	}

	@GetMapping(value = "getChartDigital/{format}/{country}/{CRG}/{city}", consumes = MediaType.ALL_VALUE)
	public ResponseEntity<List<ChartDigital>> getChartDigital(@PathVariable Integer format,
			@PathVariable Integer country, @PathVariable String CRG, @PathVariable Integer city,
			@RequestParam(defaultValue = "0") Integer radiooff) {
		logger.info("Get Data Digital at: " + LocalDateTime.now());
		List<ChartDigital> charts = procexec.getDigitalChart(format, country, CRG, 2, 0, city, radiooff);
		return new ResponseEntity<List<ChartDigital>>(charts, HttpStatus.CREATED);
	}

	@GetMapping(value = "getChartDigital/{format}/{country}/{CRG}/{city}/{top}", consumes = MediaType.ALL_VALUE)
	public ResponseEntity<List<ChartDigital>> getChartDigital(@PathVariable Integer format,
			@PathVariable Integer country, @PathVariable String CRG, @PathVariable Integer city,
			@RequestParam(defaultValue = "0") Integer radiooff, @PathVariable Integer top) {
		logger.info("Get Data Digital at: " + LocalDateTime.now());
		List<ChartDigital> charts = procexec.getDigitalChart(format, country, CRG, 2, 0, city, radiooff, top);
		return new ResponseEntity<List<ChartDigital>>(charts, HttpStatus.CREATED);
	}

	@GetMapping(value = "getChartDigitalVideo/{format}/{country}/{CRG}/{city}/{top}", consumes = MediaType.ALL_VALUE)
	public ResponseEntity<List<Map<String, Object>>> getChartDigitalVideo(@PathVariable Integer format,
			@PathVariable Integer country, @PathVariable String CRG, @PathVariable Integer city,
			@PathVariable Integer top, @RequestParam(defaultValue = "0") Integer radiooff) {
		logger.info("Get Data getChartDigitalVideo at: {}", LocalDateTime.now());
		List<Map<String, Object>> charts = procexec.getDigitalChartVideo(format, country, CRG, 2, 0, city, radiooff,
				top);

		return ResponseEntity.ok(charts);
	}

	@GetMapping(value = "getSongDigital/{cs_song}/{fmt}/{country}", consumes = MediaType.ALL_VALUE)
	public ResponseEntity<SongStatsDigital> getDigitalSong(@PathVariable Integer cs_song, @PathVariable Integer fmt,
			@PathVariable Integer country) {
		logger.info("Get Song Digital at: " + LocalDateTime.now() + " cs_song: " + cs_song + " fmt: " + fmt
				+ " country: " + country);
		SongStatsDigital song = procexec.getDigitalSong(cs_song, fmt, country);
		return new ResponseEntity<SongStatsDigital>(song, HttpStatus.CREATED);
	}

	@GetMapping(value = "getSongbyId/{id}", consumes = MediaType.ALL_VALUE)
	public ResponseEntity<Song> getSongbyId(@PathVariable Integer id) {
		logger.info("get getDigitalSong at: " + LocalDateTime.now());
		Song song = procexec.getDigitalSong(id);
		logger.info(song.toString());
		return new ResponseEntity<Song>(song, HttpStatus.CREATED);
	}

	@GetMapping(value = "getCountries", consumes = MediaType.ALL_VALUE)
	public ResponseEntity<List<Country>> getCountries() {
		logger.info("get country at: " + LocalDateTime.now());
		List<Country> country = procexec.getCountries();
		return new ResponseEntity<List<Country>>(country, HttpStatus.CREATED);
	}

	@GetMapping(value = "getFormatbyCountry/{country}", consumes = MediaType.ALL_VALUE)
	public ResponseEntity<List<Format>> getbyFormat(@PathVariable Integer country) {
		logger.info("get format at: " + LocalDateTime.now());
		List<Format> format = procexec.getFormats(country);
		return new ResponseEntity<List<Format>>(format, HttpStatus.CREATED);
	}

	@GetMapping(value = "getFormatbyCountryArtist/{country}", consumes = MediaType.ALL_VALUE)
	public ResponseEntity<List<Format>> getbyFormatArtist(@PathVariable Integer country) {
		logger.info("get format at: " + LocalDateTime.now());
		// List<Format> format = procexec.getFormats(country);
		List<Format> format = procexec.getFormats(0);
		return new ResponseEntity<List<Format>>(format, HttpStatus.CREATED);
	}

	@GetMapping(value = "getSongbyIdFormat/{id}/{country}", consumes = MediaType.ALL_VALUE)
	public ResponseEntity<List<SongFormat>> getSongbyIdFormat(@PathVariable Integer id, @PathVariable Integer country) {
		logger.info("get getDigitalSong Format at: " + LocalDateTime.now() + " cs_song: " + id + " country:" + country);
		List<SongFormat> song = procexec.getDigitalSongFormat(id, country);
		return new ResponseEntity<List<SongFormat>>(song, HttpStatus.CREATED);
	}

	@GetMapping(value = "getTrendingSongs/{format}/{country}/{CRG}/{city}", consumes = MediaType.ALL_VALUE)
	public ResponseEntity<List<TrendingSongs>> getTrendingSongs(@PathVariable Integer format,
			@PathVariable Integer country, @PathVariable String CRG, @PathVariable Integer city) {
		logger.info("get getDigitalSong Format at: " + LocalDateTime.now());
		List<TrendingSongs> song = procexec.getTrendingTop(format, country);
		return new ResponseEntity<List<TrendingSongs>>(song, HttpStatus.CREATED);
	}

	@GetMapping(value = "getTopArtist/{format}/{country}/{city}", consumes = MediaType.ALL_VALUE)
	public ResponseEntity<List<ArtistDigital>> getTopArtists(@PathVariable Integer format,
			@PathVariable Integer country, @PathVariable Integer city) {
		logger.info("get getDigitalSong Format at: " + LocalDateTime.now());
		List<ArtistDigital> song = procexec.getTopArtist(format, country, city);
		return new ResponseEntity<List<ArtistDigital>>(song, HttpStatus.CREATED);
	}

	@GetMapping(value = "getTrendingDebut/{format}/{country}/{CRG}/{city}", consumes = MediaType.ALL_VALUE)
	public ResponseEntity<List<TrendingSongs>> getTrendingDebut(@PathVariable Integer format,
			@PathVariable Integer country, @PathVariable String CRG, @PathVariable Integer city) {
		logger.info("get getDigitalSong Format at: " + LocalDateTime.now());
		List<TrendingSongs> song = procexec.getTrendingDebut(format, country, CRG, 2, 0, city);
		return new ResponseEntity<List<TrendingSongs>>(song, HttpStatus.CREATED);
	}

	@GetMapping(value = "getFormatInt", consumes = MediaType.ALL_VALUE)
	public ResponseEntity<List<Format>> getbyFormatInt() {
		logger.info("get format Int at: " + LocalDateTime.now());
		List<Format> format = procexec.getFormatsInt();
		return new ResponseEntity<List<Format>>(format, HttpStatus.CREATED);
	}

	@GetMapping(value = "getTopPlatform/{platform}/{format}/{country}", consumes = MediaType.ALL_VALUE)
	public ResponseEntity<List<PlatformTop>> getTopPlatform(@PathVariable String platform, @PathVariable Integer format,
			@PathVariable Integer country) {
		logger.info("get getTopPlatform at: " + LocalDateTime.now());
		List<PlatformTop> songs = procexec.getTopPlatform(platform, format, country);
		return new ResponseEntity<List<PlatformTop>>(songs, HttpStatus.CREATED);
	}

	@GetMapping(value = "getPlatforms", consumes = MediaType.ALL_VALUE)
	public ResponseEntity<List<Platform>> getPlatforms() {
		logger.info("get getPlatforms at: " + LocalDateTime.now());
		List<Platform> platform = procexec.getPlatforms();
		return new ResponseEntity<List<Platform>>(platform, HttpStatus.CREATED);
	}

	@GetMapping(value = "getSearch/{search}", consumes = MediaType.ALL_VALUE)
	public ResponseEntity<List<Song>> getSearch(@PathVariable String search) {
		logger.info("get getSearch at: " + LocalDateTime.now());
		List<Song> songs = procexec.getSearchSong(search);
		return new ResponseEntity<List<Song>>(songs, HttpStatus.CREATED);
	}

	@GetMapping(value = "getCityData/{id}/{country}", consumes = MediaType.ALL_VALUE)
	public ResponseEntity<List<CityData>> getCityData(@PathVariable Integer id, @PathVariable Integer country) {
		logger.info("get getCityData at: " + LocalDateTime.now());
		List<CityData> cities = procexec.getCityData(id, country);
		return new ResponseEntity<List<CityData>>(cities, HttpStatus.CREATED);
	}

	@GetMapping(value = "getCities/{country}/{CRG}", consumes = MediaType.ALL_VALUE)
	public ResponseEntity<List<City>> getCityData(@PathVariable String country, @PathVariable String CRG) {
		logger.info("get getCities at: " + LocalDateTime.now());
		List<City> cities = procexec.getCities(country, CRG);
		return new ResponseEntity<List<City>>(cities, HttpStatus.CREATED);
	}

	@GetMapping(value = "getTopMarketRadio/{id}/{country}", consumes = MediaType.ALL_VALUE)
	public ResponseEntity<List<SongCities>> getCityRadio(@PathVariable String id, @PathVariable String country) {
		logger.info("get getTopMarketRadio at: " + LocalDateTime.now());
		List<SongCities> cities = procexec.getCitiesRadio(id, country);
		return new ResponseEntity<List<SongCities>>(cities, HttpStatus.CREATED);
	}

	@GetMapping(value = "getTopRadioCountries/{id}", consumes = MediaType.ALL_VALUE)
	public ResponseEntity<List<SongCountries>> getCountryRadio(@PathVariable String id) {
		logger.info("get getTopRadioCountries at: " + LocalDateTime.now());
		List<SongCountries> countries = procexec.getCountriesRadio(id);
		return new ResponseEntity<List<SongCountries>>(countries, HttpStatus.CREATED);
	}

	@GetMapping(value = "getTopPlaylists/{id}/{type}", consumes = MediaType.ALL_VALUE)
	public ResponseEntity<List<SongPlaylist>> getCountryRadio(@PathVariable String id, @PathVariable String type) {
		logger.info("get getTopPlaylists at: " + LocalDateTime.now());
		List<SongPlaylist> plays = procexec.getSongPlaylist(id, type);
		return new ResponseEntity<List<SongPlaylist>>(plays, HttpStatus.CREATED);
	}

	@GetMapping(value = "getTopTiktok/{id}", consumes = MediaType.ALL_VALUE)
	public ResponseEntity<List<SongTiktok>> getTopTiktok(@PathVariable String id) {
		logger.info("get getTopPlaylists at: " + LocalDateTime.now());
		List<SongTiktok> plays = procexec.getSongTiktok(id);
		return new ResponseEntity<List<SongTiktok>>(plays, HttpStatus.CREATED);
	}

	@GetMapping(value = "getRecommendations/{id}", consumes = MediaType.ALL_VALUE)
	public ResponseEntity<List<SongRecommendation>> getRecommendations(@PathVariable String id) {
		logger.info("get getTopPlaylists at: " + LocalDateTime.now());
		List<SongRecommendation> plays = procexec.getSongRecommendation(id);
		return new ResponseEntity<List<SongRecommendation>>(plays, HttpStatus.CREATED);
	}

	@GetMapping(value = "getcssong", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getCsSong(@RequestParam String spotifyid) {
		logger.info("get getCsSong at: " + LocalDateTime.now());
		JSONObject result = procexec.getCsSong(spotifyid);
		return ResponseEntity.ok(result.toString());
	}

	@GetMapping(value = "getvideos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getVideos() {
		logger.info("get videos at: " + LocalDateTime.now());
		JSONArray result = procexec.getVideos();
		return ResponseEntity.ok(result.toString());
	}

	@GetMapping(value = "getSongsArtist/{spotifyid}/{country}", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getSongsArtist(@PathVariable String spotifyid, @PathVariable Integer country) {
		logger.info("get songs from artist at: " + LocalDateTime.now());
		JSONArray result = procexec.getSongsArtist(spotifyid, country);
		return ResponseEntity.ok(result.toString());
	}

	@GetMapping(value = "getTopDigtal/{country}", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getTopDigtal(@PathVariable Integer country) {
		logger.info("get songs from artist at: " + LocalDateTime.now());
		JSONArray result = procexec.getTopDigital(country);
		return ResponseEntity.ok(result.toString());
	}

	@GetMapping(value = "getTopRadio/{country}/{format}", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getTopDigtal(@PathVariable Integer country, @PathVariable Integer format) {
		logger.info("get songs from artist at: " + LocalDateTime.now());
		JSONArray result = procexec.getTopRadio(country, format);
		return ResponseEntity.ok(result.toString());
	}

	@GetMapping(value = "getTopRadioFormat/{country}/{format}", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getTopRadioFormat(@PathVariable Integer country, @PathVariable Integer format) {
		logger.info("get format at: " + LocalDateTime.now());
		JSONObject result = procexec.getTopRadioFormat(format, country);
		return ResponseEntity.ok(result.toString());
	}

	@GetMapping(value = "getTopFormat/{country}/{option}", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getRadioFormat(@PathVariable Integer country, @PathVariable Integer option) {
		logger.info("get top format at: " + LocalDateTime.now());
		JSONObject result = procexec.getTopFormat(country, option);
		return ResponseEntity.ok(result.toString());
	}

	@GetMapping(value = "getVsSong/{cs_song1}/{cs_song2}", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getVsSong(@PathVariable String cs_song1, @PathVariable String cs_song2) {
		logger.info("get vs song at: " + LocalDateTime.now());
		JSONArray result = procexec.getvsSongs(cs_song1, cs_song2);
		return ResponseEntity.ok(result.toString());
	}

	@GetMapping(value = "getVsSongPlaylists/{cs_song1}/{cs_song2}", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getVsSongPlaylists(@PathVariable String cs_song1, @PathVariable String cs_song2) {
		logger.info("get vs song at: " + LocalDateTime.now());
		JSONArray result = procexec.getvsSongsPlaylists(cs_song1, cs_song2);
		return ResponseEntity.ok(result.toString());
	}

	@GetMapping(value = "getVsSongTiktoks/{cs_song1}/{cs_song2}", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getVsSongTiktoks(@PathVariable String cs_song1, @PathVariable String cs_song2) {
		logger.info("get vs song at: " + LocalDateTime.now());
		JSONArray result = procexec.getvsSongsTiktoks(cs_song1, cs_song2);
		return ResponseEntity.ok(result.toString());
	}

	@GetMapping(value = "getTopScores/{fmt}", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getTopScores(@PathVariable String fmt) {
		logger.info("get scores at: " + LocalDateTime.now());
		JSONArray result = procexec.getScores(fmt);
		return ResponseEntity.ok(result.toString());
	}

	@GetMapping(value = "getTiktokPics/{fmt}", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getTiktokPics(@PathVariable String fmt) {
		logger.info("get tiktok pics at: " + LocalDateTime.now());
		JSONArray result = procexec.getTiktokPics(fmt);
		return ResponseEntity.ok(result.toString());
	}

	@GetMapping(value = "getPlaylistType", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getPlaylistType() {
		logger.info("get types playlist pics at: " + LocalDateTime.now());
		JSONArray result = procexec.getPlaylistType();
		return ResponseEntity.ok(result.toString());
	}

	@GetMapping(value = "getCuratorPics/{fmt}/{type}", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getCuratorPics(@PathVariable String fmt, @PathVariable String type) {
		logger.info("get curator pics at: " + LocalDateTime.now());
		JSONArray result = procexec.getCuratorPics(fmt, type);
		return ResponseEntity.ok(result.toString());
	}

	@GetMapping(value = "getSearchSpotify", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getSearchSpotify(@RequestParam String query) {
		logger.info("get getTopPlaylists at: " + LocalDateTime.now());
		JSONObject result = new JSONObject();
		try {
			// result = spotifyapi.searchSpotify(query, "20");
			result = spotifyapi.searchSpotifyAndEnrich(query, "20");

			return ResponseEntity.ok(result.toString());
		} catch (Exception e) {
			logger.error(e.getMessage());
			JSONObject error = new JSONObject();
			error.put("error", e.getMessage());
			return ResponseEntity.status(500).body(error.toString());
		}

	}

	@GetMapping(value = "getSpotifyId", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getSpotifyId(@RequestParam String cs_song) {
		logger.info("get videos at: " + LocalDateTime.now());
		JSONObject result = procexec.getSpotifyId(cs_song);
		return ResponseEntity.ok(result.toString());
	}

	@GetMapping(value = "getDataArtist/{spotifyid}", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getDataArtist(@PathVariable String spotifyid) {
		logger.info("get data artist at: " + LocalDateTime.now());
		JSONObject result = procexec.getDataArtist(spotifyid);
		return ResponseEntity.ok(result.toString());
	}

	@GetMapping(value = "getDataArtistSongid/{cs_song}", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getDataArtistSongid(@PathVariable String cs_song) {
		logger.info("get data artist at: " + LocalDateTime.now());
		JSONObject result = procexec.getDataArtistBySong(cs_song);
		return ResponseEntity.ok(result.toString());
	}

	@GetMapping(value = "getLastUpdate", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getLastUpdate() {
		logger.info("get data artist at: " + LocalDateTime.now());
		return ResponseEntity.ok(procexec.getLastFriday());
	}

	@GetMapping(value = "getDataArtistCountry/{country}/{spotifyid}", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getDataArtistCities(@PathVariable Integer country, @PathVariable String spotifyid) {
		logger.info("get songs from artist cities at: " + LocalDateTime.now());
		JSONArray result = procexec.getSongsArtistCities(country, spotifyid);
		return ResponseEntity.ok(result.toString());
	}

	@GetMapping(value = "getDataArtistCountryRelated/{country}/{spotifyid}", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getDataArtistCountryRelated(@PathVariable Integer country,
			@PathVariable String spotifyid) {
		logger.info("get artist from artist cities related at: " + LocalDateTime.now());
		String result = procexec.getSongsArtistCitiesRelated(country, spotifyid);
		return ResponseEntity.ok(result);
	}

	@GetMapping(value = "getSongbyId/{id}/{country}", consumes = MediaType.ALL_VALUE)
	public ResponseEntity<SongScore> getSongbyIdCountry(@PathVariable Integer id, @PathVariable Integer country) {
		logger.info("get getDigitalSong at: " + LocalDateTime.now());
		SongScore song = procexec.getDigitalSongData(id, country);
		logger.info(song.toString());
		return new ResponseEntity<SongScore>(song, HttpStatus.CREATED);
	}

	@PostMapping(value = "setMessage", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getSongbyIdCountry(@RequestBody MessageChat m) {
		logger.info("set message at: " + LocalDateTime.now());
		logger.info(m.toString());
		procexec.setLogMessage(m.getPhone(), m.getContact(), m.getCountry(), m.getFormat(), m.getMessage());
		return ResponseEntity.ok("ok");

	}

	@PostMapping(value = "setLogSong", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> setLogSong(@RequestBody JsonNode json) {
		logger.info("set message at: " + LocalDateTime.now());
		Integer userid = json.get("userid").asInt();
		String spotifyid = json.get("spotifyid").asText();
		Boolean isartist = json.get("isartist").asBoolean();
		procexec.setLogSong(userid, spotifyid, isartist);
		return ResponseEntity.ok("ok");

	}

	@PostMapping(value = "setContact", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> setContact(@RequestBody JsonNode json) {
		logger.info("set message at: " + LocalDateTime.now());
		String phone = json.get("phone").asText();
		String nombre = json.get("nombre").asText();
		String apellido = json.get("apellido").asText();
		String rol = json.get("rol").asText();
		Integer tipo = json.get("tipo").asInt();
		String paises = json.get("paises").asText();
		procexec.setWhatsContact(phone, nombre, apellido, rol, tipo, paises);
		return ResponseEntity.ok("ok");

	}

	@PostMapping(value = "delContact", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> delContact(@RequestBody JsonNode json) {
		logger.info("del contact at: " + LocalDateTime.now());
		String phone = json.get("phone").asText();
		procexec.delWhatsContact(phone);
		return ResponseEntity.ok("ok");

	}

	@GetMapping(value = "getAllContacs", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllContacts() {
		logger.info("get data contacts at: " + LocalDateTime.now());
		JSONArray result = procexec.getAllContacts();
		return ResponseEntity.ok(result.toString());
	}

	@GetMapping(value = "getContactbyCountry/{country}", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getContactbyCountry(@PathVariable String country) {
		logger.info("get data artist at: " + LocalDateTime.now());
		JSONArray result = procexec.getContactByCountry(country);
		return ResponseEntity.ok(result.toString());
	}

	@GetMapping(value = "getUserLog/{phoneno}", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getRadioFormat(@PathVariable String phoneno) {
		logger.info("get top log phone at: " + LocalDateTime.now());
		JSONObject result = procexec.getUserLog(phoneno);
		return ResponseEntity.ok(result.toString());
	}

	@GetMapping(value = "getArtistPlaylistRelated/{artistid}/{playlisttype}", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getArtistPlaylistRelated(@PathVariable String artistid,
			@PathVariable String playlisttype) {
		logger.info("get curator pics at: " + LocalDateTime.now());
		JSONArray result = procexec.getArtistPlaylistRelated(artistid, playlisttype);
		return ResponseEntity.ok(result.toString());
	}

	@GetMapping(value = "getArtistTiktokersRelated/{artistid}", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getArtistTiktokersRelated(@PathVariable String artistid) {
		logger.info("get curator pics at: " + LocalDateTime.now());
		JSONArray result = procexec.getArtistTiktokersRelated(artistid);
		return ResponseEntity.ok(result.toString());
	}

	@GetMapping(value = "getArtistRadioRelated/{artistid}/{countryid}", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getArtistRadioRelated(@PathVariable String artistid, @PathVariable String countryid) {
		logger.info("get curator pics at: " + LocalDateTime.now());
		JSONArray result = procexec.getArtistRadioRelated(artistid, countryid);
		return ResponseEntity.ok(result.toString());
	}

	@GetMapping(value = "getArtistRelatedGraph/{artistid}", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getArtistRelatedGraph(@PathVariable String artistid) {
		logger.info("get artist graph at: " + LocalDateTime.now());
		String result = procexec.getArtistRelatedGraph(artistid);
		return ResponseEntity.ok(result);
	}

	@GetMapping(value = "getArtistRelatedGraphv2/{artistid}", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getArtistRelatedGraphv2(@PathVariable String artistid) {
		logger.info("get artist graph at: " + LocalDateTime.now());
		String result = procexec.getArtistRelatedGraphv2(artistid);
		return ResponseEntity.ok(result);
	}
	
	@GetMapping(value = "getPlaylistData/{type}/{offset}/{page_size}", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getPlaylistData(@PathVariable Integer type,@PathVariable Integer offset,@PathVariable Integer page_size) {
		logger.info("get getPlaylistData at: " + LocalDateTime.now());
		String result = procexec.getPlaylistData(type,offset,page_size);
		return ResponseEntity.ok(result);
	}
	
	@GetMapping(value = "getTiktokData/{genre}/{offset}/{page_size}", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getTiktokData(@PathVariable Integer genre,@PathVariable Integer offset,@PathVariable Integer page_size) {
		logger.info("get getPlaylistData at: " + LocalDateTime.now());
		String result = procexec.getTiktokData(genre,offset,page_size);
		return ResponseEntity.ok(result);
	}

	@GetMapping(value = "getSongHistoricalStreams/{cs_song}", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getSongHistoricalStreams(@PathVariable Integer cs_song) {
		logger.info("get song historical at: " + LocalDateTime.now());
		String result = procexec.getSongHistoricalStreams(cs_song);
		return ResponseEntity.ok(result);
	}

	@GetMapping(value = "getSongHistoricalStreamsWeek/{cs_song}/{country}/{fmt}", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getSongHistoricalStreamsWeek(@PathVariable Integer cs_song, @PathVariable Integer country,
			@PathVariable Integer fmt) {
		logger.info("get song historical at: " + LocalDateTime.now());
		String result = procexec.getSongHistoricalStreamsWeekly(cs_song, country, fmt);
		return ResponseEntity.ok(result);
	}

	@GetMapping(value = "getArtistSongs/{artistid}", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getArtistSongs(@PathVariable String artistid) {
		logger.info("get artists songs pics at: " + LocalDateTime.now());
		String result = procexec.getArtistSongs(artistid);
		return ResponseEntity.ok(result);
	}

	@GetMapping(value = "getArtistContext/{artistid}", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JsonNode> getArtistContext(@PathVariable String artistid) {
		logger.info("get artists context at: " + LocalDateTime.now());
		String result = procexec.getArtistContext(artistid);
		// logger.info(result);
		ObjectMapper mapper = new ObjectMapper();
		JsonNode inputJson = null;
		JsonNode resultJson = null;
		try {
			inputJson = mapper.readTree(result);
		} catch (JsonProcessingException e) {
			logger.error("Error to JsonProcessingException object error: " + e.getMessage());
		}

		try {
			resultJson = openAiService.analyzeArtist(inputJson);
		} catch (Exception e) {
			logger.error("Error OpenAI:", e);
		}

		return ResponseEntity.ok(resultJson);
	}

	@GetMapping(value = "getArtistContextPhases/{artistid}", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JsonNode> getArtistContextPhases(@PathVariable String artistid) {
		logger.info("get artists context phases at: " + LocalDateTime.now());
		String result = procexec.getArtistContextPhase(artistid);
		// logger.info(result);
		ObjectMapper mapper = new ObjectMapper();
		JsonNode inputJson = null;
		JsonNode resultJson = null;
		try {
			inputJson = mapper.readTree(result);
		} catch (JsonProcessingException e) {
			logger.error("Error to JsonProcessingException object error: " + e.getMessage());
		}

		try {
			resultJson = openAiServiceV2.analyzeArtist(inputJson);
		} catch (Exception e) {
			logger.error("Error OpenAI:", e);
		}

		return ResponseEntity.ok(resultJson);
	}

	@Transactional
	@Scheduled(fixedDelay = 3600000, initialDelay = 1) // 60000)
	public void processRelatedArtists() {
		logger.info("Iniciando proceso programado de related artists...");
		try {
			JSONArray pendingArtists = procexec.getRelatedArtist();
			if (pendingArtists == null || pendingArtists.length() == 0) {
				logger.info("No hay artistas pendientes por procesar.");
				return;
			}
			int totalProcessed = 0;
			int totalInserted = 0;
			for (int i = 0; i < pendingArtists.length(); i++) {
				try {
					JSONObject item = pendingArtists.getJSONObject(i);
					long artistId = item.optLong("fk_artist", 0);
					String spotifyId = item.optString("spotifyid", "");
					if (artistId <= 0 || spotifyId.isBlank()) {
						logger.warn("Registro inválido en índice {}: {}", i, item);
						continue;
					}
					int inserted = spotifyrelated.saveRelatedArtists(artistId, spotifyId);

					totalProcessed++;
					totalInserted += inserted;
					logger.info("Procesado fk_artist={}, spotifyid={}, insertados={}", artistId, spotifyId, inserted);
				} catch (Exception e) {
					logger.error("Error procesando registro {}: {}", i, e.getMessage(), e);
				}
			}
			logger.info("Proceso terminado. artistas_procesados={}, relaciones_insertadas={}", totalProcessed,
					totalInserted);
		} catch (Exception e) {
			logger.error("Error general del proceso: {}", e.getMessage(), e);
		}
	}

}
