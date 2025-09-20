package com.ml.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ml.model.Track;
import com.ml.repository.TrackRepository;

@Service
public class TrackService {
	
	@Autowired
	TrackRepository repo;
	
	public Optional<Track> save(Track c) {
		return Optional.of(repo.saveAndFlush(c));
	}
	
	public Optional<Track> getById(Long id) {
		return repo.findById(id);
	}
	
	public void deleteById(Long id) {
		repo.deleteById(id);
	}
	
	public Track findByIsrc(String Isrc) {
		return repo.findByIsrc(Isrc);
	}
	
	public Optional<Track> findByIsrcGroup(String isrc,String acrid) {
		return repo.findByIsrcGroup(isrc,acrid);
	}
	
	public Optional<Track> FindByAcrid(String acrid){
		return repo.findByAcrid(acrid);
	}

}
