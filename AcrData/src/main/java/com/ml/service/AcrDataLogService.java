package com.ml.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ml.model.AcrDataLog;
import com.ml.repository.AcrDataLogRepository;
import com.ml.repository.ChannelRepository;

@Service
public class AcrDataLogService {
	
	@Autowired
	AcrDataLogRepository repo;
	@Autowired
	ChannelRepository crepo;
	
	public AcrDataLog save(AcrDataLog c) {
		return repo.saveAndFlush(c);
	}
	
	public Optional<AcrDataLog> getById(Long id) {
		return repo.findById(id);
	}
	
	public void deleteById(Long id) {
		repo.deleteById(id);
	}
	

}
