package com.example.grocerylist.api.hibernate.dao;

import java.util.List;

import com.example.grocerylist.api.hibernate.Item;
import com.example.grocerylist.api.hibernate.Store;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import io.dropwizard.hibernate.AbstractDAO;

public class StoreDAO extends AbstractDAO<Store> {
	public StoreDAO(SessionFactory factory) {
		super(factory);
	}
	
	public Store save(final Store store) {
		return persist(store);
	}
	
	public Store findById(final Long id) {
		return get(id);
	}
	
	public void delete(final Store store) {
		currentSession().delete(store);
	}
	
	public List<Store> findByItem(Item item) {
	    return list(criteria().createAlias("item", "Items").add(Restrictions.eq("item.id", item.getId())));
	}
	
	public List<Store> findStoresForItem(long itemId) {
	    return list(criteria().createAlias("items", "item").add(Restrictions.eq("item.id", itemId)));
	}
	
	public List<Store> findAll() {
		List<Store> store = list(criteria());
		return store;
	}
}
