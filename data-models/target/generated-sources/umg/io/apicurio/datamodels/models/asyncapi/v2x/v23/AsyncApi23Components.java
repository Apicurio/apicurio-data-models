package io.apicurio.datamodels.models.asyncapi.v2x.v23;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.asyncapi.v2x.AsyncApi2xComponents;
import java.util.Map;

public interface AsyncApi23Components extends AsyncApi2xComponents, AsyncApi23Extensible {

	public AsyncApi23Server createServer();

	public Map<String, AsyncApi23Server> getServers();

	public void addServer(String name, AsyncApi23Server value);

	public void clearServers();

	public void removeServer(String name);

	public void insertServer(String name, AsyncApi23Server value, int atIndex);

	public AsyncApi23ChannelItem createChannelItem();

	public Map<String, AsyncApi23ChannelItem> getChannels();

	public void addChannel(String name, AsyncApi23ChannelItem value);

	public void clearChannels();

	public void removeChannel(String name);

	public void insertChannel(String name, AsyncApi23ChannelItem value, int atIndex);

	public Map<String, JsonNode> getExtensions();

	public void addExtension(String name, JsonNode value);

	public void clearExtensions();

	public void removeExtension(String name);

	public void insertExtension(String name, JsonNode value, int atIndex);
}