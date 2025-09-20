package com.ml.model;

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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;



@Entity
@Table(name = "acr_data")
public class AcrData {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
	private long id;
	@Column (columnDefinition = "DATETIME", updatable = false, nullable = false,name="timestamp_utc")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss[.SSS][.SS][.S]")
	private LocalDateTime timestamputc;
	@Column
	private int played_duration;
	@Column
	private String acrid;
	@Column (columnDefinition = "DATETIME", updatable = false, nullable = false,name="record_timestamp")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss[.SSS][.SS][.S]")
	private LocalDateTime record_timestamp;
	@Column
	private int play_offset_ms;
	@Column
	private int score;
	@Column
	private int duration_ms;
	@Column (columnDefinition = "DATETIME", updatable = false, nullable = false,name="localtimestamp")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss[.SSS][.SS][.S]")
	private LocalDateTime localtimestamp;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "channel_id")
	@JsonBackReference
	private Channel channel;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "track_id")
	@JsonBackReference
	private Track track;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy = "acrdata")
	private Set<AcrDataLog> acrlog;
	
	
	
	
	
	
	public LocalDateTime getLocaltimestamp() {
		return localtimestamp;
	}
	public void setLocaltimestamp(LocalDateTime localtimestamp) {
		this.localtimestamp = localtimestamp;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public LocalDateTime getTimestamputc() {
		return timestamputc;
	}
	public void setTimestamputc(LocalDateTime timestamp_utc) {
		this.timestamputc = timestamp_utc;
	}
	public int getPlayed_duration() {
		return played_duration;
	}
	public void setPlayed_duration(int played_duration) {
		this.played_duration = played_duration;
	}
	public String getAcrid() {
		return acrid;
	}
	public void setAcrid(String acrid) {
		this.acrid = acrid;
	}
	public int getPlay_offset_ms() {
		return play_offset_ms;
	}
	public void setPlay_offset_ms(int play_offset_ms) {
		this.play_offset_ms = play_offset_ms;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
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
	public Channel getChannel() {
		return channel;
	}
	public void setChannel(Channel channel) {
		this.channel = channel;
	}
	public Track getTrack() {
		return track;
	}
	public void setTrack(Track track) {
		this.track = track;
	}

	
	
	
	
	
	

}
