package com.ml.model.report;

public class Song {
	private String id;
	private String avatar;
	private String background;
	private String title;
	private String artist;
	private String label;
	private String url;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getBackground() {
		return background;
	}
	public void setBackground(String background) {
		this.background = background;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	
	
	
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Song(String id, String avatar, String background, String title, String artist, String label, String url) {
		super();
		this.id = id;
		this.avatar = avatar;
		this.background = background;
		this.title = title;
		this.artist = artist;
		this.label = label;
		this.url = url;
	}
	public Song() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Song [id=");
		builder.append(id);
		builder.append(", avatar=");
		builder.append(avatar);
		builder.append(", background=");
		builder.append(background);
		builder.append(", title=");
		builder.append(title);
		builder.append(", artist=");
		builder.append(artist);
		builder.append(", label=");
		builder.append(label);
		builder.append("]");
		return builder.toString();
	}
	
	
	

}
