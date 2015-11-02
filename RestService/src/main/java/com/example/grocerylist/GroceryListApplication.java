package com.example.grocerylist;

import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import com.example.grocerylist.api.hibernate.GroceryList;
import com.example.grocerylist.api.hibernate.Item;
import com.example.grocerylist.api.hibernate.Purchase;
import com.example.grocerylist.api.hibernate.Store;
import com.example.grocerylist.api.hibernate.dao.GroceryListDAO;
import com.example.grocerylist.api.hibernate.dao.ItemDAO;
import com.example.grocerylist.api.hibernate.dao.PurchaseDAO;
import com.example.grocerylist.api.hibernate.dao.StoreDAO;
import com.example.grocerylist.resources.GroceryListResource;
import com.example.grocerylist.resources.ItemResource;
import com.example.grocerylist.resources.PurchaseResource;
import com.example.grocerylist.resources.StoreResource;

public class GroceryListApplication extends Application<GroceryListConfiguration> {
    public static void main(String[] args) throws Exception {
        new GroceryListApplication().run(args);    	
    }

    @Override
    public String getName() {
        return "grocery-list";
    }
    
    private final HibernateBundle<GroceryListConfiguration> hibernate = new HibernateBundle<GroceryListConfiguration>(Item.class, GroceryList.class, Store.class, Purchase.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(GroceryListConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    @Override
    public void initialize(Bootstrap<GroceryListConfiguration> bootstrap) {
        bootstrap.addBundle(hibernate);
    }

    @Override
    public void run(GroceryListConfiguration configuration,
                    Environment environment) {
    	final ItemDAO itemDao = new ItemDAO(hibernate.getSessionFactory());
    	final GroceryListDAO groceryListDao = new GroceryListDAO(hibernate.getSessionFactory());
    	final StoreDAO storeDao = new StoreDAO(hibernate.getSessionFactory());
    	final PurchaseDAO purchaseDao = new PurchaseDAO(hibernate.getSessionFactory());
    	
    	environment.jersey().register(new GroceryListResource(groceryListDao));
    	environment.jersey().register(new ItemResource(itemDao));
    	environment.jersey().register(new StoreResource(storeDao, itemDao));
    	environment.jersey().register(new PurchaseResource(purchaseDao));
    	
    	/*Session session = hibernate.getSessionFactory().openSession();
    	session.beginTransaction();
    	
    	Store store1 = new Store(null, "Costco");
    	Store store2 = new Store(null, "Vons");
    	
    	Item item1 = new Item(null, "Chicken");
    	Item item2 = new Item(null, "Eggs");
    	
    	store1.getItems();
    	
    	session.save(store1);
    	
    	session.getTransaction().commit();
    	session.close();*/
    }
}
