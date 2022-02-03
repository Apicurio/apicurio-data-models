package io.apicurio.datamodels.core.diff;

public abstract class Change {
    private ChangeType type;
    private String message;
    private Boolean disabled;

    public Change(ChangeType severity, String message) {
        this.type = severity;
        this.message = message;
    }

    public ChangeType getType() {
        return type;
    }

    public void setType(ChangeType type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }
}
