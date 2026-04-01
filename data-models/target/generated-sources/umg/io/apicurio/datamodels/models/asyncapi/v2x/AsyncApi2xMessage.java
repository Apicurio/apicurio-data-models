package io.apicurio.datamodels.models.asyncapi.v2x;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.asyncapi.AsyncApiMessage;
import java.util.List;

public interface AsyncApi2xMessage extends AsyncApiMessage {

	public String getSchemaFormat();

	public void setSchemaFormat(String value);

	public AsyncApi2xSchema getHeaders();

	public void setHeaders(AsyncApi2xSchema value);

	public AsyncApi2xSchema createSchema();

	public JsonNode getPayload();

	public void setPayload(JsonNode value);

	public AsyncApi2xMessage createMessage();

	public List<AsyncApi2xMessage> getOneOf();

	public void addOneOf(AsyncApi2xMessage value);

	public void clearOneOf();

	public void removeOneOf(AsyncApi2xMessage value);

	public void insertOneOf(AsyncApi2xMessage value, int atIndex);
}