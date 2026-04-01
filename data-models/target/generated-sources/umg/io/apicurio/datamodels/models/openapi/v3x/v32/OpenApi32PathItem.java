package io.apicurio.datamodels.models.openapi.v3x.v32;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.Server;
import io.apicurio.datamodels.models.openapi.OpenApiParameter;
import io.apicurio.datamodels.models.openapi.v3x.OpenApi3xPathItem;
import java.util.List;
import java.util.Map;

public interface OpenApi32PathItem
		extends
			OpenApi3xPathItem,
			OpenApi32Extensible,
			OpenApi32Referenceable,
			OpenApi32ServersParent,
			OpenApi32ParametersParent {

	public OpenApi32Operation getQuery();

	public void setQuery(OpenApi32Operation value);

	public OpenApi32Operation createOperation();

	public Map<String, OpenApi32Operation> getAdditionalOperations();

	public void addAdditionalOperation(String name, OpenApi32Operation value);

	public void clearAdditionalOperations();

	public void removeAdditionalOperation(String name);

	public void insertAdditionalOperation(String name, OpenApi32Operation value, int atIndex);

	public String get$ref();

	public void set$ref(String value);

	public String getSummary();

	public void setSummary(String value);

	public String getDescription();

	public void setDescription(String value);

	public OpenApi32Server createServer();

	public List<Server> getServers();

	public void addServer(Server value);

	public void clearServers();

	public void removeServer(Server value);

	public void insertServer(Server value, int atIndex);

	public OpenApi32Parameter createParameter();

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