package io.apicurio.datamodels.models.asyncapi.v2x;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.asyncapi.AsyncApiMessageTrait;
import java.util.Map;

public interface AsyncApi2xMessageTrait extends AsyncApiMessageTrait {

	public String getSchemaFormat();

	public void setSchemaFormat(String value);

	public AsyncApi2xSchema getHeaders();

	public void setHeaders(AsyncApi2xSchema value);

	public AsyncApi2xSchema createSchema();

	public Map<String, JsonNode> getExamples();

	public void setExamples(Map<String, JsonNode> value);
}