package io.apicurio.datamodels.models.openapi;

import io.apicurio.datamodels.models.Node;

public interface OpenApiPathItem extends Node {

	public OpenApiOperation getDelete();

	public void setDelete(OpenApiOperation value);

	public OpenApiOperation createOperation();

	public OpenApiOperation getPatch();

	public void setPatch(OpenApiOperation value);

	public OpenApiOperation getGet();

	public void setGet(OpenApiOperation value);

	public OpenApiOperation getPost();

	public void setPost(OpenApiOperation value);

	public OpenApiOperation getPut();

	public void setPut(OpenApiOperation value);

	public OpenApiOperation getOptions();

	public void setOptions(OpenApiOperation value);

	public OpenApiOperation getHead();

	public void setHead(OpenApiOperation value);
}