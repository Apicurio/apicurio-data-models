package io.apicurio.datamodels.models.asyncapi.v2x.v21;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.asyncapi.v2x.AsyncApi2xOAuthFlows;
import java.util.Map;

public interface AsyncApi21OAuthFlows extends AsyncApi2xOAuthFlows, AsyncApi21Extensible {

	public Map<String, JsonNode> getExtensions();

	public void addExtension(String name, JsonNode value);

	public void clearExtensions();

	public void removeExtension(String name);

	public void insertExtension(String name, JsonNode value, int atIndex);
}