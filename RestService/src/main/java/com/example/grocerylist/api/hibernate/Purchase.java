package com.example.grocerylist.api.hibernate;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "purchases")

public class Purchase {
	private Long id;
	private Long itemId;
	private Long listId;
	private Integer quantity;
	private String measurement;
	private Boolean done;
	
	public Purchase(Long id, Long itemId, Long listId, Integer quantity,
			String measurement, Boolean done) {
		this.id = id;
		this.itemId = itemId;
		this.listId = listId;
		this.quantity = quantity;
		this.measurement = measurement;
		this.done = done;
	}
	
	public Purchase() {
		
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", nullable = true)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "item_id", nullable = false)
	@NotNull
	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	@Column(name = "list_id", nullable = false)
	@NotNull
	public Long getListId() {
		return listId;
	}

	public void setListId(Long listId) {
		this.listId = listId;
	}
 
	@Column(name = "quantity", nullable = false)
	@NotNull
	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Column(name = "measurement", nullable = false)
	@NotNull
	public String getMeasurement() {
		return measurement;
	}

	public void setMeasurement(String measurement) {
		this.measurement = measurement;
	}

	@Column(name = "done", nullable = false)
	@NotNull
	public Boolean getDone() {
		return done;
	}

	public void setDone(Boolean done) {
		this.done = done;
	}
	
	
}
