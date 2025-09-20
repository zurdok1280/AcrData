package com.ml.model.report;

public class SongDigital {
	private String sp_streams;
	private String tiktok_counts;
	private String youtube_views;
	private String num_sp_playlists;
	private String date_created;
	
	
	public String getSp_streams() {
		return sp_streams;
	}
	public void setSp_streams(String sp_streams) {
		this.sp_streams = sp_streams;
	}
	public String getTiktok_counts() {
		return tiktok_counts;
	}
	public void setTiktok_counts(String tiktok_counts) {
		this.tiktok_counts = tiktok_counts;
	}
	public String getYoutube_views() {
		return youtube_views;
	}
	public void setYoutube_views(String youtube_views) {
		this.youtube_views = youtube_views;
	}
	public String getNum_sp_playlists() {
		return num_sp_playlists;
	}
	public void setNum_sp_playlists(String num_sp_playlists) {
		this.num_sp_playlists = num_sp_playlists;
	}
	public String getDate_created() {
		return date_created;
	}
	public void setDate_created(String date_created) {
		this.date_created = date_created;
	}
	public SongDigital() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SongDigital(String sp_streams, String tiktok_counts, String youtube_views, String num_sp_playlists,
			String date_created) {
		super();
		this.sp_streams = sp_streams;
		this.tiktok_counts = tiktok_counts;
		this.youtube_views = youtube_views;
		this.num_sp_playlists = num_sp_playlists;
		this.date_created = date_created;
	}
	
	
	
	
	
	

}
