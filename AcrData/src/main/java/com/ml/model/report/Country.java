package com.ml.model.report;

public class Country {
	private Integer id;
	private String country;
	private String country_name;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCountry_name() {
		return country_name;
	}
	public void setCountry_name(String country_name) {
		this.country_name = country_name;
	}
	public Country(Integer id, String country, String country_name) {
		super();
		this.id = id;
		this.country = country;
		this.country_name = country_name;
	}
	public Country() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Country(String id, String country, String country_name) {
		super();
		this.id = Integer.parseInt(id);
		this.country = country;
		this.country_name = country_name;
	}

	
	
	
}
