package io.apicurio.datamodels.models.asyncapi.v2x.v21;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.NodeImpl;
import io.apicurio.datamodels.models.ParentPropertyType;
import io.apicurio.datamodels.models.asyncapi.AsyncApiChannelBindings;
import io.apicurio.datamodels.models.asyncapi.AsyncApiOperation;
import io.apicurio.datamodels.models.asyncapi.AsyncApiParameters;
import io.apicurio.datamodels.models.asyncapi.v2x.v21.visitors.AsyncApi21Visitor;
import io.apicurio.datamodels.models.util.DataModelUtil;
import io.apicurio.datamodels.models.visitors.Visitor;
import java.util.LinkedHashMap;
import java.util.Map;

public class AsyncApi21ChannelItemImpl extends NodeImpl implements AsyncApi21ChannelItem {

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
	public AsyncApi21Operation createOperation() {
		AsyncApi21OperationImpl node = new AsyncApi21OperationImpl();
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
	public AsyncApi21Parameters createParameters() {
		AsyncApi21ParametersImpl node = new AsyncApi21ParametersImpl();
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
	public AsyncApi21ChannelBindings createChannelBindings() {
		AsyncApi21ChannelBindingsImpl node = new AsyncApi21ChannelBindingsImpl();
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
		AsyncApi21Visitor viz = (AsyncApi21Visitor) visitor;
		viz.visitChannelItem(this);
	}

	@Override
	public Node emptyClone() {
		return new AsyncApi21ChannelItemImpl();
	}
}