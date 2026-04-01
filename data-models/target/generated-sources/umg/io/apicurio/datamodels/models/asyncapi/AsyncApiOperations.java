package io.apicurio.datamodels.models.asyncapi;

import io.apicurio.datamodels.models.MappedNode;
import io.apicurio.datamodels.models.Node;

public interface AsyncApiOperations extends Node, MappedNode<AsyncApiOperation> {

	public AsyncApiOperation createOperation();
}