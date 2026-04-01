package io.apicurio.datamodels.models.union;

import io.apicurio.datamodels.models.Schema;
import io.apicurio.datamodels.models.asyncapi.AsyncApiMultiFormatSchema;

public interface MultiFormatSchemaSchemaUnion extends Union {

	public boolean isMultiFormatSchema();

	public AsyncApiMultiFormatSchema asMultiFormatSchema();

	public boolean isSchema();

	public Schema asSchema();
}