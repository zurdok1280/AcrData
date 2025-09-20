package com.ml.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ml.model.Label;
import com.ml.repository.LabelRepository;

@Service
public class LabelService {
	
	@Autowired
	LabelRepository repo;
	
	public Label save(Label c) {
		return repo.saveAndFlush(c);
	}
	
	
	public Optional<Label> getById(Long id) {
		return repo.findById(id);
	}
	
	public void deleteById(Long id) {
		repo.deleteById(id);
	}
	
	public Optional<Label> getByName(String name){
		return repo.findByName(name);
	}

}
