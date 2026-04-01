package io.apicurio.datamodels.models.openapi.v3x.v32;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.openapi.v3x.OpenApi3xOAuthFlows;
import java.util.Map;

public interface OpenApi32OAuthFlows extends OpenApi3xOAuthFlows, OpenApi32Extensible {

	public OpenApi32OAuthFlow getDeviceAuthorization();

	public void setDeviceAuthorization(OpenApi32OAuthFlow value);

	public OpenApi32OAuthFlow createOAuthFlow();

	public Map<String, JsonNode> getExtensions();

	public void addExtension(String name, JsonNode value);

	public void clearExtensions();

	public void removeExtension(String name);

	public void insertExtension(String name, JsonNode value, int atIndex);
}