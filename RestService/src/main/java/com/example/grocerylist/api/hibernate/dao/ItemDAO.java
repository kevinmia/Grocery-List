package com.example.grocerylist.api.hibernate.dao;

import java.util.List;

import org.hibernate.SessionFactory;

import com.example.grocerylist.api.hibernate.Item;


import io.dropwizard.hibernate.AbstractDAO;

public class ItemDAO extends AbstractDAO<Item>{
	public ItemDAO(SessionFactory factory) {
		super(factory);
	}
	
	public Item findById(Long id) {
		return get(id);
	}
	
	public Item save(Item item) {
		return persist(item);
	}
	
	public void delete(final Item item){
		currentSession().delete(item);
	}
	
	public List<Item> findAll() {
		List<Item> item = list(criteria());
		return item;
	}
}
