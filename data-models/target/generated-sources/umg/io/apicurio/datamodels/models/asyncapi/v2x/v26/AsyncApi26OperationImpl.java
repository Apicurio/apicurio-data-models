package io.apicurio.datamodels.models.asyncapi.v2x.v26;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.ExternalDocumentation;
import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.NodeImpl;
import io.apicurio.datamodels.models.ParentPropertyType;
import io.apicurio.datamodels.models.asyncapi.AsyncApiOperationBindings;
import io.apicurio.datamodels.models.asyncapi.AsyncApiOperationTrait;
import io.apicurio.datamodels.models.asyncapi.AsyncApiTag;
import io.apicurio.datamodels.models.asyncapi.v2x.AsyncApi2xMessage;
import io.apicurio.datamodels.models.asyncapi.v2x.v26.visitors.AsyncApi26Visitor;
import io.apicurio.datamodels.models.util.DataModelUtil;
import io.apicurio.datamodels.models.visitors.Visitor;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AsyncApi26OperationImpl extends NodeImpl implements AsyncApi26Operation {

	private String operationId;
	private String summary;
	private String description;
	private List<AsyncApi26SecurityRequirement> security;
	private List<AsyncApiTag> tags;
	private ExternalDocumentation externalDocs;
	private AsyncApiOperationBindings bindings;
	private List<AsyncApiOperationTrait> traits;
	private AsyncApi2xMessage message;
	private Map<String, JsonNode> extensions;

	@Override
	public String getOperationId() {
		return operationId;
	}

	@Override
	public void setOperationId(String value) {
		this.operationId = value;
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
	public AsyncApi26SecurityRequirement createSecurityRequirement() {
		AsyncApi26SecurityRequirementImpl node = new AsyncApi26SecurityRequirementImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public List<AsyncApi26SecurityRequirement> getSecurity() {
		return security;
	}

	@Override
	public void addSecurity(AsyncApi26SecurityRequirement value) {
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
	public void removeSecurity(AsyncApi26SecurityRequirement value) {
		if (this.security != null) {
			this.security.remove(value);
		}
	}

	@Override
	public void insertSecurity(AsyncApi26SecurityRequirement value, int atIndex) {
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
	public AsyncApi26Tag createTag() {
		AsyncApi26TagImpl node = new AsyncApi26TagImpl();
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
	public AsyncApi26ExternalDocumentation createExternalDocumentation() {
		AsyncApi26ExternalDocumentationImpl node = new AsyncApi26ExternalDocumentationImpl();
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
	public AsyncApi26OperationBindings createOperationBindings() {
		AsyncApi26OperationBindingsImpl node = new AsyncApi26OperationBindingsImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public AsyncApi26OperationTrait createOperationTrait() {
		AsyncApi26OperationTraitImpl node = new AsyncApi26OperationTraitImpl();
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
	public AsyncApi2xMessage getMessage() {
		return message;
	}

	@Override
	public void setMessage(AsyncApi2xMessage value) {
		this.message = value;
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("message");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.standard);
		}
	}

	@Override
	public AsyncApi26Message createMessage() {
		AsyncApi26MessageImpl node = new AsyncApi26MessageImpl();
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
		AsyncApi26Visitor viz = (AsyncApi26Visitor) visitor;
		viz.visitOperation(this);
	}

	@Override
	public Node emptyClone() {
		return new AsyncApi26OperationImpl();
	}
}