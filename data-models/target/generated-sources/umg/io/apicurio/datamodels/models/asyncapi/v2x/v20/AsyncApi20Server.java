package io.apicurio.datamodels.models.asyncapi.v2x.v20;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.SecurityRequirement;
import io.apicurio.datamodels.models.asyncapi.v2x.AsyncApi2xServer;
import java.util.List;
import java.util.Map;

public interface AsyncApi20Server extends AsyncApi2xServer, AsyncApi20Extensible, AsyncApi20SecurityRequirementsParent {

	public AsyncApi20SecurityRequirement createSecurityRequirement();

	public List<SecurityRequirement> getSecurity();

	public void addSecurity(SecurityRequirement value);

	public void clearSecurity();

	public void removeSecurity(SecurityRequirement value);

	public void insertSecurity(SecurityRequirement value, int atIndex);

	public Map<String, JsonNode> getExtensions();

	public void addExtension(String name, JsonNode value);

	public void clearExtensions();

	public void removeExtension(String name);

	public void insertExtension(String name, JsonNode value, int atIndex);
}