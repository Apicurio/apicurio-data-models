package io.apicurio.datamodels.core.diff;

public class DiffTestCase {
    private String name;
    private String original;
    private String updated;

    public DiffTestCase() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }
}
