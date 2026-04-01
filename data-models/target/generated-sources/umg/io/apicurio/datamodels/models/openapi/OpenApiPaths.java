package io.apicurio.datamodels.models.openapi;

import io.apicurio.datamodels.models.MappedNode;
import io.apicurio.datamodels.models.Node;

public interface OpenApiPaths extends Node, MappedNode<OpenApiPathItem> {

	public OpenApiPathItem createPathItem();
}