package com.ml.model.report;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;

@NamedStoredProcedureQueries({
	  @NamedStoredProcedureQuery(
	    name = "findByChart", 
	    procedureName = "GET_CHART_DATA", 
	    resultClasses = { Chart.class }
	    ) 
	})

@Entity
public class Chart {
	@Id
	private int id;
	private int tw_rk;
	private int lw_rk;
	private String title;
	private String artist;
	private String label;
	private int tw_spins;
	private int lw_spins;
	private int dif;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTw_rk() {
		return tw_rk;
	}
	public void setTw_rk(int tw_rk) {
		this.tw_rk = tw_rk;
	}
	public int getLw_rk() {
		return lw_rk;
	}
	public void setLw_rk(int lw_rk) {
		this.lw_rk = lw_rk;
	}
	public int getTw_spins() {
		return tw_spins;
	}
	public void setTw_spins(int tw_spins) {
		this.tw_spins = tw_spins;
	}
	public int getLw_spins() {
		return lw_spins;
	}
	public void setLw_spins(int lw_spins) {
		this.lw_spins = lw_spins;
	}
	public int getDif() {
		return dif;
	}
	public void setDif(int dif) {
		this.dif = dif;
	}
	
	

	
	
}
