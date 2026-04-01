package io.apicurio.datamodels.models.openrpc.v1x.v14;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.openrpc.v1x.OpenRpc1xServerVariable;
import java.util.Map;

public interface OpenRpc14ServerVariable extends OpenRpc1xServerVariable, OpenRpc14Extensible {

	public Map<String, JsonNode> getExtensions();

	public void addExtension(String name, JsonNode value);

	public void clearExtensions();

	public void removeExtension(String name);

	public void insertExtension(String name, JsonNode value, int atIndex);
}