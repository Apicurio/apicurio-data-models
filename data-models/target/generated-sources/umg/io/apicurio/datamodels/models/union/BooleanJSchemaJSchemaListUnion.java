package io.apicurio.datamodels.models.union;

import io.apicurio.datamodels.models.jsonschema.JsonSchemaJSchema;
import java.util.List;

public interface BooleanJSchemaJSchemaListUnion extends Union {

	public boolean isBoolean();

	public Boolean asBoolean();

	public boolean isJSchema();

	public JsonSchemaJSchema asJSchema();

	public boolean isJSchemaList();

	public List<JsonSchemaJSchema> asJSchemaList();
}