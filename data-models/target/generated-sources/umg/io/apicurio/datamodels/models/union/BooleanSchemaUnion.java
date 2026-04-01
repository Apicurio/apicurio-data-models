package io.apicurio.datamodels.models.union;

import io.apicurio.datamodels.models.Schema;

public interface BooleanSchemaUnion extends Union {

	public boolean isBoolean();

	public Boolean asBoolean();

	public boolean isSchema();

	public Schema asSchema();
}