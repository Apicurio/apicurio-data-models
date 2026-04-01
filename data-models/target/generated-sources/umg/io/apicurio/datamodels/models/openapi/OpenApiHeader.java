package io.apicurio.datamodels.models.openapi;

import io.apicurio.datamodels.models.Node;

public interface OpenApiHeader extends Node {

	public String getDescription();

	public void setDescription(String value);
}