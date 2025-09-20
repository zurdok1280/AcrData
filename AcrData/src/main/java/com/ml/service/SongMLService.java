package com.ml.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ml.model.report.SongML;
import com.ml.repository.SongMLRepository;

@Service
public class SongMLService {
	
	@Autowired
	SongMLRepository repo;
	
	
	public List<SongML> getSongbyId(Long id) {
		return repo.getSongbyId(id);
	}


}
