package com.ml.model;


import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "acr_album")
public class Album {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
	private long id;
	@Column
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "fk_artist")
	private Artist artist;
	
	@JsonIgnore
	@OneToMany(fetch=FetchType.LAZY, mappedBy = "album")
	private Set<Track_Album> album_track;
	

	
	
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
	
	public Set<Track_Album> getAlbum_track() {
		return album_track;
	}
	public void setAlbum_track(Set<Track_Album> album_track) {
		this.album_track = album_track;
	}
	public Artist getArtist() {
		return artist;
	}
	public void setArtist(Artist artist) {
		this.artist = artist;
	}
	
	

}
