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
@Table(name = "acr_upc")
public class Upc {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
	private long id;
	@Column
	private String code;
	@ManyToOne
	@JoinColumn(name = "fk_track")
	private Track track;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Track getTrack() {
		return track;
	}
	public void setTrack(Track track) {
		this.track = track;
	}
	public Upc(String code, Track track) {
		super();
		this.code = code;
		this.track = track;
	}
	public Upc() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	

}
