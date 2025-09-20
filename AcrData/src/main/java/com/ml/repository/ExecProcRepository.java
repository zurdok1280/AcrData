package com.ml.repository;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ml.model.report.ArtistDigital;
import com.ml.model.report.ChartDigital;
import com.ml.model.report.ChatUser;
import com.ml.model.report.City;
import com.ml.model.report.CityData;
import com.ml.model.report.Country;
import com.ml.model.report.Format;
import com.ml.model.report.Platform;
import com.ml.model.report.PlatformTop;
import com.ml.model.report.Song;
import com.ml.model.report.SongDigital;
import com.ml.model.report.SongFormat;
import com.ml.model.report.SongStatsDigital;
import com.ml.model.report.StationChart;
import com.ml.model.report.TrendingSongs;


@Repository
public class ExecProcRepository {
	
	Logger logger = LoggerFactory.getLogger(ExecProcRepository.class);
	
	@Autowired
	private EntityManager entityManager ;
	
	
	public List<StationChart> getStationsbySong(Long song){
		StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("GET_SONG_CHART_DATA");
		storedProcedure.registerStoredProcedureParameter( 1, String.class, ParameterMode.IN);
		//storedProcedure.registerStoredProcedureParameter( 2, Class.class, ParameterMode.REF_CURSOR );
		
		storedProcedure.setParameter(1, Long.toString(song));
		storedProcedure.execute();
		@SuppressWarnings("unchecked")
		List<Object[]> results = storedProcedure.getResultList();

		List<StationChart> lstres = results.stream().map(result -> new StationChart(
		        result[0].toString(),
			    result[1].toString(),
			    result[2].toString(),
			    result[3].toString(),
			    result[4].toString()
		    )).collect(Collectors.toList());
		return lstres;
		
		
		
	}
	
	
	public SongDigital getDigitalbySong(Long song){
		StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("GET_SONG_DIGITAL_DATA");
		storedProcedure.registerStoredProcedureParameter( 1, String.class, ParameterMode.IN);
		//storedProcedure.registerStoredProcedureParameter( 2, Class.class, ParameterMode.REF_CURSOR );
		
		storedProcedure.setParameter(1, Long.toString(song));
		storedProcedure.execute();
		@SuppressWarnings("unchecked")
		List<Object[]> results = storedProcedure.getResultList();

		SongDigital lstres = results.stream().map(result -> new SongDigital(
		        result[0].toString(),
			    result[1].toString(),
			    result[2].toString(),
			    result[3].toString(),
			    result[4].toString()
		    )).collect(Collectors.toList()).get(0);
		return lstres;
		
	}
	
	
	public List<ChartDigital>  getDigitalChart(Integer format,Integer country, String CRG, Integer custom, Integer genre, Integer city){
		StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("GET_CHART_DATA_DIGITAL");
		storedProcedure.registerStoredProcedureParameter( 1, Integer.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter( 2, Integer.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter( 3, String.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter( 4, Integer.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter( 5, Integer.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter( 6, Integer.class, ParameterMode.IN);
		//storedProcedure.registerStoredProcedureParameter( 2, Class.class, ParameterMode.REF_CURSOR );
		
		storedProcedure.setParameter(1, format);
		storedProcedure.setParameter(2, country);
		storedProcedure.setParameter(3, CRG);
		storedProcedure.setParameter(4, custom);
		storedProcedure.setParameter(5, genre);
		storedProcedure.setParameter(6, city);
		storedProcedure.execute();
		@SuppressWarnings("unchecked")
		List<Object[]> results = storedProcedure.getResultList();

		List<ChartDigital>  lstres = results.stream().map(result -> new ChartDigital(
		        result[0].toString(),
			    result[1].toString(),
			    result[2].toString(),
			    result[3].toString(),
			    result[4].toString(),
			    result[5].toString(),
			    result[6].toString(),
			    result[7].toString(),
			    result[8].toString(),
			    result[9].toString(),
			    result[10].toString(),
			    result[11].toString(),
			    result[12].toString(),
			    result[13].toString(),
			    result[14].toString(),
			    result[15].toString(),
			    result[16].toString(),
			    result[17].toString(),
			    result[18].toString(),
			    result[22].toString(),
			    result[23].toString(),
			    result[20].toString(),
			    result[24].toString()
		    )).collect(Collectors.toList());
		return lstres;
		
		
		
	}
	
	
	public SongStatsDigital  getDigitalSong(Integer cs_song,Integer fmt){
		StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("GET_SONG_DATA");
		storedProcedure.registerStoredProcedureParameter( 1, Integer.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter( 2, Integer.class, ParameterMode.IN);
		//storedProcedure.registerStoredProcedureParameter( 2, Class.class, ParameterMode.REF_CURSOR );
		
		storedProcedure.setParameter(1, cs_song);
		storedProcedure.setParameter(2, fmt);
		storedProcedure.execute();
		@SuppressWarnings("unchecked")
		List<Object[]> results = storedProcedure.getResultList();

		SongStatsDigital lstres = results.stream().map(result -> new SongStatsDigital(
		        result[0].toString(),
			    result[1].toString(),
			    result[2].toString(),
			    result[3].toString(),
			    result[4].toString(),
			    result[6].toString(),
			    result[7].toString(),
			    result[8].toString(),
			    result[9].toString(),
			    result[10].toString(),
			    result[11].toString(),
			    result[12].toString(),
			    result[13].toString(),
			    result[14].toString(),
			    result[15].toString(),
			    result[16].toString(),
			    result[18].toString(),
			    result[19].toString(),
			    result[20].toString(),
			    result[21].toString(),
			    result[22].toString(),
			    result[23].toString(),
			    result[24].toString(),
			    result[25].toString(),
			    result[26].toString(),
			    result[27].toString(),
			    result[28].toString(),
			    result[29].toString(),
			    result[30].toString(),
			    result[31].toString(),
			    result[32].toString(),
			    result[33].toString(),
			    result[38].toString(),
			    result[39].toString(),
			    result[40].toString(),
			    result[41].toString(),
			    result[42].toString(),
			    result[43].toString(),
			    result[44].toString(),
			    result[45].toString(),
			    result[46].toString(),
			    result[51].toString(),
			    result[52].toString(),
			    result[53].toString(),
			    result[54].toString(),
			    result[60].toString(),
			    result[61].toString(),
			    result[62].toString(),
			    result[65].toString(),
			    result[66].toString(),
			    result[67].toString(),
			    result[70].toString(),
			    result[71].toString(),
			    result[72].toString(),
			    result[73].toString(),
			    result[74].toString(),
			    result[75].toString(),
			    result[76].toString(),
			    result[77].toString(),
			    result[78].toString()
		    )).collect(Collectors.toList()).get(0);
		return lstres;
		
		
		
	}
	
	public Song  getDigitalSong(Integer cs_song){
		StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("GET_SONG_REPORTING_DATA");
		storedProcedure.registerStoredProcedureParameter( 1, Integer.class, ParameterMode.IN);
		//storedProcedure.registerStoredProcedureParameter( 2, Class.class, ParameterMode.REF_CURSOR );
		
		storedProcedure.setParameter(1, cs_song);
		storedProcedure.execute();
		@SuppressWarnings("unchecked")
		List<Object[]> results = storedProcedure.getResultList();
		Song lstres = results.stream().map(result -> new Song(
		        result[0].toString(),
			    result[5].toString(),
			    result[5].toString(),
			    result[1].toString(),
			    result[2].toString(),
			    result[3].toString(),
			    result[6].toString()
		    )).collect(Collectors.toList()).get(0);
		return lstres;
		
	}
	
	public List<SongFormat>  getDigitalSongFormat(Integer cs_song,Integer country){
		StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("GET_SONG_CHARTS_BY_FORMAT");
		storedProcedure.registerStoredProcedureParameter( 1, Integer.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter( 2, Integer.class, ParameterMode.IN);
		//storedProcedure.registerStoredProcedureParameter( 2, Class.class, ParameterMode.REF_CURSOR );
		
		storedProcedure.setParameter(1, cs_song);
		storedProcedure.setParameter(2, country);
		storedProcedure.execute();
		@SuppressWarnings("unchecked")
		List<Object[]> results = storedProcedure.getResultList();
		List<SongFormat>  lstres = results.stream().map(result -> new SongFormat(
		        result[0].toString(),
			    result[1].toString(),
			    result[2].toString(),
			    result[3].toString(),
			    result[4].toString()
		    )).collect(Collectors.toList());
		return lstres;
		
	}
	
	
	public List<Country>  getCountries(){
		StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("GET_COUNTRIES");
		//storedProcedure.registerStoredProcedureParameter( 1, Integer.class, ParameterMode.IN);
		//storedProcedure.registerStoredProcedureParameter( 2, Class.class, ParameterMode.REF_CURSOR );
		
		//storedProcedure.setParameter(1, cs_song);
		storedProcedure.execute();
		@SuppressWarnings("unchecked")
		List<Object[]> results = storedProcedure.getResultList();
		List<Country>  lstres = results.stream().map(result -> new Country(
		        result[0].toString(),
			    result[1].toString(),
			    result[2].toString()
		    )).collect(Collectors.toList());
		return lstres;
		
		
		
	}
	
	public List<Format>  getFormats(Integer country){
		StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("GET_FORMAT_BY_COUNTRY");
		storedProcedure.registerStoredProcedureParameter( 1, Integer.class, ParameterMode.IN);
		//storedProcedure.registerStoredProcedureParameter( 2, Class.class, ParameterMode.REF_CURSOR );
		
		storedProcedure.setParameter(1, country);
		storedProcedure.execute();
		@SuppressWarnings("unchecked")
		List<Object[]> results = storedProcedure.getResultList();
		List<Format>  lstres = results.stream().map(result -> new Format(
		        result[0].toString(),
			    result[1].toString()
		    )).collect(Collectors.toList());
		return lstres;
		
		
		
	}
	
	public List<TrendingSongs>  getTrendingTop(Integer format,Integer country){
		StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("GET_TRENDING_DIGITAL");
		storedProcedure.registerStoredProcedureParameter( 1, Integer.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter( 2, Integer.class, ParameterMode.IN);
		//storedProcedure.registerStoredProcedureParameter( 2, Class.class, ParameterMode.REF_CURSOR );
		
		storedProcedure.setParameter(1, format);
		storedProcedure.setParameter(2, country);
		storedProcedure.execute();
		@SuppressWarnings("unchecked")
		List<Object[]> results = storedProcedure.getResultList();

		List<TrendingSongs>  lstres = results.stream().map(result -> new TrendingSongs(
		        result[0].toString(),
			    result[1].toString(),
			    result[2].toString(),
			    result[3].toString(),
			    result[4].toString(),
			    result[5].toString(),
			    result[6].toString(),
			    result[7].toString(),
			    result[8].toString(),
			    ""
		    )).collect(Collectors.toList());
		return lstres;
		
		
		
	}
	
	public List<TrendingSongs>  getTrendingDebut(Integer format,Integer country, String CRG, Integer custom, Integer genre, Integer city){
		StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("GET_TRENDING_DIGITAL_DEBUT_2025");
		storedProcedure.registerStoredProcedureParameter( 1, Integer.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter( 2, Integer.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter( 3, String.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter( 4, Integer.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter( 5, Integer.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter( 6, Integer.class, ParameterMode.IN);
		//storedProcedure.registerStoredProcedureParameter( 2, Class.class, ParameterMode.REF_CURSOR );
		logger.info("run proc: " + "GET_TRENDING_DIGITAL_DEBUT_2025 " + format +" "+ country +" "+ CRG +" "+ custom +" "+ genre +" "+city);
		storedProcedure.setParameter(1, format);
		storedProcedure.setParameter(2, country);
		storedProcedure.setParameter(3, CRG);
		storedProcedure.setParameter(4, custom);
		storedProcedure.setParameter(5, genre);
		storedProcedure.setParameter(6, city);
		storedProcedure.execute();
		@SuppressWarnings("unchecked")
		List<Object[]> results = storedProcedure.getResultList();

		List<TrendingSongs>  lstres = results.stream().map(result -> new TrendingSongs(
		        result[0].toString(),
			    result[1].toString(),
			    result[2].toString(),
			    result[3].toString(),
			    result[4].toString(),
			    result[5].toString(),
			    result[6].toString(),
			    result[7].toString(),
			    result[8].toString(),
			    result[9].toString()
		    )).collect(Collectors.toList());
		return lstres;
		
		
		
	}
	
	
	public List<ArtistDigital>  getTopArtist(Integer format, Integer country){
		StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("GET_TOP_ARTIST");
		storedProcedure.registerStoredProcedureParameter( 1, Integer.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter( 2, Integer.class, ParameterMode.IN);
		storedProcedure.setParameter(1, format);
		storedProcedure.setParameter(2, country);
		storedProcedure.execute();
		@SuppressWarnings("unchecked")
		List<Object[]> results = storedProcedure.getResultList();

		List<ArtistDigital>  lstres = results.stream().map(result -> new ArtistDigital(
		        result[0].toString(),
			    result[1].toString(),
			    result[2].toString(),
			    result[3].toString(),
			    result[4].toString(),
			    result[5].toString(),
			    result[6].toString(),
			    result[7].toString(),
			    result[8].toString(),
			    result[9].toString(),
			    result[19].toString(),
			    result[26].toString(),
			    result[27].toString(),
			    result[28].toString(),
			    result[29].toString()
		    )).collect(Collectors.toList());
		return lstres;
	}
	
	public List<Format>  getFormatsInt(){
		StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("GET_FORMAT_INT");
		//storedProcedure.registerStoredProcedureParameter( 1, Integer.class, ParameterMode.IN);
		//storedProcedure.registerStoredProcedureParameter( 2, Class.class, ParameterMode.REF_CURSOR );
		
		//storedProcedure.setParameter(1, country);
		storedProcedure.execute();
		@SuppressWarnings("unchecked")
		List<Object[]> results = storedProcedure.getResultList();
		List<Format>  lstres = results.stream().map(result -> new Format(
		        result[0].toString(),
			    result[1].toString()
		    )).collect(Collectors.toList());
		return lstres;
	}
	
	
	public List<PlatformTop>  getTopPlatform(String platform, Integer format, Integer country){
		StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("GET_TOP_BY_PLATFORM");
		storedProcedure.registerStoredProcedureParameter( 1, String.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter( 2, Integer.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter( 3, Integer.class, ParameterMode.IN);
		storedProcedure.setParameter(1, platform);
		storedProcedure.setParameter(2, format);
		storedProcedure.setParameter(3, country);
		storedProcedure.execute();
		@SuppressWarnings("unchecked")
		List<Object[]> results = storedProcedure.getResultList();

		List<PlatformTop>  lstres = results.stream().map(result -> new PlatformTop(
		        result[0].toString(),
			    result[1].toString(),
			    result[2].toString(),
			    result[3].toString(),
			    result[4].toString(),
			    result[5].toString(),
			    result[6].toString()
		    )).collect(Collectors.toList());
		return lstres;
	}
	
	public List<Platform>  getPlatforms(){
		StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("GET_PLATFORM");
		//storedProcedure.registerStoredProcedureParameter( 1, Integer.class, ParameterMode.IN);
		//storedProcedure.registerStoredProcedureParameter( 2, Class.class, ParameterMode.REF_CURSOR );
		
		//storedProcedure.setParameter(1, country);
		storedProcedure.execute();
		@SuppressWarnings("unchecked")
		List<Object[]> results = storedProcedure.getResultList();
		List<Platform>  lstres = results.stream().map(result -> new Platform(
		        result[0].toString(),
			    result[1].toString(),
			    result[2].toString(),
			    result[3].toString()
		    )).collect(Collectors.toList());
		return lstres;
	}
	
	public List<Song>  getSearchSong(String search){
		StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("GET_SEARCH_SONG");
		storedProcedure.registerStoredProcedureParameter( 1, String.class, ParameterMode.IN);
		//storedProcedure.registerStoredProcedureParameter( 2, Class.class, ParameterMode.REF_CURSOR );
		
		storedProcedure.setParameter(1, search);
		storedProcedure.execute();
		@SuppressWarnings("unchecked")
		List<Object[]> results = storedProcedure.getResultList();
		List<Song> lstres = results.stream().map(result -> new Song(
				result[0].toString(),
			    result[1].toString(),
			    result[1].toString(),
			    result[2].toString(),
			    result[3].toString(),
			    result[4].toString(),
			    result[5].toString()
		    )).collect(Collectors.toList());
		return lstres;
		
	}
	
	
	public List<CityData>  getCityData(Integer cs_song,Integer country){
		StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("GET_SONG_DIGITAL_DATA_BY_CITY");
		storedProcedure.registerStoredProcedureParameter( 1, Integer.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter( 2, Integer.class, ParameterMode.IN);
		storedProcedure.setParameter(1, cs_song);
		storedProcedure.setParameter(2, country);
		storedProcedure.execute();
		@SuppressWarnings("unchecked")
		List<Object[]> results = storedProcedure.getResultList();
		List<CityData> cities = results.stream().map(result -> new CityData(
				result[0].toString(),
			    result[1].toString(),
			    result[2].toString(),
			    result[3].toString(),
			    result[4].toString(),
			    result[5].toString(),
			    result[9].toString(),
			    result[10].toString(),
			    result[11].toString()
		    )).collect(Collectors.toList());
		return cities;
		
		
	}
	
	
	public List<City>  getCities(String country, String CRG){
		StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("GET_CITIES_BY_COUNTRY");
		storedProcedure.registerStoredProcedureParameter( 1, String.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter( 2, String.class, ParameterMode.IN);
		storedProcedure.setParameter(1, country);
		storedProcedure.setParameter(2, CRG);
		storedProcedure.execute();
		@SuppressWarnings("unchecked")
		List<Object[]> results = storedProcedure.getResultList();
		List<City> cities = results.stream().map(result -> new City(
				result[0].toString(),
			    result[1].toString(),
			    result[2].toString()
		    )).collect(Collectors.toList());
		return cities;
		
		
	}
	
	public List<ChatUser>  getUserChat(String phone){
		StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("GET_CHAT_USER");
		storedProcedure.registerStoredProcedureParameter( 1, String.class, ParameterMode.IN);
		storedProcedure.setParameter(1, phone);
		storedProcedure.execute();
		@SuppressWarnings("unchecked")
		List<Object[]> results = storedProcedure.getResultList();
		List<ChatUser> users = results.stream().map(result -> new ChatUser(
				result[0].toString(),
			    result[1].toString(),
			    result[2].toString(),
			    result[3].toString(),
			    result[4].toString(),
			    result[5].toString()
		    )).collect(Collectors.toList());
		return users;
		
		
	}
	
	
	public void setLog(String phone, String message, String response) {
		StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("SEG_LOG_CHAT");
		storedProcedure.registerStoredProcedureParameter( 1, String.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter( 2, String.class, ParameterMode.IN);
		storedProcedure.registerStoredProcedureParameter( 3, String.class, ParameterMode.IN);
		storedProcedure.setParameter(1, phone);
		storedProcedure.setParameter(2, message);
		storedProcedure.setParameter(3, response);
		storedProcedure.execute();
	}
	
	
	
		
		
	
	

}
