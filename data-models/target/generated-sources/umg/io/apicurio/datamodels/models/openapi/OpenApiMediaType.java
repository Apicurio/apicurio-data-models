package io.apicurio.datamodels.models.openapi;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.Node;
import java.util.Map;

public interface OpenApiMediaType extends Node {

	public OpenApiEncoding createEncoding();

	public Map<String, OpenApiEncoding> getEncoding();

	public void addEncoding(String name, OpenApiEncoding value);

	public void clearEncoding();

	public void removeEncoding(String name);

	public void insertEncoding(String name, OpenApiEncoding value, int atIndex);

	public JsonNode getExample();

	public void setExample(JsonNode value);

	public OpenApiSchema getSchema();

	public void setSchema(OpenApiSchema value);

	public OpenApiSchema createSchema();
}