package io.apicurio.datamodels.models.openapi.v2x;

import io.apicurio.datamodels.models.openapi.OpenApiSchema;
import io.apicurio.datamodels.models.union.SchemaSchemaListUnion;

public interface OpenApi2xSchema extends OpenApiSchema, SchemaSchemaListUnion {

	public String getType();

	public void setType(String value);

	public String getDiscriminator();

	public void setDiscriminator(String value);

	public Boolean isExclusiveMaximum();

	public void setExclusiveMaximum(Boolean value);

	public Boolean isExclusiveMinimum();

	public void setExclusiveMinimum(Boolean value);

	public SchemaSchemaListUnion getItems();

	public void setItems(SchemaSchemaListUnion value);

	public OpenApi2xSchema createSchema();
}