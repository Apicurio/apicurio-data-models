package io.apicurio.datamodels.core.diff.change;

import io.apicurio.datamodels.core.diff.DiffType;

public class Change {
    private DiffType id;
    private ChangeType changeType;
    private String message;
    private Boolean disabled;

    public Change(DiffType id, ChangeType severity, String message) {
        this.id = id;
        this.changeType = severity;
        this.message = message;
    }

    public Change(DiffType id, ChangeType severity, String message, Boolean disabled) {
        this.id = id;
        this.changeType = severity;
        this.message = message;
        this.disabled = disabled;
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

    public Boolean isDisabled() {
        return disabled;
    }

    public Boolean isEnabled() {
        return !this.isDisabled();
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    public DiffType getId() {
        return id;
    }

    public void setId(DiffType id) {
        this.id = id;
    }
}
