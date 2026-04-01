package io.apicurio.datamodels.models.asyncapi.v2x.v24;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.asyncapi.v2x.AsyncApi2xComponents;
import java.util.Map;

public interface AsyncApi24Components extends AsyncApi2xComponents, AsyncApi24Extensible {

	public AsyncApi24Server createServer();

	public Map<String, AsyncApi24Server> getServers();

	public void addServer(String name, AsyncApi24Server value);

	public void clearServers();

	public void removeServer(String name);

	public void insertServer(String name, AsyncApi24Server value, int atIndex);

	public AsyncApi24ServerVariable createServerVariable();

	public Map<String, AsyncApi24ServerVariable> getServerVariables();

	public void addServerVariable(String name, AsyncApi24ServerVariable value);

	public void clearServerVariables();

	public void removeServerVariable(String name);

	public void insertServerVariable(String name, AsyncApi24ServerVariable value, int atIndex);

	public AsyncApi24ChannelItem createChannelItem();

	public Map<String, AsyncApi24ChannelItem> getChannels();

	public void addChannel(String name, AsyncApi24ChannelItem value);

	public void clearChannels();

	public void removeChannel(String name);

	public void insertChannel(String name, AsyncApi24ChannelItem value, int atIndex);

	public Map<String, JsonNode> getExtensions();

	public void addExtension(String name, JsonNode value);

	public void clearExtensions();

	public void removeExtension(String name);

	public void insertExtension(String name, JsonNode value, int atIndex);
}