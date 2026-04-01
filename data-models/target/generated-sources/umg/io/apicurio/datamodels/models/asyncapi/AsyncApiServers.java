package io.apicurio.datamodels.models.asyncapi;

import io.apicurio.datamodels.models.MappedNode;
import io.apicurio.datamodels.models.Node;

public interface AsyncApiServers extends Node, MappedNode<AsyncApiServer> {

	public AsyncApiServer createServer();
}