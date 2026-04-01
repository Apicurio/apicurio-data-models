package io.apicurio.datamodels.models.asyncapi.v2x.v23;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.asyncapi.v2x.AsyncApi2xMessage;
import java.util.Map;

public interface AsyncApi23Message extends AsyncApi2xMessage, AsyncApi23Extensible, AsyncApi23Referenceable {

	public AsyncApi23MessageExample getExamples();

	public void setExamples(AsyncApi23MessageExample value);

	public AsyncApi23MessageExample createMessageExample();

	public String get$ref();

	public void set$ref(String value);

	public Map<String, JsonNode> getExtensions();

	public void addExtension(String name, JsonNode value);

	public void clearExtensions();

	public void removeExtension(String name);

	public void insertExtension(String name, JsonNode value, int atIndex);
}