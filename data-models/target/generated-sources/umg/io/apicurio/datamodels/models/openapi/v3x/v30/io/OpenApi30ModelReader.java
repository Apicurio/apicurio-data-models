package io.apicurio.datamodels.models.openapi.v3x.v30.io;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.apicurio.datamodels.models.RootNode;
import io.apicurio.datamodels.models.io.ModelReader;
import io.apicurio.datamodels.models.openapi.v3x.v30.OpenApi30Callback;
import io.apicurio.datamodels.models.openapi.v3x.v30.OpenApi30Components;
import io.apicurio.datamodels.models.openapi.v3x.v30.OpenApi30Contact;
import io.apicurio.datamodels.models.openapi.v3x.v30.OpenApi30Discriminator;
import io.apicurio.datamodels.models.openapi.v3x.v30.OpenApi30Document;
import io.apicurio.datamodels.models.openapi.v3x.v30.OpenApi30DocumentImpl;
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
import io.apicurio.datamodels.models.union.BooleanUnionValue;
import io.apicurio.datamodels.models.union.BooleanUnionValueImpl;
import io.apicurio.datamodels.models.util.JsonUtil;
import io.apicurio.datamodels.models.util.ReaderUtil;
import java.util.List;
import java.util.Map;

public class OpenApi30ModelReader implements ModelReader {

	public void readDocument(ObjectNode json, OpenApi30Document node) {
		{
			String value = JsonUtil.consumeStringProperty(json, "openapi");
			node.setOpenapi(value);
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "info");
			if (object != null) {
				node.setInfo(node.createInfo());
				readInfo(object, (OpenApi30Info) node.getInfo());
			}
		}
		{
			List<ObjectNode> objects = JsonUtil.consumeObjectArrayProperty(json, "servers");
			if (objects != null) {
				objects.forEach(object -> {
					OpenApi30Server model = (OpenApi30Server) node.createServer();
					this.readServer(object, model);
					node.addServer(model);
				});
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "paths");
			if (object != null) {
				node.setPaths(node.createPaths());
				readPaths(object, (OpenApi30Paths) node.getPaths());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "components");
			if (object != null) {
				node.setComponents(node.createComponents());
				readComponents(object, (OpenApi30Components) node.getComponents());
			}
		}
		{
			List<ObjectNode> objects = JsonUtil.consumeObjectArrayProperty(json, "security");
			if (objects != null) {
				objects.forEach(object -> {
					OpenApi30SecurityRequirement model = (OpenApi30SecurityRequirement) node
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
					OpenApi30Tag model = (OpenApi30Tag) node.createTag();
					this.readTag(object, model);
					node.addTag(model);
				});
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "externalDocs");
			if (object != null) {
				node.setExternalDocs(node.createExternalDocumentation());
				readExternalDocumentation(object, (OpenApi30ExternalDocumentation) node.getExternalDocs());
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
		OpenApi30Document rootModel = new OpenApi30DocumentImpl();
		this.readDocument(json, rootModel);
		return rootModel;
	}

	public void readInfo(ObjectNode json, OpenApi30Info node) {
		{
			String value = JsonUtil.consumeStringProperty(json, "title");
			node.setTitle(value);
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
				readContact(object, (OpenApi30Contact) node.getContact());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "license");
			if (object != null) {
				node.setLicense(node.createLicense());
				readLicense(object, (OpenApi30License) node.getLicense());
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

	public void readContact(ObjectNode json, OpenApi30Contact node) {
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

	public void readLicense(ObjectNode json, OpenApi30License node) {
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

	public void readServer(ObjectNode json, OpenApi30Server node) {
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
					OpenApi30ServerVariable model = (OpenApi30ServerVariable) node.createServerVariable();
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

	public void readServerVariable(ObjectNode json, OpenApi30ServerVariable node) {
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

	public void readComponents(ObjectNode json, OpenApi30Components node) {
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "schemas");
			JsonUtil.keys(object).forEach(name -> {
				ObjectNode mapValue = JsonUtil.consumeObjectProperty(object, name);
				if (mapValue != null) {
					OpenApi30Schema model = (OpenApi30Schema) node.createSchema();
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
					OpenApi30Response model = (OpenApi30Response) node.createResponse();
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
					OpenApi30Parameter model = (OpenApi30Parameter) node.createParameter();
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
					OpenApi30Example model = (OpenApi30Example) node.createExample();
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
					OpenApi30RequestBody model = (OpenApi30RequestBody) node.createRequestBody();
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
					OpenApi30Header model = (OpenApi30Header) node.createHeader();
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
					OpenApi30SecurityScheme model = (OpenApi30SecurityScheme) node.createSecurityScheme();
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
					OpenApi30Link model = (OpenApi30Link) node.createLink();
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
					OpenApi30Callback model = (OpenApi30Callback) node.createCallback();
					this.readCallback(mapValue, model);
					node.addCallback(name, model);
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

	public void readPaths(ObjectNode json, OpenApi30Paths node) {
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
					OpenApi30PathItem model = (OpenApi30PathItem) node.createPathItem();
					this.readPathItem(object, model);
					node.addItem(name, model);
				}
			});
		}
		ReaderUtil.readExtraProperties(json, node);
	}

	public void readPathItem(ObjectNode json, OpenApi30PathItem node) {
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
				readOperation(object, (OpenApi30Operation) node.getGet());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "put");
			if (object != null) {
				node.setPut(node.createOperation());
				readOperation(object, (OpenApi30Operation) node.getPut());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "post");
			if (object != null) {
				node.setPost(node.createOperation());
				readOperation(object, (OpenApi30Operation) node.getPost());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "delete");
			if (object != null) {
				node.setDelete(node.createOperation());
				readOperation(object, (OpenApi30Operation) node.getDelete());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "options");
			if (object != null) {
				node.setOptions(node.createOperation());
				readOperation(object, (OpenApi30Operation) node.getOptions());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "head");
			if (object != null) {
				node.setHead(node.createOperation());
				readOperation(object, (OpenApi30Operation) node.getHead());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "patch");
			if (object != null) {
				node.setPatch(node.createOperation());
				readOperation(object, (OpenApi30Operation) node.getPatch());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "trace");
			if (object != null) {
				node.setTrace(node.createOperation());
				readOperation(object, (OpenApi30Operation) node.getTrace());
			}
		}
		{
			List<ObjectNode> objects = JsonUtil.consumeObjectArrayProperty(json, "servers");
			if (objects != null) {
				objects.forEach(object -> {
					OpenApi30Server model = (OpenApi30Server) node.createServer();
					this.readServer(object, model);
					node.addServer(model);
				});
			}
		}
		{
			List<ObjectNode> objects = JsonUtil.consumeObjectArrayProperty(json, "parameters");
			if (objects != null) {
				objects.forEach(object -> {
					OpenApi30Parameter model = (OpenApi30Parameter) node.createParameter();
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

	public void readOperation(ObjectNode json, OpenApi30Operation node) {
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
				readExternalDocumentation(object, (OpenApi30ExternalDocumentation) node.getExternalDocs());
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
					OpenApi30Parameter model = (OpenApi30Parameter) node.createParameter();
					this.readParameter(object, model);
					node.addParameter(model);
				});
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "requestBody");
			if (object != null) {
				node.setRequestBody(node.createRequestBody());
				readRequestBody(object, (OpenApi30RequestBody) node.getRequestBody());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "responses");
			if (object != null) {
				node.setResponses(node.createResponses());
				readResponses(object, (OpenApi30Responses) node.getResponses());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "callbacks");
			JsonUtil.keys(object).forEach(name -> {
				ObjectNode mapValue = JsonUtil.consumeObjectProperty(object, name);
				if (mapValue != null) {
					OpenApi30Callback model = (OpenApi30Callback) node.createCallback();
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
					OpenApi30SecurityRequirement model = (OpenApi30SecurityRequirement) node
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
					OpenApi30Server model = (OpenApi30Server) node.createServer();
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

	public void readExternalDocumentation(ObjectNode json, OpenApi30ExternalDocumentation node) {
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

	public void readParameter(ObjectNode json, OpenApi30Parameter node) {
		{
			String value = JsonUtil.consumeStringProperty(json, "$ref");
			node.set$ref(value);
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
				readSchema(object, (OpenApi30Schema) node.getSchema());
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
					OpenApi30Example model = (OpenApi30Example) node.createExample();
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
					OpenApi30MediaType model = (OpenApi30MediaType) node.createMediaType();
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

	public void readRequestBody(ObjectNode json, OpenApi30RequestBody node) {
		{
			String value = JsonUtil.consumeStringProperty(json, "$ref");
			node.set$ref(value);
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
					OpenApi30MediaType model = (OpenApi30MediaType) node.createMediaType();
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

	public void readMediaType(ObjectNode json, OpenApi30MediaType node) {
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "schema");
			if (object != null) {
				node.setSchema(node.createSchema());
				readSchema(object, (OpenApi30Schema) node.getSchema());
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
					OpenApi30Example model = (OpenApi30Example) node.createExample();
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
					OpenApi30Encoding model = (OpenApi30Encoding) node.createEncoding();
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

	public void readEncoding(ObjectNode json, OpenApi30Encoding node) {
		{
			String value = JsonUtil.consumeStringProperty(json, "contentType");
			node.setContentType(value);
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "headers");
			JsonUtil.keys(object).forEach(name -> {
				ObjectNode mapValue = JsonUtil.consumeObjectProperty(object, name);
				if (mapValue != null) {
					OpenApi30Header model = (OpenApi30Header) node.createHeader();
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

	public void readResponses(ObjectNode json, OpenApi30Responses node) {
		{
			String value = JsonUtil.consumeStringProperty(json, "$ref");
			node.set$ref(value);
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "default");
			if (object != null) {
				node.setDefault(node.createResponse());
				readResponse(object, (OpenApi30Response) node.getDefault());
			}
		}
		{
			List<String> propertyNames = JsonUtil.keys(json);
			propertyNames.forEach(name -> {
				ObjectNode object = JsonUtil.consumeObjectProperty(json, name);
				if (object != null) {
					OpenApi30Response model = (OpenApi30Response) node.createResponse();
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

	public void readResponse(ObjectNode json, OpenApi30Response node) {
		{
			String value = JsonUtil.consumeStringProperty(json, "$ref");
			node.set$ref(value);
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
					OpenApi30Header model = (OpenApi30Header) node.createHeader();
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
					OpenApi30MediaType model = (OpenApi30MediaType) node.createMediaType();
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
					OpenApi30Link model = (OpenApi30Link) node.createLink();
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

	public void readCallback(ObjectNode json, OpenApi30Callback node) {
		{
			String value = JsonUtil.consumeStringProperty(json, "$ref");
			node.set$ref(value);
		}
		{
			List<String> propertyNames = JsonUtil.keys(json);
			propertyNames.forEach(name -> {
				ObjectNode object = JsonUtil.consumeObjectProperty(json, name);
				if (object != null) {
					OpenApi30PathItem model = (OpenApi30PathItem) node.createPathItem();
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

	public void readExample(ObjectNode json, OpenApi30Example node) {
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

	public void readLink(ObjectNode json, OpenApi30Link node) {
		{
			String value = JsonUtil.consumeStringProperty(json, "$ref");
			node.set$ref(value);
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
				readServer(object, (OpenApi30Server) node.getServer());
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

	public void readHeader(ObjectNode json, OpenApi30Header node) {
		{
			String value = JsonUtil.consumeStringProperty(json, "$ref");
			node.set$ref(value);
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
				readSchema(object, (OpenApi30Schema) node.getSchema());
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
					OpenApi30Example model = (OpenApi30Example) node.createExample();
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
					OpenApi30MediaType model = (OpenApi30MediaType) node.createMediaType();
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

	public void readTag(ObjectNode json, OpenApi30Tag node) {
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
				readExternalDocumentation(object, (OpenApi30ExternalDocumentation) node.getExternalDocs());
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

	public void readSchema(ObjectNode json, OpenApi30Schema node) {
		{
			String value = JsonUtil.consumeStringProperty(json, "$ref");
			node.set$ref(value);
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
			Boolean value = JsonUtil.consumeBooleanProperty(json, "exclusiveMaximum");
			node.setExclusiveMaximum(value);
		}
		{
			Number value = JsonUtil.consumeNumberProperty(json, "minimum");
			node.setMinimum(value);
		}
		{
			Boolean value = JsonUtil.consumeBooleanProperty(json, "exclusiveMinimum");
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
			String value = JsonUtil.consumeStringProperty(json, "type");
			node.setType(value);
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "items");
			if (object != null) {
				node.setItems(node.createSchema());
				readSchema(object, (OpenApi30Schema) node.getItems());
			}
		}
		{
			List<ObjectNode> objects = JsonUtil.consumeObjectArrayProperty(json, "allOf");
			if (objects != null) {
				objects.forEach(object -> {
					OpenApi30Schema model = (OpenApi30Schema) node.createSchema();
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
					OpenApi30Schema model = (OpenApi30Schema) node.createSchema();
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
					readSchema(object, (OpenApi30Schema) node.getAdditionalProperties());
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
				readXML(object, (OpenApi30XML) node.getXml());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "externalDocs");
			if (object != null) {
				node.setExternalDocs(node.createExternalDocumentation());
				readExternalDocumentation(object, (OpenApi30ExternalDocumentation) node.getExternalDocs());
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
					OpenApi30Schema model = (OpenApi30Schema) node.createSchema();
					this.readSchema(object, model);
					node.addOneOf(model);
				});
			}
		}
		{
			List<ObjectNode> objects = JsonUtil.consumeObjectArrayProperty(json, "anyOf");
			if (objects != null) {
				objects.forEach(object -> {
					OpenApi30Schema model = (OpenApi30Schema) node.createSchema();
					this.readSchema(object, model);
					node.addAnyOf(model);
				});
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "not");
			if (object != null) {
				node.setNot(node.createSchema());
				readSchema(object, (OpenApi30Schema) node.getNot());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "discriminator");
			if (object != null) {
				node.setDiscriminator(node.createDiscriminator());
				readDiscriminator(object, (OpenApi30Discriminator) node.getDiscriminator());
			}
		}
		{
			Boolean value = JsonUtil.consumeBooleanProperty(json, "nullable");
			node.setNullable(value);
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

	public void readDiscriminator(ObjectNode json, OpenApi30Discriminator node) {
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

	public void readXML(ObjectNode json, OpenApi30XML node) {
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

	public void readSecurityScheme(ObjectNode json, OpenApi30SecurityScheme node) {
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
				readOAuthFlows(object, (OpenApi30OAuthFlows) node.getFlows());
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

	public void readOAuthFlows(ObjectNode json, OpenApi30OAuthFlows node) {
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "implicit");
			if (object != null) {
				node.setImplicit(node.createOAuthFlow());
				readOAuthFlow(object, (OpenApi30OAuthFlow) node.getImplicit());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "password");
			if (object != null) {
				node.setPassword(node.createOAuthFlow());
				readOAuthFlow(object, (OpenApi30OAuthFlow) node.getPassword());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "clientCredentials");
			if (object != null) {
				node.setClientCredentials(node.createOAuthFlow());
				readOAuthFlow(object, (OpenApi30OAuthFlow) node.getClientCredentials());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "authorizationCode");
			if (object != null) {
				node.setAuthorizationCode(node.createOAuthFlow());
				readOAuthFlow(object, (OpenApi30OAuthFlow) node.getAuthorizationCode());
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

	public void readOAuthFlow(ObjectNode json, OpenApi30OAuthFlow node) {
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

	public void readSecurityRequirement(ObjectNode json, OpenApi30SecurityRequirement node) {
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