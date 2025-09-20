package com.ml.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ml.model.Isrc;
import com.ml.repository.IsrcRepository;

@Service
public class IsrcService {
	
	@Autowired
	IsrcRepository repo;
	
	public Isrc save(Isrc c) {
		return repo.saveAndFlush(c);
	}
	
	public Optional<Isrc> getById(Long id) {
		return repo.findById(id);
	}
	
	public void deleteById(Long id) {
		repo.deleteById(id);
	}
	
	public Optional<Isrc> getByCode(String code){
		return repo.findByCode(code);
	}

}
