package io.apicurio.datamodels.models;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class ExampleModel {
    private String name;
    private String description;
    private JsonNode foo;
    private ObjectNode bar;

    public ExampleModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public JsonNode getFoo() {
        return foo;
    }

    public void setFoo(JsonNode foo) {
        this.foo = foo;
    }

    public ObjectNode getBar() {
        return bar;
    }

    public void setBar(ObjectNode bar) {
        this.bar = bar;
    }

    public String getBarProperty(String propName) {
        return this.bar.get(propName).textValue();
    }
}
