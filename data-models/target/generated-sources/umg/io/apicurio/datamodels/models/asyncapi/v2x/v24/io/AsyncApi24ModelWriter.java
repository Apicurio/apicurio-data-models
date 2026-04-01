package io.apicurio.datamodels.models.asyncapi.v2x.v24.io;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.apicurio.datamodels.models.Parameter;
import io.apicurio.datamodels.models.RootNode;
import io.apicurio.datamodels.models.Schema;
import io.apicurio.datamodels.models.SecurityRequirement;
import io.apicurio.datamodels.models.SecurityScheme;
import io.apicurio.datamodels.models.Server;
import io.apicurio.datamodels.models.ServerVariable;
import io.apicurio.datamodels.models.Tag;
import io.apicurio.datamodels.models.asyncapi.AsyncApiChannelBindings;
import io.apicurio.datamodels.models.asyncapi.AsyncApiChannelItem;
import io.apicurio.datamodels.models.asyncapi.AsyncApiCorrelationID;
import io.apicurio.datamodels.models.asyncapi.AsyncApiMessage;
import io.apicurio.datamodels.models.asyncapi.AsyncApiMessageBindings;
import io.apicurio.datamodels.models.asyncapi.AsyncApiMessageTrait;
import io.apicurio.datamodels.models.asyncapi.AsyncApiOperationBindings;
import io.apicurio.datamodels.models.asyncapi.AsyncApiOperationTrait;
import io.apicurio.datamodels.models.asyncapi.AsyncApiServerBindings;
import io.apicurio.datamodels.models.asyncapi.v2x.v24.AsyncApi24Binding;
import io.apicurio.datamodels.models.asyncapi.v2x.v24.AsyncApi24ChannelBindings;
import io.apicurio.datamodels.models.asyncapi.v2x.v24.AsyncApi24ChannelItem;
import io.apicurio.datamodels.models.asyncapi.v2x.v24.AsyncApi24Channels;
import io.apicurio.datamodels.models.asyncapi.v2x.v24.AsyncApi24Components;
import io.apicurio.datamodels.models.asyncapi.v2x.v24.AsyncApi24Contact;
import io.apicurio.datamodels.models.asyncapi.v2x.v24.AsyncApi24CorrelationID;
import io.apicurio.datamodels.models.asyncapi.v2x.v24.AsyncApi24Document;
import io.apicurio.datamodels.models.asyncapi.v2x.v24.AsyncApi24ExternalDocumentation;
import io.apicurio.datamodels.models.asyncapi.v2x.v24.AsyncApi24Info;
import io.apicurio.datamodels.models.asyncapi.v2x.v24.AsyncApi24License;
import io.apicurio.datamodels.models.asyncapi.v2x.v24.AsyncApi24Message;
import io.apicurio.datamodels.models.asyncapi.v2x.v24.AsyncApi24MessageBindings;
import io.apicurio.datamodels.models.asyncapi.v2x.v24.AsyncApi24MessageExample;
import io.apicurio.datamodels.models.asyncapi.v2x.v24.AsyncApi24MessageTrait;
import io.apicurio.datamodels.models.asyncapi.v2x.v24.AsyncApi24OAuthFlow;
import io.apicurio.datamodels.models.asyncapi.v2x.v24.AsyncApi24OAuthFlows;
import io.apicurio.datamodels.models.asyncapi.v2x.v24.AsyncApi24OneOfMessages;
import io.apicurio.datamodels.models.asyncapi.v2x.v24.AsyncApi24Operation;
import io.apicurio.datamodels.models.asyncapi.v2x.v24.AsyncApi24OperationBindings;
import io.apicurio.datamodels.models.asyncapi.v2x.v24.AsyncApi24OperationTrait;
import io.apicurio.datamodels.models.asyncapi.v2x.v24.AsyncApi24Parameter;
import io.apicurio.datamodels.models.asyncapi.v2x.v24.AsyncApi24Parameters;
import io.apicurio.datamodels.models.asyncapi.v2x.v24.AsyncApi24Schema;
import io.apicurio.datamodels.models.asyncapi.v2x.v24.AsyncApi24SecurityRequirement;
import io.apicurio.datamodels.models.asyncapi.v2x.v24.AsyncApi24SecurityScheme;
import io.apicurio.datamodels.models.asyncapi.v2x.v24.AsyncApi24Server;
import io.apicurio.datamodels.models.asyncapi.v2x.v24.AsyncApi24ServerBindings;
import io.apicurio.datamodels.models.asyncapi.v2x.v24.AsyncApi24ServerVariable;
import io.apicurio.datamodels.models.asyncapi.v2x.v24.AsyncApi24Servers;
import io.apicurio.datamodels.models.asyncapi.v2x.v24.AsyncApi24Tag;
import io.apicurio.datamodels.models.io.ModelWriter;
import io.apicurio.datamodels.models.union.BooleanSchemaUnion;
import io.apicurio.datamodels.models.union.SchemaSchemaListUnion;
import io.apicurio.datamodels.models.util.JsonUtil;
import io.apicurio.datamodels.models.util.WriterUtil;
import java.util.List;
import java.util.Map;

public class AsyncApi24ModelWriter implements ModelWriter {

	public void writeDocument(AsyncApi24Document node, ObjectNode json) {
		if (node == null) {
			return;
		}
		JsonUtil.setStringProperty(json, "asyncapi", node.getAsyncapi());
		JsonUtil.setStringProperty(json, "id", node.getId());
		{
			if (node.getInfo() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeInfo((AsyncApi24Info) node.getInfo(), object);
				JsonUtil.setObjectProperty(json, "info", object);
			}
		}
		{
			if (node.getServers() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeServers((AsyncApi24Servers) node.getServers(), object);
				JsonUtil.setObjectProperty(json, "servers", object);
			}
		}
		JsonUtil.setStringProperty(json, "defaultContentType", node.getDefaultContentType());
		{
			if (node.getChannels() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeChannels((AsyncApi24Channels) node.getChannels(), object);
				JsonUtil.setObjectProperty(json, "channels", object);
			}
		}
		{
			if (node.getComponents() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeComponents((AsyncApi24Components) node.getComponents(), object);
				JsonUtil.setObjectProperty(json, "components", object);
			}
		}
		{
			List<? extends Tag> models = node.getTags();
			if (models != null && !models.isEmpty()) {
				ArrayNode array = JsonUtil.arrayNode();
				models.forEach(model -> {
					ObjectNode object = JsonUtil.objectNode();
					this.writeTag((AsyncApi24Tag) model, object);
					JsonUtil.addToArray(array, object);
				});
				JsonUtil.setAnyProperty(json, "tags", array);
			}
		}
		{
			if (node.getExternalDocs() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeExternalDocumentation((AsyncApi24ExternalDocumentation) node.getExternalDocs(), object);
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
		this.writeDocument((AsyncApi24Document) node, json);
		return json;
	}

	public void writeInfo(AsyncApi24Info node, ObjectNode json) {
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
				this.writeContact((AsyncApi24Contact) node.getContact(), object);
				JsonUtil.setObjectProperty(json, "contact", object);
			}
		}
		{
			if (node.getLicense() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeLicense((AsyncApi24License) node.getLicense(), object);
				JsonUtil.setObjectProperty(json, "license", object);
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

	public void writeContact(AsyncApi24Contact node, ObjectNode json) {
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

	public void writeLicense(AsyncApi24License node, ObjectNode json) {
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

	public void writeServers(AsyncApi24Servers node, ObjectNode json) {
		if (node == null) {
			return;
		}
		{
			List<String> propertyNames = node.getItemNames();
			propertyNames.forEach(propertyName -> {
				ObjectNode object = JsonUtil.objectNode();
				this.writeServer((AsyncApi24Server) node.getItem(propertyName), object);
				JsonUtil.setObjectProperty(json, propertyName, object);
			});
		}
		WriterUtil.writeExtraProperties(node, json);
	}

	public void writeServer(AsyncApi24Server node, ObjectNode json) {
		if (node == null) {
			return;
		}
		JsonUtil.setStringProperty(json, "$ref", node.get$ref());
		JsonUtil.setStringProperty(json, "url", node.getUrl());
		JsonUtil.setStringProperty(json, "protocol", node.getProtocol());
		JsonUtil.setStringProperty(json, "protocolVersion", node.getProtocolVersion());
		JsonUtil.setStringProperty(json, "description", node.getDescription());
		{
			Map<String, ? extends ServerVariable> models = node.getVariables();
			if (models != null && !models.isEmpty()) {
				ObjectNode object = JsonUtil.objectNode();
				models.keySet().forEach(jsonName -> {
					ObjectNode jsonValue = JsonUtil.objectNode();
					this.writeServerVariable((AsyncApi24ServerVariable) models.get(jsonName), jsonValue);
					JsonUtil.setObjectProperty(object, jsonName, jsonValue);
				});
				JsonUtil.setObjectProperty(json, "variables", object);
			}
		}
		{
			List<? extends SecurityRequirement> models = node.getSecurity();
			if (models != null && !models.isEmpty()) {
				ArrayNode array = JsonUtil.arrayNode();
				models.forEach(model -> {
					ObjectNode object = JsonUtil.objectNode();
					this.writeSecurityRequirement((AsyncApi24SecurityRequirement) model, object);
					JsonUtil.addToArray(array, object);
				});
				JsonUtil.setAnyProperty(json, "security", array);
			}
		}
		{
			if (node.getBindings() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeServerBindings((AsyncApi24ServerBindings) node.getBindings(), object);
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

	public void writeServerVariable(AsyncApi24ServerVariable node, ObjectNode json) {
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

	public void writeChannels(AsyncApi24Channels node, ObjectNode json) {
		if (node == null) {
			return;
		}
		{
			List<String> propertyNames = node.getItemNames();
			propertyNames.forEach(propertyName -> {
				ObjectNode object = JsonUtil.objectNode();
				this.writeChannelItem((AsyncApi24ChannelItem) node.getItem(propertyName), object);
				JsonUtil.setObjectProperty(json, propertyName, object);
			});
		}
		WriterUtil.writeExtraProperties(node, json);
	}

	public void writeChannelItem(AsyncApi24ChannelItem node, ObjectNode json) {
		if (node == null) {
			return;
		}
		JsonUtil.setStringProperty(json, "$ref", node.get$ref());
		JsonUtil.setStringProperty(json, "description", node.getDescription());
		JsonUtil.setStringArrayProperty(json, "servers", node.getServers());
		{
			if (node.getSubscribe() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeOperation((AsyncApi24Operation) node.getSubscribe(), object);
				JsonUtil.setObjectProperty(json, "subscribe", object);
			}
		}
		{
			if (node.getPublish() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeOperation((AsyncApi24Operation) node.getPublish(), object);
				JsonUtil.setObjectProperty(json, "publish", object);
			}
		}
		{
			if (node.getParameters() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeParameters((AsyncApi24Parameters) node.getParameters(), object);
				JsonUtil.setObjectProperty(json, "parameters", object);
			}
		}
		{
			if (node.getBindings() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeChannelBindings((AsyncApi24ChannelBindings) node.getBindings(), object);
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

	public void writeOperation(AsyncApi24Operation node, ObjectNode json) {
		if (node == null) {
			return;
		}
		JsonUtil.setStringProperty(json, "operationId", node.getOperationId());
		JsonUtil.setStringProperty(json, "summary", node.getSummary());
		JsonUtil.setStringProperty(json, "description", node.getDescription());
		{
			List<? extends SecurityRequirement> models = node.getSecurity();
			if (models != null && !models.isEmpty()) {
				ArrayNode array = JsonUtil.arrayNode();
				models.forEach(model -> {
					ObjectNode object = JsonUtil.objectNode();
					this.writeSecurityRequirement((AsyncApi24SecurityRequirement) model, object);
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
					this.writeTag((AsyncApi24Tag) model, object);
					JsonUtil.addToArray(array, object);
				});
				JsonUtil.setAnyProperty(json, "tags", array);
			}
		}
		{
			if (node.getExternalDocs() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeExternalDocumentation((AsyncApi24ExternalDocumentation) node.getExternalDocs(), object);
				JsonUtil.setObjectProperty(json, "externalDocs", object);
			}
		}
		{
			if (node.getBindings() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeOperationBindings((AsyncApi24OperationBindings) node.getBindings(), object);
				JsonUtil.setObjectProperty(json, "bindings", object);
			}
		}
		{
			List<? extends AsyncApiOperationTrait> models = node.getTraits();
			if (models != null && !models.isEmpty()) {
				ArrayNode array = JsonUtil.arrayNode();
				models.forEach(model -> {
					ObjectNode object = JsonUtil.objectNode();
					this.writeOperationTrait((AsyncApi24OperationTrait) model, object);
					JsonUtil.addToArray(array, object);
				});
				JsonUtil.setAnyProperty(json, "traits", array);
			}
		}
		{
			if (node.getMessage() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeMessage((AsyncApi24Message) node.getMessage(), object);
				JsonUtil.setObjectProperty(json, "message", object);
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

	public void writeOperationTrait(AsyncApi24OperationTrait node, ObjectNode json) {
		if (node == null) {
			return;
		}
		JsonUtil.setStringProperty(json, "$ref", node.get$ref());
		JsonUtil.setStringProperty(json, "operationId", node.getOperationId());
		JsonUtil.setStringProperty(json, "summary", node.getSummary());
		JsonUtil.setStringProperty(json, "description", node.getDescription());
		{
			List<? extends SecurityRequirement> models = node.getSecurity();
			if (models != null && !models.isEmpty()) {
				ArrayNode array = JsonUtil.arrayNode();
				models.forEach(model -> {
					ObjectNode object = JsonUtil.objectNode();
					this.writeSecurityRequirement((AsyncApi24SecurityRequirement) model, object);
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
					this.writeTag((AsyncApi24Tag) model, object);
					JsonUtil.addToArray(array, object);
				});
				JsonUtil.setAnyProperty(json, "tags", array);
			}
		}
		{
			if (node.getExternalDocs() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeExternalDocumentation((AsyncApi24ExternalDocumentation) node.getExternalDocs(), object);
				JsonUtil.setObjectProperty(json, "externalDocs", object);
			}
		}
		{
			if (node.getBindings() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeOperationBindings((AsyncApi24OperationBindings) node.getBindings(), object);
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

	public void writeParameters(AsyncApi24Parameters node, ObjectNode json) {
		if (node == null) {
			return;
		}
		{
			List<String> propertyNames = node.getItemNames();
			propertyNames.forEach(propertyName -> {
				ObjectNode object = JsonUtil.objectNode();
				this.writeParameter((AsyncApi24Parameter) node.getItem(propertyName), object);
				JsonUtil.setObjectProperty(json, propertyName, object);
			});
		}
		WriterUtil.writeExtraProperties(node, json);
	}

	public void writeParameter(AsyncApi24Parameter node, ObjectNode json) {
		if (node == null) {
			return;
		}
		JsonUtil.setStringProperty(json, "$ref", node.get$ref());
		JsonUtil.setStringProperty(json, "description", node.getDescription());
		{
			if (node.getSchema() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeSchema((AsyncApi24Schema) node.getSchema(), object);
				JsonUtil.setObjectProperty(json, "schema", object);
			}
		}
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

	public void writeServerBindings(AsyncApi24ServerBindings node, ObjectNode json) {
		if (node == null) {
			return;
		}
		JsonUtil.setStringProperty(json, "$ref", node.get$ref());
		{
			if (node.getHttp() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi24Binding) node.getHttp(), object);
				JsonUtil.setObjectProperty(json, "http", object);
			}
		}
		{
			if (node.getWs() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi24Binding) node.getWs(), object);
				JsonUtil.setObjectProperty(json, "ws", object);
			}
		}
		{
			if (node.getKafka() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi24Binding) node.getKafka(), object);
				JsonUtil.setObjectProperty(json, "kafka", object);
			}
		}
		{
			if (node.getAnypointmq() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi24Binding) node.getAnypointmq(), object);
				JsonUtil.setObjectProperty(json, "anypointmq", object);
			}
		}
		{
			if (node.getAmqp() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi24Binding) node.getAmqp(), object);
				JsonUtil.setObjectProperty(json, "amqp", object);
			}
		}
		{
			if (node.getAmqp1() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi24Binding) node.getAmqp1(), object);
				JsonUtil.setObjectProperty(json, "amqp1", object);
			}
		}
		{
			if (node.getMqtt() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi24Binding) node.getMqtt(), object);
				JsonUtil.setObjectProperty(json, "mqtt", object);
			}
		}
		{
			if (node.getMqtt5() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi24Binding) node.getMqtt5(), object);
				JsonUtil.setObjectProperty(json, "mqtt5", object);
			}
		}
		{
			if (node.getNats() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi24Binding) node.getNats(), object);
				JsonUtil.setObjectProperty(json, "nats", object);
			}
		}
		{
			if (node.getJms() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi24Binding) node.getJms(), object);
				JsonUtil.setObjectProperty(json, "jms", object);
			}
		}
		{
			if (node.getSns() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi24Binding) node.getSns(), object);
				JsonUtil.setObjectProperty(json, "sns", object);
			}
		}
		{
			if (node.getSolace() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi24Binding) node.getSolace(), object);
				JsonUtil.setObjectProperty(json, "solace", object);
			}
		}
		{
			if (node.getSqs() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi24Binding) node.getSqs(), object);
				JsonUtil.setObjectProperty(json, "sqs", object);
			}
		}
		{
			if (node.getStomp() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi24Binding) node.getStomp(), object);
				JsonUtil.setObjectProperty(json, "stomp", object);
			}
		}
		{
			if (node.getRedis() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi24Binding) node.getRedis(), object);
				JsonUtil.setObjectProperty(json, "redis", object);
			}
		}
		{
			if (node.getMercure() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi24Binding) node.getMercure(), object);
				JsonUtil.setObjectProperty(json, "mercure", object);
			}
		}
		{
			if (node.getIbmmq() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi24Binding) node.getIbmmq(), object);
				JsonUtil.setObjectProperty(json, "ibmmq", object);
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

	public void writeChannelBindings(AsyncApi24ChannelBindings node, ObjectNode json) {
		if (node == null) {
			return;
		}
		JsonUtil.setStringProperty(json, "$ref", node.get$ref());
		{
			if (node.getHttp() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi24Binding) node.getHttp(), object);
				JsonUtil.setObjectProperty(json, "http", object);
			}
		}
		{
			if (node.getWs() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi24Binding) node.getWs(), object);
				JsonUtil.setObjectProperty(json, "ws", object);
			}
		}
		{
			if (node.getKafka() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi24Binding) node.getKafka(), object);
				JsonUtil.setObjectProperty(json, "kafka", object);
			}
		}
		{
			if (node.getAnypointmq() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi24Binding) node.getAnypointmq(), object);
				JsonUtil.setObjectProperty(json, "anypointmq", object);
			}
		}
		{
			if (node.getAmqp() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi24Binding) node.getAmqp(), object);
				JsonUtil.setObjectProperty(json, "amqp", object);
			}
		}
		{
			if (node.getAmqp1() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi24Binding) node.getAmqp1(), object);
				JsonUtil.setObjectProperty(json, "amqp1", object);
			}
		}
		{
			if (node.getMqtt() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi24Binding) node.getMqtt(), object);
				JsonUtil.setObjectProperty(json, "mqtt", object);
			}
		}
		{
			if (node.getMqtt5() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi24Binding) node.getMqtt5(), object);
				JsonUtil.setObjectProperty(json, "mqtt5", object);
			}
		}
		{
			if (node.getNats() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi24Binding) node.getNats(), object);
				JsonUtil.setObjectProperty(json, "nats", object);
			}
		}
		{
			if (node.getJms() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi24Binding) node.getJms(), object);
				JsonUtil.setObjectProperty(json, "jms", object);
			}
		}
		{
			if (node.getSns() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi24Binding) node.getSns(), object);
				JsonUtil.setObjectProperty(json, "sns", object);
			}
		}
		{
			if (node.getSolace() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi24Binding) node.getSolace(), object);
				JsonUtil.setObjectProperty(json, "solace", object);
			}
		}
		{
			if (node.getSqs() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi24Binding) node.getSqs(), object);
				JsonUtil.setObjectProperty(json, "sqs", object);
			}
		}
		{
			if (node.getStomp() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi24Binding) node.getStomp(), object);
				JsonUtil.setObjectProperty(json, "stomp", object);
			}
		}
		{
			if (node.getRedis() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi24Binding) node.getRedis(), object);
				JsonUtil.setObjectProperty(json, "redis", object);
			}
		}
		{
			if (node.getMercure() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi24Binding) node.getMercure(), object);
				JsonUtil.setObjectProperty(json, "mercure", object);
			}
		}
		{
			if (node.getIbmmq() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi24Binding) node.getIbmmq(), object);
				JsonUtil.setObjectProperty(json, "ibmmq", object);
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

	public void writeOperationBindings(AsyncApi24OperationBindings node, ObjectNode json) {
		if (node == null) {
			return;
		}
		JsonUtil.setStringProperty(json, "$ref", node.get$ref());
		{
			if (node.getHttp() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi24Binding) node.getHttp(), object);
				JsonUtil.setObjectProperty(json, "http", object);
			}
		}
		{
			if (node.getWs() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi24Binding) node.getWs(), object);
				JsonUtil.setObjectProperty(json, "ws", object);
			}
		}
		{
			if (node.getKafka() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi24Binding) node.getKafka(), object);
				JsonUtil.setObjectProperty(json, "kafka", object);
			}
		}
		{
			if (node.getAnypointmq() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi24Binding) node.getAnypointmq(), object);
				JsonUtil.setObjectProperty(json, "anypointmq", object);
			}
		}
		{
			if (node.getAmqp() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi24Binding) node.getAmqp(), object);
				JsonUtil.setObjectProperty(json, "amqp", object);
			}
		}
		{
			if (node.getAmqp1() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi24Binding) node.getAmqp1(), object);
				JsonUtil.setObjectProperty(json, "amqp1", object);
			}
		}
		{
			if (node.getMqtt() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi24Binding) node.getMqtt(), object);
				JsonUtil.setObjectProperty(json, "mqtt", object);
			}
		}
		{
			if (node.getMqtt5() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi24Binding) node.getMqtt5(), object);
				JsonUtil.setObjectProperty(json, "mqtt5", object);
			}
		}
		{
			if (node.getNats() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi24Binding) node.getNats(), object);
				JsonUtil.setObjectProperty(json, "nats", object);
			}
		}
		{
			if (node.getJms() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi24Binding) node.getJms(), object);
				JsonUtil.setObjectProperty(json, "jms", object);
			}
		}
		{
			if (node.getSns() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi24Binding) node.getSns(), object);
				JsonUtil.setObjectProperty(json, "sns", object);
			}
		}
		{
			if (node.getSolace() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi24Binding) node.getSolace(), object);
				JsonUtil.setObjectProperty(json, "solace", object);
			}
		}
		{
			if (node.getSqs() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi24Binding) node.getSqs(), object);
				JsonUtil.setObjectProperty(json, "sqs", object);
			}
		}
		{
			if (node.getStomp() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi24Binding) node.getStomp(), object);
				JsonUtil.setObjectProperty(json, "stomp", object);
			}
		}
		{
			if (node.getRedis() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi24Binding) node.getRedis(), object);
				JsonUtil.setObjectProperty(json, "redis", object);
			}
		}
		{
			if (node.getMercure() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi24Binding) node.getMercure(), object);
				JsonUtil.setObjectProperty(json, "mercure", object);
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

	public void writeMessageBindings(AsyncApi24MessageBindings node, ObjectNode json) {
		if (node == null) {
			return;
		}
		JsonUtil.setStringProperty(json, "$ref", node.get$ref());
		{
			if (node.getHttp() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi24Binding) node.getHttp(), object);
				JsonUtil.setObjectProperty(json, "http", object);
			}
		}
		{
			if (node.getWs() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi24Binding) node.getWs(), object);
				JsonUtil.setObjectProperty(json, "ws", object);
			}
		}
		{
			if (node.getKafka() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi24Binding) node.getKafka(), object);
				JsonUtil.setObjectProperty(json, "kafka", object);
			}
		}
		{
			if (node.getAnypointmq() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi24Binding) node.getAnypointmq(), object);
				JsonUtil.setObjectProperty(json, "anypointmq", object);
			}
		}
		{
			if (node.getAmqp() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi24Binding) node.getAmqp(), object);
				JsonUtil.setObjectProperty(json, "amqp", object);
			}
		}
		{
			if (node.getAmqp1() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi24Binding) node.getAmqp1(), object);
				JsonUtil.setObjectProperty(json, "amqp1", object);
			}
		}
		{
			if (node.getMqtt() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi24Binding) node.getMqtt(), object);
				JsonUtil.setObjectProperty(json, "mqtt", object);
			}
		}
		{
			if (node.getMqtt5() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi24Binding) node.getMqtt5(), object);
				JsonUtil.setObjectProperty(json, "mqtt5", object);
			}
		}
		{
			if (node.getNats() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi24Binding) node.getNats(), object);
				JsonUtil.setObjectProperty(json, "nats", object);
			}
		}
		{
			if (node.getJms() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi24Binding) node.getJms(), object);
				JsonUtil.setObjectProperty(json, "jms", object);
			}
		}
		{
			if (node.getSns() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi24Binding) node.getSns(), object);
				JsonUtil.setObjectProperty(json, "sns", object);
			}
		}
		{
			if (node.getSolace() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi24Binding) node.getSolace(), object);
				JsonUtil.setObjectProperty(json, "solace", object);
			}
		}
		{
			if (node.getSqs() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi24Binding) node.getSqs(), object);
				JsonUtil.setObjectProperty(json, "sqs", object);
			}
		}
		{
			if (node.getStomp() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi24Binding) node.getStomp(), object);
				JsonUtil.setObjectProperty(json, "stomp", object);
			}
		}
		{
			if (node.getRedis() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi24Binding) node.getRedis(), object);
				JsonUtil.setObjectProperty(json, "redis", object);
			}
		}
		{
			if (node.getMercure() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi24Binding) node.getMercure(), object);
				JsonUtil.setObjectProperty(json, "mercure", object);
			}
		}
		{
			if (node.getIbmmq() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeBinding((AsyncApi24Binding) node.getIbmmq(), object);
				JsonUtil.setObjectProperty(json, "ibmmq", object);
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

	public void writeOneOfMessages(AsyncApi24OneOfMessages node, ObjectNode json) {
		if (node == null) {
			return;
		}
		{
			List<? extends AsyncApiMessage> models = node.getOneOf();
			if (models != null && !models.isEmpty()) {
				ArrayNode array = JsonUtil.arrayNode();
				models.forEach(model -> {
					ObjectNode object = JsonUtil.objectNode();
					this.writeMessage((AsyncApi24Message) model, object);
					JsonUtil.addToArray(array, object);
				});
				JsonUtil.setAnyProperty(json, "oneOf", array);
			}
		}
		WriterUtil.writeExtraProperties(node, json);
	}

	public void writeMessage(AsyncApi24Message node, ObjectNode json) {
		if (node == null) {
			return;
		}
		JsonUtil.setStringProperty(json, "$ref", node.get$ref());
		{
			List<? extends AsyncApiMessage> models = node.getOneOf();
			if (models != null && !models.isEmpty()) {
				ArrayNode array = JsonUtil.arrayNode();
				models.forEach(model -> {
					ObjectNode object = JsonUtil.objectNode();
					this.writeMessage((AsyncApi24Message) model, object);
					JsonUtil.addToArray(array, object);
				});
				JsonUtil.setAnyProperty(json, "oneOf", array);
			}
		}
		JsonUtil.setStringProperty(json, "messageId", node.getMessageId());
		{
			if (node.getHeaders() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeSchema((AsyncApi24Schema) node.getHeaders(), object);
				JsonUtil.setObjectProperty(json, "headers", object);
			}
		}
		JsonUtil.setAnyProperty(json, "payload", node.getPayload());
		{
			if (node.getCorrelationId() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeCorrelationID((AsyncApi24CorrelationID) node.getCorrelationId(), object);
				JsonUtil.setObjectProperty(json, "correlationId", object);
			}
		}
		JsonUtil.setStringProperty(json, "schemaFormat", node.getSchemaFormat());
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
					this.writeTag((AsyncApi24Tag) model, object);
					JsonUtil.addToArray(array, object);
				});
				JsonUtil.setAnyProperty(json, "tags", array);
			}
		}
		{
			if (node.getExternalDocs() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeExternalDocumentation((AsyncApi24ExternalDocumentation) node.getExternalDocs(), object);
				JsonUtil.setObjectProperty(json, "externalDocs", object);
			}
		}
		{
			if (node.getBindings() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeMessageBindings((AsyncApi24MessageBindings) node.getBindings(), object);
				JsonUtil.setObjectProperty(json, "bindings", object);
			}
		}
		{
			if (node.getExamples() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeMessageExample((AsyncApi24MessageExample) node.getExamples(), object);
				JsonUtil.setObjectProperty(json, "examples", object);
			}
		}
		{
			List<? extends AsyncApiMessageTrait> models = node.getTraits();
			if (models != null && !models.isEmpty()) {
				ArrayNode array = JsonUtil.arrayNode();
				models.forEach(model -> {
					ObjectNode object = JsonUtil.objectNode();
					this.writeMessageTrait((AsyncApi24MessageTrait) model, object);
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

	public void writeMessageTrait(AsyncApi24MessageTrait node, ObjectNode json) {
		if (node == null) {
			return;
		}
		JsonUtil.setStringProperty(json, "$ref", node.get$ref());
		JsonUtil.setStringProperty(json, "messageId", node.getMessageId());
		{
			if (node.getHeaders() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeSchema((AsyncApi24Schema) node.getHeaders(), object);
				JsonUtil.setObjectProperty(json, "headers", object);
			}
		}
		{
			if (node.getCorrelationId() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeCorrelationID((AsyncApi24CorrelationID) node.getCorrelationId(), object);
				JsonUtil.setObjectProperty(json, "correlationId", object);
			}
		}
		JsonUtil.setStringProperty(json, "schemaFormat", node.getSchemaFormat());
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
					this.writeTag((AsyncApi24Tag) model, object);
					JsonUtil.addToArray(array, object);
				});
				JsonUtil.setAnyProperty(json, "tags", array);
			}
		}
		{
			if (node.getExternalDocs() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeExternalDocumentation((AsyncApi24ExternalDocumentation) node.getExternalDocs(), object);
				JsonUtil.setObjectProperty(json, "externalDocs", object);
			}
		}
		{
			if (node.getBindings() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeMessageBindings((AsyncApi24MessageBindings) node.getBindings(), object);
				JsonUtil.setObjectProperty(json, "bindings", object);
			}
		}
		JsonUtil.setAnyMapProperty(json, "examples", node.getExamples());
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

	public void writeMessageExample(AsyncApi24MessageExample node, ObjectNode json) {
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

	public void writeTag(AsyncApi24Tag node, ObjectNode json) {
		if (node == null) {
			return;
		}
		JsonUtil.setStringProperty(json, "name", node.getName());
		JsonUtil.setStringProperty(json, "description", node.getDescription());
		{
			if (node.getExternalDocs() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeExternalDocumentation((AsyncApi24ExternalDocumentation) node.getExternalDocs(), object);
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

	public void writeExternalDocumentation(AsyncApi24ExternalDocumentation node, ObjectNode json) {
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

	public void writeComponents(AsyncApi24Components node, ObjectNode json) {
		if (node == null) {
			return;
		}
		{
			Map<String, ? extends Schema> models = node.getSchemas();
			if (models != null && !models.isEmpty()) {
				ObjectNode object = JsonUtil.objectNode();
				models.keySet().forEach(jsonName -> {
					ObjectNode jsonValue = JsonUtil.objectNode();
					this.writeSchema((AsyncApi24Schema) models.get(jsonName), jsonValue);
					JsonUtil.setObjectProperty(object, jsonName, jsonValue);
				});
				JsonUtil.setObjectProperty(json, "schemas", object);
			}
		}
		{
			Map<String, ? extends Server> models = node.getServers();
			if (models != null && !models.isEmpty()) {
				ObjectNode object = JsonUtil.objectNode();
				models.keySet().forEach(jsonName -> {
					ObjectNode jsonValue = JsonUtil.objectNode();
					this.writeServer((AsyncApi24Server) models.get(jsonName), jsonValue);
					JsonUtil.setObjectProperty(object, jsonName, jsonValue);
				});
				JsonUtil.setObjectProperty(json, "servers", object);
			}
		}
		{
			Map<String, ? extends ServerVariable> models = node.getServerVariables();
			if (models != null && !models.isEmpty()) {
				ObjectNode object = JsonUtil.objectNode();
				models.keySet().forEach(jsonName -> {
					ObjectNode jsonValue = JsonUtil.objectNode();
					this.writeServerVariable((AsyncApi24ServerVariable) models.get(jsonName), jsonValue);
					JsonUtil.setObjectProperty(object, jsonName, jsonValue);
				});
				JsonUtil.setObjectProperty(json, "serverVariables", object);
			}
		}
		{
			Map<String, ? extends AsyncApiChannelItem> models = node.getChannels();
			if (models != null && !models.isEmpty()) {
				ObjectNode object = JsonUtil.objectNode();
				models.keySet().forEach(jsonName -> {
					ObjectNode jsonValue = JsonUtil.objectNode();
					this.writeChannelItem((AsyncApi24ChannelItem) models.get(jsonName), jsonValue);
					JsonUtil.setObjectProperty(object, jsonName, jsonValue);
				});
				JsonUtil.setObjectProperty(json, "channels", object);
			}
		}
		{
			Map<String, ? extends AsyncApiMessage> models = node.getMessages();
			if (models != null && !models.isEmpty()) {
				ObjectNode object = JsonUtil.objectNode();
				models.keySet().forEach(jsonName -> {
					ObjectNode jsonValue = JsonUtil.objectNode();
					this.writeMessage((AsyncApi24Message) models.get(jsonName), jsonValue);
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
					this.writeSecurityScheme((AsyncApi24SecurityScheme) models.get(jsonName), jsonValue);
					JsonUtil.setObjectProperty(object, jsonName, jsonValue);
				});
				JsonUtil.setObjectProperty(json, "securitySchemes", object);
			}
		}
		{
			Map<String, ? extends Parameter> models = node.getParameters();
			if (models != null && !models.isEmpty()) {
				ObjectNode object = JsonUtil.objectNode();
				models.keySet().forEach(jsonName -> {
					ObjectNode jsonValue = JsonUtil.objectNode();
					this.writeParameter((AsyncApi24Parameter) models.get(jsonName), jsonValue);
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
					this.writeCorrelationID((AsyncApi24CorrelationID) models.get(jsonName), jsonValue);
					JsonUtil.setObjectProperty(object, jsonName, jsonValue);
				});
				JsonUtil.setObjectProperty(json, "correlationIds", object);
			}
		}
		{
			Map<String, ? extends AsyncApiOperationTrait> models = node.getOperationTraits();
			if (models != null && !models.isEmpty()) {
				ObjectNode object = JsonUtil.objectNode();
				models.keySet().forEach(jsonName -> {
					ObjectNode jsonValue = JsonUtil.objectNode();
					this.writeOperationTrait((AsyncApi24OperationTrait) models.get(jsonName), jsonValue);
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
					this.writeMessageTrait((AsyncApi24MessageTrait) models.get(jsonName), jsonValue);
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
					this.writeServerBindings((AsyncApi24ServerBindings) models.get(jsonName), jsonValue);
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
					this.writeChannelBindings((AsyncApi24ChannelBindings) models.get(jsonName), jsonValue);
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
					this.writeOperationBindings((AsyncApi24OperationBindings) models.get(jsonName), jsonValue);
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
					this.writeMessageBindings((AsyncApi24MessageBindings) models.get(jsonName), jsonValue);
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

	public void writeSchema(AsyncApi24Schema node, ObjectNode json) {
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
				this.writeSchema((AsyncApi24Schema) node.getIf(), object);
				JsonUtil.setObjectProperty(json, "if", object);
			}
		}
		{
			if (node.getThen() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeSchema((AsyncApi24Schema) node.getThen(), object);
				JsonUtil.setObjectProperty(json, "then", object);
			}
		}
		{
			if (node.getElse() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeSchema((AsyncApi24Schema) node.getElse(), object);
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
					this.writeSchema((AsyncApi24Schema) models.get(jsonName), jsonValue);
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
					this.writeSchema((AsyncApi24Schema) union.asSchema(), jsonValue);
					JsonUtil.setObjectProperty(json, "additionalProperties", jsonValue);
				}
			}
		}
		{
			if (node.getAdditionalItems() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeSchema((AsyncApi24Schema) node.getAdditionalItems(), object);
				JsonUtil.setObjectProperty(json, "additionalItems", object);
			}
		}
		{
			SchemaSchemaListUnion union = node.getItems();
			if (union != null) {
				if (union.isSchema()) {
					ObjectNode jsonValue = JsonUtil.objectNode();
					this.writeSchema((AsyncApi24Schema) union.asSchema(), jsonValue);
					JsonUtil.setObjectProperty(json, "items", jsonValue);
				}
				if (union.isSchemaList()) {
					List<? extends Schema> models = union.asSchemaList();
					ArrayNode array = JsonUtil.arrayNode();
					models.forEach(model -> {
						ObjectNode object = JsonUtil.objectNode();
						this.writeSchema((AsyncApi24Schema) model, object);
						JsonUtil.addToArray(array, object);
					});
					JsonUtil.setAnyProperty(json, "items", array);
				}
			}
		}
		{
			if (node.getPropertyNames() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeSchema((AsyncApi24Schema) node.getPropertyNames(), object);
				JsonUtil.setObjectProperty(json, "propertyNames", object);
			}
		}
		{
			if (node.getContains() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeSchema((AsyncApi24Schema) node.getContains(), object);
				JsonUtil.setObjectProperty(json, "contains", object);
			}
		}
		{
			List<? extends Schema> models = node.getAllOf();
			if (models != null && !models.isEmpty()) {
				ArrayNode array = JsonUtil.arrayNode();
				models.forEach(model -> {
					ObjectNode object = JsonUtil.objectNode();
					this.writeSchema((AsyncApi24Schema) model, object);
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
					this.writeSchema((AsyncApi24Schema) model, object);
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
					this.writeSchema((AsyncApi24Schema) model, object);
					JsonUtil.addToArray(array, object);
				});
				JsonUtil.setAnyProperty(json, "anyOf", array);
			}
		}
		{
			if (node.getNot() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeSchema((AsyncApi24Schema) node.getNot(), object);
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
				this.writeExternalDocumentation((AsyncApi24ExternalDocumentation) node.getExternalDocs(), object);
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

	public void writeSecurityScheme(AsyncApi24SecurityScheme node, ObjectNode json) {
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
				this.writeOAuthFlows((AsyncApi24OAuthFlows) node.getFlows(), object);
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

	public void writeOAuthFlows(AsyncApi24OAuthFlows node, ObjectNode json) {
		if (node == null) {
			return;
		}
		{
			if (node.getImplicit() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeOAuthFlow((AsyncApi24OAuthFlow) node.getImplicit(), object);
				JsonUtil.setObjectProperty(json, "implicit", object);
			}
		}
		{
			if (node.getPassword() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeOAuthFlow((AsyncApi24OAuthFlow) node.getPassword(), object);
				JsonUtil.setObjectProperty(json, "password", object);
			}
		}
		{
			if (node.getClientCredentials() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeOAuthFlow((AsyncApi24OAuthFlow) node.getClientCredentials(), object);
				JsonUtil.setObjectProperty(json, "clientCredentials", object);
			}
		}
		{
			if (node.getAuthorizationCode() != null) {
				ObjectNode object = JsonUtil.objectNode();
				this.writeOAuthFlow((AsyncApi24OAuthFlow) node.getAuthorizationCode(), object);
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

	public void writeOAuthFlow(AsyncApi24OAuthFlow node, ObjectNode json) {
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

	public void writeSecurityRequirement(AsyncApi24SecurityRequirement node, ObjectNode json) {
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

	public void writeCorrelationID(AsyncApi24CorrelationID node, ObjectNode json) {
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

	public void writeBinding(AsyncApi24Binding node, ObjectNode json) {
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