package io.apicurio.datamodels.models.openrpc;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.Schema;
import io.apicurio.datamodels.models.union.SchemaSchemaListUnion;
import java.util.List;
import java.util.Map;

public interface OpenRpcSchema extends Schema, SchemaSchemaListUnion {

	public String getType();

	public void setType(String value);

	public JsonNode getConst();

	public void setConst(JsonNode value);

	public OpenRpcSchema getContains();

	public void setContains(OpenRpcSchema value);

	public OpenRpcSchema createSchema();

	public OpenRpcSchema getNot();

	public void setNot(OpenRpcSchema value);

	public Map<String, String> getPatternProperties();

	public void setPatternProperties(Map<String, String> value);

	public Number getExclusiveMaximum();

	public void setExclusiveMaximum(Number value);

	public OpenRpcSchema getElse();

	public void setElse(OpenRpcSchema value);

	public List<OpenRpcSchema> getOneOf();

	public void addOneOf(OpenRpcSchema value);

	public void clearOneOf();

	public void removeOneOf(OpenRpcSchema value);

	public void insertOneOf(OpenRpcSchema value, int atIndex);

	public List<OpenRpcSchema> getAnyOf();

	public void addAnyOf(OpenRpcSchema value);

	public void clearAnyOf();

	public void removeAnyOf(OpenRpcSchema value);

	public void insertAnyOf(OpenRpcSchema value, int atIndex);

	public OpenRpcSchema getPropertyNames();

	public void setPropertyNames(OpenRpcSchema value);

	public Boolean isDeprecated();

	public void setDeprecated(Boolean value);

	public OpenRpcSchema getThen();

	public void setThen(OpenRpcSchema value);

	public OpenRpcSchema getAdditionalItems();

	public void setAdditionalItems(OpenRpcSchema value);

	public SchemaSchemaListUnion getItems();

	public void setItems(SchemaSchemaListUnion value);

	public Boolean isWriteOnly();

	public void setWriteOnly(Boolean value);

	public Number getExclusiveMinimum();

	public void setExclusiveMinimum(Number value);

	public List<JsonNode> getExamples();

	public void setExamples(List<JsonNode> value);

	public OpenRpcSchema getIf();

	public void setIf(OpenRpcSchema value);
}