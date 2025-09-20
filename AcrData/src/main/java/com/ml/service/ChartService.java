package com.ml.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ml.model.report.Chart;
import com.ml.repository.ChartRepository;

@Service
public class ChartService {
	
	@Autowired
	ChartRepository repo;
	
	
	public List<Chart> getChart() {
		return repo.getChartAll();
	}
	
}
