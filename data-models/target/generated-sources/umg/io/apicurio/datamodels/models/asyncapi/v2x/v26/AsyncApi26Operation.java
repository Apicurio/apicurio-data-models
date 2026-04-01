package io.apicurio.datamodels.models.asyncapi.v2x.v26;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.asyncapi.v2x.AsyncApi2xOperation;
import java.util.List;
import java.util.Map;

public interface AsyncApi26Operation extends AsyncApi2xOperation, AsyncApi26Extensible {

	public AsyncApi26SecurityRequirement createSecurityRequirement();

	public List<AsyncApi26SecurityRequirement> getSecurity();

	public void addSecurity(AsyncApi26SecurityRequirement value);

	public void clearSecurity();

	public void removeSecurity(AsyncApi26SecurityRequirement value);

	public void insertSecurity(AsyncApi26SecurityRequirement value, int atIndex);

	public Map<String, JsonNode> getExtensions();

	public void addExtension(String name, JsonNode value);

	public void clearExtensions();

	public void removeExtension(String name);

	public void insertExtension(String name, JsonNode value, int atIndex);
}