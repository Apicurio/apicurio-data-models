package io.apicurio.datamodels.models.asyncapi.v2x.v25;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.asyncapi.v2x.AsyncApi2xSecurityScheme;
import java.util.Map;

public interface AsyncApi25SecurityScheme
		extends
			AsyncApi2xSecurityScheme,
			AsyncApi25Extensible,
			AsyncApi25Referenceable {

	public String get$ref();

	public void set$ref(String value);

	public Map<String, JsonNode> getExtensions();

	public void addExtension(String name, JsonNode value);

	public void clearExtensions();

	public void removeExtension(String name);

	public void insertExtension(String name, JsonNode value, int atIndex);
}