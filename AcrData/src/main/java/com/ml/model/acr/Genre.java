package com.ml.model.acr;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Genre {
	
	@JsonProperty("name") 
    public String getName() { 
		 return this.name; } 
    public void setName(String name) { 
		 this.name = name; } 
    String name;
    @JsonProperty("id") 
    public int getId() { 
		 return this.id; } 
    public void setId(int id) { 
		 this.id = id; } 
    int id;
}
