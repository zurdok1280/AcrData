package com.ml.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ml.model.Album;
import com.ml.model.Artist;
import com.ml.repository.AlbumRepository;

@Service
public class AlbumService {
	
	@Autowired
	AlbumRepository repo;
	
	public Album save(Album a) {
		return repo.saveAndFlush(a);
	}
	
	public Optional<Album> getById(Long id) {
		return repo.findById(id);
	}
	
	public void deleteById(Long id) {
		repo.deleteById(id);
	}
	
	public Optional<Album> getByName(String name){
		return repo.findByName(name);
	}
	
	public Optional<Album> getByNameAndArtist(String name, Artist a){
		return repo.findByAlbumAndArtistQ(name, a.getId());
	}

}
