package com.example.grocerylist.resources;

import java.util.List;

import io.dropwizard.hibernate.UnitOfWork;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.example.grocerylist.api.PurchaseAddInfo;
import com.example.grocerylist.api.hibernate.Purchase;
import com.example.grocerylist.api.hibernate.dao.PurchaseDAO;

@Path("/grocery-list/{listId}/purchases")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class PurchaseResource {
	private final PurchaseDAO purchaseDao;

	public PurchaseResource(PurchaseDAO purchaseDao) {
		this.purchaseDao = purchaseDao;
	}
	
	@POST
	@UnitOfWork
	public Purchase add(@Valid PurchaseAddInfo purchase, @PathParam("listId") long listId) {
		return purchaseDao.save(new Purchase(null, purchase.getItemId(), listId, purchase.getQuantity(), purchase.getMeasurement(), false));
	}
	
	@GET
	@UnitOfWork
	public List<Purchase> getAll() {
		return purchaseDao.findAll();
	}
}
