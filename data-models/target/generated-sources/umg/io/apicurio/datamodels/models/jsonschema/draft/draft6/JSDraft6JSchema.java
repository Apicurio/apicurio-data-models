package io.apicurio.datamodels.models.jsonschema.draft.draft6;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.jsonschema.draft.JSDraftJSchema;
import io.apicurio.datamodels.models.union.BooleanJSchemaUnion;
import java.util.List;

public interface JSDraft6JSchema extends JSDraftJSchema, JSDraft6Referenceable, BooleanJSchemaUnion {

	public List<JsonNode> getExamples();

	public void setExamples(List<JsonNode> value);

	public JsonNode getConst();

	public void setConst(JsonNode value);

	public Number getExclusiveMinimum();

	public void setExclusiveMinimum(Number value);

	public Number getExclusiveMaximum();

	public void setExclusiveMaximum(Number value);

	public BooleanJSchemaUnion getContains();

	public void setContains(BooleanJSchemaUnion value);

	public JSDraft6JSchema createJSchema();

	public BooleanJSchemaUnion getPropertyNames();

	public void setPropertyNames(BooleanJSchemaUnion value);

	public String get$ref();

	public void set$ref(String value);
}