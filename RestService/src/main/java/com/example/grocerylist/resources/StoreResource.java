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

import com.codahale.metrics.annotation.Timed;
import com.example.grocerylist.api.ItemAddInfo;
import com.example.grocerylist.api.StoreAddInfo;
import com.example.grocerylist.api.hibernate.Item;
import com.example.grocerylist.api.hibernate.Store;
import com.example.grocerylist.api.hibernate.dao.ItemDAO;
import com.example.grocerylist.api.hibernate.dao.StoreDAO;
import com.sun.jersey.api.NotFoundException;

@Path("/grocery-list/stores")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class StoreResource {
	private final StoreDAO storeDao;
	private final ItemDAO itemDao;
	
	public StoreResource(StoreDAO storeDao, ItemDAO itemDao) {
		this.storeDao = storeDao;
		this.itemDao = itemDao;
	}

    @POST
    @UnitOfWork
    public Store add(@Valid StoreAddInfo store) {
        return storeDao.save(new Store(null, store.getName(), null));
    }
    
    @GET
    @Path("/{storeId}")
    @UnitOfWork
    public Store get(@PathParam("storeId") long storeId) {
    	return storeDao.findById(storeId);
    }
    
    @GET
    @UnitOfWork
    public List<Store> getAll() {
    	return storeDao.findAll();
    }
    
    @GET
    @Timed
    @Path("/{storeId}/items")
    @UnitOfWork
    public List<Item> getAllItems(@PathParam("storeId") long storeId) {
    	System.out.println("asdfsadfdasf");
    	Store store = storeDao.findById(storeId);
    	System.out.println("ASDF");
    	List<Item> items = store.getItems();
    	return items;
    }
    
    @POST
    @Path("/{storeId}/items")
    @UnitOfWork
    public Item addItem(@Valid ItemAddInfo it, @PathParam("storeId") long storeId) {
    	Store store = storeDao.findById(storeId);
    	if (store == null) {
    		throw new NotFoundException("Store could not be found for id: " + storeId);
    	}
    	Item item = itemDao.findById(it.getId());
    	if (item == null) {
    		throw new NotFoundException("Item could not be found for id: " + it.getId());
    	}
    	if(store.getItems().contains(item)) {
    		System.out.println("Item (" + it.getId() + ") already exists within this group ("
        			+ storeId + ")");
    	}
    	store.getItems().add(item);
    	return item;
    }
    
    @GET
    @Path("/{storeId}/items/{itemId}")
    @UnitOfWork
    public Item getItem(@PathParam("storeId") long storeId, @PathParam("itemId") long itemId) {
    	Store store = storeDao.findById(storeId);
    	Item item = itemDao.findById(itemId);
    	return item;
    }
}
