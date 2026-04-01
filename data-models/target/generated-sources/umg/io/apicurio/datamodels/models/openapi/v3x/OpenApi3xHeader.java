package io.apicurio.datamodels.models.openapi.v3x;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.openapi.OpenApiHeader;
import java.util.Map;

public interface OpenApi3xHeader extends OpenApiHeader {

	public Boolean isDeprecated();

	public void setDeprecated(Boolean value);

	public Boolean isAllowEmptyValue();

	public void setAllowEmptyValue(Boolean value);

	public Boolean isRequired();

	public void setRequired(Boolean value);

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

	public OpenApi3xSchema getSchema();

	public void setSchema(OpenApi3xSchema value);

	public OpenApi3xSchema createSchema();

	public Boolean isAllowReserved();

	public void setAllowReserved(Boolean value);

	public String getStyle();

	public void setStyle(String value);
}