package io.apicurio.datamodels.models.openrpc.v1x.v14.io;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.apicurio.datamodels.models.Example;
import io.apicurio.datamodels.models.Link;
import io.apicurio.datamodels.models.RootNode;
import io.apicurio.datamodels.models.Schema;
import io.apicurio.datamodels.models.Server;
import io.apicurio.datamodels.models.ServerVariable;
import io.apicurio.datamodels.models.Tag;
import io.apicurio.datamodels.models.io.ModelWriter;
import io.apicurio.datamodels.models.openrpc.OpenRpcContentDescriptor;
import io.apicurio.datamodels.models.openrpc.OpenRpcError;
import io.apicurio.datamodels.models.openrpc.OpenRpcExamplePairing;
import io.apicurio.datamodels.models.openrpc.OpenRpcMethod;
import io.apicurio.datamodels.models.openrpc.v1x.v14.OpenRpc14Components;
import io.apicurio.datamodels.models.openrpc.v1x.v14.OpenRpc14Contact;
import io.apicurio.datamodels.models.openrpc.v1x.v14.OpenRpc14ContentDescriptor;
import io.apicurio.datamodels.models.openrpc.v1x.v14.OpenRpc14Document;
import io.apicurio.datamodels.models.openrpc.v1x.v14.OpenRpc14Error;
import io.apicurio.datamodels.models.openrpc.v1x.v14.OpenRpc14Example;
import io.apicurio.datamodels.models.openrpc.v1x.v14.OpenRpc14ExamplePairing;
import io.apicurio.datamodels.models.openrpc.v1x.v14.OpenRpc14ExternalDocumentation;
import io.apicurio.datamodels.models.openrpc.v1x.v14.OpenRpc14Info;
import io.apicurio.datamodels.models.openrpc.v1x.v14.OpenRpc14License;
import io.apicurio.datamodels.models.openrpc.v1x.v14.OpenRpc14Link;
import io.apicurio.datamodels.models.openrpc.v1x.v14.OpenRpc14Method;
import io.apicurio.datamodels.models.openrpc.v1x.v14.OpenRpc14Schema;
import io.apicurio.datamodels.models.openrpc.v1x.v14.OpenRpc14Server;
import io.apicurio.datamodels.models.openrpc.v1x.v14.OpenRpc14ServerVariable;
import io.apicurio.datamodels.models.openrpc.v1x.v14.OpenRpc14Tag;
import io.apicurio.datamodels.models.union.BooleanSchemaUnion;
import io.apicurio.datamodels.models.union.SchemaSchemaListUnion;
import io.apicurio.datamodels.models.util.JsonUtil;
import io.apicurio.datamodels.models.util.WriterUtil;
import java.util.List;
import java.util.Map;

public class OpenRpc14ModelWriter implements ModelWriter {

	public void writeDocument(OpenRpc14Document node, ObjectNode json) {
		if (node == null) {
			return;
		}
		JsonUtil.setStringProperty(json, "openrpc", node.getOpenrpc());
		{
			if (node.getInfo() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeInfo((OpenRpc14Info) node.getInfo(), object);
				JsonUtil.setObjectProperty(json, "info", object);
			}
		}
		{
			List<? extends Server> models = node.getServers();
			if (models != null && !models.isEmpty()) {
				ArrayNode array = JsonUtil.arrayNode();
				models.forEach(model -> {
					ObjectNode object = JsonUtil.objectNode();
					this.writeServer((OpenRpc14Server) model, object);
					JsonUtil.addToArray(array, object);
				});
				JsonUtil.setAnyProperty(json, "servers", array);
			}
		}
		{
			List<? extends OpenRpcMethod> models = node.getMethods();
			if (models != null && !models.isEmpty()) {
				ArrayNode array = JsonUtil.arrayNode();
				models.forEach(model -> {
					ObjectNode object = JsonUtil.objectNode();
					this.writeMethod((OpenRpc14Method) model, object);
					JsonUtil.addToArray(array, object);
				});
				JsonUtil.setAnyProperty(json, "methods", array);
			}
		}
		{
			if (node.getComponents() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeComponents((OpenRpc14Components) node.getComponents(), object);
				JsonUtil.setObjectProperty(json, "components", object);
			}
		}
		{
			if (node.getExternalDocs() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeExternalDocumentation((OpenRpc14ExternalDocumentation) node.getExternalDocs(), object);
				JsonUtil.setObjectProperty(json, "externalDocs", object);
			}
		}
		{
			Map<String, JsonNode> values = node.getExtensions();
			if (values != null && !values.isEmpty()) {
				values.keySet().forEach(propertyName -> {
					JsonNode value = values.get(propertyName);
					JsonUtil.setAnyProperty(json, propertyName, value);
				});
			}
		}
		WriterUtil.writeExtraProperties(node, json);
	}

	@Override
	public ObjectNode writeRoot(RootNode node) {
		ObjectNode json = JsonUtil.objectNode();
		this.writeDocument((OpenRpc14Document) node, json);
		return json;
	}

	public void writeInfo(OpenRpc14Info node, ObjectNode json) {
		if (node == null) {
			return;
		}
		JsonUtil.setStringProperty(json, "title", node.getTitle());
		JsonUtil.setStringProperty(json, "description", node.getDescription());
		JsonUtil.setStringProperty(json, "termsOfService", node.getTermsOfService());
		{
			if (node.getContact() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeContact((OpenRpc14Contact) node.getContact(), object);
				JsonUtil.setObjectProperty(json, "contact", object);
			}
		}
		{
			if (node.getLicense() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeLicense((OpenRpc14License) node.getLicense(), object);
				JsonUtil.setObjectProperty(json, "license", object);
			}
		}
		JsonUtil.setStringProperty(json, "version", node.getVersion());
		{
			Map<String, JsonNode> values = node.getExtensions();
			if (values != null && !values.isEmpty()) {
				values.keySet().forEach(propertyName -> {
					JsonNode value = values.get(propertyName);
					JsonUtil.setAnyProperty(json, propertyName, value);
				});
			}
		}
		WriterUtil.writeExtraProperties(node, json);
	}

	public void writeContact(OpenRpc14Contact node, ObjectNode json) {
		if (node == null) {
			return;
		}
		JsonUtil.setStringProperty(json, "name", node.getName());
		JsonUtil.setStringProperty(json, "url", node.getUrl());
		JsonUtil.setStringProperty(json, "email", node.getEmail());
		{
			Map<String, JsonNode> values = node.getExtensions();
			if (values != null && !values.isEmpty()) {
				values.keySet().forEach(propertyName -> {
					JsonNode value = values.get(propertyName);
					JsonUtil.setAnyProperty(json, propertyName, value);
				});
			}
		}
		WriterUtil.writeExtraProperties(node, json);
	}

	public void writeLicense(OpenRpc14License node, ObjectNode json) {
		if (node == null) {
			return;
		}
		JsonUtil.setStringProperty(json, "name", node.getName());
		JsonUtil.setStringProperty(json, "url", node.getUrl());
		{
			Map<String, JsonNode> values = node.getExtensions();
			if (values != null && !values.isEmpty()) {
				values.keySet().forEach(propertyName -> {
					JsonNode value = values.get(propertyName);
					JsonUtil.setAnyProperty(json, propertyName, value);
				});
			}
		}
		WriterUtil.writeExtraProperties(node, json);
	}

	public void writeServer(OpenRpc14Server node, ObjectNode json) {
		if (node == null) {
			return;
		}
		JsonUtil.setStringProperty(json, "name", node.getName());
		JsonUtil.setStringProperty(json, "url", node.getUrl());
		JsonUtil.setStringProperty(json, "summary", node.getSummary());
		JsonUtil.setStringProperty(json, "description", node.getDescription());
		{
			Map<String, ? extends ServerVariable> models = node.getVariables();
			if (models != null && !models.isEmpty()) {
				ObjectNode object = JsonUtil.objectNode();
				models.keySet().forEach(jsonName -> {
					ObjectNode jsonValue = JsonUtil.objectNode();
					this.writeServerVariable((OpenRpc14ServerVariable) models.get(jsonName), jsonValue);
					JsonUtil.setObjectProperty(object, jsonName, jsonValue);
				});
				JsonUtil.setObjectProperty(json, "variables", object);
			}
		}
		{
			Map<String, JsonNode> values = node.getExtensions();
			if (values != null && !values.isEmpty()) {
				values.keySet().forEach(propertyName -> {
					JsonNode value = values.get(propertyName);
					JsonUtil.setAnyProperty(json, propertyName, value);
				});
			}
		}
		WriterUtil.writeExtraProperties(node, json);
	}

	public void writeServerVariable(OpenRpc14ServerVariable node, ObjectNode json) {
		if (node == null) {
			return;
		}
		JsonUtil.setStringProperty(json, "default", node.getDefault());
		JsonUtil.setStringProperty(json, "description", node.getDescription());
		JsonUtil.setStringArrayProperty(json, "enum", node.getEnum());
		{
			Map<String, JsonNode> values = node.getExtensions();
			if (values != null && !values.isEmpty()) {
				values.keySet().forEach(propertyName -> {
					JsonNode value = values.get(propertyName);
					JsonUtil.setAnyProperty(json, propertyName, value);
				});
			}
		}
		WriterUtil.writeExtraProperties(node, json);
	}

	public void writeMethod(OpenRpc14Method node, ObjectNode json) {
		if (node == null) {
			return;
		}
		JsonUtil.setStringProperty(json, "$ref", node.get$ref());
		JsonUtil.setStringProperty(json, "name", node.getName());
		{
			List<? extends Tag> models = node.getTags();
			if (models != null && !models.isEmpty()) {
				ArrayNode array = JsonUtil.arrayNode();
				models.forEach(model -> {
					ObjectNode object = JsonUtil.objectNode();
					this.writeTag((OpenRpc14Tag) model, object);
					JsonUtil.addToArray(array, object);
				});
				JsonUtil.setAnyProperty(json, "tags", array);
			}
		}
		JsonUtil.setStringProperty(json, "summary", node.getSummary());
		JsonUtil.setStringProperty(json, "description", node.getDescription());
		{
			if (node.getExternalDocs() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeExternalDocumentation((OpenRpc14ExternalDocumentation) node.getExternalDocs(), object);
				JsonUtil.setObjectProperty(json, "externalDocs", object);
			}
		}
		{
			List<? extends OpenRpcContentDescriptor> models = node.getParams();
			if (models != null && !models.isEmpty()) {
				ArrayNode array = JsonUtil.arrayNode();
				models.forEach(model -> {
					ObjectNode object = JsonUtil.objectNode();
					this.writeContentDescriptor((OpenRpc14ContentDescriptor) model, object);
					JsonUtil.addToArray(array, object);
				});
				JsonUtil.setAnyProperty(json, "params", array);
			}
		}
		{
			if (node.getResult() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeContentDescriptor((OpenRpc14ContentDescriptor) node.getResult(), object);
				JsonUtil.setObjectProperty(json, "result", object);
			}
		}
		JsonUtil.setBooleanProperty(json, "deprecated", node.isDeprecated());
		{
			List<? extends Server> models = node.getServers();
			if (models != null && !models.isEmpty()) {
				ArrayNode array = JsonUtil.arrayNode();
				models.forEach(model -> {
					ObjectNode object = JsonUtil.objectNode();
					this.writeServer((OpenRpc14Server) model, object);
					JsonUtil.addToArray(array, object);
				});
				JsonUtil.setAnyProperty(json, "servers", array);
			}
		}
		{
			List<? extends OpenRpcError> models = node.getErrors();
			if (models != null && !models.isEmpty()) {
				ArrayNode array = JsonUtil.arrayNode();
				models.forEach(model -> {
					ObjectNode object = JsonUtil.objectNode();
					this.writeError((OpenRpc14Error) model, object);
					JsonUtil.addToArray(array, object);
				});
				JsonUtil.setAnyProperty(json, "errors", array);
			}
		}
		{
			List<? extends Link> models = node.getLinks();
			if (models != null && !models.isEmpty()) {
				ArrayNode array = JsonUtil.arrayNode();
				models.forEach(model -> {
					ObjectNode object = JsonUtil.objectNode();
					this.writeLink((OpenRpc14Link) model, object);
					JsonUtil.addToArray(array, object);
				});
				JsonUtil.setAnyProperty(json, "links", array);
			}
		}
		JsonUtil.setStringProperty(json, "paramStructure", node.getParamStructure());
		{
			List<? extends OpenRpcExamplePairing> models = node.getExamples();
			if (models != null && !models.isEmpty()) {
				ArrayNode array = JsonUtil.arrayNode();
				models.forEach(model -> {
					ObjectNode object = JsonUtil.objectNode();
					this.writeExamplePairing((OpenRpc14ExamplePairing) model, object);
					JsonUtil.addToArray(array, object);
				});
				JsonUtil.setAnyProperty(json, "examples", array);
			}
		}
		{
			Map<String, JsonNode> values = node.getExtensions();
			if (values != null && !values.isEmpty()) {
				values.keySet().forEach(propertyName -> {
					JsonNode value = values.get(propertyName);
					JsonUtil.setAnyProperty(json, propertyName, value);
				});
			}
		}
		WriterUtil.writeExtraProperties(node, json);
	}

	public void writeContentDescriptor(OpenRpc14ContentDescriptor node, ObjectNode json) {
		if (node == null) {
			return;
		}
		JsonUtil.setStringProperty(json, "$ref", node.get$ref());
		JsonUtil.setStringProperty(json, "name", node.getName());
		JsonUtil.setStringProperty(json, "summary", node.getSummary());
		JsonUtil.setStringProperty(json, "description", node.getDescription());
		JsonUtil.setBooleanProperty(json, "required", node.isRequired());
		JsonUtil.setBooleanProperty(json, "deprecated", node.isDeprecated());
		{
			if (node.getSchema() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeSchema((OpenRpc14Schema) node.getSchema(), object);
				JsonUtil.setObjectProperty(json, "schema", object);
			}
		}
		{
			Map<String, JsonNode> values = node.getExtensions();
			if (values != null && !values.isEmpty()) {
				values.keySet().forEach(propertyName -> {
					JsonNode value = values.get(propertyName);
					JsonUtil.setAnyProperty(json, propertyName, value);
				});
			}
		}
		WriterUtil.writeExtraProperties(node, json);
	}

	public void writeSchema(OpenRpc14Schema node, ObjectNode json) {
		if (node == null) {
			return;
		}
		JsonUtil.setStringProperty(json, "$ref", node.get$ref());
		JsonUtil.setStringProperty(json, "title", node.getTitle());
		JsonUtil.setStringProperty(json, "type", node.getType());
		JsonUtil.setStringArrayProperty(json, "required", node.getRequired());
		JsonUtil.setNumberProperty(json, "multipleOf", node.getMultipleOf());
		JsonUtil.setNumberProperty(json, "maximum", node.getMaximum());
		JsonUtil.setNumberProperty(json, "exclusiveMaximum", node.getExclusiveMaximum());
		JsonUtil.setNumberProperty(json, "minimum", node.getMinimum());
		JsonUtil.setNumberProperty(json, "exclusiveMinimum", node.getExclusiveMinimum());
		JsonUtil.setIntegerProperty(json, "maxLength", node.getMaxLength());
		JsonUtil.setIntegerProperty(json, "minLength", node.getMinLength());
		JsonUtil.setStringProperty(json, "pattern", node.getPattern());
		JsonUtil.setIntegerProperty(json, "maxItems", node.getMaxItems());
		JsonUtil.setIntegerProperty(json, "minItems", node.getMinItems());
		JsonUtil.setBooleanProperty(json, "uniqueItems", node.isUniqueItems());
		JsonUtil.setIntegerProperty(json, "maxProperties", node.getMaxProperties());
		JsonUtil.setIntegerProperty(json, "minProperties", node.getMinProperties());
		JsonUtil.setAnyArrayProperty(json, "enum", node.getEnum());
		JsonUtil.setAnyProperty(json, "const", node.getConst());
		JsonUtil.setAnyArrayProperty(json, "examples", node.getExamples());
		{
			if (node.getIf() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeSchema((OpenRpc14Schema) node.getIf(), object);
				JsonUtil.setObjectProperty(json, "if", object);
			}
		}
		{
			if (node.getThen() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeSchema((OpenRpc14Schema) node.getThen(), object);
				JsonUtil.setObjectProperty(json, "then", object);
			}
		}
		{
			if (node.getElse() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeSchema((OpenRpc14Schema) node.getElse(), object);
				JsonUtil.setObjectProperty(json, "else", object);
			}
		}
		JsonUtil.setBooleanProperty(json, "readOnly", node.isReadOnly());
		JsonUtil.setBooleanProperty(json, "writeOnly", node.isWriteOnly());
		{
			Map<String, ? extends Schema> models = node.getProperties();
			if (models != null && !models.isEmpty()) {
				ObjectNode object = JsonUtil.objectNode();
				models.keySet().forEach(jsonName -> {
					ObjectNode jsonValue = JsonUtil.objectNode();
					this.writeSchema((OpenRpc14Schema) models.get(jsonName), jsonValue);
					JsonUtil.setObjectProperty(object, jsonName, jsonValue);
				});
				JsonUtil.setObjectProperty(json, "properties", object);
			}
		}
		JsonUtil.setStringMapProperty(json, "patternProperties", node.getPatternProperties());
		{
			BooleanSchemaUnion union = node.getAdditionalProperties();
			if (union != null) {
				if (union.isBoolean()) {
					JsonUtil.setBooleanProperty(json, "additionalProperties", union.asBoolean());
				}
				if (union.isSchema()) {
					ObjectNode jsonValue = JsonUtil.objectNode();
					this.writeSchema((OpenRpc14Schema) union.asSchema(), jsonValue);
					JsonUtil.setObjectProperty(json, "additionalProperties", jsonValue);
				}
			}
		}
		{
			if (node.getAdditionalItems() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeSchema((OpenRpc14Schema) node.getAdditionalItems(), object);
				JsonUtil.setObjectProperty(json, "additionalItems", object);
			}
		}
		{
			SchemaSchemaListUnion union = node.getItems();
			if (union != null) {
				if (union.isSchema()) {
					ObjectNode jsonValue = JsonUtil.objectNode();
					this.writeSchema((OpenRpc14Schema) union.asSchema(), jsonValue);
					JsonUtil.setObjectProperty(json, "items", jsonValue);
				}
				if (union.isSchemaList()) {
					List<? extends Schema> models = union.asSchemaList();
					ArrayNode array = JsonUtil.arrayNode();
					models.forEach(model -> {
						ObjectNode object = JsonUtil.objectNode();
						this.writeSchema((OpenRpc14Schema) model, object);
						JsonUtil.addToArray(array, object);
					});
					JsonUtil.setAnyProperty(json, "items", array);
				}
			}
		}
		{
			if (node.getPropertyNames() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeSchema((OpenRpc14Schema) node.getPropertyNames(), object);
				JsonUtil.setObjectProperty(json, "propertyNames", object);
			}
		}
		{
			if (node.getContains() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeSchema((OpenRpc14Schema) node.getContains(), object);
				JsonUtil.setObjectProperty(json, "contains", object);
			}
		}
		{
			List<? extends Schema> models = node.getAllOf();
			if (models != null && !models.isEmpty()) {
				ArrayNode array = JsonUtil.arrayNode();
				models.forEach(model -> {
					ObjectNode object = JsonUtil.objectNode();
					this.writeSchema((OpenRpc14Schema) model, object);
					JsonUtil.addToArray(array, object);
				});
				JsonUtil.setAnyProperty(json, "allOf", array);
			}
		}
		{
			List<? extends Schema> models = node.getOneOf();
			if (models != null && !models.isEmpty()) {
				ArrayNode array = JsonUtil.arrayNode();
				models.forEach(model -> {
					ObjectNode object = JsonUtil.objectNode();
					this.writeSchema((OpenRpc14Schema) model, object);
					JsonUtil.addToArray(array, object);
				});
				JsonUtil.setAnyProperty(json, "oneOf", array);
			}
		}
		{
			List<? extends Schema> models = node.getAnyOf();
			if (models != null && !models.isEmpty()) {
				ArrayNode array = JsonUtil.arrayNode();
				models.forEach(model -> {
					ObjectNode object = JsonUtil.objectNode();
					this.writeSchema((OpenRpc14Schema) model, object);
					JsonUtil.addToArray(array, object);
				});
				JsonUtil.setAnyProperty(json, "anyOf", array);
			}
		}
		{
			if (node.getNot() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeSchema((OpenRpc14Schema) node.getNot(), object);
				JsonUtil.setObjectProperty(json, "not", object);
			}
		}
		JsonUtil.setStringProperty(json, "description", node.getDescription());
		JsonUtil.setStringProperty(json, "format", node.getFormat());
		JsonUtil.setAnyProperty(json, "default", node.getDefault());
		JsonUtil.setBooleanProperty(json, "deprecated", node.isDeprecated());
		{
			Map<String, JsonNode> values = node.getExtensions();
			if (values != null && !values.isEmpty()) {
				values.keySet().forEach(propertyName -> {
					JsonNode value = values.get(propertyName);
					JsonUtil.setAnyProperty(json, propertyName, value);
				});
			}
		}
		WriterUtil.writeExtraProperties(node, json);
	}

	public void writeExamplePairing(OpenRpc14ExamplePairing node, ObjectNode json) {
		if (node == null) {
			return;
		}
		JsonUtil.setStringProperty(json, "$ref", node.get$ref());
		JsonUtil.setStringProperty(json, "name", node.getName());
		JsonUtil.setStringProperty(json, "description", node.getDescription());
		JsonUtil.setStringProperty(json, "summary", node.getSummary());
		{
			List<? extends Example> models = node.getParams();
			if (models != null && !models.isEmpty()) {
				ArrayNode array = JsonUtil.arrayNode();
				models.forEach(model -> {
					ObjectNode object = JsonUtil.objectNode();
					this.writeExample((OpenRpc14Example) model, object);
					JsonUtil.addToArray(array, object);
				});
				JsonUtil.setAnyProperty(json, "params", array);
			}
		}
		{
			if (node.getResult() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeExample((OpenRpc14Example) node.getResult(), object);
				JsonUtil.setObjectProperty(json, "result", object);
			}
		}
		{
			Map<String, JsonNode> values = node.getExtensions();
			if (values != null && !values.isEmpty()) {
				values.keySet().forEach(propertyName -> {
					JsonNode value = values.get(propertyName);
					JsonUtil.setAnyProperty(json, propertyName, value);
				});
			}
		}
		WriterUtil.writeExtraProperties(node, json);
	}

	public void writeExample(OpenRpc14Example node, ObjectNode json) {
		if (node == null) {
			return;
		}
		JsonUtil.setStringProperty(json, "$ref", node.get$ref());
		JsonUtil.setStringProperty(json, "name", node.getName());
		JsonUtil.setStringProperty(json, "summary", node.getSummary());
		JsonUtil.setStringProperty(json, "description", node.getDescription());
		JsonUtil.setAnyProperty(json, "value", node.getValue());
		JsonUtil.setStringProperty(json, "externalValue", node.getExternalValue());
		{
			Map<String, JsonNode> values = node.getExtensions();
			if (values != null && !values.isEmpty()) {
				values.keySet().forEach(propertyName -> {
					JsonNode value = values.get(propertyName);
					JsonUtil.setAnyProperty(json, propertyName, value);
				});
			}
		}
		WriterUtil.writeExtraProperties(node, json);
	}

	public void writeLink(OpenRpc14Link node, ObjectNode json) {
		if (node == null) {
			return;
		}
		JsonUtil.setStringProperty(json, "$ref", node.get$ref());
		JsonUtil.setStringProperty(json, "name", node.getName());
		JsonUtil.setStringProperty(json, "summary", node.getSummary());
		JsonUtil.setStringProperty(json, "description", node.getDescription());
		JsonUtil.setStringProperty(json, "method", node.getMethod());
		JsonUtil.setAnyProperty(json, "params", node.getParams());
		{
			if (node.getServer() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeServer((OpenRpc14Server) node.getServer(), object);
				JsonUtil.setObjectProperty(json, "server", object);
			}
		}
		{
			Map<String, JsonNode> values = node.getExtensions();
			if (values != null && !values.isEmpty()) {
				values.keySet().forEach(propertyName -> {
					JsonNode value = values.get(propertyName);
					JsonUtil.setAnyProperty(json, propertyName, value);
				});
			}
		}
		WriterUtil.writeExtraProperties(node, json);
	}

	public void writeError(OpenRpc14Error node, ObjectNode json) {
		if (node == null) {
			return;
		}
		JsonUtil.setStringProperty(json, "$ref", node.get$ref());
		JsonUtil.setIntegerProperty(json, "code", node.getCode());
		JsonUtil.setStringProperty(json, "message", node.getMessage());
		JsonUtil.setAnyProperty(json, "data", node.getData());
		WriterUtil.writeExtraProperties(node, json);
	}

	public void writeComponents(OpenRpc14Components node, ObjectNode json) {
		if (node == null) {
			return;
		}
		{
			Map<String, ? extends OpenRpcContentDescriptor> models = node.getContentDescriptors();
			if (models != null && !models.isEmpty()) {
				ObjectNode object = JsonUtil.objectNode();
				models.keySet().forEach(jsonName -> {
					ObjectNode jsonValue = JsonUtil.objectNode();
					this.writeContentDescriptor((OpenRpc14ContentDescriptor) models.get(jsonName), jsonValue);
					JsonUtil.setObjectProperty(object, jsonName, jsonValue);
				});
				JsonUtil.setObjectProperty(json, "contentDescriptors", object);
			}
		}
		{
			Map<String, ? extends Schema> models = node.getSchemas();
			if (models != null && !models.isEmpty()) {
				ObjectNode object = JsonUtil.objectNode();
				models.keySet().forEach(jsonName -> {
					ObjectNode jsonValue = JsonUtil.objectNode();
					this.writeSchema((OpenRpc14Schema) models.get(jsonName), jsonValue);
					JsonUtil.setObjectProperty(object, jsonName, jsonValue);
				});
				JsonUtil.setObjectProperty(json, "schemas", object);
			}
		}
		{
			Map<String, ? extends Example> models = node.getExamples();
			if (models != null && !models.isEmpty()) {
				ObjectNode object = JsonUtil.objectNode();
				models.keySet().forEach(jsonName -> {
					ObjectNode jsonValue = JsonUtil.objectNode();
					this.writeExample((OpenRpc14Example) models.get(jsonName), jsonValue);
					JsonUtil.setObjectProperty(object, jsonName, jsonValue);
				});
				JsonUtil.setObjectProperty(json, "examples", object);
			}
		}
		{
			Map<String, ? extends Link> models = node.getLinks();
			if (models != null && !models.isEmpty()) {
				ObjectNode object = JsonUtil.objectNode();
				models.keySet().forEach(jsonName -> {
					ObjectNode jsonValue = JsonUtil.objectNode();
					this.writeLink((OpenRpc14Link) models.get(jsonName), jsonValue);
					JsonUtil.setObjectProperty(object, jsonName, jsonValue);
				});
				JsonUtil.setObjectProperty(json, "links", object);
			}
		}
		{
			Map<String, ? extends OpenRpcError> models = node.getErrors();
			if (models != null && !models.isEmpty()) {
				ObjectNode object = JsonUtil.objectNode();
				models.keySet().forEach(jsonName -> {
					ObjectNode jsonValue = JsonUtil.objectNode();
					this.writeError((OpenRpc14Error) models.get(jsonName), jsonValue);
					JsonUtil.setObjectProperty(object, jsonName, jsonValue);
				});
				JsonUtil.setObjectProperty(json, "errors", object);
			}
		}
		{
			Map<String, ? extends OpenRpcExamplePairing> models = node.getExamplePairings();
			if (models != null && !models.isEmpty()) {
				ObjectNode object = JsonUtil.objectNode();
				models.keySet().forEach(jsonName -> {
					ObjectNode jsonValue = JsonUtil.objectNode();
					this.writeExamplePairing((OpenRpc14ExamplePairing) models.get(jsonName), jsonValue);
					JsonUtil.setObjectProperty(object, jsonName, jsonValue);
				});
				JsonUtil.setObjectProperty(json, "examplePairings", object);
			}
		}
		{
			Map<String, ? extends Tag> models = node.getTags();
			if (models != null && !models.isEmpty()) {
				ObjectNode object = JsonUtil.objectNode();
				models.keySet().forEach(jsonName -> {
					ObjectNode jsonValue = JsonUtil.objectNode();
					this.writeTag((OpenRpc14Tag) models.get(jsonName), jsonValue);
					JsonUtil.setObjectProperty(object, jsonName, jsonValue);
				});
				JsonUtil.setObjectProperty(json, "tags", object);
			}
		}
		{
			Map<String, JsonNode> values = node.getExtensions();
			if (values != null && !values.isEmpty()) {
				values.keySet().forEach(propertyName -> {
					JsonNode value = values.get(propertyName);
					JsonUtil.setAnyProperty(json, propertyName, value);
				});
			}
		}
		WriterUtil.writeExtraProperties(node, json);
	}

	public void writeTag(OpenRpc14Tag node, ObjectNode json) {
		if (node == null) {
			return;
		}
		JsonUtil.setStringProperty(json, "$ref", node.get$ref());
		JsonUtil.setStringProperty(json, "name", node.getName());
		JsonUtil.setStringProperty(json, "summary", node.getSummary());
		JsonUtil.setStringProperty(json, "description", node.getDescription());
		{
			if (node.getExternalDocs() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeExternalDocumentation((OpenRpc14ExternalDocumentation) node.getExternalDocs(), object);
				JsonUtil.setObjectProperty(json, "externalDocs", object);
			}
		}
		{
			Map<String, JsonNode> values = node.getExtensions();
			if (values != null && !values.isEmpty()) {
				values.keySet().forEach(propertyName -> {
					JsonNode value = values.get(propertyName);
					JsonUtil.setAnyProperty(json, propertyName, value);
				});
			}
		}
		WriterUtil.writeExtraProperties(node, json);
	}

	public void writeExternalDocumentation(OpenRpc14ExternalDocumentation node, ObjectNode json) {
		if (node == null) {
			return;
		}
		JsonUtil.setStringProperty(json, "description", node.getDescription());
		JsonUtil.setStringProperty(json, "url", node.getUrl());
		{
			Map<String, JsonNode> values = node.getExtensions();
			if (values != null && !values.isEmpty()) {
				values.keySet().forEach(propertyName -> {
					JsonNode value = values.get(propertyName);
					JsonUtil.setAnyProperty(json, propertyName, value);
				});
			}
		}
		WriterUtil.writeExtraProperties(node, json);
	}
}