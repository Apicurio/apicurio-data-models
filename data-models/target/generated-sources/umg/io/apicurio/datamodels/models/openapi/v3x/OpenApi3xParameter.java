package io.apicurio.datamodels.models.openapi.v3x;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.openapi.OpenApiParameter;
import java.util.Map;

public interface OpenApi3xParameter extends OpenApiParameter {

	public Boolean isDeprecated();

	public void setDeprecated(Boolean value);

	public Boolean isExplode();

	public void setExplode(Boolean value);

	public JsonNode getExample();

	public void setExample(JsonNode value);

	public OpenApi3xMediaType createMediaType();

	public Map<String, OpenApi3xMediaType> getContent();

	public void addContent(String name, OpenApi3xMediaType value);

	public void clearContent();

	public void removeContent(String name);

	public void insertContent(String name, OpenApi3xMediaType value, int atIndex);

	public Boolean isAllowReserved();

	public void setAllowReserved(Boolean value);

	public String getStyle();

	public void setStyle(String value);
}