package com.example.grocerylist.api.hibernate;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "items")

public class Item {
	private Long id;
	private String name;
	private List<Store> stores;

	public Item(Long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public Item(Long id, String name, List<Store> stores) {
		this.id = id;
		this.name = name;
		this.stores = stores;
	}

	public Item() {
		
	}
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", nullable = true)
	public Long getId() {
		return id;
	}
	
	@Column(name = "name", nullable = false)
	@NotNull
	public String getName() {
		return name;
	}
	
	@ManyToMany(mappedBy = "items")
	public List<Store> getStores() {
		return stores;
	}

	public void setStores(List<Store> stores) {
		this.stores = stores;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", stores=" + stores + "]";
	}
	
	
	
}
