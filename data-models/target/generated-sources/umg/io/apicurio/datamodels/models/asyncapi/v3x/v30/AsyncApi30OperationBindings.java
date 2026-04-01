package io.apicurio.datamodels.models.asyncapi.v3x.v30;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.asyncapi.v3x.AsyncApi3xOperationBindings;
import java.util.Map;

public interface AsyncApi30OperationBindings
		extends
			AsyncApi3xOperationBindings,
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