package io.apicurio.datamodels.models.asyncapi.v2x.v22;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.NodeImpl;
import io.apicurio.datamodels.models.ParentPropertyType;
import io.apicurio.datamodels.models.asyncapi.AsyncApiCorrelationID;
import io.apicurio.datamodels.models.asyncapi.AsyncApiExternalDocumentation;
import io.apicurio.datamodels.models.asyncapi.AsyncApiMessageBindings;
import io.apicurio.datamodels.models.asyncapi.AsyncApiTag;
import io.apicurio.datamodels.models.asyncapi.v2x.AsyncApi2xSchema;
import io.apicurio.datamodels.models.asyncapi.v2x.v22.visitors.AsyncApi22Visitor;
import io.apicurio.datamodels.models.util.DataModelUtil;
import io.apicurio.datamodels.models.visitors.Visitor;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AsyncApi22MessageTraitImpl extends NodeImpl implements AsyncApi22MessageTrait {

	private String $ref;
	private AsyncApi2xSchema headers;
	private AsyncApiCorrelationID correlationId;
	private String schemaFormat;
	private String contentType;
	private String name;
	private String title;
	private String summary;
	private String description;
	private List<AsyncApiTag> tags;
	private AsyncApiExternalDocumentation externalDocs;
	private AsyncApiMessageBindings bindings;
	private Map<String, JsonNode> examples;
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
	public AsyncApi2xSchema getHeaders() {
		return headers;
	}

	@Override
	public void setHeaders(AsyncApi2xSchema value) {
		this.headers = value;
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("headers");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.standard);
		}
	}

	@Override
	public AsyncApi22Schema createSchema() {
		AsyncApi22SchemaImpl node = new AsyncApi22SchemaImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public AsyncApiCorrelationID getCorrelationId() {
		return correlationId;
	}

	@Override
	public void setCorrelationId(AsyncApiCorrelationID value) {
		this.correlationId = value;
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("correlationId");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.standard);
		}
	}

	@Override
	public AsyncApi22CorrelationID createCorrelationID() {
		AsyncApi22CorrelationIDImpl node = new AsyncApi22CorrelationIDImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public String getSchemaFormat() {
		return schemaFormat;
	}

	@Override
	public void setSchemaFormat(String value) {
		this.schemaFormat = value;
	}

	@Override
	public String getContentType() {
		return contentType;
	}

	@Override
	public void setContentType(String value) {
		this.contentType = value;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String value) {
		this.name = value;
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
	public AsyncApi22Tag createTag() {
		AsyncApi22TagImpl node = new AsyncApi22TagImpl();
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
	public AsyncApi22ExternalDocumentation createExternalDocumentation() {
		AsyncApi22ExternalDocumentationImpl node = new AsyncApi22ExternalDocumentationImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public AsyncApiMessageBindings getBindings() {
		return bindings;
	}

	@Override
	public void setBindings(AsyncApiMessageBindings value) {
		this.bindings = value;
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("bindings");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.standard);
		}
	}

	@Override
	public AsyncApi22MessageBindings createMessageBindings() {
		AsyncApi22MessageBindingsImpl node = new AsyncApi22MessageBindingsImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public Map<String, JsonNode> getExamples() {
		return examples;
	}

	@Override
	public void setExamples(Map<String, JsonNode> value) {
		this.examples = value;
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
		AsyncApi22Visitor viz = (AsyncApi22Visitor) visitor;
		viz.visitMessageTrait(this);
	}

	@Override
	public Node emptyClone() {
		return new AsyncApi22MessageTraitImpl();
	}
}