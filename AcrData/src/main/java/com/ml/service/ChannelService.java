package com.ml.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ml.model.Channel;
import com.ml.repository.ChannelRepository;

@Service
public class ChannelService {
	
	@Autowired
	ChannelRepository repo;
	
	public Channel save(Channel c) {
		return repo.saveAndFlush(c);
	}
	
	public Optional<Channel> getById(Long id) {
		return repo.findById(id);
	}
	
	public void deleteById(Long id) {
		repo.deleteById(id);
	}
	
	public List<Channel> getAllChannels(){
		return repo.findAll();
	}
	

}
