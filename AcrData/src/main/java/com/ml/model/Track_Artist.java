package com.ml.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="acr_track_artist")
public class Track_Artist {
	
	@EmbeddedId
	private TrackArtistId id;
	@JsonIgnore
	@ManyToOne
	@MapsId("fk_track")
	@JoinColumn(name="fk_track")
	private Track track;
	
	@ManyToOne
	@MapsId("fk_artist")
	@JoinColumn(name="fk_artist")
	private Artist artist;
	@Column
	private int no;
	
	
	

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public TrackArtistId getId() {
		return id;
	}

	public void setId(TrackArtistId id) {
		this.id = id;
	}

	public Track getTrack() {
		return track;
	}

	public void setTrack(Track track) {
		this.track = track;
	}

	public Artist getArtist() {
		return artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}
	
	
	
	

}
