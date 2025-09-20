package com.ml.model.report;

public class Platform {
	private Integer id;
	private String source;
	private String color;
	private String img;
	
	
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Platform(Integer id, String source, String color) {
		super();
		this.id = id;
		this.source = source;
		this.color = color;
	}
	public Platform(String id, String source, String color, String img) {
		super();
		this.id = Integer.parseInt(id);
		this.source = source;
		this.color = color;
		this.img = img;
	}
	
	
	
	

}
