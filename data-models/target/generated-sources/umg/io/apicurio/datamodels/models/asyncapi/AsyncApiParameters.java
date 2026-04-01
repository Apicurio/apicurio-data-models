package io.apicurio.datamodels.models.asyncapi;

import io.apicurio.datamodels.models.MappedNode;
import io.apicurio.datamodels.models.Node;

public interface AsyncApiParameters extends Node, MappedNode<AsyncApiParameter> {

	public AsyncApiParameter createParameter();
}