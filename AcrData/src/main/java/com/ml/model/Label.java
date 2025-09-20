package com.ml.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "acr_label")
public class Label {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
	private long id;
	@Column
	private String name;
	
	@JsonIgnore
	@OneToMany(fetch=FetchType.LAZY, mappedBy = "label")
	private Set<Track> track_data;
	
	
	
	public Set<Track> getTrack_data() {
		return track_data;
	}
	public void setTrack_data(Set<Track> track_data) {
		this.track_data = track_data;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	

}
