package io.apicurio.datamodels.models.asyncapi.v3x.v31;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.asyncapi.v3x.AsyncApi3xOperationBindings;
import java.util.Map;

public interface AsyncApi31OperationBindings
		extends
			AsyncApi3xOperationBindings,
			AsyncApi31Extensible,
			AsyncApi31Referenceable {

	public AsyncApi31Binding getRos2();

	public void setRos2(AsyncApi31Binding value);

	public AsyncApi31Binding createBinding();

	public String get$ref();

	public void set$ref(String value);

	public Map<String, JsonNode> getExtensions();

	public void addExtension(String name, JsonNode value);

	public void clearExtensions();

	public void removeExtension(String name);

	public void insertExtension(String name, JsonNode value, int atIndex);
}