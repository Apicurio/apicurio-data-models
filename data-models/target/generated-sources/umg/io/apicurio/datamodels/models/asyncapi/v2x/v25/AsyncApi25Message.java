package io.apicurio.datamodels.models.asyncapi.v2x.v25;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.asyncapi.v2x.AsyncApi2xMessage;
import java.util.Map;

public interface AsyncApi25Message extends AsyncApi2xMessage, AsyncApi25Extensible, AsyncApi25Referenceable {

	public String getMessageId();

	public void setMessageId(String value);

	public AsyncApi25MessageExample getExamples();

	public void setExamples(AsyncApi25MessageExample value);

	public AsyncApi25MessageExample createMessageExample();

	public String get$ref();

	public void set$ref(String value);

	public Map<String, JsonNode> getExtensions();

	public void addExtension(String name, JsonNode value);

	public void clearExtensions();

	public void removeExtension(String name);

	public void insertExtension(String name, JsonNode value, int atIndex);
}