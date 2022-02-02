package io.apicurio.datamodels.core.diff;

public enum ChangeSeverity {
    BREAKING_CHANGE("BREAKING_CHANGE"),
    NON_BREAKING("NON_BREAKING_CHANGE"),
    DANGEROUS_CHANGE("DANGEROUS_CHANGE");

    private final String severity;

    ChangeSeverity(String severity) {
        this.severity = severity;
    }

    public String getSeverity() {
        return severity;
    }
}
