package io.apicurio.datamodels.models.asyncapi.v3x.v30;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.asyncapi.v3x.AsyncApi3xMessageBindings;
import java.util.Map;

public interface AsyncApi30MessageBindings
		extends
			AsyncApi3xMessageBindings,
			AsyncApi30Extensible,
			AsyncApi30Referenceable {

	public String get$ref();

	public void set$ref(String value);

	public Map<String, JsonNode> getExtensions();

	public void addExtension(String name, JsonNode value);

	public void clearExtensions();

	public void removeExtension(String name);

	public void insertExtension(String name, JsonNode value, int atIndex);
}