package io.apicurio.datamodels.models.asyncapi.v2x;

import io.apicurio.datamodels.models.asyncapi.AsyncApiComponents;
import java.util.Map;

public interface AsyncApi2xComponents extends AsyncApiComponents {

	public AsyncApi2xSchema createSchema();

	public Map<String, AsyncApi2xSchema> getSchemas();

	public void addSchema(String name, AsyncApi2xSchema value);

	public void clearSchemas();

	public void removeSchema(String name);

	public void insertSchema(String name, AsyncApi2xSchema value, int atIndex);
}