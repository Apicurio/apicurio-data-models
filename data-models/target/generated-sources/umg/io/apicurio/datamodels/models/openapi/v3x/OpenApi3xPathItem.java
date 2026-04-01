package io.apicurio.datamodels.models.openapi.v3x;

import io.apicurio.datamodels.models.openapi.OpenApiPathItem;

public interface OpenApi3xPathItem extends OpenApiPathItem {

	public OpenApi3xOperation getTrace();

	public void setTrace(OpenApi3xOperation value);

	public OpenApi3xOperation createOperation();

	public String getDescription();

	public void setDescription(String value);

	public String getSummary();

	public void setSummary(String value);
}