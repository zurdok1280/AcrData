package com.ml.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ml.model.AcrData;
import com.ml.model.Channel;

@Repository
public interface AcrDataRepository extends JpaRepository<AcrData, Long> {
	AcrData findById(long id);
	List<AcrData> findByChannel(Channel channel);
	AcrData findFirstByChannelOrderByTimestamputcDesc(Channel channel);

}
