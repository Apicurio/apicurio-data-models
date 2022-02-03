package io.apicurio.datamodels.core.diff;

public class DangerousChange extends Change {

    public DangerousChange(String message) {
        super(ChangeType.DANGEROUS, message);
    }
}
