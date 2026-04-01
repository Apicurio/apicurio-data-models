package io.apicurio.datamodels.models.asyncapi.v2x;

import io.apicurio.datamodels.models.asyncapi.AsyncApiParameter;

public interface AsyncApi2xParameter extends AsyncApiParameter {

	public AsyncApi2xSchema getSchema();

	public void setSchema(AsyncApi2xSchema value);

	public AsyncApi2xSchema createSchema();
}