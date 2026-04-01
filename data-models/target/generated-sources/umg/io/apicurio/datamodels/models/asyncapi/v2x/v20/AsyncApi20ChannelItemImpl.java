package io.apicurio.datamodels.models.asyncapi.v2x.v20;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.NodeImpl;
import io.apicurio.datamodels.models.ParentPropertyType;
import io.apicurio.datamodels.models.asyncapi.AsyncApiChannelBindings;
import io.apicurio.datamodels.models.asyncapi.AsyncApiOperation;
import io.apicurio.datamodels.models.asyncapi.AsyncApiParameters;
import io.apicurio.datamodels.models.asyncapi.v2x.v20.visitors.AsyncApi20Visitor;
import io.apicurio.datamodels.models.util.DataModelUtil;
import io.apicurio.datamodels.models.visitors.Visitor;
import java.util.LinkedHashMap;
import java.util.Map;

public class AsyncApi20ChannelItemImpl extends NodeImpl implements AsyncApi20ChannelItem {

	private String $ref;
	private String description;
	private AsyncApiOperation subscribe;
	private AsyncApiOperation publish;
	private AsyncApiParameters parameters;
	private AsyncApiChannelBindings bindings;
	private Map<String, JsonNode> extensions;

	@Override
	public String get$ref() {
		return $ref;
	}

	@Override
	public void set$ref(String value) {
		this.$ref = value;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public void setDescription(String value) {
		this.description = value;
	}

	@Override
	public AsyncApiOperation getSubscribe() {
		return subscribe;
	}

	@Override
	public void setSubscribe(AsyncApiOperation value) {
		this.subscribe = value;
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("subscribe");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.standard);
		}
	}

	@Override
	public AsyncApi20Operation createOperation() {
		AsyncApi20OperationImpl node = new AsyncApi20OperationImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public AsyncApiOperation getPublish() {
		return publish;
	}

	@Override
	public void setPublish(AsyncApiOperation value) {
		this.publish = value;
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("publish");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.standard);
		}
	}

	@Override
	public AsyncApiParameters getParameters() {
		return parameters;
	}

	@Override
	public void setParameters(AsyncApiParameters value) {
		this.parameters = value;
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("parameters");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.standard);
		}
	}

	@Override
	public AsyncApi20Parameters createParameters() {
		AsyncApi20ParametersImpl node = new AsyncApi20ParametersImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public AsyncApiChannelBindings getBindings() {
		return bindings;
	}

	@Override
	public void setBindings(AsyncApiChannelBindings value) {
		this.bindings = value;
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("bindings");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.standard);
		}
	}

	@Override
	public AsyncApi20ChannelBindings createChannelBindings() {
		AsyncApi20ChannelBindingsImpl node = new AsyncApi20ChannelBindingsImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public Map<String, JsonNode> getExtensions() {
		return extensions;
	}

	@Override
	public void addExtension(String name, JsonNode value) {
		if (this.extensions == null) {
			this.extensions = new LinkedHashMap<>();
		}
		this.extensions.put(name, value);
	}

	@Override
	public void clearExtensions() {
		if (this.extensions != null) {
			this.extensions.clear();
		}
	}

	@Override
	public void removeExtension(String name) {
		if (this.extensions != null) {
			this.extensions.remove(name);
		}
	}

	@Override
	public void insertExtension(String name, JsonNode value, int atIndex) {
		if (this.extensions == null) {
			this.extensions = new LinkedHashMap<>();
			this.extensions.put(name, value);
		} else {
			this.extensions = DataModelUtil.insertMapEntry(this.extensions, name, value, atIndex);
		}
	}

	@Override
	public void accept(Visitor visitor) {
		AsyncApi20Visitor viz = (AsyncApi20Visitor) visitor;
		viz.visitChannelItem(this);
	}

	@Override
	public Node emptyClone() {
		return new AsyncApi20ChannelItemImpl();
	}
}