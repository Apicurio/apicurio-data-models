package io.apicurio.datamodels.models.asyncapi.v2x.v26;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.asyncapi.v2x.AsyncApi2xComponents;
import java.util.Map;

public interface AsyncApi26Components extends AsyncApi2xComponents, AsyncApi26Extensible {

	public AsyncApi26Server createServer();

	public Map<String, AsyncApi26Server> getServers();

	public void addServer(String name, AsyncApi26Server value);

	public void clearServers();

	public void removeServer(String name);

	public void insertServer(String name, AsyncApi26Server value, int atIndex);

	public AsyncApi26ServerVariable createServerVariable();

	public Map<String, AsyncApi26ServerVariable> getServerVariables();

	public void addServerVariable(String name, AsyncApi26ServerVariable value);

	public void clearServerVariables();

	public void removeServerVariable(String name);

	public void insertServerVariable(String name, AsyncApi26ServerVariable value, int atIndex);

	public AsyncApi26ChannelItem createChannelItem();

	public Map<String, AsyncApi26ChannelItem> getChannels();

	public void addChannel(String name, AsyncApi26ChannelItem value);

	public void clearChannels();

	public void removeChannel(String name);

	public void insertChannel(String name, AsyncApi26ChannelItem value, int atIndex);

	public Map<String, JsonNode> getExtensions();

	public void addExtension(String name, JsonNode value);

	public void clearExtensions();

	public void removeExtension(String name);

	public void insertExtension(String name, JsonNode value, int atIndex);
}