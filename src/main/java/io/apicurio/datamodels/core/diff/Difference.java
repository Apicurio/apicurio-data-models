package io.apicurio.datamodels.core.diff;

import io.apicurio.datamodels.core.diff.change.Change;
import io.apicurio.datamodels.core.diff.change.ChangeType;
import io.apicurio.datamodels.core.models.NodePath;

public class Difference {
    private DiffType code;
    private ChangeType changeType;
    private NodePath nodePath;
    private String property;
    private String message;

    public Difference(DiffType code, ChangeType changeType, NodePath path, String message) {
        this.code = code;
        this.changeType = changeType;
        this.message = message;
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

    public ChangeType getChangeType() {
        return changeType;
    }

    public void setChangeType(ChangeType changeType) {
        this.changeType = changeType;
    }

    public String getMessage() {
        return message;
    }
}
