package io.apicurio.datamodels.core.diff;

public class NonBreakingChange extends Change {
    public NonBreakingChange(String message) {
        super(ChangeType.NON_BREAKING, message);
    }
}
