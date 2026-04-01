package io.apicurio.datamodels.models.asyncapi.v2x.v22.io;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.apicurio.datamodels.models.RootNode;
import io.apicurio.datamodels.models.asyncapi.v2x.v22.AsyncApi22Binding;
import io.apicurio.datamodels.models.asyncapi.v2x.v22.AsyncApi22ChannelBindings;
import io.apicurio.datamodels.models.asyncapi.v2x.v22.AsyncApi22ChannelItem;
import io.apicurio.datamodels.models.asyncapi.v2x.v22.AsyncApi22Channels;
import io.apicurio.datamodels.models.asyncapi.v2x.v22.AsyncApi22Components;
import io.apicurio.datamodels.models.asyncapi.v2x.v22.AsyncApi22Contact;
import io.apicurio.datamodels.models.asyncapi.v2x.v22.AsyncApi22CorrelationID;
import io.apicurio.datamodels.models.asyncapi.v2x.v22.AsyncApi22Document;
import io.apicurio.datamodels.models.asyncapi.v2x.v22.AsyncApi22DocumentImpl;
import io.apicurio.datamodels.models.asyncapi.v2x.v22.AsyncApi22ExternalDocumentation;
import io.apicurio.datamodels.models.asyncapi.v2x.v22.AsyncApi22Info;
import io.apicurio.datamodels.models.asyncapi.v2x.v22.AsyncApi22License;
import io.apicurio.datamodels.models.asyncapi.v2x.v22.AsyncApi22Message;
import io.apicurio.datamodels.models.asyncapi.v2x.v22.AsyncApi22MessageBindings;
import io.apicurio.datamodels.models.asyncapi.v2x.v22.AsyncApi22MessageExample;
import io.apicurio.datamodels.models.asyncapi.v2x.v22.AsyncApi22MessageTrait;
import io.apicurio.datamodels.models.asyncapi.v2x.v22.AsyncApi22OAuthFlow;
import io.apicurio.datamodels.models.asyncapi.v2x.v22.AsyncApi22OAuthFlows;
import io.apicurio.datamodels.models.asyncapi.v2x.v22.AsyncApi22OneOfMessages;
import io.apicurio.datamodels.models.asyncapi.v2x.v22.AsyncApi22Operation;
import io.apicurio.datamodels.models.asyncapi.v2x.v22.AsyncApi22OperationBindings;
import io.apicurio.datamodels.models.asyncapi.v2x.v22.AsyncApi22OperationTrait;
import io.apicurio.datamodels.models.asyncapi.v2x.v22.AsyncApi22Parameter;
import io.apicurio.datamodels.models.asyncapi.v2x.v22.AsyncApi22Parameters;
import io.apicurio.datamodels.models.asyncapi.v2x.v22.AsyncApi22Schema;
import io.apicurio.datamodels.models.asyncapi.v2x.v22.AsyncApi22SecurityRequirement;
import io.apicurio.datamodels.models.asyncapi.v2x.v22.AsyncApi22SecurityScheme;
import io.apicurio.datamodels.models.asyncapi.v2x.v22.AsyncApi22Server;
import io.apicurio.datamodels.models.asyncapi.v2x.v22.AsyncApi22ServerBindings;
import io.apicurio.datamodels.models.asyncapi.v2x.v22.AsyncApi22ServerVariable;
import io.apicurio.datamodels.models.asyncapi.v2x.v22.AsyncApi22Servers;
import io.apicurio.datamodels.models.asyncapi.v2x.v22.AsyncApi22Tag;
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

public class AsyncApi22ModelReader implements ModelReader {

	public void readDocument(ObjectNode json, AsyncApi22Document node) {
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
				readInfo(object, (AsyncApi22Info) node.getInfo());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "servers");
			if (object != null) {
				node.setServers(node.createServers());
				readServers(object, (AsyncApi22Servers) node.getServers());
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
				readChannels(object, (AsyncApi22Channels) node.getChannels());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "components");
			if (object != null) {
				node.setComponents(node.createComponents());
				readComponents(object, (AsyncApi22Components) node.getComponents());
			}
		}
		{
			List<ObjectNode> objects = JsonUtil.consumeObjectArrayProperty(json, "tags");
			if (objects != null) {
				objects.forEach(object -> {
					AsyncApi22Tag model = (AsyncApi22Tag) node.createTag();
					this.readTag(object, model);
					node.addTag(model);
				});
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "externalDocs");
			if (object != null) {
				node.setExternalDocs(node.createExternalDocumentation());
				readExternalDocumentation(object, (AsyncApi22ExternalDocumentation) node.getExternalDocs());
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
		AsyncApi22Document rootModel = new AsyncApi22DocumentImpl();
		this.readDocument(json, rootModel);
		return rootModel;
	}

	public void readInfo(ObjectNode json, AsyncApi22Info node) {
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
				readContact(object, (AsyncApi22Contact) node.getContact());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "license");
			if (object != null) {
				node.setLicense(node.createLicense());
				readLicense(object, (AsyncApi22License) node.getLicense());
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

	public void readContact(ObjectNode json, AsyncApi22Contact node) {
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

	public void readLicense(ObjectNode json, AsyncApi22License node) {
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

	public void readServers(ObjectNode json, AsyncApi22Servers node) {
		{
			List<String> propertyNames = JsonUtil.keys(json);
			propertyNames.forEach(name -> {
				ObjectNode object = JsonUtil.consumeObjectProperty(json, name);
				if (object != null) {
					AsyncApi22Server model = (AsyncApi22Server) node.createServer();
					this.readServer(object, model);
					node.addItem(name, model);
				}
			});
		}
		ReaderUtil.readExtraProperties(json, node);
	}

	public void readServer(ObjectNode json, AsyncApi22Server node) {
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
					AsyncApi22ServerVariable model = (AsyncApi22ServerVariable) node.createServerVariable();
					this.readServerVariable(mapValue, model);
					node.addVariable(name, model);
				}
			});
		}
		{
			List<ObjectNode> objects = JsonUtil.consumeObjectArrayProperty(json, "security");
			if (objects != null) {
				objects.forEach(object -> {
					AsyncApi22SecurityRequirement model = (AsyncApi22SecurityRequirement) node
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
				readServerBindings(object, (AsyncApi22ServerBindings) node.getBindings());
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

	public void readServerVariable(ObjectNode json, AsyncApi22ServerVariable node) {
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

	public void readChannels(ObjectNode json, AsyncApi22Channels node) {
		{
			List<String> propertyNames = JsonUtil.keys(json);
			propertyNames.forEach(name -> {
				ObjectNode object = JsonUtil.consumeObjectProperty(json, name);
				if (object != null) {
					AsyncApi22ChannelItem model = (AsyncApi22ChannelItem) node.createChannelItem();
					this.readChannelItem(object, model);
					node.addItem(name, model);
				}
			});
		}
		ReaderUtil.readExtraProperties(json, node);
	}

	public void readChannelItem(ObjectNode json, AsyncApi22ChannelItem node) {
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
				readOperation(object, (AsyncApi22Operation) node.getSubscribe());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "publish");
			if (object != null) {
				node.setPublish(node.createOperation());
				readOperation(object, (AsyncApi22Operation) node.getPublish());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "parameters");
			if (object != null) {
				node.setParameters(node.createParameters());
				readParameters(object, (AsyncApi22Parameters) node.getParameters());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "bindings");
			if (object != null) {
				node.setBindings(node.createChannelBindings());
				readChannelBindings(object, (AsyncApi22ChannelBindings) node.getBindings());
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

	public void readOperation(ObjectNode json, AsyncApi22Operation node) {
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
					AsyncApi22Tag model = (AsyncApi22Tag) node.createTag();
					this.readTag(object, model);
					node.addTag(model);
				});
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "externalDocs");
			if (object != null) {
				node.setExternalDocs(node.createExternalDocumentation());
				readExternalDocumentation(object, (AsyncApi22ExternalDocumentation) node.getExternalDocs());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "bindings");
			if (object != null) {
				node.setBindings(node.createOperationBindings());
				readOperationBindings(object, (AsyncApi22OperationBindings) node.getBindings());
			}
		}
		{
			List<ObjectNode> objects = JsonUtil.consumeObjectArrayProperty(json, "traits");
			if (objects != null) {
				objects.forEach(object -> {
					AsyncApi22OperationTrait model = (AsyncApi22OperationTrait) node.createOperationTrait();
					this.readOperationTrait(object, model);
					node.addTrait(model);
				});
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "message");
			if (object != null) {
				node.setMessage(node.createMessage());
				readMessage(object, (AsyncApi22Message) node.getMessage());
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

	public void readOperationTrait(ObjectNode json, AsyncApi22OperationTrait node) {
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
					AsyncApi22Tag model = (AsyncApi22Tag) node.createTag();
					this.readTag(object, model);
					node.addTag(model);
				});
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "externalDocs");
			if (object != null) {
				node.setExternalDocs(node.createExternalDocumentation());
				readExternalDocumentation(object, (AsyncApi22ExternalDocumentation) node.getExternalDocs());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "bindings");
			if (object != null) {
				node.setBindings(node.createOperationBindings());
				readOperationBindings(object, (AsyncApi22OperationBindings) node.getBindings());
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

	public void readParameters(ObjectNode json, AsyncApi22Parameters node) {
		{
			List<String> propertyNames = JsonUtil.keys(json);
			propertyNames.forEach(name -> {
				ObjectNode object = JsonUtil.consumeObjectProperty(json, name);
				if (object != null) {
					AsyncApi22Parameter model = (AsyncApi22Parameter) node.createParameter();
					this.readParameter(object, model);
					node.addItem(name, model);
				}
			});
		}
		ReaderUtil.readExtraProperties(json, node);
	}

	public void readParameter(ObjectNode json, AsyncApi22Parameter node) {
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
				readSchema(object, (AsyncApi22Schema) node.getSchema());
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

	public void readServerBindings(ObjectNode json, AsyncApi22ServerBindings node) {
		{
			String value = JsonUtil.consumeStringProperty(json, "$ref");
			node.set$ref(value);
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "http");
			if (object != null) {
				node.setHttp(node.createBinding());
				readBinding(object, (AsyncApi22Binding) node.getHttp());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "ws");
			if (object != null) {
				node.setWs(node.createBinding());
				readBinding(object, (AsyncApi22Binding) node.getWs());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "kafka");
			if (object != null) {
				node.setKafka(node.createBinding());
				readBinding(object, (AsyncApi22Binding) node.getKafka());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "anypointmq");
			if (object != null) {
				node.setAnypointmq(node.createBinding());
				readBinding(object, (AsyncApi22Binding) node.getAnypointmq());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "amqp");
			if (object != null) {
				node.setAmqp(node.createBinding());
				readBinding(object, (AsyncApi22Binding) node.getAmqp());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "amqp1");
			if (object != null) {
				node.setAmqp1(node.createBinding());
				readBinding(object, (AsyncApi22Binding) node.getAmqp1());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "mqtt");
			if (object != null) {
				node.setMqtt(node.createBinding());
				readBinding(object, (AsyncApi22Binding) node.getMqtt());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "mqtt5");
			if (object != null) {
				node.setMqtt5(node.createBinding());
				readBinding(object, (AsyncApi22Binding) node.getMqtt5());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "nats");
			if (object != null) {
				node.setNats(node.createBinding());
				readBinding(object, (AsyncApi22Binding) node.getNats());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "jms");
			if (object != null) {
				node.setJms(node.createBinding());
				readBinding(object, (AsyncApi22Binding) node.getJms());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "sns");
			if (object != null) {
				node.setSns(node.createBinding());
				readBinding(object, (AsyncApi22Binding) node.getSns());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "sqs");
			if (object != null) {
				node.setSqs(node.createBinding());
				readBinding(object, (AsyncApi22Binding) node.getSqs());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "stomp");
			if (object != null) {
				node.setStomp(node.createBinding());
				readBinding(object, (AsyncApi22Binding) node.getStomp());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "redis");
			if (object != null) {
				node.setRedis(node.createBinding());
				readBinding(object, (AsyncApi22Binding) node.getRedis());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "mercure");
			if (object != null) {
				node.setMercure(node.createBinding());
				readBinding(object, (AsyncApi22Binding) node.getMercure());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "ibmmq");
			if (object != null) {
				node.setIbmmq(node.createBinding());
				readBinding(object, (AsyncApi22Binding) node.getIbmmq());
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

	public void readChannelBindings(ObjectNode json, AsyncApi22ChannelBindings node) {
		{
			String value = JsonUtil.consumeStringProperty(json, "$ref");
			node.set$ref(value);
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "http");
			if (object != null) {
				node.setHttp(node.createBinding());
				readBinding(object, (AsyncApi22Binding) node.getHttp());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "ws");
			if (object != null) {
				node.setWs(node.createBinding());
				readBinding(object, (AsyncApi22Binding) node.getWs());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "kafka");
			if (object != null) {
				node.setKafka(node.createBinding());
				readBinding(object, (AsyncApi22Binding) node.getKafka());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "anypointmq");
			if (object != null) {
				node.setAnypointmq(node.createBinding());
				readBinding(object, (AsyncApi22Binding) node.getAnypointmq());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "amqp");
			if (object != null) {
				node.setAmqp(node.createBinding());
				readBinding(object, (AsyncApi22Binding) node.getAmqp());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "amqp1");
			if (object != null) {
				node.setAmqp1(node.createBinding());
				readBinding(object, (AsyncApi22Binding) node.getAmqp1());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "mqtt");
			if (object != null) {
				node.setMqtt(node.createBinding());
				readBinding(object, (AsyncApi22Binding) node.getMqtt());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "mqtt5");
			if (object != null) {
				node.setMqtt5(node.createBinding());
				readBinding(object, (AsyncApi22Binding) node.getMqtt5());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "nats");
			if (object != null) {
				node.setNats(node.createBinding());
				readBinding(object, (AsyncApi22Binding) node.getNats());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "jms");
			if (object != null) {
				node.setJms(node.createBinding());
				readBinding(object, (AsyncApi22Binding) node.getJms());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "sns");
			if (object != null) {
				node.setSns(node.createBinding());
				readBinding(object, (AsyncApi22Binding) node.getSns());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "sqs");
			if (object != null) {
				node.setSqs(node.createBinding());
				readBinding(object, (AsyncApi22Binding) node.getSqs());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "stomp");
			if (object != null) {
				node.setStomp(node.createBinding());
				readBinding(object, (AsyncApi22Binding) node.getStomp());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "redis");
			if (object != null) {
				node.setRedis(node.createBinding());
				readBinding(object, (AsyncApi22Binding) node.getRedis());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "mercure");
			if (object != null) {
				node.setMercure(node.createBinding());
				readBinding(object, (AsyncApi22Binding) node.getMercure());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "ibmmq");
			if (object != null) {
				node.setIbmmq(node.createBinding());
				readBinding(object, (AsyncApi22Binding) node.getIbmmq());
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

	public void readOperationBindings(ObjectNode json, AsyncApi22OperationBindings node) {
		{
			String value = JsonUtil.consumeStringProperty(json, "$ref");
			node.set$ref(value);
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "http");
			if (object != null) {
				node.setHttp(node.createBinding());
				readBinding(object, (AsyncApi22Binding) node.getHttp());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "ws");
			if (object != null) {
				node.setWs(node.createBinding());
				readBinding(object, (AsyncApi22Binding) node.getWs());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "kafka");
			if (object != null) {
				node.setKafka(node.createBinding());
				readBinding(object, (AsyncApi22Binding) node.getKafka());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "anypointmq");
			if (object != null) {
				node.setAnypointmq(node.createBinding());
				readBinding(object, (AsyncApi22Binding) node.getAnypointmq());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "amqp");
			if (object != null) {
				node.setAmqp(node.createBinding());
				readBinding(object, (AsyncApi22Binding) node.getAmqp());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "amqp1");
			if (object != null) {
				node.setAmqp1(node.createBinding());
				readBinding(object, (AsyncApi22Binding) node.getAmqp1());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "mqtt");
			if (object != null) {
				node.setMqtt(node.createBinding());
				readBinding(object, (AsyncApi22Binding) node.getMqtt());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "mqtt5");
			if (object != null) {
				node.setMqtt5(node.createBinding());
				readBinding(object, (AsyncApi22Binding) node.getMqtt5());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "nats");
			if (object != null) {
				node.setNats(node.createBinding());
				readBinding(object, (AsyncApi22Binding) node.getNats());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "jms");
			if (object != null) {
				node.setJms(node.createBinding());
				readBinding(object, (AsyncApi22Binding) node.getJms());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "sns");
			if (object != null) {
				node.setSns(node.createBinding());
				readBinding(object, (AsyncApi22Binding) node.getSns());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "sqs");
			if (object != null) {
				node.setSqs(node.createBinding());
				readBinding(object, (AsyncApi22Binding) node.getSqs());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "stomp");
			if (object != null) {
				node.setStomp(node.createBinding());
				readBinding(object, (AsyncApi22Binding) node.getStomp());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "redis");
			if (object != null) {
				node.setRedis(node.createBinding());
				readBinding(object, (AsyncApi22Binding) node.getRedis());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "mercure");
			if (object != null) {
				node.setMercure(node.createBinding());
				readBinding(object, (AsyncApi22Binding) node.getMercure());
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

	public void readMessageBindings(ObjectNode json, AsyncApi22MessageBindings node) {
		{
			String value = JsonUtil.consumeStringProperty(json, "$ref");
			node.set$ref(value);
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "http");
			if (object != null) {
				node.setHttp(node.createBinding());
				readBinding(object, (AsyncApi22Binding) node.getHttp());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "ws");
			if (object != null) {
				node.setWs(node.createBinding());
				readBinding(object, (AsyncApi22Binding) node.getWs());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "kafka");
			if (object != null) {
				node.setKafka(node.createBinding());
				readBinding(object, (AsyncApi22Binding) node.getKafka());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "anypointmq");
			if (object != null) {
				node.setAnypointmq(node.createBinding());
				readBinding(object, (AsyncApi22Binding) node.getAnypointmq());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "amqp");
			if (object != null) {
				node.setAmqp(node.createBinding());
				readBinding(object, (AsyncApi22Binding) node.getAmqp());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "amqp1");
			if (object != null) {
				node.setAmqp1(node.createBinding());
				readBinding(object, (AsyncApi22Binding) node.getAmqp1());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "mqtt");
			if (object != null) {
				node.setMqtt(node.createBinding());
				readBinding(object, (AsyncApi22Binding) node.getMqtt());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "mqtt5");
			if (object != null) {
				node.setMqtt5(node.createBinding());
				readBinding(object, (AsyncApi22Binding) node.getMqtt5());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "nats");
			if (object != null) {
				node.setNats(node.createBinding());
				readBinding(object, (AsyncApi22Binding) node.getNats());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "jms");
			if (object != null) {
				node.setJms(node.createBinding());
				readBinding(object, (AsyncApi22Binding) node.getJms());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "sns");
			if (object != null) {
				node.setSns(node.createBinding());
				readBinding(object, (AsyncApi22Binding) node.getSns());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "sqs");
			if (object != null) {
				node.setSqs(node.createBinding());
				readBinding(object, (AsyncApi22Binding) node.getSqs());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "stomp");
			if (object != null) {
				node.setStomp(node.createBinding());
				readBinding(object, (AsyncApi22Binding) node.getStomp());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "redis");
			if (object != null) {
				node.setRedis(node.createBinding());
				readBinding(object, (AsyncApi22Binding) node.getRedis());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "mercure");
			if (object != null) {
				node.setMercure(node.createBinding());
				readBinding(object, (AsyncApi22Binding) node.getMercure());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "ibmmq");
			if (object != null) {
				node.setIbmmq(node.createBinding());
				readBinding(object, (AsyncApi22Binding) node.getIbmmq());
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

	public void readOneOfMessages(ObjectNode json, AsyncApi22OneOfMessages node) {
		{
			List<ObjectNode> objects = JsonUtil.consumeObjectArrayProperty(json, "oneOf");
			if (objects != null) {
				objects.forEach(object -> {
					AsyncApi22Message model = (AsyncApi22Message) node.createMessage();
					this.readMessage(object, model);
					node.addOneOf(model);
				});
			}
		}
		ReaderUtil.readExtraProperties(json, node);
	}

	public void readMessage(ObjectNode json, AsyncApi22Message node) {
		{
			String value = JsonUtil.consumeStringProperty(json, "$ref");
			node.set$ref(value);
		}
		{
			List<ObjectNode> objects = JsonUtil.consumeObjectArrayProperty(json, "oneOf");
			if (objects != null) {
				objects.forEach(object -> {
					AsyncApi22Message model = (AsyncApi22Message) node.createMessage();
					this.readMessage(object, model);
					node.addOneOf(model);
				});
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "headers");
			if (object != null) {
				node.setHeaders(node.createSchema());
				readSchema(object, (AsyncApi22Schema) node.getHeaders());
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
				readCorrelationID(object, (AsyncApi22CorrelationID) node.getCorrelationId());
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
					AsyncApi22Tag model = (AsyncApi22Tag) node.createTag();
					this.readTag(object, model);
					node.addTag(model);
				});
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "externalDocs");
			if (object != null) {
				node.setExternalDocs(node.createExternalDocumentation());
				readExternalDocumentation(object, (AsyncApi22ExternalDocumentation) node.getExternalDocs());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "bindings");
			if (object != null) {
				node.setBindings(node.createMessageBindings());
				readMessageBindings(object, (AsyncApi22MessageBindings) node.getBindings());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "examples");
			if (object != null) {
				node.setExamples(node.createMessageExample());
				readMessageExample(object, (AsyncApi22MessageExample) node.getExamples());
			}
		}
		{
			List<ObjectNode> objects = JsonUtil.consumeObjectArrayProperty(json, "traits");
			if (objects != null) {
				objects.forEach(object -> {
					AsyncApi22MessageTrait model = (AsyncApi22MessageTrait) node.createMessageTrait();
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

	public void readMessageTrait(ObjectNode json, AsyncApi22MessageTrait node) {
		{
			String value = JsonUtil.consumeStringProperty(json, "$ref");
			node.set$ref(value);
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "headers");
			if (object != null) {
				node.setHeaders(node.createSchema());
				readSchema(object, (AsyncApi22Schema) node.getHeaders());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "correlationId");
			if (object != null) {
				node.setCorrelationId(node.createCorrelationID());
				readCorrelationID(object, (AsyncApi22CorrelationID) node.getCorrelationId());
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
					AsyncApi22Tag model = (AsyncApi22Tag) node.createTag();
					this.readTag(object, model);
					node.addTag(model);
				});
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "externalDocs");
			if (object != null) {
				node.setExternalDocs(node.createExternalDocumentation());
				readExternalDocumentation(object, (AsyncApi22ExternalDocumentation) node.getExternalDocs());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "bindings");
			if (object != null) {
				node.setBindings(node.createMessageBindings());
				readMessageBindings(object, (AsyncApi22MessageBindings) node.getBindings());
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

	public void readMessageExample(ObjectNode json, AsyncApi22MessageExample node) {
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

	public void readTag(ObjectNode json, AsyncApi22Tag node) {
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
				readExternalDocumentation(object, (AsyncApi22ExternalDocumentation) node.getExternalDocs());
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

	public void readExternalDocumentation(ObjectNode json, AsyncApi22ExternalDocumentation node) {
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

	public void readComponents(ObjectNode json, AsyncApi22Components node) {
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "schemas");
			JsonUtil.keys(object).forEach(name -> {
				ObjectNode mapValue = JsonUtil.consumeObjectProperty(object, name);
				if (mapValue != null) {
					AsyncApi22Schema model = (AsyncApi22Schema) node.createSchema();
					this.readSchema(mapValue, model);
					node.addSchema(name, model);
				}
			});
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "messages");
			JsonUtil.keys(object).forEach(name -> {
				ObjectNode mapValue = JsonUtil.consumeObjectProperty(object, name);
				if (mapValue != null) {
					AsyncApi22Message model = (AsyncApi22Message) node.createMessage();
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
					AsyncApi22SecurityScheme model = (AsyncApi22SecurityScheme) node.createSecurityScheme();
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
					AsyncApi22Parameter model = (AsyncApi22Parameter) node.createParameter();
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
					AsyncApi22CorrelationID model = (AsyncApi22CorrelationID) node.createCorrelationID();
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
					AsyncApi22OperationTrait model = (AsyncApi22OperationTrait) node.createOperationTrait();
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
					AsyncApi22MessageTrait model = (AsyncApi22MessageTrait) node.createMessageTrait();
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
					AsyncApi22ServerBindings model = (AsyncApi22ServerBindings) node.createServerBindings();
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
					AsyncApi22ChannelBindings model = (AsyncApi22ChannelBindings) node.createChannelBindings();
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
					AsyncApi22OperationBindings model = (AsyncApi22OperationBindings) node.createOperationBindings();
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
					AsyncApi22MessageBindings model = (AsyncApi22MessageBindings) node.createMessageBindings();
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

	public void readSchema(ObjectNode json, AsyncApi22Schema node) {
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
				readSchema(object, (AsyncApi22Schema) node.getIf());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "then");
			if (object != null) {
				node.setThen(node.createSchema());
				readSchema(object, (AsyncApi22Schema) node.getThen());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "else");
			if (object != null) {
				node.setElse(node.createSchema());
				readSchema(object, (AsyncApi22Schema) node.getElse());
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
					AsyncApi22Schema model = (AsyncApi22Schema) node.createSchema();
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
					readSchema(object, (AsyncApi22Schema) node.getAdditionalProperties());
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
				readSchema(object, (AsyncApi22Schema) node.getAdditionalItems());
			}
		}
		{
			JsonNode value = JsonUtil.consumeAnyProperty(json, "items");
			if (value != null) {
				if (JsonUtil.isObject(value)) {
					ObjectNode object = JsonUtil.toObject(value);
					node.setItems(node.createSchema());
					readSchema(object, (AsyncApi22Schema) node.getItems());
				} else if (JsonUtil.isArray(value)) {
					List<JsonNode> array = JsonUtil.toList(value);
					List<AsyncApi22Schema> models = new ArrayList<>();
					array.forEach(item -> {
						ObjectNode object = JsonUtil.toObject(item);
						AsyncApi22Schema model = (AsyncApi22Schema) node.createSchema();
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
				readSchema(object, (AsyncApi22Schema) node.getPropertyNames());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "contains");
			if (object != null) {
				node.setContains(node.createSchema());
				readSchema(object, (AsyncApi22Schema) node.getContains());
			}
		}
		{
			List<ObjectNode> objects = JsonUtil.consumeObjectArrayProperty(json, "allOf");
			if (objects != null) {
				objects.forEach(object -> {
					AsyncApi22Schema model = (AsyncApi22Schema) node.createSchema();
					this.readSchema(object, model);
					node.addAllOf(model);
				});
			}
		}
		{
			List<ObjectNode> objects = JsonUtil.consumeObjectArrayProperty(json, "oneOf");
			if (objects != null) {
				objects.forEach(object -> {
					AsyncApi22Schema model = (AsyncApi22Schema) node.createSchema();
					this.readSchema(object, model);
					node.addOneOf(model);
				});
			}
		}
		{
			List<ObjectNode> objects = JsonUtil.consumeObjectArrayProperty(json, "anyOf");
			if (objects != null) {
				objects.forEach(object -> {
					AsyncApi22Schema model = (AsyncApi22Schema) node.createSchema();
					this.readSchema(object, model);
					node.addAnyOf(model);
				});
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "not");
			if (object != null) {
				node.setNot(node.createSchema());
				readSchema(object, (AsyncApi22Schema) node.getNot());
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
				readExternalDocumentation(object, (AsyncApi22ExternalDocumentation) node.getExternalDocs());
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

	public void readSecurityScheme(ObjectNode json, AsyncApi22SecurityScheme node) {
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
				readOAuthFlows(object, (AsyncApi22OAuthFlows) node.getFlows());
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

	public void readOAuthFlows(ObjectNode json, AsyncApi22OAuthFlows node) {
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "implicit");
			if (object != null) {
				node.setImplicit(node.createOAuthFlow());
				readOAuthFlow(object, (AsyncApi22OAuthFlow) node.getImplicit());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "password");
			if (object != null) {
				node.setPassword(node.createOAuthFlow());
				readOAuthFlow(object, (AsyncApi22OAuthFlow) node.getPassword());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "clientCredentials");
			if (object != null) {
				node.setClientCredentials(node.createOAuthFlow());
				readOAuthFlow(object, (AsyncApi22OAuthFlow) node.getClientCredentials());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "authorizationCode");
			if (object != null) {
				node.setAuthorizationCode(node.createOAuthFlow());
				readOAuthFlow(object, (AsyncApi22OAuthFlow) node.getAuthorizationCode());
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

	public void readOAuthFlow(ObjectNode json, AsyncApi22OAuthFlow node) {
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

	public void readSecurityRequirement(ObjectNode json, AsyncApi22SecurityRequirement node) {
		{
			List<String> propertyNames = JsonUtil.keys(json);
			propertyNames.forEach(name -> {
				List<String> value = JsonUtil.consumeStringArrayProperty(json, name);
				node.addItem(name, value);
			});
		}
		ReaderUtil.readExtraProperties(json, node);
	}

	public void readCorrelationID(ObjectNode json, AsyncApi22CorrelationID node) {
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

	public void readBinding(ObjectNode json, AsyncApi22Binding node) {
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