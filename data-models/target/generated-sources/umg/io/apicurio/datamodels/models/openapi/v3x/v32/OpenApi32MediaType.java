package io.apicurio.datamodels.models.openapi.v3x.v32;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.openapi.OpenApiExample;
import io.apicurio.datamodels.models.openapi.v3x.OpenApi3xMediaType;
import java.util.Map;

public interface OpenApi32MediaType extends OpenApi3xMediaType, OpenApi32Extensible, OpenApi32ExamplesParent {

	public OpenApi32Schema getItemSchema();

	public void setItemSchema(OpenApi32Schema value);

	public OpenApi32Schema createSchema();

	public OpenApi32Example createExample();

	public Map<String, OpenApiExample> getExamples();

	public void addExample(String name, OpenApiExample value);

	public void clearExamples();

	public void removeExample(String name);

	public void insertExample(String name, OpenApiExample value, int atIndex);

	public Map<String, JsonNode> getExtensions();

	public void addExtension(String name, JsonNode value);

	public void clearExtensions();

	public void removeExtension(String name);

	public void insertExtension(String name, JsonNode value, int atIndex);
}