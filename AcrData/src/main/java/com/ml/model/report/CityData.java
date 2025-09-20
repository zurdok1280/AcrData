package com.ml.model.report;

public class CityData {
	
	private int rnk;
	private String cityname;
	private float listeners;
	private int spins;
	private double audience;
	private int sts;
	private int cityid;
	private float citylat;
	private float citylng;
	public int getRnk() {
		return rnk;
	}
	public void setRnk(int rnk) {
		this.rnk = rnk;
	}
	public String getCityname() {
		return cityname;
	}
	public void setCityname(String cityname) {
		this.cityname = cityname;
	}
	public float getListeners() {
		return listeners;
	}
	public void setListeners(float listeners) {
		this.listeners = listeners;
	}
	public int getSpins() {
		return spins;
	}
	public void setSpins(int spins) {
		this.spins = spins;
	}
	public double getAudience() {
		return audience;
	}
	public void setAudience(double audience) {
		this.audience = audience;
	}
	public int getSts() {
		return sts;
	}
	public void setSts(int sts) {
		this.sts = sts;
	}
	public int getCityid() {
		return cityid;
	}
	public void setCityid(int cityid) {
		this.cityid = cityid;
	}
	public float getCitylat() {
		return citylat;
	}
	public void setCitylat(float citylat) {
		this.citylat = citylat;
	}
	public float getCitylng() {
		return citylng;
	}
	public void setCitylng(float citylng) {
		this.citylng = citylng;
	}
	public CityData(int rnk, String cityname, float listeners, int spins, double audience, int sts, int cityid,
			float citylat, float citylng) {
		super();
		this.rnk = rnk;
		this.cityname = cityname;
		this.listeners = listeners;
		this.spins = spins;
		this.audience = audience;
		this.sts = sts;
		this.cityid = cityid;
		this.citylat = citylat;
		this.citylng = citylng;
	}
	public CityData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CityData(String rnk, String cityname, String listeners, String spins, String audience, String sts, String cityid,
			String citylat, String citylng) {
		super();
		this.rnk = Integer.parseInt(rnk);
		this.cityname = cityname;
		this.listeners = Float.parseFloat(listeners);
		this.spins = Integer.parseInt(spins);
		this.audience = Double.parseDouble(audience);
		this.sts = Integer.parseInt(sts);
		this.cityid = Integer.parseInt(cityid);
		this.citylat = Float.parseFloat(citylat);
		this.citylng = Float.parseFloat(citylng);
	}
	
	
	

}
