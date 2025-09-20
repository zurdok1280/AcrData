package com.ml.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
@Embeddable
public class TrackArtistId implements Serializable {
	private static final long serialVersionUID = 1L;
	@Column
	private long fk_track;
	@Column
	private long fk_artist;
	public long getFk_track() {
		return fk_track;
	}
	public void setFk_track(long fk_track) {
		this.fk_track = fk_track;
	}
	public long getFk_artist() {
		return fk_artist;
	}
	public void setFk_artist(long fk_artist) {
		this.fk_artist = fk_artist;
	}
	public TrackArtistId(long fk_track, long fk_artist) {
		super();
		this.fk_track = fk_track;
		this.fk_artist = fk_artist;
	}
	public TrackArtistId() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public int hashCode() {
		return Objects.hash(fk_artist, fk_track);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TrackArtistId other = (TrackArtistId) obj;
		return fk_artist == other.fk_artist && fk_track == other.fk_track;
	}
	
	
	
	
}
