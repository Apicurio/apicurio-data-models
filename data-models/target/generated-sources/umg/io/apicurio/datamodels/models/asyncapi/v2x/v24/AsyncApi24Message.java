package io.apicurio.datamodels.models.asyncapi.v2x.v24;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.asyncapi.v2x.AsyncApi2xMessage;
import java.util.Map;

public interface AsyncApi24Message extends AsyncApi2xMessage, AsyncApi24Extensible, AsyncApi24Referenceable {

	public String getMessageId();

	public void setMessageId(String value);

	public AsyncApi24MessageExample getExamples();

	public void setExamples(AsyncApi24MessageExample value);

	public AsyncApi24MessageExample createMessageExample();

	public String get$ref();

	public void set$ref(String value);

	public Map<String, JsonNode> getExtensions();

	public void addExtension(String name, JsonNode value);

	public void clearExtensions();

	public void removeExtension(String name);

	public void insertExtension(String name, JsonNode value, int atIndex);
}