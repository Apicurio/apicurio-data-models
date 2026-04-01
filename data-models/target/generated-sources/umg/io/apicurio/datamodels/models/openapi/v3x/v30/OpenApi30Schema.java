package io.apicurio.datamodels.models.openapi.v3x.v30;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.openapi.v3x.OpenApi3xSchema;
import java.util.Map;

public interface OpenApi30Schema extends OpenApi3xSchema, OpenApi30Extensible, OpenApi30Referenceable {

	public Boolean isExclusiveMaximum();

	public void setExclusiveMaximum(Boolean value);

	public Boolean isExclusiveMinimum();

	public void setExclusiveMinimum(Boolean value);

	public String getType();

	public void setType(String value);

	public Boolean isNullable();

	public void setNullable(Boolean value);

	public String get$ref();

	public void set$ref(String value);

	public Map<String, JsonNode> getExtensions();

	public void addExtension(String name, JsonNode value);

	public void clearExtensions();

	public void removeExtension(String name);

	public void insertExtension(String name, JsonNode value, int atIndex);
}