package io.apicurio.datamodels.models.asyncapi;

import io.apicurio.datamodels.models.Server;

public interface AsyncApiServer extends Server {

	public String getProtocolVersion();

	public void setProtocolVersion(String value);

	public String getProtocol();

	public void setProtocol(String value);

	public AsyncApiServerBindings getBindings();

	public void setBindings(AsyncApiServerBindings value);

	public AsyncApiServerBindings createServerBindings();
}