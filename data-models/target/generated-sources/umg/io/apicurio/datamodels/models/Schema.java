package io.apicurio.datamodels.models;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.union.BooleanSchemaUnion;
import java.util.List;
import java.util.Map;

public interface Schema extends Node, BooleanSchemaUnion {

	public JsonNode getDefault();

	public void setDefault(JsonNode value);

	public Boolean isUniqueItems();

	public void setUniqueItems(Boolean value);

	public Boolean isReadOnly();

	public void setReadOnly(Boolean value);

	public Integer getMaxLength();

	public void setMaxLength(Integer value);

	public Number getMaximum();

	public void setMaximum(Number value);

	public Integer getMinItems();

	public void setMinItems(Integer value);

	public BooleanSchemaUnion getAdditionalProperties();

	public void setAdditionalProperties(BooleanSchemaUnion value);

	public Schema createSchema();

	public String getFormat();

	public void setFormat(String value);

	public Integer getMinProperties();

	public void setMinProperties(Integer value);

	public String getPattern();

	public void setPattern(String value);

	public List<Schema> getAllOf();

	public void addAllOf(Schema value);

	public void clearAllOf();

	public void removeAllOf(Schema value);

	public void insertAllOf(Schema value, int atIndex);

	public Map<String, Schema> getProperties();

	public void addProperty(String name, Schema value);

	public void clearProperties();

	public void removeProperty(String name);

	public void insertProperty(String name, Schema value, int atIndex);

	public List<JsonNode> getEnum();

	public void setEnum(List<JsonNode> value);

	public String getTitle();

	public void setTitle(String value);

	public Number getMultipleOf();

	public void setMultipleOf(Number value);

	public Integer getMaxItems();

	public void setMaxItems(Integer value);

	public List<String> getRequired();

	public void setRequired(List<String> value);

	public String getDescription();

	public void setDescription(String value);

	public Integer getMaxProperties();

	public void setMaxProperties(Integer value);

	public Number getMinimum();

	public void setMinimum(Number value);

	public Integer getMinLength();

	public void setMinLength(Integer value);
}