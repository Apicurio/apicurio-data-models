package io.apicurio.datamodels.models.asyncapi.v2x.v22;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.asyncapi.v2x.AsyncApi2xOperationBindings;
import java.util.Map;

public interface AsyncApi22OperationBindings
		extends
			AsyncApi2xOperationBindings,
			AsyncApi22Extensible,
			AsyncApi22Referenceable {

	public AsyncApi22Binding getAnypointmq();

	public void setAnypointmq(AsyncApi22Binding value);

	public AsyncApi22Binding createBinding();

	public AsyncApi22Binding getMercure();

	public void setMercure(AsyncApi22Binding value);

	public String get$ref();

	public void set$ref(String value);

	public Map<String, JsonNode> getExtensions();

	public void addExtension(String name, JsonNode value);

	public void clearExtensions();

	public void removeExtension(String name);

	public void insertExtension(String name, JsonNode value, int atIndex);
}