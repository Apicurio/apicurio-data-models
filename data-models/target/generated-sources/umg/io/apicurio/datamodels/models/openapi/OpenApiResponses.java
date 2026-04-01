package io.apicurio.datamodels.models.openapi;

import io.apicurio.datamodels.models.MappedNode;
import io.apicurio.datamodels.models.Node;

public interface OpenApiResponses extends Node, MappedNode<OpenApiResponse> {

	public OpenApiResponse createResponse();

	public OpenApiResponse getDefault();

	public void setDefault(OpenApiResponse value);
}