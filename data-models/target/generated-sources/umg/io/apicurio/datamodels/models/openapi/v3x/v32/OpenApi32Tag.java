package io.apicurio.datamodels.models.openapi.v3x.v32;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.openapi.v3x.OpenApi3xTag;
import java.util.Map;

public interface OpenApi32Tag extends OpenApi3xTag, OpenApi32Extensible {

	public String getSummary();

	public void setSummary(String value);

	public String getParent();

	public void setParent(String value);

	public String getKind();

	public void setKind(String value);

	public Map<String, JsonNode> getExtensions();

	public void addExtension(String name, JsonNode value);

	public void clearExtensions();

	public void removeExtension(String name);

	public void insertExtension(String name, JsonNode value, int atIndex);
}