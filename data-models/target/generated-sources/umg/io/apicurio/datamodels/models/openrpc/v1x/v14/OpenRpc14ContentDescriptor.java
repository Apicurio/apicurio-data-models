package io.apicurio.datamodels.models.openrpc.v1x.v14;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.openrpc.v1x.OpenRpc1xContentDescriptor;
import java.util.Map;

public interface OpenRpc14ContentDescriptor
		extends
			OpenRpc1xContentDescriptor,
			OpenRpc14Extensible,
			OpenRpc14Referenceable {

	public String get$ref();

	public void set$ref(String value);

	public Map<String, JsonNode> getExtensions();

	public void addExtension(String name, JsonNode value);

	public void clearExtensions();

	public void removeExtension(String name);

	public void insertExtension(String name, JsonNode value, int atIndex);
}