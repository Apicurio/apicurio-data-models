package io.apicurio.datamodels.models.asyncapi.v3x.v31;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.NodeImpl;
import io.apicurio.datamodels.models.ParentPropertyType;
import io.apicurio.datamodels.models.asyncapi.AsyncApiCorrelationID;
import io.apicurio.datamodels.models.asyncapi.AsyncApiExternalDocumentation;
import io.apicurio.datamodels.models.asyncapi.AsyncApiMessageBindings;
import io.apicurio.datamodels.models.asyncapi.AsyncApiTag;
import io.apicurio.datamodels.models.asyncapi.v3x.AsyncApi3xMessageExample;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.visitors.AsyncApi31Visitor;
import io.apicurio.datamodels.models.union.MultiFormatSchemaSchemaUnion;
import io.apicurio.datamodels.models.union.UnionValue;
import io.apicurio.datamodels.models.util.DataModelUtil;
import io.apicurio.datamodels.models.visitors.Visitor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AsyncApi31MessageTraitImpl extends NodeImpl implements AsyncApi31MessageTrait {

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
	public AsyncApi31MultiFormatSchema createMultiFormatSchema() {
		AsyncApi31MultiFormatSchemaImpl node = new AsyncApi31MultiFormatSchemaImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public AsyncApi31Schema createSchema() {
		AsyncApi31SchemaImpl node = new AsyncApi31SchemaImpl();
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
	public AsyncApi31CorrelationID createCorrelationID() {
		AsyncApi31CorrelationIDImpl node = new AsyncApi31CorrelationIDImpl();
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
	public AsyncApi31MessageBindings createMessageBindings() {
		AsyncApi31MessageBindingsImpl node = new AsyncApi31MessageBindingsImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public AsyncApi31MessageExample createMessageExample() {
		AsyncApi31MessageExampleImpl node = new AsyncApi31MessageExampleImpl();
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
		AsyncApi31Visitor viz = (AsyncApi31Visitor) visitor;
		viz.visitMessageTrait(this);
	}

	@Override
	public Node emptyClone() {
		return new AsyncApi31MessageTraitImpl();
	}
}