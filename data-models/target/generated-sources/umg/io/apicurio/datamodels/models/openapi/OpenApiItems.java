package io.apicurio.datamodels.models.openapi;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.Node;
import java.util.List;

public interface OpenApiItems extends Node {

	public String getType();

	public void setType(String value);

	public JsonNode getDefault();

	public void setDefault(JsonNode value);

	public String getFormat();

	public void setFormat(String value);

	public String getPattern();

	public void setPattern(String value);

	public Boolean isExclusiveMinimum();

	public void setExclusiveMinimum(Boolean value);

	public Boolean isUniqueItems();

	public void setUniqueItems(Boolean value);

	public Integer getMaxLength();

	public void setMaxLength(Integer value);

	public List<JsonNode> getEnum();

	public void setEnum(List<JsonNode> value);

	public OpenApiItems getItems();

	public void setItems(OpenApiItems value);

	public OpenApiItems createItems();

	public Integer getMaxItems();

	public void setMaxItems(Integer value);

	public Number getMultipleOf();

	public void setMultipleOf(Number value);

	public Boolean isExclusiveMaximum();

	public void setExclusiveMaximum(Boolean value);

	public String getCollectionFormat();

	public void setCollectionFormat(String value);

	public String getDescription();

	public void setDescription(String value);

	public Number getMinimum();

	public void setMinimum(Number value);

	public Number getMaximum();

	public void setMaximum(Number value);

	public Integer getMinItems();

	public void setMinItems(Integer value);

	public Integer getMinLength();

	public void setMinLength(Integer value);
}