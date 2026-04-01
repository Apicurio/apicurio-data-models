package io.apicurio.datamodels.models.asyncapi.v2x.v24;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.asyncapi.v2x.AsyncApi2xServerBindings;
import java.util.Map;

public interface AsyncApi24ServerBindings
		extends
			AsyncApi2xServerBindings,
			AsyncApi24Extensible,
			AsyncApi24Referenceable {

	public AsyncApi24Binding getAnypointmq();

	public void setAnypointmq(AsyncApi24Binding value);

	public AsyncApi24Binding createBinding();

	public AsyncApi24Binding getSolace();

	public void setSolace(AsyncApi24Binding value);

	public AsyncApi24Binding getMercure();

	public void setMercure(AsyncApi24Binding value);

	public AsyncApi24Binding getIbmmq();

	public void setIbmmq(AsyncApi24Binding value);

	public String get$ref();

	public void set$ref(String value);

	public Map<String, JsonNode> getExtensions();

	public void addExtension(String name, JsonNode value);

	public void clearExtensions();

	public void removeExtension(String name);

	public void insertExtension(String name, JsonNode value, int atIndex);
}