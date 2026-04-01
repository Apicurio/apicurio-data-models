package io.apicurio.datamodels.models.openapi.v2x.v20;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.NodeImpl;
import io.apicurio.datamodels.models.ParentPropertyType;
import io.apicurio.datamodels.models.Schema;
import io.apicurio.datamodels.models.openapi.OpenApiExternalDocumentation;
import io.apicurio.datamodels.models.openapi.OpenApiXML;
import io.apicurio.datamodels.models.openapi.v2x.v20.visitors.OpenApi20Visitor;
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

public class OpenApi20SchemaImpl extends NodeImpl implements OpenApi20Schema {

	private String $ref;
	private String description;
	private String type;
	private String format;
	private JsonNode _default;
	private Number maximum;
	private Boolean exclusiveMaximum;
	private Number minimum;
	private Boolean exclusiveMinimum;
	private Integer maxLength;
	private Integer minLength;
	private String pattern;
	private Integer maxItems;
	private Integer minItems;
	private Boolean uniqueItems;
	private List<JsonNode> _enum;
	private Number multipleOf;
	private String title;
	private SchemaSchemaListUnion items;
	private Integer maxProperties;
	private Integer minProperties;
	private List<String> required;
	private List<Schema> allOf;
	private Map<String, Schema> properties;
	private BooleanSchemaUnion additionalProperties;
	private String discriminator;
	private Boolean readOnly;
	private OpenApiXML xml;
	private OpenApiExternalDocumentation externalDocs;
	private JsonNode example;
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
	public String getDescription() {
		return description;
	}

	@Override
	public void setDescription(String value) {
		this.description = value;
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
	public Number getMaximum() {
		return maximum;
	}

	@Override
	public void setMaximum(Number value) {
		this.maximum = value;
	}

	@Override
	public Boolean isExclusiveMaximum() {
		return exclusiveMaximum;
	}

	@Override
	public void setExclusiveMaximum(Boolean value) {
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
	public Boolean isExclusiveMinimum() {
		return exclusiveMinimum;
	}

	@Override
	public void setExclusiveMinimum(Boolean value) {
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
	public List<JsonNode> getEnum() {
		return _enum;
	}

	@Override
	public void setEnum(List<JsonNode> value) {
		this._enum = value;
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
	public String getTitle() {
		return title;
	}

	@Override
	public void setTitle(String value) {
		this.title = value;
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
	public OpenApi20Schema createSchema() {
		OpenApi20SchemaImpl node = new OpenApi20SchemaImpl();
		node.setParent(this);
		return node;
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
	public List<String> getRequired() {
		return required;
	}

	@Override
	public void setRequired(List<String> value) {
		this.required = value;
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
	public String getDiscriminator() {
		return discriminator;
	}

	@Override
	public void setDiscriminator(String value) {
		this.discriminator = value;
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
	public OpenApiXML getXml() {
		return xml;
	}

	@Override
	public void setXml(OpenApiXML value) {
		this.xml = value;
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("xml");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.standard);
		}
	}

	@Override
	public OpenApi20XML createXML() {
		OpenApi20XMLImpl node = new OpenApi20XMLImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public OpenApiExternalDocumentation getExternalDocs() {
		return externalDocs;
	}

	@Override
	public void setExternalDocs(OpenApiExternalDocumentation value) {
		this.externalDocs = value;
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("externalDocs");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.standard);
		}
	}

	@Override
	public OpenApi20ExternalDocumentation createExternalDocumentation() {
		OpenApi20ExternalDocumentationImpl node = new OpenApi20ExternalDocumentationImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public JsonNode getExample() {
		return example;
	}

	@Override
	public void setExample(JsonNode value) {
		this.example = value;
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
	public void accept(Visitor visitor) {
		OpenApi20Visitor viz = (OpenApi20Visitor) visitor;
		viz.visitSchema(this);
	}

	@Override
	public Node emptyClone() {
		return new OpenApi20SchemaImpl();
	}
}