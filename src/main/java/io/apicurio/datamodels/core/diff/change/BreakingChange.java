package io.apicurio.datamodels.core.diff.change;

public class BreakingChange extends Change {
    public BreakingChange(String message) {
        super(ChangeType.BREAKING, message);
    }
}
