package io.apicurio.datamodels.models.jsonschema.draft.draft6.io;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.apicurio.datamodels.models.RootNode;
import io.apicurio.datamodels.models.io.ModelReader;
import io.apicurio.datamodels.models.jsonschema.draft.draft6.JSDraft6Document;
import io.apicurio.datamodels.models.jsonschema.draft.draft6.JSDraft6DocumentImpl;
import io.apicurio.datamodels.models.jsonschema.draft.draft6.JSDraft6JSchema;
import io.apicurio.datamodels.models.union.BooleanJSchemaUnion;
import io.apicurio.datamodels.models.union.BooleanUnionValue;
import io.apicurio.datamodels.models.union.BooleanUnionValueImpl;
import io.apicurio.datamodels.models.union.JSchemaListUnionValue;
import io.apicurio.datamodels.models.union.JSchemaListUnionValueImpl;
import io.apicurio.datamodels.models.union.StringListUnionValue;
import io.apicurio.datamodels.models.union.StringListUnionValueImpl;
import io.apicurio.datamodels.models.union.StringUnionValue;
import io.apicurio.datamodels.models.union.StringUnionValueImpl;
import io.apicurio.datamodels.models.util.JsonUtil;
import io.apicurio.datamodels.models.util.ReaderUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JSDraft6ModelReader implements ModelReader {

	public void readDocument(ObjectNode json, JSDraft6Document node) {
		{
			String value = JsonUtil.consumeStringProperty(json, "$id");
			node.set$id(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "$schema");
			node.set$schema(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "title");
			node.setTitle(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "description");
			node.setDescription(value);
		}
		{
			JsonNode value = JsonUtil.consumeAnyProperty(json, "default");
			node.setDefault(value);
		}
		{
			List<JsonNode> value = JsonUtil.consumeAnyArrayProperty(json, "examples");
			node.setExamples(value);
		}
		{
			ObjectNode mapObject = JsonUtil.consumeObjectProperty(json, "definitions");
			if (mapObject != null) {
				List<String> keys = JsonUtil.keys(mapObject);
				keys.forEach(key -> {
					JsonNode value = JsonUtil.consumeAnyProperty(mapObject, key);
					if (value != null) {
						if (JsonUtil.isObject(value)) {
							ObjectNode object = JsonUtil.toObject(value);
							JSDraft6JSchema model = (JSDraft6JSchema) node.createJSchema();
							readJSchema(object, model);
							node.addDefinition(key, model);
						} else if (JsonUtil.isBoolean(value)) {
							Boolean pValue = JsonUtil.toBoolean(value);
							BooleanUnionValue unionValue = new BooleanUnionValueImpl(pValue);
							node.addDefinition(key, unionValue);
						}
					}
				});
			}
		}
		{
			JsonNode value = JsonUtil.consumeAnyProperty(json, "type");
			if (value != null) {
				if (JsonUtil.isArray(value)) {
					List<JsonNode> array = JsonUtil.toList(value);
					List<String> items = new ArrayList<>();
					array.forEach(item -> {
						String pValue = JsonUtil.toString(item);
						items.add(pValue);
					});
					StringListUnionValue unionValue = new StringListUnionValueImpl(items);
					node.setType(unionValue);
				} else if (JsonUtil.isString(value)) {
					String pValue = JsonUtil.toString(value);
					StringUnionValue unionValue = new StringUnionValueImpl(pValue);
					node.setType(unionValue);
				} else {
					node.addExtraProperty("type", value);
				}
			}
		}
		{
			List<JsonNode> value = JsonUtil.consumeAnyArrayProperty(json, "enum");
			node.setEnum(value);
		}
		{
			JsonNode value = JsonUtil.consumeAnyProperty(json, "const");
			node.setConst(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "format");
			node.setFormat(value);
		}
		{
			List<JsonNode> array = JsonUtil.consumeAnyArrayProperty(json, "allOf");
			if (array != null) {
				array.forEach(value -> {
					if (JsonUtil.isObject(value)) {
						ObjectNode object = JsonUtil.toObject(value);
						JSDraft6JSchema model = (JSDraft6JSchema) node.createJSchema();
						readJSchema(object, model);
						node.addAllOf(model);
					} else if (JsonUtil.isBoolean(value)) {
						Boolean pValue = JsonUtil.toBoolean(value);
						BooleanUnionValue unionValue = new BooleanUnionValueImpl(pValue);
						node.addAllOf(unionValue);
					}
				});
			}
		}
		{
			List<JsonNode> array = JsonUtil.consumeAnyArrayProperty(json, "anyOf");
			if (array != null) {
				array.forEach(value -> {
					if (JsonUtil.isObject(value)) {
						ObjectNode object = JsonUtil.toObject(value);
						JSDraft6JSchema model = (JSDraft6JSchema) node.createJSchema();
						readJSchema(object, model);
						node.addAnyOf(model);
					} else if (JsonUtil.isBoolean(value)) {
						Boolean pValue = JsonUtil.toBoolean(value);
						BooleanUnionValue unionValue = new BooleanUnionValueImpl(pValue);
						node.addAnyOf(unionValue);
					}
				});
			}
		}
		{
			List<JsonNode> array = JsonUtil.consumeAnyArrayProperty(json, "oneOf");
			if (array != null) {
				array.forEach(value -> {
					if (JsonUtil.isObject(value)) {
						ObjectNode object = JsonUtil.toObject(value);
						JSDraft6JSchema model = (JSDraft6JSchema) node.createJSchema();
						readJSchema(object, model);
						node.addOneOf(model);
					} else if (JsonUtil.isBoolean(value)) {
						Boolean pValue = JsonUtil.toBoolean(value);
						BooleanUnionValue unionValue = new BooleanUnionValueImpl(pValue);
						node.addOneOf(unionValue);
					}
				});
			}
		}
		{
			JsonNode value = JsonUtil.consumeAnyProperty(json, "not");
			if (value != null) {
				if (JsonUtil.isObject(value)) {
					ObjectNode object = JsonUtil.toObject(value);
					node.setNot(node.createJSchema());
					readJSchema(object, (JSDraft6JSchema) node.getNot());
				} else if (JsonUtil.isBoolean(value)) {
					Boolean pValue = JsonUtil.toBoolean(value);
					BooleanUnionValue unionValue = new BooleanUnionValueImpl(pValue);
					node.setNot(unionValue);
				} else {
					node.addExtraProperty("not", value);
				}
			}
		}
		{
			Number value = JsonUtil.consumeNumberProperty(json, "multipleOf");
			node.setMultipleOf(value);
		}
		{
			Number value = JsonUtil.consumeNumberProperty(json, "minimum");
			node.setMinimum(value);
		}
		{
			Number value = JsonUtil.consumeNumberProperty(json, "maximum");
			node.setMaximum(value);
		}
		{
			Number value = JsonUtil.consumeNumberProperty(json, "exclusiveMinimum");
			node.setExclusiveMinimum(value);
		}
		{
			Number value = JsonUtil.consumeNumberProperty(json, "exclusiveMaximum");
			node.setExclusiveMaximum(value);
		}
		{
			Integer value = JsonUtil.consumeIntegerProperty(json, "minLength");
			node.setMinLength(value);
		}
		{
			Integer value = JsonUtil.consumeIntegerProperty(json, "maxLength");
			node.setMaxLength(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "pattern");
			node.setPattern(value);
		}
		{
			JsonNode value = JsonUtil.consumeAnyProperty(json, "items");
			if (value != null) {
				if (JsonUtil.isObject(value)) {
					ObjectNode object = JsonUtil.toObject(value);
					node.setItems(node.createJSchema());
					readJSchema(object, (JSDraft6JSchema) node.getItems());
				} else if (JsonUtil.isArray(value)) {
					List<JsonNode> array = JsonUtil.toList(value);
					List<JSDraft6JSchema> models = new ArrayList<>();
					array.forEach(item -> {
						ObjectNode object = JsonUtil.toObject(item);
						JSDraft6JSchema model = (JSDraft6JSchema) node.createJSchema();
						this.readJSchema(object, model);
						models.add(model);
					});
					@SuppressWarnings({"unchecked", "rawtypes"})
					JSchemaListUnionValue unionValue = new JSchemaListUnionValueImpl((List) models);
					node.setItems(unionValue);
				} else if (JsonUtil.isBoolean(value)) {
					Boolean pValue = JsonUtil.toBoolean(value);
					BooleanUnionValue unionValue = new BooleanUnionValueImpl(pValue);
					node.setItems(unionValue);
				} else {
					node.addExtraProperty("items", value);
				}
			}
		}
		{
			JsonNode value = JsonUtil.consumeAnyProperty(json, "additionalItems");
			if (value != null) {
				if (JsonUtil.isObject(value)) {
					ObjectNode object = JsonUtil.toObject(value);
					node.setAdditionalItems(node.createJSchema());
					readJSchema(object, (JSDraft6JSchema) node.getAdditionalItems());
				} else if (JsonUtil.isBoolean(value)) {
					Boolean pValue = JsonUtil.toBoolean(value);
					BooleanUnionValue unionValue = new BooleanUnionValueImpl(pValue);
					node.setAdditionalItems(unionValue);
				} else {
					node.addExtraProperty("additionalItems", value);
				}
			}
		}
		{
			Integer value = JsonUtil.consumeIntegerProperty(json, "minItems");
			node.setMinItems(value);
		}
		{
			Integer value = JsonUtil.consumeIntegerProperty(json, "maxItems");
			node.setMaxItems(value);
		}
		{
			Boolean value = JsonUtil.consumeBooleanProperty(json, "uniqueItems");
			node.setUniqueItems(value);
		}
		{
			JsonNode value = JsonUtil.consumeAnyProperty(json, "contains");
			if (value != null) {
				if (JsonUtil.isObject(value)) {
					ObjectNode object = JsonUtil.toObject(value);
					node.setContains(node.createJSchema());
					readJSchema(object, (JSDraft6JSchema) node.getContains());
				} else if (JsonUtil.isBoolean(value)) {
					Boolean pValue = JsonUtil.toBoolean(value);
					BooleanUnionValue unionValue = new BooleanUnionValueImpl(pValue);
					node.setContains(unionValue);
				} else {
					node.addExtraProperty("contains", value);
				}
			}
		}
		{
			ObjectNode mapObject = JsonUtil.consumeObjectProperty(json, "properties");
			if (mapObject != null) {
				List<String> keys = JsonUtil.keys(mapObject);
				keys.forEach(key -> {
					JsonNode value = JsonUtil.consumeAnyProperty(mapObject, key);
					if (value != null) {
						if (JsonUtil.isObject(value)) {
							ObjectNode object = JsonUtil.toObject(value);
							JSDraft6JSchema model = (JSDraft6JSchema) node.createJSchema();
							readJSchema(object, model);
							node.addProperty(key, model);
						} else if (JsonUtil.isBoolean(value)) {
							Boolean pValue = JsonUtil.toBoolean(value);
							BooleanUnionValue unionValue = new BooleanUnionValueImpl(pValue);
							node.addProperty(key, unionValue);
						}
					}
				});
			}
		}
		{
			ObjectNode mapObject = JsonUtil.consumeObjectProperty(json, "patternProperties");
			if (mapObject != null) {
				List<String> keys = JsonUtil.keys(mapObject);
				keys.forEach(key -> {
					JsonNode value = JsonUtil.consumeAnyProperty(mapObject, key);
					if (value != null) {
						if (JsonUtil.isObject(value)) {
							ObjectNode object = JsonUtil.toObject(value);
							JSDraft6JSchema model = (JSDraft6JSchema) node.createJSchema();
							readJSchema(object, model);
							node.addPatternProperty(key, model);
						} else if (JsonUtil.isBoolean(value)) {
							Boolean pValue = JsonUtil.toBoolean(value);
							BooleanUnionValue unionValue = new BooleanUnionValueImpl(pValue);
							node.addPatternProperty(key, unionValue);
						}
					}
				});
			}
		}
		{
			JsonNode value = JsonUtil.consumeAnyProperty(json, "additionalProperties");
			if (value != null) {
				if (JsonUtil.isObject(value)) {
					ObjectNode object = JsonUtil.toObject(value);
					node.setAdditionalProperties(node.createJSchema());
					readJSchema(object, (JSDraft6JSchema) node.getAdditionalProperties());
				} else if (JsonUtil.isBoolean(value)) {
					Boolean pValue = JsonUtil.toBoolean(value);
					BooleanUnionValue unionValue = new BooleanUnionValueImpl(pValue);
					node.setAdditionalProperties(unionValue);
				} else {
					node.addExtraProperty("additionalProperties", value);
				}
			}
		}
		{
			List<String> value = JsonUtil.consumeStringArrayProperty(json, "required");
			node.setRequired(value);
		}
		{
			Integer value = JsonUtil.consumeIntegerProperty(json, "minProperties");
			node.setMinProperties(value);
		}
		{
			Integer value = JsonUtil.consumeIntegerProperty(json, "maxProperties");
			node.setMaxProperties(value);
		}
		{
			Map<String, JsonNode> value = JsonUtil.consumeAnyMapProperty(json, "dependencies");
			node.setDependencies(value);
		}
		{
			JsonNode value = JsonUtil.consumeAnyProperty(json, "propertyNames");
			if (value != null) {
				if (JsonUtil.isObject(value)) {
					ObjectNode object = JsonUtil.toObject(value);
					node.setPropertyNames(node.createJSchema());
					readJSchema(object, (JSDraft6JSchema) node.getPropertyNames());
				} else if (JsonUtil.isBoolean(value)) {
					Boolean pValue = JsonUtil.toBoolean(value);
					BooleanUnionValue unionValue = new BooleanUnionValueImpl(pValue);
					node.setPropertyNames(unionValue);
				} else {
					node.addExtraProperty("propertyNames", value);
				}
			}
		}
		ReaderUtil.readExtraProperties(json, node);
	}

	@Override
	public RootNode readRoot(ObjectNode json) {
		JSDraft6Document rootModel = new JSDraft6DocumentImpl();
		this.readDocument(json, rootModel);
		return rootModel;
	}

	public void readJSchema(ObjectNode json, JSDraft6JSchema node) {
		{
			String value = JsonUtil.consumeStringProperty(json, "$ref");
			node.set$ref(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "title");
			node.setTitle(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "description");
			node.setDescription(value);
		}
		{
			JsonNode value = JsonUtil.consumeAnyProperty(json, "default");
			node.setDefault(value);
		}
		{
			List<JsonNode> value = JsonUtil.consumeAnyArrayProperty(json, "examples");
			node.setExamples(value);
		}
		{
			ObjectNode mapObject = JsonUtil.consumeObjectProperty(json, "definitions");
			if (mapObject != null) {
				List<String> keys = JsonUtil.keys(mapObject);
				keys.forEach(key -> {
					JsonNode value = JsonUtil.consumeAnyProperty(mapObject, key);
					if (value != null) {
						if (JsonUtil.isObject(value)) {
							ObjectNode object = JsonUtil.toObject(value);
							JSDraft6JSchema model = (JSDraft6JSchema) node.createJSchema();
							readJSchema(object, model);
							node.addDefinition(key, model);
						} else if (JsonUtil.isBoolean(value)) {
							Boolean pValue = JsonUtil.toBoolean(value);
							BooleanUnionValue unionValue = new BooleanUnionValueImpl(pValue);
							node.addDefinition(key, unionValue);
						}
					}
				});
			}
		}
		{
			JsonNode value = JsonUtil.consumeAnyProperty(json, "type");
			if (value != null) {
				if (JsonUtil.isArray(value)) {
					List<JsonNode> array = JsonUtil.toList(value);
					List<String> items = new ArrayList<>();
					array.forEach(item -> {
						String pValue = JsonUtil.toString(item);
						items.add(pValue);
					});
					StringListUnionValue unionValue = new StringListUnionValueImpl(items);
					node.setType(unionValue);
				} else if (JsonUtil.isString(value)) {
					String pValue = JsonUtil.toString(value);
					StringUnionValue unionValue = new StringUnionValueImpl(pValue);
					node.setType(unionValue);
				} else {
					node.addExtraProperty("type", value);
				}
			}
		}
		{
			List<JsonNode> value = JsonUtil.consumeAnyArrayProperty(json, "enum");
			node.setEnum(value);
		}
		{
			JsonNode value = JsonUtil.consumeAnyProperty(json, "const");
			node.setConst(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "format");
			node.setFormat(value);
		}
		{
			List<JsonNode> array = JsonUtil.consumeAnyArrayProperty(json, "allOf");
			if (array != null) {
				array.forEach(value -> {
					if (JsonUtil.isObject(value)) {
						ObjectNode object = JsonUtil.toObject(value);
						JSDraft6JSchema model = (JSDraft6JSchema) node.createJSchema();
						readJSchema(object, model);
						node.addAllOf(model);
					} else if (JsonUtil.isBoolean(value)) {
						Boolean pValue = JsonUtil.toBoolean(value);
						BooleanUnionValue unionValue = new BooleanUnionValueImpl(pValue);
						node.addAllOf(unionValue);
					}
				});
			}
		}
		{
			List<JsonNode> array = JsonUtil.consumeAnyArrayProperty(json, "anyOf");
			if (array != null) {
				array.forEach(value -> {
					if (JsonUtil.isObject(value)) {
						ObjectNode object = JsonUtil.toObject(value);
						JSDraft6JSchema model = (JSDraft6JSchema) node.createJSchema();
						readJSchema(object, model);
						node.addAnyOf(model);
					} else if (JsonUtil.isBoolean(value)) {
						Boolean pValue = JsonUtil.toBoolean(value);
						BooleanUnionValue unionValue = new BooleanUnionValueImpl(pValue);
						node.addAnyOf(unionValue);
					}
				});
			}
		}
		{
			List<JsonNode> array = JsonUtil.consumeAnyArrayProperty(json, "oneOf");
			if (array != null) {
				array.forEach(value -> {
					if (JsonUtil.isObject(value)) {
						ObjectNode object = JsonUtil.toObject(value);
						JSDraft6JSchema model = (JSDraft6JSchema) node.createJSchema();
						readJSchema(object, model);
						node.addOneOf(model);
					} else if (JsonUtil.isBoolean(value)) {
						Boolean pValue = JsonUtil.toBoolean(value);
						BooleanUnionValue unionValue = new BooleanUnionValueImpl(pValue);
						node.addOneOf(unionValue);
					}
				});
			}
		}
		{
			JsonNode value = JsonUtil.consumeAnyProperty(json, "not");
			if (value != null) {
				if (JsonUtil.isObject(value)) {
					ObjectNode object = JsonUtil.toObject(value);
					node.setNot(node.createJSchema());
					readJSchema(object, (JSDraft6JSchema) node.getNot());
				} else if (JsonUtil.isBoolean(value)) {
					Boolean pValue = JsonUtil.toBoolean(value);
					BooleanUnionValue unionValue = new BooleanUnionValueImpl(pValue);
					node.setNot(unionValue);
				} else {
					node.addExtraProperty("not", value);
				}
			}
		}
		{
			Number value = JsonUtil.consumeNumberProperty(json, "multipleOf");
			node.setMultipleOf(value);
		}
		{
			Number value = JsonUtil.consumeNumberProperty(json, "minimum");
			node.setMinimum(value);
		}
		{
			Number value = JsonUtil.consumeNumberProperty(json, "maximum");
			node.setMaximum(value);
		}
		{
			Number value = JsonUtil.consumeNumberProperty(json, "exclusiveMinimum");
			node.setExclusiveMinimum(value);
		}
		{
			Number value = JsonUtil.consumeNumberProperty(json, "exclusiveMaximum");
			node.setExclusiveMaximum(value);
		}
		{
			Integer value = JsonUtil.consumeIntegerProperty(json, "minLength");
			node.setMinLength(value);
		}
		{
			Integer value = JsonUtil.consumeIntegerProperty(json, "maxLength");
			node.setMaxLength(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "pattern");
			node.setPattern(value);
		}
		{
			JsonNode value = JsonUtil.consumeAnyProperty(json, "items");
			if (value != null) {
				if (JsonUtil.isObject(value)) {
					ObjectNode object = JsonUtil.toObject(value);
					node.setItems(node.createJSchema());
					readJSchema(object, (JSDraft6JSchema) node.getItems());
				} else if (JsonUtil.isArray(value)) {
					List<JsonNode> array = JsonUtil.toList(value);
					List<JSDraft6JSchema> models = new ArrayList<>();
					array.forEach(item -> {
						ObjectNode object = JsonUtil.toObject(item);
						JSDraft6JSchema model = (JSDraft6JSchema) node.createJSchema();
						this.readJSchema(object, model);
						models.add(model);
					});
					@SuppressWarnings({"unchecked", "rawtypes"})
					JSchemaListUnionValue unionValue = new JSchemaListUnionValueImpl((List) models);
					node.setItems(unionValue);
				} else if (JsonUtil.isBoolean(value)) {
					Boolean pValue = JsonUtil.toBoolean(value);
					BooleanUnionValue unionValue = new BooleanUnionValueImpl(pValue);
					node.setItems(unionValue);
				} else {
					node.addExtraProperty("items", value);
				}
			}
		}
		{
			JsonNode value = JsonUtil.consumeAnyProperty(json, "additionalItems");
			if (value != null) {
				if (JsonUtil.isObject(value)) {
					ObjectNode object = JsonUtil.toObject(value);
					node.setAdditionalItems(node.createJSchema());
					readJSchema(object, (JSDraft6JSchema) node.getAdditionalItems());
				} else if (JsonUtil.isBoolean(value)) {
					Boolean pValue = JsonUtil.toBoolean(value);
					BooleanUnionValue unionValue = new BooleanUnionValueImpl(pValue);
					node.setAdditionalItems(unionValue);
				} else {
					node.addExtraProperty("additionalItems", value);
				}
			}
		}
		{
			Integer value = JsonUtil.consumeIntegerProperty(json, "minItems");
			node.setMinItems(value);
		}
		{
			Integer value = JsonUtil.consumeIntegerProperty(json, "maxItems");
			node.setMaxItems(value);
		}
		{
			Boolean value = JsonUtil.consumeBooleanProperty(json, "uniqueItems");
			node.setUniqueItems(value);
		}
		{
			JsonNode value = JsonUtil.consumeAnyProperty(json, "contains");
			if (value != null) {
				if (JsonUtil.isObject(value)) {
					ObjectNode object = JsonUtil.toObject(value);
					node.setContains(node.createJSchema());
					readJSchema(object, (JSDraft6JSchema) node.getContains());
				} else if (JsonUtil.isBoolean(value)) {
					Boolean pValue = JsonUtil.toBoolean(value);
					BooleanUnionValue unionValue = new BooleanUnionValueImpl(pValue);
					node.setContains(unionValue);
				} else {
					node.addExtraProperty("contains", value);
				}
			}
		}
		{
			ObjectNode mapObject = JsonUtil.consumeObjectProperty(json, "properties");
			if (mapObject != null) {
				List<String> keys = JsonUtil.keys(mapObject);
				keys.forEach(key -> {
					JsonNode value = JsonUtil.consumeAnyProperty(mapObject, key);
					if (value != null) {
						if (JsonUtil.isObject(value)) {
							ObjectNode object = JsonUtil.toObject(value);
							JSDraft6JSchema model = (JSDraft6JSchema) node.createJSchema();
							readJSchema(object, model);
							node.addProperty(key, model);
						} else if (JsonUtil.isBoolean(value)) {
							Boolean pValue = JsonUtil.toBoolean(value);
							BooleanUnionValue unionValue = new BooleanUnionValueImpl(pValue);
							node.addProperty(key, unionValue);
						}
					}
				});
			}
		}
		{
			ObjectNode mapObject = JsonUtil.consumeObjectProperty(json, "patternProperties");
			if (mapObject != null) {
				List<String> keys = JsonUtil.keys(mapObject);
				keys.forEach(key -> {
					JsonNode value = JsonUtil.consumeAnyProperty(mapObject, key);
					if (value != null) {
						if (JsonUtil.isObject(value)) {
							ObjectNode object = JsonUtil.toObject(value);
							JSDraft6JSchema model = (JSDraft6JSchema) node.createJSchema();
							readJSchema(object, model);
							node.addPatternProperty(key, model);
						} else if (JsonUtil.isBoolean(value)) {
							Boolean pValue = JsonUtil.toBoolean(value);
							BooleanUnionValue unionValue = new BooleanUnionValueImpl(pValue);
							node.addPatternProperty(key, unionValue);
						}
					}
				});
			}
		}
		{
			JsonNode value = JsonUtil.consumeAnyProperty(json, "additionalProperties");
			if (value != null) {
				if (JsonUtil.isObject(value)) {
					ObjectNode object = JsonUtil.toObject(value);
					node.setAdditionalProperties(node.createJSchema());
					readJSchema(object, (JSDraft6JSchema) node.getAdditionalProperties());
				} else if (JsonUtil.isBoolean(value)) {
					Boolean pValue = JsonUtil.toBoolean(value);
					BooleanUnionValue unionValue = new BooleanUnionValueImpl(pValue);
					node.setAdditionalProperties(unionValue);
				} else {
					node.addExtraProperty("additionalProperties", value);
				}
			}
		}
		{
			List<String> value = JsonUtil.consumeStringArrayProperty(json, "required");
			node.setRequired(value);
		}
		{
			Integer value = JsonUtil.consumeIntegerProperty(json, "minProperties");
			node.setMinProperties(value);
		}
		{
			Integer value = JsonUtil.consumeIntegerProperty(json, "maxProperties");
			node.setMaxProperties(value);
		}
		{
			Map<String, JsonNode> value = JsonUtil.consumeAnyMapProperty(json, "dependencies");
			node.setDependencies(value);
		}
		{
			JsonNode value = JsonUtil.consumeAnyProperty(json, "propertyNames");
			if (value != null) {
				if (JsonUtil.isObject(value)) {
					ObjectNode object = JsonUtil.toObject(value);
					node.setPropertyNames(node.createJSchema());
					readJSchema(object, (JSDraft6JSchema) node.getPropertyNames());
				} else if (JsonUtil.isBoolean(value)) {
					Boolean pValue = JsonUtil.toBoolean(value);
					BooleanUnionValue unionValue = new BooleanUnionValueImpl(pValue);
					node.setPropertyNames(unionValue);
				} else {
					node.addExtraProperty("propertyNames", value);
				}
			}
		}
		ReaderUtil.readExtraProperties(json, node);
	}
}