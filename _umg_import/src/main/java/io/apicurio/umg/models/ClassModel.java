package io.apicurio.umg.models;

import java.util.HashMap;
import java.util.Map;

public class ClassModel {

    private ClassModel parent;
    private PackageModel _package;
    private String name;
    private Map<String, FieldModel> fields = new HashMap<>();
    private boolean _abstract;
    private boolean core;
    private boolean includeAccept;

    public ClassModel() {
    }

    public ClassModel getParent() {
        return parent;
    }

    public void setParent(ClassModel parent) {
        this.parent = parent;
    }

    public PackageModel getPackage() {
        return _package;
    }

    public void setPackage(PackageModel _package) {
        this._package = _package;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, FieldModel> getFields() {
        return fields;
    }

    public void setFields(Map<String, FieldModel> fields) {
        this.fields = fields;
    }

    public boolean isAbstract() {
        return _abstract;
    }

    public void setAbstract(boolean _abstract) {
        this._abstract = _abstract;
    }

    public String getFullyQualifiedName() {
        return getPackage().getName() + "." + getName();
    }

    public boolean isCore() {
        return core;
    }

    public void setCore(boolean core) {
        this.core = core;
    }

    public boolean isIncludeAccept() {
        return includeAccept;
    }

    public void setIncludeAccept(boolean includeAccept) {
        this.includeAccept = includeAccept;
    }
}
