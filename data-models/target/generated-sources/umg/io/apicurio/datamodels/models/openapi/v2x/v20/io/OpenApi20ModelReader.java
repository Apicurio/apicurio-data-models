package io.apicurio.datamodels.models.openapi.v2x.v20.io;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.apicurio.datamodels.models.RootNode;
import io.apicurio.datamodels.models.io.ModelReader;
import io.apicurio.datamodels.models.openapi.v2x.v20.OpenApi20Contact;
import io.apicurio.datamodels.models.openapi.v2x.v20.OpenApi20Definitions;
import io.apicurio.datamodels.models.openapi.v2x.v20.OpenApi20Document;
import io.apicurio.datamodels.models.openapi.v2x.v20.OpenApi20DocumentImpl;
import io.apicurio.datamodels.models.openapi.v2x.v20.OpenApi20Example;
import io.apicurio.datamodels.models.openapi.v2x.v20.OpenApi20ExternalDocumentation;
import io.apicurio.datamodels.models.openapi.v2x.v20.OpenApi20Header;
import io.apicurio.datamodels.models.openapi.v2x.v20.OpenApi20Info;
import io.apicurio.datamodels.models.openapi.v2x.v20.OpenApi20Items;
import io.apicurio.datamodels.models.openapi.v2x.v20.OpenApi20License;
import io.apicurio.datamodels.models.openapi.v2x.v20.OpenApi20Operation;
import io.apicurio.datamodels.models.openapi.v2x.v20.OpenApi20Parameter;
import io.apicurio.datamodels.models.openapi.v2x.v20.OpenApi20ParameterDefinitions;
import io.apicurio.datamodels.models.openapi.v2x.v20.OpenApi20PathItem;
import io.apicurio.datamodels.models.openapi.v2x.v20.OpenApi20Paths;
import io.apicurio.datamodels.models.openapi.v2x.v20.OpenApi20Response;
import io.apicurio.datamodels.models.openapi.v2x.v20.OpenApi20ResponseDefinitions;
import io.apicurio.datamodels.models.openapi.v2x.v20.OpenApi20Responses;
import io.apicurio.datamodels.models.openapi.v2x.v20.OpenApi20Schema;
import io.apicurio.datamodels.models.openapi.v2x.v20.OpenApi20Scopes;
import io.apicurio.datamodels.models.openapi.v2x.v20.OpenApi20SecurityDefinitions;
import io.apicurio.datamodels.models.openapi.v2x.v20.OpenApi20SecurityRequirement;
import io.apicurio.datamodels.models.openapi.v2x.v20.OpenApi20SecurityScheme;
import io.apicurio.datamodels.models.openapi.v2x.v20.OpenApi20Tag;
import io.apicurio.datamodels.models.openapi.v2x.v20.OpenApi20XML;
import io.apicurio.datamodels.models.union.BooleanUnionValue;
import io.apicurio.datamodels.models.union.BooleanUnionValueImpl;
import io.apicurio.datamodels.models.union.SchemaListUnionValue;
import io.apicurio.datamodels.models.union.SchemaListUnionValueImpl;
import io.apicurio.datamodels.models.util.JsonUtil;
import io.apicurio.datamodels.models.util.ReaderUtil;
import java.util.ArrayList;
import java.util.List;

public class OpenApi20ModelReader implements ModelReader {

	public void readDocument(ObjectNode json, OpenApi20Document node) {
		{
			String value = JsonUtil.consumeStringProperty(json, "swagger");
			node.setSwagger(value);
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "info");
			if (object != null) {
				node.setInfo(node.createInfo());
				readInfo(object, (OpenApi20Info) node.getInfo());
			}
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "host");
			node.setHost(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "basePath");
			node.setBasePath(value);
		}
		{
			List<String> value = JsonUtil.consumeStringArrayProperty(json, "schemes");
			node.setSchemes(value);
		}
		{
			List<String> value = JsonUtil.consumeStringArrayProperty(json, "consumes");
			node.setConsumes(value);
		}
		{
			List<String> value = JsonUtil.consumeStringArrayProperty(json, "produces");
			node.setProduces(value);
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "paths");
			if (object != null) {
				node.setPaths(node.createPaths());
				readPaths(object, (OpenApi20Paths) node.getPaths());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "definitions");
			if (object != null) {
				node.setDefinitions(node.createDefinitions());
				readDefinitions(object, (OpenApi20Definitions) node.getDefinitions());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "parameters");
			if (object != null) {
				node.setParameters(node.createParameterDefinitions());
				readParameterDefinitions(object, (OpenApi20ParameterDefinitions) node.getParameters());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "responses");
			if (object != null) {
				node.setResponses(node.createResponseDefinitions());
				readResponseDefinitions(object, (OpenApi20ResponseDefinitions) node.getResponses());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "securityDefinitions");
			if (object != null) {
				node.setSecurityDefinitions(node.createSecurityDefinitions());
				readSecurityDefinitions(object, (OpenApi20SecurityDefinitions) node.getSecurityDefinitions());
			}
		}
		{
			List<ObjectNode> objects = JsonUtil.consumeObjectArrayProperty(json, "security");
			if (objects != null) {
				objects.forEach(object -> {
					OpenApi20SecurityRequirement model = (OpenApi20SecurityRequirement) node
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
					OpenApi20Tag model = (OpenApi20Tag) node.createTag();
					this.readTag(object, model);
					node.addTag(model);
				});
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "externalDocs");
			if (object != null) {
				node.setExternalDocs(node.createExternalDocumentation());
				readExternalDocumentation(object, (OpenApi20ExternalDocumentation) node.getExternalDocs());
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
		OpenApi20Document rootModel = new OpenApi20DocumentImpl();
		this.readDocument(json, rootModel);
		return rootModel;
	}

	public void readInfo(ObjectNode json, OpenApi20Info node) {
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
				readContact(object, (OpenApi20Contact) node.getContact());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "license");
			if (object != null) {
				node.setLicense(node.createLicense());
				readLicense(object, (OpenApi20License) node.getLicense());
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

	public void readContact(ObjectNode json, OpenApi20Contact node) {
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

	public void readLicense(ObjectNode json, OpenApi20License node) {
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

	public void readPaths(ObjectNode json, OpenApi20Paths node) {
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
					OpenApi20PathItem model = (OpenApi20PathItem) node.createPathItem();
					this.readPathItem(object, model);
					node.addItem(name, model);
				}
			});
		}
		ReaderUtil.readExtraProperties(json, node);
	}

	public void readPathItem(ObjectNode json, OpenApi20PathItem node) {
		{
			String value = JsonUtil.consumeStringProperty(json, "$ref");
			node.set$ref(value);
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "get");
			if (object != null) {
				node.setGet(node.createOperation());
				readOperation(object, (OpenApi20Operation) node.getGet());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "put");
			if (object != null) {
				node.setPut(node.createOperation());
				readOperation(object, (OpenApi20Operation) node.getPut());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "post");
			if (object != null) {
				node.setPost(node.createOperation());
				readOperation(object, (OpenApi20Operation) node.getPost());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "delete");
			if (object != null) {
				node.setDelete(node.createOperation());
				readOperation(object, (OpenApi20Operation) node.getDelete());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "options");
			if (object != null) {
				node.setOptions(node.createOperation());
				readOperation(object, (OpenApi20Operation) node.getOptions());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "head");
			if (object != null) {
				node.setHead(node.createOperation());
				readOperation(object, (OpenApi20Operation) node.getHead());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "patch");
			if (object != null) {
				node.setPatch(node.createOperation());
				readOperation(object, (OpenApi20Operation) node.getPatch());
			}
		}
		{
			List<ObjectNode> objects = JsonUtil.consumeObjectArrayProperty(json, "parameters");
			if (objects != null) {
				objects.forEach(object -> {
					OpenApi20Parameter model = (OpenApi20Parameter) node.createParameter();
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

	public void readOperation(ObjectNode json, OpenApi20Operation node) {
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
				readExternalDocumentation(object, (OpenApi20ExternalDocumentation) node.getExternalDocs());
			}
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "operationId");
			node.setOperationId(value);
		}
		{
			List<String> value = JsonUtil.consumeStringArrayProperty(json, "consumes");
			node.setConsumes(value);
		}
		{
			List<String> value = JsonUtil.consumeStringArrayProperty(json, "produces");
			node.setProduces(value);
		}
		{
			List<ObjectNode> objects = JsonUtil.consumeObjectArrayProperty(json, "parameters");
			if (objects != null) {
				objects.forEach(object -> {
					OpenApi20Parameter model = (OpenApi20Parameter) node.createParameter();
					this.readParameter(object, model);
					node.addParameter(model);
				});
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "responses");
			if (object != null) {
				node.setResponses(node.createResponses());
				readResponses(object, (OpenApi20Responses) node.getResponses());
			}
		}
		{
			List<String> value = JsonUtil.consumeStringArrayProperty(json, "schemes");
			node.setSchemes(value);
		}
		{
			Boolean value = JsonUtil.consumeBooleanProperty(json, "deprecated");
			node.setDeprecated(value);
		}
		{
			List<ObjectNode> objects = JsonUtil.consumeObjectArrayProperty(json, "security");
			if (objects != null) {
				objects.forEach(object -> {
					OpenApi20SecurityRequirement model = (OpenApi20SecurityRequirement) node
							.createSecurityRequirement();
					this.readSecurityRequirement(object, model);
					node.addSecurity(model);
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

	public void readExternalDocumentation(ObjectNode json, OpenApi20ExternalDocumentation node) {
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

	public void readParameter(ObjectNode json, OpenApi20Parameter node) {
		{
			String value = JsonUtil.consumeStringProperty(json, "$ref");
			node.set$ref(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "description");
			node.setDescription(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "type");
			node.setType(value);
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
			List<JsonNode> value = JsonUtil.consumeAnyArrayProperty(json, "enum");
			node.setEnum(value);
		}
		{
			Number value = JsonUtil.consumeNumberProperty(json, "multipleOf");
			node.setMultipleOf(value);
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
			Boolean value = JsonUtil.consumeBooleanProperty(json, "required");
			node.setRequired(value);
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "schema");
			if (object != null) {
				node.setSchema(node.createSchema());
				readSchema(object, (OpenApi20Schema) node.getSchema());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "items");
			if (object != null) {
				node.setItems(node.createItems());
				readItems(object, (OpenApi20Items) node.getItems());
			}
		}
		{
			Boolean value = JsonUtil.consumeBooleanProperty(json, "allowEmptyValue");
			node.setAllowEmptyValue(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "collectionFormat");
			node.setCollectionFormat(value);
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

	public void readItems(ObjectNode json, OpenApi20Items node) {
		{
			String value = JsonUtil.consumeStringProperty(json, "$ref");
			node.set$ref(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "description");
			node.setDescription(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "type");
			node.setType(value);
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
			List<JsonNode> value = JsonUtil.consumeAnyArrayProperty(json, "enum");
			node.setEnum(value);
		}
		{
			Number value = JsonUtil.consumeNumberProperty(json, "multipleOf");
			node.setMultipleOf(value);
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "items");
			if (object != null) {
				node.setItems(node.createItems());
				readItems(object, (OpenApi20Items) node.getItems());
			}
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "collectionFormat");
			node.setCollectionFormat(value);
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

	public void readResponses(ObjectNode json, OpenApi20Responses node) {
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "default");
			if (object != null) {
				node.setDefault(node.createResponse());
				readResponse(object, (OpenApi20Response) node.getDefault());
			}
		}
		{
			List<String> propertyNames = JsonUtil.keys(json);
			propertyNames.forEach(name -> {
				ObjectNode object = JsonUtil.consumeObjectProperty(json, name);
				if (object != null) {
					OpenApi20Response model = (OpenApi20Response) node.createResponse();
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

	public void readResponse(ObjectNode json, OpenApi20Response node) {
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
				readSchema(object, (OpenApi20Schema) node.getSchema());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "headers");
			JsonUtil.keys(object).forEach(name -> {
				ObjectNode mapValue = JsonUtil.consumeObjectProperty(object, name);
				if (mapValue != null) {
					OpenApi20Header model = (OpenApi20Header) node.createHeader();
					this.readHeader(mapValue, model);
					node.addHeader(name, model);
				}
			});
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "examples");
			if (object != null) {
				node.setExamples(node.createExample());
				readExample(object, (OpenApi20Example) node.getExamples());
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

	public void readExample(ObjectNode json, OpenApi20Example node) {
		{
			List<String> propertyNames = JsonUtil.keys(json);
			propertyNames.forEach(name -> {
				JsonNode value = JsonUtil.consumeAnyProperty(json, name);
				node.addItem(name, value);
			});
		}
		ReaderUtil.readExtraProperties(json, node);
	}

	public void readHeader(ObjectNode json, OpenApi20Header node) {
		{
			String value = JsonUtil.consumeStringProperty(json, "description");
			node.setDescription(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "type");
			node.setType(value);
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
			List<JsonNode> value = JsonUtil.consumeAnyArrayProperty(json, "enum");
			node.setEnum(value);
		}
		{
			Number value = JsonUtil.consumeNumberProperty(json, "multipleOf");
			node.setMultipleOf(value);
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "items");
			if (object != null) {
				node.setItems(node.createItems());
				readItems(object, (OpenApi20Items) node.getItems());
			}
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "collectionFormat");
			node.setCollectionFormat(value);
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

	public void readTag(ObjectNode json, OpenApi20Tag node) {
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
				readExternalDocumentation(object, (OpenApi20ExternalDocumentation) node.getExternalDocs());
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

	public void readSchema(ObjectNode json, OpenApi20Schema node) {
		{
			String value = JsonUtil.consumeStringProperty(json, "$ref");
			node.set$ref(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "description");
			node.setDescription(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "type");
			node.setType(value);
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
			List<JsonNode> value = JsonUtil.consumeAnyArrayProperty(json, "enum");
			node.setEnum(value);
		}
		{
			Number value = JsonUtil.consumeNumberProperty(json, "multipleOf");
			node.setMultipleOf(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "title");
			node.setTitle(value);
		}
		{
			JsonNode value = JsonUtil.consumeAnyProperty(json, "items");
			if (value != null) {
				if (JsonUtil.isObject(value)) {
					ObjectNode object = JsonUtil.toObject(value);
					node.setItems(node.createSchema());
					readSchema(object, (OpenApi20Schema) node.getItems());
				} else if (JsonUtil.isArray(value)) {
					List<JsonNode> array = JsonUtil.toList(value);
					List<OpenApi20Schema> models = new ArrayList<>();
					array.forEach(item -> {
						ObjectNode object = JsonUtil.toObject(item);
						OpenApi20Schema model = (OpenApi20Schema) node.createSchema();
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
			List<ObjectNode> objects = JsonUtil.consumeObjectArrayProperty(json, "allOf");
			if (objects != null) {
				objects.forEach(object -> {
					OpenApi20Schema model = (OpenApi20Schema) node.createSchema();
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
					OpenApi20Schema model = (OpenApi20Schema) node.createSchema();
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
					readSchema(object, (OpenApi20Schema) node.getAdditionalProperties());
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
			String value = JsonUtil.consumeStringProperty(json, "discriminator");
			node.setDiscriminator(value);
		}
		{
			Boolean value = JsonUtil.consumeBooleanProperty(json, "readOnly");
			node.setReadOnly(value);
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "xml");
			if (object != null) {
				node.setXml(node.createXML());
				readXML(object, (OpenApi20XML) node.getXml());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "externalDocs");
			if (object != null) {
				node.setExternalDocs(node.createExternalDocumentation());
				readExternalDocumentation(object, (OpenApi20ExternalDocumentation) node.getExternalDocs());
			}
		}
		{
			JsonNode value = JsonUtil.consumeAnyProperty(json, "example");
			node.setExample(value);
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

	public void readXML(ObjectNode json, OpenApi20XML node) {
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

	public void readDefinitions(ObjectNode json, OpenApi20Definitions node) {
		{
			List<String> propertyNames = JsonUtil.keys(json);
			propertyNames.forEach(name -> {
				ObjectNode object = JsonUtil.consumeObjectProperty(json, name);
				if (object != null) {
					OpenApi20Schema model = (OpenApi20Schema) node.createSchema();
					this.readSchema(object, model);
					node.addItem(name, model);
				}
			});
		}
		ReaderUtil.readExtraProperties(json, node);
	}

	public void readParameterDefinitions(ObjectNode json, OpenApi20ParameterDefinitions node) {
		{
			List<String> propertyNames = JsonUtil.keys(json);
			propertyNames.forEach(name -> {
				ObjectNode object = JsonUtil.consumeObjectProperty(json, name);
				if (object != null) {
					OpenApi20Parameter model = (OpenApi20Parameter) node.createParameter();
					this.readParameter(object, model);
					node.addItem(name, model);
				}
			});
		}
		ReaderUtil.readExtraProperties(json, node);
	}

	public void readResponseDefinitions(ObjectNode json, OpenApi20ResponseDefinitions node) {
		{
			List<String> propertyNames = JsonUtil.keys(json);
			propertyNames.forEach(name -> {
				ObjectNode object = JsonUtil.consumeObjectProperty(json, name);
				if (object != null) {
					OpenApi20Response model = (OpenApi20Response) node.createResponse();
					this.readResponse(object, model);
					node.addItem(name, model);
				}
			});
		}
		ReaderUtil.readExtraProperties(json, node);
	}

	public void readSecurityDefinitions(ObjectNode json, OpenApi20SecurityDefinitions node) {
		{
			List<String> propertyNames = JsonUtil.keys(json);
			propertyNames.forEach(name -> {
				ObjectNode object = JsonUtil.consumeObjectProperty(json, name);
				if (object != null) {
					OpenApi20SecurityScheme model = (OpenApi20SecurityScheme) node.createSecurityScheme();
					this.readSecurityScheme(object, model);
					node.addItem(name, model);
				}
			});
		}
		ReaderUtil.readExtraProperties(json, node);
	}

	public void readSecurityScheme(ObjectNode json, OpenApi20SecurityScheme node) {
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
			String value = JsonUtil.consumeStringProperty(json, "flow");
			node.setFlow(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "authorizationUrl");
			node.setAuthorizationUrl(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "tokenUrl");
			node.setTokenUrl(value);
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "scopes");
			if (object != null) {
				node.setScopes(node.createScopes());
				readScopes(object, (OpenApi20Scopes) node.getScopes());
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

	public void readScopes(ObjectNode json, OpenApi20Scopes node) {
		{
			List<String> propertyNames = JsonUtil.keys(json);
			propertyNames.forEach(name -> {
				String value = JsonUtil.consumeStringProperty(json, name);
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

	public void readSecurityRequirement(ObjectNode json, OpenApi20SecurityRequirement node) {
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