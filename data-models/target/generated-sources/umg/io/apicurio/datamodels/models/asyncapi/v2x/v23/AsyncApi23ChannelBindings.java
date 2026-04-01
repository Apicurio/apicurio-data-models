package io.apicurio.datamodels.models.asyncapi.v2x.v23;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.asyncapi.v2x.AsyncApi2xChannelBindings;
import java.util.Map;

public interface AsyncApi23ChannelBindings
		extends
			AsyncApi2xChannelBindings,
			AsyncApi23Extensible,
			AsyncApi23Referenceable {

	public AsyncApi23Binding getAnypointmq();

	public void setAnypointmq(AsyncApi23Binding value);

	public AsyncApi23Binding createBinding();

	public AsyncApi23Binding getSolace();

	public void setSolace(AsyncApi23Binding value);

	public AsyncApi23Binding getMercure();

	public void setMercure(AsyncApi23Binding value);

	public AsyncApi23Binding getIbmmq();

	public void setIbmmq(AsyncApi23Binding value);

	public String get$ref();

	public void set$ref(String value);

	public Map<String, JsonNode> getExtensions();

	public void addExtension(String name, JsonNode value);

	public void clearExtensions();

	public void removeExtension(String name);

	public void insertExtension(String name, JsonNode value, int atIndex);
}