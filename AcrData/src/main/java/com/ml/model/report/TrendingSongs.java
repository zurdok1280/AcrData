package com.ml.model.report;

public class TrendingSongs {
	
	private Integer cs_song;
	private String CRG;
	private String song;
	private String artists;
	private String label;
	private Float tw_score;
	private Float lw_score;
	private Float dif_score;
	private Integer rk_trending;
	private String img;
	
	
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public Integer getCs_song() {
		return cs_song;
	}
	public void setCs_song(Integer cs_song) {
		this.cs_song = cs_song;
	}
	public String getCRG() {
		return CRG;
	}
	public void setCRG(String cRG) {
		CRG = cRG;
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
	public Float getTw_score() {
		return tw_score;
	}
	public void setTw_score(Float tw_score) {
		this.tw_score = tw_score;
	}
	public Float getLw_score() {
		return lw_score;
	}
	public void setLw_score(Float lw_score) {
		this.lw_score = lw_score;
	}
	public Float getDif_score() {
		return dif_score;
	}
	public void setDif_score(Float dif_score) {
		this.dif_score = dif_score;
	}
	public Integer getRk_trending() {
		return rk_trending;
	}
	public void setRk_trending(Integer rk_trending) {
		this.rk_trending = rk_trending;
	}
	public TrendingSongs(Integer cs_song, String cRG, String song, String artists, String label, Float tw_score,
			Float lw_score, Float dif_score, Integer rk_trending, String img) {
		super();
		this.cs_song = cs_song;
		CRG = cRG;
		this.song = song;
		this.artists = artists;
		this.label = label;
		this.tw_score = tw_score;
		this.lw_score = lw_score;
		this.dif_score = dif_score;
		this.rk_trending = rk_trending;
		this.img = img;
	}
	
	public TrendingSongs(String cs_song, String cRG, String song, String artists, String label, String tw_score,
			String lw_score, String dif_score, String rk_trending, String img) {
		super();
		this.cs_song = Integer.parseInt(cs_song);
		CRG = cRG;
		this.song =  song;
		this.artists = artists;
		this.label = label;
		this.tw_score = Float.parseFloat(tw_score);
		this.lw_score = Float.parseFloat(lw_score);
		this.dif_score = Float.parseFloat(dif_score);
		this.rk_trending = Integer.parseInt(rk_trending);
		this.img = img;
	}
	public TrendingSongs() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	

}
