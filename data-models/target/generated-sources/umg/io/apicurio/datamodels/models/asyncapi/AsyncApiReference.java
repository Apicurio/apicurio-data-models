package io.apicurio.datamodels.models.asyncapi;

import io.apicurio.datamodels.models.Node;

public interface AsyncApiReference extends Node {

	public String get$ref();

	public void set$ref(String value);
}