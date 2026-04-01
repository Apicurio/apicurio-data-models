package io.apicurio.datamodels.models.openrpc;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.Node;

public interface OpenRpcError extends Node {

	public String getMessage();

	public void setMessage(String value);

	public JsonNode getData();

	public void setData(JsonNode value);

	public Integer getCode();

	public void setCode(Integer value);
}