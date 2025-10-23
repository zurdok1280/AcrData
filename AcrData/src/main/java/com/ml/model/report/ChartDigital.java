package com.ml.model.report;


public class ChartDigital {
	
	private Integer cs_song;
	private String CRG;
	private String Song;
	private String Artists;
	private String Label;
	private Long spotify_streams_total;
	private Long tiktok_views_total;
	private Long youtube_video_views_total;
	private Long youtube_short_views_total;
	private Long shazams_total;
	private Long soundcloud_stream_total;
	private Long pan_streams;
	private Long audience_total;
	private Integer spins_total;
	private Float score;
	private Integer rk_total;
	private Integer tw_spins;
	private Integer tw_aud;
	private Integer rk;
	
	
	
	public Integer getRk() {
		return rk;
	}
	public void setRk(Integer rk) {
		this.rk = rk;
	}
	public Long getYoutube_short_views_total() {
		return youtube_short_views_total;
	}
	public void setYoutube_short_views_total(Long youtube_short_views_total) {
		this.youtube_short_views_total = youtube_short_views_total;
	}
	public Long getAudience_total() {
		return audience_total;
	}
	public void setAudience_total(Long audience_total) {
		this.audience_total = audience_total;
	}
	public Integer getSpins_total() {
		return spins_total;
	}
	public void setSpins_total(Integer spins_total) {
		this.spins_total = spins_total;
	}
	public Integer getTw_spins() {
		return tw_spins;
	}
	public void setTw_spins(Integer tw_spins) {
		this.tw_spins = tw_spins;
	}
	public Integer getTw_aud() {
		return tw_aud;
	}
	public void setTw_aud(Integer tw_aud) {
		this.tw_aud = tw_aud;
	}
	public Integer getCs_song() {
		return cs_song;
	}
	public void setCs_song(Integer cs_song) {
		this.cs_song = cs_song;
	}
	public String getCRG() {
		return CRG;
	}
	public void setCRG(String cRG) {
		CRG = cRG;
	}
	public String getSong() {
		return Song;
	}
	public void setSong(String song) {
		Song = song;
	}
	public String getArtists() {
		return Artists;
	}
	public void setArtists(String artists) {
		Artists = artists;
	}
	public String getLabel() {
		return Label;
	}
	public void setLabel(String label) {
		Label = label;
	}
	public Long getSpotify_streams_total() {
		return spotify_streams_total;
	}
	public void setSpotify_streams_total(Long spotify_streams_total) {
		this.spotify_streams_total = spotify_streams_total;
	}
	public Long getTiktok_views_total() {
		return tiktok_views_total;
	}
	public void setTiktok_views_total(Long tiktok_views_total) {
		this.tiktok_views_total = tiktok_views_total;
	}
	public Long getYoutube_video_views_total() {
		return youtube_video_views_total;
	}
	public void setYoutube_video_views_total(Long youtube_video_views_total) {
		this.youtube_video_views_total = youtube_video_views_total;
	}
	public Long getShazams_total() {
		return shazams_total;
	}
	public void setShazams_total(Long shazams_total) {
		this.shazams_total = shazams_total;
	}
	public Long getSoundcloud_stream_total() {
		return soundcloud_stream_total;
	}
	public void setSoundcloud_stream_total(Long soundcloud_stream_total) {
		this.soundcloud_stream_total = soundcloud_stream_total;
	}
	public Long getPan_streams() {
		return pan_streams;
	}
	public void setPan_streams(Long pan_streams) {
		this.pan_streams = pan_streams;
	}
	public Float getScore() {
		return score;
	}
	public void setScore(Float score) {
		this.score = score;
	}
	public Integer getRk_total() {
		return rk_total;
	}
	public void setRk_total(Integer rk_total) {
		this.rk_total = rk_total;
	}
	public ChartDigital(Integer cs_song, String cRG, String song, String artists, String label,
			Long spotify_streams_total, Long tiktok_views_total, Long youtube_video_views_total, Long shazams_total,
			Long soundcloud_stream_total, Long pan_streams, Float score, Integer rk_total) {
		super();
		this.cs_song = cs_song;
		CRG = cRG;
		Song = song;
		Artists = artists;
		Label = label;
		this.spotify_streams_total = spotify_streams_total;
		this.tiktok_views_total = tiktok_views_total;
		this.youtube_video_views_total = youtube_video_views_total;
		this.shazams_total = shazams_total;
		this.soundcloud_stream_total = soundcloud_stream_total;
		this.pan_streams = pan_streams;
		this.score = score;
		this.rk_total = rk_total;
	}
	
	public ChartDigital(String cs_song, String cRG, String song, String artists, String label,
			String spotify_streams_total, String tiktok_views_total, String youtube_video_views_total,String youtube_short_views_total, String shazams_total,
			String soundcloud_stream_total, String pan_streams,String audience_total,String spins_total, String score, String rk_total,String tw_spins, String tw_aud,
			String rk) {
		super();
		this.cs_song = Integer.parseInt(cs_song);
		CRG = cRG;
		Song = song;
		Artists = artists;
		Label = label;
		this.spotify_streams_total = Long.parseLong(spotify_streams_total);
		this.tiktok_views_total = Long.parseLong(tiktok_views_total);
		this.youtube_video_views_total = Long.parseLong(youtube_video_views_total);
		this.shazams_total = Long.parseLong(shazams_total);
		this.soundcloud_stream_total = Long.parseLong(soundcloud_stream_total);
		this.pan_streams = Long.parseLong(pan_streams);
		this.score = Float.parseFloat(score != " " ? score: "0.0");
		this.rk_total = Integer.parseInt(rk_total);
		this.tw_aud = Integer.parseInt(tw_aud);
		this.tw_spins = Integer.parseInt(tw_spins);
		this.spins_total = Integer.parseInt(spins_total);
		this.youtube_short_views_total = Long.parseLong(youtube_short_views_total);
		this.audience_total = Long.parseLong(audience_total);
		this.rk = Integer.parseInt(rk);
	}
	
	
	
	
	
	
	

}
