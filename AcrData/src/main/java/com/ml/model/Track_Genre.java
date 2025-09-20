package com.ml.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name="acr_track_genre")
public class Track_Genre {
	
	@EmbeddedId
	private TrackGenreId id;
	
	@ManyToOne
	@MapsId("fk_track")
	@JoinColumn(name="fk_track")
	private Track track;
	
	@ManyToOne
	@MapsId("fk_genre")
	@JoinColumn(name="fk_genre")
	private Genre genre;

	public TrackGenreId getId() {
		return id;
	}

	public void setId(TrackGenreId id) {
		this.id = id;
	}

	public Track getTrack() {
		return track;
	}

	public void setTrack(Track track) {
		this.track = track;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public Track_Genre() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Track_Genre(TrackGenreId id, Track track, Genre genre) {
		super();
		this.id = id;
		this.track = track;
		this.genre = genre;
	}
	
	
	
	

}
