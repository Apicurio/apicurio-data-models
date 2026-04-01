package io.apicurio.datamodels.models.openapi.v3x.v31.io;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.apicurio.datamodels.models.RootNode;
import io.apicurio.datamodels.models.io.ModelReader;
import io.apicurio.datamodels.models.openapi.v3x.v31.OpenApi31Callback;
import io.apicurio.datamodels.models.openapi.v3x.v31.OpenApi31Components;
import io.apicurio.datamodels.models.openapi.v3x.v31.OpenApi31Contact;
import io.apicurio.datamodels.models.openapi.v3x.v31.OpenApi31Discriminator;
import io.apicurio.datamodels.models.openapi.v3x.v31.OpenApi31Document;
import io.apicurio.datamodels.models.openapi.v3x.v31.OpenApi31DocumentImpl;
import io.apicurio.datamodels.models.openapi.v3x.v31.OpenApi31Encoding;
import io.apicurio.datamodels.models.openapi.v3x.v31.OpenApi31Example;
import io.apicurio.datamodels.models.openapi.v3x.v31.OpenApi31ExternalDocumentation;
import io.apicurio.datamodels.models.openapi.v3x.v31.OpenApi31Header;
import io.apicurio.datamodels.models.openapi.v3x.v31.OpenApi31Info;
import io.apicurio.datamodels.models.openapi.v3x.v31.OpenApi31License;
import io.apicurio.datamodels.models.openapi.v3x.v31.OpenApi31Link;
import io.apicurio.datamodels.models.openapi.v3x.v31.OpenApi31MediaType;
import io.apicurio.datamodels.models.openapi.v3x.v31.OpenApi31OAuthFlow;
import io.apicurio.datamodels.models.openapi.v3x.v31.OpenApi31OAuthFlows;
import io.apicurio.datamodels.models.openapi.v3x.v31.OpenApi31Operation;
import io.apicurio.datamodels.models.openapi.v3x.v31.OpenApi31Parameter;
import io.apicurio.datamodels.models.openapi.v3x.v31.OpenApi31PathItem;
import io.apicurio.datamodels.models.openapi.v3x.v31.OpenApi31Paths;
import io.apicurio.datamodels.models.openapi.v3x.v31.OpenApi31RequestBody;
import io.apicurio.datamodels.models.openapi.v3x.v31.OpenApi31Response;
import io.apicurio.datamodels.models.openapi.v3x.v31.OpenApi31Responses;
import io.apicurio.datamodels.models.openapi.v3x.v31.OpenApi31Schema;
import io.apicurio.datamodels.models.openapi.v3x.v31.OpenApi31SecurityRequirement;
import io.apicurio.datamodels.models.openapi.v3x.v31.OpenApi31SecurityScheme;
import io.apicurio.datamodels.models.openapi.v3x.v31.OpenApi31Server;
import io.apicurio.datamodels.models.openapi.v3x.v31.OpenApi31ServerVariable;
import io.apicurio.datamodels.models.openapi.v3x.v31.OpenApi31Tag;
import io.apicurio.datamodels.models.openapi.v3x.v31.OpenApi31XML;
import io.apicurio.datamodels.models.union.BooleanUnionValue;
import io.apicurio.datamodels.models.union.BooleanUnionValueImpl;
import io.apicurio.datamodels.models.union.StringListUnionValue;
import io.apicurio.datamodels.models.union.StringListUnionValueImpl;
import io.apicurio.datamodels.models.union.StringUnionValue;
import io.apicurio.datamodels.models.union.StringUnionValueImpl;
import io.apicurio.datamodels.models.util.JsonUtil;
import io.apicurio.datamodels.models.util.ReaderUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OpenApi31ModelReader implements ModelReader {

	public void readDocument(ObjectNode json, OpenApi31Document node) {
		{
			String value = JsonUtil.consumeStringProperty(json, "openapi");
			node.setOpenapi(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "jsonSchemaDialect");
			node.setJsonSchemaDialect(value);
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "info");
			if (object != null) {
				node.setInfo(node.createInfo());
				readInfo(object, (OpenApi31Info) node.getInfo());
			}
		}
		{
			List<ObjectNode> objects = JsonUtil.consumeObjectArrayProperty(json, "servers");
			if (objects != null) {
				objects.forEach(object -> {
					OpenApi31Server model = (OpenApi31Server) node.createServer();
					this.readServer(object, model);
					node.addServer(model);
				});
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "paths");
			if (object != null) {
				node.setPaths(node.createPaths());
				readPaths(object, (OpenApi31Paths) node.getPaths());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "webhooks");
			JsonUtil.keys(object).forEach(name -> {
				ObjectNode mapValue = JsonUtil.consumeObjectProperty(object, name);
				if (mapValue != null) {
					OpenApi31PathItem model = (OpenApi31PathItem) node.createPathItem();
					this.readPathItem(mapValue, model);
					node.addWebhook(name, model);
				}
			});
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "components");
			if (object != null) {
				node.setComponents(node.createComponents());
				readComponents(object, (OpenApi31Components) node.getComponents());
			}
		}
		{
			List<ObjectNode> objects = JsonUtil.consumeObjectArrayProperty(json, "security");
			if (objects != null) {
				objects.forEach(object -> {
					OpenApi31SecurityRequirement model = (OpenApi31SecurityRequirement) node
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
					OpenApi31Tag model = (OpenApi31Tag) node.createTag();
					this.readTag(object, model);
					node.addTag(model);
				});
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "externalDocs");
			if (object != null) {
				node.setExternalDocs(node.createExternalDocumentation());
				readExternalDocumentation(object, (OpenApi31ExternalDocumentation) node.getExternalDocs());
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
		OpenApi31Document rootModel = new OpenApi31DocumentImpl();
		this.readDocument(json, rootModel);
		return rootModel;
	}

	public void readInfo(ObjectNode json, OpenApi31Info node) {
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
			String value = JsonUtil.consumeStringProperty(json, "termsOfService");
			node.setTermsOfService(value);
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "contact");
			if (object != null) {
				node.setContact(node.createContact());
				readContact(object, (OpenApi31Contact) node.getContact());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "license");
			if (object != null) {
				node.setLicense(node.createLicense());
				readLicense(object, (OpenApi31License) node.getLicense());
			}
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "version");
			node.setVersion(value);
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

	public void readContact(ObjectNode json, OpenApi31Contact node) {
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

	public void readLicense(ObjectNode json, OpenApi31License node) {
		{
			String value = JsonUtil.consumeStringProperty(json, "name");
			node.setName(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "identifier");
			node.setIdentifier(value);
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

	public void readServer(ObjectNode json, OpenApi31Server node) {
		{
			String value = JsonUtil.consumeStringProperty(json, "url");
			node.setUrl(value);
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
					OpenApi31ServerVariable model = (OpenApi31ServerVariable) node.createServerVariable();
					this.readServerVariable(mapValue, model);
					node.addVariable(name, model);
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

	public void readServerVariable(ObjectNode json, OpenApi31ServerVariable node) {
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
			List<String> propertyNames = JsonUtil.matchingKeys("^x-.+$", json);
			propertyNames.forEach(name -> {
				JsonNode value = JsonUtil.consumeAnyProperty(json, name);
				node.addExtension(name, value);
			});
		}
		ReaderUtil.readExtraProperties(json, node);
	}

	public void readComponents(ObjectNode json, OpenApi31Components node) {
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "schemas");
			JsonUtil.keys(object).forEach(name -> {
				ObjectNode mapValue = JsonUtil.consumeObjectProperty(object, name);
				if (mapValue != null) {
					OpenApi31Schema model = (OpenApi31Schema) node.createSchema();
					this.readSchema(mapValue, model);
					node.addSchema(name, model);
				}
			});
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "responses");
			JsonUtil.keys(object).forEach(name -> {
				ObjectNode mapValue = JsonUtil.consumeObjectProperty(object, name);
				if (mapValue != null) {
					OpenApi31Response model = (OpenApi31Response) node.createResponse();
					this.readResponse(mapValue, model);
					node.addResponse(name, model);
				}
			});
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "parameters");
			JsonUtil.keys(object).forEach(name -> {
				ObjectNode mapValue = JsonUtil.consumeObjectProperty(object, name);
				if (mapValue != null) {
					OpenApi31Parameter model = (OpenApi31Parameter) node.createParameter();
					this.readParameter(mapValue, model);
					node.addParameter(name, model);
				}
			});
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "examples");
			JsonUtil.keys(object).forEach(name -> {
				ObjectNode mapValue = JsonUtil.consumeObjectProperty(object, name);
				if (mapValue != null) {
					OpenApi31Example model = (OpenApi31Example) node.createExample();
					this.readExample(mapValue, model);
					node.addExample(name, model);
				}
			});
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "requestBodies");
			JsonUtil.keys(object).forEach(name -> {
				ObjectNode mapValue = JsonUtil.consumeObjectProperty(object, name);
				if (mapValue != null) {
					OpenApi31RequestBody model = (OpenApi31RequestBody) node.createRequestBody();
					this.readRequestBody(mapValue, model);
					node.addRequestBody(name, model);
				}
			});
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "headers");
			JsonUtil.keys(object).forEach(name -> {
				ObjectNode mapValue = JsonUtil.consumeObjectProperty(object, name);
				if (mapValue != null) {
					OpenApi31Header model = (OpenApi31Header) node.createHeader();
					this.readHeader(mapValue, model);
					node.addHeader(name, model);
				}
			});
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "securitySchemes");
			JsonUtil.keys(object).forEach(name -> {
				ObjectNode mapValue = JsonUtil.consumeObjectProperty(object, name);
				if (mapValue != null) {
					OpenApi31SecurityScheme model = (OpenApi31SecurityScheme) node.createSecurityScheme();
					this.readSecurityScheme(mapValue, model);
					node.addSecurityScheme(name, model);
				}
			});
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "links");
			JsonUtil.keys(object).forEach(name -> {
				ObjectNode mapValue = JsonUtil.consumeObjectProperty(object, name);
				if (mapValue != null) {
					OpenApi31Link model = (OpenApi31Link) node.createLink();
					this.readLink(mapValue, model);
					node.addLink(name, model);
				}
			});
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "callbacks");
			JsonUtil.keys(object).forEach(name -> {
				ObjectNode mapValue = JsonUtil.consumeObjectProperty(object, name);
				if (mapValue != null) {
					OpenApi31Callback model = (OpenApi31Callback) node.createCallback();
					this.readCallback(mapValue, model);
					node.addCallback(name, model);
				}
			});
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "pathItems");
			JsonUtil.keys(object).forEach(name -> {
				ObjectNode mapValue = JsonUtil.consumeObjectProperty(object, name);
				if (mapValue != null) {
					OpenApi31PathItem model = (OpenApi31PathItem) node.createPathItem();
					this.readPathItem(mapValue, model);
					node.addPathItem(name, model);
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

	public void readPaths(ObjectNode json, OpenApi31Paths node) {
		{
			List<String> propertyNames = JsonUtil.matchingKeys("^x-.+$", json);
			propertyNames.forEach(name -> {
				JsonNode value = JsonUtil.consumeAnyProperty(json, name);
				node.addExtension(name, value);
			});
		}
		{
			List<String> propertyNames = JsonUtil.keys(json);
			propertyNames.forEach(name -> {
				ObjectNode object = JsonUtil.consumeObjectProperty(json, name);
				if (object != null) {
					OpenApi31PathItem model = (OpenApi31PathItem) node.createPathItem();
					this.readPathItem(object, model);
					node.addItem(name, model);
				}
			});
		}
		ReaderUtil.readExtraProperties(json, node);
	}

	public void readPathItem(ObjectNode json, OpenApi31PathItem node) {
		{
			String value = JsonUtil.consumeStringProperty(json, "$ref");
			node.set$ref(value);
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
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "get");
			if (object != null) {
				node.setGet(node.createOperation());
				readOperation(object, (OpenApi31Operation) node.getGet());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "put");
			if (object != null) {
				node.setPut(node.createOperation());
				readOperation(object, (OpenApi31Operation) node.getPut());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "post");
			if (object != null) {
				node.setPost(node.createOperation());
				readOperation(object, (OpenApi31Operation) node.getPost());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "delete");
			if (object != null) {
				node.setDelete(node.createOperation());
				readOperation(object, (OpenApi31Operation) node.getDelete());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "options");
			if (object != null) {
				node.setOptions(node.createOperation());
				readOperation(object, (OpenApi31Operation) node.getOptions());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "head");
			if (object != null) {
				node.setHead(node.createOperation());
				readOperation(object, (OpenApi31Operation) node.getHead());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "patch");
			if (object != null) {
				node.setPatch(node.createOperation());
				readOperation(object, (OpenApi31Operation) node.getPatch());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "trace");
			if (object != null) {
				node.setTrace(node.createOperation());
				readOperation(object, (OpenApi31Operation) node.getTrace());
			}
		}
		{
			List<ObjectNode> objects = JsonUtil.consumeObjectArrayProperty(json, "servers");
			if (objects != null) {
				objects.forEach(object -> {
					OpenApi31Server model = (OpenApi31Server) node.createServer();
					this.readServer(object, model);
					node.addServer(model);
				});
			}
		}
		{
			List<ObjectNode> objects = JsonUtil.consumeObjectArrayProperty(json, "parameters");
			if (objects != null) {
				objects.forEach(object -> {
					OpenApi31Parameter model = (OpenApi31Parameter) node.createParameter();
					this.readParameter(object, model);
					node.addParameter(model);
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

	public void readOperation(ObjectNode json, OpenApi31Operation node) {
		{
			List<String> value = JsonUtil.consumeStringArrayProperty(json, "tags");
			node.setTags(value);
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
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "externalDocs");
			if (object != null) {
				node.setExternalDocs(node.createExternalDocumentation());
				readExternalDocumentation(object, (OpenApi31ExternalDocumentation) node.getExternalDocs());
			}
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "operationId");
			node.setOperationId(value);
		}
		{
			List<ObjectNode> objects = JsonUtil.consumeObjectArrayProperty(json, "parameters");
			if (objects != null) {
				objects.forEach(object -> {
					OpenApi31Parameter model = (OpenApi31Parameter) node.createParameter();
					this.readParameter(object, model);
					node.addParameter(model);
				});
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "requestBody");
			if (object != null) {
				node.setRequestBody(node.createRequestBody());
				readRequestBody(object, (OpenApi31RequestBody) node.getRequestBody());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "responses");
			if (object != null) {
				node.setResponses(node.createResponses());
				readResponses(object, (OpenApi31Responses) node.getResponses());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "callbacks");
			JsonUtil.keys(object).forEach(name -> {
				ObjectNode mapValue = JsonUtil.consumeObjectProperty(object, name);
				if (mapValue != null) {
					OpenApi31Callback model = (OpenApi31Callback) node.createCallback();
					this.readCallback(mapValue, model);
					node.addCallback(name, model);
				}
			});
		}
		{
			Boolean value = JsonUtil.consumeBooleanProperty(json, "deprecated");
			node.setDeprecated(value);
		}
		{
			List<ObjectNode> objects = JsonUtil.consumeObjectArrayProperty(json, "security");
			if (objects != null) {
				objects.forEach(object -> {
					OpenApi31SecurityRequirement model = (OpenApi31SecurityRequirement) node
							.createSecurityRequirement();
					this.readSecurityRequirement(object, model);
					node.addSecurity(model);
				});
			}
		}
		{
			List<ObjectNode> objects = JsonUtil.consumeObjectArrayProperty(json, "servers");
			if (objects != null) {
				objects.forEach(object -> {
					OpenApi31Server model = (OpenApi31Server) node.createServer();
					this.readServer(object, model);
					node.addServer(model);
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

	public void readExternalDocumentation(ObjectNode json, OpenApi31ExternalDocumentation node) {
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

	public void readParameter(ObjectNode json, OpenApi31Parameter node) {
		{
			String value = JsonUtil.consumeStringProperty(json, "$ref");
			node.set$ref(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "summary");
			node.setSummary(value);
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
			String value = JsonUtil.consumeStringProperty(json, "description");
			node.setDescription(value);
		}
		{
			Boolean value = JsonUtil.consumeBooleanProperty(json, "required");
			node.setRequired(value);
		}
		{
			Boolean value = JsonUtil.consumeBooleanProperty(json, "deprecated");
			node.setDeprecated(value);
		}
		{
			Boolean value = JsonUtil.consumeBooleanProperty(json, "allowEmptyValue");
			node.setAllowEmptyValue(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "style");
			node.setStyle(value);
		}
		{
			Boolean value = JsonUtil.consumeBooleanProperty(json, "explode");
			node.setExplode(value);
		}
		{
			Boolean value = JsonUtil.consumeBooleanProperty(json, "allowReserved");
			node.setAllowReserved(value);
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "schema");
			if (object != null) {
				node.setSchema(node.createSchema());
				readSchema(object, (OpenApi31Schema) node.getSchema());
			}
		}
		{
			JsonNode value = JsonUtil.consumeAnyProperty(json, "example");
			node.setExample(value);
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "examples");
			JsonUtil.keys(object).forEach(name -> {
				ObjectNode mapValue = JsonUtil.consumeObjectProperty(object, name);
				if (mapValue != null) {
					OpenApi31Example model = (OpenApi31Example) node.createExample();
					this.readExample(mapValue, model);
					node.addExample(name, model);
				}
			});
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "content");
			JsonUtil.keys(object).forEach(name -> {
				ObjectNode mapValue = JsonUtil.consumeObjectProperty(object, name);
				if (mapValue != null) {
					OpenApi31MediaType model = (OpenApi31MediaType) node.createMediaType();
					this.readMediaType(mapValue, model);
					node.addContent(name, model);
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

	public void readRequestBody(ObjectNode json, OpenApi31RequestBody node) {
		{
			String value = JsonUtil.consumeStringProperty(json, "$ref");
			node.set$ref(value);
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
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "content");
			JsonUtil.keys(object).forEach(name -> {
				ObjectNode mapValue = JsonUtil.consumeObjectProperty(object, name);
				if (mapValue != null) {
					OpenApi31MediaType model = (OpenApi31MediaType) node.createMediaType();
					this.readMediaType(mapValue, model);
					node.addContent(name, model);
				}
			});
		}
		{
			Boolean value = JsonUtil.consumeBooleanProperty(json, "required");
			node.setRequired(value);
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

	public void readMediaType(ObjectNode json, OpenApi31MediaType node) {
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "schema");
			if (object != null) {
				node.setSchema(node.createSchema());
				readSchema(object, (OpenApi31Schema) node.getSchema());
			}
		}
		{
			JsonNode value = JsonUtil.consumeAnyProperty(json, "example");
			node.setExample(value);
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "examples");
			JsonUtil.keys(object).forEach(name -> {
				ObjectNode mapValue = JsonUtil.consumeObjectProperty(object, name);
				if (mapValue != null) {
					OpenApi31Example model = (OpenApi31Example) node.createExample();
					this.readExample(mapValue, model);
					node.addExample(name, model);
				}
			});
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "encoding");
			JsonUtil.keys(object).forEach(name -> {
				ObjectNode mapValue = JsonUtil.consumeObjectProperty(object, name);
				if (mapValue != null) {
					OpenApi31Encoding model = (OpenApi31Encoding) node.createEncoding();
					this.readEncoding(mapValue, model);
					node.addEncoding(name, model);
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

	public void readEncoding(ObjectNode json, OpenApi31Encoding node) {
		{
			String value = JsonUtil.consumeStringProperty(json, "contentType");
			node.setContentType(value);
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "headers");
			JsonUtil.keys(object).forEach(name -> {
				ObjectNode mapValue = JsonUtil.consumeObjectProperty(object, name);
				if (mapValue != null) {
					OpenApi31Header model = (OpenApi31Header) node.createHeader();
					this.readHeader(mapValue, model);
					node.addHeader(name, model);
				}
			});
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "style");
			node.setStyle(value);
		}
		{
			Boolean value = JsonUtil.consumeBooleanProperty(json, "explode");
			node.setExplode(value);
		}
		{
			Boolean value = JsonUtil.consumeBooleanProperty(json, "allowReserved");
			node.setAllowReserved(value);
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

	public void readResponses(ObjectNode json, OpenApi31Responses node) {
		{
			String value = JsonUtil.consumeStringProperty(json, "$ref");
			node.set$ref(value);
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
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "default");
			if (object != null) {
				node.setDefault(node.createResponse());
				readResponse(object, (OpenApi31Response) node.getDefault());
			}
		}
		{
			List<String> propertyNames = JsonUtil.keys(json);
			propertyNames.forEach(name -> {
				ObjectNode object = JsonUtil.consumeObjectProperty(json, name);
				if (object != null) {
					OpenApi31Response model = (OpenApi31Response) node.createResponse();
					this.readResponse(object, model);
					node.addItem(name, model);
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

	public void readResponse(ObjectNode json, OpenApi31Response node) {
		{
			String value = JsonUtil.consumeStringProperty(json, "$ref");
			node.set$ref(value);
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
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "headers");
			JsonUtil.keys(object).forEach(name -> {
				ObjectNode mapValue = JsonUtil.consumeObjectProperty(object, name);
				if (mapValue != null) {
					OpenApi31Header model = (OpenApi31Header) node.createHeader();
					this.readHeader(mapValue, model);
					node.addHeader(name, model);
				}
			});
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "content");
			JsonUtil.keys(object).forEach(name -> {
				ObjectNode mapValue = JsonUtil.consumeObjectProperty(object, name);
				if (mapValue != null) {
					OpenApi31MediaType model = (OpenApi31MediaType) node.createMediaType();
					this.readMediaType(mapValue, model);
					node.addContent(name, model);
				}
			});
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "links");
			JsonUtil.keys(object).forEach(name -> {
				ObjectNode mapValue = JsonUtil.consumeObjectProperty(object, name);
				if (mapValue != null) {
					OpenApi31Link model = (OpenApi31Link) node.createLink();
					this.readLink(mapValue, model);
					node.addLink(name, model);
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

	public void readCallback(ObjectNode json, OpenApi31Callback node) {
		{
			String value = JsonUtil.consumeStringProperty(json, "$ref");
			node.set$ref(value);
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
			List<String> propertyNames = JsonUtil.keys(json);
			propertyNames.forEach(name -> {
				ObjectNode object = JsonUtil.consumeObjectProperty(json, name);
				if (object != null) {
					OpenApi31PathItem model = (OpenApi31PathItem) node.createPathItem();
					this.readPathItem(object, model);
					node.addItem(name, model);
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

	public void readExample(ObjectNode json, OpenApi31Example node) {
		{
			String value = JsonUtil.consumeStringProperty(json, "$ref");
			node.set$ref(value);
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
			JsonNode value = JsonUtil.consumeAnyProperty(json, "value");
			node.setValue(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "externalValue");
			node.setExternalValue(value);
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

	public void readLink(ObjectNode json, OpenApi31Link node) {
		{
			String value = JsonUtil.consumeStringProperty(json, "$ref");
			node.set$ref(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "summary");
			node.setSummary(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "operationRef");
			node.setOperationRef(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "operationId");
			node.setOperationId(value);
		}
		{
			Map<String, JsonNode> value = JsonUtil.consumeAnyMapProperty(json, "parameters");
			node.setParameters(value);
		}
		{
			JsonNode value = JsonUtil.consumeAnyProperty(json, "requestBody");
			node.setRequestBody(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "description");
			node.setDescription(value);
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "server");
			if (object != null) {
				node.setServer(node.createServer());
				readServer(object, (OpenApi31Server) node.getServer());
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

	public void readHeader(ObjectNode json, OpenApi31Header node) {
		{
			String value = JsonUtil.consumeStringProperty(json, "$ref");
			node.set$ref(value);
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
			Boolean value = JsonUtil.consumeBooleanProperty(json, "required");
			node.setRequired(value);
		}
		{
			Boolean value = JsonUtil.consumeBooleanProperty(json, "deprecated");
			node.setDeprecated(value);
		}
		{
			Boolean value = JsonUtil.consumeBooleanProperty(json, "allowEmptyValue");
			node.setAllowEmptyValue(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "style");
			node.setStyle(value);
		}
		{
			Boolean value = JsonUtil.consumeBooleanProperty(json, "explode");
			node.setExplode(value);
		}
		{
			Boolean value = JsonUtil.consumeBooleanProperty(json, "allowReserved");
			node.setAllowReserved(value);
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "schema");
			if (object != null) {
				node.setSchema(node.createSchema());
				readSchema(object, (OpenApi31Schema) node.getSchema());
			}
		}
		{
			JsonNode value = JsonUtil.consumeAnyProperty(json, "example");
			node.setExample(value);
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "examples");
			JsonUtil.keys(object).forEach(name -> {
				ObjectNode mapValue = JsonUtil.consumeObjectProperty(object, name);
				if (mapValue != null) {
					OpenApi31Example model = (OpenApi31Example) node.createExample();
					this.readExample(mapValue, model);
					node.addExample(name, model);
				}
			});
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "content");
			JsonUtil.keys(object).forEach(name -> {
				ObjectNode mapValue = JsonUtil.consumeObjectProperty(object, name);
				if (mapValue != null) {
					OpenApi31MediaType model = (OpenApi31MediaType) node.createMediaType();
					this.readMediaType(mapValue, model);
					node.addContent(name, model);
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

	public void readTag(ObjectNode json, OpenApi31Tag node) {
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
				readExternalDocumentation(object, (OpenApi31ExternalDocumentation) node.getExternalDocs());
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

	public void readSchema(ObjectNode json, OpenApi31Schema node) {
		{
			String value = JsonUtil.consumeStringProperty(json, "$ref");
			node.set$ref(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "summary");
			node.setSummary(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "format");
			node.setFormat(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "title");
			node.setTitle(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "description");
			node.setDescription(value);
		}
		{
			JsonNode value = JsonUtil.consumeAnyProperty(json, "default");
			node.setDefault(value);
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
			List<String> value = JsonUtil.consumeStringArrayProperty(json, "required");
			node.setRequired(value);
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
			JsonNode value = JsonUtil.consumeAnyProperty(json, "type");
			if (value != null) {
				if (JsonUtil.isArray(value)) {
					List<JsonNode> array = JsonUtil.toList(value);
					List<String> items = new ArrayList<>();
					array.forEach(item -> {
						String pValue = JsonUtil.toString(item);
						items.add(pValue);
					});
					StringListUnionValue unionValue = new StringListUnionValueImpl(items);
					node.setType(unionValue);
				} else if (JsonUtil.isString(value)) {
					String pValue = JsonUtil.toString(value);
					StringUnionValue unionValue = new StringUnionValueImpl(pValue);
					node.setType(unionValue);
				} else {
					node.addExtraProperty("type", value);
				}
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "items");
			if (object != null) {
				node.setItems(node.createSchema());
				readSchema(object, (OpenApi31Schema) node.getItems());
			}
		}
		{
			List<ObjectNode> objects = JsonUtil.consumeObjectArrayProperty(json, "allOf");
			if (objects != null) {
				objects.forEach(object -> {
					OpenApi31Schema model = (OpenApi31Schema) node.createSchema();
					this.readSchema(object, model);
					node.addAllOf(model);
				});
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "properties");
			JsonUtil.keys(object).forEach(name -> {
				ObjectNode mapValue = JsonUtil.consumeObjectProperty(object, name);
				if (mapValue != null) {
					OpenApi31Schema model = (OpenApi31Schema) node.createSchema();
					this.readSchema(mapValue, model);
					node.addProperty(name, model);
				}
			});
		}
		{
			JsonNode value = JsonUtil.consumeAnyProperty(json, "additionalProperties");
			if (value != null) {
				if (JsonUtil.isObject(value)) {
					ObjectNode object = JsonUtil.toObject(value);
					node.setAdditionalProperties(node.createSchema());
					readSchema(object, (OpenApi31Schema) node.getAdditionalProperties());
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
			Boolean value = JsonUtil.consumeBooleanProperty(json, "readOnly");
			node.setReadOnly(value);
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "xml");
			if (object != null) {
				node.setXml(node.createXML());
				readXML(object, (OpenApi31XML) node.getXml());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "externalDocs");
			if (object != null) {
				node.setExternalDocs(node.createExternalDocumentation());
				readExternalDocumentation(object, (OpenApi31ExternalDocumentation) node.getExternalDocs());
			}
		}
		{
			JsonNode value = JsonUtil.consumeAnyProperty(json, "example");
			node.setExample(value);
		}
		{
			List<ObjectNode> objects = JsonUtil.consumeObjectArrayProperty(json, "oneOf");
			if (objects != null) {
				objects.forEach(object -> {
					OpenApi31Schema model = (OpenApi31Schema) node.createSchema();
					this.readSchema(object, model);
					node.addOneOf(model);
				});
			}
		}
		{
			List<ObjectNode> objects = JsonUtil.consumeObjectArrayProperty(json, "anyOf");
			if (objects != null) {
				objects.forEach(object -> {
					OpenApi31Schema model = (OpenApi31Schema) node.createSchema();
					this.readSchema(object, model);
					node.addAnyOf(model);
				});
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "not");
			if (object != null) {
				node.setNot(node.createSchema());
				readSchema(object, (OpenApi31Schema) node.getNot());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "discriminator");
			if (object != null) {
				node.setDiscriminator(node.createDiscriminator());
				readDiscriminator(object, (OpenApi31Discriminator) node.getDiscriminator());
			}
		}
		{
			Boolean value = JsonUtil.consumeBooleanProperty(json, "writeOnly");
			node.setWriteOnly(value);
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

	public void readDiscriminator(ObjectNode json, OpenApi31Discriminator node) {
		{
			String value = JsonUtil.consumeStringProperty(json, "propertyName");
			node.setPropertyName(value);
		}
		{
			Map<String, String> value = JsonUtil.consumeStringMapProperty(json, "mapping");
			node.setMapping(value);
		}
		ReaderUtil.readExtraProperties(json, node);
	}

	public void readXML(ObjectNode json, OpenApi31XML node) {
		{
			String value = JsonUtil.consumeStringProperty(json, "name");
			node.setName(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "namespace");
			node.setNamespace(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "prefix");
			node.setPrefix(value);
		}
		{
			Boolean value = JsonUtil.consumeBooleanProperty(json, "attribute");
			node.setAttribute(value);
		}
		{
			Boolean value = JsonUtil.consumeBooleanProperty(json, "wrapped");
			node.setWrapped(value);
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

	public void readSecurityScheme(ObjectNode json, OpenApi31SecurityScheme node) {
		{
			String value = JsonUtil.consumeStringProperty(json, "$ref");
			node.set$ref(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "summary");
			node.setSummary(value);
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
				readOAuthFlows(object, (OpenApi31OAuthFlows) node.getFlows());
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

	public void readOAuthFlows(ObjectNode json, OpenApi31OAuthFlows node) {
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "implicit");
			if (object != null) {
				node.setImplicit(node.createOAuthFlow());
				readOAuthFlow(object, (OpenApi31OAuthFlow) node.getImplicit());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "password");
			if (object != null) {
				node.setPassword(node.createOAuthFlow());
				readOAuthFlow(object, (OpenApi31OAuthFlow) node.getPassword());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "clientCredentials");
			if (object != null) {
				node.setClientCredentials(node.createOAuthFlow());
				readOAuthFlow(object, (OpenApi31OAuthFlow) node.getClientCredentials());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "authorizationCode");
			if (object != null) {
				node.setAuthorizationCode(node.createOAuthFlow());
				readOAuthFlow(object, (OpenApi31OAuthFlow) node.getAuthorizationCode());
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

	public void readOAuthFlow(ObjectNode json, OpenApi31OAuthFlow node) {
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

	public void readSecurityRequirement(ObjectNode json, OpenApi31SecurityRequirement node) {
		{
			List<String> propertyNames = JsonUtil.keys(json);
			propertyNames.forEach(name -> {
				List<String> value = JsonUtil.consumeStringArrayProperty(json, name);
				node.addItem(name, value);
			});
		}
		ReaderUtil.readExtraProperties(json, node);
	}
}