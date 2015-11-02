package com.example.grocerylist.api.hibernate.dao;

import java.util.List;

import org.hibernate.SessionFactory;

import com.example.grocerylist.api.hibernate.Purchase;

import io.dropwizard.hibernate.AbstractDAO;

public class PurchaseDAO extends AbstractDAO<Purchase> {
	public PurchaseDAO(SessionFactory factory) {
		super(factory);
	}
	
	public Purchase save(Purchase purchase) {
		return persist(purchase);
	}
	
	public Purchase findById(Long id) {
		return get(id);
	}
	
	public void delete(final Purchase purchase) {
		currentSession().delete(purchase);
	}
	
	public List<Purchase> findAll() {
		List<Purchase> purchase = list(criteria());
		System.out.println(purchase.toString());
		return purchase;
	}
}
