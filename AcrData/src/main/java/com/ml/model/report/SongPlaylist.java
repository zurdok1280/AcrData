package com.ml.model.report;

public class SongPlaylist {
	
	private Integer rank;
	private String spotify_id;
	private String playlist_name;
	private String owner_name;
	private String artwork;
	private String external_url;
	private Integer current_position;
	private Integer top_position;
	private Long followers;
	private String type_name;
	
	
	public Integer getRank() {
		return rank;
	}
	public void setRank(Integer rank) {
		this.rank = rank;
	}
	public String getSpotify_id() {
		return spotify_id;
	}
	public void setSpotify_id(String spotify_id) {
		this.spotify_id = spotify_id;
	}
	public String getPlaylist_name() {
		return playlist_name;
	}
	public void setPlaylist_name(String playlist_name) {
		this.playlist_name = playlist_name;
	}
	public String getOwner_name() {
		return owner_name;
	}
	public void setOwner_name(String owner_name) {
		this.owner_name = owner_name;
	}
	public String getArtwork() {
		return artwork;
	}
	public void setArtwork(String artwork) {
		this.artwork = artwork;
	}
	public String getExternal_url() {
		return external_url;
	}
	public void setExternal_url(String external_url) {
		this.external_url = external_url;
	}
	public Integer getCurrent_position() {
		return current_position;
	}
	public void setCurrent_position(Integer current_position) {
		this.current_position = current_position;
	}
	public Integer getTop_position() {
		return top_position;
	}
	public void setTop_position(Integer top_position) {
		this.top_position = top_position;
	}
	public Long getFollowers() {
		return followers;
	}
	public void setFollowers(Long followers) {
		this.followers = followers;
	}
	public String getType_name() {
		return type_name;
	}
	public void setType_name(String type_name) {
		this.type_name = type_name;
	}
	
	
	public SongPlaylist(Integer rank, String spotify_id, String playlist_name, String owner_name, String artwork,
			String external_url, Integer current_position, Integer top_position, Long followers, String type_name) {
		super();
		this.rank = rank;
		this.spotify_id = spotify_id;
		this.playlist_name = playlist_name;
		this.owner_name = owner_name;
		this.artwork = artwork;
		this.external_url = external_url;
		this.current_position = current_position;
		this.top_position = top_position;
		this.followers = followers;
		this.type_name = type_name;
	}
	
	
	public SongPlaylist(String rank, String spotify_id, String playlist_name, String owner_name, String artwork,
			String external_url, String current_position, String top_position, String followers, String type_name) {
		super();
		this.rank = Integer.parseInt(rank);
		this.spotify_id = spotify_id;
		this.playlist_name = playlist_name;
		this.owner_name = owner_name;
		this.artwork = artwork;
		this.external_url = external_url;
		this.current_position = Integer.parseInt(current_position);
		this.top_position = Integer.parseInt(top_position);
		this.followers = Long.parseLong(followers);
		this.type_name = type_name;
	}
	
	
	
	

}
