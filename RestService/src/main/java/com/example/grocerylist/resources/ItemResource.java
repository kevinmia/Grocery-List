package com.example.grocerylist.resources;

import io.dropwizard.hibernate.UnitOfWork;

import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.example.grocerylist.api.ItemAddInfo;
import com.example.grocerylist.api.hibernate.Item;
import com.example.grocerylist.api.hibernate.dao.ItemDAO;

@Path("/grocery-list/items")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ItemResource {
    private final ItemDAO itemDao;
    
    public ItemResource(ItemDAO itemDao) {
        this.itemDao = itemDao;
    }
    
    @GET
    @Path("/{itemId}")
    @UnitOfWork
    public Item get(@PathParam("itemId") long itemId) {
    	return itemDao.findById(itemId);
    }
    
    @POST
    @UnitOfWork
    public Item add(@Valid ItemAddInfo item) {
        return itemDao.save(new Item(null, item.getName()));
    }
    
    @PUT
    @Path("/{itemId}")
    @UnitOfWork
    public Item change(@PathParam("itemId") long itemId, @Valid ItemAddInfo item) {
    	return itemDao.save(new Item(itemId, item.getName()));
    }
    
    @DELETE
    @Path("/{itemId}")
    @UnitOfWork
    public Item delete(@PathParam("itemId") long itemId) {
    	Item item = itemDao.findById(itemId);
    	itemDao.delete(item);
    	return item;
    }
    
    @GET
    @UnitOfWork
    public List<Item> getAll() {
    	return itemDao.findAll();
    }
}