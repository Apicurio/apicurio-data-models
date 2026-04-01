package io.apicurio.datamodels.models.openapi.v3x.v30.io;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.apicurio.datamodels.models.Example;
import io.apicurio.datamodels.models.Link;
import io.apicurio.datamodels.models.Parameter;
import io.apicurio.datamodels.models.RootNode;
import io.apicurio.datamodels.models.Schema;
import io.apicurio.datamodels.models.SecurityRequirement;
import io.apicurio.datamodels.models.SecurityScheme;
import io.apicurio.datamodels.models.Server;
import io.apicurio.datamodels.models.ServerVariable;
import io.apicurio.datamodels.models.Tag;
import io.apicurio.datamodels.models.io.ModelWriter;
import io.apicurio.datamodels.models.openapi.OpenApiCallback;
import io.apicurio.datamodels.models.openapi.OpenApiEncoding;
import io.apicurio.datamodels.models.openapi.OpenApiHeader;
import io.apicurio.datamodels.models.openapi.OpenApiMediaType;
import io.apicurio.datamodels.models.openapi.OpenApiRequestBody;
import io.apicurio.datamodels.models.openapi.OpenApiResponse;
import io.apicurio.datamodels.models.openapi.v3x.v30.OpenApi30Callback;
import io.apicurio.datamodels.models.openapi.v3x.v30.OpenApi30Components;
import io.apicurio.datamodels.models.openapi.v3x.v30.OpenApi30Contact;
import io.apicurio.datamodels.models.openapi.v3x.v30.OpenApi30Discriminator;
import io.apicurio.datamodels.models.openapi.v3x.v30.OpenApi30Document;
import io.apicurio.datamodels.models.openapi.v3x.v30.OpenApi30Encoding;
import io.apicurio.datamodels.models.openapi.v3x.v30.OpenApi30Example;
import io.apicurio.datamodels.models.openapi.v3x.v30.OpenApi30ExternalDocumentation;
import io.apicurio.datamodels.models.openapi.v3x.v30.OpenApi30Header;
import io.apicurio.datamodels.models.openapi.v3x.v30.OpenApi30Info;
import io.apicurio.datamodels.models.openapi.v3x.v30.OpenApi30License;
import io.apicurio.datamodels.models.openapi.v3x.v30.OpenApi30Link;
import io.apicurio.datamodels.models.openapi.v3x.v30.OpenApi30MediaType;
import io.apicurio.datamodels.models.openapi.v3x.v30.OpenApi30OAuthFlow;
import io.apicurio.datamodels.models.openapi.v3x.v30.OpenApi30OAuthFlows;
import io.apicurio.datamodels.models.openapi.v3x.v30.OpenApi30Operation;
import io.apicurio.datamodels.models.openapi.v3x.v30.OpenApi30Parameter;
import io.apicurio.datamodels.models.openapi.v3x.v30.OpenApi30PathItem;
import io.apicurio.datamodels.models.openapi.v3x.v30.OpenApi30Paths;
import io.apicurio.datamodels.models.openapi.v3x.v30.OpenApi30RequestBody;
import io.apicurio.datamodels.models.openapi.v3x.v30.OpenApi30Response;
import io.apicurio.datamodels.models.openapi.v3x.v30.OpenApi30Responses;
import io.apicurio.datamodels.models.openapi.v3x.v30.OpenApi30Schema;
import io.apicurio.datamodels.models.openapi.v3x.v30.OpenApi30SecurityRequirement;
import io.apicurio.datamodels.models.openapi.v3x.v30.OpenApi30SecurityScheme;
import io.apicurio.datamodels.models.openapi.v3x.v30.OpenApi30Server;
import io.apicurio.datamodels.models.openapi.v3x.v30.OpenApi30ServerVariable;
import io.apicurio.datamodels.models.openapi.v3x.v30.OpenApi30Tag;
import io.apicurio.datamodels.models.openapi.v3x.v30.OpenApi30XML;
import io.apicurio.datamodels.models.union.BooleanSchemaUnion;
import io.apicurio.datamodels.models.util.JsonUtil;
import io.apicurio.datamodels.models.util.WriterUtil;
import java.util.List;
import java.util.Map;

public class OpenApi30ModelWriter implements ModelWriter {

	public void writeDocument(OpenApi30Document node, ObjectNode json) {
		if (node == null) {
			return;
		}
		JsonUtil.setStringProperty(json, "openapi", node.getOpenapi());
		{
			if (node.getInfo() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeInfo((OpenApi30Info) node.getInfo(), object);
				JsonUtil.setObjectProperty(json, "info", object);
			}
		}
		{
			List<? extends Server> models = node.getServers();
			if (models != null && !models.isEmpty()) {
				ArrayNode array = JsonUtil.arrayNode();
				models.forEach(model -> {
					ObjectNode object = JsonUtil.objectNode();
					this.writeServer((OpenApi30Server) model, object);
					JsonUtil.addToArray(array, object);
				});
				JsonUtil.setAnyProperty(json, "servers", array);
			}
		}
		{
			if (node.getPaths() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writePaths((OpenApi30Paths) node.getPaths(), object);
				JsonUtil.setObjectProperty(json, "paths", object);
			}
		}
		{
			if (node.getComponents() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeComponents((OpenApi30Components) node.getComponents(), object);
				JsonUtil.setObjectProperty(json, "components", object);
			}
		}
		{
			List<? extends SecurityRequirement> models = node.getSecurity();
			if (models != null && !models.isEmpty()) {
				ArrayNode array = JsonUtil.arrayNode();
				models.forEach(model -> {
					ObjectNode object = JsonUtil.objectNode();
					this.writeSecurityRequirement((OpenApi30SecurityRequirement) model, object);
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
					this.writeTag((OpenApi30Tag) model, object);
					JsonUtil.addToArray(array, object);
				});
				JsonUtil.setAnyProperty(json, "tags", array);
			}
		}
		{
			if (node.getExternalDocs() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeExternalDocumentation((OpenApi30ExternalDocumentation) node.getExternalDocs(), object);
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
		this.writeDocument((OpenApi30Document) node, json);
		return json;
	}

	public void writeInfo(OpenApi30Info node, ObjectNode json) {
		if (node == null) {
			return;
		}
		JsonUtil.setStringProperty(json, "title", node.getTitle());
		JsonUtil.setStringProperty(json, "description", node.getDescription());
		JsonUtil.setStringProperty(json, "termsOfService", node.getTermsOfService());
		{
			if (node.getContact() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeContact((OpenApi30Contact) node.getContact(), object);
				JsonUtil.setObjectProperty(json, "contact", object);
			}
		}
		{
			if (node.getLicense() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeLicense((OpenApi30License) node.getLicense(), object);
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

	public void writeContact(OpenApi30Contact node, ObjectNode json) {
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

	public void writeLicense(OpenApi30License node, ObjectNode json) {
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

	public void writeServer(OpenApi30Server node, ObjectNode json) {
		if (node == null) {
			return;
		}
		JsonUtil.setStringProperty(json, "url", node.getUrl());
		JsonUtil.setStringProperty(json, "description", node.getDescription());
		{
			Map<String, ? extends ServerVariable> models = node.getVariables();
			if (models != null && !models.isEmpty()) {
				ObjectNode object = JsonUtil.objectNode();
				models.keySet().forEach(jsonName -> {
					ObjectNode jsonValue = JsonUtil.objectNode();
					this.writeServerVariable((OpenApi30ServerVariable) models.get(jsonName), jsonValue);
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

	public void writeServerVariable(OpenApi30ServerVariable node, ObjectNode json) {
		if (node == null) {
			return;
		}
		JsonUtil.setStringArrayProperty(json, "enum", node.getEnum());
		JsonUtil.setStringProperty(json, "default", node.getDefault());
		JsonUtil.setStringProperty(json, "description", node.getDescription());
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

	public void writeComponents(OpenApi30Components node, ObjectNode json) {
		if (node == null) {
			return;
		}
		{
			Map<String, ? extends Schema> models = node.getSchemas();
			if (models != null && !models.isEmpty()) {
				ObjectNode object = JsonUtil.objectNode();
				models.keySet().forEach(jsonName -> {
					ObjectNode jsonValue = JsonUtil.objectNode();
					this.writeSchema((OpenApi30Schema) models.get(jsonName), jsonValue);
					JsonUtil.setObjectProperty(object, jsonName, jsonValue);
				});
				JsonUtil.setObjectProperty(json, "schemas", object);
			}
		}
		{
			Map<String, ? extends OpenApiResponse> models = node.getResponses();
			if (models != null && !models.isEmpty()) {
				ObjectNode object = JsonUtil.objectNode();
				models.keySet().forEach(jsonName -> {
					ObjectNode jsonValue = JsonUtil.objectNode();
					this.writeResponse((OpenApi30Response) models.get(jsonName), jsonValue);
					JsonUtil.setObjectProperty(object, jsonName, jsonValue);
				});
				JsonUtil.setObjectProperty(json, "responses", object);
			}
		}
		{
			Map<String, ? extends Parameter> models = node.getParameters();
			if (models != null && !models.isEmpty()) {
				ObjectNode object = JsonUtil.objectNode();
				models.keySet().forEach(jsonName -> {
					ObjectNode jsonValue = JsonUtil.objectNode();
					this.writeParameter((OpenApi30Parameter) models.get(jsonName), jsonValue);
					JsonUtil.setObjectProperty(object, jsonName, jsonValue);
				});
				JsonUtil.setObjectProperty(json, "parameters", object);
			}
		}
		{
			Map<String, ? extends Example> models = node.getExamples();
			if (models != null && !models.isEmpty()) {
				ObjectNode object = JsonUtil.objectNode();
				models.keySet().forEach(jsonName -> {
					ObjectNode jsonValue = JsonUtil.objectNode();
					this.writeExample((OpenApi30Example) models.get(jsonName), jsonValue);
					JsonUtil.setObjectProperty(object, jsonName, jsonValue);
				});
				JsonUtil.setObjectProperty(json, "examples", object);
			}
		}
		{
			Map<String, ? extends OpenApiRequestBody> models = node.getRequestBodies();
			if (models != null && !models.isEmpty()) {
				ObjectNode object = JsonUtil.objectNode();
				models.keySet().forEach(jsonName -> {
					ObjectNode jsonValue = JsonUtil.objectNode();
					this.writeRequestBody((OpenApi30RequestBody) models.get(jsonName), jsonValue);
					JsonUtil.setObjectProperty(object, jsonName, jsonValue);
				});
				JsonUtil.setObjectProperty(json, "requestBodies", object);
			}
		}
		{
			Map<String, ? extends OpenApiHeader> models = node.getHeaders();
			if (models != null && !models.isEmpty()) {
				ObjectNode object = JsonUtil.objectNode();
				models.keySet().forEach(jsonName -> {
					ObjectNode jsonValue = JsonUtil.objectNode();
					this.writeHeader((OpenApi30Header) models.get(jsonName), jsonValue);
					JsonUtil.setObjectProperty(object, jsonName, jsonValue);
				});
				JsonUtil.setObjectProperty(json, "headers", object);
			}
		}
		{
			Map<String, ? extends SecurityScheme> models = node.getSecuritySchemes();
			if (models != null && !models.isEmpty()) {
				ObjectNode object = JsonUtil.objectNode();
				models.keySet().forEach(jsonName -> {
					ObjectNode jsonValue = JsonUtil.objectNode();
					this.writeSecurityScheme((OpenApi30SecurityScheme) models.get(jsonName), jsonValue);
					JsonUtil.setObjectProperty(object, jsonName, jsonValue);
				});
				JsonUtil.setObjectProperty(json, "securitySchemes", object);
			}
		}
		{
			Map<String, ? extends Link> models = node.getLinks();
			if (models != null && !models.isEmpty()) {
				ObjectNode object = JsonUtil.objectNode();
				models.keySet().forEach(jsonName -> {
					ObjectNode jsonValue = JsonUtil.objectNode();
					this.writeLink((OpenApi30Link) models.get(jsonName), jsonValue);
					JsonUtil.setObjectProperty(object, jsonName, jsonValue);
				});
				JsonUtil.setObjectProperty(json, "links", object);
			}
		}
		{
			Map<String, ? extends OpenApiCallback> models = node.getCallbacks();
			if (models != null && !models.isEmpty()) {
				ObjectNode object = JsonUtil.objectNode();
				models.keySet().forEach(jsonName -> {
					ObjectNode jsonValue = JsonUtil.objectNode();
					this.writeCallback((OpenApi30Callback) models.get(jsonName), jsonValue);
					JsonUtil.setObjectProperty(object, jsonName, jsonValue);
				});
				JsonUtil.setObjectProperty(json, "callbacks", object);
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

	public void writePaths(OpenApi30Paths node, ObjectNode json) {
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
				this.writePathItem((OpenApi30PathItem) node.getItem(propertyName), object);
				JsonUtil.setObjectProperty(json, propertyName, object);
			});
		}
		WriterUtil.writeExtraProperties(node, json);
	}

	public void writePathItem(OpenApi30PathItem node, ObjectNode json) {
		if (node == null) {
			return;
		}
		JsonUtil.setStringProperty(json, "$ref", node.get$ref());
		JsonUtil.setStringProperty(json, "summary", node.getSummary());
		JsonUtil.setStringProperty(json, "description", node.getDescription());
		{
			if (node.getGet() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeOperation((OpenApi30Operation) node.getGet(), object);
				JsonUtil.setObjectProperty(json, "get", object);
			}
		}
		{
			if (node.getPut() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeOperation((OpenApi30Operation) node.getPut(), object);
				JsonUtil.setObjectProperty(json, "put", object);
			}
		}
		{
			if (node.getPost() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeOperation((OpenApi30Operation) node.getPost(), object);
				JsonUtil.setObjectProperty(json, "post", object);
			}
		}
		{
			if (node.getDelete() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeOperation((OpenApi30Operation) node.getDelete(), object);
				JsonUtil.setObjectProperty(json, "delete", object);
			}
		}
		{
			if (node.getOptions() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeOperation((OpenApi30Operation) node.getOptions(), object);
				JsonUtil.setObjectProperty(json, "options", object);
			}
		}
		{
			if (node.getHead() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeOperation((OpenApi30Operation) node.getHead(), object);
				JsonUtil.setObjectProperty(json, "head", object);
			}
		}
		{
			if (node.getPatch() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeOperation((OpenApi30Operation) node.getPatch(), object);
				JsonUtil.setObjectProperty(json, "patch", object);
			}
		}
		{
			if (node.getTrace() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeOperation((OpenApi30Operation) node.getTrace(), object);
				JsonUtil.setObjectProperty(json, "trace", object);
			}
		}
		{
			List<? extends Server> models = node.getServers();
			if (models != null && !models.isEmpty()) {
				ArrayNode array = JsonUtil.arrayNode();
				models.forEach(model -> {
					ObjectNode object = JsonUtil.objectNode();
					this.writeServer((OpenApi30Server) model, object);
					JsonUtil.addToArray(array, object);
				});
				JsonUtil.setAnyProperty(json, "servers", array);
			}
		}
		{
			List<? extends Parameter> models = node.getParameters();
			if (models != null && !models.isEmpty()) {
				ArrayNode array = JsonUtil.arrayNode();
				models.forEach(model -> {
					ObjectNode object = JsonUtil.objectNode();
					this.writeParameter((OpenApi30Parameter) model, object);
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

	public void writeOperation(OpenApi30Operation node, ObjectNode json) {
		if (node == null) {
			return;
		}
		JsonUtil.setStringArrayProperty(json, "tags", node.getTags());
		JsonUtil.setStringProperty(json, "summary", node.getSummary());
		JsonUtil.setStringProperty(json, "description", node.getDescription());
		{
			if (node.getExternalDocs() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeExternalDocumentation((OpenApi30ExternalDocumentation) node.getExternalDocs(), object);
				JsonUtil.setObjectProperty(json, "externalDocs", object);
			}
		}
		JsonUtil.setStringProperty(json, "operationId", node.getOperationId());
		{
			List<? extends Parameter> models = node.getParameters();
			if (models != null && !models.isEmpty()) {
				ArrayNode array = JsonUtil.arrayNode();
				models.forEach(model -> {
					ObjectNode object = JsonUtil.objectNode();
					this.writeParameter((OpenApi30Parameter) model, object);
					JsonUtil.addToArray(array, object);
				});
				JsonUtil.setAnyProperty(json, "parameters", array);
			}
		}
		{
			if (node.getRequestBody() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeRequestBody((OpenApi30RequestBody) node.getRequestBody(), object);
				JsonUtil.setObjectProperty(json, "requestBody", object);
			}
		}
		{
			if (node.getResponses() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeResponses((OpenApi30Responses) node.getResponses(), object);
				JsonUtil.setObjectProperty(json, "responses", object);
			}
		}
		{
			Map<String, ? extends OpenApiCallback> models = node.getCallbacks();
			if (models != null && !models.isEmpty()) {
				ObjectNode object = JsonUtil.objectNode();
				models.keySet().forEach(jsonName -> {
					ObjectNode jsonValue = JsonUtil.objectNode();
					this.writeCallback((OpenApi30Callback) models.get(jsonName), jsonValue);
					JsonUtil.setObjectProperty(object, jsonName, jsonValue);
				});
				JsonUtil.setObjectProperty(json, "callbacks", object);
			}
		}
		JsonUtil.setBooleanProperty(json, "deprecated", node.isDeprecated());
		{
			List<? extends SecurityRequirement> models = node.getSecurity();
			if (models != null && !models.isEmpty()) {
				ArrayNode array = JsonUtil.arrayNode();
				models.forEach(model -> {
					ObjectNode object = JsonUtil.objectNode();
					this.writeSecurityRequirement((OpenApi30SecurityRequirement) model, object);
					JsonUtil.addToArray(array, object);
				});
				JsonUtil.setAnyProperty(json, "security", array);
			}
		}
		{
			List<? extends Server> models = node.getServers();
			if (models != null && !models.isEmpty()) {
				ArrayNode array = JsonUtil.arrayNode();
				models.forEach(model -> {
					ObjectNode object = JsonUtil.objectNode();
					this.writeServer((OpenApi30Server) model, object);
					JsonUtil.addToArray(array, object);
				});
				JsonUtil.setAnyProperty(json, "servers", array);
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

	public void writeExternalDocumentation(OpenApi30ExternalDocumentation node, ObjectNode json) {
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

	public void writeParameter(OpenApi30Parameter node, ObjectNode json) {
		if (node == null) {
			return;
		}
		JsonUtil.setStringProperty(json, "$ref", node.get$ref());
		JsonUtil.setStringProperty(json, "name", node.getName());
		JsonUtil.setStringProperty(json, "in", node.getIn());
		JsonUtil.setStringProperty(json, "description", node.getDescription());
		JsonUtil.setBooleanProperty(json, "required", node.isRequired());
		JsonUtil.setBooleanProperty(json, "deprecated", node.isDeprecated());
		JsonUtil.setBooleanProperty(json, "allowEmptyValue", node.isAllowEmptyValue());
		JsonUtil.setStringProperty(json, "style", node.getStyle());
		JsonUtil.setBooleanProperty(json, "explode", node.isExplode());
		JsonUtil.setBooleanProperty(json, "allowReserved", node.isAllowReserved());
		{
			if (node.getSchema() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeSchema((OpenApi30Schema) node.getSchema(), object);
				JsonUtil.setObjectProperty(json, "schema", object);
			}
		}
		JsonUtil.setAnyProperty(json, "example", node.getExample());
		{
			Map<String, ? extends Example> models = node.getExamples();
			if (models != null && !models.isEmpty()) {
				ObjectNode object = JsonUtil.objectNode();
				models.keySet().forEach(jsonName -> {
					ObjectNode jsonValue = JsonUtil.objectNode();
					this.writeExample((OpenApi30Example) models.get(jsonName), jsonValue);
					JsonUtil.setObjectProperty(object, jsonName, jsonValue);
				});
				JsonUtil.setObjectProperty(json, "examples", object);
			}
		}
		{
			Map<String, ? extends OpenApiMediaType> models = node.getContent();
			if (models != null && !models.isEmpty()) {
				ObjectNode object = JsonUtil.objectNode();
				models.keySet().forEach(jsonName -> {
					ObjectNode jsonValue = JsonUtil.objectNode();
					this.writeMediaType((OpenApi30MediaType) models.get(jsonName), jsonValue);
					JsonUtil.setObjectProperty(object, jsonName, jsonValue);
				});
				JsonUtil.setObjectProperty(json, "content", object);
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

	public void writeRequestBody(OpenApi30RequestBody node, ObjectNode json) {
		if (node == null) {
			return;
		}
		JsonUtil.setStringProperty(json, "$ref", node.get$ref());
		JsonUtil.setStringProperty(json, "description", node.getDescription());
		{
			Map<String, ? extends OpenApiMediaType> models = node.getContent();
			if (models != null && !models.isEmpty()) {
				ObjectNode object = JsonUtil.objectNode();
				models.keySet().forEach(jsonName -> {
					ObjectNode jsonValue = JsonUtil.objectNode();
					this.writeMediaType((OpenApi30MediaType) models.get(jsonName), jsonValue);
					JsonUtil.setObjectProperty(object, jsonName, jsonValue);
				});
				JsonUtil.setObjectProperty(json, "content", object);
			}
		}
		JsonUtil.setBooleanProperty(json, "required", node.isRequired());
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

	public void writeMediaType(OpenApi30MediaType node, ObjectNode json) {
		if (node == null) {
			return;
		}
		{
			if (node.getSchema() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeSchema((OpenApi30Schema) node.getSchema(), object);
				JsonUtil.setObjectProperty(json, "schema", object);
			}
		}
		JsonUtil.setAnyProperty(json, "example", node.getExample());
		{
			Map<String, ? extends Example> models = node.getExamples();
			if (models != null && !models.isEmpty()) {
				ObjectNode object = JsonUtil.objectNode();
				models.keySet().forEach(jsonName -> {
					ObjectNode jsonValue = JsonUtil.objectNode();
					this.writeExample((OpenApi30Example) models.get(jsonName), jsonValue);
					JsonUtil.setObjectProperty(object, jsonName, jsonValue);
				});
				JsonUtil.setObjectProperty(json, "examples", object);
			}
		}
		{
			Map<String, ? extends OpenApiEncoding> models = node.getEncoding();
			if (models != null && !models.isEmpty()) {
				ObjectNode object = JsonUtil.objectNode();
				models.keySet().forEach(jsonName -> {
					ObjectNode jsonValue = JsonUtil.objectNode();
					this.writeEncoding((OpenApi30Encoding) models.get(jsonName), jsonValue);
					JsonUtil.setObjectProperty(object, jsonName, jsonValue);
				});
				JsonUtil.setObjectProperty(json, "encoding", object);
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

	public void writeEncoding(OpenApi30Encoding node, ObjectNode json) {
		if (node == null) {
			return;
		}
		JsonUtil.setStringProperty(json, "contentType", node.getContentType());
		{
			Map<String, ? extends OpenApiHeader> models = node.getHeaders();
			if (models != null && !models.isEmpty()) {
				ObjectNode object = JsonUtil.objectNode();
				models.keySet().forEach(jsonName -> {
					ObjectNode jsonValue = JsonUtil.objectNode();
					this.writeHeader((OpenApi30Header) models.get(jsonName), jsonValue);
					JsonUtil.setObjectProperty(object, jsonName, jsonValue);
				});
				JsonUtil.setObjectProperty(json, "headers", object);
			}
		}
		JsonUtil.setStringProperty(json, "style", node.getStyle());
		JsonUtil.setBooleanProperty(json, "explode", node.isExplode());
		JsonUtil.setBooleanProperty(json, "allowReserved", node.isAllowReserved());
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

	public void writeResponses(OpenApi30Responses node, ObjectNode json) {
		if (node == null) {
			return;
		}
		JsonUtil.setStringProperty(json, "$ref", node.get$ref());
		{
			if (node.getDefault() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeResponse((OpenApi30Response) node.getDefault(), object);
				JsonUtil.setObjectProperty(json, "default", object);
			}
		}
		{
			List<String> propertyNames = node.getItemNames();
			propertyNames.forEach(propertyName -> {
				ObjectNode object = JsonUtil.objectNode();
				this.writeResponse((OpenApi30Response) node.getItem(propertyName), object);
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

	public void writeResponse(OpenApi30Response node, ObjectNode json) {
		if (node == null) {
			return;
		}
		JsonUtil.setStringProperty(json, "$ref", node.get$ref());
		JsonUtil.setStringProperty(json, "description", node.getDescription());
		{
			Map<String, ? extends OpenApiHeader> models = node.getHeaders();
			if (models != null && !models.isEmpty()) {
				ObjectNode object = JsonUtil.objectNode();
				models.keySet().forEach(jsonName -> {
					ObjectNode jsonValue = JsonUtil.objectNode();
					this.writeHeader((OpenApi30Header) models.get(jsonName), jsonValue);
					JsonUtil.setObjectProperty(object, jsonName, jsonValue);
				});
				JsonUtil.setObjectProperty(json, "headers", object);
			}
		}
		{
			Map<String, ? extends OpenApiMediaType> models = node.getContent();
			if (models != null && !models.isEmpty()) {
				ObjectNode object = JsonUtil.objectNode();
				models.keySet().forEach(jsonName -> {
					ObjectNode jsonValue = JsonUtil.objectNode();
					this.writeMediaType((OpenApi30MediaType) models.get(jsonName), jsonValue);
					JsonUtil.setObjectProperty(object, jsonName, jsonValue);
				});
				JsonUtil.setObjectProperty(json, "content", object);
			}
		}
		{
			Map<String, ? extends Link> models = node.getLinks();
			if (models != null && !models.isEmpty()) {
				ObjectNode object = JsonUtil.objectNode();
				models.keySet().forEach(jsonName -> {
					ObjectNode jsonValue = JsonUtil.objectNode();
					this.writeLink((OpenApi30Link) models.get(jsonName), jsonValue);
					JsonUtil.setObjectProperty(object, jsonName, jsonValue);
				});
				JsonUtil.setObjectProperty(json, "links", object);
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

	public void writeCallback(OpenApi30Callback node, ObjectNode json) {
		if (node == null) {
			return;
		}
		JsonUtil.setStringProperty(json, "$ref", node.get$ref());
		{
			List<String> propertyNames = node.getItemNames();
			propertyNames.forEach(propertyName -> {
				ObjectNode object = JsonUtil.objectNode();
				this.writePathItem((OpenApi30PathItem) node.getItem(propertyName), object);
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

	public void writeExample(OpenApi30Example node, ObjectNode json) {
		if (node == null) {
			return;
		}
		JsonUtil.setStringProperty(json, "$ref", node.get$ref());
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

	public void writeLink(OpenApi30Link node, ObjectNode json) {
		if (node == null) {
			return;
		}
		JsonUtil.setStringProperty(json, "$ref", node.get$ref());
		JsonUtil.setStringProperty(json, "operationRef", node.getOperationRef());
		JsonUtil.setStringProperty(json, "operationId", node.getOperationId());
		JsonUtil.setAnyMapProperty(json, "parameters", node.getParameters());
		JsonUtil.setAnyProperty(json, "requestBody", node.getRequestBody());
		JsonUtil.setStringProperty(json, "description", node.getDescription());
		{
			if (node.getServer() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeServer((OpenApi30Server) node.getServer(), object);
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

	public void writeHeader(OpenApi30Header node, ObjectNode json) {
		if (node == null) {
			return;
		}
		JsonUtil.setStringProperty(json, "$ref", node.get$ref());
		JsonUtil.setStringProperty(json, "description", node.getDescription());
		JsonUtil.setBooleanProperty(json, "required", node.isRequired());
		JsonUtil.setBooleanProperty(json, "deprecated", node.isDeprecated());
		JsonUtil.setBooleanProperty(json, "allowEmptyValue", node.isAllowEmptyValue());
		JsonUtil.setStringProperty(json, "style", node.getStyle());
		JsonUtil.setBooleanProperty(json, "explode", node.isExplode());
		JsonUtil.setBooleanProperty(json, "allowReserved", node.isAllowReserved());
		{
			if (node.getSchema() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeSchema((OpenApi30Schema) node.getSchema(), object);
				JsonUtil.setObjectProperty(json, "schema", object);
			}
		}
		JsonUtil.setAnyProperty(json, "example", node.getExample());
		{
			Map<String, ? extends Example> models = node.getExamples();
			if (models != null && !models.isEmpty()) {
				ObjectNode object = JsonUtil.objectNode();
				models.keySet().forEach(jsonName -> {
					ObjectNode jsonValue = JsonUtil.objectNode();
					this.writeExample((OpenApi30Example) models.get(jsonName), jsonValue);
					JsonUtil.setObjectProperty(object, jsonName, jsonValue);
				});
				JsonUtil.setObjectProperty(json, "examples", object);
			}
		}
		{
			Map<String, ? extends OpenApiMediaType> models = node.getContent();
			if (models != null && !models.isEmpty()) {
				ObjectNode object = JsonUtil.objectNode();
				models.keySet().forEach(jsonName -> {
					ObjectNode jsonValue = JsonUtil.objectNode();
					this.writeMediaType((OpenApi30MediaType) models.get(jsonName), jsonValue);
					JsonUtil.setObjectProperty(object, jsonName, jsonValue);
				});
				JsonUtil.setObjectProperty(json, "content", object);
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

	public void writeTag(OpenApi30Tag node, ObjectNode json) {
		if (node == null) {
			return;
		}
		JsonUtil.setStringProperty(json, "name", node.getName());
		JsonUtil.setStringProperty(json, "description", node.getDescription());
		{
			if (node.getExternalDocs() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeExternalDocumentation((OpenApi30ExternalDocumentation) node.getExternalDocs(), object);
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

	public void writeSchema(OpenApi30Schema node, ObjectNode json) {
		if (node == null) {
			return;
		}
		JsonUtil.setStringProperty(json, "$ref", node.get$ref());
		JsonUtil.setStringProperty(json, "format", node.getFormat());
		JsonUtil.setStringProperty(json, "title", node.getTitle());
		JsonUtil.setStringProperty(json, "description", node.getDescription());
		JsonUtil.setAnyProperty(json, "default", node.getDefault());
		JsonUtil.setNumberProperty(json, "multipleOf", node.getMultipleOf());
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
		JsonUtil.setIntegerProperty(json, "maxProperties", node.getMaxProperties());
		JsonUtil.setIntegerProperty(json, "minProperties", node.getMinProperties());
		JsonUtil.setStringArrayProperty(json, "required", node.getRequired());
		JsonUtil.setAnyArrayProperty(json, "enum", node.getEnum());
		JsonUtil.setStringProperty(json, "type", node.getType());
		{
			if (node.getItems() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeSchema((OpenApi30Schema) node.getItems(), object);
				JsonUtil.setObjectProperty(json, "items", object);
			}
		}
		{
			List<? extends Schema> models = node.getAllOf();
			if (models != null && !models.isEmpty()) {
				ArrayNode array = JsonUtil.arrayNode();
				models.forEach(model -> {
					ObjectNode object = JsonUtil.objectNode();
					this.writeSchema((OpenApi30Schema) model, object);
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
					this.writeSchema((OpenApi30Schema) models.get(jsonName), jsonValue);
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
					this.writeSchema((OpenApi30Schema) union.asSchema(), jsonValue);
					JsonUtil.setObjectProperty(json, "additionalProperties", jsonValue);
				}
			}
		}
		JsonUtil.setBooleanProperty(json, "readOnly", node.isReadOnly());
		{
			if (node.getXml() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeXML((OpenApi30XML) node.getXml(), object);
				JsonUtil.setObjectProperty(json, "xml", object);
			}
		}
		{
			if (node.getExternalDocs() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeExternalDocumentation((OpenApi30ExternalDocumentation) node.getExternalDocs(), object);
				JsonUtil.setObjectProperty(json, "externalDocs", object);
			}
		}
		JsonUtil.setAnyProperty(json, "example", node.getExample());
		{
			List<? extends Schema> models = node.getOneOf();
			if (models != null && !models.isEmpty()) {
				ArrayNode array = JsonUtil.arrayNode();
				models.forEach(model -> {
					ObjectNode object = JsonUtil.objectNode();
					this.writeSchema((OpenApi30Schema) model, object);
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
					this.writeSchema((OpenApi30Schema) model, object);
					JsonUtil.addToArray(array, object);
				});
				JsonUtil.setAnyProperty(json, "anyOf", array);
			}
		}
		{
			if (node.getNot() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeSchema((OpenApi30Schema) node.getNot(), object);
				JsonUtil.setObjectProperty(json, "not", object);
			}
		}
		{
			if (node.getDiscriminator() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeDiscriminator((OpenApi30Discriminator) node.getDiscriminator(), object);
				JsonUtil.setObjectProperty(json, "discriminator", object);
			}
		}
		JsonUtil.setBooleanProperty(json, "nullable", node.isNullable());
		JsonUtil.setBooleanProperty(json, "writeOnly", node.isWriteOnly());
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

	public void writeDiscriminator(OpenApi30Discriminator node, ObjectNode json) {
		if (node == null) {
			return;
		}
		JsonUtil.setStringProperty(json, "propertyName", node.getPropertyName());
		JsonUtil.setStringMapProperty(json, "mapping", node.getMapping());
		WriterUtil.writeExtraProperties(node, json);
	}

	public void writeXML(OpenApi30XML node, ObjectNode json) {
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

	public void writeSecurityScheme(OpenApi30SecurityScheme node, ObjectNode json) {
		if (node == null) {
			return;
		}
		JsonUtil.setStringProperty(json, "$ref", node.get$ref());
		JsonUtil.setStringProperty(json, "type", node.getType());
		JsonUtil.setStringProperty(json, "description", node.getDescription());
		JsonUtil.setStringProperty(json, "name", node.getName());
		JsonUtil.setStringProperty(json, "in", node.getIn());
		JsonUtil.setStringProperty(json, "scheme", node.getScheme());
		JsonUtil.setStringProperty(json, "bearerFormat", node.getBearerFormat());
		{
			if (node.getFlows() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeOAuthFlows((OpenApi30OAuthFlows) node.getFlows(), object);
				JsonUtil.setObjectProperty(json, "flows", object);
			}
		}
		JsonUtil.setStringProperty(json, "openIdConnectUrl", node.getOpenIdConnectUrl());
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

	public void writeOAuthFlows(OpenApi30OAuthFlows node, ObjectNode json) {
		if (node == null) {
			return;
		}
		{
			if (node.getImplicit() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeOAuthFlow((OpenApi30OAuthFlow) node.getImplicit(), object);
				JsonUtil.setObjectProperty(json, "implicit", object);
			}
		}
		{
			if (node.getPassword() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeOAuthFlow((OpenApi30OAuthFlow) node.getPassword(), object);
				JsonUtil.setObjectProperty(json, "password", object);
			}
		}
		{
			if (node.getClientCredentials() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeOAuthFlow((OpenApi30OAuthFlow) node.getClientCredentials(), object);
				JsonUtil.setObjectProperty(json, "clientCredentials", object);
			}
		}
		{
			if (node.getAuthorizationCode() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeOAuthFlow((OpenApi30OAuthFlow) node.getAuthorizationCode(), object);
				JsonUtil.setObjectProperty(json, "authorizationCode", object);
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

	public void writeOAuthFlow(OpenApi30OAuthFlow node, ObjectNode json) {
		if (node == null) {
			return;
		}
		JsonUtil.setStringProperty(json, "authorizationUrl", node.getAuthorizationUrl());
		JsonUtil.setStringProperty(json, "tokenUrl", node.getTokenUrl());
		JsonUtil.setStringProperty(json, "refreshUrl", node.getRefreshUrl());
		JsonUtil.setStringMapProperty(json, "scopes", node.getScopes());
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

	public void writeSecurityRequirement(OpenApi30SecurityRequirement node, ObjectNode json) {
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