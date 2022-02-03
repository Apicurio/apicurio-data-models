package io.apicurio.datamodels.core.diff.change;

import io.apicurio.datamodels.core.diff.change.Change;
import io.apicurio.datamodels.core.diff.change.ChangeType;

public class NonBreakingChange extends Change {
    public NonBreakingChange(String message) {
        super(ChangeType.NON_BREAKING, message);
    }
}
