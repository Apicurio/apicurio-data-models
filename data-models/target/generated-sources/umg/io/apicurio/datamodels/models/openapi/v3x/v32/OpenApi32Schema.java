package io.apicurio.datamodels.models.openapi.v3x.v32;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.openapi.v3x.OpenApi3xSchema;
import io.apicurio.datamodels.models.union.StringStringListUnion;
import java.util.Map;

public interface OpenApi32Schema extends OpenApi3xSchema, OpenApi32Extensible, OpenApi32Referenceable {

	public Number getExclusiveMaximum();

	public void setExclusiveMaximum(Number value);

	public Number getExclusiveMinimum();

	public void setExclusiveMinimum(Number value);

	public JsonNode getConst();

	public void setConst(JsonNode value);

	public StringStringListUnion getType();

	public void setType(StringStringListUnion value);

	public String get$ref();

	public void set$ref(String value);

	public String getSummary();

	public void setSummary(String value);

	public String getDescription();

	public void setDescription(String value);

	public Map<String, JsonNode> getExtensions();

	public void addExtension(String name, JsonNode value);

	public void clearExtensions();

	public void removeExtension(String name);

	public void insertExtension(String name, JsonNode value, int atIndex);
}