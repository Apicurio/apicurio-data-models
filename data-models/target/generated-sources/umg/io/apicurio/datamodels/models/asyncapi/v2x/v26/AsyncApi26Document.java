package io.apicurio.datamodels.models.asyncapi.v2x.v26;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.RootNode;
import io.apicurio.datamodels.models.asyncapi.v2x.AsyncApi2xDocument;
import java.util.Map;

public interface AsyncApi26Document extends RootNode, AsyncApi2xDocument, AsyncApi26Extensible {

	public Map<String, JsonNode> getExtensions();

	public void addExtension(String name, JsonNode value);

	public void clearExtensions();

	public void removeExtension(String name);

	public void insertExtension(String name, JsonNode value, int atIndex);
}