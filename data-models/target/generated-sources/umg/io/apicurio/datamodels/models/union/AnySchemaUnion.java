package io.apicurio.datamodels.models.union;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.Schema;

public interface AnySchemaUnion extends Union {

	public boolean isAny();

	public JsonNode asAny();

	public boolean isSchema();

	public Schema asSchema();
}