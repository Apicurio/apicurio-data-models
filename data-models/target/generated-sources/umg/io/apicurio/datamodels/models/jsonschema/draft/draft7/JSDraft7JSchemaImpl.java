package io.apicurio.datamodels.models.jsonschema.draft.draft7;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.NodeImpl;
import io.apicurio.datamodels.models.ParentPropertyType;
import io.apicurio.datamodels.models.jsonschema.JsonSchemaJSchema;
import io.apicurio.datamodels.models.jsonschema.draft.draft7.visitors.JSDraft7Visitor;
import io.apicurio.datamodels.models.union.BooleanJSchemaJSchemaListUnion;
import io.apicurio.datamodels.models.union.BooleanJSchemaUnion;
import io.apicurio.datamodels.models.union.StringStringListUnion;
import io.apicurio.datamodels.models.union.UnionValue;
import io.apicurio.datamodels.models.util.DataModelUtil;
import io.apicurio.datamodels.models.visitors.Visitor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class JSDraft7JSchemaImpl extends NodeImpl implements JSDraft7JSchema {

	private String $ref;
	private String $comment;
	private String title;
	private String description;
	private JsonNode _default;
	private List<JsonNode> examples;
	private Boolean readOnly;
	private Boolean writeOnly;
	private Map<String, BooleanJSchemaUnion> definitions;
	private StringStringListUnion type;
	private List<JsonNode> _enum;
	private JsonNode _const;
	private String format;
	private List<BooleanJSchemaUnion> allOf;
	private List<BooleanJSchemaUnion> anyOf;
	private List<BooleanJSchemaUnion> oneOf;
	private BooleanJSchemaUnion not;
	private BooleanJSchemaUnion _if;
	private BooleanJSchemaUnion then;
	private BooleanJSchemaUnion _else;
	private Number multipleOf;
	private Number minimum;
	private Number maximum;
	private Number exclusiveMinimum;
	private Number exclusiveMaximum;
	private Integer minLength;
	private Integer maxLength;
	private String pattern;
	private String contentMediaType;
	private String contentEncoding;
	private BooleanJSchemaJSchemaListUnion items;
	private BooleanJSchemaUnion additionalItems;
	private Integer minItems;
	private Integer maxItems;
	private Boolean uniqueItems;
	private BooleanJSchemaUnion contains;
	private Map<String, BooleanJSchemaUnion> properties;
	private Map<String, BooleanJSchemaUnion> patternProperties;
	private BooleanJSchemaUnion additionalProperties;
	private List<String> required;
	private Integer minProperties;
	private Integer maxProperties;
	private Map<String, JsonNode> dependencies;
	private BooleanJSchemaUnion propertyNames;

	@Override
	public String get$ref() {
		return $ref;
	}

	@Override
	public void set$ref(String value) {
		this.$ref = value;
	}

	@Override
	public String get$comment() {
		return $comment;
	}

	@Override
	public void set$comment(String value) {
		this.$comment = value;
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
	public String getDescription() {
		return description;
	}

	@Override
	public void setDescription(String value) {
		this.description = value;
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
	public List<JsonNode> getExamples() {
		return examples;
	}

	@Override
	public void setExamples(List<JsonNode> value) {
		this.examples = value;
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
	public JSDraft7JSchema createJSchema() {
		JSDraft7JSchemaImpl node = new JSDraft7JSchemaImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public Map<String, BooleanJSchemaUnion> getDefinitions() {
		return definitions;
	}

	@Override
	public void addDefinition(String name, BooleanJSchemaUnion value) {
		if (this.definitions == null) {
			this.definitions = new LinkedHashMap<>();
		}
		this.definitions.put(name, value);
		if (value != null && value.isEntity()) {
			((NodeImpl) value)._setParentPropertyName("definitions");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.map);
			((NodeImpl) value)._setMapPropertyName(name);
		}
	}

	@Override
	public void clearDefinitions() {
		if (this.definitions != null) {
			this.definitions.clear();
		}
	}

	@Override
	public void removeDefinition(String name) {
		if (this.definitions != null) {
			this.definitions.remove(name);
		}
	}

	@Override
	public void insertDefinition(String name, BooleanJSchemaUnion value, int atIndex) {
		if (this.definitions == null) {
			this.definitions = new LinkedHashMap<>();
			this.definitions.put(name, value);
		} else {
			this.definitions = DataModelUtil.insertMapEntry(this.definitions, name, value, atIndex);
		}
		if (value != null && value.isEntity()) {
			((NodeImpl) value)._setParentPropertyName("definitions");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.map);
			((NodeImpl) value)._setMapPropertyName(name);
		}
	}

	@Override
	public StringStringListUnion getType() {
		return type;
	}

	@Override
	public void setType(StringStringListUnion value) {
		this.type = value;
		if (value != null) {
			if (value.isEntity()) {
				((NodeImpl) value)._setParentPropertyName("type");
				((NodeImpl) value)._setParentPropertyType(ParentPropertyType.standard);
			} else if (value.isEntityList()) {
				List<?> entityList = (List<?>) ((UnionValue<?>) value).getValue();
				for (Object entity : entityList) {
					if (entity != null) {
						((NodeImpl) entity)._setParentPropertyName("type");
						((NodeImpl) entity)._setParentPropertyType(ParentPropertyType.array);
					}
				}
			} else if (value.isEntityMap()) {
				Map<String, ?> entityMap = (Map<String, ?>) ((UnionValue<?>) value).getValue();
				Collection<String> keys = entityMap.keySet();
				for (String key : keys) {
					NodeImpl entity = (NodeImpl) entityMap.get(key);
					if (entity != null) {
						entity._setParentPropertyName("type");
						entity._setParentPropertyType(ParentPropertyType.map);
						entity._setMapPropertyName(key);
					}
				}
			}
		}
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
	public String getFormat() {
		return format;
	}

	@Override
	public void setFormat(String value) {
		this.format = value;
	}

	@Override
	public List<BooleanJSchemaUnion> getAllOf() {
		return allOf;
	}

	@Override
	public void addAllOf(BooleanJSchemaUnion value) {
		if (this.allOf == null) {
			this.allOf = new ArrayList<>();
		}
		this.allOf.add(value);
		if (value != null && value.isEntity()) {
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
	public void removeAllOf(BooleanJSchemaUnion value) {
		if (this.allOf != null) {
			this.allOf.remove(value);
		}
	}

	@Override
	public void insertAllOf(BooleanJSchemaUnion value, int atIndex) {
		if (this.allOf == null) {
			this.allOf = new ArrayList<>();
			this.allOf.add(value);
		} else {
			this.allOf = DataModelUtil.insertListEntry(this.allOf, value, atIndex);
		}
		if (value != null && value.isEntity()) {
			((NodeImpl) value)._setParentPropertyName("allOf");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.array);
		}
	}

	@Override
	public List<BooleanJSchemaUnion> getAnyOf() {
		return anyOf;
	}

	@Override
	public void addAnyOf(BooleanJSchemaUnion value) {
		if (this.anyOf == null) {
			this.anyOf = new ArrayList<>();
		}
		this.anyOf.add(value);
		if (value != null && value.isEntity()) {
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
	public void removeAnyOf(BooleanJSchemaUnion value) {
		if (this.anyOf != null) {
			this.anyOf.remove(value);
		}
	}

	@Override
	public void insertAnyOf(BooleanJSchemaUnion value, int atIndex) {
		if (this.anyOf == null) {
			this.anyOf = new ArrayList<>();
			this.anyOf.add(value);
		} else {
			this.anyOf = DataModelUtil.insertListEntry(this.anyOf, value, atIndex);
		}
		if (value != null && value.isEntity()) {
			((NodeImpl) value)._setParentPropertyName("anyOf");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.array);
		}
	}

	@Override
	public List<BooleanJSchemaUnion> getOneOf() {
		return oneOf;
	}

	@Override
	public void addOneOf(BooleanJSchemaUnion value) {
		if (this.oneOf == null) {
			this.oneOf = new ArrayList<>();
		}
		this.oneOf.add(value);
		if (value != null && value.isEntity()) {
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
	public void removeOneOf(BooleanJSchemaUnion value) {
		if (this.oneOf != null) {
			this.oneOf.remove(value);
		}
	}

	@Override
	public void insertOneOf(BooleanJSchemaUnion value, int atIndex) {
		if (this.oneOf == null) {
			this.oneOf = new ArrayList<>();
			this.oneOf.add(value);
		} else {
			this.oneOf = DataModelUtil.insertListEntry(this.oneOf, value, atIndex);
		}
		if (value != null && value.isEntity()) {
			((NodeImpl) value)._setParentPropertyName("oneOf");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.array);
		}
	}

	@Override
	public BooleanJSchemaUnion getNot() {
		return not;
	}

	@Override
	public void setNot(BooleanJSchemaUnion value) {
		this.not = value;
		if (value != null) {
			if (value.isEntity()) {
				((NodeImpl) value)._setParentPropertyName("not");
				((NodeImpl) value)._setParentPropertyType(ParentPropertyType.standard);
			} else if (value.isEntityList()) {
				List<?> entityList = (List<?>) ((UnionValue<?>) value).getValue();
				for (Object entity : entityList) {
					if (entity != null) {
						((NodeImpl) entity)._setParentPropertyName("not");
						((NodeImpl) entity)._setParentPropertyType(ParentPropertyType.array);
					}
				}
			} else if (value.isEntityMap()) {
				Map<String, ?> entityMap = (Map<String, ?>) ((UnionValue<?>) value).getValue();
				Collection<String> keys = entityMap.keySet();
				for (String key : keys) {
					NodeImpl entity = (NodeImpl) entityMap.get(key);
					if (entity != null) {
						entity._setParentPropertyName("not");
						entity._setParentPropertyType(ParentPropertyType.map);
						entity._setMapPropertyName(key);
					}
				}
			}
		}
	}

	@Override
	public BooleanJSchemaUnion getIf() {
		return _if;
	}

	@Override
	public void setIf(BooleanJSchemaUnion value) {
		this._if = value;
		if (value != null) {
			if (value.isEntity()) {
				((NodeImpl) value)._setParentPropertyName("if");
				((NodeImpl) value)._setParentPropertyType(ParentPropertyType.standard);
			} else if (value.isEntityList()) {
				List<?> entityList = (List<?>) ((UnionValue<?>) value).getValue();
				for (Object entity : entityList) {
					if (entity != null) {
						((NodeImpl) entity)._setParentPropertyName("if");
						((NodeImpl) entity)._setParentPropertyType(ParentPropertyType.array);
					}
				}
			} else if (value.isEntityMap()) {
				Map<String, ?> entityMap = (Map<String, ?>) ((UnionValue<?>) value).getValue();
				Collection<String> keys = entityMap.keySet();
				for (String key : keys) {
					NodeImpl entity = (NodeImpl) entityMap.get(key);
					if (entity != null) {
						entity._setParentPropertyName("if");
						entity._setParentPropertyType(ParentPropertyType.map);
						entity._setMapPropertyName(key);
					}
				}
			}
		}
	}

	@Override
	public BooleanJSchemaUnion getThen() {
		return then;
	}

	@Override
	public void setThen(BooleanJSchemaUnion value) {
		this.then = value;
		if (value != null) {
			if (value.isEntity()) {
				((NodeImpl) value)._setParentPropertyName("then");
				((NodeImpl) value)._setParentPropertyType(ParentPropertyType.standard);
			} else if (value.isEntityList()) {
				List<?> entityList = (List<?>) ((UnionValue<?>) value).getValue();
				for (Object entity : entityList) {
					if (entity != null) {
						((NodeImpl) entity)._setParentPropertyName("then");
						((NodeImpl) entity)._setParentPropertyType(ParentPropertyType.array);
					}
				}
			} else if (value.isEntityMap()) {
				Map<String, ?> entityMap = (Map<String, ?>) ((UnionValue<?>) value).getValue();
				Collection<String> keys = entityMap.keySet();
				for (String key : keys) {
					NodeImpl entity = (NodeImpl) entityMap.get(key);
					if (entity != null) {
						entity._setParentPropertyName("then");
						entity._setParentPropertyType(ParentPropertyType.map);
						entity._setMapPropertyName(key);
					}
				}
			}
		}
	}

	@Override
	public BooleanJSchemaUnion getElse() {
		return _else;
	}

	@Override
	public void setElse(BooleanJSchemaUnion value) {
		this._else = value;
		if (value != null) {
			if (value.isEntity()) {
				((NodeImpl) value)._setParentPropertyName("else");
				((NodeImpl) value)._setParentPropertyType(ParentPropertyType.standard);
			} else if (value.isEntityList()) {
				List<?> entityList = (List<?>) ((UnionValue<?>) value).getValue();
				for (Object entity : entityList) {
					if (entity != null) {
						((NodeImpl) entity)._setParentPropertyName("else");
						((NodeImpl) entity)._setParentPropertyType(ParentPropertyType.array);
					}
				}
			} else if (value.isEntityMap()) {
				Map<String, ?> entityMap = (Map<String, ?>) ((UnionValue<?>) value).getValue();
				Collection<String> keys = entityMap.keySet();
				for (String key : keys) {
					NodeImpl entity = (NodeImpl) entityMap.get(key);
					if (entity != null) {
						entity._setParentPropertyName("else");
						entity._setParentPropertyType(ParentPropertyType.map);
						entity._setMapPropertyName(key);
					}
				}
			}
		}
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
	public Number getMinimum() {
		return minimum;
	}

	@Override
	public void setMinimum(Number value) {
		this.minimum = value;
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
	public Number getExclusiveMinimum() {
		return exclusiveMinimum;
	}

	@Override
	public void setExclusiveMinimum(Number value) {
		this.exclusiveMinimum = value;
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
	public Integer getMinLength() {
		return minLength;
	}

	@Override
	public void setMinLength(Integer value) {
		this.minLength = value;
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
	public String getPattern() {
		return pattern;
	}

	@Override
	public void setPattern(String value) {
		this.pattern = value;
	}

	@Override
	public String getContentMediaType() {
		return contentMediaType;
	}

	@Override
	public void setContentMediaType(String value) {
		this.contentMediaType = value;
	}

	@Override
	public String getContentEncoding() {
		return contentEncoding;
	}

	@Override
	public void setContentEncoding(String value) {
		this.contentEncoding = value;
	}

	@Override
	public BooleanJSchemaJSchemaListUnion getItems() {
		return items;
	}

	@Override
	public void setItems(BooleanJSchemaJSchemaListUnion value) {
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
	public BooleanJSchemaUnion getAdditionalItems() {
		return additionalItems;
	}

	@Override
	public void setAdditionalItems(BooleanJSchemaUnion value) {
		this.additionalItems = value;
		if (value != null) {
			if (value.isEntity()) {
				((NodeImpl) value)._setParentPropertyName("additionalItems");
				((NodeImpl) value)._setParentPropertyType(ParentPropertyType.standard);
			} else if (value.isEntityList()) {
				List<?> entityList = (List<?>) ((UnionValue<?>) value).getValue();
				for (Object entity : entityList) {
					if (entity != null) {
						((NodeImpl) entity)._setParentPropertyName("additionalItems");
						((NodeImpl) entity)._setParentPropertyType(ParentPropertyType.array);
					}
				}
			} else if (value.isEntityMap()) {
				Map<String, ?> entityMap = (Map<String, ?>) ((UnionValue<?>) value).getValue();
				Collection<String> keys = entityMap.keySet();
				for (String key : keys) {
					NodeImpl entity = (NodeImpl) entityMap.get(key);
					if (entity != null) {
						entity._setParentPropertyName("additionalItems");
						entity._setParentPropertyType(ParentPropertyType.map);
						entity._setMapPropertyName(key);
					}
				}
			}
		}
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
	public Integer getMaxItems() {
		return maxItems;
	}

	@Override
	public void setMaxItems(Integer value) {
		this.maxItems = value;
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
	public BooleanJSchemaUnion getContains() {
		return contains;
	}

	@Override
	public void setContains(BooleanJSchemaUnion value) {
		this.contains = value;
		if (value != null) {
			if (value.isEntity()) {
				((NodeImpl) value)._setParentPropertyName("contains");
				((NodeImpl) value)._setParentPropertyType(ParentPropertyType.standard);
			} else if (value.isEntityList()) {
				List<?> entityList = (List<?>) ((UnionValue<?>) value).getValue();
				for (Object entity : entityList) {
					if (entity != null) {
						((NodeImpl) entity)._setParentPropertyName("contains");
						((NodeImpl) entity)._setParentPropertyType(ParentPropertyType.array);
					}
				}
			} else if (value.isEntityMap()) {
				Map<String, ?> entityMap = (Map<String, ?>) ((UnionValue<?>) value).getValue();
				Collection<String> keys = entityMap.keySet();
				for (String key : keys) {
					NodeImpl entity = (NodeImpl) entityMap.get(key);
					if (entity != null) {
						entity._setParentPropertyName("contains");
						entity._setParentPropertyType(ParentPropertyType.map);
						entity._setMapPropertyName(key);
					}
				}
			}
		}
	}

	@Override
	public Map<String, BooleanJSchemaUnion> getProperties() {
		return properties;
	}

	@Override
	public void addProperty(String name, BooleanJSchemaUnion value) {
		if (this.properties == null) {
			this.properties = new LinkedHashMap<>();
		}
		this.properties.put(name, value);
		if (value != null && value.isEntity()) {
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
	public void insertProperty(String name, BooleanJSchemaUnion value, int atIndex) {
		if (this.properties == null) {
			this.properties = new LinkedHashMap<>();
			this.properties.put(name, value);
		} else {
			this.properties = DataModelUtil.insertMapEntry(this.properties, name, value, atIndex);
		}
		if (value != null && value.isEntity()) {
			((NodeImpl) value)._setParentPropertyName("properties");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.map);
			((NodeImpl) value)._setMapPropertyName(name);
		}
	}

	@Override
	public Map<String, BooleanJSchemaUnion> getPatternProperties() {
		return patternProperties;
	}

	@Override
	public void addPatternProperty(String name, BooleanJSchemaUnion value) {
		if (this.patternProperties == null) {
			this.patternProperties = new LinkedHashMap<>();
		}
		this.patternProperties.put(name, value);
		if (value != null && value.isEntity()) {
			((NodeImpl) value)._setParentPropertyName("patternProperties");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.map);
			((NodeImpl) value)._setMapPropertyName(name);
		}
	}

	@Override
	public void clearPatternProperties() {
		if (this.patternProperties != null) {
			this.patternProperties.clear();
		}
	}

	@Override
	public void removePatternProperty(String name) {
		if (this.patternProperties != null) {
			this.patternProperties.remove(name);
		}
	}

	@Override
	public void insertPatternProperty(String name, BooleanJSchemaUnion value, int atIndex) {
		if (this.patternProperties == null) {
			this.patternProperties = new LinkedHashMap<>();
			this.patternProperties.put(name, value);
		} else {
			this.patternProperties = DataModelUtil.insertMapEntry(this.patternProperties, name, value, atIndex);
		}
		if (value != null && value.isEntity()) {
			((NodeImpl) value)._setParentPropertyName("patternProperties");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.map);
			((NodeImpl) value)._setMapPropertyName(name);
		}
	}

	@Override
	public BooleanJSchemaUnion getAdditionalProperties() {
		return additionalProperties;
	}

	@Override
	public void setAdditionalProperties(BooleanJSchemaUnion value) {
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
	public List<String> getRequired() {
		return required;
	}

	@Override
	public void setRequired(List<String> value) {
		this.required = value;
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
	public Integer getMaxProperties() {
		return maxProperties;
	}

	@Override
	public void setMaxProperties(Integer value) {
		this.maxProperties = value;
	}

	@Override
	public Map<String, JsonNode> getDependencies() {
		return dependencies;
	}

	@Override
	public void setDependencies(Map<String, JsonNode> value) {
		this.dependencies = value;
	}

	@Override
	public BooleanJSchemaUnion getPropertyNames() {
		return propertyNames;
	}

	@Override
	public void setPropertyNames(BooleanJSchemaUnion value) {
		this.propertyNames = value;
		if (value != null) {
			if (value.isEntity()) {
				((NodeImpl) value)._setParentPropertyName("propertyNames");
				((NodeImpl) value)._setParentPropertyType(ParentPropertyType.standard);
			} else if (value.isEntityList()) {
				List<?> entityList = (List<?>) ((UnionValue<?>) value).getValue();
				for (Object entity : entityList) {
					if (entity != null) {
						((NodeImpl) entity)._setParentPropertyName("propertyNames");
						((NodeImpl) entity)._setParentPropertyType(ParentPropertyType.array);
					}
				}
			} else if (value.isEntityMap()) {
				Map<String, ?> entityMap = (Map<String, ?>) ((UnionValue<?>) value).getValue();
				Collection<String> keys = entityMap.keySet();
				for (String key : keys) {
					NodeImpl entity = (NodeImpl) entityMap.get(key);
					if (entity != null) {
						entity._setParentPropertyName("propertyNames");
						entity._setParentPropertyType(ParentPropertyType.map);
						entity._setMapPropertyName(key);
					}
				}
			}
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
	public boolean isJSchema() {
		return true;
	}

	@Override
	public JsonSchemaJSchema asJSchema() {
		return this;
	}

	@Override
	public Object unionValue() {
		return this;
	}

	@Override
	public boolean isJSchemaList() {
		return false;
	}

	@Override
	public List<JsonSchemaJSchema> asJSchemaList() {
		throw new ClassCastException();
	}

	@Override
	public void accept(Visitor visitor) {
		JSDraft7Visitor viz = (JSDraft7Visitor) visitor;
		viz.visitJSchema(this);
	}

	@Override
	public Node emptyClone() {
		return new JSDraft7JSchemaImpl();
	}
}