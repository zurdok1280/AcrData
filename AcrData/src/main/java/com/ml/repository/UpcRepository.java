package com.ml.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ml.model.Upc;
@Repository
public interface UpcRepository extends JpaRepository<Upc, Long> {
	
	Optional<Upc> findByCode(String code);

}
