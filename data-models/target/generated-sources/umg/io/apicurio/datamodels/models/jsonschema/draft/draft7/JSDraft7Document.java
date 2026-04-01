package io.apicurio.datamodels.models.jsonschema.draft.draft7;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.RootNode;
import io.apicurio.datamodels.models.jsonschema.draft.JSDraftDocument;
import io.apicurio.datamodels.models.union.BooleanJSchemaUnion;
import java.util.List;

public interface JSDraft7Document extends RootNode, JSDraftDocument {

	public String get$id();

	public void set$id(String value);

	public String get$comment();

	public void set$comment(String value);

	public List<JsonNode> getExamples();

	public void setExamples(List<JsonNode> value);

	public Boolean isReadOnly();

	public void setReadOnly(Boolean value);

	public Boolean isWriteOnly();

	public void setWriteOnly(Boolean value);

	public JsonNode getConst();

	public void setConst(JsonNode value);

	public BooleanJSchemaUnion getIf();

	public void setIf(BooleanJSchemaUnion value);

	public JSDraft7JSchema createJSchema();

	public BooleanJSchemaUnion getThen();

	public void setThen(BooleanJSchemaUnion value);

	public BooleanJSchemaUnion getElse();

	public void setElse(BooleanJSchemaUnion value);

	public Number getExclusiveMinimum();

	public void setExclusiveMinimum(Number value);

	public Number getExclusiveMaximum();

	public void setExclusiveMaximum(Number value);

	public String getContentMediaType();

	public void setContentMediaType(String value);

	public String getContentEncoding();

	public void setContentEncoding(String value);

	public BooleanJSchemaUnion getContains();

	public void setContains(BooleanJSchemaUnion value);

	public BooleanJSchemaUnion getPropertyNames();

	public void setPropertyNames(BooleanJSchemaUnion value);
}