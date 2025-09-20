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
@Table(name="acr_genre")
public class Genre {
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
	private int id;
	@Column
	private String name;
	
	@JsonIgnore
	@OneToMany(fetch=FetchType.LAZY, mappedBy = "genre")
	private Set<Track_Genre> genre_track;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Track_Genre> getGenre_track() {
		return genre_track;
	}

	public void setGenre_track(Set<Track_Genre> genre_track) {
		this.genre_track = genre_track;
	}

	public Genre() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Genre(int id, String name, Set<Track_Genre> genre_track) {
		super();
		this.id = id;
		this.name = name;
		this.genre_track = genre_track;
	}
	
	
	
	

}
