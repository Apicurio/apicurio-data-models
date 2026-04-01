package io.apicurio.datamodels.models.openapi.v3x.v30;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.openapi.v3x.OpenApi3xExternalDocumentation;
import java.util.Map;

public interface OpenApi30ExternalDocumentation extends OpenApi3xExternalDocumentation, OpenApi30Extensible {

	public Map<String, JsonNode> getExtensions();

	public void addExtension(String name, JsonNode value);

	public void clearExtensions();

	public void removeExtension(String name);

	public void insertExtension(String name, JsonNode value, int atIndex);
}