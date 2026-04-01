package io.apicurio.datamodels.models.openapi.v2x.v20;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.openapi.OpenApiParameter;
import io.apicurio.datamodels.models.openapi.v2x.OpenApi2xPathItem;
import java.util.List;
import java.util.Map;

public interface OpenApi20PathItem
		extends
			OpenApi2xPathItem,
			OpenApi20Extensible,
			OpenApi20Referenceable,
			OpenApi20ParametersParent {

	public String get$ref();

	public void set$ref(String value);

	public OpenApi20Parameter createParameter();

	public List<OpenApiParameter> getParameters();

	public void addParameter(OpenApiParameter value);

	public void clearParameters();

	public void removeParameter(OpenApiParameter value);

	public void insertParameter(OpenApiParameter value, int atIndex);

	public Map<String, JsonNode> getExtensions();

	public void addExtension(String name, JsonNode value);

	public void clearExtensions();

	public void removeExtension(String name);

	public void insertExtension(String name, JsonNode value, int atIndex);
}