package com.ml.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "acr_external_metadata")
public class ExternalMetadata {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
	private long id;
	@Column
	private int fk_platform;
	@Column
	private String external_code;
	
	@ManyToOne
	@JoinColumn(name = "fk_track")
	private Track track;


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public int getFk_platform() {
		return fk_platform;
	}


	public void setFk_platform(int fk_platform) {
		this.fk_platform = fk_platform;
	}


	public Track getTrack() {
		return track;
	}


	public void setTrack(Track track) {
		this.track = track;
	}


	public ExternalMetadata() {
		super();
		// TODO Auto-generated constructor stub
	}


	public String getExternal_code() {
		return external_code;
	}


	public void setExternal_code(String external_code) {
		this.external_code = external_code;
	}


	public ExternalMetadata(int fk_platform, String external_code, Track track) {
		super();
		this.fk_platform = fk_platform;
		this.external_code = external_code;
		this.track = track;
	}


	
	
	
	

}
