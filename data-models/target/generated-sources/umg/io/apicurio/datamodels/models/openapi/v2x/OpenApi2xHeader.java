package io.apicurio.datamodels.models.openapi.v2x;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.openapi.OpenApiHeader;
import java.util.List;

public interface OpenApi2xHeader extends OpenApiHeader {

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

	public OpenApi2xItems getItems();

	public void setItems(OpenApi2xItems value);

	public OpenApi2xItems createItems();

	public Integer getMaxItems();

	public void setMaxItems(Integer value);

	public Number getMultipleOf();

	public void setMultipleOf(Number value);

	public Boolean isExclusiveMaximum();

	public void setExclusiveMaximum(Boolean value);

	public String getCollectionFormat();

	public void setCollectionFormat(String value);

	public Number getMinimum();

	public void setMinimum(Number value);

	public Number getMaximum();

	public void setMaximum(Number value);

	public Integer getMinItems();

	public void setMinItems(Integer value);

	public Integer getMinLength();

	public void setMinLength(Integer value);
}