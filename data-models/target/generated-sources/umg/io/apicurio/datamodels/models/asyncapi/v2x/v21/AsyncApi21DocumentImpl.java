package io.apicurio.datamodels.models.asyncapi.v2x.v21;

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
import io.apicurio.datamodels.models.asyncapi.v2x.v21.visitors.AsyncApi21Visitor;
import io.apicurio.datamodels.models.util.DataModelUtil;
import io.apicurio.datamodels.models.visitors.Visitor;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AsyncApi21DocumentImpl extends RootNodeImpl implements AsyncApi21Document {

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

	public AsyncApi21DocumentImpl() {
		super(ModelType.ASYNCAPI21);
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
	public AsyncApi21Info createInfo() {
		AsyncApi21InfoImpl node = new AsyncApi21InfoImpl();
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
	public AsyncApi21Servers createServers() {
		AsyncApi21ServersImpl node = new AsyncApi21ServersImpl();
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
	public AsyncApi21Channels createChannels() {
		AsyncApi21ChannelsImpl node = new AsyncApi21ChannelsImpl();
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
	public AsyncApi21Components createComponents() {
		AsyncApi21ComponentsImpl node = new AsyncApi21ComponentsImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public AsyncApi21Tag createTag() {
		AsyncApi21TagImpl node = new AsyncApi21TagImpl();
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
	public AsyncApi21ExternalDocumentation createExternalDocumentation() {
		AsyncApi21ExternalDocumentationImpl node = new AsyncApi21ExternalDocumentationImpl();
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
		viz.visitDocument(this);
	}

	@Override
	public Node emptyClone() {
		return new AsyncApi21DocumentImpl();
	}
}