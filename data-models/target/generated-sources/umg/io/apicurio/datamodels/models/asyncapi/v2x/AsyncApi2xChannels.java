package io.apicurio.datamodels.models.asyncapi.v2x;

import io.apicurio.datamodels.models.MappedNode;
import io.apicurio.datamodels.models.asyncapi.AsyncApiChannels;

public interface AsyncApi2xChannels extends AsyncApiChannels, MappedNode<AsyncApi2xChannelItem> {

	public AsyncApi2xChannelItem createChannelItem();
}