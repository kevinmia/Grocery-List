package com.example.grocerylist.api.hibernate.dao;

import java.util.List;

import org.hibernate.SessionFactory;

import com.example.grocerylist.api.hibernate.GroceryList;

import io.dropwizard.hibernate.AbstractDAO;

public class GroceryListDAO extends AbstractDAO<GroceryList> {
	public GroceryListDAO(SessionFactory factory) {
		super(factory);
	}

	public GroceryList save(GroceryList groceryList) {
		return persist(groceryList);
	}

	
	public GroceryList findById(Long id) {
		return get(id);
	}
	
	public void delete(final GroceryList list){
		currentSession().delete(list);
	}
	
	public List<GroceryList> findAll() {
		List<GroceryList> list = list(criteria());
		System.out.println(list.toString());
		return list;
	}
}
