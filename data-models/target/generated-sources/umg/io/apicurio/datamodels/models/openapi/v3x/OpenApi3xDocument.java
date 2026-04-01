package io.apicurio.datamodels.models.openapi.v3x;

import io.apicurio.datamodels.models.openapi.OpenApiDocument;

public interface OpenApi3xDocument extends OpenApiDocument {

	public String getOpenapi();

	public void setOpenapi(String value);

	public OpenApi3xComponents getComponents();

	public void setComponents(OpenApi3xComponents value);

	public OpenApi3xComponents createComponents();
}