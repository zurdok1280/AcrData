package com.ml.model.report;

public class StationChart {
	private int rk;
	private Long stream_id;
	private String stream_desc;
	private String market;
	private int spins;
	
	
	
	public int getRk() {
		return rk;
	}
	public void setRk(int rk) {
		this.rk = rk;
	}
	public Long getStream_id() {
		return stream_id;
	}
	public void setStream_id(Long stream_id) {
		this.stream_id = stream_id;
	}
	public String getStream_desc() {
		return stream_desc;
	}
	public void setStream_desc(String stream_desc) {
		this.stream_desc = stream_desc;
	}
	public String getMarket() {
		return market;
	}
	public void setMarket(String market) {
		this.market = market;
	}
	public int getSpins() {
		return spins;
	}
	public void setSpins(int spins) {
		this.spins = spins;
	}
	public StationChart(int rk, Long stream_id, String stream_desc, String market, int spins) {
		super();
		this.rk = rk;
		this.stream_id = stream_id;
		this.stream_desc = stream_desc;
		this.market = market;
		this.spins = spins;
	}
	public StationChart(String rk, String stream_id, String stream_desc, String market, String spins) {
		super();
		this.rk = Integer.parseInt(rk);
		this.stream_id = Long.parseLong(stream_id);
		this.stream_desc = stream_desc;
		this.market = market;
		this.spins = Integer.parseInt(spins);
	}
	public StationChart() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}
