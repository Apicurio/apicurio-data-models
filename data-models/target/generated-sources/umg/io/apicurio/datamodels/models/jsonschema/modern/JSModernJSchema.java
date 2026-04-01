package io.apicurio.datamodels.models.jsonschema.modern;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.jsonschema.JsonSchemaJSchema;
import io.apicurio.datamodels.models.union.BooleanJSchemaUnion;
import java.util.List;
import java.util.Map;

public interface JSModernJSchema extends JsonSchemaJSchema, BooleanJSchemaUnion {

	public BooleanJSchemaUnion getUnevaluatedProperties();

	public void setUnevaluatedProperties(BooleanJSchemaUnion value);

	public JSModernJSchema createJSchema();

	public BooleanJSchemaUnion getPropertyNames();

	public void setPropertyNames(BooleanJSchemaUnion value);

	public Boolean isReadOnly();

	public void setReadOnly(Boolean value);

	public Number getExclusiveMaximum();

	public void setExclusiveMaximum(Number value);

	public BooleanJSchemaUnion getUnevaluatedItems();

	public void setUnevaluatedItems(BooleanJSchemaUnion value);

	public Integer getMaxContains();

	public void setMaxContains(Integer value);

	public Map<String, BooleanJSchemaUnion> get$defs();

	public void add$def(String name, BooleanJSchemaUnion value);

	public void clear$defs();

	public void remove$def(String name);

	public void insert$def(String name, BooleanJSchemaUnion value, int atIndex);

	public Boolean isDeprecated();

	public void setDeprecated(Boolean value);

	public String getContentEncoding();

	public void setContentEncoding(String value);

	public String get$anchor();

	public void set$anchor(String value);

	public Boolean isWriteOnly();

	public void setWriteOnly(Boolean value);

	public Number getExclusiveMinimum();

	public void setExclusiveMinimum(Number value);

	public Map<String, JsonNode> getDependentRequired();

	public void setDependentRequired(Map<String, JsonNode> value);

	public BooleanJSchemaUnion getElse();

	public void setElse(BooleanJSchemaUnion value);

	public JsonNode getConst();

	public void setConst(JsonNode value);

	public BooleanJSchemaUnion getThen();

	public void setThen(BooleanJSchemaUnion value);

	public BooleanJSchemaUnion getIf();

	public void setIf(BooleanJSchemaUnion value);

	public BooleanJSchemaUnion getContains();

	public void setContains(BooleanJSchemaUnion value);

	public String getContentMediaType();

	public void setContentMediaType(String value);

	public BooleanJSchemaUnion getContentSchema();

	public void setContentSchema(BooleanJSchemaUnion value);

	public Map<String, BooleanJSchemaUnion> getDependentSchemas();

	public void addDependentSchema(String name, BooleanJSchemaUnion value);

	public void clearDependentSchemas();

	public void removeDependentSchema(String name);

	public void insertDependentSchema(String name, BooleanJSchemaUnion value, int atIndex);

	public Integer getMinContains();

	public void setMinContains(Integer value);

	public String get$comment();

	public void set$comment(String value);

	public List<JsonNode> getExamples();

	public void setExamples(List<JsonNode> value);
}