package io.apicurio.datamodels.core.diff.change;

import io.apicurio.datamodels.core.diff.DiffType;

public class DangerousChange extends Change {

    public DangerousChange(DiffType id, String message) {
        super(id, ChangeType.DANGEROUS, message);
    }
}
