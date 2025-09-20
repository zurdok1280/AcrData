package com.ml.model.report;

public class Format {
	private Integer id;	
	private String format;
	
	
	
	
	
	
	public Integer getId() {
		return id;
	}






	public void setId(Integer id) {
		this.id = id;
	}






	public String getFormat() {
		return format;
	}






	public void setFormat(String format) {
		this.format = format;
	}




	

	public Format() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Format(Integer id, String format) {
		super();
		this.id = id;
		this.format = format;
	}
	
	public Format(String id, String format) {
		super();
		this.id = Integer.parseInt(id);
		this.format = format;
	}
	
	
	

}
