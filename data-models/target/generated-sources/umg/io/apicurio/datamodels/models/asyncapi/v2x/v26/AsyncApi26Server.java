package io.apicurio.datamodels.models.asyncapi.v2x.v26;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.SecurityRequirement;
import io.apicurio.datamodels.models.asyncapi.v2x.AsyncApi2xServer;
import java.util.List;
import java.util.Map;

public interface AsyncApi26Server
		extends
			AsyncApi2xServer,
			AsyncApi26Extensible,
			AsyncApi26Referenceable,
			AsyncApi26SecurityRequirementsParent {

	public AsyncApi26Tag createTag();

	public List<AsyncApi26Tag> getTags();

	public void addTag(AsyncApi26Tag value);

	public void clearTags();

	public void removeTag(AsyncApi26Tag value);

	public void insertTag(AsyncApi26Tag value, int atIndex);

	public String get$ref();

	public void set$ref(String value);

	public AsyncApi26SecurityRequirement createSecurityRequirement();

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