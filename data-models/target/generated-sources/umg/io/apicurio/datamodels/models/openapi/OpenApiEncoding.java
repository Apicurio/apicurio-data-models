package io.apicurio.datamodels.models.openapi;

import io.apicurio.datamodels.models.Node;

public interface OpenApiEncoding extends Node {

	public Boolean isExplode();

	public void setExplode(Boolean value);

	public String getContentType();

	public void setContentType(String value);

	public Boolean isAllowReserved();

	public void setAllowReserved(Boolean value);

	public String getStyle();

	public void setStyle(String value);
}