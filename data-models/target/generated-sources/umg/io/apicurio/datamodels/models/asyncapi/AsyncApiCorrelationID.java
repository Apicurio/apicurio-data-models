package io.apicurio.datamodels.models.asyncapi;

import io.apicurio.datamodels.models.Node;

public interface AsyncApiCorrelationID extends Node {

	public String getDescription();

	public void setDescription(String value);

	public String getLocation();

	public void setLocation(String value);
}