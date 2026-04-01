package io.apicurio.datamodels.models.openapi;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.Schema;

public interface OpenApiSchema extends Schema {

	public JsonNode getExample();

	public void setExample(JsonNode value);

	public OpenApiExternalDocumentation getExternalDocs();

	public void setExternalDocs(OpenApiExternalDocumentation value);

	public OpenApiExternalDocumentation createExternalDocumentation();

	public OpenApiXML getXml();

	public void setXml(OpenApiXML value);

	public OpenApiXML createXML();
}