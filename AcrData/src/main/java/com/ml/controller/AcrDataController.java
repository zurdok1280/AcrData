package com.ml.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ml.model.AcrData;
import com.ml.model.acr.Acr;
import com.ml.model.acr.Datum;
import com.ml.service.AcrDataService;


@RestController
@RequestMapping(value = "/api/data/")
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class AcrDataController {
	
	Logger logger = LoggerFactory.getLogger(AcrDataController.class);
	
	@Autowired
	AcrDataService acrserv;
	
	
	@PutMapping(value = "setData", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AcrData> create(@RequestBody AcrData m) {
		logger.info("Set Data at: " +  LocalDateTime.now());
		AcrData acr = acrserv.save(m);
		if (acr!=null)
			return new ResponseEntity<AcrData>(acr, HttpStatus.CREATED);
		else
			return new ResponseEntity<AcrData>(acr, HttpStatus.NOT_FOUND);
	}
	
	@PutMapping(value = "setAcrData/{channel}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Acr> createAcr(@RequestBody Acr m, @PathVariable Long channel) {
		logger.info("Set Data at: " +  LocalDateTime.now() + " from stream: " + channel);
		AcrData adl =  acrserv.getLastRow(channel);
		Acr filter = m;
		if (adl!=null) {
			logger.info("Filter data to only missed");
			List<Datum> ldat = m.getData().stream().filter(f -> f.getMetadata().sgetDateRecord().isAfter(adl.getTimestamputc()))
								.collect(Collectors.toList());
			filter.setData(ldat);
		}
		logger.info("Try to insert data, size: " +  filter.getData().size());
		acrserv.saveAcr(filter, channel);
		return new ResponseEntity<Acr>(filter, HttpStatus.CREATED);
	}
	
	@GetMapping(value = "getLastLog/{channel}", consumes = MediaType.ALL_VALUE)
	public ResponseEntity<AcrData> getLastLog(@PathVariable Long channel) {
		logger.info("Get Last Log: " +  LocalDateTime.now());
		AcrData adl =  acrserv.getLastRow(channel);
		return new ResponseEntity<AcrData>(adl, HttpStatus.ACCEPTED);
	}
	
	

}
