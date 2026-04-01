package io.apicurio.datamodels.models.asyncapi.v2x.v26;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.NodeImpl;
import io.apicurio.datamodels.models.ParentPropertyType;
import io.apicurio.datamodels.models.Schema;
import io.apicurio.datamodels.models.asyncapi.AsyncApiExternalDocumentation;
import io.apicurio.datamodels.models.asyncapi.AsyncApiSchema;
import io.apicurio.datamodels.models.asyncapi.v2x.v26.visitors.AsyncApi26Visitor;
import io.apicurio.datamodels.models.union.BooleanSchemaUnion;
import io.apicurio.datamodels.models.union.SchemaSchemaListUnion;
import io.apicurio.datamodels.models.union.UnionValue;
import io.apicurio.datamodels.models.util.DataModelUtil;
import io.apicurio.datamodels.models.visitors.Visitor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AsyncApi26SchemaImpl extends NodeImpl implements AsyncApi26Schema {

	private String $ref;
	private String title;
	private String type;
	private List<String> required;
	private Number multipleOf;
	private Number maximum;
	private Number exclusiveMaximum;
	private Number minimum;
	private Number exclusiveMinimum;
	private Integer maxLength;
	private Integer minLength;
	private String pattern;
	private Integer maxItems;
	private Integer minItems;
	private Boolean uniqueItems;
	private Integer maxProperties;
	private Integer minProperties;
	private List<JsonNode> _enum;
	private JsonNode _const;
	private List<JsonNode> examples;
	private AsyncApiSchema _if;
	private AsyncApiSchema then;
	private AsyncApiSchema _else;
	private Boolean readOnly;
	private Boolean writeOnly;
	private Map<String, Schema> properties;
	private Map<String, String> patternProperties;
	private BooleanSchemaUnion additionalProperties;
	private AsyncApiSchema additionalItems;
	private SchemaSchemaListUnion items;
	private AsyncApiSchema propertyNames;
	private AsyncApiSchema contains;
	private List<Schema> allOf;
	private List<AsyncApiSchema> oneOf;
	private List<AsyncApiSchema> anyOf;
	private AsyncApiSchema not;
	private String description;
	private String format;
	private JsonNode _default;
	private String discriminator;
	private AsyncApiExternalDocumentation externalDocs;
	private Boolean deprecated;
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
	public String getTitle() {
		return title;
	}

	@Override
	public void setTitle(String value) {
		this.title = value;
	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public void setType(String value) {
		this.type = value;
	}

	@Override
	public List<String> getRequired() {
		return required;
	}

	@Override
	public void setRequired(List<String> value) {
		this.required = value;
	}

	@Override
	public Number getMultipleOf() {
		return multipleOf;
	}

	@Override
	public void setMultipleOf(Number value) {
		this.multipleOf = value;
	}

	@Override
	public Number getMaximum() {
		return maximum;
	}

	@Override
	public void setMaximum(Number value) {
		this.maximum = value;
	}

	@Override
	public Number getExclusiveMaximum() {
		return exclusiveMaximum;
	}

	@Override
	public void setExclusiveMaximum(Number value) {
		this.exclusiveMaximum = value;
	}

	@Override
	public Number getMinimum() {
		return minimum;
	}

	@Override
	public void setMinimum(Number value) {
		this.minimum = value;
	}

	@Override
	public Number getExclusiveMinimum() {
		return exclusiveMinimum;
	}

	@Override
	public void setExclusiveMinimum(Number value) {
		this.exclusiveMinimum = value;
	}

	@Override
	public Integer getMaxLength() {
		return maxLength;
	}

	@Override
	public void setMaxLength(Integer value) {
		this.maxLength = value;
	}

	@Override
	public Integer getMinLength() {
		return minLength;
	}

	@Override
	public void setMinLength(Integer value) {
		this.minLength = value;
	}

	@Override
	public String getPattern() {
		return pattern;
	}

	@Override
	public void setPattern(String value) {
		this.pattern = value;
	}

	@Override
	public Integer getMaxItems() {
		return maxItems;
	}

	@Override
	public void setMaxItems(Integer value) {
		this.maxItems = value;
	}

	@Override
	public Integer getMinItems() {
		return minItems;
	}

	@Override
	public void setMinItems(Integer value) {
		this.minItems = value;
	}

	@Override
	public Boolean isUniqueItems() {
		return uniqueItems;
	}

	@Override
	public void setUniqueItems(Boolean value) {
		this.uniqueItems = value;
	}

	@Override
	public Integer getMaxProperties() {
		return maxProperties;
	}

	@Override
	public void setMaxProperties(Integer value) {
		this.maxProperties = value;
	}

	@Override
	public Integer getMinProperties() {
		return minProperties;
	}

	@Override
	public void setMinProperties(Integer value) {
		this.minProperties = value;
	}

	@Override
	public List<JsonNode> getEnum() {
		return _enum;
	}

	@Override
	public void setEnum(List<JsonNode> value) {
		this._enum = value;
	}

	@Override
	public JsonNode getConst() {
		return _const;
	}

	@Override
	public void setConst(JsonNode value) {
		this._const = value;
	}

	@Override
	public List<JsonNode> getExamples() {
		return examples;
	}

	@Override
	public void setExamples(List<JsonNode> value) {
		this.examples = value;
	}

	@Override
	public AsyncApiSchema getIf() {
		return _if;
	}

	@Override
	public void setIf(AsyncApiSchema value) {
		this._if = value;
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("if");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.standard);
		}
	}

	@Override
	public AsyncApi26Schema createSchema() {
		AsyncApi26SchemaImpl node = new AsyncApi26SchemaImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public AsyncApiSchema getThen() {
		return then;
	}

	@Override
	public void setThen(AsyncApiSchema value) {
		this.then = value;
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("then");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.standard);
		}
	}

	@Override
	public AsyncApiSchema getElse() {
		return _else;
	}

	@Override
	public void setElse(AsyncApiSchema value) {
		this._else = value;
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("else");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.standard);
		}
	}

	@Override
	public Boolean isReadOnly() {
		return readOnly;
	}

	@Override
	public void setReadOnly(Boolean value) {
		this.readOnly = value;
	}

	@Override
	public Boolean isWriteOnly() {
		return writeOnly;
	}

	@Override
	public void setWriteOnly(Boolean value) {
		this.writeOnly = value;
	}

	@Override
	public Map<String, Schema> getProperties() {
		return properties;
	}

	@Override
	public void addProperty(String name, Schema value) {
		if (this.properties == null) {
			this.properties = new LinkedHashMap<>();
		}
		this.properties.put(name, value);
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("properties");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.map);
			((NodeImpl) value)._setMapPropertyName(name);
		}
	}

	@Override
	public void clearProperties() {
		if (this.properties != null) {
			this.properties.clear();
		}
	}

	@Override
	public void removeProperty(String name) {
		if (this.properties != null) {
			this.properties.remove(name);
		}
	}

	@Override
	public void insertProperty(String name, Schema value, int atIndex) {
		if (this.properties == null) {
			this.properties = new LinkedHashMap<>();
			this.properties.put(name, value);
		} else {
			this.properties = DataModelUtil.insertMapEntry(this.properties, name, value, atIndex);
		}
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("properties");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.map);
			((NodeImpl) value)._setMapPropertyName(name);
		}
	}

	@Override
	public Map<String, String> getPatternProperties() {
		return patternProperties;
	}

	@Override
	public void setPatternProperties(Map<String, String> value) {
		this.patternProperties = value;
	}

	@Override
	public BooleanSchemaUnion getAdditionalProperties() {
		return additionalProperties;
	}

	@Override
	public void setAdditionalProperties(BooleanSchemaUnion value) {
		this.additionalProperties = value;
		if (value != null) {
			if (value.isEntity()) {
				((NodeImpl) value)._setParentPropertyName("additionalProperties");
				((NodeImpl) value)._setParentPropertyType(ParentPropertyType.standard);
			} else if (value.isEntityList()) {
				List<?> entityList = (List<?>) ((UnionValue<?>) value).getValue();
				for (Object entity : entityList) {
					if (entity != null) {
						((NodeImpl) entity)._setParentPropertyName("additionalProperties");
						((NodeImpl) entity)._setParentPropertyType(ParentPropertyType.array);
					}
				}
			} else if (value.isEntityMap()) {
				Map<String, ?> entityMap = (Map<String, ?>) ((UnionValue<?>) value).getValue();
				Collection<String> keys = entityMap.keySet();
				for (String key : keys) {
					NodeImpl entity = (NodeImpl) entityMap.get(key);
					if (entity != null) {
						entity._setParentPropertyName("additionalProperties");
						entity._setParentPropertyType(ParentPropertyType.map);
						entity._setMapPropertyName(key);
					}
				}
			}
		}
	}

	@Override
	public AsyncApiSchema getAdditionalItems() {
		return additionalItems;
	}

	@Override
	public void setAdditionalItems(AsyncApiSchema value) {
		this.additionalItems = value;
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("additionalItems");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.standard);
		}
	}

	@Override
	public SchemaSchemaListUnion getItems() {
		return items;
	}

	@Override
	public void setItems(SchemaSchemaListUnion value) {
		this.items = value;
		if (value != null) {
			if (value.isEntity()) {
				((NodeImpl) value)._setParentPropertyName("items");
				((NodeImpl) value)._setParentPropertyType(ParentPropertyType.standard);
			} else if (value.isEntityList()) {
				List<?> entityList = (List<?>) ((UnionValue<?>) value).getValue();
				for (Object entity : entityList) {
					if (entity != null) {
						((NodeImpl) entity)._setParentPropertyName("items");
						((NodeImpl) entity)._setParentPropertyType(ParentPropertyType.array);
					}
				}
			} else if (value.isEntityMap()) {
				Map<String, ?> entityMap = (Map<String, ?>) ((UnionValue<?>) value).getValue();
				Collection<String> keys = entityMap.keySet();
				for (String key : keys) {
					NodeImpl entity = (NodeImpl) entityMap.get(key);
					if (entity != null) {
						entity._setParentPropertyName("items");
						entity._setParentPropertyType(ParentPropertyType.map);
						entity._setMapPropertyName(key);
					}
				}
			}
		}
	}

	@Override
	public AsyncApiSchema getPropertyNames() {
		return propertyNames;
	}

	@Override
	public void setPropertyNames(AsyncApiSchema value) {
		this.propertyNames = value;
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("propertyNames");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.standard);
		}
	}

	@Override
	public AsyncApiSchema getContains() {
		return contains;
	}

	@Override
	public void setContains(AsyncApiSchema value) {
		this.contains = value;
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("contains");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.standard);
		}
	}

	@Override
	public List<Schema> getAllOf() {
		return allOf;
	}

	@Override
	public void addAllOf(Schema value) {
		if (this.allOf == null) {
			this.allOf = new ArrayList<>();
		}
		this.allOf.add(value);
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("allOf");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.array);
		}
	}

	@Override
	public void clearAllOf() {
		if (this.allOf != null) {
			this.allOf.clear();
		}
	}

	@Override
	public void removeAllOf(Schema value) {
		if (this.allOf != null) {
			this.allOf.remove(value);
		}
	}

	@Override
	public void insertAllOf(Schema value, int atIndex) {
		if (this.allOf == null) {
			this.allOf = new ArrayList<>();
			this.allOf.add(value);
		} else {
			this.allOf = DataModelUtil.insertListEntry(this.allOf, value, atIndex);
		}
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("allOf");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.array);
		}
	}

	@Override
	public List<AsyncApiSchema> getOneOf() {
		return oneOf;
	}

	@Override
	public void addOneOf(AsyncApiSchema value) {
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
	public void removeOneOf(AsyncApiSchema value) {
		if (this.oneOf != null) {
			this.oneOf.remove(value);
		}
	}

	@Override
	public void insertOneOf(AsyncApiSchema value, int atIndex) {
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
	public List<AsyncApiSchema> getAnyOf() {
		return anyOf;
	}

	@Override
	public void addAnyOf(AsyncApiSchema value) {
		if (this.anyOf == null) {
			this.anyOf = new ArrayList<>();
		}
		this.anyOf.add(value);
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("anyOf");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.array);
		}
	}

	@Override
	public void clearAnyOf() {
		if (this.anyOf != null) {
			this.anyOf.clear();
		}
	}

	@Override
	public void removeAnyOf(AsyncApiSchema value) {
		if (this.anyOf != null) {
			this.anyOf.remove(value);
		}
	}

	@Override
	public void insertAnyOf(AsyncApiSchema value, int atIndex) {
		if (this.anyOf == null) {
			this.anyOf = new ArrayList<>();
			this.anyOf.add(value);
		} else {
			this.anyOf = DataModelUtil.insertListEntry(this.anyOf, value, atIndex);
		}
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("anyOf");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.array);
		}
	}

	@Override
	public AsyncApiSchema getNot() {
		return not;
	}

	@Override
	public void setNot(AsyncApiSchema value) {
		this.not = value;
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("not");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.standard);
		}
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
	public String getFormat() {
		return format;
	}

	@Override
	public void setFormat(String value) {
		this.format = value;
	}

	@Override
	public JsonNode getDefault() {
		return _default;
	}

	@Override
	public void setDefault(JsonNode value) {
		this._default = value;
	}

	@Override
	public String getDiscriminator() {
		return discriminator;
	}

	@Override
	public void setDiscriminator(String value) {
		this.discriminator = value;
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
	public AsyncApi26ExternalDocumentation createExternalDocumentation() {
		AsyncApi26ExternalDocumentationImpl node = new AsyncApi26ExternalDocumentationImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public Boolean isDeprecated() {
		return deprecated;
	}

	@Override
	public void setDeprecated(Boolean value) {
		this.deprecated = value;
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
	public boolean isBoolean() {
		return false;
	}

	@Override
	public Boolean asBoolean() {
		throw new ClassCastException();
	}

	@Override
	public boolean isSchema() {
		return true;
	}

	@Override
	public Schema asSchema() {
		return this;
	}

	@Override
	public Object unionValue() {
		return this;
	}

	@Override
	public boolean isSchemaList() {
		return false;
	}

	@Override
	public List<Schema> asSchemaList() {
		throw new ClassCastException();
	}

	@Override
	public boolean isAny() {
		return false;
	}

	@Override
	public JsonNode asAny() {
		throw new ClassCastException();
	}

	@Override
	public void accept(Visitor visitor) {
		AsyncApi26Visitor viz = (AsyncApi26Visitor) visitor;
		viz.visitSchema(this);
	}

	@Override
	public Node emptyClone() {
		return new AsyncApi26SchemaImpl();
	}
}