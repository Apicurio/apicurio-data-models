package io.apicurio.datamodels.models.asyncapi.v2x.v24;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.asyncapi.v2x.AsyncApi2xOperationTrait;
import java.util.List;
import java.util.Map;

public interface AsyncApi24OperationTrait
		extends
			AsyncApi2xOperationTrait,
			AsyncApi24Extensible,
			AsyncApi24Referenceable {

	public AsyncApi24SecurityRequirement createSecurityRequirement();

	public List<AsyncApi24SecurityRequirement> getSecurity();

	public void addSecurity(AsyncApi24SecurityRequirement value);

	public void clearSecurity();

	public void removeSecurity(AsyncApi24SecurityRequirement value);

	public void insertSecurity(AsyncApi24SecurityRequirement value, int atIndex);

	public String get$ref();

	public void set$ref(String value);

	public Map<String, JsonNode> getExtensions();

	public void addExtension(String name, JsonNode value);

	public void clearExtensions();

	public void removeExtension(String name);

	public void insertExtension(String name, JsonNode value, int atIndex);
}