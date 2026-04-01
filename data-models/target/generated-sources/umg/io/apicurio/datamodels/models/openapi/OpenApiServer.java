package io.apicurio.datamodels.models.openapi;

import io.apicurio.datamodels.models.Server;

public interface OpenApiServer extends Server {

	public String getUrl();

	public void setUrl(String value);
}