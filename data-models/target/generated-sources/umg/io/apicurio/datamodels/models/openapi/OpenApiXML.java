package io.apicurio.datamodels.models.openapi;

import io.apicurio.datamodels.models.Node;

public interface OpenApiXML extends Node {

	public String getName();

	public void setName(String value);

	public String getNamespace();

	public void setNamespace(String value);

	public String getPrefix();

	public void setPrefix(String value);

	public Boolean isAttribute();

	public void setAttribute(Boolean value);

	public Boolean isWrapped();

	public void setWrapped(Boolean value);
}