package io.apicurio.datamodels.core.diff;

import io.apicurio.datamodels.core.diff.change.Change;
import io.apicurio.datamodels.core.diff.change.ChangeType;
import io.apicurio.datamodels.core.models.NodePath;

public class Difference {
    private DiffType code;
    private ChangeType changeType;
    private Change change;
    private String message;
    private NodePath nodePath;
    private String property;

    public Difference(DiffType code, ChangeType sev, String message, NodePath path) {
        this.code = code;
        this.changeType = sev;
        this.message = message;
        this.nodePath = path;
    }

    public Difference(DiffType code, Change change, NodePath path) {
        this.code = code;
        this.change = change;
        this.nodePath = path;
    }

    public Difference(DiffType code, ChangeType sev, String message, NodePath path, String property) {
        this.code = code;
        this.changeType = sev;
        this.message = message;
        this.nodePath = path;
        this.property = property;
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

    public ChangeType getChangeType() {
        return changeType;
    }

    public void setChangeType(ChangeType changeType) {
        this.changeType = changeType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
