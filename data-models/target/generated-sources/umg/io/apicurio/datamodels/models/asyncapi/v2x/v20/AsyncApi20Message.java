package io.apicurio.datamodels.models.asyncapi.v2x.v20;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.asyncapi.v2x.AsyncApi2xMessage;
import java.util.Map;

public interface AsyncApi20Message extends AsyncApi2xMessage, AsyncApi20Extensible, AsyncApi20Referenceable {

	public Map<String, JsonNode> getExamples();

	public void setExamples(Map<String, JsonNode> value);

	public String get$ref();

	public void set$ref(String value);

	public Map<String, JsonNode> getExtensions();

	public void addExtension(String name, JsonNode value);

	public void clearExtensions();

	public void removeExtension(String name);

	public void insertExtension(String name, JsonNode value, int atIndex);
}