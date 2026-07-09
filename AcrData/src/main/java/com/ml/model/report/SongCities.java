package com.ml.model.report;

public class SongCities {
	
	private String market;
	private Integer spins;
	private Integer rank;
	public String getMarket() {
		return market;
	}
	public void setMarket(String market) {
		this.market = market;
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
	public SongCities(String market, Integer spins, Integer rank) {
		super();
		this.market = market;
		this.spins = spins;
		this.rank = rank;
	}
	
	
	public SongCities(String market, String spins, String rank) {
		super();
		this.market = market;
		this.spins = Integer.parseInt(spins);
		this.rank = Integer.parseInt(rank);
	}
	
	
	
	

}
