package io.apicurio.datamodels.core.diff;

import io.apicurio.datamodels.core.models.NodePath;

public class Difference {
    private DiffType code;
    private ChangeType severity;
    private String message;
    private NodePath nodePath;
    private String property;

    public Difference(DiffType code, ChangeType sev, String message, NodePath path) {
        this.code = code;
        this.severity = sev;
        this.message = message;
        this.nodePath = path;
    }

    public Difference(DiffType code, ChangeType sev, String message, NodePath path, String property) {
        this.code = code;
        this.severity = sev;
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

    public ChangeType getSeverity() {
        return severity;
    }

    public void setSeverity(ChangeType severity) {
        this.severity = severity;
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
}
