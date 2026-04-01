package io.apicurio.datamodels.models.asyncapi;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.Node;
import java.util.Map;

public interface AsyncApiMessageExample extends Node {

	public String getName();

	public void setName(String value);

	public Map<String, JsonNode> getHeaders();

	public void setHeaders(Map<String, JsonNode> value);

	public String getSummary();

	public void setSummary(String value);
}