package io.apicurio.datamodels.models.openrpc.v1x.v13;

import io.apicurio.datamodels.models.openrpc.v1x.OpenRpc1xError;

public interface OpenRpc13Error extends OpenRpc1xError, OpenRpc13Referenceable {

	public String get$ref();

	public void set$ref(String value);
}