package com.ml.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ml.model.Artist;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {
	Optional<Artist> findByName(String name);
	

}
