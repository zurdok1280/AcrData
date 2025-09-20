package com.ml.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ml.model.Genre;
import com.ml.repository.GenreRepository;
@Service
public class GenreService {
	@Autowired
	GenreRepository repo;
	
	public Genre save(Genre c) {
		return repo.saveAndFlush(c);
	}
	
	public Optional<Genre> getById(Integer id) {
		return repo.findById(id);
	}
	
	public void deleteById(Integer id) {
		repo.deleteById(id);
	}
	
	public Optional<Genre> getByName(String name){
		return repo.findByName(name);
	}

}
