package com.ml.model.report;

public class MessageChat {
	private String phone;
	private String contact;
	private Integer country;
	private Integer format;
	private String message;
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public Integer getCountry() {
		return country;
	}
	public void setCountry(Integer country) {
		this.country = country;
	}
	public Integer getFormat() {
		return format;
	}
	public void setFormat(Integer format) {
		this.format = format;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public MessageChat(String phone, String contact, Integer country, Integer format, String message) {
		super();
		this.phone = phone;
		this.contact = contact;
		this.country = country;
		this.format = format;
		this.message = message;
	}
	public MessageChat() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MessageChat [phone=");
		builder.append(phone);
		builder.append(", contact=");
		builder.append(contact);
		builder.append(", country=");
		builder.append(country);
		builder.append(", format=");
		builder.append(format);
		builder.append(", message=");
		builder.append(message);
		builder.append("]");
		return builder.toString();
	}
	
	
	
	
	
}
