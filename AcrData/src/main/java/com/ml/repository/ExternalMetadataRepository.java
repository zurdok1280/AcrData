package com.ml.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ml.model.ExternalMetadata;
@Repository
public interface ExternalMetadataRepository extends JpaRepository<ExternalMetadata, Long> {

}
