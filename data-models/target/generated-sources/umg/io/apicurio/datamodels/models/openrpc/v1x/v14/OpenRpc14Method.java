package io.apicurio.datamodels.models.openrpc.v1x.v14;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.Server;
import io.apicurio.datamodels.models.openrpc.v1x.OpenRpc1xMethod;
import java.util.List;
import java.util.Map;

public interface OpenRpc14Method
		extends
			OpenRpc1xMethod,
			OpenRpc14Extensible,
			OpenRpc14Referenceable,
			OpenRpc14ServersParent {

	public String get$ref();

	public void set$ref(String value);

	public OpenRpc14Server createServer();

	public List<Server> getServers();

	public void addServer(Server value);

	public void clearServers();

	public void removeServer(Server value);

	public void insertServer(Server value, int atIndex);

	public Map<String, JsonNode> getExtensions();

	public void addExtension(String name, JsonNode value);

	public void clearExtensions();

	public void removeExtension(String name);

	public void insertExtension(String name, JsonNode value, int atIndex);
}