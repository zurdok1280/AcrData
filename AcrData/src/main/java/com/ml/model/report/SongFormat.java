package com.ml.model.report;

public class SongFormat {
	private String format;
	private Float score;
	private Integer rk;
	private Integer rk_radio;
	private String fcolor;
	
	
	
	
	public String getFcolor() {
		return fcolor;
	}
	public void setFcolor(String fcolor) {
		this.fcolor = fcolor;
	}
	public Integer getRk_radio() {
		return rk_radio;
	}
	public void setRk_radio(Integer rk_radio) {
		this.rk_radio = rk_radio;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public Float getScore() {
		return score;
	}
	public void setScore(Float score) {
		this.score = score;
	}
	public Integer getRk() {
		return rk;
	}
	public void setRk(Integer rk) {
		this.rk = rk;
	}
	public SongFormat(String format, Float score, Integer rk,String fcolor, Integer rk_radio) {
		super();
		this.format = format;
		this.score = score;
		this.rk = rk;
		this.rk_radio = rk_radio;
		this.fcolor = fcolor;
	}
	
	public SongFormat(String format, String score, String rk,String fcolor,  String rk_radio) {
		super();
		this.format = format;
		this.score = Float.parseFloat(score);
		this.rk = Integer.parseInt(rk);
		this.rk_radio = Integer.parseInt(rk_radio);
		this.fcolor = fcolor;
	}
	public SongFormat() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}
