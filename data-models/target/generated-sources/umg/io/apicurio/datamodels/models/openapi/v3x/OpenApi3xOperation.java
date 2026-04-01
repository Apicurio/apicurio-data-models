package io.apicurio.datamodels.models.openapi.v3x;

import io.apicurio.datamodels.models.openapi.OpenApiOperation;
import java.util.Map;

public interface OpenApi3xOperation extends OpenApiOperation {

	public OpenApi3xRequestBody getRequestBody();

	public void setRequestBody(OpenApi3xRequestBody value);

	public OpenApi3xRequestBody createRequestBody();

	public OpenApi3xCallback createCallback();

	public Map<String, OpenApi3xCallback> getCallbacks();

	public void addCallback(String name, OpenApi3xCallback value);

	public void clearCallbacks();

	public void removeCallback(String name);

	public void insertCallback(String name, OpenApi3xCallback value, int atIndex);
}