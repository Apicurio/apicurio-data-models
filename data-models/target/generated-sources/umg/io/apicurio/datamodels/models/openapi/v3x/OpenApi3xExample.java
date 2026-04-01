package io.apicurio.datamodels.models.openapi.v3x;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.openapi.OpenApiExample;

public interface OpenApi3xExample extends OpenApiExample {

	public String getDescription();

	public void setDescription(String value);

	public String getExternalValue();

	public void setExternalValue(String value);

	public String getSummary();

	public void setSummary(String value);

	public JsonNode getValue();

	public void setValue(JsonNode value);
}