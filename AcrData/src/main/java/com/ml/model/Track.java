package com.ml.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "acr_track")
public class Track {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
	private long id;
	@Column
	private String acrid;
	@Column
	private String title;
	@Column(columnDefinition = "DATE", updatable = false, nullable = true,name="release_date")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate release_date;
	
	@ManyToOne
	@JoinColumn(name = "label_id")
	private Label label;
	@Column
	private int duration_ms;
	@CreationTimestamp
	@Column(columnDefinition = "DATETIME", updatable = false, nullable = false,name="record_timestamp")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss[.SSS][.SS][.S]")
	private LocalDateTime record_timestamp;
	

	@OneToMany(fetch=FetchType.LAZY, mappedBy = "track")
	@JsonBackReference
	private Set<AcrData> acrdata;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy = "track")
	private Set<Isrc> isrc;
	@JsonIgnore
	@OneToMany(fetch=FetchType.LAZY, mappedBy = "track")
	private Set<Upc> upc;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy = "track")
	private Set<Track_Artist> track_artist;
	
	@JsonIgnore
	@OneToMany(fetch=FetchType.LAZY, mappedBy = "track")
	private Set<Track_Genre> track_genre;
	@JsonIgnore
	@OneToMany(fetch=FetchType.LAZY, mappedBy = "track")
	private Set<ExternalMetadata> external_metadata;
	
	
	
	
	
	public Set<Track_Genre> getTrack_genre() {
		return track_genre;
	}
	public void setTrack_genre(Set<Track_Genre> track_genre) {
		this.track_genre = track_genre;
	}
	public Set<ExternalMetadata> getExternal_metadata() {
		return external_metadata;
	}
	public void setExternal_metadata(Set<ExternalMetadata> external_metadata) {
		this.external_metadata = external_metadata;
	}
	public Set<Upc> getUpc() {
		return upc;
	}
	public void setUpc(Set<Upc> upc) {
		this.upc = upc;
	}
	public Set<Track_Artist> getTrack_artist() {
		return track_artist;
	}
	public void setTrack_artist(Set<Track_Artist> track_artist) {
		this.track_artist = track_artist;
	}
	public Set<Isrc> getIsrc() {
		return isrc;
	}
	public void setIsrc(Set<Isrc> isrc) {
		this.isrc = isrc;
	}

	
	public Label getLabel() {
		return label;
	}
	public void setLabel(Label label) {
		this.label = label;
	}
	public Set<AcrData> getAcrdata() {
		return acrdata;
	}
	public void setAcrdata(Set<AcrData> acrdata) {
		this.acrdata = acrdata;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getAcrid() {
		return acrid;
	}
	public void setAcrid(String acrid) {
		this.acrid = acrid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public LocalDate getRelease_date() {
		return release_date;
	}
	public void setRelease_date(LocalDate release_date) {
		this.release_date = release_date;
	}
	public int getDuration_ms() {
		return duration_ms;
	}
	public void setDuration_ms(int duration_ms) {
		this.duration_ms = duration_ms;
	}
	public LocalDateTime getRecord_timestamp() {
		return record_timestamp;
	}
	public void setRecord_timestamp(LocalDateTime record_timestamp) {
		this.record_timestamp = record_timestamp;
	}
	
	
	
	
	

}
