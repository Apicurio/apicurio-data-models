package io.apicurio.datamodels.models.openrpc.v1x.v13;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.RootNode;
import io.apicurio.datamodels.models.openrpc.v1x.OpenRpc1xDocument;
import java.util.Map;

public interface OpenRpc13Document extends RootNode, OpenRpc1xDocument, OpenRpc13Extensible {

	public Map<String, JsonNode> getExtensions();

	public void addExtension(String name, JsonNode value);

	public void clearExtensions();

	public void removeExtension(String name);

	public void insertExtension(String name, JsonNode value, int atIndex);
}