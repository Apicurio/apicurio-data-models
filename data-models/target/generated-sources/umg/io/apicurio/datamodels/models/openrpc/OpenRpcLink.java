package io.apicurio.datamodels.models.openrpc;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.Link;

public interface OpenRpcLink extends Link {

	public String getName();

	public void setName(String value);

	public JsonNode getParams();

	public void setParams(JsonNode value);

	public String getSummary();

	public void setSummary(String value);

	public String getMethod();

	public void setMethod(String value);
}