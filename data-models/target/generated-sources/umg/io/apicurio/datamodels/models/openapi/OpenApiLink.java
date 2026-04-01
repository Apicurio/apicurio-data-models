package io.apicurio.datamodels.models.openapi;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.Link;
import java.util.Map;

public interface OpenApiLink extends Link {

	public String getOperationId();

	public void setOperationId(String value);

	public String getOperationRef();

	public void setOperationRef(String value);

	public JsonNode getRequestBody();

	public void setRequestBody(JsonNode value);

	public Map<String, JsonNode> getParameters();

	public void setParameters(Map<String, JsonNode> value);
}