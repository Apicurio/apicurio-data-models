package io.apicurio.datamodels.models.openapi;

import io.apicurio.datamodels.models.Parameter;

public interface OpenApiParameter extends Parameter {

	public Boolean isRequired();

	public void setRequired(Boolean value);

	public String getName();

	public void setName(String value);

	public OpenApiSchema getSchema();

	public void setSchema(OpenApiSchema value);

	public OpenApiSchema createSchema();

	public String getIn();

	public void setIn(String value);

	public Boolean isAllowEmptyValue();

	public void setAllowEmptyValue(Boolean value);
}