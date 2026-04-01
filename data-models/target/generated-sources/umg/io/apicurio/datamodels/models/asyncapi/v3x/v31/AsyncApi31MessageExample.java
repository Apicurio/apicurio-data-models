package io.apicurio.datamodels.models.asyncapi.v3x.v31;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.asyncapi.v3x.AsyncApi3xMessageExample;
import java.util.Map;

public interface AsyncApi31MessageExample extends AsyncApi3xMessageExample, AsyncApi31Extensible {

	public JsonNode getPayload();

	public void setPayload(JsonNode value);

	public Map<String, JsonNode> getExtensions();

	public void addExtension(String name, JsonNode value);

	public void clearExtensions();

	public void removeExtension(String name);

	public void insertExtension(String name, JsonNode value, int atIndex);
}