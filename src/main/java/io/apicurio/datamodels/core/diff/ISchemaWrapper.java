package io.apicurio.datamodels.core.diff;

public interface ISchemaWrapper {
    void accept(UpdatedOas30DiffVisitor visitor);
}
