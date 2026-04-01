package io.apicurio.datamodels.models.asyncapi.v2x.v23;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.SecurityRequirement;
import io.apicurio.datamodels.models.asyncapi.v2x.AsyncApi2xServer;
import java.util.List;
import java.util.Map;

public interface AsyncApi23Server
		extends
			AsyncApi2xServer,
			AsyncApi23Extensible,
			AsyncApi23Referenceable,
			AsyncApi23SecurityRequirementsParent {

	public String get$ref();

	public void set$ref(String value);

	public AsyncApi23SecurityRequirement createSecurityRequirement();

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