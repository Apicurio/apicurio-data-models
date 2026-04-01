package io.apicurio.datamodels.models.asyncapi.v2x;

import io.apicurio.datamodels.models.asyncapi.AsyncApiServer;

public interface AsyncApi2xServer extends AsyncApiServer {

	public String getUrl();

	public void setUrl(String value);
}