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
@Table(name = "acr_artist")
public class Artist {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
	private long id;
	@Column
	private String name;
	
	@JsonIgnore
	@OneToMany(fetch=FetchType.LAZY, mappedBy = "artist")
	private Set<Track_Artist> artist_track;
	
	@JsonIgnore
	@OneToMany(fetch=FetchType.LAZY, mappedBy = "album")
	private Set<Track_Album> album;
	

	
	
	
	
	
	public Set<Track_Artist> getArtist_track() {
		return artist_track;
	}
	public void setArtist_track(Set<Track_Artist> artist_track) {
		this.artist_track = artist_track;
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
