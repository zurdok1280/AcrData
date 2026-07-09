package com.ml.model.report;

public class SongTiktok {
	private Integer rk;
	private String video_id;
	private Long views_total;
	private Long likes_total;
	private Long comments_total;
	private Long shares_total;
	private Long tiktok_user_followers;
	private String username;
	private String user_handle;
	public Integer getRk() {
		return rk;
	}
	public void setRk(Integer rk) {
		this.rk = rk;
	}
	public String getVideo_id() {
		return video_id;
	}
	public void setVideo_id(String video_id) {
		this.video_id = video_id;
	}
	public Long getViews_total() {
		return views_total;
	}
	public void setViews_total(Long views_total) {
		this.views_total = views_total;
	}
	public Long getLikes_total() {
		return likes_total;
	}
	public void setLikes_total(Long likes_total) {
		this.likes_total = likes_total;
	}
	public Long getComments_total() {
		return comments_total;
	}
	public void setComments_total(Long comments_total) {
		this.comments_total = comments_total;
	}
	public Long getShares_total() {
		return shares_total;
	}
	public void setShares_total(Long shares_total) {
		this.shares_total = shares_total;
	}
	public Long getTiktok_user_followers() {
		return tiktok_user_followers;
	}
	public void setTiktok_user_followers(Long tiktok_user_followers) {
		this.tiktok_user_followers = tiktok_user_followers;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUser_handle() {
		return user_handle;
	}
	public void setUser_handle(String user_handle) {
		this.user_handle = user_handle;
	}
	public SongTiktok(Integer rk, String video_id, Long views_total, Long likes_total, Long comments_total,
			Long shares_total, Long tiktok_user_followers, String username, String user_handle) {
		super();
		this.rk = rk;
		this.video_id = video_id;
		this.views_total = views_total;
		this.likes_total = likes_total;
		this.comments_total = comments_total;
		this.shares_total = shares_total;
		this.tiktok_user_followers = tiktok_user_followers;
		this.username = username;
		this.user_handle = user_handle;
	}
	public SongTiktok() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public SongTiktok(String rk, String video_id, String views_total, String likes_total, String comments_total,
			String shares_total, String tiktok_user_followers, String username, String user_handle) {
		super();
		this.rk = Integer.parseInt(rk);
		this.video_id = video_id;
		this.views_total = Long.parseLong(views_total);
		this.likes_total = Long.parseLong(likes_total);
		this.comments_total = Long.parseLong(comments_total);
		this.shares_total = Long.parseLong(shares_total);
		this.tiktok_user_followers = Long.parseLong(tiktok_user_followers);
		this.username = username;
		this.user_handle = user_handle;
	}
	
	
	
	
}
