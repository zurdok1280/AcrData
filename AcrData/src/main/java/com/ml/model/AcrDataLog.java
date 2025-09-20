package com.ml.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "acr_data_log")
public class AcrDataLog {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
	private long id;
	@Column
	private String metadata;
	@Column (columnDefinition = "DATETIME", updatable = false, nullable = false,name="timestamp_utc")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss[.SSS][.SS][.S]")
	private LocalDateTime timestamputc;
	@CreationTimestamp
	@Column (columnDefinition = "DATETIME", updatable = false, nullable = false,name="date_created")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss[.SSS][.SS][.S]")
	private LocalDateTime date_created;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_acr_data")
	private AcrData acrdata;

	
	
	
	
	

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public LocalDateTime getTimestamputc() {
		return timestamputc;
	}
	public void setTimestamputc(LocalDateTime timestamp_utc) {
		this.timestamputc = timestamp_utc;
	}
	public LocalDateTime getDate_created() {
		return date_created;
	}
	public void setDate_created(LocalDateTime date_created) {
		this.date_created = date_created;
	}
	public AcrData getAcrdata() {
		return acrdata;
	}
	public void setAcrdata(AcrData acrdata) {
		this.acrdata = acrdata;
	}
	public AcrDataLog(String metadata, LocalDateTime timestamp_utc,AcrData acrdata) {
		super();
		this.metadata = metadata;
		this.timestamputc = timestamp_utc;
		this.acrdata = acrdata;
	}
	public AcrDataLog() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	


}
