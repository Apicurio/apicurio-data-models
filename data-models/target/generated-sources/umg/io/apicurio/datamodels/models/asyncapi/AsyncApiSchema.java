package io.apicurio.datamodels.models.asyncapi;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.Schema;
import io.apicurio.datamodels.models.union.AnySchemaUnion;
import io.apicurio.datamodels.models.union.SchemaSchemaListUnion;
import java.util.List;
import java.util.Map;

public interface AsyncApiSchema extends Schema, AnySchemaUnion, SchemaSchemaListUnion {

	public String getType();

	public void setType(String value);

	public JsonNode getConst();

	public void setConst(JsonNode value);

	public String getDiscriminator();

	public void setDiscriminator(String value);

	public AsyncApiSchema getContains();

	public void setContains(AsyncApiSchema value);

	public AsyncApiSchema createSchema();

	public AsyncApiSchema getNot();

	public void setNot(AsyncApiSchema value);

	public Map<String, String> getPatternProperties();

	public void setPatternProperties(Map<String, String> value);

	public Number getExclusiveMaximum();

	public void setExclusiveMaximum(Number value);

	public AsyncApiSchema getElse();

	public void setElse(AsyncApiSchema value);

	public List<AsyncApiSchema> getOneOf();

	public void addOneOf(AsyncApiSchema value);

	public void clearOneOf();

	public void removeOneOf(AsyncApiSchema value);

	public void insertOneOf(AsyncApiSchema value, int atIndex);

	public List<AsyncApiSchema> getAnyOf();

	public void addAnyOf(AsyncApiSchema value);

	public void clearAnyOf();

	public void removeAnyOf(AsyncApiSchema value);

	public void insertAnyOf(AsyncApiSchema value, int atIndex);

	public AsyncApiExternalDocumentation getExternalDocs();

	public void setExternalDocs(AsyncApiExternalDocumentation value);

	public AsyncApiExternalDocumentation createExternalDocumentation();

	public AsyncApiSchema getPropertyNames();

	public void setPropertyNames(AsyncApiSchema value);

	public Boolean isDeprecated();

	public void setDeprecated(Boolean value);

	public AsyncApiSchema getThen();

	public void setThen(AsyncApiSchema value);

	public AsyncApiSchema getAdditionalItems();

	public void setAdditionalItems(AsyncApiSchema value);

	public SchemaSchemaListUnion getItems();

	public void setItems(SchemaSchemaListUnion value);

	public Boolean isWriteOnly();

	public void setWriteOnly(Boolean value);

	public Number getExclusiveMinimum();

	public void setExclusiveMinimum(Number value);

	public List<JsonNode> getExamples();

	public void setExamples(List<JsonNode> value);

	public AsyncApiSchema getIf();

	public void setIf(AsyncApiSchema value);
}