package io.apicurio.datamodels.models.openapi.v3x.v31;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.openapi.v3x.OpenApi3xComponents;
import java.util.Map;

public interface OpenApi31Components extends OpenApi3xComponents, OpenApi31Extensible {

	public OpenApi31PathItem createPathItem();

	public Map<String, OpenApi31PathItem> getPathItems();

	public void addPathItem(String name, OpenApi31PathItem value);

	public void clearPathItems();

	public void removePathItem(String name);

	public void insertPathItem(String name, OpenApi31PathItem value, int atIndex);

	public Map<String, JsonNode> getExtensions();

	public void addExtension(String name, JsonNode value);

	public void clearExtensions();

	public void removeExtension(String name);

	public void insertExtension(String name, JsonNode value, int atIndex);
}