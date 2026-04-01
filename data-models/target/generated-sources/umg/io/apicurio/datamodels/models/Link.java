package io.apicurio.datamodels.models;
public interface Link extends Node {

	public String getDescription();

	public void setDescription(String value);

	public Server getServer();

	public void setServer(Server value);

	public Server createServer();
}