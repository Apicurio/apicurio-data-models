package io.apicurio.datamodels.models.openapi.v3x.v32;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.openapi.v3x.OpenApi3xOAuthFlow;
import java.util.Map;

public interface OpenApi32OAuthFlow extends OpenApi3xOAuthFlow, OpenApi32Extensible {

	public String getDeviceAuthorizationUrl();

	public void setDeviceAuthorizationUrl(String value);

	public Map<String, JsonNode> getExtensions();

	public void addExtension(String name, JsonNode value);

	public void clearExtensions();

	public void removeExtension(String name);

	public void insertExtension(String name, JsonNode value, int atIndex);
}