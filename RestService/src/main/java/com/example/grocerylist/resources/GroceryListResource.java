package com.example.grocerylist.resources;

import java.util.List;

import io.dropwizard.hibernate.UnitOfWork;

import com.example.grocerylist.api.GroceryListAddInfo;
import com.example.grocerylist.api.hibernate.GroceryList;
import com.example.grocerylist.api.hibernate.dao.GroceryListDAO;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/grocery-list/lists")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GroceryListResource {
    private final GroceryListDAO groceryListDao;

    public GroceryListResource(GroceryListDAO groceryListDao) {
        this.groceryListDao = groceryListDao;
    }

    @POST
    @UnitOfWork
    public GroceryList add(@Valid GroceryListAddInfo groceryList) {
        return groceryListDao.save(new GroceryList(null, groceryList.getName(), false));
    }
    
    @GET
    @UnitOfWork
    public List<GroceryList> getAll() {
    	return groceryListDao.findAll();
    }
    
    @DELETE
    @Path("/{listId}")
    @UnitOfWork
    public GroceryList delete(@PathParam("listId") long listId) {
    	GroceryList list = groceryListDao.findById(listId);
    	groceryListDao.delete(list);
    	return list;
    }
    
}