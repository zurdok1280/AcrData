package com.ml.model.report;

public class SongCountries {
	
	private String country;
	private Integer spins;
	private Integer rank;
	private Long audience;
	private Integer sts;
	
	
	
	
	public Integer getSts() {
		return sts;
	}
	public void setSts(Integer sts) {
		this.sts = sts;
	}
	public Long getAudience() {
		return audience;
	}
	public void setAudience(Long audience) {
		this.audience = audience;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public Integer getSpins() {
		return spins;
	}
	public void setSpins(Integer spins) {
		this.spins = spins;
	}
	public Integer getRank() {
		return rank;
	}
	public void setRank(Integer rank) {
		this.rank = rank;
	}
	public SongCountries(String country, Integer spins, Integer rank, Long audience, Integer sts) {
		super();
		this.country = country;
		this.spins = spins;
		this.rank = rank;
		this.audience = audience;
		this.sts = sts;
	}
	
	
	public SongCountries(String country, String spins, String rank, String audience, String sts) {
		super();
		this.country = country;
		this.spins = Integer.parseInt(spins);
		this.rank = Integer.parseInt(rank);
		this.audience = Long.parseLong(audience);
		this.sts = Integer.parseInt(sts);
	}
	
	
	

}
