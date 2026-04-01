package io.apicurio.datamodels.models.openapi.v3x.v31;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.openapi.v3x.OpenApi3xLicense;
import java.util.Map;

public interface OpenApi31License extends OpenApi3xLicense, OpenApi31Extensible {

	public String getIdentifier();

	public void setIdentifier(String value);

	public Map<String, JsonNode> getExtensions();

	public void addExtension(String name, JsonNode value);

	public void clearExtensions();

	public void removeExtension(String name);

	public void insertExtension(String name, JsonNode value, int atIndex);
}