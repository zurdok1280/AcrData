package com.ml.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class TrackAlbumId implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Column
	private long fk_track;
	@Column
	private long fk_album;
	public long getFk_track() {
		return fk_track;
	}
	public void setFk_track(long fk_track) {
		this.fk_track = fk_track;
	}
	public long getFk_album() {
		return fk_album;
	}
	public void setFk_album(long fk_album) {
		this.fk_album = fk_album;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		return Objects.hash(fk_album, fk_track);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TrackAlbumId other = (TrackAlbumId) obj;
		return fk_album == other.fk_album && fk_track == other.fk_track;
	}
	public TrackAlbumId() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TrackAlbumId(long fk_track, long fk_album) {
		super();
		this.fk_track = fk_track;
		this.fk_album = fk_album;
	}
	
	
	

}
