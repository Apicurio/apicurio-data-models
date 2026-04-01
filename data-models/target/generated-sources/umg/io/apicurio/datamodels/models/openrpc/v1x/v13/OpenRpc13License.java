package io.apicurio.datamodels.models.openrpc.v1x.v13;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.openrpc.v1x.OpenRpc1xLicense;
import java.util.Map;

public interface OpenRpc13License extends OpenRpc1xLicense, OpenRpc13Extensible {

	public Map<String, JsonNode> getExtensions();

	public void addExtension(String name, JsonNode value);

	public void clearExtensions();

	public void removeExtension(String name);

	public void insertExtension(String name, JsonNode value, int atIndex);
}