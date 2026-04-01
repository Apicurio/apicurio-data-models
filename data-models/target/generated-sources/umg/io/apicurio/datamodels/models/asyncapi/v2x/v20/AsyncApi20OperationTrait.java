package io.apicurio.datamodels.models.asyncapi.v2x.v20;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.asyncapi.v2x.AsyncApi2xOperationTrait;
import java.util.Map;

public interface AsyncApi20OperationTrait
		extends
			AsyncApi2xOperationTrait,
			AsyncApi20Extensible,
			AsyncApi20Referenceable {

	public String get$ref();

	public void set$ref(String value);

	public Map<String, JsonNode> getExtensions();

	public void addExtension(String name, JsonNode value);

	public void clearExtensions();

	public void removeExtension(String name);

	public void insertExtension(String name, JsonNode value, int atIndex);
}