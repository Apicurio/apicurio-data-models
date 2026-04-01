package io.apicurio.datamodels.models.openapi.v2x.v20.io;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.apicurio.datamodels.models.Parameter;
import io.apicurio.datamodels.models.RootNode;
import io.apicurio.datamodels.models.Schema;
import io.apicurio.datamodels.models.SecurityRequirement;
import io.apicurio.datamodels.models.Tag;
import io.apicurio.datamodels.models.io.ModelWriter;
import io.apicurio.datamodels.models.openapi.OpenApiHeader;
import io.apicurio.datamodels.models.openapi.v2x.v20.OpenApi20Contact;
import io.apicurio.datamodels.models.openapi.v2x.v20.OpenApi20Definitions;
import io.apicurio.datamodels.models.openapi.v2x.v20.OpenApi20Document;
import io.apicurio.datamodels.models.openapi.v2x.v20.OpenApi20Example;
import io.apicurio.datamodels.models.openapi.v2x.v20.OpenApi20ExternalDocumentation;
import io.apicurio.datamodels.models.openapi.v2x.v20.OpenApi20Header;
import io.apicurio.datamodels.models.openapi.v2x.v20.OpenApi20Info;
import io.apicurio.datamodels.models.openapi.v2x.v20.OpenApi20Items;
import io.apicurio.datamodels.models.openapi.v2x.v20.OpenApi20License;
import io.apicurio.datamodels.models.openapi.v2x.v20.OpenApi20Operation;
import io.apicurio.datamodels.models.openapi.v2x.v20.OpenApi20Parameter;
import io.apicurio.datamodels.models.openapi.v2x.v20.OpenApi20ParameterDefinitions;
import io.apicurio.datamodels.models.openapi.v2x.v20.OpenApi20PathItem;
import io.apicurio.datamodels.models.openapi.v2x.v20.OpenApi20Paths;
import io.apicurio.datamodels.models.openapi.v2x.v20.OpenApi20Response;
import io.apicurio.datamodels.models.openapi.v2x.v20.OpenApi20ResponseDefinitions;
import io.apicurio.datamodels.models.openapi.v2x.v20.OpenApi20Responses;
import io.apicurio.datamodels.models.openapi.v2x.v20.OpenApi20Schema;
import io.apicurio.datamodels.models.openapi.v2x.v20.OpenApi20Scopes;
import io.apicurio.datamodels.models.openapi.v2x.v20.OpenApi20SecurityDefinitions;
import io.apicurio.datamodels.models.openapi.v2x.v20.OpenApi20SecurityRequirement;
import io.apicurio.datamodels.models.openapi.v2x.v20.OpenApi20SecurityScheme;
import io.apicurio.datamodels.models.openapi.v2x.v20.OpenApi20Tag;
import io.apicurio.datamodels.models.openapi.v2x.v20.OpenApi20XML;
import io.apicurio.datamodels.models.union.BooleanSchemaUnion;
import io.apicurio.datamodels.models.union.SchemaSchemaListUnion;
import io.apicurio.datamodels.models.util.JsonUtil;
import io.apicurio.datamodels.models.util.WriterUtil;
import java.util.List;
import java.util.Map;

public class OpenApi20ModelWriter implements ModelWriter {

	public void writeDocument(OpenApi20Document node, ObjectNode json) {
		if (node == null) {
			return;
		}
		JsonUtil.setStringProperty(json, "swagger", node.getSwagger());
		{
			if (node.getInfo() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeInfo((OpenApi20Info) node.getInfo(), object);
				JsonUtil.setObjectProperty(json, "info", object);
			}
		}
		JsonUtil.setStringProperty(json, "host", node.getHost());
		JsonUtil.setStringProperty(json, "basePath", node.getBasePath());
		JsonUtil.setStringArrayProperty(json, "schemes", node.getSchemes());
		JsonUtil.setStringArrayProperty(json, "consumes", node.getConsumes());
		JsonUtil.setStringArrayProperty(json, "produces", node.getProduces());
		{
			if (node.getPaths() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writePaths((OpenApi20Paths) node.getPaths(), object);
				JsonUtil.setObjectProperty(json, "paths", object);
			}
		}
		{
			if (node.getDefinitions() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeDefinitions((OpenApi20Definitions) node.getDefinitions(), object);
				JsonUtil.setObjectProperty(json, "definitions", object);
			}
		}
		{
			if (node.getParameters() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeParameterDefinitions((OpenApi20ParameterDefinitions) node.getParameters(), object);
				JsonUtil.setObjectProperty(json, "parameters", object);
			}
		}
		{
			if (node.getResponses() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeResponseDefinitions((OpenApi20ResponseDefinitions) node.getResponses(), object);
				JsonUtil.setObjectProperty(json, "responses", object);
			}
		}
		{
			if (node.getSecurityDefinitions() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeSecurityDefinitions((OpenApi20SecurityDefinitions) node.getSecurityDefinitions(), object);
				JsonUtil.setObjectProperty(json, "securityDefinitions", object);
			}
		}
		{
			List<? extends SecurityRequirement> models = node.getSecurity();
			if (models != null && !models.isEmpty()) {
				ArrayNode array = JsonUtil.arrayNode();
				models.forEach(model -> {
					ObjectNode object = JsonUtil.objectNode();
					this.writeSecurityRequirement((OpenApi20SecurityRequirement) model, object);
					JsonUtil.addToArray(array, object);
				});
				JsonUtil.setAnyProperty(json, "security", array);
			}
		}
		{
			List<? extends Tag> models = node.getTags();
			if (models != null && !models.isEmpty()) {
				ArrayNode array = JsonUtil.arrayNode();
				models.forEach(model -> {
					ObjectNode object = JsonUtil.objectNode();
					this.writeTag((OpenApi20Tag) model, object);
					JsonUtil.addToArray(array, object);
				});
				JsonUtil.setAnyProperty(json, "tags", array);
			}
		}
		{
			if (node.getExternalDocs() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeExternalDocumentation((OpenApi20ExternalDocumentation) node.getExternalDocs(), object);
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
		this.writeDocument((OpenApi20Document) node, json);
		return json;
	}

	public void writeInfo(OpenApi20Info node, ObjectNode json) {
		if (node == null) {
			return;
		}
		JsonUtil.setStringProperty(json, "title", node.getTitle());
		JsonUtil.setStringProperty(json, "description", node.getDescription());
		JsonUtil.setStringProperty(json, "termsOfService", node.getTermsOfService());
		{
			if (node.getContact() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeContact((OpenApi20Contact) node.getContact(), object);
				JsonUtil.setObjectProperty(json, "contact", object);
			}
		}
		{
			if (node.getLicense() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeLicense((OpenApi20License) node.getLicense(), object);
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

	public void writeContact(OpenApi20Contact node, ObjectNode json) {
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

	public void writeLicense(OpenApi20License node, ObjectNode json) {
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

	public void writePaths(OpenApi20Paths node, ObjectNode json) {
		if (node == null) {
			return;
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
		{
			List<String> propertyNames = node.getItemNames();
			propertyNames.forEach(propertyName -> {
				ObjectNode object = JsonUtil.objectNode();
				this.writePathItem((OpenApi20PathItem) node.getItem(propertyName), object);
				JsonUtil.setObjectProperty(json, propertyName, object);
			});
		}
		WriterUtil.writeExtraProperties(node, json);
	}

	public void writePathItem(OpenApi20PathItem node, ObjectNode json) {
		if (node == null) {
			return;
		}
		JsonUtil.setStringProperty(json, "$ref", node.get$ref());
		{
			if (node.getGet() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeOperation((OpenApi20Operation) node.getGet(), object);
				JsonUtil.setObjectProperty(json, "get", object);
			}
		}
		{
			if (node.getPut() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeOperation((OpenApi20Operation) node.getPut(), object);
				JsonUtil.setObjectProperty(json, "put", object);
			}
		}
		{
			if (node.getPost() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeOperation((OpenApi20Operation) node.getPost(), object);
				JsonUtil.setObjectProperty(json, "post", object);
			}
		}
		{
			if (node.getDelete() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeOperation((OpenApi20Operation) node.getDelete(), object);
				JsonUtil.setObjectProperty(json, "delete", object);
			}
		}
		{
			if (node.getOptions() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeOperation((OpenApi20Operation) node.getOptions(), object);
				JsonUtil.setObjectProperty(json, "options", object);
			}
		}
		{
			if (node.getHead() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeOperation((OpenApi20Operation) node.getHead(), object);
				JsonUtil.setObjectProperty(json, "head", object);
			}
		}
		{
			if (node.getPatch() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeOperation((OpenApi20Operation) node.getPatch(), object);
				JsonUtil.setObjectProperty(json, "patch", object);
			}
		}
		{
			List<? extends Parameter> models = node.getParameters();
			if (models != null && !models.isEmpty()) {
				ArrayNode array = JsonUtil.arrayNode();
				models.forEach(model -> {
					ObjectNode object = JsonUtil.objectNode();
					this.writeParameter((OpenApi20Parameter) model, object);
					JsonUtil.addToArray(array, object);
				});
				JsonUtil.setAnyProperty(json, "parameters", array);
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

	public void writeOperation(OpenApi20Operation node, ObjectNode json) {
		if (node == null) {
			return;
		}
		JsonUtil.setStringArrayProperty(json, "tags", node.getTags());
		JsonUtil.setStringProperty(json, "summary", node.getSummary());
		JsonUtil.setStringProperty(json, "description", node.getDescription());
		{
			if (node.getExternalDocs() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeExternalDocumentation((OpenApi20ExternalDocumentation) node.getExternalDocs(), object);
				JsonUtil.setObjectProperty(json, "externalDocs", object);
			}
		}
		JsonUtil.setStringProperty(json, "operationId", node.getOperationId());
		JsonUtil.setStringArrayProperty(json, "consumes", node.getConsumes());
		JsonUtil.setStringArrayProperty(json, "produces", node.getProduces());
		{
			List<? extends Parameter> models = node.getParameters();
			if (models != null && !models.isEmpty()) {
				ArrayNode array = JsonUtil.arrayNode();
				models.forEach(model -> {
					ObjectNode object = JsonUtil.objectNode();
					this.writeParameter((OpenApi20Parameter) model, object);
					JsonUtil.addToArray(array, object);
				});
				JsonUtil.setAnyProperty(json, "parameters", array);
			}
		}
		{
			if (node.getResponses() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeResponses((OpenApi20Responses) node.getResponses(), object);
				JsonUtil.setObjectProperty(json, "responses", object);
			}
		}
		JsonUtil.setStringArrayProperty(json, "schemes", node.getSchemes());
		JsonUtil.setBooleanProperty(json, "deprecated", node.isDeprecated());
		{
			List<? extends SecurityRequirement> models = node.getSecurity();
			if (models != null && !models.isEmpty()) {
				ArrayNode array = JsonUtil.arrayNode();
				models.forEach(model -> {
					ObjectNode object = JsonUtil.objectNode();
					this.writeSecurityRequirement((OpenApi20SecurityRequirement) model, object);
					JsonUtil.addToArray(array, object);
				});
				JsonUtil.setAnyProperty(json, "security", array);
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

	public void writeExternalDocumentation(OpenApi20ExternalDocumentation node, ObjectNode json) {
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

	public void writeParameter(OpenApi20Parameter node, ObjectNode json) {
		if (node == null) {
			return;
		}
		JsonUtil.setStringProperty(json, "$ref", node.get$ref());
		JsonUtil.setStringProperty(json, "description", node.getDescription());
		JsonUtil.setStringProperty(json, "type", node.getType());
		JsonUtil.setStringProperty(json, "format", node.getFormat());
		JsonUtil.setAnyProperty(json, "default", node.getDefault());
		JsonUtil.setNumberProperty(json, "maximum", node.getMaximum());
		JsonUtil.setBooleanProperty(json, "exclusiveMaximum", node.isExclusiveMaximum());
		JsonUtil.setNumberProperty(json, "minimum", node.getMinimum());
		JsonUtil.setBooleanProperty(json, "exclusiveMinimum", node.isExclusiveMinimum());
		JsonUtil.setIntegerProperty(json, "maxLength", node.getMaxLength());
		JsonUtil.setIntegerProperty(json, "minLength", node.getMinLength());
		JsonUtil.setStringProperty(json, "pattern", node.getPattern());
		JsonUtil.setIntegerProperty(json, "maxItems", node.getMaxItems());
		JsonUtil.setIntegerProperty(json, "minItems", node.getMinItems());
		JsonUtil.setBooleanProperty(json, "uniqueItems", node.isUniqueItems());
		JsonUtil.setAnyArrayProperty(json, "enum", node.getEnum());
		JsonUtil.setNumberProperty(json, "multipleOf", node.getMultipleOf());
		JsonUtil.setStringProperty(json, "name", node.getName());
		JsonUtil.setStringProperty(json, "in", node.getIn());
		JsonUtil.setBooleanProperty(json, "required", node.isRequired());
		{
			if (node.getSchema() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeSchema((OpenApi20Schema) node.getSchema(), object);
				JsonUtil.setObjectProperty(json, "schema", object);
			}
		}
		{
			if (node.getItems() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeItems((OpenApi20Items) node.getItems(), object);
				JsonUtil.setObjectProperty(json, "items", object);
			}
		}
		JsonUtil.setBooleanProperty(json, "allowEmptyValue", node.isAllowEmptyValue());
		JsonUtil.setStringProperty(json, "collectionFormat", node.getCollectionFormat());
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

	public void writeItems(OpenApi20Items node, ObjectNode json) {
		if (node == null) {
			return;
		}
		JsonUtil.setStringProperty(json, "$ref", node.get$ref());
		JsonUtil.setStringProperty(json, "description", node.getDescription());
		JsonUtil.setStringProperty(json, "type", node.getType());
		JsonUtil.setStringProperty(json, "format", node.getFormat());
		JsonUtil.setAnyProperty(json, "default", node.getDefault());
		JsonUtil.setNumberProperty(json, "maximum", node.getMaximum());
		JsonUtil.setBooleanProperty(json, "exclusiveMaximum", node.isExclusiveMaximum());
		JsonUtil.setNumberProperty(json, "minimum", node.getMinimum());
		JsonUtil.setBooleanProperty(json, "exclusiveMinimum", node.isExclusiveMinimum());
		JsonUtil.setIntegerProperty(json, "maxLength", node.getMaxLength());
		JsonUtil.setIntegerProperty(json, "minLength", node.getMinLength());
		JsonUtil.setStringProperty(json, "pattern", node.getPattern());
		JsonUtil.setIntegerProperty(json, "maxItems", node.getMaxItems());
		JsonUtil.setIntegerProperty(json, "minItems", node.getMinItems());
		JsonUtil.setBooleanProperty(json, "uniqueItems", node.isUniqueItems());
		JsonUtil.setAnyArrayProperty(json, "enum", node.getEnum());
		JsonUtil.setNumberProperty(json, "multipleOf", node.getMultipleOf());
		{
			if (node.getItems() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeItems((OpenApi20Items) node.getItems(), object);
				JsonUtil.setObjectProperty(json, "items", object);
			}
		}
		JsonUtil.setStringProperty(json, "collectionFormat", node.getCollectionFormat());
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

	public void writeResponses(OpenApi20Responses node, ObjectNode json) {
		if (node == null) {
			return;
		}
		{
			if (node.getDefault() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeResponse((OpenApi20Response) node.getDefault(), object);
				JsonUtil.setObjectProperty(json, "default", object);
			}
		}
		{
			List<String> propertyNames = node.getItemNames();
			propertyNames.forEach(propertyName -> {
				ObjectNode object = JsonUtil.objectNode();
				this.writeResponse((OpenApi20Response) node.getItem(propertyName), object);
				JsonUtil.setObjectProperty(json, propertyName, object);
			});
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

	public void writeResponse(OpenApi20Response node, ObjectNode json) {
		if (node == null) {
			return;
		}
		JsonUtil.setStringProperty(json, "$ref", node.get$ref());
		JsonUtil.setStringProperty(json, "description", node.getDescription());
		{
			if (node.getSchema() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeSchema((OpenApi20Schema) node.getSchema(), object);
				JsonUtil.setObjectProperty(json, "schema", object);
			}
		}
		{
			Map<String, ? extends OpenApiHeader> models = node.getHeaders();
			if (models != null && !models.isEmpty()) {
				ObjectNode object = JsonUtil.objectNode();
				models.keySet().forEach(jsonName -> {
					ObjectNode jsonValue = JsonUtil.objectNode();
					this.writeHeader((OpenApi20Header) models.get(jsonName), jsonValue);
					JsonUtil.setObjectProperty(object, jsonName, jsonValue);
				});
				JsonUtil.setObjectProperty(json, "headers", object);
			}
		}
		{
			if (node.getExamples() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeExample((OpenApi20Example) node.getExamples(), object);
				JsonUtil.setObjectProperty(json, "examples", object);
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

	public void writeExample(OpenApi20Example node, ObjectNode json) {
		if (node == null) {
			return;
		}
		{
			List<String> propertyNames = node.getItemNames();
			propertyNames.forEach(propertyName -> {
				JsonNode value = node.getItem(propertyName);
				JsonUtil.setAnyProperty(json, propertyName, value);
			});
		}
		WriterUtil.writeExtraProperties(node, json);
	}

	public void writeHeader(OpenApi20Header node, ObjectNode json) {
		if (node == null) {
			return;
		}
		JsonUtil.setStringProperty(json, "description", node.getDescription());
		JsonUtil.setStringProperty(json, "type", node.getType());
		JsonUtil.setStringProperty(json, "format", node.getFormat());
		JsonUtil.setAnyProperty(json, "default", node.getDefault());
		JsonUtil.setNumberProperty(json, "maximum", node.getMaximum());
		JsonUtil.setBooleanProperty(json, "exclusiveMaximum", node.isExclusiveMaximum());
		JsonUtil.setNumberProperty(json, "minimum", node.getMinimum());
		JsonUtil.setBooleanProperty(json, "exclusiveMinimum", node.isExclusiveMinimum());
		JsonUtil.setIntegerProperty(json, "maxLength", node.getMaxLength());
		JsonUtil.setIntegerProperty(json, "minLength", node.getMinLength());
		JsonUtil.setStringProperty(json, "pattern", node.getPattern());
		JsonUtil.setIntegerProperty(json, "maxItems", node.getMaxItems());
		JsonUtil.setIntegerProperty(json, "minItems", node.getMinItems());
		JsonUtil.setBooleanProperty(json, "uniqueItems", node.isUniqueItems());
		JsonUtil.setAnyArrayProperty(json, "enum", node.getEnum());
		JsonUtil.setNumberProperty(json, "multipleOf", node.getMultipleOf());
		{
			if (node.getItems() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeItems((OpenApi20Items) node.getItems(), object);
				JsonUtil.setObjectProperty(json, "items", object);
			}
		}
		JsonUtil.setStringProperty(json, "collectionFormat", node.getCollectionFormat());
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

	public void writeTag(OpenApi20Tag node, ObjectNode json) {
		if (node == null) {
			return;
		}
		JsonUtil.setStringProperty(json, "name", node.getName());
		JsonUtil.setStringProperty(json, "description", node.getDescription());
		{
			if (node.getExternalDocs() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeExternalDocumentation((OpenApi20ExternalDocumentation) node.getExternalDocs(), object);
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

	public void writeSchema(OpenApi20Schema node, ObjectNode json) {
		if (node == null) {
			return;
		}
		JsonUtil.setStringProperty(json, "$ref", node.get$ref());
		JsonUtil.setStringProperty(json, "description", node.getDescription());
		JsonUtil.setStringProperty(json, "type", node.getType());
		JsonUtil.setStringProperty(json, "format", node.getFormat());
		JsonUtil.setAnyProperty(json, "default", node.getDefault());
		JsonUtil.setNumberProperty(json, "maximum", node.getMaximum());
		JsonUtil.setBooleanProperty(json, "exclusiveMaximum", node.isExclusiveMaximum());
		JsonUtil.setNumberProperty(json, "minimum", node.getMinimum());
		JsonUtil.setBooleanProperty(json, "exclusiveMinimum", node.isExclusiveMinimum());
		JsonUtil.setIntegerProperty(json, "maxLength", node.getMaxLength());
		JsonUtil.setIntegerProperty(json, "minLength", node.getMinLength());
		JsonUtil.setStringProperty(json, "pattern", node.getPattern());
		JsonUtil.setIntegerProperty(json, "maxItems", node.getMaxItems());
		JsonUtil.setIntegerProperty(json, "minItems", node.getMinItems());
		JsonUtil.setBooleanProperty(json, "uniqueItems", node.isUniqueItems());
		JsonUtil.setAnyArrayProperty(json, "enum", node.getEnum());
		JsonUtil.setNumberProperty(json, "multipleOf", node.getMultipleOf());
		JsonUtil.setStringProperty(json, "title", node.getTitle());
		{
			SchemaSchemaListUnion union = node.getItems();
			if (union != null) {
				if (union.isSchema()) {
					ObjectNode jsonValue = JsonUtil.objectNode();
					this.writeSchema((OpenApi20Schema) union.asSchema(), jsonValue);
					JsonUtil.setObjectProperty(json, "items", jsonValue);
				}
				if (union.isSchemaList()) {
					List<? extends Schema> models = union.asSchemaList();
					ArrayNode array = JsonUtil.arrayNode();
					models.forEach(model -> {
						ObjectNode object = JsonUtil.objectNode();
						this.writeSchema((OpenApi20Schema) model, object);
						JsonUtil.addToArray(array, object);
					});
					JsonUtil.setAnyProperty(json, "items", array);
				}
			}
		}
		JsonUtil.setIntegerProperty(json, "maxProperties", node.getMaxProperties());
		JsonUtil.setIntegerProperty(json, "minProperties", node.getMinProperties());
		JsonUtil.setStringArrayProperty(json, "required", node.getRequired());
		{
			List<? extends Schema> models = node.getAllOf();
			if (models != null && !models.isEmpty()) {
				ArrayNode array = JsonUtil.arrayNode();
				models.forEach(model -> {
					ObjectNode object = JsonUtil.objectNode();
					this.writeSchema((OpenApi20Schema) model, object);
					JsonUtil.addToArray(array, object);
				});
				JsonUtil.setAnyProperty(json, "allOf", array);
			}
		}
		{
			Map<String, ? extends Schema> models = node.getProperties();
			if (models != null && !models.isEmpty()) {
				ObjectNode object = JsonUtil.objectNode();
				models.keySet().forEach(jsonName -> {
					ObjectNode jsonValue = JsonUtil.objectNode();
					this.writeSchema((OpenApi20Schema) models.get(jsonName), jsonValue);
					JsonUtil.setObjectProperty(object, jsonName, jsonValue);
				});
				JsonUtil.setObjectProperty(json, "properties", object);
			}
		}
		{
			BooleanSchemaUnion union = node.getAdditionalProperties();
			if (union != null) {
				if (union.isBoolean()) {
					JsonUtil.setBooleanProperty(json, "additionalProperties", union.asBoolean());
				}
				if (union.isSchema()) {
					ObjectNode jsonValue = JsonUtil.objectNode();
					this.writeSchema((OpenApi20Schema) union.asSchema(), jsonValue);
					JsonUtil.setObjectProperty(json, "additionalProperties", jsonValue);
				}
			}
		}
		JsonUtil.setStringProperty(json, "discriminator", node.getDiscriminator());
		JsonUtil.setBooleanProperty(json, "readOnly", node.isReadOnly());
		{
			if (node.getXml() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeXML((OpenApi20XML) node.getXml(), object);
				JsonUtil.setObjectProperty(json, "xml", object);
			}
		}
		{
			if (node.getExternalDocs() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeExternalDocumentation((OpenApi20ExternalDocumentation) node.getExternalDocs(), object);
				JsonUtil.setObjectProperty(json, "externalDocs", object);
			}
		}
		JsonUtil.setAnyProperty(json, "example", node.getExample());
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

	public void writeXML(OpenApi20XML node, ObjectNode json) {
		if (node == null) {
			return;
		}
		JsonUtil.setStringProperty(json, "name", node.getName());
		JsonUtil.setStringProperty(json, "namespace", node.getNamespace());
		JsonUtil.setStringProperty(json, "prefix", node.getPrefix());
		JsonUtil.setBooleanProperty(json, "attribute", node.isAttribute());
		JsonUtil.setBooleanProperty(json, "wrapped", node.isWrapped());
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

	public void writeDefinitions(OpenApi20Definitions node, ObjectNode json) {
		if (node == null) {
			return;
		}
		{
			List<String> propertyNames = node.getItemNames();
			propertyNames.forEach(propertyName -> {
				ObjectNode object = JsonUtil.objectNode();
				this.writeSchema((OpenApi20Schema) node.getItem(propertyName), object);
				JsonUtil.setObjectProperty(json, propertyName, object);
			});
		}
		WriterUtil.writeExtraProperties(node, json);
	}

	public void writeParameterDefinitions(OpenApi20ParameterDefinitions node, ObjectNode json) {
		if (node == null) {
			return;
		}
		{
			List<String> propertyNames = node.getItemNames();
			propertyNames.forEach(propertyName -> {
				ObjectNode object = JsonUtil.objectNode();
				this.writeParameter((OpenApi20Parameter) node.getItem(propertyName), object);
				JsonUtil.setObjectProperty(json, propertyName, object);
			});
		}
		WriterUtil.writeExtraProperties(node, json);
	}

	public void writeResponseDefinitions(OpenApi20ResponseDefinitions node, ObjectNode json) {
		if (node == null) {
			return;
		}
		{
			List<String> propertyNames = node.getItemNames();
			propertyNames.forEach(propertyName -> {
				ObjectNode object = JsonUtil.objectNode();
				this.writeResponse((OpenApi20Response) node.getItem(propertyName), object);
				JsonUtil.setObjectProperty(json, propertyName, object);
			});
		}
		WriterUtil.writeExtraProperties(node, json);
	}

	public void writeSecurityDefinitions(OpenApi20SecurityDefinitions node, ObjectNode json) {
		if (node == null) {
			return;
		}
		{
			List<String> propertyNames = node.getItemNames();
			propertyNames.forEach(propertyName -> {
				ObjectNode object = JsonUtil.objectNode();
				this.writeSecurityScheme((OpenApi20SecurityScheme) node.getItem(propertyName), object);
				JsonUtil.setObjectProperty(json, propertyName, object);
			});
		}
		WriterUtil.writeExtraProperties(node, json);
	}

	public void writeSecurityScheme(OpenApi20SecurityScheme node, ObjectNode json) {
		if (node == null) {
			return;
		}
		JsonUtil.setStringProperty(json, "type", node.getType());
		JsonUtil.setStringProperty(json, "description", node.getDescription());
		JsonUtil.setStringProperty(json, "name", node.getName());
		JsonUtil.setStringProperty(json, "in", node.getIn());
		JsonUtil.setStringProperty(json, "flow", node.getFlow());
		JsonUtil.setStringProperty(json, "authorizationUrl", node.getAuthorizationUrl());
		JsonUtil.setStringProperty(json, "tokenUrl", node.getTokenUrl());
		{
			if (node.getScopes() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeScopes((OpenApi20Scopes) node.getScopes(), object);
				JsonUtil.setObjectProperty(json, "scopes", object);
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

	public void writeScopes(OpenApi20Scopes node, ObjectNode json) {
		if (node == null) {
			return;
		}
		{
			List<String> propertyNames = node.getItemNames();
			propertyNames.forEach(propertyName -> {
				String value = node.getItem(propertyName);
				JsonUtil.setStringProperty(json, propertyName, value);
			});
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

	public void writeSecurityRequirement(OpenApi20SecurityRequirement node, ObjectNode json) {
		if (node == null) {
			return;
		}
		{
			List<String> propertyNames = node.getItemNames();
			propertyNames.forEach(propertyName -> {
				List<String> value = node.getItem(propertyName);
				JsonUtil.setStringArrayProperty(json, propertyName, value);
			});
		}
		WriterUtil.writeExtraProperties(node, json);
	}
}