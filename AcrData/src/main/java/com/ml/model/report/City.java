package com.ml.model.report;

public class City {
	
	private Integer id;
	private String city_name;
	private String country_code;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCity_name() {
		return city_name;
	}
	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}
	public String getCountry_code() {
		return country_code;
	}
	public void setCountry_code(String country_code) {
		this.country_code = country_code;
	}
	public City(Integer id, String city_name, String country_code) {
		super();
		this.id = id;
		this.city_name = city_name;
		this.country_code = country_code;
	}
	public City() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public City(String id, String city_name, String country_code) {
		super();
		this.id = Integer.parseInt(id);
		this.city_name = city_name;
		this.country_code = country_code;
	}
	
	
	
	

}
