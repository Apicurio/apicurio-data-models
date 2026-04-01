package io.apicurio.datamodels.models.openrpc;

import io.apicurio.datamodels.models.Tag;

public interface OpenRpcTag extends Tag {

	public String getSummary();

	public void setSummary(String value);
}