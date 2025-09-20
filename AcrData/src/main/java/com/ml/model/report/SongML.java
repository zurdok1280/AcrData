package com.ml.model.report;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.ParameterMode;

@NamedStoredProcedureQueries({
	  @NamedStoredProcedureQuery(
	    name = "findBySong", 
	    procedureName = "GET_SONG_EXTERNAL_DATA", 
	    resultClasses = { SongML.class },
	    parameters= {
	    		@StoredProcedureParameter(
	    	            mode=ParameterMode.IN,
	    	            name="id",
	    	            type = Long.class
	    	        )
	    }) 
	})

@Entity
public class SongML {
	@Id
	private int cs_song;
	private String song;
	private String artist;
	private String label;
	private String avatar;
	
	
	public int getCs_song() {
		return cs_song;
	}
	public void setCs_song(int cs_song) {
		this.cs_song = cs_song;
	}
	public String getSong() {
		return song;
	}
	public void setSong(String song) {
		this.song = song;
	}
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
	
	
}
