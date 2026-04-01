package io.apicurio.datamodels.models.asyncapi.v2x.v24;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.asyncapi.v2x.AsyncApi2xOperation;
import java.util.List;
import java.util.Map;

public interface AsyncApi24Operation extends AsyncApi2xOperation, AsyncApi24Extensible {

	public AsyncApi24SecurityRequirement createSecurityRequirement();

	public List<AsyncApi24SecurityRequirement> getSecurity();

	public void addSecurity(AsyncApi24SecurityRequirement value);

	public void clearSecurity();

	public void removeSecurity(AsyncApi24SecurityRequirement value);

	public void insertSecurity(AsyncApi24SecurityRequirement value, int atIndex);

	public Map<String, JsonNode> getExtensions();

	public void addExtension(String name, JsonNode value);

	public void clearExtensions();

	public void removeExtension(String name);

	public void insertExtension(String name, JsonNode value, int atIndex);
}