package com.ml.model.report;

public class PlatformTop {
	private Integer rk;
	private String song;
	private String artists;
	private String label;
	private Long data_res;
	private Integer cs_song;
	
	
	
	
	public Integer getCs_song() {
		return cs_song;
	}
	public void setCs_song(Integer cs_song) {
		this.cs_song = cs_song;
	}
	public Integer getRk() {
		return rk;
	}
	public void setRk(Integer rk) {
		this.rk = rk;
	}
	public String getSong() {
		return song;
	}
	public void setSong(String song) {
		this.song = song;
	}
	public String getArtists() {
		return artists;
	}
	public void setArtists(String artists) {
		this.artists = artists;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public Long getData_res() {
		return data_res;
	}
	public void setData_res(Long data_res) {
		this.data_res = data_res;
	}
	public PlatformTop(Integer rk, String song, String artists, String label, Long data_res, Integer cs_song) {
		super();
		this.rk = rk;
		this.song = song;
		this.artists = artists;
		this.label = label;
		this.data_res = data_res;
	}
	
	public PlatformTop(String rk, String song, String artists, String label, String data_res, String cs_song) {
		super();
		this.rk = Integer.parseInt(rk);
		this.song = song;
		this.artists = artists;
		this.label = label;
		this.data_res = Long.parseLong(data_res);
		this.cs_song = Integer.parseInt(cs_song);
	}
	
	
	
	

}
