package io.apicurio.datamodels.models.asyncapi.v2x.v22;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.RootNode;
import io.apicurio.datamodels.models.asyncapi.v2x.AsyncApi2xDocument;
import java.util.Map;

public interface AsyncApi22Document extends RootNode, AsyncApi2xDocument, AsyncApi22Extensible {

	public Map<String, JsonNode> getExtensions();

	public void addExtension(String name, JsonNode value);

	public void clearExtensions();

	public void removeExtension(String name);

	public void insertExtension(String name, JsonNode value, int atIndex);
}