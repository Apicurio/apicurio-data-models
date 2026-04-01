package io.apicurio.datamodels.models.asyncapi.v3x.v31;

import io.apicurio.datamodels.models.asyncapi.v3x.AsyncApi3xReference;

public interface AsyncApi31Reference extends AsyncApi3xReference, AsyncApi31Referenceable {

	public String get$ref();

	public void set$ref(String value);
}