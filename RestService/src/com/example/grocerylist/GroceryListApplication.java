package com.example.grocerylist;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import com.example.grocerylist.health.TemplateHealthCheck;
import com.example.grocerylist.resources.GroceryListResource;

public class GroceryListApplication extends Application<GroceryListConfiguration> {
    public static void main(String[] args) throws Exception {
        new GroceryListApplication().run(args);
    }

    @Override
    public String getName() {
        return "grocery-list";
    }

    @Override
    public void initialize(Bootstrap<GroceryListConfiguration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(GroceryListConfiguration configuration,
                    Environment environment) {
        final GroceryListResource resource = new GroceryListResource(
            configuration.getTemplate(),
            configuration.getDefaultName()
        );
        final TemplateHealthCheck healthCheck =
        	new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);
        environment.jersey().register(resource);
    }

}
