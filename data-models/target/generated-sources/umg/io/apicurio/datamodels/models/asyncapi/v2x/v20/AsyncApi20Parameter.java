package io.apicurio.datamodels.models.asyncapi.v2x.v20;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.asyncapi.v2x.AsyncApi2xParameter;
import java.util.Map;

public interface AsyncApi20Parameter extends AsyncApi2xParameter, AsyncApi20Extensible, AsyncApi20Referenceable {

	public String get$ref();

	public void set$ref(String value);

	public Map<String, JsonNode> getExtensions();

	public void addExtension(String name, JsonNode value);

	public void clearExtensions();

	public void removeExtension(String name);

	public void insertExtension(String name, JsonNode value, int atIndex);
}