package com.ml.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ml.model.report.ArtistDigital;
import com.ml.model.report.Chart;
import com.ml.model.report.ChartDigital;
import com.ml.model.report.City;
import com.ml.model.report.CityData;
import com.ml.model.report.Country;
import com.ml.model.report.Format;
import com.ml.model.report.Platform;
import com.ml.model.report.PlatformTop;
import com.ml.model.report.Song;
import com.ml.model.report.SongFormat;
import com.ml.model.report.SongStatsDigital;
import com.ml.model.report.TrendingSongs;
import com.ml.repository.ExecProcRepository;
import com.ml.service.ChartService;

@RestController
@RequestMapping(value = "/api/report/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ReportController {

	Logger logger = LoggerFactory.getLogger(ReportController.class);
	@Autowired
	ChartService schart;

	@Autowired
	ExecProcRepository procexec;

	@GetMapping(value = "getChart", consumes = MediaType.ALL_VALUE)
	public ResponseEntity<List<Chart>> getChart() {
		logger.info("Set Data at: " + LocalDateTime.now());
		List<Chart> charts = schart.getChart();

		return new ResponseEntity<List<Chart>>(charts, HttpStatus.CREATED);
	}

	@GetMapping(value = "getChartDigital/{format}/{country}/{CRG}/{city}", consumes = MediaType.ALL_VALUE)
	public ResponseEntity<List<ChartDigital>> getChartDigital(@PathVariable Integer format,
			@PathVariable Integer country, @PathVariable String CRG, @PathVariable Integer city) {
		logger.info("Get Data Digital at: " + LocalDateTime.now() + "params format: " + format + ",country: " + country + ",CRG: " + CRG + ",city: " + city);
		List<ChartDigital> charts = procexec.getDigitalChart(format, country, CRG, 2, 0, city);
		return new ResponseEntity<List<ChartDigital>>(charts, HttpStatus.CREATED);
	}

	@GetMapping(value = "getSongDigital/{cs_song}/{fmt}", consumes = MediaType.ALL_VALUE)
	public ResponseEntity<SongStatsDigital> getDigitalSong(@PathVariable Integer cs_song, @PathVariable Integer fmt) {
		logger.info("Get Song Digital at: " + LocalDateTime.now() + " cs_song: " + cs_song + " fmt: " + fmt);
		SongStatsDigital song = procexec.getDigitalSong(cs_song, fmt);
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

	@GetMapping(value = "getTopArtist/{format}/{country}", consumes = MediaType.ALL_VALUE)
	public ResponseEntity<List<ArtistDigital>> getTopArtists(@PathVariable Integer format,
			@PathVariable Integer country) {
		logger.info("get Top Artist Format at: " + LocalDateTime.now());
		List<ArtistDigital> song = procexec.getTopArtist(format, country);
		return new ResponseEntity<List<ArtistDigital>>(song, HttpStatus.CREATED);
	}

	@GetMapping(value = "getTrendingDebut/{format}/{country}/{CRG}/{city}", consumes = MediaType.ALL_VALUE)
	public ResponseEntity<List<TrendingSongs>> getTrendingDebut(@PathVariable Integer format,
			@PathVariable Integer country, @PathVariable String CRG, @PathVariable Integer city) {
		logger.info("get getDigitalSong Format at: " + LocalDateTime.now());
		List<TrendingSongs> song = procexec.getTrendingDebut(format, country,CRG,2,0,city);
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
		logger.info("get getTopPlatform at: " + LocalDateTime.now());
		List<Platform> platform = procexec.getPlatforms();
		return new ResponseEntity<List<Platform>>(platform, HttpStatus.CREATED);
	}

	@GetMapping(value = "getSearch/{search}", consumes = MediaType.ALL_VALUE)
	public ResponseEntity<List<Song>> getSearch(@PathVariable String search) {
		logger.info("get getTopPlatform at: " + LocalDateTime.now());
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
		logger.info("get getCityData at: " + LocalDateTime.now());
		List<City> cities = procexec.getCities(country, CRG);
		return new ResponseEntity<List<City>>(cities, HttpStatus.CREATED);
	}
	
	

}
