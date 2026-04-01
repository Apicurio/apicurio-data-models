package io.apicurio.datamodels.models.openrpc;

import io.apicurio.datamodels.models.Node;

public interface OpenRpcContentDescriptor extends Node {

	public String getName();

	public void setName(String value);

	public Boolean isDeprecated();

	public void setDeprecated(Boolean value);

	public String getDescription();

	public void setDescription(String value);

	public Boolean isRequired();

	public void setRequired(Boolean value);

	public OpenRpcSchema getSchema();

	public void setSchema(OpenRpcSchema value);

	public OpenRpcSchema createSchema();

	public String getSummary();

	public void setSummary(String value);
}