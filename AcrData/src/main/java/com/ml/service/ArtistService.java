package com.ml.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ml.model.Artist;
import com.ml.repository.ArtistRepository;

@Service
public class ArtistService {
	
	@Autowired
	ArtistRepository repo;
	
	public Artist save(Artist c) {
		return repo.saveAndFlush(c);
	}
	
	public Optional<Artist> getById(Long id) {
		return repo.findById(id);
	}
	
	public void deleteById(Long id) {
		repo.deleteById(id);
	}
	
	public Optional<Artist> getByName(String name){
		return repo.findByName(name);
	}


}
