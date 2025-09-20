package com.ml.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name="acr_track_album")
public class Track_Album {
	
	@EmbeddedId
	private TrackAlbumId id;
	
	@ManyToOne
	@MapsId("fk_track")
	@JoinColumn(name="fk_track")
	private Track track;
	
	@ManyToOne
	@MapsId("fk_album")
	@JoinColumn(name="fk_album")
	private Album album;

	public TrackAlbumId getId() {
		return id;
	}

	public void setId(TrackAlbumId id) {
		this.id = id;
	}

	public Track getTrack() {
		return track;
	}

	public void setTrack(Track track) {
		this.track = track;
	}

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}
	
	
	
	
	

}
