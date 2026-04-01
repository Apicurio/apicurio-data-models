package io.apicurio.datamodels.models.openapi.v3x.v32;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.openapi.v3x.OpenApi3xSecurityScheme;
import java.util.Map;

public interface OpenApi32SecurityScheme extends OpenApi3xSecurityScheme, OpenApi32Extensible, OpenApi32Referenceable {

	public String getOauth2MetadataUrl();

	public void setOauth2MetadataUrl(String value);

	public Boolean isDeprecated();

	public void setDeprecated(Boolean value);

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