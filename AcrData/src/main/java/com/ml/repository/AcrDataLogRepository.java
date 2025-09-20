package com.ml.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ml.model.AcrDataLog;
@Repository
public interface AcrDataLogRepository extends JpaRepository<AcrDataLog, Long> {


}
