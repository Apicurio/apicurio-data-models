package io.apicurio.datamodels.models.asyncapi.v3x;

import io.apicurio.datamodels.models.asyncapi.AsyncApiDocument;

public interface AsyncApi3xDocument extends AsyncApiDocument {

	public AsyncApi3xOperations getOperations();

	public void setOperations(AsyncApi3xOperations value);

	public AsyncApi3xOperations createOperations();
}