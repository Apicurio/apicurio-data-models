package io.apicurio.datamodels.models.asyncapi.v2x.v20.io;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.apicurio.datamodels.models.RootNode;
import io.apicurio.datamodels.models.asyncapi.v2x.v20.AsyncApi20Binding;
import io.apicurio.datamodels.models.asyncapi.v2x.v20.AsyncApi20ChannelBindings;
import io.apicurio.datamodels.models.asyncapi.v2x.v20.AsyncApi20ChannelItem;
import io.apicurio.datamodels.models.asyncapi.v2x.v20.AsyncApi20Channels;
import io.apicurio.datamodels.models.asyncapi.v2x.v20.AsyncApi20Components;
import io.apicurio.datamodels.models.asyncapi.v2x.v20.AsyncApi20Contact;
import io.apicurio.datamodels.models.asyncapi.v2x.v20.AsyncApi20CorrelationID;
import io.apicurio.datamodels.models.asyncapi.v2x.v20.AsyncApi20Document;
import io.apicurio.datamodels.models.asyncapi.v2x.v20.AsyncApi20DocumentImpl;
import io.apicurio.datamodels.models.asyncapi.v2x.v20.AsyncApi20ExternalDocumentation;
import io.apicurio.datamodels.models.asyncapi.v2x.v20.AsyncApi20Info;
import io.apicurio.datamodels.models.asyncapi.v2x.v20.AsyncApi20License;
import io.apicurio.datamodels.models.asyncapi.v2x.v20.AsyncApi20Message;
import io.apicurio.datamodels.models.asyncapi.v2x.v20.AsyncApi20MessageBindings;
import io.apicurio.datamodels.models.asyncapi.v2x.v20.AsyncApi20MessageTrait;
import io.apicurio.datamodels.models.asyncapi.v2x.v20.AsyncApi20OAuthFlow;
import io.apicurio.datamodels.models.asyncapi.v2x.v20.AsyncApi20OAuthFlows;
import io.apicurio.datamodels.models.asyncapi.v2x.v20.AsyncApi20OneOfMessages;
import io.apicurio.datamodels.models.asyncapi.v2x.v20.AsyncApi20Operation;
import io.apicurio.datamodels.models.asyncapi.v2x.v20.AsyncApi20OperationBindings;
import io.apicurio.datamodels.models.asyncapi.v2x.v20.AsyncApi20OperationTrait;
import io.apicurio.datamodels.models.asyncapi.v2x.v20.AsyncApi20Parameter;
import io.apicurio.datamodels.models.asyncapi.v2x.v20.AsyncApi20Parameters;
import io.apicurio.datamodels.models.asyncapi.v2x.v20.AsyncApi20Schema;
import io.apicurio.datamodels.models.asyncapi.v2x.v20.AsyncApi20SecurityRequirement;
import io.apicurio.datamodels.models.asyncapi.v2x.v20.AsyncApi20SecurityScheme;
import io.apicurio.datamodels.models.asyncapi.v2x.v20.AsyncApi20Server;
import io.apicurio.datamodels.models.asyncapi.v2x.v20.AsyncApi20ServerBindings;
import io.apicurio.datamodels.models.asyncapi.v2x.v20.AsyncApi20ServerVariable;
import io.apicurio.datamodels.models.asyncapi.v2x.v20.AsyncApi20Servers;
import io.apicurio.datamodels.models.asyncapi.v2x.v20.AsyncApi20Tag;
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

public class AsyncApi20ModelReader implements ModelReader {

	public void readDocument(ObjectNode json, AsyncApi20Document node) {
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
				readInfo(object, (AsyncApi20Info) node.getInfo());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "servers");
			if (object != null) {
				node.setServers(node.createServers());
				readServers(object, (AsyncApi20Servers) node.getServers());
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
				readChannels(object, (AsyncApi20Channels) node.getChannels());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "components");
			if (object != null) {
				node.setComponents(node.createComponents());
				readComponents(object, (AsyncApi20Components) node.getComponents());
			}
		}
		{
			List<ObjectNode> objects = JsonUtil.consumeObjectArrayProperty(json, "tags");
			if (objects != null) {
				objects.forEach(object -> {
					AsyncApi20Tag model = (AsyncApi20Tag) node.createTag();
					this.readTag(object, model);
					node.addTag(model);
				});
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "externalDocs");
			if (object != null) {
				node.setExternalDocs(node.createExternalDocumentation());
				readExternalDocumentation(object, (AsyncApi20ExternalDocumentation) node.getExternalDocs());
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
		AsyncApi20Document rootModel = new AsyncApi20DocumentImpl();
		this.readDocument(json, rootModel);
		return rootModel;
	}

	public void readInfo(ObjectNode json, AsyncApi20Info node) {
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
				readContact(object, (AsyncApi20Contact) node.getContact());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "license");
			if (object != null) {
				node.setLicense(node.createLicense());
				readLicense(object, (AsyncApi20License) node.getLicense());
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

	public void readContact(ObjectNode json, AsyncApi20Contact node) {
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

	public void readLicense(ObjectNode json, AsyncApi20License node) {
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

	public void readServers(ObjectNode json, AsyncApi20Servers node) {
		{
			List<String> propertyNames = JsonUtil.keys(json);
			propertyNames.forEach(name -> {
				ObjectNode object = JsonUtil.consumeObjectProperty(json, name);
				if (object != null) {
					AsyncApi20Server model = (AsyncApi20Server) node.createServer();
					this.readServer(object, model);
					node.addItem(name, model);
				}
			});
		}
		ReaderUtil.readExtraProperties(json, node);
	}

	public void readServer(ObjectNode json, AsyncApi20Server node) {
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
					AsyncApi20ServerVariable model = (AsyncApi20ServerVariable) node.createServerVariable();
					this.readServerVariable(mapValue, model);
					node.addVariable(name, model);
				}
			});
		}
		{
			List<ObjectNode> objects = JsonUtil.consumeObjectArrayProperty(json, "security");
			if (objects != null) {
				objects.forEach(object -> {
					AsyncApi20SecurityRequirement model = (AsyncApi20SecurityRequirement) node
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
				readServerBindings(object, (AsyncApi20ServerBindings) node.getBindings());
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

	public void readServerVariable(ObjectNode json, AsyncApi20ServerVariable node) {
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

	public void readChannels(ObjectNode json, AsyncApi20Channels node) {
		{
			List<String> propertyNames = JsonUtil.keys(json);
			propertyNames.forEach(name -> {
				ObjectNode object = JsonUtil.consumeObjectProperty(json, name);
				if (object != null) {
					AsyncApi20ChannelItem model = (AsyncApi20ChannelItem) node.createChannelItem();
					this.readChannelItem(object, model);
					node.addItem(name, model);
				}
			});
		}
		ReaderUtil.readExtraProperties(json, node);
	}

	public void readChannelItem(ObjectNode json, AsyncApi20ChannelItem node) {
		{
			String value = JsonUtil.consumeStringProperty(json, "$ref");
			node.set$ref(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "description");
			node.setDescription(value);
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "subscribe");
			if (object != null) {
				node.setSubscribe(node.createOperation());
				readOperation(object, (AsyncApi20Operation) node.getSubscribe());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "publish");
			if (object != null) {
				node.setPublish(node.createOperation());
				readOperation(object, (AsyncApi20Operation) node.getPublish());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "parameters");
			if (object != null) {
				node.setParameters(node.createParameters());
				readParameters(object, (AsyncApi20Parameters) node.getParameters());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "bindings");
			if (object != null) {
				node.setBindings(node.createChannelBindings());
				readChannelBindings(object, (AsyncApi20ChannelBindings) node.getBindings());
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

	public void readOperation(ObjectNode json, AsyncApi20Operation node) {
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
					AsyncApi20Tag model = (AsyncApi20Tag) node.createTag();
					this.readTag(object, model);
					node.addTag(model);
				});
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "externalDocs");
			if (object != null) {
				node.setExternalDocs(node.createExternalDocumentation());
				readExternalDocumentation(object, (AsyncApi20ExternalDocumentation) node.getExternalDocs());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "bindings");
			if (object != null) {
				node.setBindings(node.createOperationBindings());
				readOperationBindings(object, (AsyncApi20OperationBindings) node.getBindings());
			}
		}
		{
			List<ObjectNode> objects = JsonUtil.consumeObjectArrayProperty(json, "traits");
			if (objects != null) {
				objects.forEach(object -> {
					AsyncApi20OperationTrait model = (AsyncApi20OperationTrait) node.createOperationTrait();
					this.readOperationTrait(object, model);
					node.addTrait(model);
				});
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "message");
			if (object != null) {
				node.setMessage(node.createMessage());
				readMessage(object, (AsyncApi20Message) node.getMessage());
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

	public void readOperationTrait(ObjectNode json, AsyncApi20OperationTrait node) {
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
					AsyncApi20Tag model = (AsyncApi20Tag) node.createTag();
					this.readTag(object, model);
					node.addTag(model);
				});
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "externalDocs");
			if (object != null) {
				node.setExternalDocs(node.createExternalDocumentation());
				readExternalDocumentation(object, (AsyncApi20ExternalDocumentation) node.getExternalDocs());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "bindings");
			if (object != null) {
				node.setBindings(node.createOperationBindings());
				readOperationBindings(object, (AsyncApi20OperationBindings) node.getBindings());
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

	public void readParameters(ObjectNode json, AsyncApi20Parameters node) {
		{
			List<String> propertyNames = JsonUtil.keys(json);
			propertyNames.forEach(name -> {
				ObjectNode object = JsonUtil.consumeObjectProperty(json, name);
				if (object != null) {
					AsyncApi20Parameter model = (AsyncApi20Parameter) node.createParameter();
					this.readParameter(object, model);
					node.addItem(name, model);
				}
			});
		}
		ReaderUtil.readExtraProperties(json, node);
	}

	public void readParameter(ObjectNode json, AsyncApi20Parameter node) {
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
				readSchema(object, (AsyncApi20Schema) node.getSchema());
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

	public void readServerBindings(ObjectNode json, AsyncApi20ServerBindings node) {
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "http");
			if (object != null) {
				node.setHttp(node.createBinding());
				readBinding(object, (AsyncApi20Binding) node.getHttp());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "ws");
			if (object != null) {
				node.setWs(node.createBinding());
				readBinding(object, (AsyncApi20Binding) node.getWs());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "kafka");
			if (object != null) {
				node.setKafka(node.createBinding());
				readBinding(object, (AsyncApi20Binding) node.getKafka());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "amqp");
			if (object != null) {
				node.setAmqp(node.createBinding());
				readBinding(object, (AsyncApi20Binding) node.getAmqp());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "amqp1");
			if (object != null) {
				node.setAmqp1(node.createBinding());
				readBinding(object, (AsyncApi20Binding) node.getAmqp1());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "mqtt");
			if (object != null) {
				node.setMqtt(node.createBinding());
				readBinding(object, (AsyncApi20Binding) node.getMqtt());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "mqtt5");
			if (object != null) {
				node.setMqtt5(node.createBinding());
				readBinding(object, (AsyncApi20Binding) node.getMqtt5());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "nats");
			if (object != null) {
				node.setNats(node.createBinding());
				readBinding(object, (AsyncApi20Binding) node.getNats());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "jms");
			if (object != null) {
				node.setJms(node.createBinding());
				readBinding(object, (AsyncApi20Binding) node.getJms());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "sns");
			if (object != null) {
				node.setSns(node.createBinding());
				readBinding(object, (AsyncApi20Binding) node.getSns());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "sqs");
			if (object != null) {
				node.setSqs(node.createBinding());
				readBinding(object, (AsyncApi20Binding) node.getSqs());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "stomp");
			if (object != null) {
				node.setStomp(node.createBinding());
				readBinding(object, (AsyncApi20Binding) node.getStomp());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "redis");
			if (object != null) {
				node.setRedis(node.createBinding());
				readBinding(object, (AsyncApi20Binding) node.getRedis());
			}
		}
		ReaderUtil.readExtraProperties(json, node);
	}

	public void readChannelBindings(ObjectNode json, AsyncApi20ChannelBindings node) {
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "http");
			if (object != null) {
				node.setHttp(node.createBinding());
				readBinding(object, (AsyncApi20Binding) node.getHttp());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "ws");
			if (object != null) {
				node.setWs(node.createBinding());
				readBinding(object, (AsyncApi20Binding) node.getWs());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "kafka");
			if (object != null) {
				node.setKafka(node.createBinding());
				readBinding(object, (AsyncApi20Binding) node.getKafka());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "amqp");
			if (object != null) {
				node.setAmqp(node.createBinding());
				readBinding(object, (AsyncApi20Binding) node.getAmqp());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "amqp1");
			if (object != null) {
				node.setAmqp1(node.createBinding());
				readBinding(object, (AsyncApi20Binding) node.getAmqp1());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "mqtt");
			if (object != null) {
				node.setMqtt(node.createBinding());
				readBinding(object, (AsyncApi20Binding) node.getMqtt());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "mqtt5");
			if (object != null) {
				node.setMqtt5(node.createBinding());
				readBinding(object, (AsyncApi20Binding) node.getMqtt5());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "nats");
			if (object != null) {
				node.setNats(node.createBinding());
				readBinding(object, (AsyncApi20Binding) node.getNats());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "jms");
			if (object != null) {
				node.setJms(node.createBinding());
				readBinding(object, (AsyncApi20Binding) node.getJms());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "sns");
			if (object != null) {
				node.setSns(node.createBinding());
				readBinding(object, (AsyncApi20Binding) node.getSns());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "sqs");
			if (object != null) {
				node.setSqs(node.createBinding());
				readBinding(object, (AsyncApi20Binding) node.getSqs());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "stomp");
			if (object != null) {
				node.setStomp(node.createBinding());
				readBinding(object, (AsyncApi20Binding) node.getStomp());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "redis");
			if (object != null) {
				node.setRedis(node.createBinding());
				readBinding(object, (AsyncApi20Binding) node.getRedis());
			}
		}
		ReaderUtil.readExtraProperties(json, node);
	}

	public void readOperationBindings(ObjectNode json, AsyncApi20OperationBindings node) {
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "http");
			if (object != null) {
				node.setHttp(node.createBinding());
				readBinding(object, (AsyncApi20Binding) node.getHttp());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "ws");
			if (object != null) {
				node.setWs(node.createBinding());
				readBinding(object, (AsyncApi20Binding) node.getWs());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "kafka");
			if (object != null) {
				node.setKafka(node.createBinding());
				readBinding(object, (AsyncApi20Binding) node.getKafka());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "amqp");
			if (object != null) {
				node.setAmqp(node.createBinding());
				readBinding(object, (AsyncApi20Binding) node.getAmqp());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "amqp1");
			if (object != null) {
				node.setAmqp1(node.createBinding());
				readBinding(object, (AsyncApi20Binding) node.getAmqp1());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "mqtt");
			if (object != null) {
				node.setMqtt(node.createBinding());
				readBinding(object, (AsyncApi20Binding) node.getMqtt());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "mqtt5");
			if (object != null) {
				node.setMqtt5(node.createBinding());
				readBinding(object, (AsyncApi20Binding) node.getMqtt5());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "nats");
			if (object != null) {
				node.setNats(node.createBinding());
				readBinding(object, (AsyncApi20Binding) node.getNats());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "jms");
			if (object != null) {
				node.setJms(node.createBinding());
				readBinding(object, (AsyncApi20Binding) node.getJms());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "sns");
			if (object != null) {
				node.setSns(node.createBinding());
				readBinding(object, (AsyncApi20Binding) node.getSns());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "sqs");
			if (object != null) {
				node.setSqs(node.createBinding());
				readBinding(object, (AsyncApi20Binding) node.getSqs());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "stomp");
			if (object != null) {
				node.setStomp(node.createBinding());
				readBinding(object, (AsyncApi20Binding) node.getStomp());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "redis");
			if (object != null) {
				node.setRedis(node.createBinding());
				readBinding(object, (AsyncApi20Binding) node.getRedis());
			}
		}
		ReaderUtil.readExtraProperties(json, node);
	}

	public void readMessageBindings(ObjectNode json, AsyncApi20MessageBindings node) {
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "http");
			if (object != null) {
				node.setHttp(node.createBinding());
				readBinding(object, (AsyncApi20Binding) node.getHttp());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "ws");
			if (object != null) {
				node.setWs(node.createBinding());
				readBinding(object, (AsyncApi20Binding) node.getWs());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "kafka");
			if (object != null) {
				node.setKafka(node.createBinding());
				readBinding(object, (AsyncApi20Binding) node.getKafka());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "amqp");
			if (object != null) {
				node.setAmqp(node.createBinding());
				readBinding(object, (AsyncApi20Binding) node.getAmqp());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "amqp1");
			if (object != null) {
				node.setAmqp1(node.createBinding());
				readBinding(object, (AsyncApi20Binding) node.getAmqp1());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "mqtt");
			if (object != null) {
				node.setMqtt(node.createBinding());
				readBinding(object, (AsyncApi20Binding) node.getMqtt());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "mqtt5");
			if (object != null) {
				node.setMqtt5(node.createBinding());
				readBinding(object, (AsyncApi20Binding) node.getMqtt5());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "nats");
			if (object != null) {
				node.setNats(node.createBinding());
				readBinding(object, (AsyncApi20Binding) node.getNats());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "jms");
			if (object != null) {
				node.setJms(node.createBinding());
				readBinding(object, (AsyncApi20Binding) node.getJms());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "sns");
			if (object != null) {
				node.setSns(node.createBinding());
				readBinding(object, (AsyncApi20Binding) node.getSns());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "sqs");
			if (object != null) {
				node.setSqs(node.createBinding());
				readBinding(object, (AsyncApi20Binding) node.getSqs());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "stomp");
			if (object != null) {
				node.setStomp(node.createBinding());
				readBinding(object, (AsyncApi20Binding) node.getStomp());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "redis");
			if (object != null) {
				node.setRedis(node.createBinding());
				readBinding(object, (AsyncApi20Binding) node.getRedis());
			}
		}
		ReaderUtil.readExtraProperties(json, node);
	}

	public void readOneOfMessages(ObjectNode json, AsyncApi20OneOfMessages node) {
		{
			List<ObjectNode> objects = JsonUtil.consumeObjectArrayProperty(json, "oneOf");
			if (objects != null) {
				objects.forEach(object -> {
					AsyncApi20Message model = (AsyncApi20Message) node.createMessage();
					this.readMessage(object, model);
					node.addOneOf(model);
				});
			}
		}
		ReaderUtil.readExtraProperties(json, node);
	}

	public void readMessage(ObjectNode json, AsyncApi20Message node) {
		{
			String value = JsonUtil.consumeStringProperty(json, "$ref");
			node.set$ref(value);
		}
		{
			List<ObjectNode> objects = JsonUtil.consumeObjectArrayProperty(json, "oneOf");
			if (objects != null) {
				objects.forEach(object -> {
					AsyncApi20Message model = (AsyncApi20Message) node.createMessage();
					this.readMessage(object, model);
					node.addOneOf(model);
				});
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "headers");
			if (object != null) {
				node.setHeaders(node.createSchema());
				readSchema(object, (AsyncApi20Schema) node.getHeaders());
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
				readCorrelationID(object, (AsyncApi20CorrelationID) node.getCorrelationId());
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
					AsyncApi20Tag model = (AsyncApi20Tag) node.createTag();
					this.readTag(object, model);
					node.addTag(model);
				});
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "externalDocs");
			if (object != null) {
				node.setExternalDocs(node.createExternalDocumentation());
				readExternalDocumentation(object, (AsyncApi20ExternalDocumentation) node.getExternalDocs());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "bindings");
			if (object != null) {
				node.setBindings(node.createMessageBindings());
				readMessageBindings(object, (AsyncApi20MessageBindings) node.getBindings());
			}
		}
		{
			Map<String, JsonNode> value = JsonUtil.consumeAnyMapProperty(json, "examples");
			node.setExamples(value);
		}
		{
			List<ObjectNode> objects = JsonUtil.consumeObjectArrayProperty(json, "traits");
			if (objects != null) {
				objects.forEach(object -> {
					AsyncApi20MessageTrait model = (AsyncApi20MessageTrait) node.createMessageTrait();
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

	public void readMessageTrait(ObjectNode json, AsyncApi20MessageTrait node) {
		{
			String value = JsonUtil.consumeStringProperty(json, "$ref");
			node.set$ref(value);
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "headers");
			if (object != null) {
				node.setHeaders(node.createSchema());
				readSchema(object, (AsyncApi20Schema) node.getHeaders());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "correlationId");
			if (object != null) {
				node.setCorrelationId(node.createCorrelationID());
				readCorrelationID(object, (AsyncApi20CorrelationID) node.getCorrelationId());
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
					AsyncApi20Tag model = (AsyncApi20Tag) node.createTag();
					this.readTag(object, model);
					node.addTag(model);
				});
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "externalDocs");
			if (object != null) {
				node.setExternalDocs(node.createExternalDocumentation());
				readExternalDocumentation(object, (AsyncApi20ExternalDocumentation) node.getExternalDocs());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "bindings");
			if (object != null) {
				node.setBindings(node.createMessageBindings());
				readMessageBindings(object, (AsyncApi20MessageBindings) node.getBindings());
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

	public void readTag(ObjectNode json, AsyncApi20Tag node) {
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
				readExternalDocumentation(object, (AsyncApi20ExternalDocumentation) node.getExternalDocs());
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

	public void readExternalDocumentation(ObjectNode json, AsyncApi20ExternalDocumentation node) {
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

	public void readComponents(ObjectNode json, AsyncApi20Components node) {
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "schemas");
			JsonUtil.keys(object).forEach(name -> {
				ObjectNode mapValue = JsonUtil.consumeObjectProperty(object, name);
				if (mapValue != null) {
					AsyncApi20Schema model = (AsyncApi20Schema) node.createSchema();
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
					AsyncApi20Message model = (AsyncApi20Message) node.createMessage();
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
					AsyncApi20SecurityScheme model = (AsyncApi20SecurityScheme) node.createSecurityScheme();
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
					AsyncApi20Parameter model = (AsyncApi20Parameter) node.createParameter();
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
					AsyncApi20CorrelationID model = (AsyncApi20CorrelationID) node.createCorrelationID();
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
					AsyncApi20OperationTrait model = (AsyncApi20OperationTrait) node.createOperationTrait();
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
					AsyncApi20MessageTrait model = (AsyncApi20MessageTrait) node.createMessageTrait();
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
					AsyncApi20ServerBindings model = (AsyncApi20ServerBindings) node.createServerBindings();
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
					AsyncApi20ChannelBindings model = (AsyncApi20ChannelBindings) node.createChannelBindings();
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
					AsyncApi20OperationBindings model = (AsyncApi20OperationBindings) node.createOperationBindings();
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
					AsyncApi20MessageBindings model = (AsyncApi20MessageBindings) node.createMessageBindings();
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

	public void readSchema(ObjectNode json, AsyncApi20Schema node) {
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
				readSchema(object, (AsyncApi20Schema) node.getIf());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "then");
			if (object != null) {
				node.setThen(node.createSchema());
				readSchema(object, (AsyncApi20Schema) node.getThen());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "else");
			if (object != null) {
				node.setElse(node.createSchema());
				readSchema(object, (AsyncApi20Schema) node.getElse());
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
					AsyncApi20Schema model = (AsyncApi20Schema) node.createSchema();
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
					readSchema(object, (AsyncApi20Schema) node.getAdditionalProperties());
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
				readSchema(object, (AsyncApi20Schema) node.getAdditionalItems());
			}
		}
		{
			JsonNode value = JsonUtil.consumeAnyProperty(json, "items");
			if (value != null) {
				if (JsonUtil.isObject(value)) {
					ObjectNode object = JsonUtil.toObject(value);
					node.setItems(node.createSchema());
					readSchema(object, (AsyncApi20Schema) node.getItems());
				} else if (JsonUtil.isArray(value)) {
					List<JsonNode> array = JsonUtil.toList(value);
					List<AsyncApi20Schema> models = new ArrayList<>();
					array.forEach(item -> {
						ObjectNode object = JsonUtil.toObject(item);
						AsyncApi20Schema model = (AsyncApi20Schema) node.createSchema();
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
				readSchema(object, (AsyncApi20Schema) node.getPropertyNames());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "contains");
			if (object != null) {
				node.setContains(node.createSchema());
				readSchema(object, (AsyncApi20Schema) node.getContains());
			}
		}
		{
			List<ObjectNode> objects = JsonUtil.consumeObjectArrayProperty(json, "allOf");
			if (objects != null) {
				objects.forEach(object -> {
					AsyncApi20Schema model = (AsyncApi20Schema) node.createSchema();
					this.readSchema(object, model);
					node.addAllOf(model);
				});
			}
		}
		{
			List<ObjectNode> objects = JsonUtil.consumeObjectArrayProperty(json, "oneOf");
			if (objects != null) {
				objects.forEach(object -> {
					AsyncApi20Schema model = (AsyncApi20Schema) node.createSchema();
					this.readSchema(object, model);
					node.addOneOf(model);
				});
			}
		}
		{
			List<ObjectNode> objects = JsonUtil.consumeObjectArrayProperty(json, "anyOf");
			if (objects != null) {
				objects.forEach(object -> {
					AsyncApi20Schema model = (AsyncApi20Schema) node.createSchema();
					this.readSchema(object, model);
					node.addAnyOf(model);
				});
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "not");
			if (object != null) {
				node.setNot(node.createSchema());
				readSchema(object, (AsyncApi20Schema) node.getNot());
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
				readExternalDocumentation(object, (AsyncApi20ExternalDocumentation) node.getExternalDocs());
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

	public void readSecurityScheme(ObjectNode json, AsyncApi20SecurityScheme node) {
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
				readOAuthFlows(object, (AsyncApi20OAuthFlows) node.getFlows());
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

	public void readOAuthFlows(ObjectNode json, AsyncApi20OAuthFlows node) {
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "implicit");
			if (object != null) {
				node.setImplicit(node.createOAuthFlow());
				readOAuthFlow(object, (AsyncApi20OAuthFlow) node.getImplicit());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "password");
			if (object != null) {
				node.setPassword(node.createOAuthFlow());
				readOAuthFlow(object, (AsyncApi20OAuthFlow) node.getPassword());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "clientCredentials");
			if (object != null) {
				node.setClientCredentials(node.createOAuthFlow());
				readOAuthFlow(object, (AsyncApi20OAuthFlow) node.getClientCredentials());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "authorizationCode");
			if (object != null) {
				node.setAuthorizationCode(node.createOAuthFlow());
				readOAuthFlow(object, (AsyncApi20OAuthFlow) node.getAuthorizationCode());
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

	public void readOAuthFlow(ObjectNode json, AsyncApi20OAuthFlow node) {
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

	public void readSecurityRequirement(ObjectNode json, AsyncApi20SecurityRequirement node) {
		{
			List<String> propertyNames = JsonUtil.keys(json);
			propertyNames.forEach(name -> {
				List<String> value = JsonUtil.consumeStringArrayProperty(json, name);
				node.addItem(name, value);
			});
		}
		ReaderUtil.readExtraProperties(json, node);
	}

	public void readCorrelationID(ObjectNode json, AsyncApi20CorrelationID node) {
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

	public void readBinding(ObjectNode json, AsyncApi20Binding node) {
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