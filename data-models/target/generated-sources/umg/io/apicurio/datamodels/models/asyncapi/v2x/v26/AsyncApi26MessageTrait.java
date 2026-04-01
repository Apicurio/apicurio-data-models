package io.apicurio.datamodels.models.asyncapi.v2x.v26;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.asyncapi.v2x.AsyncApi2xMessageTrait;
import java.util.Map;

public interface AsyncApi26MessageTrait extends AsyncApi2xMessageTrait, AsyncApi26Extensible, AsyncApi26Referenceable {

	public String getMessageId();

	public void setMessageId(String value);

	public String get$ref();

	public void set$ref(String value);

	public Map<String, JsonNode> getExtensions();

	public void addExtension(String name, JsonNode value);

	public void clearExtensions();

	public void removeExtension(String name);

	public void insertExtension(String name, JsonNode value, int atIndex);
}