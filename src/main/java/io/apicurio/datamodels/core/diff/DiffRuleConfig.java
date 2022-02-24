package io.apicurio.datamodels.core.diff;

public class DiffRuleConfig {
    private DiffType code;
    private ChangeType changeType;
    private String messageTemplate;
    private Boolean disabled;

    public DiffRuleConfig(DiffType id, ChangeType severity, String message, Boolean disabled) {
        this.code = id;
        this.changeType = severity;
        this.messageTemplate = message;
        this.disabled = disabled;
    }

    public ChangeType getChangeType() {
        return changeType;
    }

    public void setChangeType(ChangeType changeType) {
        this.changeType = changeType;
    }

    public String getMessageTemplate() {
        return messageTemplate;
    }

    public void setMessageTemplate(String messageTemplate) {
        this.messageTemplate = messageTemplate;
    }

    public Boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    public DiffType getCode() {
        return code;
    }

    public void setCode(DiffType code) {
        this.code = code;
    }
}
