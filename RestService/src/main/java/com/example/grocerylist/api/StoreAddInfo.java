package com.example.grocerylist.api;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StoreAddInfo {
	private String name;
	
	@NotNull
	@JsonProperty
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
