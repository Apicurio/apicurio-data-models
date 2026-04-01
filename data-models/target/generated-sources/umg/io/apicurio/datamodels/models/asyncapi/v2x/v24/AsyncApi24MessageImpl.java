package io.apicurio.datamodels.models.asyncapi.v2x.v24;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.NodeImpl;
import io.apicurio.datamodels.models.ParentPropertyType;
import io.apicurio.datamodels.models.asyncapi.AsyncApiCorrelationID;
import io.apicurio.datamodels.models.asyncapi.AsyncApiExternalDocumentation;
import io.apicurio.datamodels.models.asyncapi.AsyncApiMessageBindings;
import io.apicurio.datamodels.models.asyncapi.AsyncApiMessageTrait;
import io.apicurio.datamodels.models.asyncapi.AsyncApiTag;
import io.apicurio.datamodels.models.asyncapi.v2x.AsyncApi2xMessage;
import io.apicurio.datamodels.models.asyncapi.v2x.AsyncApi2xSchema;
import io.apicurio.datamodels.models.asyncapi.v2x.v24.visitors.AsyncApi24Visitor;
import io.apicurio.datamodels.models.util.DataModelUtil;
import io.apicurio.datamodels.models.visitors.Visitor;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AsyncApi24MessageImpl extends NodeImpl implements AsyncApi24Message {

	private String $ref;
	private List<AsyncApi2xMessage> oneOf;
	private String messageId;
	private AsyncApi2xSchema headers;
	private JsonNode payload;
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
	private AsyncApi24MessageExample examples;
	private List<AsyncApiMessageTrait> traits;
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
	public AsyncApi24Message createMessage() {
		AsyncApi24MessageImpl node = new AsyncApi24MessageImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public List<AsyncApi2xMessage> getOneOf() {
		return oneOf;
	}

	@Override
	public void addOneOf(AsyncApi2xMessage value) {
		if (this.oneOf == null) {
			this.oneOf = new ArrayList<>();
		}
		this.oneOf.add(value);
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("oneOf");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.array);
		}
	}

	@Override
	public void clearOneOf() {
		if (this.oneOf != null) {
			this.oneOf.clear();
		}
	}

	@Override
	public void removeOneOf(AsyncApi2xMessage value) {
		if (this.oneOf != null) {
			this.oneOf.remove(value);
		}
	}

	@Override
	public void insertOneOf(AsyncApi2xMessage value, int atIndex) {
		if (this.oneOf == null) {
			this.oneOf = new ArrayList<>();
			this.oneOf.add(value);
		} else {
			this.oneOf = DataModelUtil.insertListEntry(this.oneOf, value, atIndex);
		}
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("oneOf");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.array);
		}
	}

	@Override
	public String getMessageId() {
		return messageId;
	}

	@Override
	public void setMessageId(String value) {
		this.messageId = value;
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
	public AsyncApi24Schema createSchema() {
		AsyncApi24SchemaImpl node = new AsyncApi24SchemaImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public JsonNode getPayload() {
		return payload;
	}

	@Override
	public void setPayload(JsonNode value) {
		this.payload = value;
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
	public AsyncApi24CorrelationID createCorrelationID() {
		AsyncApi24CorrelationIDImpl node = new AsyncApi24CorrelationIDImpl();
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
	public AsyncApi24Tag createTag() {
		AsyncApi24TagImpl node = new AsyncApi24TagImpl();
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
	public AsyncApi24ExternalDocumentation createExternalDocumentation() {
		AsyncApi24ExternalDocumentationImpl node = new AsyncApi24ExternalDocumentationImpl();
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
	public AsyncApi24MessageBindings createMessageBindings() {
		AsyncApi24MessageBindingsImpl node = new AsyncApi24MessageBindingsImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public AsyncApi24MessageExample getExamples() {
		return examples;
	}

	@Override
	public void setExamples(AsyncApi24MessageExample value) {
		this.examples = value;
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("examples");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.standard);
		}
	}

	@Override
	public AsyncApi24MessageExample createMessageExample() {
		AsyncApi24MessageExampleImpl node = new AsyncApi24MessageExampleImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public AsyncApi24MessageTrait createMessageTrait() {
		AsyncApi24MessageTraitImpl node = new AsyncApi24MessageTraitImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public List<AsyncApiMessageTrait> getTraits() {
		return traits;
	}

	@Override
	public void addTrait(AsyncApiMessageTrait value) {
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
	public void removeTrait(AsyncApiMessageTrait value) {
		if (this.traits != null) {
			this.traits.remove(value);
		}
	}

	@Override
	public void insertTrait(AsyncApiMessageTrait value, int atIndex) {
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
		AsyncApi24Visitor viz = (AsyncApi24Visitor) visitor;
		viz.visitMessage(this);
	}

	@Override
	public Node emptyClone() {
		return new AsyncApi24MessageImpl();
	}
}