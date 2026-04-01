package io.apicurio.datamodels.models.openrpc;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.Example;

public interface OpenRpcExample extends Example {

	public String getName();

	public void setName(String value);

	public String getDescription();

	public void setDescription(String value);

	public String getExternalValue();

	public void setExternalValue(String value);

	public String getSummary();

	public void setSummary(String value);

	public JsonNode getValue();

	public void setValue(JsonNode value);
}