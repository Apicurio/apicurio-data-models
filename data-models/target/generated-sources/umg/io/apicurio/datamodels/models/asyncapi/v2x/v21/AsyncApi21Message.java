package io.apicurio.datamodels.models.asyncapi.v2x.v21;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.asyncapi.v2x.AsyncApi2xMessage;
import java.util.Map;

public interface AsyncApi21Message extends AsyncApi2xMessage, AsyncApi21Extensible, AsyncApi21Referenceable {

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