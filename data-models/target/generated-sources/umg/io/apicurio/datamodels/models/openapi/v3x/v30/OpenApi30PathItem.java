package io.apicurio.datamodels.models.openapi.v3x.v30;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.Server;
import io.apicurio.datamodels.models.openapi.OpenApiParameter;
import io.apicurio.datamodels.models.openapi.v3x.OpenApi3xPathItem;
import java.util.List;
import java.util.Map;

public interface OpenApi30PathItem
		extends
			OpenApi3xPathItem,
			OpenApi30Extensible,
			OpenApi30Referenceable,
			OpenApi30ServersParent,
			OpenApi30ParametersParent {

	public String get$ref();

	public void set$ref(String value);

	public OpenApi30Server createServer();

	public List<Server> getServers();

	public void addServer(Server value);

	public void clearServers();

	public void removeServer(Server value);

	public void insertServer(Server value, int atIndex);

	public OpenApi30Parameter createParameter();

	public List<OpenApiParameter> getParameters();

	public void addParameter(OpenApiParameter value);

	public void clearParameters();

	public void removeParameter(OpenApiParameter value);

	public void insertParameter(OpenApiParameter value, int atIndex);

	public Map<String, JsonNode> getExtensions();

	public void addExtension(String name, JsonNode value);

	public void clearExtensions();

	public void removeExtension(String name);

	public void insertExtension(String name, JsonNode value, int atIndex);
}