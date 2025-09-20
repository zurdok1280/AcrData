package com.ml.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ml.model.Isrc;
@Repository
public interface IsrcRepository extends JpaRepository<Isrc, Long> {
	Optional<Isrc> findByCode(String code);
	

}
