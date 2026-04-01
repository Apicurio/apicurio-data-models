package io.apicurio.datamodels.models.openapi.v3x.v30;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.openapi.OpenApiHeader;
import io.apicurio.datamodels.models.openapi.v3x.OpenApi3xResponse;
import java.util.Map;

public interface OpenApi30Response
		extends
			OpenApi3xResponse,
			OpenApi30Extensible,
			OpenApi30Referenceable,
			OpenApi30HeadersParent {

	public String get$ref();

	public void set$ref(String value);

	public OpenApi30Header createHeader();

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