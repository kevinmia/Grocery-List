package com.example.grocerylist.api.hibernate;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "stores")

public class Store {
	private Long id;
	private String name;
	private List<Item> items;
	
	public Store(Long id, String name, List<Item> items) {
		this.id = id;
		this.name = name;
		this.items = items;
	}
	
	public Store() {
		
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
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "stores_items", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "item_id"))
	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "Store [id=" + id + ", name=" + name + ", items=" + items + "]";
	}
	
	
}
