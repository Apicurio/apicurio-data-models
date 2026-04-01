package io.apicurio.datamodels.models.asyncapi;

import io.apicurio.datamodels.models.Parameter;

public interface AsyncApiParameter extends Parameter {

	public String getLocation();

	public void setLocation(String value);
}