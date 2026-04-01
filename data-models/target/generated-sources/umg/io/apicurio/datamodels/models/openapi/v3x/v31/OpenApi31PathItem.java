package io.apicurio.datamodels.models.openapi.v3x.v31;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.Server;
import io.apicurio.datamodels.models.openapi.OpenApiParameter;
import io.apicurio.datamodels.models.openapi.v3x.OpenApi3xPathItem;
import java.util.List;
import java.util.Map;

public interface OpenApi31PathItem
		extends
			OpenApi3xPathItem,
			OpenApi31Extensible,
			OpenApi31Referenceable,
			OpenApi31ServersParent,
			OpenApi31ParametersParent {

	public String get$ref();

	public void set$ref(String value);

	public String getSummary();

	public void setSummary(String value);

	public String getDescription();

	public void setDescription(String value);

	public OpenApi31Server createServer();

	public List<Server> getServers();

	public void addServer(Server value);

	public void clearServers();

	public void removeServer(Server value);

	public void insertServer(Server value, int atIndex);

	public OpenApi31Parameter createParameter();

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