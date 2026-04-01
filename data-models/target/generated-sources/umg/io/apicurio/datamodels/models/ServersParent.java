package io.apicurio.datamodels.models;

import java.util.List;

public interface ServersParent {

	public Server createServer();

	public List<Server> getServers();

	public void addServer(Server value);

	public void clearServers();

	public void removeServer(Server value);

	public void insertServer(Server value, int atIndex);
}