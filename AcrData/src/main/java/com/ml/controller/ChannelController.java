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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ml.service.ChannelService;
import com.ml.model.Channel;

@RestController
@RequestMapping(value = "/api/channel/")
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class ChannelController {
	
	Logger logger = LoggerFactory.getLogger(ChannelController.class);
	
	@Autowired
	ChannelService schannel;
	

	@RequestMapping(value = "setAcrChannel", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<com.ml.model.acr.Channel> createAcr(@RequestBody com.ml.model.acr.Channel m) {
		logger.info("Set Data at: " +  LocalDateTime.now());
		m.getData().stream().forEach(f ->{
			boolean timebol= (f.getTimemap()==1);
			com.ml.model.Channel c = new com.ml.model.Channel(f.getUid(),
											f.getProjectId(),
											f.getId(),
											f.getName(),
											f.getSubTitle(),
											f.getLanguage(),
											f.getCity(),
											f.getProvince(),
											f.getCountry(),
											f.getContinent(),
											f.getWebsite(),
											f.getTwitter(),
											f.getMytuner(),
											f.getStatusCode(),
											f.getMonitorAt(),
											timebol,
											f.getTimemapLifecycle(),
											f.getAddAt()
											);
			logger.info("Channel data update: " +  c);
			schannel.save(c);
		});
		
		return new ResponseEntity<com.ml.model.acr.Channel>(m, HttpStatus.CREATED);
	}
	
	
	@RequestMapping(value = "getChannelbyId/{channel}", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
	public ResponseEntity<Channel> getChannelbyId(@PathVariable Long channel) {
		logger.info("Set Data at: " +  LocalDateTime.now());
		Optional<Channel> chan = schannel.getById(channel);
		if (!chan.isPresent()) {
			chan = schannel.getById(107741L);
		}
		return new ResponseEntity<Channel> (chan.get(), HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "getChannels", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
	public ResponseEntity<List<Channel>> getChannels() {
		logger.info("Set Data at: " +  LocalDateTime.now());
		List<Channel> channels = schannel.getAllChannels();
		
		return new ResponseEntity<List<Channel>> (channels, HttpStatus.CREATED);
	}
	
	
	

}
