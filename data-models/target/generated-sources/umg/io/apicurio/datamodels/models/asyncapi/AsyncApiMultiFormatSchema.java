package io.apicurio.datamodels.models.asyncapi;

import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.union.AnySchemaUnion;

public interface AsyncApiMultiFormatSchema extends Node {

	public String getSchemaFormat();

	public void setSchemaFormat(String value);

	public AnySchemaUnion getSchema();

	public void setSchema(AnySchemaUnion value);

	public AsyncApiSchema createSchema();
}