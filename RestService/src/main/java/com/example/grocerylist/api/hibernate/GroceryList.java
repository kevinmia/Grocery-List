package com.example.grocerylist.api.hibernate;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "list")

public class GroceryList {
	private Long id;
	private String name;
	private List<Purchase> purchases;
	private Boolean done;
	
	public GroceryList(Long id, String name, Boolean done) {
		this.id = id;
		this.name = name;
		this.done = done;
	}
	
	public GroceryList(Long id, String name, Boolean done, List<Purchase> purchases) {
		this.id = id;
		this.name = name;
		this.done = done;
		this.purchases = purchases;
	}
	
	public GroceryList() {
		
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
	
	@Column(name = "name", nullable = false)
	@NotNull
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany
	@JoinColumn(name="list_id")
	public List<Purchase> getPurchases() {
		return purchases;
	}

	public void setPurchases(List<Purchase> purchases) {
		this.purchases = purchases;
	}
	
	@Column(name = "done", nullable = false)
	@NotNull
	public Boolean getDone() {
		return done;
	}

	public void setDone(Boolean done) {
		this.done = done;
	}

	@Override
	public String toString() {
		return "GroceryList [id=" + id + ", name=" + name + ", purchases=" + purchases
				+ ", done=" + done + "]";
	}
	
	
}
