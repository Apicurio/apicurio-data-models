package io.apicurio.datamodels.core.diff.change;

import io.apicurio.datamodels.core.diff.DiffType;

public class BreakingChange extends Change {
    public BreakingChange(DiffType id, String message) {
        super(id, ChangeType.BREAKING, message);
    }
}
