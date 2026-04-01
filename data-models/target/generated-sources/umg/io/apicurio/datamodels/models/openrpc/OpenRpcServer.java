package io.apicurio.datamodels.models.openrpc;

import io.apicurio.datamodels.models.Server;

public interface OpenRpcServer extends Server {

	public String getName();

	public void setName(String value);

	public String getUrl();

	public void setUrl(String value);

	public String getSummary();

	public void setSummary(String value);
}