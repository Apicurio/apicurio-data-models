package io.apicurio.datamodels.models.openapi.v2x.v20;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.openapi.OpenApiHeader;
import io.apicurio.datamodels.models.openapi.v2x.OpenApi2xResponse;
import java.util.Map;

public interface OpenApi20Response
		extends
			OpenApi2xResponse,
			OpenApi20Extensible,
			OpenApi20Referenceable,
			OpenApi20HeadersParent {

	public String get$ref();

	public void set$ref(String value);

	public OpenApi20Header createHeader();

	public Map<String, OpenApiHeader> getHeaders();

	public void addHeader(String name, OpenApiHeader value);

	public void clearHeaders();

	public void removeHeader(String name);

	public void insertHeader(String name, OpenApiHeader value, int atIndex);

	public Map<String, JsonNode> getExtensions();

	public void addExtension(String name, JsonNode value);

	public void clearExtensions();

	public void removeExtension(String name);

	public void insertExtension(String name, JsonNode value, int atIndex);
}