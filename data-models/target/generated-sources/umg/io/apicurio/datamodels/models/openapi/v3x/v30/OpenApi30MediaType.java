package io.apicurio.datamodels.models.openapi.v3x.v30;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.openapi.OpenApiExample;
import io.apicurio.datamodels.models.openapi.v3x.OpenApi3xMediaType;
import java.util.Map;

public interface OpenApi30MediaType extends OpenApi3xMediaType, OpenApi30Extensible, OpenApi30ExamplesParent {

	public OpenApi30Example createExample();

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