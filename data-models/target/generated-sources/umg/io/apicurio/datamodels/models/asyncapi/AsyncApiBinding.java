package io.apicurio.datamodels.models.asyncapi;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.MappedNode;
import io.apicurio.datamodels.models.Node;

public interface AsyncApiBinding extends Node, MappedNode<JsonNode> {
}