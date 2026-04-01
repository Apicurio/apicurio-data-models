package io.apicurio.datamodels.models.openapi.v3x.v32.io;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.apicurio.datamodels.models.RootNode;
import io.apicurio.datamodels.models.io.ModelReader;
import io.apicurio.datamodels.models.openapi.v3x.v32.OpenApi32Callback;
import io.apicurio.datamodels.models.openapi.v3x.v32.OpenApi32Components;
import io.apicurio.datamodels.models.openapi.v3x.v32.OpenApi32Contact;
import io.apicurio.datamodels.models.openapi.v3x.v32.OpenApi32Discriminator;
import io.apicurio.datamodels.models.openapi.v3x.v32.OpenApi32Document;
import io.apicurio.datamodels.models.openapi.v3x.v32.OpenApi32DocumentImpl;
import io.apicurio.datamodels.models.openapi.v3x.v32.OpenApi32Encoding;
import io.apicurio.datamodels.models.openapi.v3x.v32.OpenApi32Example;
import io.apicurio.datamodels.models.openapi.v3x.v32.OpenApi32ExternalDocumentation;
import io.apicurio.datamodels.models.openapi.v3x.v32.OpenApi32Header;
import io.apicurio.datamodels.models.openapi.v3x.v32.OpenApi32Info;
import io.apicurio.datamodels.models.openapi.v3x.v32.OpenApi32License;
import io.apicurio.datamodels.models.openapi.v3x.v32.OpenApi32Link;
import io.apicurio.datamodels.models.openapi.v3x.v32.OpenApi32MediaType;
import io.apicurio.datamodels.models.openapi.v3x.v32.OpenApi32OAuthFlow;
import io.apicurio.datamodels.models.openapi.v3x.v32.OpenApi32OAuthFlows;
import io.apicurio.datamodels.models.openapi.v3x.v32.OpenApi32Operation;
import io.apicurio.datamodels.models.openapi.v3x.v32.OpenApi32Parameter;
import io.apicurio.datamodels.models.openapi.v3x.v32.OpenApi32PathItem;
import io.apicurio.datamodels.models.openapi.v3x.v32.OpenApi32Paths;
import io.apicurio.datamodels.models.openapi.v3x.v32.OpenApi32RequestBody;
import io.apicurio.datamodels.models.openapi.v3x.v32.OpenApi32Response;
import io.apicurio.datamodels.models.openapi.v3x.v32.OpenApi32Responses;
import io.apicurio.datamodels.models.openapi.v3x.v32.OpenApi32Schema;
import io.apicurio.datamodels.models.openapi.v3x.v32.OpenApi32SecurityRequirement;
import io.apicurio.datamodels.models.openapi.v3x.v32.OpenApi32SecurityScheme;
import io.apicurio.datamodels.models.openapi.v3x.v32.OpenApi32Server;
import io.apicurio.datamodels.models.openapi.v3x.v32.OpenApi32ServerVariable;
import io.apicurio.datamodels.models.openapi.v3x.v32.OpenApi32Tag;
import io.apicurio.datamodels.models.openapi.v3x.v32.OpenApi32XML;
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

public class OpenApi32ModelReader implements ModelReader {

	public void readDocument(ObjectNode json, OpenApi32Document node) {
		{
			String value = JsonUtil.consumeStringProperty(json, "openapi");
			node.setOpenapi(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "$self");
			node.set$self(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "jsonSchemaDialect");
			node.setJsonSchemaDialect(value);
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "info");
			if (object != null) {
				node.setInfo(node.createInfo());
				readInfo(object, (OpenApi32Info) node.getInfo());
			}
		}
		{
			List<ObjectNode> objects = JsonUtil.consumeObjectArrayProperty(json, "servers");
			if (objects != null) {
				objects.forEach(object -> {
					OpenApi32Server model = (OpenApi32Server) node.createServer();
					this.readServer(object, model);
					node.addServer(model);
				});
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "paths");
			if (object != null) {
				node.setPaths(node.createPaths());
				readPaths(object, (OpenApi32Paths) node.getPaths());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "webhooks");
			JsonUtil.keys(object).forEach(name -> {
				ObjectNode mapValue = JsonUtil.consumeObjectProperty(object, name);
				if (mapValue != null) {
					OpenApi32PathItem model = (OpenApi32PathItem) node.createPathItem();
					this.readPathItem(mapValue, model);
					node.addWebhook(name, model);
				}
			});
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "components");
			if (object != null) {
				node.setComponents(node.createComponents());
				readComponents(object, (OpenApi32Components) node.getComponents());
			}
		}
		{
			List<ObjectNode> objects = JsonUtil.consumeObjectArrayProperty(json, "security");
			if (objects != null) {
				objects.forEach(object -> {
					OpenApi32SecurityRequirement model = (OpenApi32SecurityRequirement) node
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
					OpenApi32Tag model = (OpenApi32Tag) node.createTag();
					this.readTag(object, model);
					node.addTag(model);
				});
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "externalDocs");
			if (object != null) {
				node.setExternalDocs(node.createExternalDocumentation());
				readExternalDocumentation(object, (OpenApi32ExternalDocumentation) node.getExternalDocs());
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
		OpenApi32Document rootModel = new OpenApi32DocumentImpl();
		this.readDocument(json, rootModel);
		return rootModel;
	}

	public void readInfo(ObjectNode json, OpenApi32Info node) {
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
				readContact(object, (OpenApi32Contact) node.getContact());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "license");
			if (object != null) {
				node.setLicense(node.createLicense());
				readLicense(object, (OpenApi32License) node.getLicense());
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

	public void readContact(ObjectNode json, OpenApi32Contact node) {
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

	public void readLicense(ObjectNode json, OpenApi32License node) {
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

	public void readServer(ObjectNode json, OpenApi32Server node) {
		{
			String value = JsonUtil.consumeStringProperty(json, "url");
			node.setUrl(value);
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
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "variables");
			JsonUtil.keys(object).forEach(name -> {
				ObjectNode mapValue = JsonUtil.consumeObjectProperty(object, name);
				if (mapValue != null) {
					OpenApi32ServerVariable model = (OpenApi32ServerVariable) node.createServerVariable();
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

	public void readServerVariable(ObjectNode json, OpenApi32ServerVariable node) {
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

	public void readComponents(ObjectNode json, OpenApi32Components node) {
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "schemas");
			JsonUtil.keys(object).forEach(name -> {
				ObjectNode mapValue = JsonUtil.consumeObjectProperty(object, name);
				if (mapValue != null) {
					OpenApi32Schema model = (OpenApi32Schema) node.createSchema();
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
					OpenApi32Response model = (OpenApi32Response) node.createResponse();
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
					OpenApi32Parameter model = (OpenApi32Parameter) node.createParameter();
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
					OpenApi32Example model = (OpenApi32Example) node.createExample();
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
					OpenApi32RequestBody model = (OpenApi32RequestBody) node.createRequestBody();
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
					OpenApi32Header model = (OpenApi32Header) node.createHeader();
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
					OpenApi32SecurityScheme model = (OpenApi32SecurityScheme) node.createSecurityScheme();
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
					OpenApi32Link model = (OpenApi32Link) node.createLink();
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
					OpenApi32Callback model = (OpenApi32Callback) node.createCallback();
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
					OpenApi32PathItem model = (OpenApi32PathItem) node.createPathItem();
					this.readPathItem(mapValue, model);
					node.addPathItem(name, model);
				}
			});
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "mediaTypes");
			JsonUtil.keys(object).forEach(name -> {
				ObjectNode mapValue = JsonUtil.consumeObjectProperty(object, name);
				if (mapValue != null) {
					OpenApi32MediaType model = (OpenApi32MediaType) node.createMediaType();
					this.readMediaType(mapValue, model);
					node.addMediaType(name, model);
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

	public void readPaths(ObjectNode json, OpenApi32Paths node) {
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
					OpenApi32PathItem model = (OpenApi32PathItem) node.createPathItem();
					this.readPathItem(object, model);
					node.addItem(name, model);
				}
			});
		}
		ReaderUtil.readExtraProperties(json, node);
	}

	public void readPathItem(ObjectNode json, OpenApi32PathItem node) {
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
				readOperation(object, (OpenApi32Operation) node.getGet());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "put");
			if (object != null) {
				node.setPut(node.createOperation());
				readOperation(object, (OpenApi32Operation) node.getPut());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "post");
			if (object != null) {
				node.setPost(node.createOperation());
				readOperation(object, (OpenApi32Operation) node.getPost());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "delete");
			if (object != null) {
				node.setDelete(node.createOperation());
				readOperation(object, (OpenApi32Operation) node.getDelete());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "options");
			if (object != null) {
				node.setOptions(node.createOperation());
				readOperation(object, (OpenApi32Operation) node.getOptions());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "head");
			if (object != null) {
				node.setHead(node.createOperation());
				readOperation(object, (OpenApi32Operation) node.getHead());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "patch");
			if (object != null) {
				node.setPatch(node.createOperation());
				readOperation(object, (OpenApi32Operation) node.getPatch());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "trace");
			if (object != null) {
				node.setTrace(node.createOperation());
				readOperation(object, (OpenApi32Operation) node.getTrace());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "query");
			if (object != null) {
				node.setQuery(node.createOperation());
				readOperation(object, (OpenApi32Operation) node.getQuery());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "additionalOperations");
			JsonUtil.keys(object).forEach(name -> {
				ObjectNode mapValue = JsonUtil.consumeObjectProperty(object, name);
				if (mapValue != null) {
					OpenApi32Operation model = (OpenApi32Operation) node.createOperation();
					this.readOperation(mapValue, model);
					node.addAdditionalOperation(name, model);
				}
			});
		}
		{
			List<ObjectNode> objects = JsonUtil.consumeObjectArrayProperty(json, "servers");
			if (objects != null) {
				objects.forEach(object -> {
					OpenApi32Server model = (OpenApi32Server) node.createServer();
					this.readServer(object, model);
					node.addServer(model);
				});
			}
		}
		{
			List<ObjectNode> objects = JsonUtil.consumeObjectArrayProperty(json, "parameters");
			if (objects != null) {
				objects.forEach(object -> {
					OpenApi32Parameter model = (OpenApi32Parameter) node.createParameter();
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

	public void readOperation(ObjectNode json, OpenApi32Operation node) {
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
				readExternalDocumentation(object, (OpenApi32ExternalDocumentation) node.getExternalDocs());
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
					OpenApi32Parameter model = (OpenApi32Parameter) node.createParameter();
					this.readParameter(object, model);
					node.addParameter(model);
				});
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "requestBody");
			if (object != null) {
				node.setRequestBody(node.createRequestBody());
				readRequestBody(object, (OpenApi32RequestBody) node.getRequestBody());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "responses");
			if (object != null) {
				node.setResponses(node.createResponses());
				readResponses(object, (OpenApi32Responses) node.getResponses());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "callbacks");
			JsonUtil.keys(object).forEach(name -> {
				ObjectNode mapValue = JsonUtil.consumeObjectProperty(object, name);
				if (mapValue != null) {
					OpenApi32Callback model = (OpenApi32Callback) node.createCallback();
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
					OpenApi32SecurityRequirement model = (OpenApi32SecurityRequirement) node
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
					OpenApi32Server model = (OpenApi32Server) node.createServer();
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

	public void readExternalDocumentation(ObjectNode json, OpenApi32ExternalDocumentation node) {
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

	public void readParameter(ObjectNode json, OpenApi32Parameter node) {
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
				readSchema(object, (OpenApi32Schema) node.getSchema());
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
					OpenApi32Example model = (OpenApi32Example) node.createExample();
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
					OpenApi32MediaType model = (OpenApi32MediaType) node.createMediaType();
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

	public void readRequestBody(ObjectNode json, OpenApi32RequestBody node) {
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
					OpenApi32MediaType model = (OpenApi32MediaType) node.createMediaType();
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

	public void readMediaType(ObjectNode json, OpenApi32MediaType node) {
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "schema");
			if (object != null) {
				node.setSchema(node.createSchema());
				readSchema(object, (OpenApi32Schema) node.getSchema());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "itemSchema");
			if (object != null) {
				node.setItemSchema(node.createSchema());
				readSchema(object, (OpenApi32Schema) node.getItemSchema());
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
					OpenApi32Example model = (OpenApi32Example) node.createExample();
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
					OpenApi32Encoding model = (OpenApi32Encoding) node.createEncoding();
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

	public void readEncoding(ObjectNode json, OpenApi32Encoding node) {
		{
			String value = JsonUtil.consumeStringProperty(json, "contentType");
			node.setContentType(value);
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "headers");
			JsonUtil.keys(object).forEach(name -> {
				ObjectNode mapValue = JsonUtil.consumeObjectProperty(object, name);
				if (mapValue != null) {
					OpenApi32Header model = (OpenApi32Header) node.createHeader();
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

	public void readResponses(ObjectNode json, OpenApi32Responses node) {
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
				readResponse(object, (OpenApi32Response) node.getDefault());
			}
		}
		{
			List<String> propertyNames = JsonUtil.keys(json);
			propertyNames.forEach(name -> {
				ObjectNode object = JsonUtil.consumeObjectProperty(json, name);
				if (object != null) {
					OpenApi32Response model = (OpenApi32Response) node.createResponse();
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

	public void readResponse(ObjectNode json, OpenApi32Response node) {
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
					OpenApi32Header model = (OpenApi32Header) node.createHeader();
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
					OpenApi32MediaType model = (OpenApi32MediaType) node.createMediaType();
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
					OpenApi32Link model = (OpenApi32Link) node.createLink();
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

	public void readCallback(ObjectNode json, OpenApi32Callback node) {
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
					OpenApi32PathItem model = (OpenApi32PathItem) node.createPathItem();
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

	public void readExample(ObjectNode json, OpenApi32Example node) {
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
			JsonNode value = JsonUtil.consumeAnyProperty(json, "dataValue");
			node.setDataValue(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "serializedValue");
			node.setSerializedValue(value);
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

	public void readLink(ObjectNode json, OpenApi32Link node) {
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
				readServer(object, (OpenApi32Server) node.getServer());
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

	public void readHeader(ObjectNode json, OpenApi32Header node) {
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
				readSchema(object, (OpenApi32Schema) node.getSchema());
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
					OpenApi32Example model = (OpenApi32Example) node.createExample();
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
					OpenApi32MediaType model = (OpenApi32MediaType) node.createMediaType();
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

	public void readTag(ObjectNode json, OpenApi32Tag node) {
		{
			String value = JsonUtil.consumeStringProperty(json, "name");
			node.setName(value);
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
			String value = JsonUtil.consumeStringProperty(json, "parent");
			node.setParent(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "kind");
			node.setKind(value);
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "externalDocs");
			if (object != null) {
				node.setExternalDocs(node.createExternalDocumentation());
				readExternalDocumentation(object, (OpenApi32ExternalDocumentation) node.getExternalDocs());
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

	public void readSchema(ObjectNode json, OpenApi32Schema node) {
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
				readSchema(object, (OpenApi32Schema) node.getItems());
			}
		}
		{
			List<ObjectNode> objects = JsonUtil.consumeObjectArrayProperty(json, "allOf");
			if (objects != null) {
				objects.forEach(object -> {
					OpenApi32Schema model = (OpenApi32Schema) node.createSchema();
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
					OpenApi32Schema model = (OpenApi32Schema) node.createSchema();
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
					readSchema(object, (OpenApi32Schema) node.getAdditionalProperties());
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
				readXML(object, (OpenApi32XML) node.getXml());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "externalDocs");
			if (object != null) {
				node.setExternalDocs(node.createExternalDocumentation());
				readExternalDocumentation(object, (OpenApi32ExternalDocumentation) node.getExternalDocs());
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
					OpenApi32Schema model = (OpenApi32Schema) node.createSchema();
					this.readSchema(object, model);
					node.addOneOf(model);
				});
			}
		}
		{
			List<ObjectNode> objects = JsonUtil.consumeObjectArrayProperty(json, "anyOf");
			if (objects != null) {
				objects.forEach(object -> {
					OpenApi32Schema model = (OpenApi32Schema) node.createSchema();
					this.readSchema(object, model);
					node.addAnyOf(model);
				});
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "not");
			if (object != null) {
				node.setNot(node.createSchema());
				readSchema(object, (OpenApi32Schema) node.getNot());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "discriminator");
			if (object != null) {
				node.setDiscriminator(node.createDiscriminator());
				readDiscriminator(object, (OpenApi32Discriminator) node.getDiscriminator());
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

	public void readDiscriminator(ObjectNode json, OpenApi32Discriminator node) {
		{
			String value = JsonUtil.consumeStringProperty(json, "propertyName");
			node.setPropertyName(value);
		}
		{
			Map<String, String> value = JsonUtil.consumeStringMapProperty(json, "mapping");
			node.setMapping(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "defaultMapping");
			node.setDefaultMapping(value);
		}
		ReaderUtil.readExtraProperties(json, node);
	}

	public void readXML(ObjectNode json, OpenApi32XML node) {
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
			String value = JsonUtil.consumeStringProperty(json, "nodeType");
			node.setNodeType(value);
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

	public void readSecurityScheme(ObjectNode json, OpenApi32SecurityScheme node) {
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
				readOAuthFlows(object, (OpenApi32OAuthFlows) node.getFlows());
			}
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "openIdConnectUrl");
			node.setOpenIdConnectUrl(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "oauth2MetadataUrl");
			node.setOauth2MetadataUrl(value);
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

	public void readOAuthFlows(ObjectNode json, OpenApi32OAuthFlows node) {
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "implicit");
			if (object != null) {
				node.setImplicit(node.createOAuthFlow());
				readOAuthFlow(object, (OpenApi32OAuthFlow) node.getImplicit());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "password");
			if (object != null) {
				node.setPassword(node.createOAuthFlow());
				readOAuthFlow(object, (OpenApi32OAuthFlow) node.getPassword());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "clientCredentials");
			if (object != null) {
				node.setClientCredentials(node.createOAuthFlow());
				readOAuthFlow(object, (OpenApi32OAuthFlow) node.getClientCredentials());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "authorizationCode");
			if (object != null) {
				node.setAuthorizationCode(node.createOAuthFlow());
				readOAuthFlow(object, (OpenApi32OAuthFlow) node.getAuthorizationCode());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "deviceAuthorization");
			if (object != null) {
				node.setDeviceAuthorization(node.createOAuthFlow());
				readOAuthFlow(object, (OpenApi32OAuthFlow) node.getDeviceAuthorization());
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

	public void readOAuthFlow(ObjectNode json, OpenApi32OAuthFlow node) {
		{
			String value = JsonUtil.consumeStringProperty(json, "authorizationUrl");
			node.setAuthorizationUrl(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "tokenUrl");
			node.setTokenUrl(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "deviceAuthorizationUrl");
			node.setDeviceAuthorizationUrl(value);
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

	public void readSecurityRequirement(ObjectNode json, OpenApi32SecurityRequirement node) {
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