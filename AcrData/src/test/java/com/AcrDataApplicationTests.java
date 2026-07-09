package com;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ml.service.SpotifySearchService;

@SpringBootTest
class AcrDataApplicationTests {
	
	@Autowired
	private SpotifySearchService controller;

	@Test
	void contextLoads() {
		try {
			System.out.println(controller.searchSpotify("shakira","20"));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
