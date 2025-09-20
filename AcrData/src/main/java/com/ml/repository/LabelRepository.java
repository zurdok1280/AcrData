package com.ml.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ml.model.Label;

@Repository
public interface LabelRepository extends JpaRepository<Label, Long> {
	Optional<Label> findByName(String name);

}
