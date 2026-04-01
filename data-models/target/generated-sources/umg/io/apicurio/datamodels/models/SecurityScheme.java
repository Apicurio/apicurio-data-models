package io.apicurio.datamodels.models;
public interface SecurityScheme extends Node {

	public String getIn();

	public void setIn(String value);

	public String getType();

	public void setType(String value);

	public String getName();

	public void setName(String value);

	public String getDescription();

	public void setDescription(String value);
}