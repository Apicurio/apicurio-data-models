package io.apicurio.datamodels.core.diff.change;

import io.apicurio.datamodels.core.diff.DiffType;
import io.apicurio.datamodels.core.diff.change.Change;
import io.apicurio.datamodels.core.diff.change.ChangeType;

public class NonBreakingChange extends Change {
    public NonBreakingChange(DiffType id, String message) {
        super(id, ChangeType.NON_BREAKING, message);
    }
}
