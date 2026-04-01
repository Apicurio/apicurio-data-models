package io.apicurio.datamodels.models.asyncapi.v3x;

import io.apicurio.datamodels.models.MappedNode;
import io.apicurio.datamodels.models.asyncapi.AsyncApiChannels;

public interface AsyncApi3xChannels extends AsyncApiChannels, MappedNode<AsyncApi3xChannel> {

	public AsyncApi3xChannel createChannel();
}