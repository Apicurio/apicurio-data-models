package io.apicurio.datamodels.models.openapi;

import io.apicurio.datamodels.models.MappedNode;
import io.apicurio.datamodels.models.Node;

public interface OpenApiSecurityDefinitions extends Node, MappedNode<OpenApiSecurityScheme> {

	public OpenApiSecurityScheme createSecurityScheme();
}