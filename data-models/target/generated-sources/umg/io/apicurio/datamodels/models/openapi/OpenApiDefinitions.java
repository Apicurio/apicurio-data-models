package io.apicurio.datamodels.models.openapi;

import io.apicurio.datamodels.models.MappedNode;
import io.apicurio.datamodels.models.Node;

public interface OpenApiDefinitions extends Node, MappedNode<OpenApiSchema> {

	public OpenApiSchema createSchema();
}