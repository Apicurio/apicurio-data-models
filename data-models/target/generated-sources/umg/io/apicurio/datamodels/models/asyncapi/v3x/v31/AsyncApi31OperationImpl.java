package io.apicurio.datamodels.models.asyncapi.v3x.v31;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.ExternalDocumentation;
import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.NodeImpl;
import io.apicurio.datamodels.models.ParentPropertyType;
import io.apicurio.datamodels.models.asyncapi.AsyncApiOperationBindings;
import io.apicurio.datamodels.models.asyncapi.AsyncApiOperationTrait;
import io.apicurio.datamodels.models.asyncapi.AsyncApiTag;
import io.apicurio.datamodels.models.asyncapi.v3x.AsyncApi3xOperationReply;
import io.apicurio.datamodels.models.asyncapi.v3x.AsyncApi3xReference;
import io.apicurio.datamodels.models.asyncapi.v3x.AsyncApi3xSecurityScheme;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.visitors.AsyncApi31Visitor;
import io.apicurio.datamodels.models.util.DataModelUtil;
import io.apicurio.datamodels.models.visitors.Visitor;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AsyncApi31OperationImpl extends NodeImpl implements AsyncApi31Operation {

	private String $ref;
	private String action;
	private AsyncApi3xReference channel;
	private String title;
	private String summary;
	private String description;
	private List<AsyncApi3xSecurityScheme> security;
	private List<AsyncApiTag> tags;
	private ExternalDocumentation externalDocs;
	private AsyncApiOperationBindings bindings;
	private List<AsyncApiOperationTrait> traits;
	private List<AsyncApi3xReference> messages;
	private AsyncApi3xOperationReply reply;
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
	public String getAction() {
		return action;
	}

	@Override
	public void setAction(String value) {
		this.action = value;
	}

	@Override
	public AsyncApi3xReference getChannel() {
		return channel;
	}

	@Override
	public void setChannel(AsyncApi3xReference value) {
		this.channel = value;
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("channel");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.standard);
		}
	}

	@Override
	public AsyncApi31Reference createReference() {
		AsyncApi31ReferenceImpl node = new AsyncApi31ReferenceImpl();
		node.setParent(this);
		return node;
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
	public AsyncApi31SecurityScheme createSecurityScheme() {
		AsyncApi31SecuritySchemeImpl node = new AsyncApi31SecuritySchemeImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public List<AsyncApi3xSecurityScheme> getSecurity() {
		return security;
	}

	@Override
	public void addSecurity(AsyncApi3xSecurityScheme value) {
		if (this.security == null) {
			this.security = new ArrayList<>();
		}
		this.security.add(value);
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("security");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.array);
		}
	}

	@Override
	public void clearSecurity() {
		if (this.security != null) {
			this.security.clear();
		}
	}

	@Override
	public void removeSecurity(AsyncApi3xSecurityScheme value) {
		if (this.security != null) {
			this.security.remove(value);
		}
	}

	@Override
	public void insertSecurity(AsyncApi3xSecurityScheme value, int atIndex) {
		if (this.security == null) {
			this.security = new ArrayList<>();
			this.security.add(value);
		} else {
			this.security = DataModelUtil.insertListEntry(this.security, value, atIndex);
		}
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("security");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.array);
		}
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
	public ExternalDocumentation getExternalDocs() {
		return externalDocs;
	}

	@Override
	public void setExternalDocs(ExternalDocumentation value) {
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
	public AsyncApiOperationBindings getBindings() {
		return bindings;
	}

	@Override
	public void setBindings(AsyncApiOperationBindings value) {
		this.bindings = value;
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("bindings");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.standard);
		}
	}

	@Override
	public AsyncApi31OperationBindings createOperationBindings() {
		AsyncApi31OperationBindingsImpl node = new AsyncApi31OperationBindingsImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public AsyncApi31OperationTrait createOperationTrait() {
		AsyncApi31OperationTraitImpl node = new AsyncApi31OperationTraitImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public List<AsyncApiOperationTrait> getTraits() {
		return traits;
	}

	@Override
	public void addTrait(AsyncApiOperationTrait value) {
		if (this.traits == null) {
			this.traits = new ArrayList<>();
		}
		this.traits.add(value);
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("traits");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.array);
		}
	}

	@Override
	public void clearTraits() {
		if (this.traits != null) {
			this.traits.clear();
		}
	}

	@Override
	public void removeTrait(AsyncApiOperationTrait value) {
		if (this.traits != null) {
			this.traits.remove(value);
		}
	}

	@Override
	public void insertTrait(AsyncApiOperationTrait value, int atIndex) {
		if (this.traits == null) {
			this.traits = new ArrayList<>();
			this.traits.add(value);
		} else {
			this.traits = DataModelUtil.insertListEntry(this.traits, value, atIndex);
		}
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("traits");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.array);
		}
	}

	@Override
	public List<AsyncApi3xReference> getMessages() {
		return messages;
	}

	@Override
	public void addMessage(AsyncApi3xReference value) {
		if (this.messages == null) {
			this.messages = new ArrayList<>();
		}
		this.messages.add(value);
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("messages");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.array);
		}
	}

	@Override
	public void clearMessages() {
		if (this.messages != null) {
			this.messages.clear();
		}
	}

	@Override
	public void removeMessage(AsyncApi3xReference value) {
		if (this.messages != null) {
			this.messages.remove(value);
		}
	}

	@Override
	public void insertMessage(AsyncApi3xReference value, int atIndex) {
		if (this.messages == null) {
			this.messages = new ArrayList<>();
			this.messages.add(value);
		} else {
			this.messages = DataModelUtil.insertListEntry(this.messages, value, atIndex);
		}
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("messages");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.array);
		}
	}

	@Override
	public AsyncApi3xOperationReply getReply() {
		return reply;
	}

	@Override
	public void setReply(AsyncApi3xOperationReply value) {
		this.reply = value;
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("reply");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.standard);
		}
	}

	@Override
	public AsyncApi31OperationReply createOperationReply() {
		AsyncApi31OperationReplyImpl node = new AsyncApi31OperationReplyImpl();
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
		viz.visitOperation(this);
	}

	@Override
	public Node emptyClone() {
		return new AsyncApi31OperationImpl();
	}
}