package io.apicurio.datamodels.models.asyncapi.v2x.v23;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.asyncapi.v2x.AsyncApi2xSchema;
import java.util.Map;

public interface AsyncApi23Schema extends AsyncApi2xSchema, AsyncApi23Extensible, AsyncApi23Referenceable {

	public String get$ref();

	public void set$ref(String value);

	public Map<String, JsonNode> getExtensions();

	public void addExtension(String name, JsonNode value);

	public void clearExtensions();

	public void removeExtension(String name);

	public void insertExtension(String name, JsonNode value, int atIndex);
}