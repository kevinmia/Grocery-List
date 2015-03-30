package com.example.grocerylist;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

public class GroceryListConfiguration extends Configuration {
	@Valid
	@NotNull
	private DataSourceFactory database = new DataSourceFactory();
	
	@JsonProperty("database")
    public DataSourceFactory getDataSourceFactory() {
        return database;
    }
	
    @NotEmpty
    private String template;

    @NotEmpty
    private String defaultName = "Stranger";

    @JsonProperty
    public String getTemplate() {
        return template;
    }

    @JsonProperty
    public void setTemplate(String template) {
        this.template = template;
    }

    @JsonProperty
    public String getDefaultName() {
        return defaultName;
    }

    @JsonProperty
    public void setDefaultName(String name) {
        this.defaultName = name;
    }
}