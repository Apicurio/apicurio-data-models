package io.apicurio.datamodels.models.jsonschema.draft.draft6.io;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.apicurio.datamodels.models.RootNode;
import io.apicurio.datamodels.models.io.ModelWriter;
import io.apicurio.datamodels.models.jsonschema.JsonSchemaJSchema;
import io.apicurio.datamodels.models.jsonschema.draft.draft6.JSDraft6Document;
import io.apicurio.datamodels.models.jsonschema.draft.draft6.JSDraft6JSchema;
import io.apicurio.datamodels.models.union.BooleanJSchemaJSchemaListUnion;
import io.apicurio.datamodels.models.union.BooleanJSchemaUnion;
import io.apicurio.datamodels.models.union.StringStringListUnion;
import io.apicurio.datamodels.models.util.JsonUtil;
import io.apicurio.datamodels.models.util.WriterUtil;
import java.util.List;
import java.util.Map;

public class JSDraft6ModelWriter implements ModelWriter {

	public void writeDocument(JSDraft6Document node, ObjectNode json) {
		if (node == null) {
			return;
		}
		JsonUtil.setStringProperty(json, "$id", node.get$id());
		JsonUtil.setStringProperty(json, "$schema", node.get$schema());
		JsonUtil.setStringProperty(json, "title", node.getTitle());
		JsonUtil.setStringProperty(json, "description", node.getDescription());
		JsonUtil.setAnyProperty(json, "default", node.getDefault());
		JsonUtil.setAnyArrayProperty(json, "examples", node.getExamples());
		{
			Map<String, BooleanJSchemaUnion> unionMap = node.getDefinitions();
			if (unionMap != null && !unionMap.isEmpty()) {
				ObjectNode mapObject = JsonUtil.objectNode();
				unionMap.keySet().forEach(key -> {
					BooleanJSchemaUnion union = unionMap.get(key);
					if (union.isBoolean()) {
						mapObject.put(key, union.asBoolean());
					}
					if (union.isJSchema()) {
						ObjectNode object = JsonUtil.objectNode();
						this.writeJSchema((JSDraft6JSchema) union.asJSchema(), object);
						JsonUtil.setObjectProperty(mapObject, key, object);
					}
				});
				JsonUtil.setObjectProperty(json, "definitions", mapObject);
			}
		}
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
						this.writeJSchema((JSDraft6JSchema) union.asJSchema(), object);
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
						this.writeJSchema((JSDraft6JSchema) union.asJSchema(), object);
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
						this.writeJSchema((JSDraft6JSchema) union.asJSchema(), object);
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
					this.writeJSchema((JSDraft6JSchema) union.asJSchema(), jsonValue);
					JsonUtil.setObjectProperty(json, "not", jsonValue);
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
		{
			BooleanJSchemaJSchemaListUnion union = node.getItems();
			if (union != null) {
				if (union.isBoolean()) {
					JsonUtil.setBooleanProperty(json, "items", union.asBoolean());
				}
				if (union.isJSchema()) {
					ObjectNode jsonValue = JsonUtil.objectNode();
					this.writeJSchema((JSDraft6JSchema) union.asJSchema(), jsonValue);
					JsonUtil.setObjectProperty(json, "items", jsonValue);
				}
				if (union.isJSchemaList()) {
					List<? extends JsonSchemaJSchema> models = union.asJSchemaList();
					ArrayNode array = JsonUtil.arrayNode();
					models.forEach(model -> {
						ObjectNode object = JsonUtil.objectNode();
						this.writeJSchema((JSDraft6JSchema) model, object);
						JsonUtil.addToArray(array, object);
					});
					JsonUtil.setAnyProperty(json, "items", array);
				}
			}
		}
		{
			BooleanJSchemaUnion union = node.getAdditionalItems();
			if (union != null) {
				if (union.isBoolean()) {
					JsonUtil.setBooleanProperty(json, "additionalItems", union.asBoolean());
				}
				if (union.isJSchema()) {
					ObjectNode jsonValue = JsonUtil.objectNode();
					this.writeJSchema((JSDraft6JSchema) union.asJSchema(), jsonValue);
					JsonUtil.setObjectProperty(json, "additionalItems", jsonValue);
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
					this.writeJSchema((JSDraft6JSchema) union.asJSchema(), jsonValue);
					JsonUtil.setObjectProperty(json, "contains", jsonValue);
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
						this.writeJSchema((JSDraft6JSchema) union.asJSchema(), object);
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
						this.writeJSchema((JSDraft6JSchema) union.asJSchema(), object);
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
					this.writeJSchema((JSDraft6JSchema) union.asJSchema(), jsonValue);
					JsonUtil.setObjectProperty(json, "additionalProperties", jsonValue);
				}
			}
		}
		JsonUtil.setStringArrayProperty(json, "required", node.getRequired());
		JsonUtil.setIntegerProperty(json, "minProperties", node.getMinProperties());
		JsonUtil.setIntegerProperty(json, "maxProperties", node.getMaxProperties());
		JsonUtil.setAnyMapProperty(json, "dependencies", node.getDependencies());
		{
			BooleanJSchemaUnion union = node.getPropertyNames();
			if (union != null) {
				if (union.isBoolean()) {
					JsonUtil.setBooleanProperty(json, "propertyNames", union.asBoolean());
				}
				if (union.isJSchema()) {
					ObjectNode jsonValue = JsonUtil.objectNode();
					this.writeJSchema((JSDraft6JSchema) union.asJSchema(), jsonValue);
					JsonUtil.setObjectProperty(json, "propertyNames", jsonValue);
				}
			}
		}
		WriterUtil.writeExtraProperties(node, json);
	}

	@Override
	public ObjectNode writeRoot(RootNode node) {
		ObjectNode json = JsonUtil.objectNode();
		this.writeDocument((JSDraft6Document) node, json);
		return json;
	}

	public void writeJSchema(JSDraft6JSchema node, ObjectNode json) {
		if (node == null) {
			return;
		}
		JsonUtil.setStringProperty(json, "$ref", node.get$ref());
		JsonUtil.setStringProperty(json, "title", node.getTitle());
		JsonUtil.setStringProperty(json, "description", node.getDescription());
		JsonUtil.setAnyProperty(json, "default", node.getDefault());
		JsonUtil.setAnyArrayProperty(json, "examples", node.getExamples());
		{
			Map<String, BooleanJSchemaUnion> unionMap = node.getDefinitions();
			if (unionMap != null && !unionMap.isEmpty()) {
				ObjectNode mapObject = JsonUtil.objectNode();
				unionMap.keySet().forEach(key -> {
					BooleanJSchemaUnion union = unionMap.get(key);
					if (union.isBoolean()) {
						mapObject.put(key, union.asBoolean());
					}
					if (union.isJSchema()) {
						ObjectNode object = JsonUtil.objectNode();
						this.writeJSchema((JSDraft6JSchema) union.asJSchema(), object);
						JsonUtil.setObjectProperty(mapObject, key, object);
					}
				});
				JsonUtil.setObjectProperty(json, "definitions", mapObject);
			}
		}
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
						this.writeJSchema((JSDraft6JSchema) union.asJSchema(), object);
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
						this.writeJSchema((JSDraft6JSchema) union.asJSchema(), object);
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
						this.writeJSchema((JSDraft6JSchema) union.asJSchema(), object);
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
					this.writeJSchema((JSDraft6JSchema) union.asJSchema(), jsonValue);
					JsonUtil.setObjectProperty(json, "not", jsonValue);
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
		{
			BooleanJSchemaJSchemaListUnion union = node.getItems();
			if (union != null) {
				if (union.isBoolean()) {
					JsonUtil.setBooleanProperty(json, "items", union.asBoolean());
				}
				if (union.isJSchema()) {
					ObjectNode jsonValue = JsonUtil.objectNode();
					this.writeJSchema((JSDraft6JSchema) union.asJSchema(), jsonValue);
					JsonUtil.setObjectProperty(json, "items", jsonValue);
				}
				if (union.isJSchemaList()) {
					List<? extends JsonSchemaJSchema> models = union.asJSchemaList();
					ArrayNode array = JsonUtil.arrayNode();
					models.forEach(model -> {
						ObjectNode object = JsonUtil.objectNode();
						this.writeJSchema((JSDraft6JSchema) model, object);
						JsonUtil.addToArray(array, object);
					});
					JsonUtil.setAnyProperty(json, "items", array);
				}
			}
		}
		{
			BooleanJSchemaUnion union = node.getAdditionalItems();
			if (union != null) {
				if (union.isBoolean()) {
					JsonUtil.setBooleanProperty(json, "additionalItems", union.asBoolean());
				}
				if (union.isJSchema()) {
					ObjectNode jsonValue = JsonUtil.objectNode();
					this.writeJSchema((JSDraft6JSchema) union.asJSchema(), jsonValue);
					JsonUtil.setObjectProperty(json, "additionalItems", jsonValue);
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
					this.writeJSchema((JSDraft6JSchema) union.asJSchema(), jsonValue);
					JsonUtil.setObjectProperty(json, "contains", jsonValue);
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
						this.writeJSchema((JSDraft6JSchema) union.asJSchema(), object);
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
						this.writeJSchema((JSDraft6JSchema) union.asJSchema(), object);
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
					this.writeJSchema((JSDraft6JSchema) union.asJSchema(), jsonValue);
					JsonUtil.setObjectProperty(json, "additionalProperties", jsonValue);
				}
			}
		}
		JsonUtil.setStringArrayProperty(json, "required", node.getRequired());
		JsonUtil.setIntegerProperty(json, "minProperties", node.getMinProperties());
		JsonUtil.setIntegerProperty(json, "maxProperties", node.getMaxProperties());
		JsonUtil.setAnyMapProperty(json, "dependencies", node.getDependencies());
		{
			BooleanJSchemaUnion union = node.getPropertyNames();
			if (union != null) {
				if (union.isBoolean()) {
					JsonUtil.setBooleanProperty(json, "propertyNames", union.asBoolean());
				}
				if (union.isJSchema()) {
					ObjectNode jsonValue = JsonUtil.objectNode();
					this.writeJSchema((JSDraft6JSchema) union.asJSchema(), jsonValue);
					JsonUtil.setObjectProperty(json, "propertyNames", jsonValue);
				}
			}
		}
		WriterUtil.writeExtraProperties(node, json);
	}
}