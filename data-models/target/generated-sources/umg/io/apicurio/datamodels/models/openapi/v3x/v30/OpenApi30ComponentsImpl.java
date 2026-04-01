package io.apicurio.datamodels.models.openapi.v3x.v30;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.NodeImpl;
import io.apicurio.datamodels.models.ParentPropertyType;
import io.apicurio.datamodels.models.openapi.OpenApiCallback;
import io.apicurio.datamodels.models.openapi.OpenApiExample;
import io.apicurio.datamodels.models.openapi.OpenApiHeader;
import io.apicurio.datamodels.models.openapi.OpenApiLink;
import io.apicurio.datamodels.models.openapi.OpenApiParameter;
import io.apicurio.datamodels.models.openapi.OpenApiRequestBody;
import io.apicurio.datamodels.models.openapi.OpenApiResponse;
import io.apicurio.datamodels.models.openapi.OpenApiSchema;
import io.apicurio.datamodels.models.openapi.OpenApiSecurityScheme;
import io.apicurio.datamodels.models.openapi.v3x.v30.visitors.OpenApi30Visitor;
import io.apicurio.datamodels.models.util.DataModelUtil;
import io.apicurio.datamodels.models.visitors.Visitor;
import java.util.LinkedHashMap;
import java.util.Map;

public class OpenApi30ComponentsImpl extends NodeImpl implements OpenApi30Components {

	private Map<String, OpenApiSchema> schemas;
	private Map<String, OpenApiResponse> responses;
	private Map<String, OpenApiParameter> parameters;
	private Map<String, OpenApiExample> examples;
	private Map<String, OpenApiRequestBody> requestBodies;
	private Map<String, OpenApiHeader> headers;
	private Map<String, OpenApiSecurityScheme> securitySchemes;
	private Map<String, OpenApiLink> links;
	private Map<String, OpenApiCallback> callbacks;
	private Map<String, JsonNode> extensions;

	@Override
	public OpenApi30Schema createSchema() {
		OpenApi30SchemaImpl node = new OpenApi30SchemaImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public Map<String, OpenApiSchema> getSchemas() {
		return schemas;
	}

	@Override
	public void addSchema(String name, OpenApiSchema value) {
		if (this.schemas == null) {
			this.schemas = new LinkedHashMap<>();
		}
		this.schemas.put(name, value);
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("schemas");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.map);
			((NodeImpl) value)._setMapPropertyName(name);
		}
	}

	@Override
	public void clearSchemas() {
		if (this.schemas != null) {
			this.schemas.clear();
		}
	}

	@Override
	public void removeSchema(String name) {
		if (this.schemas != null) {
			this.schemas.remove(name);
		}
	}

	@Override
	public void insertSchema(String name, OpenApiSchema value, int atIndex) {
		if (this.schemas == null) {
			this.schemas = new LinkedHashMap<>();
			this.schemas.put(name, value);
		} else {
			this.schemas = DataModelUtil.insertMapEntry(this.schemas, name, value, atIndex);
		}
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("schemas");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.map);
			((NodeImpl) value)._setMapPropertyName(name);
		}
	}

	@Override
	public OpenApi30Response createResponse() {
		OpenApi30ResponseImpl node = new OpenApi30ResponseImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public Map<String, OpenApiResponse> getResponses() {
		return responses;
	}

	@Override
	public void addResponse(String name, OpenApiResponse value) {
		if (this.responses == null) {
			this.responses = new LinkedHashMap<>();
		}
		this.responses.put(name, value);
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("responses");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.map);
			((NodeImpl) value)._setMapPropertyName(name);
		}
	}

	@Override
	public void clearResponses() {
		if (this.responses != null) {
			this.responses.clear();
		}
	}

	@Override
	public void removeResponse(String name) {
		if (this.responses != null) {
			this.responses.remove(name);
		}
	}

	@Override
	public void insertResponse(String name, OpenApiResponse value, int atIndex) {
		if (this.responses == null) {
			this.responses = new LinkedHashMap<>();
			this.responses.put(name, value);
		} else {
			this.responses = DataModelUtil.insertMapEntry(this.responses, name, value, atIndex);
		}
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("responses");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.map);
			((NodeImpl) value)._setMapPropertyName(name);
		}
	}

	@Override
	public OpenApi30Parameter createParameter() {
		OpenApi30ParameterImpl node = new OpenApi30ParameterImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public Map<String, OpenApiParameter> getParameters() {
		return parameters;
	}

	@Override
	public void addParameter(String name, OpenApiParameter value) {
		if (this.parameters == null) {
			this.parameters = new LinkedHashMap<>();
		}
		this.parameters.put(name, value);
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("parameters");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.map);
			((NodeImpl) value)._setMapPropertyName(name);
		}
	}

	@Override
	public void clearParameters() {
		if (this.parameters != null) {
			this.parameters.clear();
		}
	}

	@Override
	public void removeParameter(String name) {
		if (this.parameters != null) {
			this.parameters.remove(name);
		}
	}

	@Override
	public void insertParameter(String name, OpenApiParameter value, int atIndex) {
		if (this.parameters == null) {
			this.parameters = new LinkedHashMap<>();
			this.parameters.put(name, value);
		} else {
			this.parameters = DataModelUtil.insertMapEntry(this.parameters, name, value, atIndex);
		}
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("parameters");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.map);
			((NodeImpl) value)._setMapPropertyName(name);
		}
	}

	@Override
	public OpenApi30Example createExample() {
		OpenApi30ExampleImpl node = new OpenApi30ExampleImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public Map<String, OpenApiExample> getExamples() {
		return examples;
	}

	@Override
	public void addExample(String name, OpenApiExample value) {
		if (this.examples == null) {
			this.examples = new LinkedHashMap<>();
		}
		this.examples.put(name, value);
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("examples");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.map);
			((NodeImpl) value)._setMapPropertyName(name);
		}
	}

	@Override
	public void clearExamples() {
		if (this.examples != null) {
			this.examples.clear();
		}
	}

	@Override
	public void removeExample(String name) {
		if (this.examples != null) {
			this.examples.remove(name);
		}
	}

	@Override
	public void insertExample(String name, OpenApiExample value, int atIndex) {
		if (this.examples == null) {
			this.examples = new LinkedHashMap<>();
			this.examples.put(name, value);
		} else {
			this.examples = DataModelUtil.insertMapEntry(this.examples, name, value, atIndex);
		}
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("examples");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.map);
			((NodeImpl) value)._setMapPropertyName(name);
		}
	}

	@Override
	public OpenApi30RequestBody createRequestBody() {
		OpenApi30RequestBodyImpl node = new OpenApi30RequestBodyImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public Map<String, OpenApiRequestBody> getRequestBodies() {
		return requestBodies;
	}

	@Override
	public void addRequestBody(String name, OpenApiRequestBody value) {
		if (this.requestBodies == null) {
			this.requestBodies = new LinkedHashMap<>();
		}
		this.requestBodies.put(name, value);
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("requestBodies");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.map);
			((NodeImpl) value)._setMapPropertyName(name);
		}
	}

	@Override
	public void clearRequestBodies() {
		if (this.requestBodies != null) {
			this.requestBodies.clear();
		}
	}

	@Override
	public void removeRequestBody(String name) {
		if (this.requestBodies != null) {
			this.requestBodies.remove(name);
		}
	}

	@Override
	public void insertRequestBody(String name, OpenApiRequestBody value, int atIndex) {
		if (this.requestBodies == null) {
			this.requestBodies = new LinkedHashMap<>();
			this.requestBodies.put(name, value);
		} else {
			this.requestBodies = DataModelUtil.insertMapEntry(this.requestBodies, name, value, atIndex);
		}
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("requestBodies");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.map);
			((NodeImpl) value)._setMapPropertyName(name);
		}
	}

	@Override
	public OpenApi30Header createHeader() {
		OpenApi30HeaderImpl node = new OpenApi30HeaderImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public Map<String, OpenApiHeader> getHeaders() {
		return headers;
	}

	@Override
	public void addHeader(String name, OpenApiHeader value) {
		if (this.headers == null) {
			this.headers = new LinkedHashMap<>();
		}
		this.headers.put(name, value);
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("headers");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.map);
			((NodeImpl) value)._setMapPropertyName(name);
		}
	}

	@Override
	public void clearHeaders() {
		if (this.headers != null) {
			this.headers.clear();
		}
	}

	@Override
	public void removeHeader(String name) {
		if (this.headers != null) {
			this.headers.remove(name);
		}
	}

	@Override
	public void insertHeader(String name, OpenApiHeader value, int atIndex) {
		if (this.headers == null) {
			this.headers = new LinkedHashMap<>();
			this.headers.put(name, value);
		} else {
			this.headers = DataModelUtil.insertMapEntry(this.headers, name, value, atIndex);
		}
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("headers");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.map);
			((NodeImpl) value)._setMapPropertyName(name);
		}
	}

	@Override
	public OpenApi30SecurityScheme createSecurityScheme() {
		OpenApi30SecuritySchemeImpl node = new OpenApi30SecuritySchemeImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public Map<String, OpenApiSecurityScheme> getSecuritySchemes() {
		return securitySchemes;
	}

	@Override
	public void addSecurityScheme(String name, OpenApiSecurityScheme value) {
		if (this.securitySchemes == null) {
			this.securitySchemes = new LinkedHashMap<>();
		}
		this.securitySchemes.put(name, value);
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("securitySchemes");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.map);
			((NodeImpl) value)._setMapPropertyName(name);
		}
	}

	@Override
	public void clearSecuritySchemes() {
		if (this.securitySchemes != null) {
			this.securitySchemes.clear();
		}
	}

	@Override
	public void removeSecurityScheme(String name) {
		if (this.securitySchemes != null) {
			this.securitySchemes.remove(name);
		}
	}

	@Override
	public void insertSecurityScheme(String name, OpenApiSecurityScheme value, int atIndex) {
		if (this.securitySchemes == null) {
			this.securitySchemes = new LinkedHashMap<>();
			this.securitySchemes.put(name, value);
		} else {
			this.securitySchemes = DataModelUtil.insertMapEntry(this.securitySchemes, name, value, atIndex);
		}
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("securitySchemes");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.map);
			((NodeImpl) value)._setMapPropertyName(name);
		}
	}

	@Override
	public OpenApi30Link createLink() {
		OpenApi30LinkImpl node = new OpenApi30LinkImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public Map<String, OpenApiLink> getLinks() {
		return links;
	}

	@Override
	public void addLink(String name, OpenApiLink value) {
		if (this.links == null) {
			this.links = new LinkedHashMap<>();
		}
		this.links.put(name, value);
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("links");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.map);
			((NodeImpl) value)._setMapPropertyName(name);
		}
	}

	@Override
	public void clearLinks() {
		if (this.links != null) {
			this.links.clear();
		}
	}

	@Override
	public void removeLink(String name) {
		if (this.links != null) {
			this.links.remove(name);
		}
	}

	@Override
	public void insertLink(String name, OpenApiLink value, int atIndex) {
		if (this.links == null) {
			this.links = new LinkedHashMap<>();
			this.links.put(name, value);
		} else {
			this.links = DataModelUtil.insertMapEntry(this.links, name, value, atIndex);
		}
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("links");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.map);
			((NodeImpl) value)._setMapPropertyName(name);
		}
	}

	@Override
	public OpenApi30Callback createCallback() {
		OpenApi30CallbackImpl node = new OpenApi30CallbackImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public Map<String, OpenApiCallback> getCallbacks() {
		return callbacks;
	}

	@Override
	public void addCallback(String name, OpenApiCallback value) {
		if (this.callbacks == null) {
			this.callbacks = new LinkedHashMap<>();
		}
		this.callbacks.put(name, value);
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("callbacks");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.map);
			((NodeImpl) value)._setMapPropertyName(name);
		}
	}

	@Override
	public void clearCallbacks() {
		if (this.callbacks != null) {
			this.callbacks.clear();
		}
	}

	@Override
	public void removeCallback(String name) {
		if (this.callbacks != null) {
			this.callbacks.remove(name);
		}
	}

	@Override
	public void insertCallback(String name, OpenApiCallback value, int atIndex) {
		if (this.callbacks == null) {
			this.callbacks = new LinkedHashMap<>();
			this.callbacks.put(name, value);
		} else {
			this.callbacks = DataModelUtil.insertMapEntry(this.callbacks, name, value, atIndex);
		}
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("callbacks");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.map);
			((NodeImpl) value)._setMapPropertyName(name);
		}
	}

	@Override
	public Map<String, JsonNode> getExtensions() {
		return extensions;
	}

	@Override
	public void addExtension(String name, JsonNode value) {
		if (this.extensions == null) {
			this.extensions = new LinkedHashMap<>();
		}
		this.extensions.put(name, value);
	}

	@Override
	public void clearExtensions() {
		if (this.extensions != null) {
			this.extensions.clear();
		}
	}

	@Override
	public void removeExtension(String name) {
		if (this.extensions != null) {
			this.extensions.remove(name);
		}
	}

	@Override
	public void insertExtension(String name, JsonNode value, int atIndex) {
		if (this.extensions == null) {
			this.extensions = new LinkedHashMap<>();
			this.extensions.put(name, value);
		} else {
			this.extensions = DataModelUtil.insertMapEntry(this.extensions, name, value, atIndex);
		}
	}

	@Override
	public void accept(Visitor visitor) {
		OpenApi30Visitor viz = (OpenApi30Visitor) visitor;
		viz.visitComponents(this);
	}

	@Override
	public Node emptyClone() {
		return new OpenApi30ComponentsImpl();
	}
}