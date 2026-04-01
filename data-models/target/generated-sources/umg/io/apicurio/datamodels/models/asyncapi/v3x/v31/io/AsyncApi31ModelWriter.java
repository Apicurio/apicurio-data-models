package io.apicurio.datamodels.models.asyncapi.v3x.v31.io;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.apicurio.datamodels.models.ExternalDocumentation;
import io.apicurio.datamodels.models.Operation;
import io.apicurio.datamodels.models.Parameter;
import io.apicurio.datamodels.models.RootNode;
import io.apicurio.datamodels.models.Schema;
import io.apicurio.datamodels.models.SecurityScheme;
import io.apicurio.datamodels.models.Server;
import io.apicurio.datamodels.models.ServerVariable;
import io.apicurio.datamodels.models.Tag;
import io.apicurio.datamodels.models.asyncapi.AsyncApiChannel;
import io.apicurio.datamodels.models.asyncapi.AsyncApiChannelBindings;
import io.apicurio.datamodels.models.asyncapi.AsyncApiCorrelationID;
import io.apicurio.datamodels.models.asyncapi.AsyncApiMessage;
import io.apicurio.datamodels.models.asyncapi.AsyncApiMessageBindings;
import io.apicurio.datamodels.models.asyncapi.AsyncApiMessageExample;
import io.apicurio.datamodels.models.asyncapi.AsyncApiMessageTrait;
import io.apicurio.datamodels.models.asyncapi.AsyncApiOperationBindings;
import io.apicurio.datamodels.models.asyncapi.AsyncApiOperationReply;
import io.apicurio.datamodels.models.asyncapi.AsyncApiOperationReplyAddress;
import io.apicurio.datamodels.models.asyncapi.AsyncApiOperationTrait;
import io.apicurio.datamodels.models.asyncapi.AsyncApiReference;
import io.apicurio.datamodels.models.asyncapi.AsyncApiServerBindings;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31Binding;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31Channel;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31ChannelBindings;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31Channels;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31Components;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31Contact;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31CorrelationID;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31Document;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31ExternalDocumentation;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31Info;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31License;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31Message;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31MessageBindings;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31MessageExample;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31MessageTrait;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31MultiFormatSchema;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31OAuthFlow;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31OAuthFlows;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31Operation;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31OperationBindings;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31OperationReply;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31OperationReplyAddress;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31OperationTrait;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31Operations;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31Parameter;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31Parameters;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31Reference;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31Schema;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31SecurityScheme;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31Server;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31ServerBindings;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31ServerVariable;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31Servers;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31Tag;
import io.apicurio.datamodels.models.io.ModelWriter;
import io.apicurio.datamodels.models.union.AnySchemaUnion;
import io.apicurio.datamodels.models.union.BooleanSchemaUnion;
import io.apicurio.datamodels.models.union.MultiFormatSchemaSchemaUnion;
import io.apicurio.datamodels.models.union.SchemaSchemaListUnion;
import io.apicurio.datamodels.models.util.JsonUtil;
import io.apicurio.datamodels.models.util.WriterUtil;
import java.util.List;
import java.util.Map;

public class AsyncApi31ModelWriter implements ModelWriter {

	public void writeDocument(AsyncApi31Document node, ObjectNode json) {
		if (node == null) {
			return;
		}
		JsonUtil.setStringProperty(json, "asyncapi", node.getAsyncapi());
		JsonUtil.setStringProperty(json, "id", node.getId());
		{
			if (node.getInfo() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeInfo((AsyncApi31Info) node.getInfo(), object);
				JsonUtil.setObjectProperty(json, "info", object);
			}
		}
		{
			if (node.getServers() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeServers((AsyncApi31Servers) node.getServers(), object);
				JsonUtil.setObjectProperty(json, "servers", object);
			}
		}
		JsonUtil.setStringProperty(json, "defaultContentType", node.getDefaultContentType());
		{
			if (node.getChannels() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeChannels((AsyncApi31Channels) node.getChannels(), object);
				JsonUtil.setObjectProperty(json, "channels", object);
			}
		}
		{
			if (node.getOperations() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeOperations((AsyncApi31Operations) node.getOperations(), object);
				JsonUtil.setObjectProperty(json, "operations", object);
			}
		}
		{
			if (node.getComponents() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeComponents((AsyncApi31Components) node.getComponents(), object);
				JsonUtil.setObjectProperty(json, "components", object);
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
		this.writeDocument((AsyncApi31Document) node, json);
		return json;
	}

	public void writeInfo(AsyncApi31Info node, ObjectNode json) {
		if (node == null) {
			return;
		}
		JsonUtil.setStringProperty(json, "title", node.getTitle());
		JsonUtil.setStringProperty(json, "version", node.getVersion());
		JsonUtil.setStringProperty(json, "description", node.getDescription());
		JsonUtil.setStringProperty(json, "termsOfService", node.getTermsOfService());
		{
			if (node.getContact() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeContact((AsyncApi31Contact) node.getContact(), object);
				JsonUtil.setObjectProperty(json, "contact", object);
			}
		}
		{
			if (node.getLicense() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeLicense((AsyncApi31License) node.getLicense(), object);
				JsonUtil.setObjectProperty(json, "license", object);
			}
		}
		{
			List<? extends Tag> models = node.getTags();
			if (models != null && !models.isEmpty()) {
				ArrayNode array = JsonUtil.arrayNode();
				models.forEach(model -> {
					ObjectNode object = JsonUtil.objectNode();
					this.writeTag((AsyncApi31Tag) model, object);
					JsonUtil.addToArray(array, object);
				});
				JsonUtil.setAnyProperty(json, "tags", array);
			}
		}
		{
			if (node.getExternalDocs() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeExternalDocumentation((AsyncApi31ExternalDocumentation) node.getExternalDocs(), object);
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

	public void writeContact(AsyncApi31Contact node, ObjectNode json) {
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

	public void writeLicense(AsyncApi31License node, ObjectNode json) {
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

	public void writeServers(AsyncApi31Servers node, ObjectNode json) {
		if (node == null) {
			return;
		}
		{
			List<String> propertyNames = node.getItemNames();
			propertyNames.forEach(propertyName -> {
				ObjectNode object = JsonUtil.objectNode();
				this.writeServer((AsyncApi31Server) node.getItem(propertyName), object);
				JsonUtil.setObjectProperty(json, propertyName, object);
			});
		}
		WriterUtil.writeExtraProperties(node, json);
	}

	public void writeServer(AsyncApi31Server node, ObjectNode json) {
		if (node == null) {
			return;
		}
		JsonUtil.setStringProperty(json, "$ref", node.get$ref());
		JsonUtil.setStringProperty(json, "host", node.getHost());
		JsonUtil.setStringProperty(json, "protocol", node.getProtocol());
		JsonUtil.setStringProperty(json, "protocolVersion", node.getProtocolVersion());
		JsonUtil.setStringProperty(json, "pathname", node.getPathname());
		JsonUtil.setStringProperty(json, "description", node.getDescription());
		JsonUtil.setStringProperty(json, "title", node.getTitle());
		JsonUtil.setStringProperty(json, "summary", node.getSummary());
		{
			Map<String, ? extends ServerVariable> models = node.getVariables();
			if (models != null && !models.isEmpty()) {
				ObjectNode object = JsonUtil.objectNode();
				models.keySet().forEach(jsonName -> {
					ObjectNode jsonValue = JsonUtil.objectNode();
					this.writeServerVariable((AsyncApi31ServerVariable) models.get(jsonName), jsonValue);
					JsonUtil.setObjectProperty(object, jsonName, jsonValue);
				});
				JsonUtil.setObjectProperty(json, "variables", object);
			}
		}
		{
			List<? extends SecurityScheme> models = node.getSecurity();
			if (models != null && !models.isEmpty()) {
				ArrayNode array = JsonUtil.arrayNode();
				models.forEach(model -> {
					ObjectNode object = JsonUtil.objectNode();
					this.writeSecurityScheme((AsyncApi31SecurityScheme) model, object);
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
					this.writeTag((AsyncApi31Tag) model, object);
					JsonUtil.addToArray(array, object);
				});
				JsonUtil.setAnyProperty(json, "tags", array);
			}
		}
		{
			if (node.getExternalDocs() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeExternalDocumentation((AsyncApi31ExternalDocumentation) node.getExternalDocs(), object);
				JsonUtil.setObjectProperty(json, "externalDocs", object);
			}
		}
		{
			if (node.getBindings() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeServerBindings((AsyncApi31ServerBindings) node.getBindings(), object);
				JsonUtil.setObjectProperty(json, "bindings", object);
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

	public void writeServerVariable(AsyncApi31ServerVariable node, ObjectNode json) {
		if (node == null) {
			return;
		}
		JsonUtil.setStringProperty(json, "$ref", node.get$ref());
		JsonUtil.setStringArrayProperty(json, "enum", node.getEnum());
		JsonUtil.setStringProperty(json, "default", node.getDefault());
		JsonUtil.setStringProperty(json, "description", node.getDescription());
		JsonUtil.setStringArrayProperty(json, "examples", node.getExamples());
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

	public void writeChannels(AsyncApi31Channels node, ObjectNode json) {
		if (node == null) {
			return;
		}
		{
			List<String> propertyNames = node.getItemNames();
			propertyNames.forEach(propertyName -> {
				ObjectNode object = JsonUtil.objectNode();
				this.writeChannel((AsyncApi31Channel) node.getItem(propertyName), object);
				JsonUtil.setObjectProperty(json, propertyName, object);
			});
		}
		WriterUtil.writeExtraProperties(node, json);
	}

	public void writeChannel(AsyncApi31Channel node, ObjectNode json) {
		if (node == null) {
			return;
		}
		JsonUtil.setStringProperty(json, "$ref", node.get$ref());
		JsonUtil.setStringProperty(json, "address", node.getAddress());
		{
			Map<String, ? extends AsyncApiMessage> models = node.getMessages();
			if (models != null && !models.isEmpty()) {
				ObjectNode object = JsonUtil.objectNode();
				models.keySet().forEach(jsonName -> {
					ObjectNode jsonValue = JsonUtil.objectNode();
					this.writeMessage((AsyncApi31Message) models.get(jsonName), jsonValue);
					JsonUtil.setObjectProperty(object, jsonName, jsonValue);
				});
				JsonUtil.setObjectProperty(json, "messages", object);
			}
		}
		JsonUtil.setStringProperty(json, "title", node.getTitle());
		JsonUtil.setStringProperty(json, "summary", node.getSummary());
		JsonUtil.setStringProperty(json, "description", node.getDescription());
		{
			List<? extends AsyncApiReference> models = node.getServers();
			if (models != null && !models.isEmpty()) {
				ArrayNode array = JsonUtil.arrayNode();
				models.forEach(model -> {
					ObjectNode object = JsonUtil.objectNode();
					this.writeReference((AsyncApi31Reference) model, object);
					JsonUtil.addToArray(array, object);
				});
				JsonUtil.setAnyProperty(json, "servers", array);
			}
		}
		{
			if (node.getParameters() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeParameters((AsyncApi31Parameters) node.getParameters(), object);
				JsonUtil.setObjectProperty(json, "parameters", object);
			}
		}
		{
			List<? extends Tag> models = node.getTags();
			if (models != null && !models.isEmpty()) {
				ArrayNode array = JsonUtil.arrayNode();
				models.forEach(model -> {
					ObjectNode object = JsonUtil.objectNode();
					this.writeTag((AsyncApi31Tag) model, object);
					JsonUtil.addToArray(array, object);
				});
				JsonUtil.setAnyProperty(json, "tags", array);
			}
		}
		{
			if (node.getExternalDocs() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeExternalDocumentation((AsyncApi31ExternalDocumentation) node.getExternalDocs(), object);
				JsonUtil.setObjectProperty(json, "externalDocs", object);
			}
		}
		{
			if (node.getBindings() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeChannelBindings((AsyncApi31ChannelBindings) node.getBindings(), object);
				JsonUtil.setObjectProperty(json, "bindings", object);
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

	public void writeOperations(AsyncApi31Operations node, ObjectNode json) {
		if (node == null) {
			return;
		}
		{
			List<String> propertyNames = node.getItemNames();
			propertyNames.forEach(propertyName -> {
				ObjectNode object = JsonUtil.objectNode();
				this.writeOperation((AsyncApi31Operation) node.getItem(propertyName), object);
				JsonUtil.setObjectProperty(json, propertyName, object);
			});
		}
		WriterUtil.writeExtraProperties(node, json);
	}

	public void writeOperation(AsyncApi31Operation node, ObjectNode json) {
		if (node == null) {
			return;
		}
		JsonUtil.setStringProperty(json, "$ref", node.get$ref());
		JsonUtil.setStringProperty(json, "action", node.getAction());
		{
			if (node.getChannel() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeReference((AsyncApi31Reference) node.getChannel(), object);
				JsonUtil.setObjectProperty(json, "channel", object);
			}
		}
		JsonUtil.setStringProperty(json, "title", node.getTitle());
		JsonUtil.setStringProperty(json, "summary", node.getSummary());
		JsonUtil.setStringProperty(json, "description", node.getDescription());
		{
			List<? extends SecurityScheme> models = node.getSecurity();
			if (models != null && !models.isEmpty()) {
				ArrayNode array = JsonUtil.arrayNode();
				models.forEach(model -> {
					ObjectNode object = JsonUtil.objectNode();
					this.writeSecurityScheme((AsyncApi31SecurityScheme) model, object);
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
					this.writeTag((AsyncApi31Tag) model, object);
					JsonUtil.addToArray(array, object);
				});
				JsonUtil.setAnyProperty(json, "tags", array);
			}
		}
		{
			if (node.getExternalDocs() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeExternalDocumentation((AsyncApi31ExternalDocumentation) node.getExternalDocs(), object);
				JsonUtil.setObjectProperty(json, "externalDocs", object);
			}
		}
		{
			if (node.getBindings() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeOperationBindings((AsyncApi31OperationBindings) node.getBindings(), object);
				JsonUtil.setObjectProperty(json, "bindings", object);
			}
		}
		{
			List<? extends AsyncApiOperationTrait> models = node.getTraits();
			if (models != null && !models.isEmpty()) {
				ArrayNode array = JsonUtil.arrayNode();
				models.forEach(model -> {
					ObjectNode object = JsonUtil.objectNode();
					this.writeOperationTrait((AsyncApi31OperationTrait) model, object);
					JsonUtil.addToArray(array, object);
				});
				JsonUtil.setAnyProperty(json, "traits", array);
			}
		}
		{
			List<? extends AsyncApiReference> models = node.getMessages();
			if (models != null && !models.isEmpty()) {
				ArrayNode array = JsonUtil.arrayNode();
				models.forEach(model -> {
					ObjectNode object = JsonUtil.objectNode();
					this.writeReference((AsyncApi31Reference) model, object);
					JsonUtil.addToArray(array, object);
				});
				JsonUtil.setAnyProperty(json, "messages", array);
			}
		}
		{
			if (node.getReply() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeOperationReply((AsyncApi31OperationReply) node.getReply(), object);
				JsonUtil.setObjectProperty(json, "reply", object);
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

	public void writeOperationTrait(AsyncApi31OperationTrait node, ObjectNode json) {
		if (node == null) {
			return;
		}
		JsonUtil.setStringProperty(json, "$ref", node.get$ref());
		JsonUtil.setStringProperty(json, "title", node.getTitle());
		JsonUtil.setStringProperty(json, "summary", node.getSummary());
		JsonUtil.setStringProperty(json, "description", node.getDescription());
		{
			List<? extends SecurityScheme> models = node.getSecurity();
			if (models != null && !models.isEmpty()) {
				ArrayNode array = JsonUtil.arrayNode();
				models.forEach(model -> {
					ObjectNode object = JsonUtil.objectNode();
					this.writeSecurityScheme((AsyncApi31SecurityScheme) model, object);
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
					this.writeTag((AsyncApi31Tag) model, object);
					JsonUtil.addToArray(array, object);
				});
				JsonUtil.setAnyProperty(json, "tags", array);
			}
		}
		{
			if (node.getExternalDocs() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeExternalDocumentation((AsyncApi31ExternalDocumentation) node.getExternalDocs(), object);
				JsonUtil.setObjectProperty(json, "externalDocs", object);
			}
		}
		{
			if (node.getBindings() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeOperationBindings((AsyncApi31OperationBindings) node.getBindings(), object);
				JsonUtil.setObjectProperty(json, "bindings", object);
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

	public void writeOperationReply(AsyncApi31OperationReply node, ObjectNode json) {
		if (node == null) {
			return;
		}
		JsonUtil.setStringProperty(json, "$ref", node.get$ref());
		{
			if (node.getAddress() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeOperationReplyAddress((AsyncApi31OperationReplyAddress) node.getAddress(), object);
				JsonUtil.setObjectProperty(json, "address", object);
			}
		}
		{
			if (node.getChannel() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeReference((AsyncApi31Reference) node.getChannel(), object);
				JsonUtil.setObjectProperty(json, "channel", object);
			}
		}
		{
			List<? extends AsyncApiReference> models = node.getMessages();
			if (models != null && !models.isEmpty()) {
				ArrayNode array = JsonUtil.arrayNode();
				models.forEach(model -> {
					ObjectNode object = JsonUtil.objectNode();
					this.writeReference((AsyncApi31Reference) model, object);
					JsonUtil.addToArray(array, object);
				});
				JsonUtil.setAnyProperty(json, "messages", array);
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

	public void writeOperationReplyAddress(AsyncApi31OperationReplyAddress node, ObjectNode json) {
		if (node == null) {
			return;
		}
		JsonUtil.setStringProperty(json, "$ref", node.get$ref());
		JsonUtil.setStringProperty(json, "description", node.getDescription());
		JsonUtil.setStringProperty(json, "location", node.getLocation());
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

	public void writeParameters(AsyncApi31Parameters node, ObjectNode json) {
		if (node == null) {
			return;
		}
		{
			List<String> propertyNames = node.getItemNames();
			propertyNames.forEach(propertyName -> {
				ObjectNode object = JsonUtil.objectNode();
				this.writeParameter((AsyncApi31Parameter) node.getItem(propertyName), object);
				JsonUtil.setObjectProperty(json, propertyName, object);
			});
		}
		WriterUtil.writeExtraProperties(node, json);
	}

	public void writeParameter(AsyncApi31Parameter node, ObjectNode json) {
		if (node == null) {
			return;
		}
		JsonUtil.setStringProperty(json, "$ref", node.get$ref());
		JsonUtil.setStringArrayProperty(json, "enum", node.getEnum());
		JsonUtil.setStringProperty(json, "default", node.getDefault());
		JsonUtil.setStringProperty(json, "description", node.getDescription());
		JsonUtil.setStringArrayProperty(json, "examples", node.getExamples());
		JsonUtil.setStringProperty(json, "location", node.getLocation());
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

	public void writeServerBindings(AsyncApi31ServerBindings node, ObjectNode json) {
		if (node == null) {
			return;
		}
		JsonUtil.setStringProperty(json, "$ref", node.get$ref());
		{
			if (node.getHttp() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi31Binding) node.getHttp(), object);
				JsonUtil.setObjectProperty(json, "http", object);
			}
		}
		{
			if (node.getWs() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi31Binding) node.getWs(), object);
				JsonUtil.setObjectProperty(json, "ws", object);
			}
		}
		{
			if (node.getKafka() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi31Binding) node.getKafka(), object);
				JsonUtil.setObjectProperty(json, "kafka", object);
			}
		}
		{
			if (node.getAnypointmq() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi31Binding) node.getAnypointmq(), object);
				JsonUtil.setObjectProperty(json, "anypointmq", object);
			}
		}
		{
			if (node.getAmqp() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi31Binding) node.getAmqp(), object);
				JsonUtil.setObjectProperty(json, "amqp", object);
			}
		}
		{
			if (node.getAmqp1() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi31Binding) node.getAmqp1(), object);
				JsonUtil.setObjectProperty(json, "amqp1", object);
			}
		}
		{
			if (node.getMqtt() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi31Binding) node.getMqtt(), object);
				JsonUtil.setObjectProperty(json, "mqtt", object);
			}
		}
		{
			if (node.getMqtt5() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi31Binding) node.getMqtt5(), object);
				JsonUtil.setObjectProperty(json, "mqtt5", object);
			}
		}
		{
			if (node.getNats() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi31Binding) node.getNats(), object);
				JsonUtil.setObjectProperty(json, "nats", object);
			}
		}
		{
			if (node.getJms() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi31Binding) node.getJms(), object);
				JsonUtil.setObjectProperty(json, "jms", object);
			}
		}
		{
			if (node.getSns() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi31Binding) node.getSns(), object);
				JsonUtil.setObjectProperty(json, "sns", object);
			}
		}
		{
			if (node.getSolace() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi31Binding) node.getSolace(), object);
				JsonUtil.setObjectProperty(json, "solace", object);
			}
		}
		{
			if (node.getSqs() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi31Binding) node.getSqs(), object);
				JsonUtil.setObjectProperty(json, "sqs", object);
			}
		}
		{
			if (node.getStomp() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi31Binding) node.getStomp(), object);
				JsonUtil.setObjectProperty(json, "stomp", object);
			}
		}
		{
			if (node.getRedis() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi31Binding) node.getRedis(), object);
				JsonUtil.setObjectProperty(json, "redis", object);
			}
		}
		{
			if (node.getMercure() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi31Binding) node.getMercure(), object);
				JsonUtil.setObjectProperty(json, "mercure", object);
			}
		}
		{
			if (node.getIbmmq() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi31Binding) node.getIbmmq(), object);
				JsonUtil.setObjectProperty(json, "ibmmq", object);
			}
		}
		{
			if (node.getGooglepubsub() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi31Binding) node.getGooglepubsub(), object);
				JsonUtil.setObjectProperty(json, "googlepubsub", object);
			}
		}
		{
			if (node.getPulsar() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi31Binding) node.getPulsar(), object);
				JsonUtil.setObjectProperty(json, "pulsar", object);
			}
		}
		{
			if (node.getRos2() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi31Binding) node.getRos2(), object);
				JsonUtil.setObjectProperty(json, "ros2", object);
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

	public void writeChannelBindings(AsyncApi31ChannelBindings node, ObjectNode json) {
		if (node == null) {
			return;
		}
		JsonUtil.setStringProperty(json, "$ref", node.get$ref());
		{
			if (node.getHttp() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi31Binding) node.getHttp(), object);
				JsonUtil.setObjectProperty(json, "http", object);
			}
		}
		{
			if (node.getWs() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi31Binding) node.getWs(), object);
				JsonUtil.setObjectProperty(json, "ws", object);
			}
		}
		{
			if (node.getKafka() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi31Binding) node.getKafka(), object);
				JsonUtil.setObjectProperty(json, "kafka", object);
			}
		}
		{
			if (node.getAnypointmq() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi31Binding) node.getAnypointmq(), object);
				JsonUtil.setObjectProperty(json, "anypointmq", object);
			}
		}
		{
			if (node.getAmqp() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi31Binding) node.getAmqp(), object);
				JsonUtil.setObjectProperty(json, "amqp", object);
			}
		}
		{
			if (node.getAmqp1() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi31Binding) node.getAmqp1(), object);
				JsonUtil.setObjectProperty(json, "amqp1", object);
			}
		}
		{
			if (node.getMqtt() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi31Binding) node.getMqtt(), object);
				JsonUtil.setObjectProperty(json, "mqtt", object);
			}
		}
		{
			if (node.getMqtt5() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi31Binding) node.getMqtt5(), object);
				JsonUtil.setObjectProperty(json, "mqtt5", object);
			}
		}
		{
			if (node.getNats() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi31Binding) node.getNats(), object);
				JsonUtil.setObjectProperty(json, "nats", object);
			}
		}
		{
			if (node.getJms() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi31Binding) node.getJms(), object);
				JsonUtil.setObjectProperty(json, "jms", object);
			}
		}
		{
			if (node.getSns() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi31Binding) node.getSns(), object);
				JsonUtil.setObjectProperty(json, "sns", object);
			}
		}
		{
			if (node.getSolace() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi31Binding) node.getSolace(), object);
				JsonUtil.setObjectProperty(json, "solace", object);
			}
		}
		{
			if (node.getSqs() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi31Binding) node.getSqs(), object);
				JsonUtil.setObjectProperty(json, "sqs", object);
			}
		}
		{
			if (node.getStomp() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi31Binding) node.getStomp(), object);
				JsonUtil.setObjectProperty(json, "stomp", object);
			}
		}
		{
			if (node.getRedis() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi31Binding) node.getRedis(), object);
				JsonUtil.setObjectProperty(json, "redis", object);
			}
		}
		{
			if (node.getMercure() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi31Binding) node.getMercure(), object);
				JsonUtil.setObjectProperty(json, "mercure", object);
			}
		}
		{
			if (node.getIbmmq() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi31Binding) node.getIbmmq(), object);
				JsonUtil.setObjectProperty(json, "ibmmq", object);
			}
		}
		{
			if (node.getGooglepubsub() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi31Binding) node.getGooglepubsub(), object);
				JsonUtil.setObjectProperty(json, "googlepubsub", object);
			}
		}
		{
			if (node.getPulsar() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi31Binding) node.getPulsar(), object);
				JsonUtil.setObjectProperty(json, "pulsar", object);
			}
		}
		{
			if (node.getRos2() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi31Binding) node.getRos2(), object);
				JsonUtil.setObjectProperty(json, "ros2", object);
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

	public void writeOperationBindings(AsyncApi31OperationBindings node, ObjectNode json) {
		if (node == null) {
			return;
		}
		JsonUtil.setStringProperty(json, "$ref", node.get$ref());
		{
			if (node.getHttp() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi31Binding) node.getHttp(), object);
				JsonUtil.setObjectProperty(json, "http", object);
			}
		}
		{
			if (node.getWs() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi31Binding) node.getWs(), object);
				JsonUtil.setObjectProperty(json, "ws", object);
			}
		}
		{
			if (node.getKafka() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi31Binding) node.getKafka(), object);
				JsonUtil.setObjectProperty(json, "kafka", object);
			}
		}
		{
			if (node.getAnypointmq() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi31Binding) node.getAnypointmq(), object);
				JsonUtil.setObjectProperty(json, "anypointmq", object);
			}
		}
		{
			if (node.getAmqp() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi31Binding) node.getAmqp(), object);
				JsonUtil.setObjectProperty(json, "amqp", object);
			}
		}
		{
			if (node.getAmqp1() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi31Binding) node.getAmqp1(), object);
				JsonUtil.setObjectProperty(json, "amqp1", object);
			}
		}
		{
			if (node.getMqtt() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi31Binding) node.getMqtt(), object);
				JsonUtil.setObjectProperty(json, "mqtt", object);
			}
		}
		{
			if (node.getMqtt5() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi31Binding) node.getMqtt5(), object);
				JsonUtil.setObjectProperty(json, "mqtt5", object);
			}
		}
		{
			if (node.getNats() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi31Binding) node.getNats(), object);
				JsonUtil.setObjectProperty(json, "nats", object);
			}
		}
		{
			if (node.getJms() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi31Binding) node.getJms(), object);
				JsonUtil.setObjectProperty(json, "jms", object);
			}
		}
		{
			if (node.getSns() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi31Binding) node.getSns(), object);
				JsonUtil.setObjectProperty(json, "sns", object);
			}
		}
		{
			if (node.getSolace() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi31Binding) node.getSolace(), object);
				JsonUtil.setObjectProperty(json, "solace", object);
			}
		}
		{
			if (node.getSqs() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi31Binding) node.getSqs(), object);
				JsonUtil.setObjectProperty(json, "sqs", object);
			}
		}
		{
			if (node.getStomp() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi31Binding) node.getStomp(), object);
				JsonUtil.setObjectProperty(json, "stomp", object);
			}
		}
		{
			if (node.getRedis() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi31Binding) node.getRedis(), object);
				JsonUtil.setObjectProperty(json, "redis", object);
			}
		}
		{
			if (node.getMercure() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi31Binding) node.getMercure(), object);
				JsonUtil.setObjectProperty(json, "mercure", object);
			}
		}
		{
			if (node.getIbmmq() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi31Binding) node.getIbmmq(), object);
				JsonUtil.setObjectProperty(json, "ibmmq", object);
			}
		}
		{
			if (node.getGooglepubsub() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi31Binding) node.getGooglepubsub(), object);
				JsonUtil.setObjectProperty(json, "googlepubsub", object);
			}
		}
		{
			if (node.getPulsar() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi31Binding) node.getPulsar(), object);
				JsonUtil.setObjectProperty(json, "pulsar", object);
			}
		}
		{
			if (node.getRos2() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi31Binding) node.getRos2(), object);
				JsonUtil.setObjectProperty(json, "ros2", object);
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

	public void writeMessageBindings(AsyncApi31MessageBindings node, ObjectNode json) {
		if (node == null) {
			return;
		}
		JsonUtil.setStringProperty(json, "$ref", node.get$ref());
		{
			if (node.getHttp() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi31Binding) node.getHttp(), object);
				JsonUtil.setObjectProperty(json, "http", object);
			}
		}
		{
			if (node.getWs() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi31Binding) node.getWs(), object);
				JsonUtil.setObjectProperty(json, "ws", object);
			}
		}
		{
			if (node.getKafka() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi31Binding) node.getKafka(), object);
				JsonUtil.setObjectProperty(json, "kafka", object);
			}
		}
		{
			if (node.getAnypointmq() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi31Binding) node.getAnypointmq(), object);
				JsonUtil.setObjectProperty(json, "anypointmq", object);
			}
		}
		{
			if (node.getAmqp() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi31Binding) node.getAmqp(), object);
				JsonUtil.setObjectProperty(json, "amqp", object);
			}
		}
		{
			if (node.getAmqp1() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi31Binding) node.getAmqp1(), object);
				JsonUtil.setObjectProperty(json, "amqp1", object);
			}
		}
		{
			if (node.getMqtt() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi31Binding) node.getMqtt(), object);
				JsonUtil.setObjectProperty(json, "mqtt", object);
			}
		}
		{
			if (node.getMqtt5() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi31Binding) node.getMqtt5(), object);
				JsonUtil.setObjectProperty(json, "mqtt5", object);
			}
		}
		{
			if (node.getNats() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi31Binding) node.getNats(), object);
				JsonUtil.setObjectProperty(json, "nats", object);
			}
		}
		{
			if (node.getJms() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi31Binding) node.getJms(), object);
				JsonUtil.setObjectProperty(json, "jms", object);
			}
		}
		{
			if (node.getSns() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi31Binding) node.getSns(), object);
				JsonUtil.setObjectProperty(json, "sns", object);
			}
		}
		{
			if (node.getSolace() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi31Binding) node.getSolace(), object);
				JsonUtil.setObjectProperty(json, "solace", object);
			}
		}
		{
			if (node.getSqs() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi31Binding) node.getSqs(), object);
				JsonUtil.setObjectProperty(json, "sqs", object);
			}
		}
		{
			if (node.getStomp() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi31Binding) node.getStomp(), object);
				JsonUtil.setObjectProperty(json, "stomp", object);
			}
		}
		{
			if (node.getRedis() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi31Binding) node.getRedis(), object);
				JsonUtil.setObjectProperty(json, "redis", object);
			}
		}
		{
			if (node.getMercure() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi31Binding) node.getMercure(), object);
				JsonUtil.setObjectProperty(json, "mercure", object);
			}
		}
		{
			if (node.getIbmmq() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi31Binding) node.getIbmmq(), object);
				JsonUtil.setObjectProperty(json, "ibmmq", object);
			}
		}
		{
			if (node.getGooglepubsub() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi31Binding) node.getGooglepubsub(), object);
				JsonUtil.setObjectProperty(json, "googlepubsub", object);
			}
		}
		{
			if (node.getPulsar() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi31Binding) node.getPulsar(), object);
				JsonUtil.setObjectProperty(json, "pulsar", object);
			}
		}
		{
			if (node.getRos2() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi31Binding) node.getRos2(), object);
				JsonUtil.setObjectProperty(json, "ros2", object);
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

	public void writeMessage(AsyncApi31Message node, ObjectNode json) {
		if (node == null) {
			return;
		}
		JsonUtil.setStringProperty(json, "$ref", node.get$ref());
		{
			MultiFormatSchemaSchemaUnion union = node.getHeaders();
			if (union != null) {
				if (union.isMultiFormatSchema()) {
					ObjectNode jsonValue = JsonUtil.objectNode();
					this.writeMultiFormatSchema((AsyncApi31MultiFormatSchema) union.asMultiFormatSchema(), jsonValue);
					JsonUtil.setObjectProperty(json, "headers", jsonValue);
				}
				if (union.isSchema()) {
					ObjectNode jsonValue = JsonUtil.objectNode();
					this.writeSchema((AsyncApi31Schema) union.asSchema(), jsonValue);
					JsonUtil.setObjectProperty(json, "headers", jsonValue);
				}
			}
		}
		{
			MultiFormatSchemaSchemaUnion union = node.getPayload();
			if (union != null) {
				if (union.isMultiFormatSchema()) {
					ObjectNode jsonValue = JsonUtil.objectNode();
					this.writeMultiFormatSchema((AsyncApi31MultiFormatSchema) union.asMultiFormatSchema(), jsonValue);
					JsonUtil.setObjectProperty(json, "payload", jsonValue);
				}
				if (union.isSchema()) {
					ObjectNode jsonValue = JsonUtil.objectNode();
					this.writeSchema((AsyncApi31Schema) union.asSchema(), jsonValue);
					JsonUtil.setObjectProperty(json, "payload", jsonValue);
				}
			}
		}
		{
			if (node.getCorrelationId() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeCorrelationID((AsyncApi31CorrelationID) node.getCorrelationId(), object);
				JsonUtil.setObjectProperty(json, "correlationId", object);
			}
		}
		JsonUtil.setStringProperty(json, "contentType", node.getContentType());
		JsonUtil.setStringProperty(json, "name", node.getName());
		JsonUtil.setStringProperty(json, "title", node.getTitle());
		JsonUtil.setStringProperty(json, "summary", node.getSummary());
		JsonUtil.setStringProperty(json, "description", node.getDescription());
		{
			List<? extends Tag> models = node.getTags();
			if (models != null && !models.isEmpty()) {
				ArrayNode array = JsonUtil.arrayNode();
				models.forEach(model -> {
					ObjectNode object = JsonUtil.objectNode();
					this.writeTag((AsyncApi31Tag) model, object);
					JsonUtil.addToArray(array, object);
				});
				JsonUtil.setAnyProperty(json, "tags", array);
			}
		}
		{
			if (node.getExternalDocs() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeExternalDocumentation((AsyncApi31ExternalDocumentation) node.getExternalDocs(), object);
				JsonUtil.setObjectProperty(json, "externalDocs", object);
			}
		}
		{
			if (node.getBindings() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeMessageBindings((AsyncApi31MessageBindings) node.getBindings(), object);
				JsonUtil.setObjectProperty(json, "bindings", object);
			}
		}
		{
			List<? extends AsyncApiMessageExample> models = node.getExamples();
			if (models != null && !models.isEmpty()) {
				ArrayNode array = JsonUtil.arrayNode();
				models.forEach(model -> {
					ObjectNode object = JsonUtil.objectNode();
					this.writeMessageExample((AsyncApi31MessageExample) model, object);
					JsonUtil.addToArray(array, object);
				});
				JsonUtil.setAnyProperty(json, "examples", array);
			}
		}
		{
			List<? extends AsyncApiMessageTrait> models = node.getTraits();
			if (models != null && !models.isEmpty()) {
				ArrayNode array = JsonUtil.arrayNode();
				models.forEach(model -> {
					ObjectNode object = JsonUtil.objectNode();
					this.writeMessageTrait((AsyncApi31MessageTrait) model, object);
					JsonUtil.addToArray(array, object);
				});
				JsonUtil.setAnyProperty(json, "traits", array);
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

	public void writeMessageTrait(AsyncApi31MessageTrait node, ObjectNode json) {
		if (node == null) {
			return;
		}
		JsonUtil.setStringProperty(json, "$ref", node.get$ref());
		{
			MultiFormatSchemaSchemaUnion union = node.getHeaders();
			if (union != null) {
				if (union.isMultiFormatSchema()) {
					ObjectNode jsonValue = JsonUtil.objectNode();
					this.writeMultiFormatSchema((AsyncApi31MultiFormatSchema) union.asMultiFormatSchema(), jsonValue);
					JsonUtil.setObjectProperty(json, "headers", jsonValue);
				}
				if (union.isSchema()) {
					ObjectNode jsonValue = JsonUtil.objectNode();
					this.writeSchema((AsyncApi31Schema) union.asSchema(), jsonValue);
					JsonUtil.setObjectProperty(json, "headers", jsonValue);
				}
			}
		}
		{
			if (node.getCorrelationId() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeCorrelationID((AsyncApi31CorrelationID) node.getCorrelationId(), object);
				JsonUtil.setObjectProperty(json, "correlationId", object);
			}
		}
		JsonUtil.setStringProperty(json, "contentType", node.getContentType());
		JsonUtil.setStringProperty(json, "name", node.getName());
		JsonUtil.setStringProperty(json, "title", node.getTitle());
		JsonUtil.setStringProperty(json, "summary", node.getSummary());
		JsonUtil.setStringProperty(json, "description", node.getDescription());
		{
			List<? extends Tag> models = node.getTags();
			if (models != null && !models.isEmpty()) {
				ArrayNode array = JsonUtil.arrayNode();
				models.forEach(model -> {
					ObjectNode object = JsonUtil.objectNode();
					this.writeTag((AsyncApi31Tag) model, object);
					JsonUtil.addToArray(array, object);
				});
				JsonUtil.setAnyProperty(json, "tags", array);
			}
		}
		{
			if (node.getExternalDocs() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeExternalDocumentation((AsyncApi31ExternalDocumentation) node.getExternalDocs(), object);
				JsonUtil.setObjectProperty(json, "externalDocs", object);
			}
		}
		{
			if (node.getBindings() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeMessageBindings((AsyncApi31MessageBindings) node.getBindings(), object);
				JsonUtil.setObjectProperty(json, "bindings", object);
			}
		}
		{
			List<? extends AsyncApiMessageExample> models = node.getExamples();
			if (models != null && !models.isEmpty()) {
				ArrayNode array = JsonUtil.arrayNode();
				models.forEach(model -> {
					ObjectNode object = JsonUtil.objectNode();
					this.writeMessageExample((AsyncApi31MessageExample) model, object);
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

	public void writeMessageExample(AsyncApi31MessageExample node, ObjectNode json) {
		if (node == null) {
			return;
		}
		JsonUtil.setAnyMapProperty(json, "headers", node.getHeaders());
		JsonUtil.setAnyProperty(json, "payload", node.getPayload());
		JsonUtil.setStringProperty(json, "name", node.getName());
		JsonUtil.setStringProperty(json, "summary", node.getSummary());
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

	public void writeTag(AsyncApi31Tag node, ObjectNode json) {
		if (node == null) {
			return;
		}
		JsonUtil.setStringProperty(json, "$ref", node.get$ref());
		JsonUtil.setStringProperty(json, "name", node.getName());
		JsonUtil.setStringProperty(json, "description", node.getDescription());
		{
			if (node.getExternalDocs() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeExternalDocumentation((AsyncApi31ExternalDocumentation) node.getExternalDocs(), object);
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

	public void writeExternalDocumentation(AsyncApi31ExternalDocumentation node, ObjectNode json) {
		if (node == null) {
			return;
		}
		JsonUtil.setStringProperty(json, "$ref", node.get$ref());
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

	public void writeReference(AsyncApi31Reference node, ObjectNode json) {
		if (node == null) {
			return;
		}
		JsonUtil.setStringProperty(json, "$ref", node.get$ref());
		WriterUtil.writeExtraProperties(node, json);
	}

	public void writeComponents(AsyncApi31Components node, ObjectNode json) {
		if (node == null) {
			return;
		}
		{
			Map<String, MultiFormatSchemaSchemaUnion> unionMap = node.getSchemas();
			if (unionMap != null && !unionMap.isEmpty()) {
				ObjectNode mapObject = JsonUtil.objectNode();
				unionMap.keySet().forEach(key -> {
					MultiFormatSchemaSchemaUnion union = unionMap.get(key);
					if (union.isMultiFormatSchema()) {
						ObjectNode object = JsonUtil.objectNode();
						this.writeMultiFormatSchema((AsyncApi31MultiFormatSchema) union.asMultiFormatSchema(), object);
						JsonUtil.setObjectProperty(mapObject, key, object);
					}
					if (union.isSchema()) {
						ObjectNode object = JsonUtil.objectNode();
						this.writeSchema((AsyncApi31Schema) union.asSchema(), object);
						JsonUtil.setObjectProperty(mapObject, key, object);
					}
				});
				JsonUtil.setObjectProperty(json, "schemas", mapObject);
			}
		}
		{
			Map<String, ? extends Server> models = node.getServers();
			if (models != null && !models.isEmpty()) {
				ObjectNode object = JsonUtil.objectNode();
				models.keySet().forEach(jsonName -> {
					ObjectNode jsonValue = JsonUtil.objectNode();
					this.writeServer((AsyncApi31Server) models.get(jsonName), jsonValue);
					JsonUtil.setObjectProperty(object, jsonName, jsonValue);
				});
				JsonUtil.setObjectProperty(json, "servers", object);
			}
		}
		{
			Map<String, ? extends AsyncApiChannel> models = node.getChannels();
			if (models != null && !models.isEmpty()) {
				ObjectNode object = JsonUtil.objectNode();
				models.keySet().forEach(jsonName -> {
					ObjectNode jsonValue = JsonUtil.objectNode();
					this.writeChannel((AsyncApi31Channel) models.get(jsonName), jsonValue);
					JsonUtil.setObjectProperty(object, jsonName, jsonValue);
				});
				JsonUtil.setObjectProperty(json, "channels", object);
			}
		}
		{
			Map<String, ? extends Operation> models = node.getOperations();
			if (models != null && !models.isEmpty()) {
				ObjectNode object = JsonUtil.objectNode();
				models.keySet().forEach(jsonName -> {
					ObjectNode jsonValue = JsonUtil.objectNode();
					this.writeOperation((AsyncApi31Operation) models.get(jsonName), jsonValue);
					JsonUtil.setObjectProperty(object, jsonName, jsonValue);
				});
				JsonUtil.setObjectProperty(json, "operations", object);
			}
		}
		{
			Map<String, ? extends AsyncApiMessage> models = node.getMessages();
			if (models != null && !models.isEmpty()) {
				ObjectNode object = JsonUtil.objectNode();
				models.keySet().forEach(jsonName -> {
					ObjectNode jsonValue = JsonUtil.objectNode();
					this.writeMessage((AsyncApi31Message) models.get(jsonName), jsonValue);
					JsonUtil.setObjectProperty(object, jsonName, jsonValue);
				});
				JsonUtil.setObjectProperty(json, "messages", object);
			}
		}
		{
			Map<String, ? extends SecurityScheme> models = node.getSecuritySchemes();
			if (models != null && !models.isEmpty()) {
				ObjectNode object = JsonUtil.objectNode();
				models.keySet().forEach(jsonName -> {
					ObjectNode jsonValue = JsonUtil.objectNode();
					this.writeSecurityScheme((AsyncApi31SecurityScheme) models.get(jsonName), jsonValue);
					JsonUtil.setObjectProperty(object, jsonName, jsonValue);
				});
				JsonUtil.setObjectProperty(json, "securitySchemes", object);
			}
		}
		{
			Map<String, ? extends ServerVariable> models = node.getServerVariables();
			if (models != null && !models.isEmpty()) {
				ObjectNode object = JsonUtil.objectNode();
				models.keySet().forEach(jsonName -> {
					ObjectNode jsonValue = JsonUtil.objectNode();
					this.writeServerVariable((AsyncApi31ServerVariable) models.get(jsonName), jsonValue);
					JsonUtil.setObjectProperty(object, jsonName, jsonValue);
				});
				JsonUtil.setObjectProperty(json, "serverVariables", object);
			}
		}
		{
			Map<String, ? extends Parameter> models = node.getParameters();
			if (models != null && !models.isEmpty()) {
				ObjectNode object = JsonUtil.objectNode();
				models.keySet().forEach(jsonName -> {
					ObjectNode jsonValue = JsonUtil.objectNode();
					this.writeParameter((AsyncApi31Parameter) models.get(jsonName), jsonValue);
					JsonUtil.setObjectProperty(object, jsonName, jsonValue);
				});
				JsonUtil.setObjectProperty(json, "parameters", object);
			}
		}
		{
			Map<String, ? extends AsyncApiCorrelationID> models = node.getCorrelationIds();
			if (models != null && !models.isEmpty()) {
				ObjectNode object = JsonUtil.objectNode();
				models.keySet().forEach(jsonName -> {
					ObjectNode jsonValue = JsonUtil.objectNode();
					this.writeCorrelationID((AsyncApi31CorrelationID) models.get(jsonName), jsonValue);
					JsonUtil.setObjectProperty(object, jsonName, jsonValue);
				});
				JsonUtil.setObjectProperty(json, "correlationIds", object);
			}
		}
		{
			Map<String, ? extends AsyncApiOperationReply> models = node.getReplies();
			if (models != null && !models.isEmpty()) {
				ObjectNode object = JsonUtil.objectNode();
				models.keySet().forEach(jsonName -> {
					ObjectNode jsonValue = JsonUtil.objectNode();
					this.writeOperationReply((AsyncApi31OperationReply) models.get(jsonName), jsonValue);
					JsonUtil.setObjectProperty(object, jsonName, jsonValue);
				});
				JsonUtil.setObjectProperty(json, "replies", object);
			}
		}
		{
			Map<String, ? extends AsyncApiOperationReplyAddress> models = node.getReplyAddresses();
			if (models != null && !models.isEmpty()) {
				ObjectNode object = JsonUtil.objectNode();
				models.keySet().forEach(jsonName -> {
					ObjectNode jsonValue = JsonUtil.objectNode();
					this.writeOperationReplyAddress((AsyncApi31OperationReplyAddress) models.get(jsonName), jsonValue);
					JsonUtil.setObjectProperty(object, jsonName, jsonValue);
				});
				JsonUtil.setObjectProperty(json, "replyAddresses", object);
			}
		}
		{
			Map<String, ? extends ExternalDocumentation> models = node.getExternalDocs();
			if (models != null && !models.isEmpty()) {
				ObjectNode object = JsonUtil.objectNode();
				models.keySet().forEach(jsonName -> {
					ObjectNode jsonValue = JsonUtil.objectNode();
					this.writeExternalDocumentation((AsyncApi31ExternalDocumentation) models.get(jsonName), jsonValue);
					JsonUtil.setObjectProperty(object, jsonName, jsonValue);
				});
				JsonUtil.setObjectProperty(json, "externalDocs", object);
			}
		}
		{
			Map<String, ? extends Tag> models = node.getTags();
			if (models != null && !models.isEmpty()) {
				ObjectNode object = JsonUtil.objectNode();
				models.keySet().forEach(jsonName -> {
					ObjectNode jsonValue = JsonUtil.objectNode();
					this.writeTag((AsyncApi31Tag) models.get(jsonName), jsonValue);
					JsonUtil.setObjectProperty(object, jsonName, jsonValue);
				});
				JsonUtil.setObjectProperty(json, "tags", object);
			}
		}
		{
			Map<String, ? extends AsyncApiOperationTrait> models = node.getOperationTraits();
			if (models != null && !models.isEmpty()) {
				ObjectNode object = JsonUtil.objectNode();
				models.keySet().forEach(jsonName -> {
					ObjectNode jsonValue = JsonUtil.objectNode();
					this.writeOperationTrait((AsyncApi31OperationTrait) models.get(jsonName), jsonValue);
					JsonUtil.setObjectProperty(object, jsonName, jsonValue);
				});
				JsonUtil.setObjectProperty(json, "operationTraits", object);
			}
		}
		{
			Map<String, ? extends AsyncApiMessageTrait> models = node.getMessageTraits();
			if (models != null && !models.isEmpty()) {
				ObjectNode object = JsonUtil.objectNode();
				models.keySet().forEach(jsonName -> {
					ObjectNode jsonValue = JsonUtil.objectNode();
					this.writeMessageTrait((AsyncApi31MessageTrait) models.get(jsonName), jsonValue);
					JsonUtil.setObjectProperty(object, jsonName, jsonValue);
				});
				JsonUtil.setObjectProperty(json, "messageTraits", object);
			}
		}
		{
			Map<String, ? extends AsyncApiServerBindings> models = node.getServerBindings();
			if (models != null && !models.isEmpty()) {
				ObjectNode object = JsonUtil.objectNode();
				models.keySet().forEach(jsonName -> {
					ObjectNode jsonValue = JsonUtil.objectNode();
					this.writeServerBindings((AsyncApi31ServerBindings) models.get(jsonName), jsonValue);
					JsonUtil.setObjectProperty(object, jsonName, jsonValue);
				});
				JsonUtil.setObjectProperty(json, "serverBindings", object);
			}
		}
		{
			Map<String, ? extends AsyncApiChannelBindings> models = node.getChannelBindings();
			if (models != null && !models.isEmpty()) {
				ObjectNode object = JsonUtil.objectNode();
				models.keySet().forEach(jsonName -> {
					ObjectNode jsonValue = JsonUtil.objectNode();
					this.writeChannelBindings((AsyncApi31ChannelBindings) models.get(jsonName), jsonValue);
					JsonUtil.setObjectProperty(object, jsonName, jsonValue);
				});
				JsonUtil.setObjectProperty(json, "channelBindings", object);
			}
		}
		{
			Map<String, ? extends AsyncApiOperationBindings> models = node.getOperationBindings();
			if (models != null && !models.isEmpty()) {
				ObjectNode object = JsonUtil.objectNode();
				models.keySet().forEach(jsonName -> {
					ObjectNode jsonValue = JsonUtil.objectNode();
					this.writeOperationBindings((AsyncApi31OperationBindings) models.get(jsonName), jsonValue);
					JsonUtil.setObjectProperty(object, jsonName, jsonValue);
				});
				JsonUtil.setObjectProperty(json, "operationBindings", object);
			}
		}
		{
			Map<String, ? extends AsyncApiMessageBindings> models = node.getMessageBindings();
			if (models != null && !models.isEmpty()) {
				ObjectNode object = JsonUtil.objectNode();
				models.keySet().forEach(jsonName -> {
					ObjectNode jsonValue = JsonUtil.objectNode();
					this.writeMessageBindings((AsyncApi31MessageBindings) models.get(jsonName), jsonValue);
					JsonUtil.setObjectProperty(object, jsonName, jsonValue);
				});
				JsonUtil.setObjectProperty(json, "messageBindings", object);
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

	public void writeMultiFormatSchema(AsyncApi31MultiFormatSchema node, ObjectNode json) {
		if (node == null) {
			return;
		}
		JsonUtil.setStringProperty(json, "$ref", node.get$ref());
		JsonUtil.setStringProperty(json, "schemaFormat", node.getSchemaFormat());
		{
			AnySchemaUnion union = node.getSchema();
			if (union != null) {
				if (union.isAny()) {
					JsonUtil.setAnyProperty(json, "schema", union.asAny());
				}
				if (union.isSchema()) {
					ObjectNode jsonValue = JsonUtil.objectNode();
					this.writeSchema((AsyncApi31Schema) union.asSchema(), jsonValue);
					JsonUtil.setObjectProperty(json, "schema", jsonValue);
				}
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

	public void writeSchema(AsyncApi31Schema node, ObjectNode json) {
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
				this.writeSchema((AsyncApi31Schema) node.getIf(), object);
				JsonUtil.setObjectProperty(json, "if", object);
			}
		}
		{
			if (node.getThen() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeSchema((AsyncApi31Schema) node.getThen(), object);
				JsonUtil.setObjectProperty(json, "then", object);
			}
		}
		{
			if (node.getElse() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeSchema((AsyncApi31Schema) node.getElse(), object);
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
					this.writeSchema((AsyncApi31Schema) models.get(jsonName), jsonValue);
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
					this.writeSchema((AsyncApi31Schema) union.asSchema(), jsonValue);
					JsonUtil.setObjectProperty(json, "additionalProperties", jsonValue);
				}
			}
		}
		{
			if (node.getAdditionalItems() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeSchema((AsyncApi31Schema) node.getAdditionalItems(), object);
				JsonUtil.setObjectProperty(json, "additionalItems", object);
			}
		}
		{
			SchemaSchemaListUnion union = node.getItems();
			if (union != null) {
				if (union.isSchema()) {
					ObjectNode jsonValue = JsonUtil.objectNode();
					this.writeSchema((AsyncApi31Schema) union.asSchema(), jsonValue);
					JsonUtil.setObjectProperty(json, "items", jsonValue);
				}
				if (union.isSchemaList()) {
					List<? extends Schema> models = union.asSchemaList();
					ArrayNode array = JsonUtil.arrayNode();
					models.forEach(model -> {
						ObjectNode object = JsonUtil.objectNode();
						this.writeSchema((AsyncApi31Schema) model, object);
						JsonUtil.addToArray(array, object);
					});
					JsonUtil.setAnyProperty(json, "items", array);
				}
			}
		}
		{
			if (node.getPropertyNames() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeSchema((AsyncApi31Schema) node.getPropertyNames(), object);
				JsonUtil.setObjectProperty(json, "propertyNames", object);
			}
		}
		{
			if (node.getContains() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeSchema((AsyncApi31Schema) node.getContains(), object);
				JsonUtil.setObjectProperty(json, "contains", object);
			}
		}
		{
			List<? extends Schema> models = node.getAllOf();
			if (models != null && !models.isEmpty()) {
				ArrayNode array = JsonUtil.arrayNode();
				models.forEach(model -> {
					ObjectNode object = JsonUtil.objectNode();
					this.writeSchema((AsyncApi31Schema) model, object);
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
					this.writeSchema((AsyncApi31Schema) model, object);
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
					this.writeSchema((AsyncApi31Schema) model, object);
					JsonUtil.addToArray(array, object);
				});
				JsonUtil.setAnyProperty(json, "anyOf", array);
			}
		}
		{
			if (node.getNot() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeSchema((AsyncApi31Schema) node.getNot(), object);
				JsonUtil.setObjectProperty(json, "not", object);
			}
		}
		JsonUtil.setStringProperty(json, "description", node.getDescription());
		JsonUtil.setStringProperty(json, "format", node.getFormat());
		JsonUtil.setAnyProperty(json, "default", node.getDefault());
		JsonUtil.setStringProperty(json, "discriminator", node.getDiscriminator());
		{
			if (node.getExternalDocs() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeExternalDocumentation((AsyncApi31ExternalDocumentation) node.getExternalDocs(), object);
				JsonUtil.setObjectProperty(json, "externalDocs", object);
			}
		}
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

	public void writeSecurityScheme(AsyncApi31SecurityScheme node, ObjectNode json) {
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
				this.writeOAuthFlows((AsyncApi31OAuthFlows) node.getFlows(), object);
				JsonUtil.setObjectProperty(json, "flows", object);
			}
		}
		JsonUtil.setStringProperty(json, "openIdConnectUrl", node.getOpenIdConnectUrl());
		JsonUtil.setStringArrayProperty(json, "scopes", node.getScopes());
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

	public void writeOAuthFlows(AsyncApi31OAuthFlows node, ObjectNode json) {
		if (node == null) {
			return;
		}
		{
			if (node.getImplicit() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeOAuthFlow((AsyncApi31OAuthFlow) node.getImplicit(), object);
				JsonUtil.setObjectProperty(json, "implicit", object);
			}
		}
		{
			if (node.getPassword() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeOAuthFlow((AsyncApi31OAuthFlow) node.getPassword(), object);
				JsonUtil.setObjectProperty(json, "password", object);
			}
		}
		{
			if (node.getClientCredentials() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeOAuthFlow((AsyncApi31OAuthFlow) node.getClientCredentials(), object);
				JsonUtil.setObjectProperty(json, "clientCredentials", object);
			}
		}
		{
			if (node.getAuthorizationCode() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeOAuthFlow((AsyncApi31OAuthFlow) node.getAuthorizationCode(), object);
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

	public void writeOAuthFlow(AsyncApi31OAuthFlow node, ObjectNode json) {
		if (node == null) {
			return;
		}
		JsonUtil.setStringProperty(json, "authorizationUrl", node.getAuthorizationUrl());
		JsonUtil.setStringProperty(json, "tokenUrl", node.getTokenUrl());
		JsonUtil.setStringProperty(json, "refreshUrl", node.getRefreshUrl());
		JsonUtil.setStringMapProperty(json, "availableScopes", node.getAvailableScopes());
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

	public void writeCorrelationID(AsyncApi31CorrelationID node, ObjectNode json) {
		if (node == null) {
			return;
		}
		JsonUtil.setStringProperty(json, "$ref", node.get$ref());
		JsonUtil.setStringProperty(json, "description", node.getDescription());
		JsonUtil.setStringProperty(json, "location", node.getLocation());
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

	public void writeBinding(AsyncApi31Binding node, ObjectNode json) {
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