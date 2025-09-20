package com.ml.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ml.model.report.SongML;

@Repository
public class SongMLRepository {
	
	@Autowired
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<SongML> getSongbyId(Long id) {
        return em.createNamedStoredProcedureQuery("findBySong").setParameter("id", id).getResultList();
    }

}
