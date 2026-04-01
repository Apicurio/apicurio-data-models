package io.apicurio.datamodels.models.asyncapi.v3x.v31.io;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.apicurio.datamodels.models.RootNode;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31Binding;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31Channel;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31ChannelBindings;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31Channels;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31Components;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31Contact;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31CorrelationID;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31Document;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.AsyncApi31DocumentImpl;
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
import io.apicurio.datamodels.models.io.ModelReader;
import io.apicurio.datamodels.models.union.AnyUnionValue;
import io.apicurio.datamodels.models.union.AnyUnionValueImpl;
import io.apicurio.datamodels.models.union.BooleanUnionValue;
import io.apicurio.datamodels.models.union.BooleanUnionValueImpl;
import io.apicurio.datamodels.models.union.MultiFormatSchemaSchemaUnion;
import io.apicurio.datamodels.models.union.SchemaListUnionValue;
import io.apicurio.datamodels.models.union.SchemaListUnionValueImpl;
import io.apicurio.datamodels.models.util.JsonUtil;
import io.apicurio.datamodels.models.util.ReaderUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AsyncApi31ModelReader implements ModelReader {

	public void readDocument(ObjectNode json, AsyncApi31Document node) {
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
				readInfo(object, (AsyncApi31Info) node.getInfo());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "servers");
			if (object != null) {
				node.setServers(node.createServers());
				readServers(object, (AsyncApi31Servers) node.getServers());
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
				readChannels(object, (AsyncApi31Channels) node.getChannels());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "operations");
			if (object != null) {
				node.setOperations(node.createOperations());
				readOperations(object, (AsyncApi31Operations) node.getOperations());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "components");
			if (object != null) {
				node.setComponents(node.createComponents());
				readComponents(object, (AsyncApi31Components) node.getComponents());
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
		AsyncApi31Document rootModel = new AsyncApi31DocumentImpl();
		this.readDocument(json, rootModel);
		return rootModel;
	}

	public void readInfo(ObjectNode json, AsyncApi31Info node) {
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
				readContact(object, (AsyncApi31Contact) node.getContact());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "license");
			if (object != null) {
				node.setLicense(node.createLicense());
				readLicense(object, (AsyncApi31License) node.getLicense());
			}
		}
		{
			List<ObjectNode> objects = JsonUtil.consumeObjectArrayProperty(json, "tags");
			if (objects != null) {
				objects.forEach(object -> {
					AsyncApi31Tag model = (AsyncApi31Tag) node.createTag();
					this.readTag(object, model);
					node.addTag(model);
				});
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "externalDocs");
			if (object != null) {
				node.setExternalDocs(node.createExternalDocumentation());
				readExternalDocumentation(object, (AsyncApi31ExternalDocumentation) node.getExternalDocs());
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

	public void readContact(ObjectNode json, AsyncApi31Contact node) {
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

	public void readLicense(ObjectNode json, AsyncApi31License node) {
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

	public void readServers(ObjectNode json, AsyncApi31Servers node) {
		{
			List<String> propertyNames = JsonUtil.keys(json);
			propertyNames.forEach(name -> {
				ObjectNode object = JsonUtil.consumeObjectProperty(json, name);
				if (object != null) {
					AsyncApi31Server model = (AsyncApi31Server) node.createServer();
					this.readServer(object, model);
					node.addItem(name, model);
				}
			});
		}
		ReaderUtil.readExtraProperties(json, node);
	}

	public void readServer(ObjectNode json, AsyncApi31Server node) {
		{
			String value = JsonUtil.consumeStringProperty(json, "$ref");
			node.set$ref(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "host");
			node.setHost(value);
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
			String value = JsonUtil.consumeStringProperty(json, "pathname");
			node.setPathname(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "description");
			node.setDescription(value);
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
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "variables");
			JsonUtil.keys(object).forEach(name -> {
				ObjectNode mapValue = JsonUtil.consumeObjectProperty(object, name);
				if (mapValue != null) {
					AsyncApi31ServerVariable model = (AsyncApi31ServerVariable) node.createServerVariable();
					this.readServerVariable(mapValue, model);
					node.addVariable(name, model);
				}
			});
		}
		{
			List<ObjectNode> objects = JsonUtil.consumeObjectArrayProperty(json, "security");
			if (objects != null) {
				objects.forEach(object -> {
					AsyncApi31SecurityScheme model = (AsyncApi31SecurityScheme) node.createSecurityScheme();
					this.readSecurityScheme(object, model);
					node.addSecurity(model);
				});
			}
		}
		{
			List<ObjectNode> objects = JsonUtil.consumeObjectArrayProperty(json, "tags");
			if (objects != null) {
				objects.forEach(object -> {
					AsyncApi31Tag model = (AsyncApi31Tag) node.createTag();
					this.readTag(object, model);
					node.addTag(model);
				});
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "externalDocs");
			if (object != null) {
				node.setExternalDocs(node.createExternalDocumentation());
				readExternalDocumentation(object, (AsyncApi31ExternalDocumentation) node.getExternalDocs());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "bindings");
			if (object != null) {
				node.setBindings(node.createServerBindings());
				readServerBindings(object, (AsyncApi31ServerBindings) node.getBindings());
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

	public void readServerVariable(ObjectNode json, AsyncApi31ServerVariable node) {
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

	public void readChannels(ObjectNode json, AsyncApi31Channels node) {
		{
			List<String> propertyNames = JsonUtil.keys(json);
			propertyNames.forEach(name -> {
				ObjectNode object = JsonUtil.consumeObjectProperty(json, name);
				if (object != null) {
					AsyncApi31Channel model = (AsyncApi31Channel) node.createChannel();
					this.readChannel(object, model);
					node.addItem(name, model);
				}
			});
		}
		ReaderUtil.readExtraProperties(json, node);
	}

	public void readChannel(ObjectNode json, AsyncApi31Channel node) {
		{
			String value = JsonUtil.consumeStringProperty(json, "$ref");
			node.set$ref(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "address");
			node.setAddress(value);
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "messages");
			JsonUtil.keys(object).forEach(name -> {
				ObjectNode mapValue = JsonUtil.consumeObjectProperty(object, name);
				if (mapValue != null) {
					AsyncApi31Message model = (AsyncApi31Message) node.createMessage();
					this.readMessage(mapValue, model);
					node.addMessage(name, model);
				}
			});
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
			List<ObjectNode> objects = JsonUtil.consumeObjectArrayProperty(json, "servers");
			if (objects != null) {
				objects.forEach(object -> {
					AsyncApi31Reference model = (AsyncApi31Reference) node.createReference();
					this.readReference(object, model);
					node.addServer(model);
				});
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "parameters");
			if (object != null) {
				node.setParameters(node.createParameters());
				readParameters(object, (AsyncApi31Parameters) node.getParameters());
			}
		}
		{
			List<ObjectNode> objects = JsonUtil.consumeObjectArrayProperty(json, "tags");
			if (objects != null) {
				objects.forEach(object -> {
					AsyncApi31Tag model = (AsyncApi31Tag) node.createTag();
					this.readTag(object, model);
					node.addTag(model);
				});
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "externalDocs");
			if (object != null) {
				node.setExternalDocs(node.createExternalDocumentation());
				readExternalDocumentation(object, (AsyncApi31ExternalDocumentation) node.getExternalDocs());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "bindings");
			if (object != null) {
				node.setBindings(node.createChannelBindings());
				readChannelBindings(object, (AsyncApi31ChannelBindings) node.getBindings());
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

	public void readOperations(ObjectNode json, AsyncApi31Operations node) {
		{
			List<String> propertyNames = JsonUtil.keys(json);
			propertyNames.forEach(name -> {
				ObjectNode object = JsonUtil.consumeObjectProperty(json, name);
				if (object != null) {
					AsyncApi31Operation model = (AsyncApi31Operation) node.createOperation();
					this.readOperation(object, model);
					node.addItem(name, model);
				}
			});
		}
		ReaderUtil.readExtraProperties(json, node);
	}

	public void readOperation(ObjectNode json, AsyncApi31Operation node) {
		{
			String value = JsonUtil.consumeStringProperty(json, "$ref");
			node.set$ref(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "action");
			node.setAction(value);
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "channel");
			if (object != null) {
				node.setChannel(node.createReference());
				readReference(object, (AsyncApi31Reference) node.getChannel());
			}
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
			List<ObjectNode> objects = JsonUtil.consumeObjectArrayProperty(json, "security");
			if (objects != null) {
				objects.forEach(object -> {
					AsyncApi31SecurityScheme model = (AsyncApi31SecurityScheme) node.createSecurityScheme();
					this.readSecurityScheme(object, model);
					node.addSecurity(model);
				});
			}
		}
		{
			List<ObjectNode> objects = JsonUtil.consumeObjectArrayProperty(json, "tags");
			if (objects != null) {
				objects.forEach(object -> {
					AsyncApi31Tag model = (AsyncApi31Tag) node.createTag();
					this.readTag(object, model);
					node.addTag(model);
				});
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "externalDocs");
			if (object != null) {
				node.setExternalDocs(node.createExternalDocumentation());
				readExternalDocumentation(object, (AsyncApi31ExternalDocumentation) node.getExternalDocs());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "bindings");
			if (object != null) {
				node.setBindings(node.createOperationBindings());
				readOperationBindings(object, (AsyncApi31OperationBindings) node.getBindings());
			}
		}
		{
			List<ObjectNode> objects = JsonUtil.consumeObjectArrayProperty(json, "traits");
			if (objects != null) {
				objects.forEach(object -> {
					AsyncApi31OperationTrait model = (AsyncApi31OperationTrait) node.createOperationTrait();
					this.readOperationTrait(object, model);
					node.addTrait(model);
				});
			}
		}
		{
			List<ObjectNode> objects = JsonUtil.consumeObjectArrayProperty(json, "messages");
			if (objects != null) {
				objects.forEach(object -> {
					AsyncApi31Reference model = (AsyncApi31Reference) node.createReference();
					this.readReference(object, model);
					node.addMessage(model);
				});
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "reply");
			if (object != null) {
				node.setReply(node.createOperationReply());
				readOperationReply(object, (AsyncApi31OperationReply) node.getReply());
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

	public void readOperationTrait(ObjectNode json, AsyncApi31OperationTrait node) {
		{
			String value = JsonUtil.consumeStringProperty(json, "$ref");
			node.set$ref(value);
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
			List<ObjectNode> objects = JsonUtil.consumeObjectArrayProperty(json, "security");
			if (objects != null) {
				objects.forEach(object -> {
					AsyncApi31SecurityScheme model = (AsyncApi31SecurityScheme) node.createSecurityScheme();
					this.readSecurityScheme(object, model);
					node.addSecurity(model);
				});
			}
		}
		{
			List<ObjectNode> objects = JsonUtil.consumeObjectArrayProperty(json, "tags");
			if (objects != null) {
				objects.forEach(object -> {
					AsyncApi31Tag model = (AsyncApi31Tag) node.createTag();
					this.readTag(object, model);
					node.addTag(model);
				});
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "externalDocs");
			if (object != null) {
				node.setExternalDocs(node.createExternalDocumentation());
				readExternalDocumentation(object, (AsyncApi31ExternalDocumentation) node.getExternalDocs());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "bindings");
			if (object != null) {
				node.setBindings(node.createOperationBindings());
				readOperationBindings(object, (AsyncApi31OperationBindings) node.getBindings());
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

	public void readOperationReply(ObjectNode json, AsyncApi31OperationReply node) {
		{
			String value = JsonUtil.consumeStringProperty(json, "$ref");
			node.set$ref(value);
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "address");
			if (object != null) {
				node.setAddress(node.createOperationReplyAddress());
				readOperationReplyAddress(object, (AsyncApi31OperationReplyAddress) node.getAddress());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "channel");
			if (object != null) {
				node.setChannel(node.createReference());
				readReference(object, (AsyncApi31Reference) node.getChannel());
			}
		}
		{
			List<ObjectNode> objects = JsonUtil.consumeObjectArrayProperty(json, "messages");
			if (objects != null) {
				objects.forEach(object -> {
					AsyncApi31Reference model = (AsyncApi31Reference) node.createReference();
					this.readReference(object, model);
					node.addMessage(model);
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

	public void readOperationReplyAddress(ObjectNode json, AsyncApi31OperationReplyAddress node) {
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

	public void readParameters(ObjectNode json, AsyncApi31Parameters node) {
		{
			List<String> propertyNames = JsonUtil.keys(json);
			propertyNames.forEach(name -> {
				ObjectNode object = JsonUtil.consumeObjectProperty(json, name);
				if (object != null) {
					AsyncApi31Parameter model = (AsyncApi31Parameter) node.createParameter();
					this.readParameter(object, model);
					node.addItem(name, model);
				}
			});
		}
		ReaderUtil.readExtraProperties(json, node);
	}

	public void readParameter(ObjectNode json, AsyncApi31Parameter node) {
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

	public void readServerBindings(ObjectNode json, AsyncApi31ServerBindings node) {
		{
			String value = JsonUtil.consumeStringProperty(json, "$ref");
			node.set$ref(value);
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "http");
			if (object != null) {
				node.setHttp(node.createBinding());
				readBinding(object, (AsyncApi31Binding) node.getHttp());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "ws");
			if (object != null) {
				node.setWs(node.createBinding());
				readBinding(object, (AsyncApi31Binding) node.getWs());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "kafka");
			if (object != null) {
				node.setKafka(node.createBinding());
				readBinding(object, (AsyncApi31Binding) node.getKafka());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "anypointmq");
			if (object != null) {
				node.setAnypointmq(node.createBinding());
				readBinding(object, (AsyncApi31Binding) node.getAnypointmq());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "amqp");
			if (object != null) {
				node.setAmqp(node.createBinding());
				readBinding(object, (AsyncApi31Binding) node.getAmqp());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "amqp1");
			if (object != null) {
				node.setAmqp1(node.createBinding());
				readBinding(object, (AsyncApi31Binding) node.getAmqp1());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "mqtt");
			if (object != null) {
				node.setMqtt(node.createBinding());
				readBinding(object, (AsyncApi31Binding) node.getMqtt());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "mqtt5");
			if (object != null) {
				node.setMqtt5(node.createBinding());
				readBinding(object, (AsyncApi31Binding) node.getMqtt5());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "nats");
			if (object != null) {
				node.setNats(node.createBinding());
				readBinding(object, (AsyncApi31Binding) node.getNats());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "jms");
			if (object != null) {
				node.setJms(node.createBinding());
				readBinding(object, (AsyncApi31Binding) node.getJms());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "sns");
			if (object != null) {
				node.setSns(node.createBinding());
				readBinding(object, (AsyncApi31Binding) node.getSns());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "solace");
			if (object != null) {
				node.setSolace(node.createBinding());
				readBinding(object, (AsyncApi31Binding) node.getSolace());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "sqs");
			if (object != null) {
				node.setSqs(node.createBinding());
				readBinding(object, (AsyncApi31Binding) node.getSqs());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "stomp");
			if (object != null) {
				node.setStomp(node.createBinding());
				readBinding(object, (AsyncApi31Binding) node.getStomp());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "redis");
			if (object != null) {
				node.setRedis(node.createBinding());
				readBinding(object, (AsyncApi31Binding) node.getRedis());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "mercure");
			if (object != null) {
				node.setMercure(node.createBinding());
				readBinding(object, (AsyncApi31Binding) node.getMercure());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "ibmmq");
			if (object != null) {
				node.setIbmmq(node.createBinding());
				readBinding(object, (AsyncApi31Binding) node.getIbmmq());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "googlepubsub");
			if (object != null) {
				node.setGooglepubsub(node.createBinding());
				readBinding(object, (AsyncApi31Binding) node.getGooglepubsub());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "pulsar");
			if (object != null) {
				node.setPulsar(node.createBinding());
				readBinding(object, (AsyncApi31Binding) node.getPulsar());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "ros2");
			if (object != null) {
				node.setRos2(node.createBinding());
				readBinding(object, (AsyncApi31Binding) node.getRos2());
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

	public void readChannelBindings(ObjectNode json, AsyncApi31ChannelBindings node) {
		{
			String value = JsonUtil.consumeStringProperty(json, "$ref");
			node.set$ref(value);
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "http");
			if (object != null) {
				node.setHttp(node.createBinding());
				readBinding(object, (AsyncApi31Binding) node.getHttp());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "ws");
			if (object != null) {
				node.setWs(node.createBinding());
				readBinding(object, (AsyncApi31Binding) node.getWs());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "kafka");
			if (object != null) {
				node.setKafka(node.createBinding());
				readBinding(object, (AsyncApi31Binding) node.getKafka());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "anypointmq");
			if (object != null) {
				node.setAnypointmq(node.createBinding());
				readBinding(object, (AsyncApi31Binding) node.getAnypointmq());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "amqp");
			if (object != null) {
				node.setAmqp(node.createBinding());
				readBinding(object, (AsyncApi31Binding) node.getAmqp());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "amqp1");
			if (object != null) {
				node.setAmqp1(node.createBinding());
				readBinding(object, (AsyncApi31Binding) node.getAmqp1());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "mqtt");
			if (object != null) {
				node.setMqtt(node.createBinding());
				readBinding(object, (AsyncApi31Binding) node.getMqtt());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "mqtt5");
			if (object != null) {
				node.setMqtt5(node.createBinding());
				readBinding(object, (AsyncApi31Binding) node.getMqtt5());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "nats");
			if (object != null) {
				node.setNats(node.createBinding());
				readBinding(object, (AsyncApi31Binding) node.getNats());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "jms");
			if (object != null) {
				node.setJms(node.createBinding());
				readBinding(object, (AsyncApi31Binding) node.getJms());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "sns");
			if (object != null) {
				node.setSns(node.createBinding());
				readBinding(object, (AsyncApi31Binding) node.getSns());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "solace");
			if (object != null) {
				node.setSolace(node.createBinding());
				readBinding(object, (AsyncApi31Binding) node.getSolace());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "sqs");
			if (object != null) {
				node.setSqs(node.createBinding());
				readBinding(object, (AsyncApi31Binding) node.getSqs());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "stomp");
			if (object != null) {
				node.setStomp(node.createBinding());
				readBinding(object, (AsyncApi31Binding) node.getStomp());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "redis");
			if (object != null) {
				node.setRedis(node.createBinding());
				readBinding(object, (AsyncApi31Binding) node.getRedis());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "mercure");
			if (object != null) {
				node.setMercure(node.createBinding());
				readBinding(object, (AsyncApi31Binding) node.getMercure());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "ibmmq");
			if (object != null) {
				node.setIbmmq(node.createBinding());
				readBinding(object, (AsyncApi31Binding) node.getIbmmq());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "googlepubsub");
			if (object != null) {
				node.setGooglepubsub(node.createBinding());
				readBinding(object, (AsyncApi31Binding) node.getGooglepubsub());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "pulsar");
			if (object != null) {
				node.setPulsar(node.createBinding());
				readBinding(object, (AsyncApi31Binding) node.getPulsar());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "ros2");
			if (object != null) {
				node.setRos2(node.createBinding());
				readBinding(object, (AsyncApi31Binding) node.getRos2());
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

	public void readOperationBindings(ObjectNode json, AsyncApi31OperationBindings node) {
		{
			String value = JsonUtil.consumeStringProperty(json, "$ref");
			node.set$ref(value);
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "http");
			if (object != null) {
				node.setHttp(node.createBinding());
				readBinding(object, (AsyncApi31Binding) node.getHttp());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "ws");
			if (object != null) {
				node.setWs(node.createBinding());
				readBinding(object, (AsyncApi31Binding) node.getWs());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "kafka");
			if (object != null) {
				node.setKafka(node.createBinding());
				readBinding(object, (AsyncApi31Binding) node.getKafka());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "anypointmq");
			if (object != null) {
				node.setAnypointmq(node.createBinding());
				readBinding(object, (AsyncApi31Binding) node.getAnypointmq());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "amqp");
			if (object != null) {
				node.setAmqp(node.createBinding());
				readBinding(object, (AsyncApi31Binding) node.getAmqp());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "amqp1");
			if (object != null) {
				node.setAmqp1(node.createBinding());
				readBinding(object, (AsyncApi31Binding) node.getAmqp1());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "mqtt");
			if (object != null) {
				node.setMqtt(node.createBinding());
				readBinding(object, (AsyncApi31Binding) node.getMqtt());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "mqtt5");
			if (object != null) {
				node.setMqtt5(node.createBinding());
				readBinding(object, (AsyncApi31Binding) node.getMqtt5());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "nats");
			if (object != null) {
				node.setNats(node.createBinding());
				readBinding(object, (AsyncApi31Binding) node.getNats());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "jms");
			if (object != null) {
				node.setJms(node.createBinding());
				readBinding(object, (AsyncApi31Binding) node.getJms());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "sns");
			if (object != null) {
				node.setSns(node.createBinding());
				readBinding(object, (AsyncApi31Binding) node.getSns());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "solace");
			if (object != null) {
				node.setSolace(node.createBinding());
				readBinding(object, (AsyncApi31Binding) node.getSolace());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "sqs");
			if (object != null) {
				node.setSqs(node.createBinding());
				readBinding(object, (AsyncApi31Binding) node.getSqs());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "stomp");
			if (object != null) {
				node.setStomp(node.createBinding());
				readBinding(object, (AsyncApi31Binding) node.getStomp());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "redis");
			if (object != null) {
				node.setRedis(node.createBinding());
				readBinding(object, (AsyncApi31Binding) node.getRedis());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "mercure");
			if (object != null) {
				node.setMercure(node.createBinding());
				readBinding(object, (AsyncApi31Binding) node.getMercure());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "ibmmq");
			if (object != null) {
				node.setIbmmq(node.createBinding());
				readBinding(object, (AsyncApi31Binding) node.getIbmmq());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "googlepubsub");
			if (object != null) {
				node.setGooglepubsub(node.createBinding());
				readBinding(object, (AsyncApi31Binding) node.getGooglepubsub());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "pulsar");
			if (object != null) {
				node.setPulsar(node.createBinding());
				readBinding(object, (AsyncApi31Binding) node.getPulsar());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "ros2");
			if (object != null) {
				node.setRos2(node.createBinding());
				readBinding(object, (AsyncApi31Binding) node.getRos2());
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

	public void readMessageBindings(ObjectNode json, AsyncApi31MessageBindings node) {
		{
			String value = JsonUtil.consumeStringProperty(json, "$ref");
			node.set$ref(value);
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "http");
			if (object != null) {
				node.setHttp(node.createBinding());
				readBinding(object, (AsyncApi31Binding) node.getHttp());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "ws");
			if (object != null) {
				node.setWs(node.createBinding());
				readBinding(object, (AsyncApi31Binding) node.getWs());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "kafka");
			if (object != null) {
				node.setKafka(node.createBinding());
				readBinding(object, (AsyncApi31Binding) node.getKafka());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "anypointmq");
			if (object != null) {
				node.setAnypointmq(node.createBinding());
				readBinding(object, (AsyncApi31Binding) node.getAnypointmq());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "amqp");
			if (object != null) {
				node.setAmqp(node.createBinding());
				readBinding(object, (AsyncApi31Binding) node.getAmqp());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "amqp1");
			if (object != null) {
				node.setAmqp1(node.createBinding());
				readBinding(object, (AsyncApi31Binding) node.getAmqp1());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "mqtt");
			if (object != null) {
				node.setMqtt(node.createBinding());
				readBinding(object, (AsyncApi31Binding) node.getMqtt());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "mqtt5");
			if (object != null) {
				node.setMqtt5(node.createBinding());
				readBinding(object, (AsyncApi31Binding) node.getMqtt5());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "nats");
			if (object != null) {
				node.setNats(node.createBinding());
				readBinding(object, (AsyncApi31Binding) node.getNats());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "jms");
			if (object != null) {
				node.setJms(node.createBinding());
				readBinding(object, (AsyncApi31Binding) node.getJms());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "sns");
			if (object != null) {
				node.setSns(node.createBinding());
				readBinding(object, (AsyncApi31Binding) node.getSns());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "solace");
			if (object != null) {
				node.setSolace(node.createBinding());
				readBinding(object, (AsyncApi31Binding) node.getSolace());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "sqs");
			if (object != null) {
				node.setSqs(node.createBinding());
				readBinding(object, (AsyncApi31Binding) node.getSqs());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "stomp");
			if (object != null) {
				node.setStomp(node.createBinding());
				readBinding(object, (AsyncApi31Binding) node.getStomp());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "redis");
			if (object != null) {
				node.setRedis(node.createBinding());
				readBinding(object, (AsyncApi31Binding) node.getRedis());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "mercure");
			if (object != null) {
				node.setMercure(node.createBinding());
				readBinding(object, (AsyncApi31Binding) node.getMercure());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "ibmmq");
			if (object != null) {
				node.setIbmmq(node.createBinding());
				readBinding(object, (AsyncApi31Binding) node.getIbmmq());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "googlepubsub");
			if (object != null) {
				node.setGooglepubsub(node.createBinding());
				readBinding(object, (AsyncApi31Binding) node.getGooglepubsub());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "pulsar");
			if (object != null) {
				node.setPulsar(node.createBinding());
				readBinding(object, (AsyncApi31Binding) node.getPulsar());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "ros2");
			if (object != null) {
				node.setRos2(node.createBinding());
				readBinding(object, (AsyncApi31Binding) node.getRos2());
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

	public void readMessage(ObjectNode json, AsyncApi31Message node) {
		{
			String value = JsonUtil.consumeStringProperty(json, "$ref");
			node.set$ref(value);
		}
		{
			JsonNode value = JsonUtil.consumeAnyProperty(json, "headers");
			if (value != null) {
				if (JsonUtil.isObjectWithProperty(value, "schemaFormat")) {
					ObjectNode object = JsonUtil.toObject(value);
					node.setHeaders(node.createMultiFormatSchema());
					readMultiFormatSchema(object, (AsyncApi31MultiFormatSchema) node.getHeaders());
				} else if (JsonUtil.isObject(value)) {
					ObjectNode object = JsonUtil.toObject(value);
					node.setHeaders(node.createSchema());
					readSchema(object, (AsyncApi31Schema) node.getHeaders());
				} else {
					node.addExtraProperty("headers", value);
				}
			}
		}
		{
			JsonNode value = JsonUtil.consumeAnyProperty(json, "payload");
			if (value != null) {
				if (JsonUtil.isObjectWithProperty(value, "schemaFormat")) {
					ObjectNode object = JsonUtil.toObject(value);
					node.setPayload(node.createMultiFormatSchema());
					readMultiFormatSchema(object, (AsyncApi31MultiFormatSchema) node.getPayload());
				} else if (JsonUtil.isObject(value)) {
					ObjectNode object = JsonUtil.toObject(value);
					node.setPayload(node.createSchema());
					readSchema(object, (AsyncApi31Schema) node.getPayload());
				} else {
					node.addExtraProperty("payload", value);
				}
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "correlationId");
			if (object != null) {
				node.setCorrelationId(node.createCorrelationID());
				readCorrelationID(object, (AsyncApi31CorrelationID) node.getCorrelationId());
			}
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
					AsyncApi31Tag model = (AsyncApi31Tag) node.createTag();
					this.readTag(object, model);
					node.addTag(model);
				});
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "externalDocs");
			if (object != null) {
				node.setExternalDocs(node.createExternalDocumentation());
				readExternalDocumentation(object, (AsyncApi31ExternalDocumentation) node.getExternalDocs());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "bindings");
			if (object != null) {
				node.setBindings(node.createMessageBindings());
				readMessageBindings(object, (AsyncApi31MessageBindings) node.getBindings());
			}
		}
		{
			List<ObjectNode> objects = JsonUtil.consumeObjectArrayProperty(json, "examples");
			if (objects != null) {
				objects.forEach(object -> {
					AsyncApi31MessageExample model = (AsyncApi31MessageExample) node.createMessageExample();
					this.readMessageExample(object, model);
					node.addExample(model);
				});
			}
		}
		{
			List<ObjectNode> objects = JsonUtil.consumeObjectArrayProperty(json, "traits");
			if (objects != null) {
				objects.forEach(object -> {
					AsyncApi31MessageTrait model = (AsyncApi31MessageTrait) node.createMessageTrait();
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

	public void readMessageTrait(ObjectNode json, AsyncApi31MessageTrait node) {
		{
			String value = JsonUtil.consumeStringProperty(json, "$ref");
			node.set$ref(value);
		}
		{
			JsonNode value = JsonUtil.consumeAnyProperty(json, "headers");
			if (value != null) {
				if (JsonUtil.isObjectWithProperty(value, "schemaFormat")) {
					ObjectNode object = JsonUtil.toObject(value);
					node.setHeaders(node.createMultiFormatSchema());
					readMultiFormatSchema(object, (AsyncApi31MultiFormatSchema) node.getHeaders());
				} else if (JsonUtil.isObject(value)) {
					ObjectNode object = JsonUtil.toObject(value);
					node.setHeaders(node.createSchema());
					readSchema(object, (AsyncApi31Schema) node.getHeaders());
				} else {
					node.addExtraProperty("headers", value);
				}
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "correlationId");
			if (object != null) {
				node.setCorrelationId(node.createCorrelationID());
				readCorrelationID(object, (AsyncApi31CorrelationID) node.getCorrelationId());
			}
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
					AsyncApi31Tag model = (AsyncApi31Tag) node.createTag();
					this.readTag(object, model);
					node.addTag(model);
				});
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "externalDocs");
			if (object != null) {
				node.setExternalDocs(node.createExternalDocumentation());
				readExternalDocumentation(object, (AsyncApi31ExternalDocumentation) node.getExternalDocs());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "bindings");
			if (object != null) {
				node.setBindings(node.createMessageBindings());
				readMessageBindings(object, (AsyncApi31MessageBindings) node.getBindings());
			}
		}
		{
			List<ObjectNode> objects = JsonUtil.consumeObjectArrayProperty(json, "examples");
			if (objects != null) {
				objects.forEach(object -> {
					AsyncApi31MessageExample model = (AsyncApi31MessageExample) node.createMessageExample();
					this.readMessageExample(object, model);
					node.addExample(model);
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

	public void readMessageExample(ObjectNode json, AsyncApi31MessageExample node) {
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

	public void readTag(ObjectNode json, AsyncApi31Tag node) {
		{
			String value = JsonUtil.consumeStringProperty(json, "$ref");
			node.set$ref(value);
		}
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
				readExternalDocumentation(object, (AsyncApi31ExternalDocumentation) node.getExternalDocs());
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

	public void readExternalDocumentation(ObjectNode json, AsyncApi31ExternalDocumentation node) {
		{
			String value = JsonUtil.consumeStringProperty(json, "$ref");
			node.set$ref(value);
		}
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

	public void readReference(ObjectNode json, AsyncApi31Reference node) {
		{
			String value = JsonUtil.consumeStringProperty(json, "$ref");
			node.set$ref(value);
		}
		ReaderUtil.readExtraProperties(json, node);
	}

	public void readComponents(ObjectNode json, AsyncApi31Components node) {
		{
			ObjectNode mapObject = JsonUtil.consumeObjectProperty(json, "schemas");
			if (mapObject != null) {
				List<String> keys = JsonUtil.keys(mapObject);
				keys.forEach(key -> {
					JsonNode value = JsonUtil.consumeAnyProperty(mapObject, key);
					if (value != null) {
						if (JsonUtil.isObjectWithProperty(value, "schemaFormat")) {
							ObjectNode object = JsonUtil.toObject(value);
							AsyncApi31MultiFormatSchema model = (AsyncApi31MultiFormatSchema) node
									.createMultiFormatSchema();
							readMultiFormatSchema(object, model);
							node.addSchema(key, model);
						} else if (JsonUtil.isObject(value)) {
							ObjectNode object = JsonUtil.toObject(value);
							AsyncApi31Schema model = (AsyncApi31Schema) node.createSchema();
							readSchema(object, model);
							node.addSchema(key, model);
						}
					}
				});
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "servers");
			JsonUtil.keys(object).forEach(name -> {
				ObjectNode mapValue = JsonUtil.consumeObjectProperty(object, name);
				if (mapValue != null) {
					AsyncApi31Server model = (AsyncApi31Server) node.createServer();
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
					AsyncApi31Channel model = (AsyncApi31Channel) node.createChannel();
					this.readChannel(mapValue, model);
					node.addChannel(name, model);
				}
			});
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "operations");
			JsonUtil.keys(object).forEach(name -> {
				ObjectNode mapValue = JsonUtil.consumeObjectProperty(object, name);
				if (mapValue != null) {
					AsyncApi31Operation model = (AsyncApi31Operation) node.createOperation();
					this.readOperation(mapValue, model);
					node.addOperation(name, model);
				}
			});
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "messages");
			JsonUtil.keys(object).forEach(name -> {
				ObjectNode mapValue = JsonUtil.consumeObjectProperty(object, name);
				if (mapValue != null) {
					AsyncApi31Message model = (AsyncApi31Message) node.createMessage();
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
					AsyncApi31SecurityScheme model = (AsyncApi31SecurityScheme) node.createSecurityScheme();
					this.readSecurityScheme(mapValue, model);
					node.addSecurityScheme(name, model);
				}
			});
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "serverVariables");
			JsonUtil.keys(object).forEach(name -> {
				ObjectNode mapValue = JsonUtil.consumeObjectProperty(object, name);
				if (mapValue != null) {
					AsyncApi31ServerVariable model = (AsyncApi31ServerVariable) node.createServerVariable();
					this.readServerVariable(mapValue, model);
					node.addServerVariable(name, model);
				}
			});
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "parameters");
			JsonUtil.keys(object).forEach(name -> {
				ObjectNode mapValue = JsonUtil.consumeObjectProperty(object, name);
				if (mapValue != null) {
					AsyncApi31Parameter model = (AsyncApi31Parameter) node.createParameter();
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
					AsyncApi31CorrelationID model = (AsyncApi31CorrelationID) node.createCorrelationID();
					this.readCorrelationID(mapValue, model);
					node.addCorrelationId(name, model);
				}
			});
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "replies");
			JsonUtil.keys(object).forEach(name -> {
				ObjectNode mapValue = JsonUtil.consumeObjectProperty(object, name);
				if (mapValue != null) {
					AsyncApi31OperationReply model = (AsyncApi31OperationReply) node.createOperationReply();
					this.readOperationReply(mapValue, model);
					node.addReply(name, model);
				}
			});
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "replyAddresses");
			JsonUtil.keys(object).forEach(name -> {
				ObjectNode mapValue = JsonUtil.consumeObjectProperty(object, name);
				if (mapValue != null) {
					AsyncApi31OperationReplyAddress model = (AsyncApi31OperationReplyAddress) node
							.createOperationReplyAddress();
					this.readOperationReplyAddress(mapValue, model);
					node.addReplyAddress(name, model);
				}
			});
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "externalDocs");
			JsonUtil.keys(object).forEach(name -> {
				ObjectNode mapValue = JsonUtil.consumeObjectProperty(object, name);
				if (mapValue != null) {
					AsyncApi31ExternalDocumentation model = (AsyncApi31ExternalDocumentation) node
							.createExternalDocumentation();
					this.readExternalDocumentation(mapValue, model);
					node.addExternalDoc(name, model);
				}
			});
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "tags");
			JsonUtil.keys(object).forEach(name -> {
				ObjectNode mapValue = JsonUtil.consumeObjectProperty(object, name);
				if (mapValue != null) {
					AsyncApi31Tag model = (AsyncApi31Tag) node.createTag();
					this.readTag(mapValue, model);
					node.addTag(name, model);
				}
			});
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "operationTraits");
			JsonUtil.keys(object).forEach(name -> {
				ObjectNode mapValue = JsonUtil.consumeObjectProperty(object, name);
				if (mapValue != null) {
					AsyncApi31OperationTrait model = (AsyncApi31OperationTrait) node.createOperationTrait();
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
					AsyncApi31MessageTrait model = (AsyncApi31MessageTrait) node.createMessageTrait();
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
					AsyncApi31ServerBindings model = (AsyncApi31ServerBindings) node.createServerBindings();
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
					AsyncApi31ChannelBindings model = (AsyncApi31ChannelBindings) node.createChannelBindings();
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
					AsyncApi31OperationBindings model = (AsyncApi31OperationBindings) node.createOperationBindings();
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
					AsyncApi31MessageBindings model = (AsyncApi31MessageBindings) node.createMessageBindings();
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

	public void readMultiFormatSchema(ObjectNode json, AsyncApi31MultiFormatSchema node) {
		{
			String value = JsonUtil.consumeStringProperty(json, "$ref");
			node.set$ref(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "schemaFormat");
			node.setSchemaFormat(value);
		}
		{
			JsonNode value = JsonUtil.consumeAnyProperty(json, "schema");
			if (value != null) {
				if (JsonUtil.isObject(value)) {
					ObjectNode object = JsonUtil.toObject(value);
					node.setSchema(node.createSchema());
					readSchema(object, (AsyncApi31Schema) node.getSchema());
				} else if (JsonUtil.isJsonNode(value)) {
					JsonNode pValue = JsonUtil.toJsonNode(value);
					AnyUnionValue unionValue = new AnyUnionValueImpl(pValue);
					node.setSchema(unionValue);
				} else {
					node.addExtraProperty("schema", value);
				}
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

	public void readSchema(ObjectNode json, AsyncApi31Schema node) {
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
				readSchema(object, (AsyncApi31Schema) node.getIf());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "then");
			if (object != null) {
				node.setThen(node.createSchema());
				readSchema(object, (AsyncApi31Schema) node.getThen());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "else");
			if (object != null) {
				node.setElse(node.createSchema());
				readSchema(object, (AsyncApi31Schema) node.getElse());
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
					AsyncApi31Schema model = (AsyncApi31Schema) node.createSchema();
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
					readSchema(object, (AsyncApi31Schema) node.getAdditionalProperties());
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
				readSchema(object, (AsyncApi31Schema) node.getAdditionalItems());
			}
		}
		{
			JsonNode value = JsonUtil.consumeAnyProperty(json, "items");
			if (value != null) {
				if (JsonUtil.isObject(value)) {
					ObjectNode object = JsonUtil.toObject(value);
					node.setItems(node.createSchema());
					readSchema(object, (AsyncApi31Schema) node.getItems());
				} else if (JsonUtil.isArray(value)) {
					List<JsonNode> array = JsonUtil.toList(value);
					List<AsyncApi31Schema> models = new ArrayList<>();
					array.forEach(item -> {
						ObjectNode object = JsonUtil.toObject(item);
						AsyncApi31Schema model = (AsyncApi31Schema) node.createSchema();
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
				readSchema(object, (AsyncApi31Schema) node.getPropertyNames());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "contains");
			if (object != null) {
				node.setContains(node.createSchema());
				readSchema(object, (AsyncApi31Schema) node.getContains());
			}
		}
		{
			List<ObjectNode> objects = JsonUtil.consumeObjectArrayProperty(json, "allOf");
			if (objects != null) {
				objects.forEach(object -> {
					AsyncApi31Schema model = (AsyncApi31Schema) node.createSchema();
					this.readSchema(object, model);
					node.addAllOf(model);
				});
			}
		}
		{
			List<ObjectNode> objects = JsonUtil.consumeObjectArrayProperty(json, "oneOf");
			if (objects != null) {
				objects.forEach(object -> {
					AsyncApi31Schema model = (AsyncApi31Schema) node.createSchema();
					this.readSchema(object, model);
					node.addOneOf(model);
				});
			}
		}
		{
			List<ObjectNode> objects = JsonUtil.consumeObjectArrayProperty(json, "anyOf");
			if (objects != null) {
				objects.forEach(object -> {
					AsyncApi31Schema model = (AsyncApi31Schema) node.createSchema();
					this.readSchema(object, model);
					node.addAnyOf(model);
				});
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "not");
			if (object != null) {
				node.setNot(node.createSchema());
				readSchema(object, (AsyncApi31Schema) node.getNot());
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
				readExternalDocumentation(object, (AsyncApi31ExternalDocumentation) node.getExternalDocs());
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

	public void readSecurityScheme(ObjectNode json, AsyncApi31SecurityScheme node) {
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
				readOAuthFlows(object, (AsyncApi31OAuthFlows) node.getFlows());
			}
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "openIdConnectUrl");
			node.setOpenIdConnectUrl(value);
		}
		{
			List<String> value = JsonUtil.consumeStringArrayProperty(json, "scopes");
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

	public void readOAuthFlows(ObjectNode json, AsyncApi31OAuthFlows node) {
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "implicit");
			if (object != null) {
				node.setImplicit(node.createOAuthFlow());
				readOAuthFlow(object, (AsyncApi31OAuthFlow) node.getImplicit());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "password");
			if (object != null) {
				node.setPassword(node.createOAuthFlow());
				readOAuthFlow(object, (AsyncApi31OAuthFlow) node.getPassword());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "clientCredentials");
			if (object != null) {
				node.setClientCredentials(node.createOAuthFlow());
				readOAuthFlow(object, (AsyncApi31OAuthFlow) node.getClientCredentials());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "authorizationCode");
			if (object != null) {
				node.setAuthorizationCode(node.createOAuthFlow());
				readOAuthFlow(object, (AsyncApi31OAuthFlow) node.getAuthorizationCode());
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

	public void readOAuthFlow(ObjectNode json, AsyncApi31OAuthFlow node) {
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
			Map<String, String> value = JsonUtil.consumeStringMapProperty(json, "availableScopes");
			node.setAvailableScopes(value);
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

	public void readCorrelationID(ObjectNode json, AsyncApi31CorrelationID node) {
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

	public void readBinding(ObjectNode json, AsyncApi31Binding node) {
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