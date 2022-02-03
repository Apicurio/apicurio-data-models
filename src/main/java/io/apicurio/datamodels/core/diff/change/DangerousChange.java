package io.apicurio.datamodels.core.diff.change;

public class DangerousChange extends Change {

    public DangerousChange(String message) {
        super(ChangeType.DANGEROUS, message);
    }
}
