package io.apicurio.datamodels.models.asyncapi;

import io.apicurio.datamodels.models.Node;

public interface AsyncApiChannelItem extends Node {

	public AsyncApiOperation getPublish();

	public void setPublish(AsyncApiOperation value);

	public AsyncApiOperation createOperation();

	public String getDescription();

	public void setDescription(String value);

	public AsyncApiChannelBindings getBindings();

	public void setBindings(AsyncApiChannelBindings value);

	public AsyncApiChannelBindings createChannelBindings();

	public AsyncApiOperation getSubscribe();

	public void setSubscribe(AsyncApiOperation value);

	public AsyncApiParameters getParameters();

	public void setParameters(AsyncApiParameters value);

	public AsyncApiParameters createParameters();
}