package com.ml.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ml.model.Track;
import com.ml.model.report.Song;
import com.ml.model.report.SongDigital;
import com.ml.model.report.SongML;
import com.ml.model.report.StationChart;
import com.ml.repository.ExecProcRepository;
import com.ml.service.SongMLService;
import com.ml.service.TrackService;

@RestController
@RequestMapping(value = "/api/track/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TrackController {
	
	Logger logger = LoggerFactory.getLogger(TrackController.class);
	
	@Autowired
	TrackService strack;
	@Autowired
	SongMLService mltrack;
	@Autowired
	ExecProcRepository procs;
	
	@GetMapping(value = "getTrackbyId/{track}", consumes = MediaType.ALL_VALUE)
	public ResponseEntity<Track> getTrackbyId(@PathVariable Long track) {
		logger.info("Set Data at: " +  LocalDateTime.now());
		Optional<Track> chan = strack.getById(track);
		if (!chan.isPresent()) {
			chan = strack.getById(279L);
		}
		return new ResponseEntity<Track> (chan.get(), HttpStatus.CREATED);
	}
	
	@GetMapping(value = "getSongbyId/{track}", consumes = MediaType.ALL_VALUE)
	public ResponseEntity<Song> getSongbyId(@PathVariable Long track) {
		logger.info("Set Data at: " +  LocalDateTime.now());
		Optional<Track> chan = strack.getById(track);
		if (!chan.isPresent()) {
			chan = strack.getById(279L);
		}
		SongML songm = mltrack.getSongbyId(chan.get().getId()).get(0);
		Song song = new Song(Long.toString(chan.get().getId()),
							songm.getAvatar(),songm.getAvatar(),
							chan.get().getTitle(),
							chan.get().getTrack_artist().stream().findFirst().get().getArtist().getName(),
							chan.get().getLabel().getName(),""
							);
		logger.info(song.toString());
		return new ResponseEntity<Song> (song, HttpStatus.CREATED);
	}
	
	@GetMapping(value = "getSongStreams/{track}", consumes = MediaType.ALL_VALUE)
	public ResponseEntity<List<StationChart>> getDatabyId(@PathVariable Long track) {
		logger.info("Set Data at: " +  LocalDateTime.now());
		
		List<StationChart> st = procs.getStationsbySong(track);
		return new ResponseEntity<List<StationChart>> (st, HttpStatus.CREATED);
	}
	
	@GetMapping(value = "getSongDigital/{track}", consumes = MediaType.ALL_VALUE)
	public ResponseEntity<SongDigital> getDigitalbyId(@PathVariable Long track) {
		logger.info("Set Data at: " +  LocalDateTime.now());
		
		SongDigital st = procs.getDigitalbySong(track);
		return new ResponseEntity<SongDigital> (st, HttpStatus.CREATED);
	}

}
