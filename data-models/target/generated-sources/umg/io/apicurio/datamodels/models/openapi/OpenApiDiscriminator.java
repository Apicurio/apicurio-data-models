package io.apicurio.datamodels.models.openapi;

import io.apicurio.datamodels.models.Node;
import java.util.Map;

public interface OpenApiDiscriminator extends Node {

	public String getPropertyName();

	public void setPropertyName(String value);

	public Map<String, String> getMapping();

	public void setMapping(Map<String, String> value);
}