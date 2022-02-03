package io.apicurio.datamodels.core.diff;

import io.apicurio.datamodels.core.diff.change.Change;
import io.apicurio.datamodels.core.diff.change.ChangeType;
import io.apicurio.datamodels.core.models.NodePath;

public class Difference {
    private DiffType code;
    private Change change;
    private NodePath nodePath;
    private String property;

    public Difference(DiffType code, Change change, NodePath path) {
        this.code = code;
        this.change = change;
        this.nodePath = path;
    }

    public NodePath getPath() {
        return nodePath;
    }

    public void setPath(NodePath path) {
        this.nodePath = path;
    }

    public DiffType getCode() {
        return code;
    }

    public void setCode(DiffType code) {
        this.code = code;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public Change getChange() {
        return change;
    }

    public void setChange(Change change) {
        this.change = change;
    }
}
