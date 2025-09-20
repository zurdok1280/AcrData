package com.ml.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ml.model.Upc;
import com.ml.repository.UpcRepository;

@Service
public class UpcService {
	
	@Autowired
	UpcRepository repo;
	
	public Upc save(Upc c) {
		return repo.saveAndFlush(c);
	}
	
	public Optional<Upc> getById(Long id) {
		return repo.findById(id);
	}
	
	public void deleteById(Long id) {
		repo.deleteById(id);
	}
	
	public Optional<Upc> getByCode(String code){
		return repo.findByCode(code);
	}

}
