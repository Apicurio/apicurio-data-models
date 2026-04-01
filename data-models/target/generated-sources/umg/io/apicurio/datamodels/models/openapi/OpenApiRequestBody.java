package io.apicurio.datamodels.models.openapi;

import io.apicurio.datamodels.models.Node;
import java.util.Map;

public interface OpenApiRequestBody extends Node {

	public String getDescription();

	public void setDescription(String value);

	public Boolean isRequired();

	public void setRequired(Boolean value);

	public OpenApiMediaType createMediaType();

	public Map<String, OpenApiMediaType> getContent();

	public void addContent(String name, OpenApiMediaType value);

	public void clearContent();

	public void removeContent(String name);

	public void insertContent(String name, OpenApiMediaType value, int atIndex);
}