package io.apicurio.datamodels.models.jsonschema.modern.v202012.io;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.apicurio.datamodels.models.RootNode;
import io.apicurio.datamodels.models.io.ModelWriter;
import io.apicurio.datamodels.models.jsonschema.modern.v202012.JS202012Document;
import io.apicurio.datamodels.models.jsonschema.modern.v202012.JS202012JSchema;
import io.apicurio.datamodels.models.union.BooleanJSchemaUnion;
import io.apicurio.datamodels.models.union.StringStringListUnion;
import io.apicurio.datamodels.models.util.JsonUtil;
import io.apicurio.datamodels.models.util.WriterUtil;
import java.util.List;
import java.util.Map;

public class JS202012ModelWriter implements ModelWriter {

	public void writeDocument(JS202012Document node, ObjectNode json) {
		if (node == null) {
			return;
		}
		JsonUtil.setStringProperty(json, "$id", node.get$id());
		JsonUtil.setStringProperty(json, "$schema", node.get$schema());
		JsonUtil.setStringProperty(json, "$anchor", node.get$anchor());
		JsonUtil.setStringProperty(json, "$dynamicRef", node.get$dynamicRef());
		JsonUtil.setStringProperty(json, "$dynamicAnchor", node.get$dynamicAnchor());
		JsonUtil.setStringProperty(json, "$comment", node.get$comment());
		{
			Map<String, BooleanJSchemaUnion> unionMap = node.get$defs();
			if (unionMap != null && !unionMap.isEmpty()) {
				ObjectNode mapObject = JsonUtil.objectNode();
				unionMap.keySet().forEach(key -> {
					BooleanJSchemaUnion union = unionMap.get(key);
					if (union.isBoolean()) {
						mapObject.put(key, union.asBoolean());
					}
					if (union.isJSchema()) {
						ObjectNode object = JsonUtil.objectNode();
						this.writeJSchema((JS202012JSchema) union.asJSchema(), object);
						JsonUtil.setObjectProperty(mapObject, key, object);
					}
				});
				JsonUtil.setObjectProperty(json, "$defs", mapObject);
			}
		}
		JsonUtil.setStringProperty(json, "title", node.getTitle());
		JsonUtil.setStringProperty(json, "description", node.getDescription());
		JsonUtil.setAnyProperty(json, "default", node.getDefault());
		JsonUtil.setAnyArrayProperty(json, "examples", node.getExamples());
		JsonUtil.setBooleanProperty(json, "readOnly", node.isReadOnly());
		JsonUtil.setBooleanProperty(json, "writeOnly", node.isWriteOnly());
		JsonUtil.setBooleanProperty(json, "deprecated", node.isDeprecated());
		{
			StringStringListUnion union = node.getType();
			if (union != null) {
				if (union.isString()) {
					JsonUtil.setStringProperty(json, "type", union.asString());
				}
				if (union.isStringList()) {
					JsonUtil.setStringArrayProperty(json, "type", union.asStringList());
				}
			}
		}
		JsonUtil.setAnyArrayProperty(json, "enum", node.getEnum());
		JsonUtil.setAnyProperty(json, "const", node.getConst());
		JsonUtil.setStringProperty(json, "format", node.getFormat());
		{
			List<BooleanJSchemaUnion> unionList = node.getAllOf();
			if (unionList != null && !unionList.isEmpty()) {
				ArrayNode array = JsonUtil.arrayNode();
				unionList.forEach(union -> {
					if (union.isBoolean()) {
						array.add(union.asBoolean());
					}
					if (union.isJSchema()) {
						ObjectNode object = JsonUtil.objectNode();
						this.writeJSchema((JS202012JSchema) union.asJSchema(), object);
						JsonUtil.addToArray(array, object);
					}
				});
				JsonUtil.setAnyProperty(json, "allOf", array);
			}
		}
		{
			List<BooleanJSchemaUnion> unionList = node.getAnyOf();
			if (unionList != null && !unionList.isEmpty()) {
				ArrayNode array = JsonUtil.arrayNode();
				unionList.forEach(union -> {
					if (union.isBoolean()) {
						array.add(union.asBoolean());
					}
					if (union.isJSchema()) {
						ObjectNode object = JsonUtil.objectNode();
						this.writeJSchema((JS202012JSchema) union.asJSchema(), object);
						JsonUtil.addToArray(array, object);
					}
				});
				JsonUtil.setAnyProperty(json, "anyOf", array);
			}
		}
		{
			List<BooleanJSchemaUnion> unionList = node.getOneOf();
			if (unionList != null && !unionList.isEmpty()) {
				ArrayNode array = JsonUtil.arrayNode();
				unionList.forEach(union -> {
					if (union.isBoolean()) {
						array.add(union.asBoolean());
					}
					if (union.isJSchema()) {
						ObjectNode object = JsonUtil.objectNode();
						this.writeJSchema((JS202012JSchema) union.asJSchema(), object);
						JsonUtil.addToArray(array, object);
					}
				});
				JsonUtil.setAnyProperty(json, "oneOf", array);
			}
		}
		{
			BooleanJSchemaUnion union = node.getNot();
			if (union != null) {
				if (union.isBoolean()) {
					JsonUtil.setBooleanProperty(json, "not", union.asBoolean());
				}
				if (union.isJSchema()) {
					ObjectNode jsonValue = JsonUtil.objectNode();
					this.writeJSchema((JS202012JSchema) union.asJSchema(), jsonValue);
					JsonUtil.setObjectProperty(json, "not", jsonValue);
				}
			}
		}
		{
			BooleanJSchemaUnion union = node.getIf();
			if (union != null) {
				if (union.isBoolean()) {
					JsonUtil.setBooleanProperty(json, "if", union.asBoolean());
				}
				if (union.isJSchema()) {
					ObjectNode jsonValue = JsonUtil.objectNode();
					this.writeJSchema((JS202012JSchema) union.asJSchema(), jsonValue);
					JsonUtil.setObjectProperty(json, "if", jsonValue);
				}
			}
		}
		{
			BooleanJSchemaUnion union = node.getThen();
			if (union != null) {
				if (union.isBoolean()) {
					JsonUtil.setBooleanProperty(json, "then", union.asBoolean());
				}
				if (union.isJSchema()) {
					ObjectNode jsonValue = JsonUtil.objectNode();
					this.writeJSchema((JS202012JSchema) union.asJSchema(), jsonValue);
					JsonUtil.setObjectProperty(json, "then", jsonValue);
				}
			}
		}
		{
			BooleanJSchemaUnion union = node.getElse();
			if (union != null) {
				if (union.isBoolean()) {
					JsonUtil.setBooleanProperty(json, "else", union.asBoolean());
				}
				if (union.isJSchema()) {
					ObjectNode jsonValue = JsonUtil.objectNode();
					this.writeJSchema((JS202012JSchema) union.asJSchema(), jsonValue);
					JsonUtil.setObjectProperty(json, "else", jsonValue);
				}
			}
		}
		JsonUtil.setNumberProperty(json, "multipleOf", node.getMultipleOf());
		JsonUtil.setNumberProperty(json, "minimum", node.getMinimum());
		JsonUtil.setNumberProperty(json, "maximum", node.getMaximum());
		JsonUtil.setNumberProperty(json, "exclusiveMinimum", node.getExclusiveMinimum());
		JsonUtil.setNumberProperty(json, "exclusiveMaximum", node.getExclusiveMaximum());
		JsonUtil.setIntegerProperty(json, "minLength", node.getMinLength());
		JsonUtil.setIntegerProperty(json, "maxLength", node.getMaxLength());
		JsonUtil.setStringProperty(json, "pattern", node.getPattern());
		JsonUtil.setStringProperty(json, "contentMediaType", node.getContentMediaType());
		JsonUtil.setStringProperty(json, "contentEncoding", node.getContentEncoding());
		{
			BooleanJSchemaUnion union = node.getContentSchema();
			if (union != null) {
				if (union.isBoolean()) {
					JsonUtil.setBooleanProperty(json, "contentSchema", union.asBoolean());
				}
				if (union.isJSchema()) {
					ObjectNode jsonValue = JsonUtil.objectNode();
					this.writeJSchema((JS202012JSchema) union.asJSchema(), jsonValue);
					JsonUtil.setObjectProperty(json, "contentSchema", jsonValue);
				}
			}
		}
		{
			List<BooleanJSchemaUnion> unionList = node.getPrefixItems();
			if (unionList != null && !unionList.isEmpty()) {
				ArrayNode array = JsonUtil.arrayNode();
				unionList.forEach(union -> {
					if (union.isBoolean()) {
						array.add(union.asBoolean());
					}
					if (union.isJSchema()) {
						ObjectNode object = JsonUtil.objectNode();
						this.writeJSchema((JS202012JSchema) union.asJSchema(), object);
						JsonUtil.addToArray(array, object);
					}
				});
				JsonUtil.setAnyProperty(json, "prefixItems", array);
			}
		}
		{
			BooleanJSchemaUnion union = node.getItems();
			if (union != null) {
				if (union.isBoolean()) {
					JsonUtil.setBooleanProperty(json, "items", union.asBoolean());
				}
				if (union.isJSchema()) {
					ObjectNode jsonValue = JsonUtil.objectNode();
					this.writeJSchema((JS202012JSchema) union.asJSchema(), jsonValue);
					JsonUtil.setObjectProperty(json, "items", jsonValue);
				}
			}
		}
		JsonUtil.setIntegerProperty(json, "minItems", node.getMinItems());
		JsonUtil.setIntegerProperty(json, "maxItems", node.getMaxItems());
		JsonUtil.setBooleanProperty(json, "uniqueItems", node.isUniqueItems());
		{
			BooleanJSchemaUnion union = node.getContains();
			if (union != null) {
				if (union.isBoolean()) {
					JsonUtil.setBooleanProperty(json, "contains", union.asBoolean());
				}
				if (union.isJSchema()) {
					ObjectNode jsonValue = JsonUtil.objectNode();
					this.writeJSchema((JS202012JSchema) union.asJSchema(), jsonValue);
					JsonUtil.setObjectProperty(json, "contains", jsonValue);
				}
			}
		}
		JsonUtil.setIntegerProperty(json, "minContains", node.getMinContains());
		JsonUtil.setIntegerProperty(json, "maxContains", node.getMaxContains());
		{
			BooleanJSchemaUnion union = node.getUnevaluatedItems();
			if (union != null) {
				if (union.isBoolean()) {
					JsonUtil.setBooleanProperty(json, "unevaluatedItems", union.asBoolean());
				}
				if (union.isJSchema()) {
					ObjectNode jsonValue = JsonUtil.objectNode();
					this.writeJSchema((JS202012JSchema) union.asJSchema(), jsonValue);
					JsonUtil.setObjectProperty(json, "unevaluatedItems", jsonValue);
				}
			}
		}
		{
			Map<String, BooleanJSchemaUnion> unionMap = node.getProperties();
			if (unionMap != null && !unionMap.isEmpty()) {
				ObjectNode mapObject = JsonUtil.objectNode();
				unionMap.keySet().forEach(key -> {
					BooleanJSchemaUnion union = unionMap.get(key);
					if (union.isBoolean()) {
						mapObject.put(key, union.asBoolean());
					}
					if (union.isJSchema()) {
						ObjectNode object = JsonUtil.objectNode();
						this.writeJSchema((JS202012JSchema) union.asJSchema(), object);
						JsonUtil.setObjectProperty(mapObject, key, object);
					}
				});
				JsonUtil.setObjectProperty(json, "properties", mapObject);
			}
		}
		{
			Map<String, BooleanJSchemaUnion> unionMap = node.getPatternProperties();
			if (unionMap != null && !unionMap.isEmpty()) {
				ObjectNode mapObject = JsonUtil.objectNode();
				unionMap.keySet().forEach(key -> {
					BooleanJSchemaUnion union = unionMap.get(key);
					if (union.isBoolean()) {
						mapObject.put(key, union.asBoolean());
					}
					if (union.isJSchema()) {
						ObjectNode object = JsonUtil.objectNode();
						this.writeJSchema((JS202012JSchema) union.asJSchema(), object);
						JsonUtil.setObjectProperty(mapObject, key, object);
					}
				});
				JsonUtil.setObjectProperty(json, "patternProperties", mapObject);
			}
		}
		{
			BooleanJSchemaUnion union = node.getAdditionalProperties();
			if (union != null) {
				if (union.isBoolean()) {
					JsonUtil.setBooleanProperty(json, "additionalProperties", union.asBoolean());
				}
				if (union.isJSchema()) {
					ObjectNode jsonValue = JsonUtil.objectNode();
					this.writeJSchema((JS202012JSchema) union.asJSchema(), jsonValue);
					JsonUtil.setObjectProperty(json, "additionalProperties", jsonValue);
				}
			}
		}
		JsonUtil.setStringArrayProperty(json, "required", node.getRequired());
		JsonUtil.setIntegerProperty(json, "minProperties", node.getMinProperties());
		JsonUtil.setIntegerProperty(json, "maxProperties", node.getMaxProperties());
		{
			Map<String, BooleanJSchemaUnion> unionMap = node.getDependentSchemas();
			if (unionMap != null && !unionMap.isEmpty()) {
				ObjectNode mapObject = JsonUtil.objectNode();
				unionMap.keySet().forEach(key -> {
					BooleanJSchemaUnion union = unionMap.get(key);
					if (union.isBoolean()) {
						mapObject.put(key, union.asBoolean());
					}
					if (union.isJSchema()) {
						ObjectNode object = JsonUtil.objectNode();
						this.writeJSchema((JS202012JSchema) union.asJSchema(), object);
						JsonUtil.setObjectProperty(mapObject, key, object);
					}
				});
				JsonUtil.setObjectProperty(json, "dependentSchemas", mapObject);
			}
		}
		JsonUtil.setAnyMapProperty(json, "dependentRequired", node.getDependentRequired());
		{
			BooleanJSchemaUnion union = node.getPropertyNames();
			if (union != null) {
				if (union.isBoolean()) {
					JsonUtil.setBooleanProperty(json, "propertyNames", union.asBoolean());
				}
				if (union.isJSchema()) {
					ObjectNode jsonValue = JsonUtil.objectNode();
					this.writeJSchema((JS202012JSchema) union.asJSchema(), jsonValue);
					JsonUtil.setObjectProperty(json, "propertyNames", jsonValue);
				}
			}
		}
		{
			BooleanJSchemaUnion union = node.getUnevaluatedProperties();
			if (union != null) {
				if (union.isBoolean()) {
					JsonUtil.setBooleanProperty(json, "unevaluatedProperties", union.asBoolean());
				}
				if (union.isJSchema()) {
					ObjectNode jsonValue = JsonUtil.objectNode();
					this.writeJSchema((JS202012JSchema) union.asJSchema(), jsonValue);
					JsonUtil.setObjectProperty(json, "unevaluatedProperties", jsonValue);
				}
			}
		}
		WriterUtil.writeExtraProperties(node, json);
	}

	@Override
	public ObjectNode writeRoot(RootNode node) {
		ObjectNode json = JsonUtil.objectNode();
		this.writeDocument((JS202012Document) node, json);
		return json;
	}

	public void writeJSchema(JS202012JSchema node, ObjectNode json) {
		if (node == null) {
			return;
		}
		JsonUtil.setStringProperty(json, "$ref", node.get$ref());
		JsonUtil.setStringProperty(json, "$anchor", node.get$anchor());
		JsonUtil.setStringProperty(json, "$dynamicRef", node.get$dynamicRef());
		JsonUtil.setStringProperty(json, "$dynamicAnchor", node.get$dynamicAnchor());
		JsonUtil.setStringProperty(json, "$comment", node.get$comment());
		{
			Map<String, BooleanJSchemaUnion> unionMap = node.get$defs();
			if (unionMap != null && !unionMap.isEmpty()) {
				ObjectNode mapObject = JsonUtil.objectNode();
				unionMap.keySet().forEach(key -> {
					BooleanJSchemaUnion union = unionMap.get(key);
					if (union.isBoolean()) {
						mapObject.put(key, union.asBoolean());
					}
					if (union.isJSchema()) {
						ObjectNode object = JsonUtil.objectNode();
						this.writeJSchema((JS202012JSchema) union.asJSchema(), object);
						JsonUtil.setObjectProperty(mapObject, key, object);
					}
				});
				JsonUtil.setObjectProperty(json, "$defs", mapObject);
			}
		}
		JsonUtil.setStringProperty(json, "title", node.getTitle());
		JsonUtil.setStringProperty(json, "description", node.getDescription());
		JsonUtil.setAnyProperty(json, "default", node.getDefault());
		JsonUtil.setAnyArrayProperty(json, "examples", node.getExamples());
		JsonUtil.setBooleanProperty(json, "readOnly", node.isReadOnly());
		JsonUtil.setBooleanProperty(json, "writeOnly", node.isWriteOnly());
		JsonUtil.setBooleanProperty(json, "deprecated", node.isDeprecated());
		{
			StringStringListUnion union = node.getType();
			if (union != null) {
				if (union.isString()) {
					JsonUtil.setStringProperty(json, "type", union.asString());
				}
				if (union.isStringList()) {
					JsonUtil.setStringArrayProperty(json, "type", union.asStringList());
				}
			}
		}
		JsonUtil.setAnyArrayProperty(json, "enum", node.getEnum());
		JsonUtil.setAnyProperty(json, "const", node.getConst());
		JsonUtil.setStringProperty(json, "format", node.getFormat());
		{
			List<BooleanJSchemaUnion> unionList = node.getAllOf();
			if (unionList != null && !unionList.isEmpty()) {
				ArrayNode array = JsonUtil.arrayNode();
				unionList.forEach(union -> {
					if (union.isBoolean()) {
						array.add(union.asBoolean());
					}
					if (union.isJSchema()) {
						ObjectNode object = JsonUtil.objectNode();
						this.writeJSchema((JS202012JSchema) union.asJSchema(), object);
						JsonUtil.addToArray(array, object);
					}
				});
				JsonUtil.setAnyProperty(json, "allOf", array);
			}
		}
		{
			List<BooleanJSchemaUnion> unionList = node.getAnyOf();
			if (unionList != null && !unionList.isEmpty()) {
				ArrayNode array = JsonUtil.arrayNode();
				unionList.forEach(union -> {
					if (union.isBoolean()) {
						array.add(union.asBoolean());
					}
					if (union.isJSchema()) {
						ObjectNode object = JsonUtil.objectNode();
						this.writeJSchema((JS202012JSchema) union.asJSchema(), object);
						JsonUtil.addToArray(array, object);
					}
				});
				JsonUtil.setAnyProperty(json, "anyOf", array);
			}
		}
		{
			List<BooleanJSchemaUnion> unionList = node.getOneOf();
			if (unionList != null && !unionList.isEmpty()) {
				ArrayNode array = JsonUtil.arrayNode();
				unionList.forEach(union -> {
					if (union.isBoolean()) {
						array.add(union.asBoolean());
					}
					if (union.isJSchema()) {
						ObjectNode object = JsonUtil.objectNode();
						this.writeJSchema((JS202012JSchema) union.asJSchema(), object);
						JsonUtil.addToArray(array, object);
					}
				});
				JsonUtil.setAnyProperty(json, "oneOf", array);
			}
		}
		{
			BooleanJSchemaUnion union = node.getNot();
			if (union != null) {
				if (union.isBoolean()) {
					JsonUtil.setBooleanProperty(json, "not", union.asBoolean());
				}
				if (union.isJSchema()) {
					ObjectNode jsonValue = JsonUtil.objectNode();
					this.writeJSchema((JS202012JSchema) union.asJSchema(), jsonValue);
					JsonUtil.setObjectProperty(json, "not", jsonValue);
				}
			}
		}
		{
			BooleanJSchemaUnion union = node.getIf();
			if (union != null) {
				if (union.isBoolean()) {
					JsonUtil.setBooleanProperty(json, "if", union.asBoolean());
				}
				if (union.isJSchema()) {
					ObjectNode jsonValue = JsonUtil.objectNode();
					this.writeJSchema((JS202012JSchema) union.asJSchema(), jsonValue);
					JsonUtil.setObjectProperty(json, "if", jsonValue);
				}
			}
		}
		{
			BooleanJSchemaUnion union = node.getThen();
			if (union != null) {
				if (union.isBoolean()) {
					JsonUtil.setBooleanProperty(json, "then", union.asBoolean());
				}
				if (union.isJSchema()) {
					ObjectNode jsonValue = JsonUtil.objectNode();
					this.writeJSchema((JS202012JSchema) union.asJSchema(), jsonValue);
					JsonUtil.setObjectProperty(json, "then", jsonValue);
				}
			}
		}
		{
			BooleanJSchemaUnion union = node.getElse();
			if (union != null) {
				if (union.isBoolean()) {
					JsonUtil.setBooleanProperty(json, "else", union.asBoolean());
				}
				if (union.isJSchema()) {
					ObjectNode jsonValue = JsonUtil.objectNode();
					this.writeJSchema((JS202012JSchema) union.asJSchema(), jsonValue);
					JsonUtil.setObjectProperty(json, "else", jsonValue);
				}
			}
		}
		JsonUtil.setNumberProperty(json, "multipleOf", node.getMultipleOf());
		JsonUtil.setNumberProperty(json, "minimum", node.getMinimum());
		JsonUtil.setNumberProperty(json, "maximum", node.getMaximum());
		JsonUtil.setNumberProperty(json, "exclusiveMinimum", node.getExclusiveMinimum());
		JsonUtil.setNumberProperty(json, "exclusiveMaximum", node.getExclusiveMaximum());
		JsonUtil.setIntegerProperty(json, "minLength", node.getMinLength());
		JsonUtil.setIntegerProperty(json, "maxLength", node.getMaxLength());
		JsonUtil.setStringProperty(json, "pattern", node.getPattern());
		JsonUtil.setStringProperty(json, "contentMediaType", node.getContentMediaType());
		JsonUtil.setStringProperty(json, "contentEncoding", node.getContentEncoding());
		{
			BooleanJSchemaUnion union = node.getContentSchema();
			if (union != null) {
				if (union.isBoolean()) {
					JsonUtil.setBooleanProperty(json, "contentSchema", union.asBoolean());
				}
				if (union.isJSchema()) {
					ObjectNode jsonValue = JsonUtil.objectNode();
					this.writeJSchema((JS202012JSchema) union.asJSchema(), jsonValue);
					JsonUtil.setObjectProperty(json, "contentSchema", jsonValue);
				}
			}
		}
		{
			List<BooleanJSchemaUnion> unionList = node.getPrefixItems();
			if (unionList != null && !unionList.isEmpty()) {
				ArrayNode array = JsonUtil.arrayNode();
				unionList.forEach(union -> {
					if (union.isBoolean()) {
						array.add(union.asBoolean());
					}
					if (union.isJSchema()) {
						ObjectNode object = JsonUtil.objectNode();
						this.writeJSchema((JS202012JSchema) union.asJSchema(), object);
						JsonUtil.addToArray(array, object);
					}
				});
				JsonUtil.setAnyProperty(json, "prefixItems", array);
			}
		}
		{
			BooleanJSchemaUnion union = node.getItems();
			if (union != null) {
				if (union.isBoolean()) {
					JsonUtil.setBooleanProperty(json, "items", union.asBoolean());
				}
				if (union.isJSchema()) {
					ObjectNode jsonValue = JsonUtil.objectNode();
					this.writeJSchema((JS202012JSchema) union.asJSchema(), jsonValue);
					JsonUtil.setObjectProperty(json, "items", jsonValue);
				}
			}
		}
		JsonUtil.setIntegerProperty(json, "minItems", node.getMinItems());
		JsonUtil.setIntegerProperty(json, "maxItems", node.getMaxItems());
		JsonUtil.setBooleanProperty(json, "uniqueItems", node.isUniqueItems());
		{
			BooleanJSchemaUnion union = node.getContains();
			if (union != null) {
				if (union.isBoolean()) {
					JsonUtil.setBooleanProperty(json, "contains", union.asBoolean());
				}
				if (union.isJSchema()) {
					ObjectNode jsonValue = JsonUtil.objectNode();
					this.writeJSchema((JS202012JSchema) union.asJSchema(), jsonValue);
					JsonUtil.setObjectProperty(json, "contains", jsonValue);
				}
			}
		}
		JsonUtil.setIntegerProperty(json, "minContains", node.getMinContains());
		JsonUtil.setIntegerProperty(json, "maxContains", node.getMaxContains());
		{
			BooleanJSchemaUnion union = node.getUnevaluatedItems();
			if (union != null) {
				if (union.isBoolean()) {
					JsonUtil.setBooleanProperty(json, "unevaluatedItems", union.asBoolean());
				}
				if (union.isJSchema()) {
					ObjectNode jsonValue = JsonUtil.objectNode();
					this.writeJSchema((JS202012JSchema) union.asJSchema(), jsonValue);
					JsonUtil.setObjectProperty(json, "unevaluatedItems", jsonValue);
				}
			}
		}
		{
			Map<String, BooleanJSchemaUnion> unionMap = node.getProperties();
			if (unionMap != null && !unionMap.isEmpty()) {
				ObjectNode mapObject = JsonUtil.objectNode();
				unionMap.keySet().forEach(key -> {
					BooleanJSchemaUnion union = unionMap.get(key);
					if (union.isBoolean()) {
						mapObject.put(key, union.asBoolean());
					}
					if (union.isJSchema()) {
						ObjectNode object = JsonUtil.objectNode();
						this.writeJSchema((JS202012JSchema) union.asJSchema(), object);
						JsonUtil.setObjectProperty(mapObject, key, object);
					}
				});
				JsonUtil.setObjectProperty(json, "properties", mapObject);
			}
		}
		{
			Map<String, BooleanJSchemaUnion> unionMap = node.getPatternProperties();
			if (unionMap != null && !unionMap.isEmpty()) {
				ObjectNode mapObject = JsonUtil.objectNode();
				unionMap.keySet().forEach(key -> {
					BooleanJSchemaUnion union = unionMap.get(key);
					if (union.isBoolean()) {
						mapObject.put(key, union.asBoolean());
					}
					if (union.isJSchema()) {
						ObjectNode object = JsonUtil.objectNode();
						this.writeJSchema((JS202012JSchema) union.asJSchema(), object);
						JsonUtil.setObjectProperty(mapObject, key, object);
					}
				});
				JsonUtil.setObjectProperty(json, "patternProperties", mapObject);
			}
		}
		{
			BooleanJSchemaUnion union = node.getAdditionalProperties();
			if (union != null) {
				if (union.isBoolean()) {
					JsonUtil.setBooleanProperty(json, "additionalProperties", union.asBoolean());
				}
				if (union.isJSchema()) {
					ObjectNode jsonValue = JsonUtil.objectNode();
					this.writeJSchema((JS202012JSchema) union.asJSchema(), jsonValue);
					JsonUtil.setObjectProperty(json, "additionalProperties", jsonValue);
				}
			}
		}
		JsonUtil.setStringArrayProperty(json, "required", node.getRequired());
		JsonUtil.setIntegerProperty(json, "minProperties", node.getMinProperties());
		JsonUtil.setIntegerProperty(json, "maxProperties", node.getMaxProperties());
		{
			Map<String, BooleanJSchemaUnion> unionMap = node.getDependentSchemas();
			if (unionMap != null && !unionMap.isEmpty()) {
				ObjectNode mapObject = JsonUtil.objectNode();
				unionMap.keySet().forEach(key -> {
					BooleanJSchemaUnion union = unionMap.get(key);
					if (union.isBoolean()) {
						mapObject.put(key, union.asBoolean());
					}
					if (union.isJSchema()) {
						ObjectNode object = JsonUtil.objectNode();
						this.writeJSchema((JS202012JSchema) union.asJSchema(), object);
						JsonUtil.setObjectProperty(mapObject, key, object);
					}
				});
				JsonUtil.setObjectProperty(json, "dependentSchemas", mapObject);
			}
		}
		JsonUtil.setAnyMapProperty(json, "dependentRequired", node.getDependentRequired());
		{
			BooleanJSchemaUnion union = node.getPropertyNames();
			if (union != null) {
				if (union.isBoolean()) {
					JsonUtil.setBooleanProperty(json, "propertyNames", union.asBoolean());
				}
				if (union.isJSchema()) {
					ObjectNode jsonValue = JsonUtil.objectNode();
					this.writeJSchema((JS202012JSchema) union.asJSchema(), jsonValue);
					JsonUtil.setObjectProperty(json, "propertyNames", jsonValue);
				}
			}
		}
		{
			BooleanJSchemaUnion union = node.getUnevaluatedProperties();
			if (union != null) {
				if (union.isBoolean()) {
					JsonUtil.setBooleanProperty(json, "unevaluatedProperties", union.asBoolean());
				}
				if (union.isJSchema()) {
					ObjectNode jsonValue = JsonUtil.objectNode();
					this.writeJSchema((JS202012JSchema) union.asJSchema(), jsonValue);
					JsonUtil.setObjectProperty(json, "unevaluatedProperties", jsonValue);
				}
			}
		}
		WriterUtil.writeExtraProperties(node, json);
	}
}