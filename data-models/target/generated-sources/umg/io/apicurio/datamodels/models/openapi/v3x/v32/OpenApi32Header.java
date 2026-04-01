package io.apicurio.datamodels.models.openapi.v3x.v32;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.openapi.OpenApiExample;
import io.apicurio.datamodels.models.openapi.v3x.OpenApi3xHeader;
import java.util.Map;

public interface OpenApi32Header
		extends
			OpenApi3xHeader,
			OpenApi32Extensible,
			OpenApi32Referenceable,
			OpenApi32ExamplesParent {

	public String get$ref();

	public void set$ref(String value);

	public String getSummary();

	public void setSummary(String value);

	public String getDescription();

	public void setDescription(String value);

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