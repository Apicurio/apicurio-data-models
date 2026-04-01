package io.apicurio.datamodels.models.openapi.v2x.v20;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.RootNode;
import io.apicurio.datamodels.models.SecurityRequirement;
import io.apicurio.datamodels.models.openapi.v2x.OpenApi2xDocument;
import java.util.List;
import java.util.Map;

public interface OpenApi20Document
		extends
			RootNode,
			OpenApi2xDocument,
			OpenApi20Extensible,
			OpenApi20SecurityRequirementsParent {

	public OpenApi20SecurityRequirement createSecurityRequirement();

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