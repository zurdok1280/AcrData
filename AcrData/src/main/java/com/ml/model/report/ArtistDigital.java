package com.ml.model.report;

public class ArtistDigital {
	private Integer rk;
	private String artist;
	private Long monthly_listeners;
	private Long followers_total;
	private Long popularity;
	private Long streams_total;
	private Long playlists;
	private Long playlist_reach;
	private Long followers_total_instagram;
	private Long followers_total_tiktok;
	private Long videos_views_total_youtube;
	private Long followers_total_facebook;
	private Long followers_total_twitter;
	private Double spotify_streams;
	private String img;
	
	
	
	
	
	
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public Double getSpotify_streams() {
		return spotify_streams;
	}
	public void setSpotify_streams(Double spotify_streams) {
		this.spotify_streams = spotify_streams;
	}
	public Long getFollowers_total_tiktok() {
		return followers_total_tiktok;
	}
	public void setFollowers_total_tiktok(Long followers_total_tiktok) {
		this.followers_total_tiktok = followers_total_tiktok;
	}
	public Long getVideos_views_total_youtube() {
		return videos_views_total_youtube;
	}
	public void setVideos_views_total_youtube(Long videos_views_total_youtube) {
		this.videos_views_total_youtube = videos_views_total_youtube;
	}
	public Long getFollowers_total_facebook() {
		return followers_total_facebook;
	}
	public void setFollowers_total_facebook(Long followers_total_facebook) {
		this.followers_total_facebook = followers_total_facebook;
	}
	public Long getFollowers_total_twitter() {
		return followers_total_twitter;
	}
	public void setFollowers_total_twitter(Long followers_total_twitter) {
		this.followers_total_twitter = followers_total_twitter;
	}
	public Integer getRk() {
		return rk;
	}
	public void setRk(Integer rk) {
		this.rk = rk;
	}
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	public Long getMonthly_listeners() {
		return monthly_listeners;
	}
	public void setMonthly_listeners(Long monthly_listeners) {
		this.monthly_listeners = monthly_listeners;
	}
	public Long getFollowers_total() {
		return followers_total;
	}
	public void setFollowers_total(Long followers_total) {
		this.followers_total = followers_total;
	}
	public Long getPopularity() {
		return popularity;
	}
	public void setPopularity(Long popularity) {
		this.popularity = popularity;
	}
	public Long getStreams_total() {
		return streams_total;
	}
	public void setStreams_total(Long streams_total) {
		this.streams_total = streams_total;
	}
	public Long getPlaylists() {
		return playlists;
	}
	public void setPlaylists(Long playlists) {
		this.playlists = playlists;
	}
	public Long getPlaylist_reach() {
		return playlist_reach;
	}
	public void setPlaylist_reach(Long playlist_reach) {
		this.playlist_reach = playlist_reach;
	}
	public Long getFollowers_total_instagram() {
		return followers_total_instagram;
	}
	public void setFollowers_total_instagram(Long followers_total_instagram) {
		this.followers_total_instagram = followers_total_instagram;
	}
	public ArtistDigital(Integer rk, String artist, Long monthly_listeners, Long followers_total,
			Long popularity, Long streams_total, Long playlists, Long playlist_reach,
			Long followers_total_instagram) {
		super();
		this.rk = rk;
		this.artist = artist;
		this.monthly_listeners = monthly_listeners;
		this.followers_total = followers_total;
		this.popularity = popularity;
		this.streams_total = streams_total;
		this.playlists = playlists;
		this.playlist_reach = playlist_reach;
		this.followers_total_instagram = followers_total_instagram;
	}
	
	
	public ArtistDigital(String rk, String artist, String monthly_listeners, String followers_total,
			String popularity, String streams_total, String playlists, String playlist_reach,
			String followers_total_instagram, String followers_total_tiktok, String videos_views_total_youtube, String followers_total_facebook, String followers_total_twitter, String spotify_streams,
			String img) {
		super();
		this.rk = Integer.parseInt(rk);
		this.artist = artist;
		this.monthly_listeners = Long.parseLong(monthly_listeners);
		this.followers_total = Long.parseLong(followers_total);
		this.popularity = Long.parseLong(popularity);
		this.streams_total = Long.parseLong(streams_total);
		this.playlists = Long.parseLong(playlists);
		this.playlist_reach = Long.parseLong(playlist_reach);
		this.followers_total_instagram = Long.parseLong(followers_total_instagram);
		this.followers_total_tiktok = Long.parseLong(followers_total_tiktok);
		this.videos_views_total_youtube = Long.parseLong(videos_views_total_youtube);
		this.followers_total_facebook = Long.parseLong(followers_total_facebook);
		this.followers_total_twitter = Long.parseLong(followers_total_twitter);
		this.spotify_streams = Double.parseDouble(spotify_streams);
		this.img = img;
	}
	
	
	
	

}
