package io.apicurio.datamodels.models.asyncapi.v2x;

import io.apicurio.datamodels.models.asyncapi.AsyncApiOperation;

public interface AsyncApi2xOperation extends AsyncApiOperation {

	public String getOperationId();

	public void setOperationId(String value);

	public AsyncApi2xMessage getMessage();

	public void setMessage(AsyncApi2xMessage value);

	public AsyncApi2xMessage createMessage();
}