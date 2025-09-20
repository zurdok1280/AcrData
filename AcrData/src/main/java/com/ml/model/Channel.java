package com.ml.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "channel")
public class Channel {
	@Column
	private Long uid;
	@Column
	private Long project_id;
	@Id
	@Column(name = "id", nullable = false)
	private Long id;
	@Column
	private String name;
	@Column
	private String sub_title;
	@Column
	private String language;
	@Column
	private String city;
	@Column
	private String province;
	@Column
	private String country;
	@Column
	private String continent;
	@Column
	private String website;
	@Column
	private String twitter;
	@Column
	private String mytuner;
	@Column
	private Integer status_code;
	@CreationTimestamp
	@Column (columnDefinition = "DATETIME", updatable = false, nullable = false,name="monitor_at")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss[.SSS][.SS][.S]")
	private LocalDateTime monitor_at;
	@Column
	private boolean timemap;
	@Column
	private Integer timemap_lifecycle;
	@Column
	private Integer utc;
	@CreationTimestamp
	@Column (columnDefinition = "DATETIME", updatable = false, nullable = false,name="add_at")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss[.SSS][.SS][.S]")
	private LocalDateTime add_at;
	
	@JsonIgnore
	@OneToMany(fetch=FetchType.LAZY, mappedBy = "channel")
	private Set<AcrData> acrdata;
	

	public Integer getUtc() {
		return utc;
	}
	public void setUtc(Integer utc) {
		this.utc = utc;
	}
	public Integer getTimemap_lifecycle() {
		return timemap_lifecycle;
	}
	public Set<AcrData> getAcrdata() {
		return acrdata;
	}
	public void setAcrdata(Set<AcrData> acrdata) {
		this.acrdata = acrdata;
	}
	public Long getUid() {
		return uid;
	}
	public void setUid(Long uid) {
		this.uid = uid;
	}
	public long getProject_id() {
		return project_id;
	}
	public void setProject_id(Long project_id) {
		this.project_id = project_id;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSub_title() {
		return sub_title;
	}
	public void setSub_title(String sub_title) {
		this.sub_title = sub_title;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getContinent() {
		return continent;
	}
	public void setContinent(String continent) {
		this.continent = continent;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getTwitter() {
		return twitter;
	}
	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}
	public String getMytuner() {
		return mytuner;
	}
	public void setMytuner(String mytuner) {
		this.mytuner = mytuner;
	}
	public Integer getStatus_code() {
		return status_code;
	}
	public void setStatus_code(Integer status_code) {
		this.status_code = status_code;
	}
	public LocalDateTime getMonitor_at() {
		return monitor_at;
	}
	public void setMonitor_at(LocalDateTime monitor_at) {
		this.monitor_at = monitor_at;
	}
	public boolean isTimemap() {
		return timemap;
	}
	public void setTimemap(boolean timemap) {
		this.timemap = timemap;
	}
	public Integer isTimemap_lifecycle() {
		return timemap_lifecycle;
	}
	public void setTimemap_lifecycle(Integer timemap_lifecycle) {
		this.timemap_lifecycle = timemap_lifecycle;
	}
	public LocalDateTime getAdd_at() {
		return add_at;
	}
	public void setAdd_at(LocalDateTime add_at) {
		this.add_at = add_at;
	}
	public Channel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Channel(Long uid, Long project_id, Long id, String name, String sub_title, String language, String city,
			String province, String country, String continent, String website, String twitter, String mytuner,
			int status_code, LocalDateTime monitor_at, boolean timemap, Integer timemap_lifecycle, LocalDateTime add_at, Integer utc) {
		super();
		this.uid = uid;
		this.project_id = project_id;
		this.id = id;
		this.name = name;
		this.sub_title = sub_title;
		this.language = language;
		this.city = city;
		this.province = province;
		this.country = country;
		this.continent = continent;
		this.website = website;
		this.twitter = twitter;
		this.mytuner = mytuner;
		this.status_code = status_code;
		this.monitor_at = monitor_at;
		this.timemap = timemap;
		this.timemap_lifecycle = timemap_lifecycle;
		this.add_at = add_at;
		this.utc = utc;
	}
	
	public Channel(Long uid, Long project_id, Long id, String name, String sub_title, String language, String city,
			String province, String country, String continent, String website, String twitter, String mytuner,
			int status_code, String monitor_at, boolean timemap, Integer timemap_lifecycle, String add_at) {
		super();
		this.uid = uid;
		this.project_id = project_id;
		this.id = id;
		this.name = name;
		this.sub_title = sub_title;
		this.language = language;
		this.city = city;
		this.province = province;
		this.country = country;
		this.continent = continent;
		this.website = website;
		this.twitter = twitter;
		this.mytuner = mytuner;
		this.status_code = status_code;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime dateTime = LocalDateTime.parse(monitor_at, formatter);
		this.monitor_at = dateTime;
		this.timemap = timemap;
		this.timemap_lifecycle = timemap_lifecycle;
		dateTime = LocalDateTime.parse(add_at, formatter);
		this.add_at = dateTime;
		this.utc = 0;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Channel [uid=");
		builder.append(uid);
		builder.append(", project_id=");
		builder.append(project_id);
		builder.append(", id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", sub_title=");
		builder.append(sub_title);
		builder.append(", language=");
		builder.append(language);
		builder.append(", city=");
		builder.append(city);
		builder.append(", province=");
		builder.append(province);
		builder.append(", country=");
		builder.append(country);
		builder.append(", continent=");
		builder.append(continent);
		builder.append(", website=");
		builder.append(website);
		builder.append(", twitter=");
		builder.append(twitter);
		builder.append(", mytuner=");
		builder.append(mytuner);
		builder.append(", status_code=");
		builder.append(status_code);
		builder.append(", monitor_at=");
		builder.append(monitor_at);
		builder.append(", timemap=");
		builder.append(timemap);
		builder.append(", timemap_lifecycle=");
		builder.append(timemap_lifecycle);
		builder.append(", add_at=");
		builder.append(add_at);
		builder.append(", acrdata=");
		builder.append(acrdata);
		builder.append("]");
		return builder.toString();
	}

	
	
	
	

}
