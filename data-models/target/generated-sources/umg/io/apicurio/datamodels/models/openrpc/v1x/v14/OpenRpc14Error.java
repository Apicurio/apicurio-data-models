package io.apicurio.datamodels.models.openrpc.v1x.v14;

import io.apicurio.datamodels.models.openrpc.v1x.OpenRpc1xError;

public interface OpenRpc14Error extends OpenRpc1xError, OpenRpc14Referenceable {

	public String get$ref();

	public void set$ref(String value);
}