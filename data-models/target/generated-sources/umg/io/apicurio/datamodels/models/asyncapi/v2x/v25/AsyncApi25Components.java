package io.apicurio.datamodels.models.asyncapi.v2x.v25;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.asyncapi.v2x.AsyncApi2xComponents;
import java.util.Map;

public interface AsyncApi25Components extends AsyncApi2xComponents, AsyncApi25Extensible {

	public AsyncApi25Server createServer();

	public Map<String, AsyncApi25Server> getServers();

	public void addServer(String name, AsyncApi25Server value);

	public void clearServers();

	public void removeServer(String name);

	public void insertServer(String name, AsyncApi25Server value, int atIndex);

	public AsyncApi25ServerVariable createServerVariable();

	public Map<String, AsyncApi25ServerVariable> getServerVariables();

	public void addServerVariable(String name, AsyncApi25ServerVariable value);

	public void clearServerVariables();

	public void removeServerVariable(String name);

	public void insertServerVariable(String name, AsyncApi25ServerVariable value, int atIndex);

	public AsyncApi25ChannelItem createChannelItem();

	public Map<String, AsyncApi25ChannelItem> getChannels();

	public void addChannel(String name, AsyncApi25ChannelItem value);

	public void clearChannels();

	public void removeChannel(String name);

	public void insertChannel(String name, AsyncApi25ChannelItem value, int atIndex);

	public Map<String, JsonNode> getExtensions();

	public void addExtension(String name, JsonNode value);

	public void clearExtensions();

	public void removeExtension(String name);

	public void insertExtension(String name, JsonNode value, int atIndex);
}