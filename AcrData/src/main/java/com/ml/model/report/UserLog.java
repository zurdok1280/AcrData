package com.ml.model.report;

public class UserLog {
	
	private String phone;
	private String message;
	private String response;
	
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public UserLog(String phone, String message, String response) {
		super();
		this.phone = phone;
		this.message = message;
		this.response = response;
	}
	public UserLog() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	

}
