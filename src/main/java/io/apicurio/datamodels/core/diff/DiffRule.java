package io.apicurio.datamodels.core.diff;

public class DiffRule {
    private ChangeSeverity changeSeverity;
    private String message;
    private Boolean disabled;

    public DiffRule(ChangeSeverity severity, String message) {
        this.changeSeverity = severity;
        this.message = message;
    }

    public ChangeSeverity getChangeSeverity() {
        return changeSeverity;
    }

    public void setChangeSeverity(ChangeSeverity changeSeverity) {
        this.changeSeverity = changeSeverity;
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
