package io.apicurio.datamodels.models.asyncapi.v2x;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.asyncapi.AsyncApiMessageExample;

public interface AsyncApi2xMessageExample extends AsyncApiMessageExample {

	public JsonNode getPayload();

	public void setPayload(JsonNode value);
}