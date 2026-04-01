package io.apicurio.datamodels.models.asyncapi.v2x.v25;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.NodeImpl;
import io.apicurio.datamodels.models.ParentPropertyType;
import io.apicurio.datamodels.models.asyncapi.AsyncApiChannelBindings;
import io.apicurio.datamodels.models.asyncapi.AsyncApiOperation;
import io.apicurio.datamodels.models.asyncapi.AsyncApiParameters;
import io.apicurio.datamodels.models.asyncapi.v2x.v25.visitors.AsyncApi25Visitor;
import io.apicurio.datamodels.models.util.DataModelUtil;
import io.apicurio.datamodels.models.visitors.Visitor;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AsyncApi25ChannelItemImpl extends NodeImpl implements AsyncApi25ChannelItem {

	private String $ref;
	private String description;
	private List<String> servers;
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
	public List<String> getServers() {
		return servers;
	}

	@Override
	public void setServers(List<String> value) {
		this.servers = value;
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
	public AsyncApi25Operation createOperation() {
		AsyncApi25OperationImpl node = new AsyncApi25OperationImpl();
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
	public AsyncApi25Parameters createParameters() {
		AsyncApi25ParametersImpl node = new AsyncApi25ParametersImpl();
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
	public AsyncApi25ChannelBindings createChannelBindings() {
		AsyncApi25ChannelBindingsImpl node = new AsyncApi25ChannelBindingsImpl();
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
		AsyncApi25Visitor viz = (AsyncApi25Visitor) visitor;
		viz.visitChannelItem(this);
	}

	@Override
	public Node emptyClone() {
		return new AsyncApi25ChannelItemImpl();
	}
}