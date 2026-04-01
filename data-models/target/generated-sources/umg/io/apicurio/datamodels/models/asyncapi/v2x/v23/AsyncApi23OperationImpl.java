package io.apicurio.datamodels.models.asyncapi.v2x.v23;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.ExternalDocumentation;
import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.NodeImpl;
import io.apicurio.datamodels.models.ParentPropertyType;
import io.apicurio.datamodels.models.asyncapi.AsyncApiOperationBindings;
import io.apicurio.datamodels.models.asyncapi.AsyncApiOperationTrait;
import io.apicurio.datamodels.models.asyncapi.AsyncApiTag;
import io.apicurio.datamodels.models.asyncapi.v2x.AsyncApi2xMessage;
import io.apicurio.datamodels.models.asyncapi.v2x.v23.visitors.AsyncApi23Visitor;
import io.apicurio.datamodels.models.util.DataModelUtil;
import io.apicurio.datamodels.models.visitors.Visitor;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AsyncApi23OperationImpl extends NodeImpl implements AsyncApi23Operation {

	private String operationId;
	private String summary;
	private String description;
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
	public AsyncApi23Tag createTag() {
		AsyncApi23TagImpl node = new AsyncApi23TagImpl();
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
	public AsyncApi23ExternalDocumentation createExternalDocumentation() {
		AsyncApi23ExternalDocumentationImpl node = new AsyncApi23ExternalDocumentationImpl();
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
	public AsyncApi23OperationBindings createOperationBindings() {
		AsyncApi23OperationBindingsImpl node = new AsyncApi23OperationBindingsImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public AsyncApi23OperationTrait createOperationTrait() {
		AsyncApi23OperationTraitImpl node = new AsyncApi23OperationTraitImpl();
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
	public AsyncApi23Message createMessage() {
		AsyncApi23MessageImpl node = new AsyncApi23MessageImpl();
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
		AsyncApi23Visitor viz = (AsyncApi23Visitor) visitor;
		viz.visitOperation(this);
	}

	@Override
	public Node emptyClone() {
		return new AsyncApi23OperationImpl();
	}
}