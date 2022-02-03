package io.apicurio.datamodels.core.diff;

public enum ChangeType {
    BREAKING("BREAKING"),
    NON_BREAKING("NON_BREAKING"),
    DANGEROUS("DANGEROUS");

    private final String type;

    ChangeType(String severity) {
        this.type = severity;
    }

    public String getType() {
        return type;
    }
}
