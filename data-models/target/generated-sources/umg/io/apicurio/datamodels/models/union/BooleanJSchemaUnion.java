package io.apicurio.datamodels.models.union;

import io.apicurio.datamodels.models.jsonschema.JsonSchemaJSchema;

public interface BooleanJSchemaUnion extends Union {

	public boolean isBoolean();

	public Boolean asBoolean();

	public boolean isJSchema();

	public JsonSchemaJSchema asJSchema();
}