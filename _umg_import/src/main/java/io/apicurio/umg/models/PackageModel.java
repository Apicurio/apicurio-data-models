package io.apicurio.umg.models;

import java.util.HashMap;
import java.util.Map;

public class PackageModel {

    private String name;
    private PackageModel parent;
    private Map<String, PackageModel> children = new HashMap<>();
    private Map<String, ClassModel> classes = new HashMap<>();

    public PackageModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PackageModel getParent() {
        return parent;
    }

    public void setParent(PackageModel parent) {
        this.parent = parent;
    }

    public Map<String, PackageModel> getChildren() {
        return children;
    }

    public void setChildren(Map<String, PackageModel> children) {
        this.children = children;
    }

    public Map<String, ClassModel> getClasses() {
        return classes;
    }

    public void setClasses(Map<String, ClassModel> classes) {
        this.classes = classes;
    }
}
