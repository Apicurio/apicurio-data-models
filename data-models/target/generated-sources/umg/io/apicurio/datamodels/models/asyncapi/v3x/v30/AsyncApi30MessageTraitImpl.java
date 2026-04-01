package io.apicurio.datamodels.models.asyncapi.v3x.v30;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.NodeImpl;
import io.apicurio.datamodels.models.ParentPropertyType;
import io.apicurio.datamodels.models.asyncapi.AsyncApiCorrelationID;
import io.apicurio.datamodels.models.asyncapi.AsyncApiExternalDocumentation;
import io.apicurio.datamodels.models.asyncapi.AsyncApiMessageBindings;
import io.apicurio.datamodels.models.asyncapi.AsyncApiTag;
import io.apicurio.datamodels.models.asyncapi.v3x.AsyncApi3xMessageExample;
import io.apicurio.datamodels.models.asyncapi.v3x.v30.visitors.AsyncApi30Visitor;
import io.apicurio.datamodels.models.union.MultiFormatSchemaSchemaUnion;
import io.apicurio.datamodels.models.union.UnionValue;
import io.apicurio.datamodels.models.util.DataModelUtil;
import io.apicurio.datamodels.models.visitors.Visitor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AsyncApi30MessageTraitImpl extends NodeImpl implements AsyncApi30MessageTrait {

	private String $ref;
	private MultiFormatSchemaSchemaUnion headers;
	private AsyncApiCorrelationID correlationId;
	private String contentType;
	private String name;
	private String title;
	private String summary;
	private String description;
	private List<AsyncApiTag> tags;
	private AsyncApiExternalDocumentation externalDocs;
	private AsyncApiMessageBindings bindings;
	private List<AsyncApi3xMessageExample> examples;
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
	public MultiFormatSchemaSchemaUnion getHeaders() {
		return headers;
	}

	@Override
	public void setHeaders(MultiFormatSchemaSchemaUnion value) {
		this.headers = value;
		if (value != null) {
			if (value.isEntity()) {
				((NodeImpl) value)._setParentPropertyName("headers");
				((NodeImpl) value)._setParentPropertyType(ParentPropertyType.standard);
			} else if (value.isEntityList()) {
				List<?> entityList = (List<?>) ((UnionValue<?>) value).getValue();
				for (Object entity : entityList) {
					if (entity != null) {
						((NodeImpl) entity)._setParentPropertyName("headers");
						((NodeImpl) entity)._setParentPropertyType(ParentPropertyType.array);
					}
				}
			} else if (value.isEntityMap()) {
				Map<String, ?> entityMap = (Map<String, ?>) ((UnionValue<?>) value).getValue();
				Collection<String> keys = entityMap.keySet();
				for (String key : keys) {
					NodeImpl entity = (NodeImpl) entityMap.get(key);
					if (entity != null) {
						entity._setParentPropertyName("headers");
						entity._setParentPropertyType(ParentPropertyType.map);
						entity._setMapPropertyName(key);
					}
				}
			}
		}
	}

	@Override
	public AsyncApi30MultiFormatSchema createMultiFormatSchema() {
		AsyncApi30MultiFormatSchemaImpl node = new AsyncApi30MultiFormatSchemaImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public AsyncApi30Schema createSchema() {
		AsyncApi30SchemaImpl node = new AsyncApi30SchemaImpl();
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
	public AsyncApi30CorrelationID createCorrelationID() {
		AsyncApi30CorrelationIDImpl node = new AsyncApi30CorrelationIDImpl();
		node.setParent(this);
		return node;
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
	public AsyncApi30Tag createTag() {
		AsyncApi30TagImpl node = new AsyncApi30TagImpl();
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
	public AsyncApi30ExternalDocumentation createExternalDocumentation() {
		AsyncApi30ExternalDocumentationImpl node = new AsyncApi30ExternalDocumentationImpl();
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
	public AsyncApi30MessageBindings createMessageBindings() {
		AsyncApi30MessageBindingsImpl node = new AsyncApi30MessageBindingsImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public AsyncApi30MessageExample createMessageExample() {
		AsyncApi30MessageExampleImpl node = new AsyncApi30MessageExampleImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public List<AsyncApi3xMessageExample> getExamples() {
		return examples;
	}

	@Override
	public void addExample(AsyncApi3xMessageExample value) {
		if (this.examples == null) {
			this.examples = new ArrayList<>();
		}
		this.examples.add(value);
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("examples");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.array);
		}
	}

	@Override
	public void clearExamples() {
		if (this.examples != null) {
			this.examples.clear();
		}
	}

	@Override
	public void removeExample(AsyncApi3xMessageExample value) {
		if (this.examples != null) {
			this.examples.remove(value);
		}
	}

	@Override
	public void insertExample(AsyncApi3xMessageExample value, int atIndex) {
		if (this.examples == null) {
			this.examples = new ArrayList<>();
			this.examples.add(value);
		} else {
			this.examples = DataModelUtil.insertListEntry(this.examples, value, atIndex);
		}
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("examples");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.array);
		}
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
		viz.visitMessageTrait(this);
	}

	@Override
	public Node emptyClone() {
		return new AsyncApi30MessageTraitImpl();
	}
}