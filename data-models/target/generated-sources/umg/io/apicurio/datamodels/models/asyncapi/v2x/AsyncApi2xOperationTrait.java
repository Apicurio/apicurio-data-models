package io.apicurio.datamodels.models.asyncapi.v2x;

import io.apicurio.datamodels.models.asyncapi.AsyncApiOperationTrait;

public interface AsyncApi2xOperationTrait extends AsyncApiOperationTrait {

	public String getOperationId();

	public void setOperationId(String value);
}