package io.apicurio.datamodels.models.asyncapi.v2x.v25;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.asyncapi.v2x.AsyncApi2xMessageBindings;
import java.util.Map;

public interface AsyncApi25MessageBindings
		extends
			AsyncApi2xMessageBindings,
			AsyncApi25Extensible,
			AsyncApi25Referenceable {

	public AsyncApi25Binding getAnypointmq();

	public void setAnypointmq(AsyncApi25Binding value);

	public AsyncApi25Binding createBinding();

	public AsyncApi25Binding getSolace();

	public void setSolace(AsyncApi25Binding value);

	public AsyncApi25Binding getMercure();

	public void setMercure(AsyncApi25Binding value);

	public AsyncApi25Binding getIbmmq();

	public void setIbmmq(AsyncApi25Binding value);

	public AsyncApi25Binding getGooglepubsub();

	public void setGooglepubsub(AsyncApi25Binding value);

	public String get$ref();

	public void set$ref(String value);

	public Map<String, JsonNode> getExtensions();

	public void addExtension(String name, JsonNode value);

	public void clearExtensions();

	public void removeExtension(String name);

	public void insertExtension(String name, JsonNode value, int atIndex);
}