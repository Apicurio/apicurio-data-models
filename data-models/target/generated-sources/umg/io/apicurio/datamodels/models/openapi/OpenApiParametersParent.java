package io.apicurio.datamodels.models.openapi;

import java.util.List;

public interface OpenApiParametersParent {

	public OpenApiParameter createParameter();

	public List<OpenApiParameter> getParameters();

	public void addParameter(OpenApiParameter value);

	public void clearParameters();

	public void removeParameter(OpenApiParameter value);

	public void insertParameter(OpenApiParameter value, int atIndex);
}