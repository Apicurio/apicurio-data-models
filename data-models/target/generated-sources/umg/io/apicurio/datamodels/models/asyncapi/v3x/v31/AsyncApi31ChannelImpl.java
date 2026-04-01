package io.apicurio.datamodels.models.asyncapi.v3x.v31;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.NodeImpl;
import io.apicurio.datamodels.models.ParentPropertyType;
import io.apicurio.datamodels.models.asyncapi.AsyncApiChannelBindings;
import io.apicurio.datamodels.models.asyncapi.AsyncApiExternalDocumentation;
import io.apicurio.datamodels.models.asyncapi.AsyncApiMessage;
import io.apicurio.datamodels.models.asyncapi.AsyncApiParameters;
import io.apicurio.datamodels.models.asyncapi.AsyncApiReference;
import io.apicurio.datamodels.models.asyncapi.AsyncApiTag;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.visitors.AsyncApi31Visitor;
import io.apicurio.datamodels.models.util.DataModelUtil;
import io.apicurio.datamodels.models.visitors.Visitor;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AsyncApi31ChannelImpl extends NodeImpl implements AsyncApi31Channel {

	private String $ref;
	private String address;
	private Map<String, AsyncApiMessage> messages;
	private String title;
	private String summary;
	private String description;
	private List<AsyncApiReference> servers;
	private AsyncApiParameters parameters;
	private List<AsyncApiTag> tags;
	private AsyncApiExternalDocumentation externalDocs;
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
	public String getAddress() {
		return address;
	}

	@Override
	public void setAddress(String value) {
		this.address = value;
	}

	@Override
	public AsyncApi31Message createMessage() {
		AsyncApi31MessageImpl node = new AsyncApi31MessageImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public Map<String, AsyncApiMessage> getMessages() {
		return messages;
	}

	@Override
	public void addMessage(String name, AsyncApiMessage value) {
		if (this.messages == null) {
			this.messages = new LinkedHashMap<>();
		}
		this.messages.put(name, value);
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("messages");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.map);
			((NodeImpl) value)._setMapPropertyName(name);
		}
	}

	@Override
	public void clearMessages() {
		if (this.messages != null) {
			this.messages.clear();
		}
	}

	@Override
	public void removeMessage(String name) {
		if (this.messages != null) {
			this.messages.remove(name);
		}
	}

	@Override
	public void insertMessage(String name, AsyncApiMessage value, int atIndex) {
		if (this.messages == null) {
			this.messages = new LinkedHashMap<>();
			this.messages.put(name, value);
		} else {
			this.messages = DataModelUtil.insertMapEntry(this.messages, name, value, atIndex);
		}
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("messages");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.map);
			((NodeImpl) value)._setMapPropertyName(name);
		}
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public void setTitle(String value) {
		this.title = value;
	}

	@Override
	public String getSummary() {
		return summary;
	}

	@Override
	public void setSummary(String value) {
		this.summary = value;
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
	public AsyncApi31Reference createReference() {
		AsyncApi31ReferenceImpl node = new AsyncApi31ReferenceImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public List<AsyncApiReference> getServers() {
		return servers;
	}

	@Override
	public void addServer(AsyncApiReference value) {
		if (this.servers == null) {
			this.servers = new ArrayList<>();
		}
		this.servers.add(value);
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("servers");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.array);
		}
	}

	@Override
	public void clearServers() {
		if (this.servers != null) {
			this.servers.clear();
		}
	}

	@Override
	public void removeServer(AsyncApiReference value) {
		if (this.servers != null) {
			this.servers.remove(value);
		}
	}

	@Override
	public void insertServer(AsyncApiReference value, int atIndex) {
		if (this.servers == null) {
			this.servers = new ArrayList<>();
			this.servers.add(value);
		} else {
			this.servers = DataModelUtil.insertListEntry(this.servers, value, atIndex);
		}
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("servers");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.array);
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
	public AsyncApi31Parameters createParameters() {
		AsyncApi31ParametersImpl node = new AsyncApi31ParametersImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public AsyncApi31Tag createTag() {
		AsyncApi31TagImpl node = new AsyncApi31TagImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public List<AsyncApiTag> getTags() {
		return tags;
	}

	@Override
	public void addTag(AsyncApiTag value) {
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
	public void removeTag(AsyncApiTag value) {
		if (this.tags != null) {
			this.tags.remove(value);
		}
	}

	@Override
	public void insertTag(AsyncApiTag value, int atIndex) {
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
	public AsyncApiExternalDocumentation getExternalDocs() {
		return externalDocs;
	}

	@Override
	public void setExternalDocs(AsyncApiExternalDocumentation value) {
		this.externalDocs = value;
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("externalDocs");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.standard);
		}
	}

	@Override
	public AsyncApi31ExternalDocumentation createExternalDocumentation() {
		AsyncApi31ExternalDocumentationImpl node = new AsyncApi31ExternalDocumentationImpl();
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
	public AsyncApi31ChannelBindings createChannelBindings() {
		AsyncApi31ChannelBindingsImpl node = new AsyncApi31ChannelBindingsImpl();
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
		AsyncApi31Visitor viz = (AsyncApi31Visitor) visitor;
		viz.visitChannel(this);
	}

	@Override
	public Node emptyClone() {
		return new AsyncApi31ChannelImpl();
	}
}