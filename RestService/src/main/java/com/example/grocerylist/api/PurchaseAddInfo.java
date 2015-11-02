package com.example.grocerylist.api;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PurchaseAddInfo {
	private Long itemId;
	private String name;
	private int quantity;
	private String measurement;
	private Boolean done;
	
	@NotNull
	@JsonProperty
	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	@NotNull
	@JsonProperty
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@NotNull
	@JsonProperty
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	@NotNull
	@JsonProperty
	public String getMeasurement() {
		return measurement;
	}
	
	public void setMeasurement(String measurement) {
		this.measurement = measurement;
	}
	
	@NotNull
	@JsonProperty
	public Boolean getDone() {
		return done;
	}
	
	public void setDone(Boolean done) {
		this.done = done;
	}
}
