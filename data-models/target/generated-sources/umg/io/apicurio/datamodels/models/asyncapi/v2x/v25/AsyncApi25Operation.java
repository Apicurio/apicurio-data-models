package io.apicurio.datamodels.models.asyncapi.v2x.v25;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.asyncapi.v2x.AsyncApi2xOperation;
import java.util.List;
import java.util.Map;

public interface AsyncApi25Operation extends AsyncApi2xOperation, AsyncApi25Extensible {

	public AsyncApi25SecurityRequirement createSecurityRequirement();

	public List<AsyncApi25SecurityRequirement> getSecurity();

	public void addSecurity(AsyncApi25SecurityRequirement value);

	public void clearSecurity();

	public void removeSecurity(AsyncApi25SecurityRequirement value);

	public void insertSecurity(AsyncApi25SecurityRequirement value, int atIndex);

	public Map<String, JsonNode> getExtensions();

	public void addExtension(String name, JsonNode value);

	public void clearExtensions();

	public void removeExtension(String name);

	public void insertExtension(String name, JsonNode value, int atIndex);
}