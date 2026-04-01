package io.apicurio.datamodels.models.asyncapi.v2x.v23.io;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.apicurio.datamodels.models.RootNode;
import io.apicurio.datamodels.models.asyncapi.v2x.v23.AsyncApi23Binding;
import io.apicurio.datamodels.models.asyncapi.v2x.v23.AsyncApi23ChannelBindings;
import io.apicurio.datamodels.models.asyncapi.v2x.v23.AsyncApi23ChannelItem;
import io.apicurio.datamodels.models.asyncapi.v2x.v23.AsyncApi23Channels;
import io.apicurio.datamodels.models.asyncapi.v2x.v23.AsyncApi23Components;
import io.apicurio.datamodels.models.asyncapi.v2x.v23.AsyncApi23Contact;
import io.apicurio.datamodels.models.asyncapi.v2x.v23.AsyncApi23CorrelationID;
import io.apicurio.datamodels.models.asyncapi.v2x.v23.AsyncApi23Document;
import io.apicurio.datamodels.models.asyncapi.v2x.v23.AsyncApi23DocumentImpl;
import io.apicurio.datamodels.models.asyncapi.v2x.v23.AsyncApi23ExternalDocumentation;
import io.apicurio.datamodels.models.asyncapi.v2x.v23.AsyncApi23Info;
import io.apicurio.datamodels.models.asyncapi.v2x.v23.AsyncApi23License;
import io.apicurio.datamodels.models.asyncapi.v2x.v23.AsyncApi23Message;
import io.apicurio.datamodels.models.asyncapi.v2x.v23.AsyncApi23MessageBindings;
import io.apicurio.datamodels.models.asyncapi.v2x.v23.AsyncApi23MessageExample;
import io.apicurio.datamodels.models.asyncapi.v2x.v23.AsyncApi23MessageTrait;
import io.apicurio.datamodels.models.asyncapi.v2x.v23.AsyncApi23OAuthFlow;
import io.apicurio.datamodels.models.asyncapi.v2x.v23.AsyncApi23OAuthFlows;
import io.apicurio.datamodels.models.asyncapi.v2x.v23.AsyncApi23OneOfMessages;
import io.apicurio.datamodels.models.asyncapi.v2x.v23.AsyncApi23Operation;
import io.apicurio.datamodels.models.asyncapi.v2x.v23.AsyncApi23OperationBindings;
import io.apicurio.datamodels.models.asyncapi.v2x.v23.AsyncApi23OperationTrait;
import io.apicurio.datamodels.models.asyncapi.v2x.v23.AsyncApi23Parameter;
import io.apicurio.datamodels.models.asyncapi.v2x.v23.AsyncApi23Parameters;
import io.apicurio.datamodels.models.asyncapi.v2x.v23.AsyncApi23Schema;
import io.apicurio.datamodels.models.asyncapi.v2x.v23.AsyncApi23SecurityRequirement;
import io.apicurio.datamodels.models.asyncapi.v2x.v23.AsyncApi23SecurityScheme;
import io.apicurio.datamodels.models.asyncapi.v2x.v23.AsyncApi23Server;
import io.apicurio.datamodels.models.asyncapi.v2x.v23.AsyncApi23ServerBindings;
import io.apicurio.datamodels.models.asyncapi.v2x.v23.AsyncApi23ServerVariable;
import io.apicurio.datamodels.models.asyncapi.v2x.v23.AsyncApi23Servers;
import io.apicurio.datamodels.models.asyncapi.v2x.v23.AsyncApi23Tag;
import io.apicurio.datamodels.models.io.ModelReader;
import io.apicurio.datamodels.models.union.BooleanUnionValue;
import io.apicurio.datamodels.models.union.BooleanUnionValueImpl;
import io.apicurio.datamodels.models.union.SchemaListUnionValue;
import io.apicurio.datamodels.models.union.SchemaListUnionValueImpl;
import io.apicurio.datamodels.models.util.JsonUtil;
import io.apicurio.datamodels.models.util.ReaderUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AsyncApi23ModelReader implements ModelReader {

	public void readDocument(ObjectNode json, AsyncApi23Document node) {
		{
			String value = JsonUtil.consumeStringProperty(json, "asyncapi");
			node.setAsyncapi(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "id");
			node.setId(value);
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "info");
			if (object != null) {
				node.setInfo(node.createInfo());
				readInfo(object, (AsyncApi23Info) node.getInfo());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "servers");
			if (object != null) {
				node.setServers(node.createServers());
				readServers(object, (AsyncApi23Servers) node.getServers());
			}
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "defaultContentType");
			node.setDefaultContentType(value);
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "channels");
			if (object != null) {
				node.setChannels(node.createChannels());
				readChannels(object, (AsyncApi23Channels) node.getChannels());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "components");
			if (object != null) {
				node.setComponents(node.createComponents());
				readComponents(object, (AsyncApi23Components) node.getComponents());
			}
		}
		{
			List<ObjectNode> objects = JsonUtil.consumeObjectArrayProperty(json, "tags");
			if (objects != null) {
				objects.forEach(object -> {
					AsyncApi23Tag model = (AsyncApi23Tag) node.createTag();
					this.readTag(object, model);
					node.addTag(model);
				});
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "externalDocs");
			if (object != null) {
				node.setExternalDocs(node.createExternalDocumentation());
				readExternalDocumentation(object, (AsyncApi23ExternalDocumentation) node.getExternalDocs());
			}
		}
		{
			List<String> propertyNames = JsonUtil.matchingKeys("^x-.+$", json);
			propertyNames.forEach(name -> {
				JsonNode value = JsonUtil.consumeAnyProperty(json, name);
				node.addExtension(name, value);
			});
		}
		ReaderUtil.readExtraProperties(json, node);
	}

	@Override
	public RootNode readRoot(ObjectNode json) {
		AsyncApi23Document rootModel = new AsyncApi23DocumentImpl();
		this.readDocument(json, rootModel);
		return rootModel;
	}

	public void readInfo(ObjectNode json, AsyncApi23Info node) {
		{
			String value = JsonUtil.consumeStringProperty(json, "title");
			node.setTitle(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "version");
			node.setVersion(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "description");
			node.setDescription(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "termsOfService");
			node.setTermsOfService(value);
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "contact");
			if (object != null) {
				node.setContact(node.createContact());
				readContact(object, (AsyncApi23Contact) node.getContact());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "license");
			if (object != null) {
				node.setLicense(node.createLicense());
				readLicense(object, (AsyncApi23License) node.getLicense());
			}
		}
		{
			List<String> propertyNames = JsonUtil.matchingKeys("^x-.+$", json);
			propertyNames.forEach(name -> {
				JsonNode value = JsonUtil.consumeAnyProperty(json, name);
				node.addExtension(name, value);
			});
		}
		ReaderUtil.readExtraProperties(json, node);
	}

	public void readContact(ObjectNode json, AsyncApi23Contact node) {
		{
			String value = JsonUtil.consumeStringProperty(json, "name");
			node.setName(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "url");
			node.setUrl(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "email");
			node.setEmail(value);
		}
		{
			List<String> propertyNames = JsonUtil.matchingKeys("^x-.+$", json);
			propertyNames.forEach(name -> {
				JsonNode value = JsonUtil.consumeAnyProperty(json, name);
				node.addExtension(name, value);
			});
		}
		ReaderUtil.readExtraProperties(json, node);
	}

	public void readLicense(ObjectNode json, AsyncApi23License node) {
		{
			String value = JsonUtil.consumeStringProperty(json, "name");
			node.setName(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "url");
			node.setUrl(value);
		}
		{
			List<String> propertyNames = JsonUtil.matchingKeys("^x-.+$", json);
			propertyNames.forEach(name -> {
				JsonNode value = JsonUtil.consumeAnyProperty(json, name);
				node.addExtension(name, value);
			});
		}
		ReaderUtil.readExtraProperties(json, node);
	}

	public void readServers(ObjectNode json, AsyncApi23Servers node) {
		{
			List<String> propertyNames = JsonUtil.keys(json);
			propertyNames.forEach(name -> {
				ObjectNode object = JsonUtil.consumeObjectProperty(json, name);
				if (object != null) {
					AsyncApi23Server model = (AsyncApi23Server) node.createServer();
					this.readServer(object, model);
					node.addItem(name, model);
				}
			});
		}
		ReaderUtil.readExtraProperties(json, node);
	}

	public void readServer(ObjectNode json, AsyncApi23Server node) {
		{
			String value = JsonUtil.consumeStringProperty(json, "$ref");
			node.set$ref(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "url");
			node.setUrl(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "protocol");
			node.setProtocol(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "protocolVersion");
			node.setProtocolVersion(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "description");
			node.setDescription(value);
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "variables");
			JsonUtil.keys(object).forEach(name -> {
				ObjectNode mapValue = JsonUtil.consumeObjectProperty(object, name);
				if (mapValue != null) {
					AsyncApi23ServerVariable model = (AsyncApi23ServerVariable) node.createServerVariable();
					this.readServerVariable(mapValue, model);
					node.addVariable(name, model);
				}
			});
		}
		{
			List<ObjectNode> objects = JsonUtil.consumeObjectArrayProperty(json, "security");
			if (objects != null) {
				objects.forEach(object -> {
					AsyncApi23SecurityRequirement model = (AsyncApi23SecurityRequirement) node
							.createSecurityRequirement();
					this.readSecurityRequirement(object, model);
					node.addSecurity(model);
				});
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "bindings");
			if (object != null) {
				node.setBindings(node.createServerBindings());
				readServerBindings(object, (AsyncApi23ServerBindings) node.getBindings());
			}
		}
		{
			List<String> propertyNames = JsonUtil.matchingKeys("^x-.+$", json);
			propertyNames.forEach(name -> {
				JsonNode value = JsonUtil.consumeAnyProperty(json, name);
				node.addExtension(name, value);
			});
		}
		ReaderUtil.readExtraProperties(json, node);
	}

	public void readServerVariable(ObjectNode json, AsyncApi23ServerVariable node) {
		{
			List<String> value = JsonUtil.consumeStringArrayProperty(json, "enum");
			node.setEnum(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "default");
			node.setDefault(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "description");
			node.setDescription(value);
		}
		{
			List<String> value = JsonUtil.consumeStringArrayProperty(json, "examples");
			node.setExamples(value);
		}
		{
			List<String> propertyNames = JsonUtil.matchingKeys("^x-.+$", json);
			propertyNames.forEach(name -> {
				JsonNode value = JsonUtil.consumeAnyProperty(json, name);
				node.addExtension(name, value);
			});
		}
		ReaderUtil.readExtraProperties(json, node);
	}

	public void readChannels(ObjectNode json, AsyncApi23Channels node) {
		{
			List<String> propertyNames = JsonUtil.keys(json);
			propertyNames.forEach(name -> {
				ObjectNode object = JsonUtil.consumeObjectProperty(json, name);
				if (object != null) {
					AsyncApi23ChannelItem model = (AsyncApi23ChannelItem) node.createChannelItem();
					this.readChannelItem(object, model);
					node.addItem(name, model);
				}
			});
		}
		ReaderUtil.readExtraProperties(json, node);
	}

	public void readChannelItem(ObjectNode json, AsyncApi23ChannelItem node) {
		{
			String value = JsonUtil.consumeStringProperty(json, "$ref");
			node.set$ref(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "description");
			node.setDescription(value);
		}
		{
			List<String> value = JsonUtil.consumeStringArrayProperty(json, "servers");
			node.setServers(value);
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "subscribe");
			if (object != null) {
				node.setSubscribe(node.createOperation());
				readOperation(object, (AsyncApi23Operation) node.getSubscribe());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "publish");
			if (object != null) {
				node.setPublish(node.createOperation());
				readOperation(object, (AsyncApi23Operation) node.getPublish());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "parameters");
			if (object != null) {
				node.setParameters(node.createParameters());
				readParameters(object, (AsyncApi23Parameters) node.getParameters());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "bindings");
			if (object != null) {
				node.setBindings(node.createChannelBindings());
				readChannelBindings(object, (AsyncApi23ChannelBindings) node.getBindings());
			}
		}
		{
			List<String> propertyNames = JsonUtil.matchingKeys("^x-.+$", json);
			propertyNames.forEach(name -> {
				JsonNode value = JsonUtil.consumeAnyProperty(json, name);
				node.addExtension(name, value);
			});
		}
		ReaderUtil.readExtraProperties(json, node);
	}

	public void readOperation(ObjectNode json, AsyncApi23Operation node) {
		{
			String value = JsonUtil.consumeStringProperty(json, "operationId");
			node.setOperationId(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "summary");
			node.setSummary(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "description");
			node.setDescription(value);
		}
		{
			List<ObjectNode> objects = JsonUtil.consumeObjectArrayProperty(json, "tags");
			if (objects != null) {
				objects.forEach(object -> {
					AsyncApi23Tag model = (AsyncApi23Tag) node.createTag();
					this.readTag(object, model);
					node.addTag(model);
				});
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "externalDocs");
			if (object != null) {
				node.setExternalDocs(node.createExternalDocumentation());
				readExternalDocumentation(object, (AsyncApi23ExternalDocumentation) node.getExternalDocs());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "bindings");
			if (object != null) {
				node.setBindings(node.createOperationBindings());
				readOperationBindings(object, (AsyncApi23OperationBindings) node.getBindings());
			}
		}
		{
			List<ObjectNode> objects = JsonUtil.consumeObjectArrayProperty(json, "traits");
			if (objects != null) {
				objects.forEach(object -> {
					AsyncApi23OperationTrait model = (AsyncApi23OperationTrait) node.createOperationTrait();
					this.readOperationTrait(object, model);
					node.addTrait(model);
				});
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "message");
			if (object != null) {
				node.setMessage(node.createMessage());
				readMessage(object, (AsyncApi23Message) node.getMessage());
			}
		}
		{
			List<String> propertyNames = JsonUtil.matchingKeys("^x-.+$", json);
			propertyNames.forEach(name -> {
				JsonNode value = JsonUtil.consumeAnyProperty(json, name);
				node.addExtension(name, value);
			});
		}
		ReaderUtil.readExtraProperties(json, node);
	}

	public void readOperationTrait(ObjectNode json, AsyncApi23OperationTrait node) {
		{
			String value = JsonUtil.consumeStringProperty(json, "$ref");
			node.set$ref(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "operationId");
			node.setOperationId(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "summary");
			node.setSummary(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "description");
			node.setDescription(value);
		}
		{
			List<ObjectNode> objects = JsonUtil.consumeObjectArrayProperty(json, "tags");
			if (objects != null) {
				objects.forEach(object -> {
					AsyncApi23Tag model = (AsyncApi23Tag) node.createTag();
					this.readTag(object, model);
					node.addTag(model);
				});
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "externalDocs");
			if (object != null) {
				node.setExternalDocs(node.createExternalDocumentation());
				readExternalDocumentation(object, (AsyncApi23ExternalDocumentation) node.getExternalDocs());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "bindings");
			if (object != null) {
				node.setBindings(node.createOperationBindings());
				readOperationBindings(object, (AsyncApi23OperationBindings) node.getBindings());
			}
		}
		{
			List<String> propertyNames = JsonUtil.matchingKeys("^x-.+$", json);
			propertyNames.forEach(name -> {
				JsonNode value = JsonUtil.consumeAnyProperty(json, name);
				node.addExtension(name, value);
			});
		}
		ReaderUtil.readExtraProperties(json, node);
	}

	public void readParameters(ObjectNode json, AsyncApi23Parameters node) {
		{
			List<String> propertyNames = JsonUtil.keys(json);
			propertyNames.forEach(name -> {
				ObjectNode object = JsonUtil.consumeObjectProperty(json, name);
				if (object != null) {
					AsyncApi23Parameter model = (AsyncApi23Parameter) node.createParameter();
					this.readParameter(object, model);
					node.addItem(name, model);
				}
			});
		}
		ReaderUtil.readExtraProperties(json, node);
	}

	public void readParameter(ObjectNode json, AsyncApi23Parameter node) {
		{
			String value = JsonUtil.consumeStringProperty(json, "$ref");
			node.set$ref(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "description");
			node.setDescription(value);
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "schema");
			if (object != null) {
				node.setSchema(node.createSchema());
				readSchema(object, (AsyncApi23Schema) node.getSchema());
			}
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "location");
			node.setLocation(value);
		}
		{
			List<String> propertyNames = JsonUtil.matchingKeys("^x-.+$", json);
			propertyNames.forEach(name -> {
				JsonNode value = JsonUtil.consumeAnyProperty(json, name);
				node.addExtension(name, value);
			});
		}
		ReaderUtil.readExtraProperties(json, node);
	}

	public void readServerBindings(ObjectNode json, AsyncApi23ServerBindings node) {
		{
			String value = JsonUtil.consumeStringProperty(json, "$ref");
			node.set$ref(value);
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "http");
			if (object != null) {
				node.setHttp(node.createBinding());
				readBinding(object, (AsyncApi23Binding) node.getHttp());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "ws");
			if (object != null) {
				node.setWs(node.createBinding());
				readBinding(object, (AsyncApi23Binding) node.getWs());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "kafka");
			if (object != null) {
				node.setKafka(node.createBinding());
				readBinding(object, (AsyncApi23Binding) node.getKafka());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "anypointmq");
			if (object != null) {
				node.setAnypointmq(node.createBinding());
				readBinding(object, (AsyncApi23Binding) node.getAnypointmq());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "amqp");
			if (object != null) {
				node.setAmqp(node.createBinding());
				readBinding(object, (AsyncApi23Binding) node.getAmqp());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "amqp1");
			if (object != null) {
				node.setAmqp1(node.createBinding());
				readBinding(object, (AsyncApi23Binding) node.getAmqp1());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "mqtt");
			if (object != null) {
				node.setMqtt(node.createBinding());
				readBinding(object, (AsyncApi23Binding) node.getMqtt());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "mqtt5");
			if (object != null) {
				node.setMqtt5(node.createBinding());
				readBinding(object, (AsyncApi23Binding) node.getMqtt5());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "nats");
			if (object != null) {
				node.setNats(node.createBinding());
				readBinding(object, (AsyncApi23Binding) node.getNats());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "jms");
			if (object != null) {
				node.setJms(node.createBinding());
				readBinding(object, (AsyncApi23Binding) node.getJms());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "sns");
			if (object != null) {
				node.setSns(node.createBinding());
				readBinding(object, (AsyncApi23Binding) node.getSns());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "solace");
			if (object != null) {
				node.setSolace(node.createBinding());
				readBinding(object, (AsyncApi23Binding) node.getSolace());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "sqs");
			if (object != null) {
				node.setSqs(node.createBinding());
				readBinding(object, (AsyncApi23Binding) node.getSqs());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "stomp");
			if (object != null) {
				node.setStomp(node.createBinding());
				readBinding(object, (AsyncApi23Binding) node.getStomp());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "redis");
			if (object != null) {
				node.setRedis(node.createBinding());
				readBinding(object, (AsyncApi23Binding) node.getRedis());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "mercure");
			if (object != null) {
				node.setMercure(node.createBinding());
				readBinding(object, (AsyncApi23Binding) node.getMercure());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "ibmmq");
			if (object != null) {
				node.setIbmmq(node.createBinding());
				readBinding(object, (AsyncApi23Binding) node.getIbmmq());
			}
		}
		{
			List<String> propertyNames = JsonUtil.matchingKeys("^x-.+$", json);
			propertyNames.forEach(name -> {
				JsonNode value = JsonUtil.consumeAnyProperty(json, name);
				node.addExtension(name, value);
			});
		}
		ReaderUtil.readExtraProperties(json, node);
	}

	public void readChannelBindings(ObjectNode json, AsyncApi23ChannelBindings node) {
		{
			String value = JsonUtil.consumeStringProperty(json, "$ref");
			node.set$ref(value);
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "http");
			if (object != null) {
				node.setHttp(node.createBinding());
				readBinding(object, (AsyncApi23Binding) node.getHttp());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "ws");
			if (object != null) {
				node.setWs(node.createBinding());
				readBinding(object, (AsyncApi23Binding) node.getWs());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "kafka");
			if (object != null) {
				node.setKafka(node.createBinding());
				readBinding(object, (AsyncApi23Binding) node.getKafka());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "anypointmq");
			if (object != null) {
				node.setAnypointmq(node.createBinding());
				readBinding(object, (AsyncApi23Binding) node.getAnypointmq());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "amqp");
			if (object != null) {
				node.setAmqp(node.createBinding());
				readBinding(object, (AsyncApi23Binding) node.getAmqp());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "amqp1");
			if (object != null) {
				node.setAmqp1(node.createBinding());
				readBinding(object, (AsyncApi23Binding) node.getAmqp1());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "mqtt");
			if (object != null) {
				node.setMqtt(node.createBinding());
				readBinding(object, (AsyncApi23Binding) node.getMqtt());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "mqtt5");
			if (object != null) {
				node.setMqtt5(node.createBinding());
				readBinding(object, (AsyncApi23Binding) node.getMqtt5());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "nats");
			if (object != null) {
				node.setNats(node.createBinding());
				readBinding(object, (AsyncApi23Binding) node.getNats());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "jms");
			if (object != null) {
				node.setJms(node.createBinding());
				readBinding(object, (AsyncApi23Binding) node.getJms());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "sns");
			if (object != null) {
				node.setSns(node.createBinding());
				readBinding(object, (AsyncApi23Binding) node.getSns());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "solace");
			if (object != null) {
				node.setSolace(node.createBinding());
				readBinding(object, (AsyncApi23Binding) node.getSolace());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "sqs");
			if (object != null) {
				node.setSqs(node.createBinding());
				readBinding(object, (AsyncApi23Binding) node.getSqs());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "stomp");
			if (object != null) {
				node.setStomp(node.createBinding());
				readBinding(object, (AsyncApi23Binding) node.getStomp());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "redis");
			if (object != null) {
				node.setRedis(node.createBinding());
				readBinding(object, (AsyncApi23Binding) node.getRedis());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "mercure");
			if (object != null) {
				node.setMercure(node.createBinding());
				readBinding(object, (AsyncApi23Binding) node.getMercure());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "ibmmq");
			if (object != null) {
				node.setIbmmq(node.createBinding());
				readBinding(object, (AsyncApi23Binding) node.getIbmmq());
			}
		}
		{
			List<String> propertyNames = JsonUtil.matchingKeys("^x-.+$", json);
			propertyNames.forEach(name -> {
				JsonNode value = JsonUtil.consumeAnyProperty(json, name);
				node.addExtension(name, value);
			});
		}
		ReaderUtil.readExtraProperties(json, node);
	}

	public void readOperationBindings(ObjectNode json, AsyncApi23OperationBindings node) {
		{
			String value = JsonUtil.consumeStringProperty(json, "$ref");
			node.set$ref(value);
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "http");
			if (object != null) {
				node.setHttp(node.createBinding());
				readBinding(object, (AsyncApi23Binding) node.getHttp());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "ws");
			if (object != null) {
				node.setWs(node.createBinding());
				readBinding(object, (AsyncApi23Binding) node.getWs());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "kafka");
			if (object != null) {
				node.setKafka(node.createBinding());
				readBinding(object, (AsyncApi23Binding) node.getKafka());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "anypointmq");
			if (object != null) {
				node.setAnypointmq(node.createBinding());
				readBinding(object, (AsyncApi23Binding) node.getAnypointmq());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "amqp");
			if (object != null) {
				node.setAmqp(node.createBinding());
				readBinding(object, (AsyncApi23Binding) node.getAmqp());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "amqp1");
			if (object != null) {
				node.setAmqp1(node.createBinding());
				readBinding(object, (AsyncApi23Binding) node.getAmqp1());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "mqtt");
			if (object != null) {
				node.setMqtt(node.createBinding());
				readBinding(object, (AsyncApi23Binding) node.getMqtt());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "mqtt5");
			if (object != null) {
				node.setMqtt5(node.createBinding());
				readBinding(object, (AsyncApi23Binding) node.getMqtt5());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "nats");
			if (object != null) {
				node.setNats(node.createBinding());
				readBinding(object, (AsyncApi23Binding) node.getNats());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "jms");
			if (object != null) {
				node.setJms(node.createBinding());
				readBinding(object, (AsyncApi23Binding) node.getJms());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "sns");
			if (object != null) {
				node.setSns(node.createBinding());
				readBinding(object, (AsyncApi23Binding) node.getSns());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "solace");
			if (object != null) {
				node.setSolace(node.createBinding());
				readBinding(object, (AsyncApi23Binding) node.getSolace());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "sqs");
			if (object != null) {
				node.setSqs(node.createBinding());
				readBinding(object, (AsyncApi23Binding) node.getSqs());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "stomp");
			if (object != null) {
				node.setStomp(node.createBinding());
				readBinding(object, (AsyncApi23Binding) node.getStomp());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "redis");
			if (object != null) {
				node.setRedis(node.createBinding());
				readBinding(object, (AsyncApi23Binding) node.getRedis());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "mercure");
			if (object != null) {
				node.setMercure(node.createBinding());
				readBinding(object, (AsyncApi23Binding) node.getMercure());
			}
		}
		{
			List<String> propertyNames = JsonUtil.matchingKeys("^x-.+$", json);
			propertyNames.forEach(name -> {
				JsonNode value = JsonUtil.consumeAnyProperty(json, name);
				node.addExtension(name, value);
			});
		}
		ReaderUtil.readExtraProperties(json, node);
	}

	public void readMessageBindings(ObjectNode json, AsyncApi23MessageBindings node) {
		{
			String value = JsonUtil.consumeStringProperty(json, "$ref");
			node.set$ref(value);
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "http");
			if (object != null) {
				node.setHttp(node.createBinding());
				readBinding(object, (AsyncApi23Binding) node.getHttp());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "ws");
			if (object != null) {
				node.setWs(node.createBinding());
				readBinding(object, (AsyncApi23Binding) node.getWs());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "kafka");
			if (object != null) {
				node.setKafka(node.createBinding());
				readBinding(object, (AsyncApi23Binding) node.getKafka());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "anypointmq");
			if (object != null) {
				node.setAnypointmq(node.createBinding());
				readBinding(object, (AsyncApi23Binding) node.getAnypointmq());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "amqp");
			if (object != null) {
				node.setAmqp(node.createBinding());
				readBinding(object, (AsyncApi23Binding) node.getAmqp());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "amqp1");
			if (object != null) {
				node.setAmqp1(node.createBinding());
				readBinding(object, (AsyncApi23Binding) node.getAmqp1());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "mqtt");
			if (object != null) {
				node.setMqtt(node.createBinding());
				readBinding(object, (AsyncApi23Binding) node.getMqtt());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "mqtt5");
			if (object != null) {
				node.setMqtt5(node.createBinding());
				readBinding(object, (AsyncApi23Binding) node.getMqtt5());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "nats");
			if (object != null) {
				node.setNats(node.createBinding());
				readBinding(object, (AsyncApi23Binding) node.getNats());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "jms");
			if (object != null) {
				node.setJms(node.createBinding());
				readBinding(object, (AsyncApi23Binding) node.getJms());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "sns");
			if (object != null) {
				node.setSns(node.createBinding());
				readBinding(object, (AsyncApi23Binding) node.getSns());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "solace");
			if (object != null) {
				node.setSolace(node.createBinding());
				readBinding(object, (AsyncApi23Binding) node.getSolace());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "sqs");
			if (object != null) {
				node.setSqs(node.createBinding());
				readBinding(object, (AsyncApi23Binding) node.getSqs());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "stomp");
			if (object != null) {
				node.setStomp(node.createBinding());
				readBinding(object, (AsyncApi23Binding) node.getStomp());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "redis");
			if (object != null) {
				node.setRedis(node.createBinding());
				readBinding(object, (AsyncApi23Binding) node.getRedis());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "mercure");
			if (object != null) {
				node.setMercure(node.createBinding());
				readBinding(object, (AsyncApi23Binding) node.getMercure());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "ibmmq");
			if (object != null) {
				node.setIbmmq(node.createBinding());
				readBinding(object, (AsyncApi23Binding) node.getIbmmq());
			}
		}
		{
			List<String> propertyNames = JsonUtil.matchingKeys("^x-.+$", json);
			propertyNames.forEach(name -> {
				JsonNode value = JsonUtil.consumeAnyProperty(json, name);
				node.addExtension(name, value);
			});
		}
		ReaderUtil.readExtraProperties(json, node);
	}

	public void readOneOfMessages(ObjectNode json, AsyncApi23OneOfMessages node) {
		{
			List<ObjectNode> objects = JsonUtil.consumeObjectArrayProperty(json, "oneOf");
			if (objects != null) {
				objects.forEach(object -> {
					AsyncApi23Message model = (AsyncApi23Message) node.createMessage();
					this.readMessage(object, model);
					node.addOneOf(model);
				});
			}
		}
		ReaderUtil.readExtraProperties(json, node);
	}

	public void readMessage(ObjectNode json, AsyncApi23Message node) {
		{
			String value = JsonUtil.consumeStringProperty(json, "$ref");
			node.set$ref(value);
		}
		{
			List<ObjectNode> objects = JsonUtil.consumeObjectArrayProperty(json, "oneOf");
			if (objects != null) {
				objects.forEach(object -> {
					AsyncApi23Message model = (AsyncApi23Message) node.createMessage();
					this.readMessage(object, model);
					node.addOneOf(model);
				});
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "headers");
			if (object != null) {
				node.setHeaders(node.createSchema());
				readSchema(object, (AsyncApi23Schema) node.getHeaders());
			}
		}
		{
			JsonNode value = JsonUtil.consumeAnyProperty(json, "payload");
			node.setPayload(value);
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "correlationId");
			if (object != null) {
				node.setCorrelationId(node.createCorrelationID());
				readCorrelationID(object, (AsyncApi23CorrelationID) node.getCorrelationId());
			}
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "schemaFormat");
			node.setSchemaFormat(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "contentType");
			node.setContentType(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "name");
			node.setName(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "title");
			node.setTitle(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "summary");
			node.setSummary(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "description");
			node.setDescription(value);
		}
		{
			List<ObjectNode> objects = JsonUtil.consumeObjectArrayProperty(json, "tags");
			if (objects != null) {
				objects.forEach(object -> {
					AsyncApi23Tag model = (AsyncApi23Tag) node.createTag();
					this.readTag(object, model);
					node.addTag(model);
				});
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "externalDocs");
			if (object != null) {
				node.setExternalDocs(node.createExternalDocumentation());
				readExternalDocumentation(object, (AsyncApi23ExternalDocumentation) node.getExternalDocs());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "bindings");
			if (object != null) {
				node.setBindings(node.createMessageBindings());
				readMessageBindings(object, (AsyncApi23MessageBindings) node.getBindings());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "examples");
			if (object != null) {
				node.setExamples(node.createMessageExample());
				readMessageExample(object, (AsyncApi23MessageExample) node.getExamples());
			}
		}
		{
			List<ObjectNode> objects = JsonUtil.consumeObjectArrayProperty(json, "traits");
			if (objects != null) {
				objects.forEach(object -> {
					AsyncApi23MessageTrait model = (AsyncApi23MessageTrait) node.createMessageTrait();
					this.readMessageTrait(object, model);
					node.addTrait(model);
				});
			}
		}
		{
			List<String> propertyNames = JsonUtil.matchingKeys("^x-.+$", json);
			propertyNames.forEach(name -> {
				JsonNode value = JsonUtil.consumeAnyProperty(json, name);
				node.addExtension(name, value);
			});
		}
		ReaderUtil.readExtraProperties(json, node);
	}

	public void readMessageTrait(ObjectNode json, AsyncApi23MessageTrait node) {
		{
			String value = JsonUtil.consumeStringProperty(json, "$ref");
			node.set$ref(value);
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "headers");
			if (object != null) {
				node.setHeaders(node.createSchema());
				readSchema(object, (AsyncApi23Schema) node.getHeaders());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "correlationId");
			if (object != null) {
				node.setCorrelationId(node.createCorrelationID());
				readCorrelationID(object, (AsyncApi23CorrelationID) node.getCorrelationId());
			}
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "schemaFormat");
			node.setSchemaFormat(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "contentType");
			node.setContentType(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "name");
			node.setName(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "title");
			node.setTitle(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "summary");
			node.setSummary(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "description");
			node.setDescription(value);
		}
		{
			List<ObjectNode> objects = JsonUtil.consumeObjectArrayProperty(json, "tags");
			if (objects != null) {
				objects.forEach(object -> {
					AsyncApi23Tag model = (AsyncApi23Tag) node.createTag();
					this.readTag(object, model);
					node.addTag(model);
				});
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "externalDocs");
			if (object != null) {
				node.setExternalDocs(node.createExternalDocumentation());
				readExternalDocumentation(object, (AsyncApi23ExternalDocumentation) node.getExternalDocs());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "bindings");
			if (object != null) {
				node.setBindings(node.createMessageBindings());
				readMessageBindings(object, (AsyncApi23MessageBindings) node.getBindings());
			}
		}
		{
			Map<String, JsonNode> value = JsonUtil.consumeAnyMapProperty(json, "examples");
			node.setExamples(value);
		}
		{
			List<String> propertyNames = JsonUtil.matchingKeys("^x-.+$", json);
			propertyNames.forEach(name -> {
				JsonNode value = JsonUtil.consumeAnyProperty(json, name);
				node.addExtension(name, value);
			});
		}
		ReaderUtil.readExtraProperties(json, node);
	}

	public void readMessageExample(ObjectNode json, AsyncApi23MessageExample node) {
		{
			Map<String, JsonNode> value = JsonUtil.consumeAnyMapProperty(json, "headers");
			node.setHeaders(value);
		}
		{
			JsonNode value = JsonUtil.consumeAnyProperty(json, "payload");
			node.setPayload(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "name");
			node.setName(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "summary");
			node.setSummary(value);
		}
		{
			List<String> propertyNames = JsonUtil.matchingKeys("^x-.+$", json);
			propertyNames.forEach(name -> {
				JsonNode value = JsonUtil.consumeAnyProperty(json, name);
				node.addExtension(name, value);
			});
		}
		ReaderUtil.readExtraProperties(json, node);
	}

	public void readTag(ObjectNode json, AsyncApi23Tag node) {
		{
			String value = JsonUtil.consumeStringProperty(json, "name");
			node.setName(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "description");
			node.setDescription(value);
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "externalDocs");
			if (object != null) {
				node.setExternalDocs(node.createExternalDocumentation());
				readExternalDocumentation(object, (AsyncApi23ExternalDocumentation) node.getExternalDocs());
			}
		}
		{
			List<String> propertyNames = JsonUtil.matchingKeys("^x-.+$", json);
			propertyNames.forEach(name -> {
				JsonNode value = JsonUtil.consumeAnyProperty(json, name);
				node.addExtension(name, value);
			});
		}
		ReaderUtil.readExtraProperties(json, node);
	}

	public void readExternalDocumentation(ObjectNode json, AsyncApi23ExternalDocumentation node) {
		{
			String value = JsonUtil.consumeStringProperty(json, "description");
			node.setDescription(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "url");
			node.setUrl(value);
		}
		{
			List<String> propertyNames = JsonUtil.matchingKeys("^x-.+$", json);
			propertyNames.forEach(name -> {
				JsonNode value = JsonUtil.consumeAnyProperty(json, name);
				node.addExtension(name, value);
			});
		}
		ReaderUtil.readExtraProperties(json, node);
	}

	public void readComponents(ObjectNode json, AsyncApi23Components node) {
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "schemas");
			JsonUtil.keys(object).forEach(name -> {
				ObjectNode mapValue = JsonUtil.consumeObjectProperty(object, name);
				if (mapValue != null) {
					AsyncApi23Schema model = (AsyncApi23Schema) node.createSchema();
					this.readSchema(mapValue, model);
					node.addSchema(name, model);
				}
			});
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "servers");
			JsonUtil.keys(object).forEach(name -> {
				ObjectNode mapValue = JsonUtil.consumeObjectProperty(object, name);
				if (mapValue != null) {
					AsyncApi23Server model = (AsyncApi23Server) node.createServer();
					this.readServer(mapValue, model);
					node.addServer(name, model);
				}
			});
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "channels");
			JsonUtil.keys(object).forEach(name -> {
				ObjectNode mapValue = JsonUtil.consumeObjectProperty(object, name);
				if (mapValue != null) {
					AsyncApi23ChannelItem model = (AsyncApi23ChannelItem) node.createChannelItem();
					this.readChannelItem(mapValue, model);
					node.addChannel(name, model);
				}
			});
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "messages");
			JsonUtil.keys(object).forEach(name -> {
				ObjectNode mapValue = JsonUtil.consumeObjectProperty(object, name);
				if (mapValue != null) {
					AsyncApi23Message model = (AsyncApi23Message) node.createMessage();
					this.readMessage(mapValue, model);
					node.addMessage(name, model);
				}
			});
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "securitySchemes");
			JsonUtil.keys(object).forEach(name -> {
				ObjectNode mapValue = JsonUtil.consumeObjectProperty(object, name);
				if (mapValue != null) {
					AsyncApi23SecurityScheme model = (AsyncApi23SecurityScheme) node.createSecurityScheme();
					this.readSecurityScheme(mapValue, model);
					node.addSecurityScheme(name, model);
				}
			});
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "parameters");
			JsonUtil.keys(object).forEach(name -> {
				ObjectNode mapValue = JsonUtil.consumeObjectProperty(object, name);
				if (mapValue != null) {
					AsyncApi23Parameter model = (AsyncApi23Parameter) node.createParameter();
					this.readParameter(mapValue, model);
					node.addParameter(name, model);
				}
			});
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "correlationIds");
			JsonUtil.keys(object).forEach(name -> {
				ObjectNode mapValue = JsonUtil.consumeObjectProperty(object, name);
				if (mapValue != null) {
					AsyncApi23CorrelationID model = (AsyncApi23CorrelationID) node.createCorrelationID();
					this.readCorrelationID(mapValue, model);
					node.addCorrelationId(name, model);
				}
			});
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "operationTraits");
			JsonUtil.keys(object).forEach(name -> {
				ObjectNode mapValue = JsonUtil.consumeObjectProperty(object, name);
				if (mapValue != null) {
					AsyncApi23OperationTrait model = (AsyncApi23OperationTrait) node.createOperationTrait();
					this.readOperationTrait(mapValue, model);
					node.addOperationTrait(name, model);
				}
			});
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "messageTraits");
			JsonUtil.keys(object).forEach(name -> {
				ObjectNode mapValue = JsonUtil.consumeObjectProperty(object, name);
				if (mapValue != null) {
					AsyncApi23MessageTrait model = (AsyncApi23MessageTrait) node.createMessageTrait();
					this.readMessageTrait(mapValue, model);
					node.addMessageTrait(name, model);
				}
			});
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "serverBindings");
			JsonUtil.keys(object).forEach(name -> {
				ObjectNode mapValue = JsonUtil.consumeObjectProperty(object, name);
				if (mapValue != null) {
					AsyncApi23ServerBindings model = (AsyncApi23ServerBindings) node.createServerBindings();
					this.readServerBindings(mapValue, model);
					node.addServerBinding(name, model);
				}
			});
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "channelBindings");
			JsonUtil.keys(object).forEach(name -> {
				ObjectNode mapValue = JsonUtil.consumeObjectProperty(object, name);
				if (mapValue != null) {
					AsyncApi23ChannelBindings model = (AsyncApi23ChannelBindings) node.createChannelBindings();
					this.readChannelBindings(mapValue, model);
					node.addChannelBinding(name, model);
				}
			});
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "operationBindings");
			JsonUtil.keys(object).forEach(name -> {
				ObjectNode mapValue = JsonUtil.consumeObjectProperty(object, name);
				if (mapValue != null) {
					AsyncApi23OperationBindings model = (AsyncApi23OperationBindings) node.createOperationBindings();
					this.readOperationBindings(mapValue, model);
					node.addOperationBinding(name, model);
				}
			});
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "messageBindings");
			JsonUtil.keys(object).forEach(name -> {
				ObjectNode mapValue = JsonUtil.consumeObjectProperty(object, name);
				if (mapValue != null) {
					AsyncApi23MessageBindings model = (AsyncApi23MessageBindings) node.createMessageBindings();
					this.readMessageBindings(mapValue, model);
					node.addMessageBinding(name, model);
				}
			});
		}
		{
			List<String> propertyNames = JsonUtil.matchingKeys("^x-.+$", json);
			propertyNames.forEach(name -> {
				JsonNode value = JsonUtil.consumeAnyProperty(json, name);
				node.addExtension(name, value);
			});
		}
		ReaderUtil.readExtraProperties(json, node);
	}

	public void readSchema(ObjectNode json, AsyncApi23Schema node) {
		{
			String value = JsonUtil.consumeStringProperty(json, "$ref");
			node.set$ref(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "title");
			node.setTitle(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "type");
			node.setType(value);
		}
		{
			List<String> value = JsonUtil.consumeStringArrayProperty(json, "required");
			node.setRequired(value);
		}
		{
			Number value = JsonUtil.consumeNumberProperty(json, "multipleOf");
			node.setMultipleOf(value);
		}
		{
			Number value = JsonUtil.consumeNumberProperty(json, "maximum");
			node.setMaximum(value);
		}
		{
			Number value = JsonUtil.consumeNumberProperty(json, "exclusiveMaximum");
			node.setExclusiveMaximum(value);
		}
		{
			Number value = JsonUtil.consumeNumberProperty(json, "minimum");
			node.setMinimum(value);
		}
		{
			Number value = JsonUtil.consumeNumberProperty(json, "exclusiveMinimum");
			node.setExclusiveMinimum(value);
		}
		{
			Integer value = JsonUtil.consumeIntegerProperty(json, "maxLength");
			node.setMaxLength(value);
		}
		{
			Integer value = JsonUtil.consumeIntegerProperty(json, "minLength");
			node.setMinLength(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "pattern");
			node.setPattern(value);
		}
		{
			Integer value = JsonUtil.consumeIntegerProperty(json, "maxItems");
			node.setMaxItems(value);
		}
		{
			Integer value = JsonUtil.consumeIntegerProperty(json, "minItems");
			node.setMinItems(value);
		}
		{
			Boolean value = JsonUtil.consumeBooleanProperty(json, "uniqueItems");
			node.setUniqueItems(value);
		}
		{
			Integer value = JsonUtil.consumeIntegerProperty(json, "maxProperties");
			node.setMaxProperties(value);
		}
		{
			Integer value = JsonUtil.consumeIntegerProperty(json, "minProperties");
			node.setMinProperties(value);
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
			List<JsonNode> value = JsonUtil.consumeAnyArrayProperty(json, "examples");
			node.setExamples(value);
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "if");
			if (object != null) {
				node.setIf(node.createSchema());
				readSchema(object, (AsyncApi23Schema) node.getIf());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "then");
			if (object != null) {
				node.setThen(node.createSchema());
				readSchema(object, (AsyncApi23Schema) node.getThen());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "else");
			if (object != null) {
				node.setElse(node.createSchema());
				readSchema(object, (AsyncApi23Schema) node.getElse());
			}
		}
		{
			Boolean value = JsonUtil.consumeBooleanProperty(json, "readOnly");
			node.setReadOnly(value);
		}
		{
			Boolean value = JsonUtil.consumeBooleanProperty(json, "writeOnly");
			node.setWriteOnly(value);
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "properties");
			JsonUtil.keys(object).forEach(name -> {
				ObjectNode mapValue = JsonUtil.consumeObjectProperty(object, name);
				if (mapValue != null) {
					AsyncApi23Schema model = (AsyncApi23Schema) node.createSchema();
					this.readSchema(mapValue, model);
					node.addProperty(name, model);
				}
			});
		}
		{
			Map<String, String> value = JsonUtil.consumeStringMapProperty(json, "patternProperties");
			node.setPatternProperties(value);
		}
		{
			JsonNode value = JsonUtil.consumeAnyProperty(json, "additionalProperties");
			if (value != null) {
				if (JsonUtil.isObject(value)) {
					ObjectNode object = JsonUtil.toObject(value);
					node.setAdditionalProperties(node.createSchema());
					readSchema(object, (AsyncApi23Schema) node.getAdditionalProperties());
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
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "additionalItems");
			if (object != null) {
				node.setAdditionalItems(node.createSchema());
				readSchema(object, (AsyncApi23Schema) node.getAdditionalItems());
			}
		}
		{
			JsonNode value = JsonUtil.consumeAnyProperty(json, "items");
			if (value != null) {
				if (JsonUtil.isObject(value)) {
					ObjectNode object = JsonUtil.toObject(value);
					node.setItems(node.createSchema());
					readSchema(object, (AsyncApi23Schema) node.getItems());
				} else if (JsonUtil.isArray(value)) {
					List<JsonNode> array = JsonUtil.toList(value);
					List<AsyncApi23Schema> models = new ArrayList<>();
					array.forEach(item -> {
						ObjectNode object = JsonUtil.toObject(item);
						AsyncApi23Schema model = (AsyncApi23Schema) node.createSchema();
						this.readSchema(object, model);
						models.add(model);
					});
					@SuppressWarnings({"unchecked", "rawtypes"})
					SchemaListUnionValue unionValue = new SchemaListUnionValueImpl((List) models);
					node.setItems(unionValue);
				} else {
					node.addExtraProperty("items", value);
				}
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "propertyNames");
			if (object != null) {
				node.setPropertyNames(node.createSchema());
				readSchema(object, (AsyncApi23Schema) node.getPropertyNames());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "contains");
			if (object != null) {
				node.setContains(node.createSchema());
				readSchema(object, (AsyncApi23Schema) node.getContains());
			}
		}
		{
			List<ObjectNode> objects = JsonUtil.consumeObjectArrayProperty(json, "allOf");
			if (objects != null) {
				objects.forEach(object -> {
					AsyncApi23Schema model = (AsyncApi23Schema) node.createSchema();
					this.readSchema(object, model);
					node.addAllOf(model);
				});
			}
		}
		{
			List<ObjectNode> objects = JsonUtil.consumeObjectArrayProperty(json, "oneOf");
			if (objects != null) {
				objects.forEach(object -> {
					AsyncApi23Schema model = (AsyncApi23Schema) node.createSchema();
					this.readSchema(object, model);
					node.addOneOf(model);
				});
			}
		}
		{
			List<ObjectNode> objects = JsonUtil.consumeObjectArrayProperty(json, "anyOf");
			if (objects != null) {
				objects.forEach(object -> {
					AsyncApi23Schema model = (AsyncApi23Schema) node.createSchema();
					this.readSchema(object, model);
					node.addAnyOf(model);
				});
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "not");
			if (object != null) {
				node.setNot(node.createSchema());
				readSchema(object, (AsyncApi23Schema) node.getNot());
			}
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "description");
			node.setDescription(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "format");
			node.setFormat(value);
		}
		{
			JsonNode value = JsonUtil.consumeAnyProperty(json, "default");
			node.setDefault(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "discriminator");
			node.setDiscriminator(value);
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "externalDocs");
			if (object != null) {
				node.setExternalDocs(node.createExternalDocumentation());
				readExternalDocumentation(object, (AsyncApi23ExternalDocumentation) node.getExternalDocs());
			}
		}
		{
			Boolean value = JsonUtil.consumeBooleanProperty(json, "deprecated");
			node.setDeprecated(value);
		}
		{
			List<String> propertyNames = JsonUtil.matchingKeys("^x-.+$", json);
			propertyNames.forEach(name -> {
				JsonNode value = JsonUtil.consumeAnyProperty(json, name);
				node.addExtension(name, value);
			});
		}
		ReaderUtil.readExtraProperties(json, node);
	}

	public void readSecurityScheme(ObjectNode json, AsyncApi23SecurityScheme node) {
		{
			String value = JsonUtil.consumeStringProperty(json, "$ref");
			node.set$ref(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "type");
			node.setType(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "description");
			node.setDescription(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "name");
			node.setName(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "in");
			node.setIn(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "scheme");
			node.setScheme(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "bearerFormat");
			node.setBearerFormat(value);
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "flows");
			if (object != null) {
				node.setFlows(node.createOAuthFlows());
				readOAuthFlows(object, (AsyncApi23OAuthFlows) node.getFlows());
			}
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "openIdConnectUrl");
			node.setOpenIdConnectUrl(value);
		}
		{
			List<String> propertyNames = JsonUtil.matchingKeys("^x-.+$", json);
			propertyNames.forEach(name -> {
				JsonNode value = JsonUtil.consumeAnyProperty(json, name);
				node.addExtension(name, value);
			});
		}
		ReaderUtil.readExtraProperties(json, node);
	}

	public void readOAuthFlows(ObjectNode json, AsyncApi23OAuthFlows node) {
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "implicit");
			if (object != null) {
				node.setImplicit(node.createOAuthFlow());
				readOAuthFlow(object, (AsyncApi23OAuthFlow) node.getImplicit());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "password");
			if (object != null) {
				node.setPassword(node.createOAuthFlow());
				readOAuthFlow(object, (AsyncApi23OAuthFlow) node.getPassword());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "clientCredentials");
			if (object != null) {
				node.setClientCredentials(node.createOAuthFlow());
				readOAuthFlow(object, (AsyncApi23OAuthFlow) node.getClientCredentials());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "authorizationCode");
			if (object != null) {
				node.setAuthorizationCode(node.createOAuthFlow());
				readOAuthFlow(object, (AsyncApi23OAuthFlow) node.getAuthorizationCode());
			}
		}
		{
			List<String> propertyNames = JsonUtil.matchingKeys("^x-.+$", json);
			propertyNames.forEach(name -> {
				JsonNode value = JsonUtil.consumeAnyProperty(json, name);
				node.addExtension(name, value);
			});
		}
		ReaderUtil.readExtraProperties(json, node);
	}

	public void readOAuthFlow(ObjectNode json, AsyncApi23OAuthFlow node) {
		{
			String value = JsonUtil.consumeStringProperty(json, "authorizationUrl");
			node.setAuthorizationUrl(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "tokenUrl");
			node.setTokenUrl(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "refreshUrl");
			node.setRefreshUrl(value);
		}
		{
			Map<String, String> value = JsonUtil.consumeStringMapProperty(json, "scopes");
			node.setScopes(value);
		}
		{
			List<String> propertyNames = JsonUtil.matchingKeys("^x-.+$", json);
			propertyNames.forEach(name -> {
				JsonNode value = JsonUtil.consumeAnyProperty(json, name);
				node.addExtension(name, value);
			});
		}
		ReaderUtil.readExtraProperties(json, node);
	}

	public void readSecurityRequirement(ObjectNode json, AsyncApi23SecurityRequirement node) {
		{
			List<String> propertyNames = JsonUtil.keys(json);
			propertyNames.forEach(name -> {
				List<String> value = JsonUtil.consumeStringArrayProperty(json, name);
				node.addItem(name, value);
			});
		}
		ReaderUtil.readExtraProperties(json, node);
	}

	public void readCorrelationID(ObjectNode json, AsyncApi23CorrelationID node) {
		{
			String value = JsonUtil.consumeStringProperty(json, "$ref");
			node.set$ref(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "description");
			node.setDescription(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "location");
			node.setLocation(value);
		}
		{
			List<String> propertyNames = JsonUtil.matchingKeys("^x-.+$", json);
			propertyNames.forEach(name -> {
				JsonNode value = JsonUtil.consumeAnyProperty(json, name);
				node.addExtension(name, value);
			});
		}
		ReaderUtil.readExtraProperties(json, node);
	}

	public void readBinding(ObjectNode json, AsyncApi23Binding node) {
		{
			List<String> propertyNames = JsonUtil.keys(json);
			propertyNames.forEach(name -> {
				JsonNode value = JsonUtil.consumeAnyProperty(json, name);
				node.addItem(name, value);
			});
		}
		{
			List<String> propertyNames = JsonUtil.matchingKeys("^x-.+$", json);
			propertyNames.forEach(name -> {
				JsonNode value = JsonUtil.consumeAnyProperty(json, name);
				node.addExtension(name, value);
			});
		}
		ReaderUtil.readExtraProperties(json, node);
	}
}