package io.apicurio.datamodels.models.asyncapi.v2x.v25;

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
import io.apicurio.datamodels.models.asyncapi.v2x.AsyncApi2xExternalDocumentation;
import io.apicurio.datamodels.models.asyncapi.v2x.AsyncApi2xTag;
import io.apicurio.datamodels.models.asyncapi.v2x.v25.visitors.AsyncApi25Visitor;
import io.apicurio.datamodels.models.util.DataModelUtil;
import io.apicurio.datamodels.models.visitors.Visitor;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AsyncApi25DocumentImpl extends RootNodeImpl implements AsyncApi25Document {

	private String asyncapi;
	private String id;
	private AsyncApiInfo info;
	private AsyncApiServers servers;
	private String defaultContentType;
	private AsyncApiChannels channels;
	private AsyncApiComponents components;
	private List<AsyncApi2xTag> tags;
	private AsyncApi2xExternalDocumentation externalDocs;
	private Map<String, JsonNode> extensions;

	public AsyncApi25DocumentImpl() {
		super(ModelType.ASYNCAPI25);
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
	public AsyncApi25Info createInfo() {
		AsyncApi25InfoImpl node = new AsyncApi25InfoImpl();
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
	public AsyncApi25Servers createServers() {
		AsyncApi25ServersImpl node = new AsyncApi25ServersImpl();
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
	public AsyncApi25Channels createChannels() {
		AsyncApi25ChannelsImpl node = new AsyncApi25ChannelsImpl();
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
	public AsyncApi25Components createComponents() {
		AsyncApi25ComponentsImpl node = new AsyncApi25ComponentsImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public AsyncApi25Tag createTag() {
		AsyncApi25TagImpl node = new AsyncApi25TagImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public List<AsyncApi2xTag> getTags() {
		return tags;
	}

	@Override
	public void addTag(AsyncApi2xTag value) {
		if (this.tags == null) {
			this.tags = new ArrayList<>();
		}
		this.tags.add(value);
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("tags");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.array);
		}
	}

	@Override
	public void clearTags() {
		if (this.tags != null) {
			this.tags.clear();
		}
	}

	@Override
	public void removeTag(AsyncApi2xTag value) {
		if (this.tags != null) {
			this.tags.remove(value);
		}
	}

	@Override
	public void insertTag(AsyncApi2xTag value, int atIndex) {
		if (this.tags == null) {
			this.tags = new ArrayList<>();
			this.tags.add(value);
		} else {
			this.tags = DataModelUtil.insertListEntry(this.tags, value, atIndex);
		}
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("tags");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.array);
		}
	}

	@Override
	public AsyncApi2xExternalDocumentation getExternalDocs() {
		return externalDocs;
	}

	@Override
	public void setExternalDocs(AsyncApi2xExternalDocumentation value) {
		this.externalDocs = value;
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("externalDocs");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.standard);
		}
	}

	@Override
	public AsyncApi25ExternalDocumentation createExternalDocumentation() {
		AsyncApi25ExternalDocumentationImpl node = new AsyncApi25ExternalDocumentationImpl();
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
		viz.visitDocument(this);
	}

	@Override
	public Node emptyClone() {
		return new AsyncApi25DocumentImpl();
	}
}