package io.apicurio.datamodels.models.jsonschema;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.union.BooleanJSchemaUnion;
import io.apicurio.datamodels.models.union.StringStringListUnion;
import java.util.List;
import java.util.Map;

public interface JsonSchemaJSchema extends Node, BooleanJSchemaUnion {

	public JsonSchemaJSchema createJSchema();

	public List<BooleanJSchemaUnion> getAllOf();

	public void addAllOf(BooleanJSchemaUnion value);

	public void clearAllOf();

	public void removeAllOf(BooleanJSchemaUnion value);

	public void insertAllOf(BooleanJSchemaUnion value, int atIndex);

	public Boolean isUniqueItems();

	public void setUniqueItems(Boolean value);

	public BooleanJSchemaUnion getAdditionalProperties();

	public void setAdditionalProperties(BooleanJSchemaUnion value);

	public Map<String, BooleanJSchemaUnion> getProperties();

	public void addProperty(String name, BooleanJSchemaUnion value);

	public void clearProperties();

	public void removeProperty(String name);

	public void insertProperty(String name, BooleanJSchemaUnion value, int atIndex);

	public Number getMaximum();

	public void setMaximum(Number value);

	public Integer getMinProperties();

	public void setMinProperties(Integer value);

	public List<JsonNode> getEnum();

	public void setEnum(List<JsonNode> value);

	public String getTitle();

	public void setTitle(String value);

	public BooleanJSchemaUnion getNot();

	public void setNot(BooleanJSchemaUnion value);

	public Number getMultipleOf();

	public void setMultipleOf(Number value);

	public List<String> getRequired();

	public void setRequired(List<String> value);

	public List<BooleanJSchemaUnion> getAnyOf();

	public void addAnyOf(BooleanJSchemaUnion value);

	public void clearAnyOf();

	public void removeAnyOf(BooleanJSchemaUnion value);

	public void insertAnyOf(BooleanJSchemaUnion value, int atIndex);

	public JsonNode getDefault();

	public void setDefault(JsonNode value);

	public Integer getMaxLength();

	public void setMaxLength(Integer value);

	public Map<String, BooleanJSchemaUnion> getPatternProperties();

	public void addPatternProperty(String name, BooleanJSchemaUnion value);

	public void clearPatternProperties();

	public void removePatternProperty(String name);

	public void insertPatternProperty(String name, BooleanJSchemaUnion value, int atIndex);

	public Integer getMinItems();

	public void setMinItems(Integer value);

	public StringStringListUnion getType();

	public void setType(StringStringListUnion value);

	public String getFormat();

	public void setFormat(String value);

	public String getPattern();

	public void setPattern(String value);

	public Integer getMaxItems();

	public void setMaxItems(Integer value);

	public String getDescription();

	public void setDescription(String value);

	public Integer getMaxProperties();

	public void setMaxProperties(Integer value);

	public List<BooleanJSchemaUnion> getOneOf();

	public void addOneOf(BooleanJSchemaUnion value);

	public void clearOneOf();

	public void removeOneOf(BooleanJSchemaUnion value);

	public void insertOneOf(BooleanJSchemaUnion value, int atIndex);

	public Number getMinimum();

	public void setMinimum(Number value);

	public Integer getMinLength();

	public void setMinLength(Integer value);
}