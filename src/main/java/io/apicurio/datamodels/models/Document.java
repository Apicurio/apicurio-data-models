package io.apicurio.datamodels.models;

public class Document {

    private String id;
    private ExampleModel example;

    public Document() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ExampleModel getExample() {
        return example;
    }

    public void setExample(ExampleModel example) {
        this.example = example;
    }

}
