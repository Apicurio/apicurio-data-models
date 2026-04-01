package io.apicurio.datamodels.models.asyncapi.v3x.v30;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.ModelType;
import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.NodeImpl;
import io.apicurio.datamodels.models.ParentPropertyType;
import io.apicurio.datamodels.models.RootNodeImpl;
import io.apicurio.datamodels.models.asyncapi.AsyncApiChannels;
import io.apicurio.datamodels.models.asyncapi.AsyncApiComponents;
import io.apicurio.datamodels.models.asyncapi.AsyncApiInfo;
import io.apicurio.datamodels.models.asyncapi.AsyncApiServers;
import io.apicurio.datamodels.models.asyncapi.v3x.AsyncApi3xOperations;
import io.apicurio.datamodels.models.asyncapi.v3x.v30.visitors.AsyncApi30Visitor;
import io.apicurio.datamodels.models.util.DataModelUtil;
import io.apicurio.datamodels.models.visitors.Visitor;
import java.util.LinkedHashMap;
import java.util.Map;

public class AsyncApi30DocumentImpl extends RootNodeImpl implements AsyncApi30Document {

	private String asyncapi;
	private String id;
	private AsyncApiInfo info;
	private AsyncApiServers servers;
	private String defaultContentType;
	private AsyncApiChannels channels;
	private AsyncApi3xOperations operations;
	private AsyncApiComponents components;
	private Map<String, JsonNode> extensions;

	public AsyncApi30DocumentImpl() {
		super(ModelType.ASYNCAPI30);
	}

	@Override
	public String getAsyncapi() {
		return asyncapi;
	}

	@Override
	public void setAsyncapi(String value) {
		this.asyncapi = value;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String value) {
		this.id = value;
	}

	@Override
	public AsyncApiInfo getInfo() {
		return info;
	}

	@Override
	public void setInfo(AsyncApiInfo value) {
		this.info = value;
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("info");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.standard);
		}
	}

	@Override
	public AsyncApi30Info createInfo() {
		AsyncApi30InfoImpl node = new AsyncApi30InfoImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public AsyncApiServers getServers() {
		return servers;
	}

	@Override
	public void setServers(AsyncApiServers value) {
		this.servers = value;
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("servers");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.standard);
		}
	}

	@Override
	public AsyncApi30Servers createServers() {
		AsyncApi30ServersImpl node = new AsyncApi30ServersImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public String getDefaultContentType() {
		return defaultContentType;
	}

	@Override
	public void setDefaultContentType(String value) {
		this.defaultContentType = value;
	}

	@Override
	public AsyncApiChannels getChannels() {
		return channels;
	}

	@Override
	public void setChannels(AsyncApiChannels value) {
		this.channels = value;
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("channels");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.standard);
		}
	}

	@Override
	public AsyncApi30Channels createChannels() {
		AsyncApi30ChannelsImpl node = new AsyncApi30ChannelsImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public AsyncApi3xOperations getOperations() {
		return operations;
	}

	@Override
	public void setOperations(AsyncApi3xOperations value) {
		this.operations = value;
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("operations");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.standard);
		}
	}

	@Override
	public AsyncApi30Operations createOperations() {
		AsyncApi30OperationsImpl node = new AsyncApi30OperationsImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public AsyncApiComponents getComponents() {
		return components;
	}

	@Override
	public void setComponents(AsyncApiComponents value) {
		this.components = value;
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("components");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.standard);
		}
	}

	@Override
	public AsyncApi30Components createComponents() {
		AsyncApi30ComponentsImpl node = new AsyncApi30ComponentsImpl();
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
		AsyncApi30Visitor viz = (AsyncApi30Visitor) visitor;
		viz.visitDocument(this);
	}

	@Override
	public Node emptyClone() {
		return new AsyncApi30DocumentImpl();
	}
}