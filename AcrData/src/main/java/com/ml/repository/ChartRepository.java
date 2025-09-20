package com.ml.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ml.model.report.Chart;


@Repository
public class ChartRepository {
	
	@Autowired
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<Chart> getChartAll() {
        return em.createNamedStoredProcedureQuery("findByChart").getResultList();
    }

}
