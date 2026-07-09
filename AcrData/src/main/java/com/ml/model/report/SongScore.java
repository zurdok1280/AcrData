package com.ml.model.report;

public class SongScore {

	private Integer rk;
	private Integer cs_song;
	private Float score;
	private String avatar;
	private String song;
	private String artists;
	private String label;
	public Integer getRk() {
		return rk;
	}
	public void setRk(Integer rk) {
		this.rk = rk;
	}
	public Integer getCs_song() {
		return cs_song;
	}
	public void setCs_song(Integer cs_song) {
		this.cs_song = cs_song;
	}
	public Float getScore() {
		return score;
	}
	public void setScore(Float score) {
		this.score = score;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
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
	public SongScore(Integer rk, Integer cs_song, Float score, String avatar, String song, String artists,
			String label) {
		super();
		this.rk = rk;
		this.cs_song = cs_song;
		this.score = score;
		this.avatar = avatar;
		this.song = song;
		this.artists = artists;
		this.label = label;
	}
	public SongScore() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public SongScore(String rk, String cs_song, String score, String avatar, String song, String artists,
			String label) {
		super();
		this.rk = Integer.parseInt(rk);
		this.cs_song = Integer.parseInt(cs_song);
		this.score = Float.parseFloat(score);
		this.avatar = avatar;
		this.song = song;
		this.artists = artists;
		this.label = label;
	}
	
	
	
	
}
