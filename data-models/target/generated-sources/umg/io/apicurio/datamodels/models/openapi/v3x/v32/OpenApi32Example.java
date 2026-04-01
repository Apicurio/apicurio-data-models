package io.apicurio.datamodels.models.openapi.v3x.v32;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.openapi.v3x.OpenApi3xExample;
import java.util.Map;

public interface OpenApi32Example extends OpenApi3xExample, OpenApi32Extensible, OpenApi32Referenceable {

	public JsonNode getDataValue();

	public void setDataValue(JsonNode value);

	public String getSerializedValue();

	public void setSerializedValue(String value);

	public String get$ref();

	public void set$ref(String value);

	public String getSummary();

	public void setSummary(String value);

	public String getDescription();

	public void setDescription(String value);

	public Map<String, JsonNode> getExtensions();

	public void addExtension(String name, JsonNode value);

	public void clearExtensions();

	public void removeExtension(String name);

	public void insertExtension(String name, JsonNode value, int atIndex);
}