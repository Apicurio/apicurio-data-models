package io.apicurio.datamodels.models.asyncapi.v3x.v30;

import io.apicurio.datamodels.models.asyncapi.v3x.AsyncApi3xReference;

public interface AsyncApi30Reference extends AsyncApi3xReference, AsyncApi30Referenceable {

	public String get$ref();

	public void set$ref(String value);
}