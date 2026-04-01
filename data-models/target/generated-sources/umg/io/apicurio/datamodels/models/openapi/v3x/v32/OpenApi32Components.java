package io.apicurio.datamodels.models.openapi.v3x.v32;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.openapi.v3x.OpenApi3xComponents;
import java.util.Map;

public interface OpenApi32Components extends OpenApi3xComponents, OpenApi32Extensible {

	public OpenApi32PathItem createPathItem();

	public Map<String, OpenApi32PathItem> getPathItems();

	public void addPathItem(String name, OpenApi32PathItem value);

	public void clearPathItems();

	public void removePathItem(String name);

	public void insertPathItem(String name, OpenApi32PathItem value, int atIndex);

	public OpenApi32MediaType createMediaType();

	public Map<String, OpenApi32MediaType> getMediaTypes();

	public void addMediaType(String name, OpenApi32MediaType value);

	public void clearMediaTypes();

	public void removeMediaType(String name);

	public void insertMediaType(String name, OpenApi32MediaType value, int atIndex);

	public Map<String, JsonNode> getExtensions();

	public void addExtension(String name, JsonNode value);

	public void clearExtensions();

	public void removeExtension(String name);

	public void insertExtension(String name, JsonNode value, int atIndex);
}