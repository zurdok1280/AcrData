package com.ml.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class TrackGenreId implements Serializable {
	private static final long serialVersionUID = 1L;
	@Column
	private long fk_track;
	@Column
	private int fk_genre;
	
	public long getFk_track() {
		return fk_track;
	}
	public void setFk_track(long fk_track) {
		this.fk_track = fk_track;
	}
	public int getFk_genre() {
		return fk_genre;
	}
	public void setFk_genre(int fk_genre) {
		this.fk_genre = fk_genre;
	}
	public TrackGenreId(long fk_track, int fk_genre) {
		super();
		this.fk_track = fk_track;
		this.fk_genre = fk_genre;
	}
	public TrackGenreId() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public int hashCode() {
		return Objects.hash(fk_genre, fk_track);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TrackGenreId other = (TrackGenreId) obj;
		return fk_genre == other.fk_genre && fk_track == other.fk_track;
	}
	
	
	

}
