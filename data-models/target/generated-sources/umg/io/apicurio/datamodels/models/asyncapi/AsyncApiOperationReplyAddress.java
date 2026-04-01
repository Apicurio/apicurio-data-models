package io.apicurio.datamodels.models.asyncapi;

import io.apicurio.datamodels.models.Node;

public interface AsyncApiOperationReplyAddress extends Node {

	public String getDescription();

	public void setDescription(String value);

	public String getLocation();

	public void setLocation(String value);
}