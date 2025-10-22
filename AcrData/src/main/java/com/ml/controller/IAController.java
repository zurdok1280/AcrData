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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ml.model.report.ChatUser;
import com.ml.model.report.UserLog;
import com.ml.repository.ExecProcRepository;

@RestController
@RequestMapping(value = "/api/ia/")
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class IAController {
	
	Logger logger = LoggerFactory.getLogger(IAController.class);
	
	@Autowired
	ExecProcRepository procexec;
	
	@GetMapping(value = "getConverstation/{phone}", consumes = MediaType.ALL_VALUE)
	public ResponseEntity<List<ChatUser>> getCityData(@PathVariable String phone) {
		logger.info("get getConverstation at: " + LocalDateTime.now());
		List<ChatUser> users = procexec.getUserChat(phone);
		return new ResponseEntity<List<ChatUser>>(users, HttpStatus.CREATED);
	}
	
	
	
	@PutMapping(value = "setLog", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserLog> create(@RequestBody UserLog m) {
		logger.info("set getConverstation at: " + LocalDateTime.now());
		procexec.setLog(m.getPhone(), m.getMessage(), m.getResponse());
		return new ResponseEntity<UserLog>(m, HttpStatus.CREATED);
		
	}
}
