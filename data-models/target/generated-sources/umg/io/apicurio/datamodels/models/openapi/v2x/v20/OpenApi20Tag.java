package io.apicurio.datamodels.models.openapi.v2x.v20;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.openapi.v2x.OpenApi2xTag;
import java.util.Map;

public interface OpenApi20Tag extends OpenApi2xTag, OpenApi20Extensible {

	public Map<String, JsonNode> getExtensions();

	public void addExtension(String name, JsonNode value);

	public void clearExtensions();

	public void removeExtension(String name);

	public void insertExtension(String name, JsonNode value, int atIndex);
}