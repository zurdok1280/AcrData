package com.ml.model.report;

public class SongRecommendation {
	private Integer id;
	private String artist;
	private Long followers_total_facebook;
	private Long followers_total_instagram;
	private Long followers_total_tiktok;
	private Long followers_total_twitter;
	private Long subscribers_total_youtube;
	private Long playlist_reach;
	private Long video_total_youtube;
	private Long monthly_listeners;
	private Long video_views_total_youtube;
	private Long video_reach_total_youtube;
	private Float engagement_rate_tiktok;
	private Long spotify_playlist_reach_current;
	private Float tiktok_engagement_rate_total;
	
	
	
	
	public Float getEngagement_rate_tiktok() {
		return engagement_rate_tiktok;
	}
	public void setEngagement_rate_tiktok(Float engagement_rate_tiktok) {
		this.engagement_rate_tiktok = engagement_rate_tiktok;
	}
	public Long getSpotify_playlist_reach_current() {
		return spotify_playlist_reach_current;
	}
	public void setSpotify_playlist_reach_current(Long spotify_playlist_reach_current) {
		this.spotify_playlist_reach_current = spotify_playlist_reach_current;
	}
	public Float getTiktok_engagement_rate_total() {
		return tiktok_engagement_rate_total;
	}
	public void setTiktok_engagement_rate_total(Float tiktok_engagement_rate_total) {
		this.tiktok_engagement_rate_total = tiktok_engagement_rate_total;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	public Long getFollowers_total_facebook() {
		return followers_total_facebook;
	}
	public void setFollowers_total_facebook(Long followers_total_facebook) {
		this.followers_total_facebook = followers_total_facebook;
	}
	public Long getFollowers_total_instagram() {
		return followers_total_instagram;
	}
	public void setFollowers_total_instagram(Long followers_total_instagram) {
		this.followers_total_instagram = followers_total_instagram;
	}
	public Long getFollowers_total_tiktok() {
		return followers_total_tiktok;
	}
	public void setFollowers_total_tiktok(Long followers_total_tiktok) {
		this.followers_total_tiktok = followers_total_tiktok;
	}
	public Long getFollowers_total_twitter() {
		return followers_total_twitter;
	}
	public void setFollowers_total_twitter(Long followers_total_twitter) {
		this.followers_total_twitter = followers_total_twitter;
	}
	public Long getSubscribers_total_youtube() {
		return subscribers_total_youtube;
	}
	public void setSubscribers_total_youtube(Long subscribers_total_youtube) {
		this.subscribers_total_youtube = subscribers_total_youtube;
	}
	public Long getPlaylist_reach() {
		return playlist_reach;
	}
	public void setPlaylist_reach(Long playlist_reach) {
		this.playlist_reach = playlist_reach;
	}
	public Long getVideo_total_youtube() {
		return video_total_youtube;
	}
	public void setVideo_total_youtube(Long video_total_youtube) {
		this.video_total_youtube = video_total_youtube;
	}
	public Long getMonthly_listeners() {
		return monthly_listeners;
	}
	public void setMonthly_listeners(Long monthly_listeners) {
		this.monthly_listeners = monthly_listeners;
	}
	public Long getVideo_views_total_youtube() {
		return video_views_total_youtube;
	}
	public void setVideo_views_total_youtube(Long video_views_total_youtube) {
		this.video_views_total_youtube = video_views_total_youtube;
	}
	public Long getVideo_reach_total_youtube() {
		return video_reach_total_youtube;
	}
	public void setVideo_reach_total_youtube(Long video_reach_total_youtube) {
		this.video_reach_total_youtube = video_reach_total_youtube;
	}
	public SongRecommendation(Integer id, String artist, Long followers_total_facebook, Long followers_total_instagram,
			Long followers_total_tiktok, Long followers_total_twitter, Long subscribers_total_youtube,
			Long playlist_reach, Long video_total_youtube, Long monthly_listeners, Long video_views_total_youtube,
			Long video_reach_total_youtube, Float engagement_rate_tiktok,Long spotify_playlist_reach_current,Float tiktok_engagement_rate_total) {
		super();
		this.id = id;
		this.artist = artist;
		this.followers_total_facebook = followers_total_facebook;
		this.followers_total_instagram = followers_total_instagram;
		this.followers_total_tiktok = followers_total_tiktok;
		this.followers_total_twitter = followers_total_twitter;
		this.subscribers_total_youtube = subscribers_total_youtube;
		this.playlist_reach = playlist_reach;
		this.video_total_youtube = video_total_youtube;
		this.monthly_listeners = monthly_listeners;
		this.video_views_total_youtube = video_views_total_youtube;
		this.video_reach_total_youtube = video_reach_total_youtube;
		this.engagement_rate_tiktok = engagement_rate_tiktok;
		this.spotify_playlist_reach_current = spotify_playlist_reach_current;
		this.tiktok_engagement_rate_total = tiktok_engagement_rate_total;
	}
	
	
	public SongRecommendation(String id, String artist, String followers_total_facebook, String followers_total_instagram,
			String followers_total_tiktok, String followers_total_twitter, String subscribers_total_youtube,
			String playlist_reach, String video_total_youtube, String monthly_listeners, String video_views_total_youtube,
			String video_reach_total_youtube, String engagement_rate_tiktok,String spotify_playlist_reach_current,String tiktok_engagement_rate_total) {
		super();
		this.id = Integer.parseInt(id);
		this.artist = artist;
		this.followers_total_facebook = Long.parseLong(followers_total_facebook);
		this.followers_total_instagram = Long.parseLong(followers_total_instagram);
		this.followers_total_tiktok = Long.parseLong(followers_total_tiktok);
		this.followers_total_twitter = Long.parseLong(followers_total_twitter);
		this.subscribers_total_youtube = Long.parseLong(subscribers_total_youtube);
		this.playlist_reach = Long.parseLong(playlist_reach);
		this.video_total_youtube = Long.parseLong(video_total_youtube);
		this.monthly_listeners = Long.parseLong(monthly_listeners);
		this.video_views_total_youtube = Long.parseLong(video_views_total_youtube);
		this.video_reach_total_youtube = Long.parseLong(video_reach_total_youtube);
		this.engagement_rate_tiktok = Float.parseFloat(engagement_rate_tiktok);
		this.spotify_playlist_reach_current = Long.parseLong(spotify_playlist_reach_current);
		this.tiktok_engagement_rate_total = Float.parseFloat(tiktok_engagement_rate_total);
	}
	
	
	public SongRecommendation() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}
