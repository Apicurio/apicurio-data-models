package io.apicurio.datamodels.models.openapi.v2x;

import io.apicurio.datamodels.models.openapi.OpenApiResponse;

public interface OpenApi2xResponse extends OpenApiResponse {

	public OpenApi2xSchema getSchema();

	public void setSchema(OpenApi2xSchema value);

	public OpenApi2xSchema createSchema();

	public OpenApi2xExample getExamples();

	public void setExamples(OpenApi2xExample value);

	public OpenApi2xExample createExample();
}