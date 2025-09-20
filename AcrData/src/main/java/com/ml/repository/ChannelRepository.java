package com.ml.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ml.model.Channel;
@Repository
public interface ChannelRepository extends JpaRepository<Channel, Long> {
	
	

}
