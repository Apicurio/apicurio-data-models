package io.apicurio.datamodels.models.asyncapi;

import io.apicurio.datamodels.models.Document;

public interface AsyncApiDocument extends Document {

	public AsyncApiInfo getInfo();

	public void setInfo(AsyncApiInfo value);

	public AsyncApiInfo createInfo();

	public String getId();

	public void setId(String value);

	public AsyncApiComponents getComponents();

	public void setComponents(AsyncApiComponents value);

	public AsyncApiComponents createComponents();

	public String getAsyncapi();

	public void setAsyncapi(String value);

	public AsyncApiServers getServers();

	public void setServers(AsyncApiServers value);

	public AsyncApiServers createServers();

	public AsyncApiChannels getChannels();

	public void setChannels(AsyncApiChannels value);

	public AsyncApiChannels createChannels();

	public String getDefaultContentType();

	public void setDefaultContentType(String value);
}