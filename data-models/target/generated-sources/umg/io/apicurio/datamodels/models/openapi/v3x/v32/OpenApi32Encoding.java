package io.apicurio.datamodels.models.openapi.v3x.v32;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.openapi.OpenApiHeader;
import io.apicurio.datamodels.models.openapi.v3x.OpenApi3xEncoding;
import java.util.Map;

public interface OpenApi32Encoding extends OpenApi3xEncoding, OpenApi32Extensible, OpenApi32HeadersParent {

	public OpenApi32Header createHeader();

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