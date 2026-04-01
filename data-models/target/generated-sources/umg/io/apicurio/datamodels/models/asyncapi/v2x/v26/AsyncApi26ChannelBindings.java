package io.apicurio.datamodels.models.asyncapi.v2x.v26;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.asyncapi.v2x.AsyncApi2xChannelBindings;
import java.util.Map;

public interface AsyncApi26ChannelBindings
		extends
			AsyncApi2xChannelBindings,
			AsyncApi26Extensible,
			AsyncApi26Referenceable {

	public AsyncApi26Binding getAnypointmq();

	public void setAnypointmq(AsyncApi26Binding value);

	public AsyncApi26Binding createBinding();

	public AsyncApi26Binding getSolace();

	public void setSolace(AsyncApi26Binding value);

	public AsyncApi26Binding getMercure();

	public void setMercure(AsyncApi26Binding value);

	public AsyncApi26Binding getIbmmq();

	public void setIbmmq(AsyncApi26Binding value);

	public AsyncApi26Binding getGooglepubsub();

	public void setGooglepubsub(AsyncApi26Binding value);

	public AsyncApi26Binding getPulsar();

	public void setPulsar(AsyncApi26Binding value);

	public String get$ref();

	public void set$ref(String value);

	public Map<String, JsonNode> getExtensions();

	public void addExtension(String name, JsonNode value);

	public void clearExtensions();

	public void removeExtension(String name);

	public void insertExtension(String name, JsonNode value, int atIndex);
}