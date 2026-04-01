package io.apicurio.datamodels.models.asyncapi.v3x.v30;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.asyncapi.v3x.AsyncApi3xMessageExample;
import java.util.Map;

public interface AsyncApi30MessageExample extends AsyncApi3xMessageExample, AsyncApi30Extensible {

	public Map<String, JsonNode> getPayload();

	public void setPayload(Map<String, JsonNode> value);

	public Map<String, JsonNode> getExtensions();

	public void addExtension(String name, JsonNode value);

	public void clearExtensions();

	public void removeExtension(String name);

	public void insertExtension(String name, JsonNode value, int atIndex);
}