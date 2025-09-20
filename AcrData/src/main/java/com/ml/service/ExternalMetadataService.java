package com.ml.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ml.model.ExternalMetadata;
import com.ml.repository.ExternalMetadataRepository;

@Service
public class ExternalMetadataService {
	
	@Autowired
	ExternalMetadataRepository repo;
	
	public ExternalMetadata save(ExternalMetadata c) {
		return repo.saveAndFlush(c);
	}
	
	public Optional<ExternalMetadata> getById(Long id) {
		return repo.findById(id);
	}
	
	public void deleteById(Long id) {
		repo.deleteById(id);
	}


}
