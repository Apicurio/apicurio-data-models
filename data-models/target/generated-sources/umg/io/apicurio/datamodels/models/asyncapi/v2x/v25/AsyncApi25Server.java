package io.apicurio.datamodels.models.asyncapi.v2x.v25;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.SecurityRequirement;
import io.apicurio.datamodels.models.asyncapi.v2x.AsyncApi2xServer;
import java.util.List;
import java.util.Map;

public interface AsyncApi25Server
		extends
			AsyncApi2xServer,
			AsyncApi25Extensible,
			AsyncApi25Referenceable,
			AsyncApi25SecurityRequirementsParent {

	public AsyncApi25Tag createTag();

	public List<AsyncApi25Tag> getTags();

	public void addTag(AsyncApi25Tag value);

	public void clearTags();

	public void removeTag(AsyncApi25Tag value);

	public void insertTag(AsyncApi25Tag value, int atIndex);

	public String get$ref();

	public void set$ref(String value);

	public AsyncApi25SecurityRequirement createSecurityRequirement();

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