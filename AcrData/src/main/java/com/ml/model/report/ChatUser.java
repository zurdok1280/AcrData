package com.ml.model.report;

public class ChatUser {
	private Integer id;
	private String name;
	private String phone;
	private Boolean isOn;
	private String thread;
	private Integer lastmin;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Boolean getIsOn() {
		return isOn;
	}
	public void setIsOn(Boolean isOn) {
		this.isOn = isOn;
	}
	public String getThread() {
		return thread;
	}
	public void setThread(String thread) {
		this.thread = thread;
	}
	public Integer getLastmin() {
		return lastmin;
	}
	public void setLastmin(Integer lastmin) {
		this.lastmin = lastmin;
	}
	public ChatUser(Integer id, String name, String phone, Boolean isOn, String thread, Integer lastmin) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.isOn = isOn;
		this.thread = thread;
		this.lastmin = lastmin;
	}
	
	
	public ChatUser(String id, String name, String phone, String isOn, String thread, String lastmin) {
		super();
		this.id = Integer.parseInt(id);
		this.name = name;
		this.phone = phone;
		this.isOn = Boolean.parseBoolean(isOn);
		this.thread = thread;
		this.lastmin = Integer.parseInt(lastmin);
	}
	
	
	
	
	
}
