package io.apicurio.datamodels.models.asyncapi.v2x.v25.io;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.apicurio.datamodels.models.RootNode;
import io.apicurio.datamodels.models.asyncapi.v2x.v25.AsyncApi25Binding;
import io.apicurio.datamodels.models.asyncapi.v2x.v25.AsyncApi25ChannelBindings;
import io.apicurio.datamodels.models.asyncapi.v2x.v25.AsyncApi25ChannelItem;
import io.apicurio.datamodels.models.asyncapi.v2x.v25.AsyncApi25Channels;
import io.apicurio.datamodels.models.asyncapi.v2x.v25.AsyncApi25Components;
import io.apicurio.datamodels.models.asyncapi.v2x.v25.AsyncApi25Contact;
import io.apicurio.datamodels.models.asyncapi.v2x.v25.AsyncApi25CorrelationID;
import io.apicurio.datamodels.models.asyncapi.v2x.v25.AsyncApi25Document;
import io.apicurio.datamodels.models.asyncapi.v2x.v25.AsyncApi25DocumentImpl;
import io.apicurio.datamodels.models.asyncapi.v2x.v25.AsyncApi25ExternalDocumentation;
import io.apicurio.datamodels.models.asyncapi.v2x.v25.AsyncApi25Info;
import io.apicurio.datamodels.models.asyncapi.v2x.v25.AsyncApi25License;
import io.apicurio.datamodels.models.asyncapi.v2x.v25.AsyncApi25Message;
import io.apicurio.datamodels.models.asyncapi.v2x.v25.AsyncApi25MessageBindings;
import io.apicurio.datamodels.models.asyncapi.v2x.v25.AsyncApi25MessageExample;
import io.apicurio.datamodels.models.asyncapi.v2x.v25.AsyncApi25MessageTrait;
import io.apicurio.datamodels.models.asyncapi.v2x.v25.AsyncApi25OAuthFlow;
import io.apicurio.datamodels.models.asyncapi.v2x.v25.AsyncApi25OAuthFlows;
import io.apicurio.datamodels.models.asyncapi.v2x.v25.AsyncApi25OneOfMessages;
import io.apicurio.datamodels.models.asyncapi.v2x.v25.AsyncApi25Operation;
import io.apicurio.datamodels.models.asyncapi.v2x.v25.AsyncApi25OperationBindings;
import io.apicurio.datamodels.models.asyncapi.v2x.v25.AsyncApi25OperationTrait;
import io.apicurio.datamodels.models.asyncapi.v2x.v25.AsyncApi25Parameter;
import io.apicurio.datamodels.models.asyncapi.v2x.v25.AsyncApi25Parameters;
import io.apicurio.datamodels.models.asyncapi.v2x.v25.AsyncApi25Schema;
import io.apicurio.datamodels.models.asyncapi.v2x.v25.AsyncApi25SecurityRequirement;
import io.apicurio.datamodels.models.asyncapi.v2x.v25.AsyncApi25SecurityScheme;
import io.apicurio.datamodels.models.asyncapi.v2x.v25.AsyncApi25Server;
import io.apicurio.datamodels.models.asyncapi.v2x.v25.AsyncApi25ServerBindings;
import io.apicurio.datamodels.models.asyncapi.v2x.v25.AsyncApi25ServerVariable;
import io.apicurio.datamodels.models.asyncapi.v2x.v25.AsyncApi25Servers;
import io.apicurio.datamodels.models.asyncapi.v2x.v25.AsyncApi25Tag;
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

public class AsyncApi25ModelReader implements ModelReader {

	public void readDocument(ObjectNode json, AsyncApi25Document node) {
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
				readInfo(object, (AsyncApi25Info) node.getInfo());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "servers");
			if (object != null) {
				node.setServers(node.createServers());
				readServers(object, (AsyncApi25Servers) node.getServers());
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
				readChannels(object, (AsyncApi25Channels) node.getChannels());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "components");
			if (object != null) {
				node.setComponents(node.createComponents());
				readComponents(object, (AsyncApi25Components) node.getComponents());
			}
		}
		{
			List<ObjectNode> objects = JsonUtil.consumeObjectArrayProperty(json, "tags");
			if (objects != null) {
				objects.forEach(object -> {
					AsyncApi25Tag model = (AsyncApi25Tag) node.createTag();
					this.readTag(object, model);
					node.addTag(model);
				});
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "externalDocs");
			if (object != null) {
				node.setExternalDocs(node.createExternalDocumentation());
				readExternalDocumentation(object, (AsyncApi25ExternalDocumentation) node.getExternalDocs());
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
		AsyncApi25Document rootModel = new AsyncApi25DocumentImpl();
		this.readDocument(json, rootModel);
		return rootModel;
	}

	public void readInfo(ObjectNode json, AsyncApi25Info node) {
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
				readContact(object, (AsyncApi25Contact) node.getContact());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "license");
			if (object != null) {
				node.setLicense(node.createLicense());
				readLicense(object, (AsyncApi25License) node.getLicense());
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

	public void readContact(ObjectNode json, AsyncApi25Contact node) {
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

	public void readLicense(ObjectNode json, AsyncApi25License node) {
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

	public void readServers(ObjectNode json, AsyncApi25Servers node) {
		{
			List<String> propertyNames = JsonUtil.keys(json);
			propertyNames.forEach(name -> {
				ObjectNode object = JsonUtil.consumeObjectProperty(json, name);
				if (object != null) {
					AsyncApi25Server model = (AsyncApi25Server) node.createServer();
					this.readServer(object, model);
					node.addItem(name, model);
				}
			});
		}
		ReaderUtil.readExtraProperties(json, node);
	}

	public void readServer(ObjectNode json, AsyncApi25Server node) {
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
					AsyncApi25ServerVariable model = (AsyncApi25ServerVariable) node.createServerVariable();
					this.readServerVariable(mapValue, model);
					node.addVariable(name, model);
				}
			});
		}
		{
			List<ObjectNode> objects = JsonUtil.consumeObjectArrayProperty(json, "security");
			if (objects != null) {
				objects.forEach(object -> {
					AsyncApi25SecurityRequirement model = (AsyncApi25SecurityRequirement) node
							.createSecurityRequirement();
					this.readSecurityRequirement(object, model);
					node.addSecurity(model);
				});
			}
		}
		{
			List<ObjectNode> objects = JsonUtil.consumeObjectArrayProperty(json, "tags");
			if (objects != null) {
				objects.forEach(object -> {
					AsyncApi25Tag model = (AsyncApi25Tag) node.createTag();
					this.readTag(object, model);
					node.addTag(model);
				});
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "bindings");
			if (object != null) {
				node.setBindings(node.createServerBindings());
				readServerBindings(object, (AsyncApi25ServerBindings) node.getBindings());
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

	public void readServerVariable(ObjectNode json, AsyncApi25ServerVariable node) {
		{
			String value = JsonUtil.consumeStringProperty(json, "$ref");
			node.set$ref(value);
		}
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

	public void readChannels(ObjectNode json, AsyncApi25Channels node) {
		{
			List<String> propertyNames = JsonUtil.keys(json);
			propertyNames.forEach(name -> {
				ObjectNode object = JsonUtil.consumeObjectProperty(json, name);
				if (object != null) {
					AsyncApi25ChannelItem model = (AsyncApi25ChannelItem) node.createChannelItem();
					this.readChannelItem(object, model);
					node.addItem(name, model);
				}
			});
		}
		ReaderUtil.readExtraProperties(json, node);
	}

	public void readChannelItem(ObjectNode json, AsyncApi25ChannelItem node) {
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
				readOperation(object, (AsyncApi25Operation) node.getSubscribe());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "publish");
			if (object != null) {
				node.setPublish(node.createOperation());
				readOperation(object, (AsyncApi25Operation) node.getPublish());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "parameters");
			if (object != null) {
				node.setParameters(node.createParameters());
				readParameters(object, (AsyncApi25Parameters) node.getParameters());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "bindings");
			if (object != null) {
				node.setBindings(node.createChannelBindings());
				readChannelBindings(object, (AsyncApi25ChannelBindings) node.getBindings());
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

	public void readOperation(ObjectNode json, AsyncApi25Operation node) {
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
			List<ObjectNode> objects = JsonUtil.consumeObjectArrayProperty(json, "security");
			if (objects != null) {
				objects.forEach(object -> {
					AsyncApi25SecurityRequirement model = (AsyncApi25SecurityRequirement) node
							.createSecurityRequirement();
					this.readSecurityRequirement(object, model);
					node.addSecurity(model);
				});
			}
		}
		{
			List<ObjectNode> objects = JsonUtil.consumeObjectArrayProperty(json, "tags");
			if (objects != null) {
				objects.forEach(object -> {
					AsyncApi25Tag model = (AsyncApi25Tag) node.createTag();
					this.readTag(object, model);
					node.addTag(model);
				});
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "externalDocs");
			if (object != null) {
				node.setExternalDocs(node.createExternalDocumentation());
				readExternalDocumentation(object, (AsyncApi25ExternalDocumentation) node.getExternalDocs());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "bindings");
			if (object != null) {
				node.setBindings(node.createOperationBindings());
				readOperationBindings(object, (AsyncApi25OperationBindings) node.getBindings());
			}
		}
		{
			List<ObjectNode> objects = JsonUtil.consumeObjectArrayProperty(json, "traits");
			if (objects != null) {
				objects.forEach(object -> {
					AsyncApi25OperationTrait model = (AsyncApi25OperationTrait) node.createOperationTrait();
					this.readOperationTrait(object, model);
					node.addTrait(model);
				});
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "message");
			if (object != null) {
				node.setMessage(node.createMessage());
				readMessage(object, (AsyncApi25Message) node.getMessage());
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

	public void readOperationTrait(ObjectNode json, AsyncApi25OperationTrait node) {
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
			List<ObjectNode> objects = JsonUtil.consumeObjectArrayProperty(json, "security");
			if (objects != null) {
				objects.forEach(object -> {
					AsyncApi25SecurityRequirement model = (AsyncApi25SecurityRequirement) node
							.createSecurityRequirement();
					this.readSecurityRequirement(object, model);
					node.addSecurity(model);
				});
			}
		}
		{
			List<ObjectNode> objects = JsonUtil.consumeObjectArrayProperty(json, "tags");
			if (objects != null) {
				objects.forEach(object -> {
					AsyncApi25Tag model = (AsyncApi25Tag) node.createTag();
					this.readTag(object, model);
					node.addTag(model);
				});
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "externalDocs");
			if (object != null) {
				node.setExternalDocs(node.createExternalDocumentation());
				readExternalDocumentation(object, (AsyncApi25ExternalDocumentation) node.getExternalDocs());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "bindings");
			if (object != null) {
				node.setBindings(node.createOperationBindings());
				readOperationBindings(object, (AsyncApi25OperationBindings) node.getBindings());
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

	public void readParameters(ObjectNode json, AsyncApi25Parameters node) {
		{
			List<String> propertyNames = JsonUtil.keys(json);
			propertyNames.forEach(name -> {
				ObjectNode object = JsonUtil.consumeObjectProperty(json, name);
				if (object != null) {
					AsyncApi25Parameter model = (AsyncApi25Parameter) node.createParameter();
					this.readParameter(object, model);
					node.addItem(name, model);
				}
			});
		}
		ReaderUtil.readExtraProperties(json, node);
	}

	public void readParameter(ObjectNode json, AsyncApi25Parameter node) {
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
				readSchema(object, (AsyncApi25Schema) node.getSchema());
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

	public void readServerBindings(ObjectNode json, AsyncApi25ServerBindings node) {
		{
			String value = JsonUtil.consumeStringProperty(json, "$ref");
			node.set$ref(value);
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "http");
			if (object != null) {
				node.setHttp(node.createBinding());
				readBinding(object, (AsyncApi25Binding) node.getHttp());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "ws");
			if (object != null) {
				node.setWs(node.createBinding());
				readBinding(object, (AsyncApi25Binding) node.getWs());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "kafka");
			if (object != null) {
				node.setKafka(node.createBinding());
				readBinding(object, (AsyncApi25Binding) node.getKafka());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "anypointmq");
			if (object != null) {
				node.setAnypointmq(node.createBinding());
				readBinding(object, (AsyncApi25Binding) node.getAnypointmq());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "amqp");
			if (object != null) {
				node.setAmqp(node.createBinding());
				readBinding(object, (AsyncApi25Binding) node.getAmqp());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "amqp1");
			if (object != null) {
				node.setAmqp1(node.createBinding());
				readBinding(object, (AsyncApi25Binding) node.getAmqp1());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "mqtt");
			if (object != null) {
				node.setMqtt(node.createBinding());
				readBinding(object, (AsyncApi25Binding) node.getMqtt());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "mqtt5");
			if (object != null) {
				node.setMqtt5(node.createBinding());
				readBinding(object, (AsyncApi25Binding) node.getMqtt5());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "nats");
			if (object != null) {
				node.setNats(node.createBinding());
				readBinding(object, (AsyncApi25Binding) node.getNats());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "jms");
			if (object != null) {
				node.setJms(node.createBinding());
				readBinding(object, (AsyncApi25Binding) node.getJms());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "sns");
			if (object != null) {
				node.setSns(node.createBinding());
				readBinding(object, (AsyncApi25Binding) node.getSns());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "solace");
			if (object != null) {
				node.setSolace(node.createBinding());
				readBinding(object, (AsyncApi25Binding) node.getSolace());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "sqs");
			if (object != null) {
				node.setSqs(node.createBinding());
				readBinding(object, (AsyncApi25Binding) node.getSqs());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "stomp");
			if (object != null) {
				node.setStomp(node.createBinding());
				readBinding(object, (AsyncApi25Binding) node.getStomp());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "redis");
			if (object != null) {
				node.setRedis(node.createBinding());
				readBinding(object, (AsyncApi25Binding) node.getRedis());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "mercure");
			if (object != null) {
				node.setMercure(node.createBinding());
				readBinding(object, (AsyncApi25Binding) node.getMercure());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "ibmmq");
			if (object != null) {
				node.setIbmmq(node.createBinding());
				readBinding(object, (AsyncApi25Binding) node.getIbmmq());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "googlepubsub");
			if (object != null) {
				node.setGooglepubsub(node.createBinding());
				readBinding(object, (AsyncApi25Binding) node.getGooglepubsub());
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

	public void readChannelBindings(ObjectNode json, AsyncApi25ChannelBindings node) {
		{
			String value = JsonUtil.consumeStringProperty(json, "$ref");
			node.set$ref(value);
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "http");
			if (object != null) {
				node.setHttp(node.createBinding());
				readBinding(object, (AsyncApi25Binding) node.getHttp());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "ws");
			if (object != null) {
				node.setWs(node.createBinding());
				readBinding(object, (AsyncApi25Binding) node.getWs());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "kafka");
			if (object != null) {
				node.setKafka(node.createBinding());
				readBinding(object, (AsyncApi25Binding) node.getKafka());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "anypointmq");
			if (object != null) {
				node.setAnypointmq(node.createBinding());
				readBinding(object, (AsyncApi25Binding) node.getAnypointmq());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "amqp");
			if (object != null) {
				node.setAmqp(node.createBinding());
				readBinding(object, (AsyncApi25Binding) node.getAmqp());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "amqp1");
			if (object != null) {
				node.setAmqp1(node.createBinding());
				readBinding(object, (AsyncApi25Binding) node.getAmqp1());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "mqtt");
			if (object != null) {
				node.setMqtt(node.createBinding());
				readBinding(object, (AsyncApi25Binding) node.getMqtt());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "mqtt5");
			if (object != null) {
				node.setMqtt5(node.createBinding());
				readBinding(object, (AsyncApi25Binding) node.getMqtt5());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "nats");
			if (object != null) {
				node.setNats(node.createBinding());
				readBinding(object, (AsyncApi25Binding) node.getNats());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "jms");
			if (object != null) {
				node.setJms(node.createBinding());
				readBinding(object, (AsyncApi25Binding) node.getJms());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "sns");
			if (object != null) {
				node.setSns(node.createBinding());
				readBinding(object, (AsyncApi25Binding) node.getSns());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "solace");
			if (object != null) {
				node.setSolace(node.createBinding());
				readBinding(object, (AsyncApi25Binding) node.getSolace());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "sqs");
			if (object != null) {
				node.setSqs(node.createBinding());
				readBinding(object, (AsyncApi25Binding) node.getSqs());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "stomp");
			if (object != null) {
				node.setStomp(node.createBinding());
				readBinding(object, (AsyncApi25Binding) node.getStomp());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "redis");
			if (object != null) {
				node.setRedis(node.createBinding());
				readBinding(object, (AsyncApi25Binding) node.getRedis());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "mercure");
			if (object != null) {
				node.setMercure(node.createBinding());
				readBinding(object, (AsyncApi25Binding) node.getMercure());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "ibmmq");
			if (object != null) {
				node.setIbmmq(node.createBinding());
				readBinding(object, (AsyncApi25Binding) node.getIbmmq());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "googlepubsub");
			if (object != null) {
				node.setGooglepubsub(node.createBinding());
				readBinding(object, (AsyncApi25Binding) node.getGooglepubsub());
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

	public void readOperationBindings(ObjectNode json, AsyncApi25OperationBindings node) {
		{
			String value = JsonUtil.consumeStringProperty(json, "$ref");
			node.set$ref(value);
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "http");
			if (object != null) {
				node.setHttp(node.createBinding());
				readBinding(object, (AsyncApi25Binding) node.getHttp());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "ws");
			if (object != null) {
				node.setWs(node.createBinding());
				readBinding(object, (AsyncApi25Binding) node.getWs());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "kafka");
			if (object != null) {
				node.setKafka(node.createBinding());
				readBinding(object, (AsyncApi25Binding) node.getKafka());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "anypointmq");
			if (object != null) {
				node.setAnypointmq(node.createBinding());
				readBinding(object, (AsyncApi25Binding) node.getAnypointmq());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "amqp");
			if (object != null) {
				node.setAmqp(node.createBinding());
				readBinding(object, (AsyncApi25Binding) node.getAmqp());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "amqp1");
			if (object != null) {
				node.setAmqp1(node.createBinding());
				readBinding(object, (AsyncApi25Binding) node.getAmqp1());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "mqtt");
			if (object != null) {
				node.setMqtt(node.createBinding());
				readBinding(object, (AsyncApi25Binding) node.getMqtt());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "mqtt5");
			if (object != null) {
				node.setMqtt5(node.createBinding());
				readBinding(object, (AsyncApi25Binding) node.getMqtt5());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "nats");
			if (object != null) {
				node.setNats(node.createBinding());
				readBinding(object, (AsyncApi25Binding) node.getNats());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "jms");
			if (object != null) {
				node.setJms(node.createBinding());
				readBinding(object, (AsyncApi25Binding) node.getJms());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "sns");
			if (object != null) {
				node.setSns(node.createBinding());
				readBinding(object, (AsyncApi25Binding) node.getSns());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "solace");
			if (object != null) {
				node.setSolace(node.createBinding());
				readBinding(object, (AsyncApi25Binding) node.getSolace());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "sqs");
			if (object != null) {
				node.setSqs(node.createBinding());
				readBinding(object, (AsyncApi25Binding) node.getSqs());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "stomp");
			if (object != null) {
				node.setStomp(node.createBinding());
				readBinding(object, (AsyncApi25Binding) node.getStomp());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "redis");
			if (object != null) {
				node.setRedis(node.createBinding());
				readBinding(object, (AsyncApi25Binding) node.getRedis());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "mercure");
			if (object != null) {
				node.setMercure(node.createBinding());
				readBinding(object, (AsyncApi25Binding) node.getMercure());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "ibmmq");
			if (object != null) {
				node.setIbmmq(node.createBinding());
				readBinding(object, (AsyncApi25Binding) node.getIbmmq());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "googlepubsub");
			if (object != null) {
				node.setGooglepubsub(node.createBinding());
				readBinding(object, (AsyncApi25Binding) node.getGooglepubsub());
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

	public void readMessageBindings(ObjectNode json, AsyncApi25MessageBindings node) {
		{
			String value = JsonUtil.consumeStringProperty(json, "$ref");
			node.set$ref(value);
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "http");
			if (object != null) {
				node.setHttp(node.createBinding());
				readBinding(object, (AsyncApi25Binding) node.getHttp());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "ws");
			if (object != null) {
				node.setWs(node.createBinding());
				readBinding(object, (AsyncApi25Binding) node.getWs());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "kafka");
			if (object != null) {
				node.setKafka(node.createBinding());
				readBinding(object, (AsyncApi25Binding) node.getKafka());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "anypointmq");
			if (object != null) {
				node.setAnypointmq(node.createBinding());
				readBinding(object, (AsyncApi25Binding) node.getAnypointmq());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "amqp");
			if (object != null) {
				node.setAmqp(node.createBinding());
				readBinding(object, (AsyncApi25Binding) node.getAmqp());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "amqp1");
			if (object != null) {
				node.setAmqp1(node.createBinding());
				readBinding(object, (AsyncApi25Binding) node.getAmqp1());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "mqtt");
			if (object != null) {
				node.setMqtt(node.createBinding());
				readBinding(object, (AsyncApi25Binding) node.getMqtt());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "mqtt5");
			if (object != null) {
				node.setMqtt5(node.createBinding());
				readBinding(object, (AsyncApi25Binding) node.getMqtt5());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "nats");
			if (object != null) {
				node.setNats(node.createBinding());
				readBinding(object, (AsyncApi25Binding) node.getNats());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "jms");
			if (object != null) {
				node.setJms(node.createBinding());
				readBinding(object, (AsyncApi25Binding) node.getJms());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "sns");
			if (object != null) {
				node.setSns(node.createBinding());
				readBinding(object, (AsyncApi25Binding) node.getSns());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "solace");
			if (object != null) {
				node.setSolace(node.createBinding());
				readBinding(object, (AsyncApi25Binding) node.getSolace());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "sqs");
			if (object != null) {
				node.setSqs(node.createBinding());
				readBinding(object, (AsyncApi25Binding) node.getSqs());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "stomp");
			if (object != null) {
				node.setStomp(node.createBinding());
				readBinding(object, (AsyncApi25Binding) node.getStomp());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "redis");
			if (object != null) {
				node.setRedis(node.createBinding());
				readBinding(object, (AsyncApi25Binding) node.getRedis());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "mercure");
			if (object != null) {
				node.setMercure(node.createBinding());
				readBinding(object, (AsyncApi25Binding) node.getMercure());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "ibmmq");
			if (object != null) {
				node.setIbmmq(node.createBinding());
				readBinding(object, (AsyncApi25Binding) node.getIbmmq());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "googlepubsub");
			if (object != null) {
				node.setGooglepubsub(node.createBinding());
				readBinding(object, (AsyncApi25Binding) node.getGooglepubsub());
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

	public void readOneOfMessages(ObjectNode json, AsyncApi25OneOfMessages node) {
		{
			List<ObjectNode> objects = JsonUtil.consumeObjectArrayProperty(json, "oneOf");
			if (objects != null) {
				objects.forEach(object -> {
					AsyncApi25Message model = (AsyncApi25Message) node.createMessage();
					this.readMessage(object, model);
					node.addOneOf(model);
				});
			}
		}
		ReaderUtil.readExtraProperties(json, node);
	}

	public void readMessage(ObjectNode json, AsyncApi25Message node) {
		{
			String value = JsonUtil.consumeStringProperty(json, "$ref");
			node.set$ref(value);
		}
		{
			List<ObjectNode> objects = JsonUtil.consumeObjectArrayProperty(json, "oneOf");
			if (objects != null) {
				objects.forEach(object -> {
					AsyncApi25Message model = (AsyncApi25Message) node.createMessage();
					this.readMessage(object, model);
					node.addOneOf(model);
				});
			}
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "messageId");
			node.setMessageId(value);
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "headers");
			if (object != null) {
				node.setHeaders(node.createSchema());
				readSchema(object, (AsyncApi25Schema) node.getHeaders());
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
				readCorrelationID(object, (AsyncApi25CorrelationID) node.getCorrelationId());
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
					AsyncApi25Tag model = (AsyncApi25Tag) node.createTag();
					this.readTag(object, model);
					node.addTag(model);
				});
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "externalDocs");
			if (object != null) {
				node.setExternalDocs(node.createExternalDocumentation());
				readExternalDocumentation(object, (AsyncApi25ExternalDocumentation) node.getExternalDocs());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "bindings");
			if (object != null) {
				node.setBindings(node.createMessageBindings());
				readMessageBindings(object, (AsyncApi25MessageBindings) node.getBindings());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "examples");
			if (object != null) {
				node.setExamples(node.createMessageExample());
				readMessageExample(object, (AsyncApi25MessageExample) node.getExamples());
			}
		}
		{
			List<ObjectNode> objects = JsonUtil.consumeObjectArrayProperty(json, "traits");
			if (objects != null) {
				objects.forEach(object -> {
					AsyncApi25MessageTrait model = (AsyncApi25MessageTrait) node.createMessageTrait();
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

	public void readMessageTrait(ObjectNode json, AsyncApi25MessageTrait node) {
		{
			String value = JsonUtil.consumeStringProperty(json, "$ref");
			node.set$ref(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "messageId");
			node.setMessageId(value);
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "headers");
			if (object != null) {
				node.setHeaders(node.createSchema());
				readSchema(object, (AsyncApi25Schema) node.getHeaders());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "correlationId");
			if (object != null) {
				node.setCorrelationId(node.createCorrelationID());
				readCorrelationID(object, (AsyncApi25CorrelationID) node.getCorrelationId());
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
					AsyncApi25Tag model = (AsyncApi25Tag) node.createTag();
					this.readTag(object, model);
					node.addTag(model);
				});
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "externalDocs");
			if (object != null) {
				node.setExternalDocs(node.createExternalDocumentation());
				readExternalDocumentation(object, (AsyncApi25ExternalDocumentation) node.getExternalDocs());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "bindings");
			if (object != null) {
				node.setBindings(node.createMessageBindings());
				readMessageBindings(object, (AsyncApi25MessageBindings) node.getBindings());
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

	public void readMessageExample(ObjectNode json, AsyncApi25MessageExample node) {
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

	public void readTag(ObjectNode json, AsyncApi25Tag node) {
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
				readExternalDocumentation(object, (AsyncApi25ExternalDocumentation) node.getExternalDocs());
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

	public void readExternalDocumentation(ObjectNode json, AsyncApi25ExternalDocumentation node) {
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

	public void readComponents(ObjectNode json, AsyncApi25Components node) {
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "schemas");
			JsonUtil.keys(object).forEach(name -> {
				ObjectNode mapValue = JsonUtil.consumeObjectProperty(object, name);
				if (mapValue != null) {
					AsyncApi25Schema model = (AsyncApi25Schema) node.createSchema();
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
					AsyncApi25Server model = (AsyncApi25Server) node.createServer();
					this.readServer(mapValue, model);
					node.addServer(name, model);
				}
			});
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "serverVariables");
			JsonUtil.keys(object).forEach(name -> {
				ObjectNode mapValue = JsonUtil.consumeObjectProperty(object, name);
				if (mapValue != null) {
					AsyncApi25ServerVariable model = (AsyncApi25ServerVariable) node.createServerVariable();
					this.readServerVariable(mapValue, model);
					node.addServerVariable(name, model);
				}
			});
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "channels");
			JsonUtil.keys(object).forEach(name -> {
				ObjectNode mapValue = JsonUtil.consumeObjectProperty(object, name);
				if (mapValue != null) {
					AsyncApi25ChannelItem model = (AsyncApi25ChannelItem) node.createChannelItem();
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
					AsyncApi25Message model = (AsyncApi25Message) node.createMessage();
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
					AsyncApi25SecurityScheme model = (AsyncApi25SecurityScheme) node.createSecurityScheme();
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
					AsyncApi25Parameter model = (AsyncApi25Parameter) node.createParameter();
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
					AsyncApi25CorrelationID model = (AsyncApi25CorrelationID) node.createCorrelationID();
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
					AsyncApi25OperationTrait model = (AsyncApi25OperationTrait) node.createOperationTrait();
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
					AsyncApi25MessageTrait model = (AsyncApi25MessageTrait) node.createMessageTrait();
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
					AsyncApi25ServerBindings model = (AsyncApi25ServerBindings) node.createServerBindings();
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
					AsyncApi25ChannelBindings model = (AsyncApi25ChannelBindings) node.createChannelBindings();
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
					AsyncApi25OperationBindings model = (AsyncApi25OperationBindings) node.createOperationBindings();
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
					AsyncApi25MessageBindings model = (AsyncApi25MessageBindings) node.createMessageBindings();
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

	public void readSchema(ObjectNode json, AsyncApi25Schema node) {
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
				readSchema(object, (AsyncApi25Schema) node.getIf());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "then");
			if (object != null) {
				node.setThen(node.createSchema());
				readSchema(object, (AsyncApi25Schema) node.getThen());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "else");
			if (object != null) {
				node.setElse(node.createSchema());
				readSchema(object, (AsyncApi25Schema) node.getElse());
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
					AsyncApi25Schema model = (AsyncApi25Schema) node.createSchema();
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
					readSchema(object, (AsyncApi25Schema) node.getAdditionalProperties());
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
				readSchema(object, (AsyncApi25Schema) node.getAdditionalItems());
			}
		}
		{
			JsonNode value = JsonUtil.consumeAnyProperty(json, "items");
			if (value != null) {
				if (JsonUtil.isObject(value)) {
					ObjectNode object = JsonUtil.toObject(value);
					node.setItems(node.createSchema());
					readSchema(object, (AsyncApi25Schema) node.getItems());
				} else if (JsonUtil.isArray(value)) {
					List<JsonNode> array = JsonUtil.toList(value);
					List<AsyncApi25Schema> models = new ArrayList<>();
					array.forEach(item -> {
						ObjectNode object = JsonUtil.toObject(item);
						AsyncApi25Schema model = (AsyncApi25Schema) node.createSchema();
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
				readSchema(object, (AsyncApi25Schema) node.getPropertyNames());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "contains");
			if (object != null) {
				node.setContains(node.createSchema());
				readSchema(object, (AsyncApi25Schema) node.getContains());
			}
		}
		{
			List<ObjectNode> objects = JsonUtil.consumeObjectArrayProperty(json, "allOf");
			if (objects != null) {
				objects.forEach(object -> {
					AsyncApi25Schema model = (AsyncApi25Schema) node.createSchema();
					this.readSchema(object, model);
					node.addAllOf(model);
				});
			}
		}
		{
			List<ObjectNode> objects = JsonUtil.consumeObjectArrayProperty(json, "oneOf");
			if (objects != null) {
				objects.forEach(object -> {
					AsyncApi25Schema model = (AsyncApi25Schema) node.createSchema();
					this.readSchema(object, model);
					node.addOneOf(model);
				});
			}
		}
		{
			List<ObjectNode> objects = JsonUtil.consumeObjectArrayProperty(json, "anyOf");
			if (objects != null) {
				objects.forEach(object -> {
					AsyncApi25Schema model = (AsyncApi25Schema) node.createSchema();
					this.readSchema(object, model);
					node.addAnyOf(model);
				});
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "not");
			if (object != null) {
				node.setNot(node.createSchema());
				readSchema(object, (AsyncApi25Schema) node.getNot());
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
				readExternalDocumentation(object, (AsyncApi25ExternalDocumentation) node.getExternalDocs());
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

	public void readSecurityScheme(ObjectNode json, AsyncApi25SecurityScheme node) {
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
				readOAuthFlows(object, (AsyncApi25OAuthFlows) node.getFlows());
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

	public void readOAuthFlows(ObjectNode json, AsyncApi25OAuthFlows node) {
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "implicit");
			if (object != null) {
				node.setImplicit(node.createOAuthFlow());
				readOAuthFlow(object, (AsyncApi25OAuthFlow) node.getImplicit());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "password");
			if (object != null) {
				node.setPassword(node.createOAuthFlow());
				readOAuthFlow(object, (AsyncApi25OAuthFlow) node.getPassword());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "clientCredentials");
			if (object != null) {
				node.setClientCredentials(node.createOAuthFlow());
				readOAuthFlow(object, (AsyncApi25OAuthFlow) node.getClientCredentials());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "authorizationCode");
			if (object != null) {
				node.setAuthorizationCode(node.createOAuthFlow());
				readOAuthFlow(object, (AsyncApi25OAuthFlow) node.getAuthorizationCode());
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

	public void readOAuthFlow(ObjectNode json, AsyncApi25OAuthFlow node) {
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

	public void readSecurityRequirement(ObjectNode json, AsyncApi25SecurityRequirement node) {
		{
			List<String> propertyNames = JsonUtil.keys(json);
			propertyNames.forEach(name -> {
				List<String> value = JsonUtil.consumeStringArrayProperty(json, name);
				node.addItem(name, value);
			});
		}
		ReaderUtil.readExtraProperties(json, node);
	}

	public void readCorrelationID(ObjectNode json, AsyncApi25CorrelationID node) {
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

	public void readBinding(ObjectNode json, AsyncApi25Binding node) {
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