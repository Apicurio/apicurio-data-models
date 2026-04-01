package io.apicurio.datamodels.models.jsonschema.draft.draft6;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.RootNode;
import io.apicurio.datamodels.models.jsonschema.draft.JSDraftDocument;
import io.apicurio.datamodels.models.union.BooleanJSchemaUnion;
import java.util.List;

public interface JSDraft6Document extends RootNode, JSDraftDocument {

	public String get$id();

	public void set$id(String value);

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
}