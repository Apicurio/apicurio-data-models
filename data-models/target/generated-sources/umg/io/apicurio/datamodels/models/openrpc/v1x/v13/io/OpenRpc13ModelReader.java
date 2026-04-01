package io.apicurio.datamodels.models.openrpc.v1x.v13.io;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.apicurio.datamodels.models.RootNode;
import io.apicurio.datamodels.models.io.ModelReader;
import io.apicurio.datamodels.models.openrpc.v1x.v13.OpenRpc13Components;
import io.apicurio.datamodels.models.openrpc.v1x.v13.OpenRpc13Contact;
import io.apicurio.datamodels.models.openrpc.v1x.v13.OpenRpc13ContentDescriptor;
import io.apicurio.datamodels.models.openrpc.v1x.v13.OpenRpc13Document;
import io.apicurio.datamodels.models.openrpc.v1x.v13.OpenRpc13DocumentImpl;
import io.apicurio.datamodels.models.openrpc.v1x.v13.OpenRpc13Error;
import io.apicurio.datamodels.models.openrpc.v1x.v13.OpenRpc13Example;
import io.apicurio.datamodels.models.openrpc.v1x.v13.OpenRpc13ExamplePairing;
import io.apicurio.datamodels.models.openrpc.v1x.v13.OpenRpc13ExternalDocumentation;
import io.apicurio.datamodels.models.openrpc.v1x.v13.OpenRpc13Info;
import io.apicurio.datamodels.models.openrpc.v1x.v13.OpenRpc13License;
import io.apicurio.datamodels.models.openrpc.v1x.v13.OpenRpc13Link;
import io.apicurio.datamodels.models.openrpc.v1x.v13.OpenRpc13Method;
import io.apicurio.datamodels.models.openrpc.v1x.v13.OpenRpc13Schema;
import io.apicurio.datamodels.models.openrpc.v1x.v13.OpenRpc13Server;
import io.apicurio.datamodels.models.openrpc.v1x.v13.OpenRpc13ServerVariable;
import io.apicurio.datamodels.models.openrpc.v1x.v13.OpenRpc13Tag;
import io.apicurio.datamodels.models.union.BooleanUnionValue;
import io.apicurio.datamodels.models.union.BooleanUnionValueImpl;
import io.apicurio.datamodels.models.union.SchemaListUnionValue;
import io.apicurio.datamodels.models.union.SchemaListUnionValueImpl;
import io.apicurio.datamodels.models.util.JsonUtil;
import io.apicurio.datamodels.models.util.ReaderUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OpenRpc13ModelReader implements ModelReader {

	public void readDocument(ObjectNode json, OpenRpc13Document node) {
		{
			String value = JsonUtil.consumeStringProperty(json, "openrpc");
			node.setOpenrpc(value);
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "info");
			if (object != null) {
				node.setInfo(node.createInfo());
				readInfo(object, (OpenRpc13Info) node.getInfo());
			}
		}
		{
			List<ObjectNode> objects = JsonUtil.consumeObjectArrayProperty(json, "servers");
			if (objects != null) {
				objects.forEach(object -> {
					OpenRpc13Server model = (OpenRpc13Server) node.createServer();
					this.readServer(object, model);
					node.addServer(model);
				});
			}
		}
		{
			List<ObjectNode> objects = JsonUtil.consumeObjectArrayProperty(json, "methods");
			if (objects != null) {
				objects.forEach(object -> {
					OpenRpc13Method model = (OpenRpc13Method) node.createMethod();
					this.readMethod(object, model);
					node.addMethod(model);
				});
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "components");
			if (object != null) {
				node.setComponents(node.createComponents());
				readComponents(object, (OpenRpc13Components) node.getComponents());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "externalDocs");
			if (object != null) {
				node.setExternalDocs(node.createExternalDocumentation());
				readExternalDocumentation(object, (OpenRpc13ExternalDocumentation) node.getExternalDocs());
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
		OpenRpc13Document rootModel = new OpenRpc13DocumentImpl();
		this.readDocument(json, rootModel);
		return rootModel;
	}

	public void readInfo(ObjectNode json, OpenRpc13Info node) {
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
				readContact(object, (OpenRpc13Contact) node.getContact());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "license");
			if (object != null) {
				node.setLicense(node.createLicense());
				readLicense(object, (OpenRpc13License) node.getLicense());
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

	public void readContact(ObjectNode json, OpenRpc13Contact node) {
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

	public void readLicense(ObjectNode json, OpenRpc13License node) {
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

	public void readServer(ObjectNode json, OpenRpc13Server node) {
		{
			String value = JsonUtil.consumeStringProperty(json, "name");
			node.setName(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "url");
			node.setUrl(value);
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
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "variables");
			JsonUtil.keys(object).forEach(name -> {
				ObjectNode mapValue = JsonUtil.consumeObjectProperty(object, name);
				if (mapValue != null) {
					OpenRpc13ServerVariable model = (OpenRpc13ServerVariable) node.createServerVariable();
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

	public void readServerVariable(ObjectNode json, OpenRpc13ServerVariable node) {
		{
			String value = JsonUtil.consumeStringProperty(json, "default");
			node.setDefault(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "description");
			node.setDescription(value);
		}
		{
			List<String> value = JsonUtil.consumeStringArrayProperty(json, "enum");
			node.setEnum(value);
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

	public void readMethod(ObjectNode json, OpenRpc13Method node) {
		{
			String value = JsonUtil.consumeStringProperty(json, "$ref");
			node.set$ref(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "name");
			node.setName(value);
		}
		{
			List<ObjectNode> objects = JsonUtil.consumeObjectArrayProperty(json, "tags");
			if (objects != null) {
				objects.forEach(object -> {
					OpenRpc13Tag model = (OpenRpc13Tag) node.createTag();
					this.readTag(object, model);
					node.addTag(model);
				});
			}
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
				readExternalDocumentation(object, (OpenRpc13ExternalDocumentation) node.getExternalDocs());
			}
		}
		{
			List<ObjectNode> objects = JsonUtil.consumeObjectArrayProperty(json, "params");
			if (objects != null) {
				objects.forEach(object -> {
					OpenRpc13ContentDescriptor model = (OpenRpc13ContentDescriptor) node.createContentDescriptor();
					this.readContentDescriptor(object, model);
					node.addParam(model);
				});
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "result");
			if (object != null) {
				node.setResult(node.createContentDescriptor());
				readContentDescriptor(object, (OpenRpc13ContentDescriptor) node.getResult());
			}
		}
		{
			Boolean value = JsonUtil.consumeBooleanProperty(json, "deprecated");
			node.setDeprecated(value);
		}
		{
			List<ObjectNode> objects = JsonUtil.consumeObjectArrayProperty(json, "servers");
			if (objects != null) {
				objects.forEach(object -> {
					OpenRpc13Server model = (OpenRpc13Server) node.createServer();
					this.readServer(object, model);
					node.addServer(model);
				});
			}
		}
		{
			List<ObjectNode> objects = JsonUtil.consumeObjectArrayProperty(json, "errors");
			if (objects != null) {
				objects.forEach(object -> {
					OpenRpc13Error model = (OpenRpc13Error) node.createError();
					this.readError(object, model);
					node.addError(model);
				});
			}
		}
		{
			List<ObjectNode> objects = JsonUtil.consumeObjectArrayProperty(json, "links");
			if (objects != null) {
				objects.forEach(object -> {
					OpenRpc13Link model = (OpenRpc13Link) node.createLink();
					this.readLink(object, model);
					node.addLink(model);
				});
			}
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "paramStructure");
			node.setParamStructure(value);
		}
		{
			List<ObjectNode> objects = JsonUtil.consumeObjectArrayProperty(json, "examples");
			if (objects != null) {
				objects.forEach(object -> {
					OpenRpc13ExamplePairing model = (OpenRpc13ExamplePairing) node.createExamplePairing();
					this.readExamplePairing(object, model);
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

	public void readContentDescriptor(ObjectNode json, OpenRpc13ContentDescriptor node) {
		{
			String value = JsonUtil.consumeStringProperty(json, "$ref");
			node.set$ref(value);
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
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "schema");
			if (object != null) {
				node.setSchema(node.createSchema());
				readSchema(object, (OpenRpc13Schema) node.getSchema());
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

	public void readSchema(ObjectNode json, OpenRpc13Schema node) {
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
				readSchema(object, (OpenRpc13Schema) node.getIf());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "then");
			if (object != null) {
				node.setThen(node.createSchema());
				readSchema(object, (OpenRpc13Schema) node.getThen());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "else");
			if (object != null) {
				node.setElse(node.createSchema());
				readSchema(object, (OpenRpc13Schema) node.getElse());
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
					OpenRpc13Schema model = (OpenRpc13Schema) node.createSchema();
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
					readSchema(object, (OpenRpc13Schema) node.getAdditionalProperties());
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
				readSchema(object, (OpenRpc13Schema) node.getAdditionalItems());
			}
		}
		{
			JsonNode value = JsonUtil.consumeAnyProperty(json, "items");
			if (value != null) {
				if (JsonUtil.isObject(value)) {
					ObjectNode object = JsonUtil.toObject(value);
					node.setItems(node.createSchema());
					readSchema(object, (OpenRpc13Schema) node.getItems());
				} else if (JsonUtil.isArray(value)) {
					List<JsonNode> array = JsonUtil.toList(value);
					List<OpenRpc13Schema> models = new ArrayList<>();
					array.forEach(item -> {
						ObjectNode object = JsonUtil.toObject(item);
						OpenRpc13Schema model = (OpenRpc13Schema) node.createSchema();
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
				readSchema(object, (OpenRpc13Schema) node.getPropertyNames());
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "contains");
			if (object != null) {
				node.setContains(node.createSchema());
				readSchema(object, (OpenRpc13Schema) node.getContains());
			}
		}
		{
			List<ObjectNode> objects = JsonUtil.consumeObjectArrayProperty(json, "allOf");
			if (objects != null) {
				objects.forEach(object -> {
					OpenRpc13Schema model = (OpenRpc13Schema) node.createSchema();
					this.readSchema(object, model);
					node.addAllOf(model);
				});
			}
		}
		{
			List<ObjectNode> objects = JsonUtil.consumeObjectArrayProperty(json, "oneOf");
			if (objects != null) {
				objects.forEach(object -> {
					OpenRpc13Schema model = (OpenRpc13Schema) node.createSchema();
					this.readSchema(object, model);
					node.addOneOf(model);
				});
			}
		}
		{
			List<ObjectNode> objects = JsonUtil.consumeObjectArrayProperty(json, "anyOf");
			if (objects != null) {
				objects.forEach(object -> {
					OpenRpc13Schema model = (OpenRpc13Schema) node.createSchema();
					this.readSchema(object, model);
					node.addAnyOf(model);
				});
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "not");
			if (object != null) {
				node.setNot(node.createSchema());
				readSchema(object, (OpenRpc13Schema) node.getNot());
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

	public void readExamplePairing(ObjectNode json, OpenRpc13ExamplePairing node) {
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
			String value = JsonUtil.consumeStringProperty(json, "summary");
			node.setSummary(value);
		}
		{
			List<ObjectNode> objects = JsonUtil.consumeObjectArrayProperty(json, "params");
			if (objects != null) {
				objects.forEach(object -> {
					OpenRpc13Example model = (OpenRpc13Example) node.createExample();
					this.readExample(object, model);
					node.addParam(model);
				});
			}
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "result");
			if (object != null) {
				node.setResult(node.createExample());
				readExample(object, (OpenRpc13Example) node.getResult());
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

	public void readExample(ObjectNode json, OpenRpc13Example node) {
		{
			String value = JsonUtil.consumeStringProperty(json, "$ref");
			node.set$ref(value);
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

	public void readLink(ObjectNode json, OpenRpc13Link node) {
		{
			String value = JsonUtil.consumeStringProperty(json, "$ref");
			node.set$ref(value);
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
			String value = JsonUtil.consumeStringProperty(json, "description");
			node.setDescription(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "method");
			node.setMethod(value);
		}
		{
			JsonNode value = JsonUtil.consumeAnyProperty(json, "params");
			node.setParams(value);
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "server");
			if (object != null) {
				node.setServer(node.createServer());
				readServer(object, (OpenRpc13Server) node.getServer());
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

	public void readError(ObjectNode json, OpenRpc13Error node) {
		{
			String value = JsonUtil.consumeStringProperty(json, "$ref");
			node.set$ref(value);
		}
		{
			Integer value = JsonUtil.consumeIntegerProperty(json, "code");
			node.setCode(value);
		}
		{
			String value = JsonUtil.consumeStringProperty(json, "message");
			node.setMessage(value);
		}
		{
			JsonNode value = JsonUtil.consumeAnyProperty(json, "data");
			node.setData(value);
		}
		ReaderUtil.readExtraProperties(json, node);
	}

	public void readComponents(ObjectNode json, OpenRpc13Components node) {
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "contentDescriptors");
			JsonUtil.keys(object).forEach(name -> {
				ObjectNode mapValue = JsonUtil.consumeObjectProperty(object, name);
				if (mapValue != null) {
					OpenRpc13ContentDescriptor model = (OpenRpc13ContentDescriptor) node.createContentDescriptor();
					this.readContentDescriptor(mapValue, model);
					node.addContentDescriptor(name, model);
				}
			});
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "schemas");
			JsonUtil.keys(object).forEach(name -> {
				ObjectNode mapValue = JsonUtil.consumeObjectProperty(object, name);
				if (mapValue != null) {
					OpenRpc13Schema model = (OpenRpc13Schema) node.createSchema();
					this.readSchema(mapValue, model);
					node.addSchema(name, model);
				}
			});
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "examples");
			JsonUtil.keys(object).forEach(name -> {
				ObjectNode mapValue = JsonUtil.consumeObjectProperty(object, name);
				if (mapValue != null) {
					OpenRpc13Example model = (OpenRpc13Example) node.createExample();
					this.readExample(mapValue, model);
					node.addExample(name, model);
				}
			});
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "links");
			JsonUtil.keys(object).forEach(name -> {
				ObjectNode mapValue = JsonUtil.consumeObjectProperty(object, name);
				if (mapValue != null) {
					OpenRpc13Link model = (OpenRpc13Link) node.createLink();
					this.readLink(mapValue, model);
					node.addLink(name, model);
				}
			});
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "errors");
			JsonUtil.keys(object).forEach(name -> {
				ObjectNode mapValue = JsonUtil.consumeObjectProperty(object, name);
				if (mapValue != null) {
					OpenRpc13Error model = (OpenRpc13Error) node.createError();
					this.readError(mapValue, model);
					node.addError(name, model);
				}
			});
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "examplePairings");
			JsonUtil.keys(object).forEach(name -> {
				ObjectNode mapValue = JsonUtil.consumeObjectProperty(object, name);
				if (mapValue != null) {
					OpenRpc13ExamplePairing model = (OpenRpc13ExamplePairing) node.createExamplePairing();
					this.readExamplePairing(mapValue, model);
					node.addExamplePairing(name, model);
				}
			});
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "tags");
			JsonUtil.keys(object).forEach(name -> {
				ObjectNode mapValue = JsonUtil.consumeObjectProperty(object, name);
				if (mapValue != null) {
					OpenRpc13Tag model = (OpenRpc13Tag) node.createTag();
					this.readTag(mapValue, model);
					node.addTag(name, model);
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

	public void readTag(ObjectNode json, OpenRpc13Tag node) {
		{
			String value = JsonUtil.consumeStringProperty(json, "$ref");
			node.set$ref(value);
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
			String value = JsonUtil.consumeStringProperty(json, "description");
			node.setDescription(value);
		}
		{
			ObjectNode object = JsonUtil.consumeObjectProperty(json, "externalDocs");
			if (object != null) {
				node.setExternalDocs(node.createExternalDocumentation());
				readExternalDocumentation(object, (OpenRpc13ExternalDocumentation) node.getExternalDocs());
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

	public void readExternalDocumentation(ObjectNode json, OpenRpc13ExternalDocumentation node) {
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
}