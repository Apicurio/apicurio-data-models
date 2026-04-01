package io.apicurio.datamodels.models.openapi;

import io.apicurio.datamodels.models.MappedNode;
import io.apicurio.datamodels.models.Node;

public interface OpenApiParameterDefinitions extends Node, MappedNode<OpenApiParameter> {

	public OpenApiParameter createParameter();
}