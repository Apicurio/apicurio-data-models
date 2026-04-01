package io.apicurio.datamodels.models.openapi.v3x.v32;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.ExternalDocumentation;
import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.NodeImpl;
import io.apicurio.datamodels.models.ParentPropertyType;
import io.apicurio.datamodels.models.SecurityRequirement;
import io.apicurio.datamodels.models.Server;
import io.apicurio.datamodels.models.openapi.OpenApiParameter;
import io.apicurio.datamodels.models.openapi.OpenApiResponses;
import io.apicurio.datamodels.models.openapi.v3x.OpenApi3xCallback;
import io.apicurio.datamodels.models.openapi.v3x.OpenApi3xRequestBody;
import io.apicurio.datamodels.models.openapi.v3x.v32.visitors.OpenApi32Visitor;
import io.apicurio.datamodels.models.util.DataModelUtil;
import io.apicurio.datamodels.models.visitors.Visitor;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class OpenApi32OperationImpl extends NodeImpl implements OpenApi32Operation {

	private List<String> tags;
	private String summary;
	private String description;
	private ExternalDocumentation externalDocs;
	private String operationId;
	private List<OpenApiParameter> parameters;
	private OpenApi3xRequestBody requestBody;
	private OpenApiResponses responses;
	private Map<String, OpenApi3xCallback> callbacks;
	private Boolean deprecated;
	private List<SecurityRequirement> security;
	private List<Server> servers;
	private Map<String, JsonNode> extensions;

	@Override
	public List<String> getTags() {
		return tags;
	}

	@Override
	public void setTags(List<String> value) {
		this.tags = value;
	}

	@Override
	public String getSummary() {
		return summary;
	}

	@Override
	public void setSummary(String value) {
		this.summary = value;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public void setDescription(String value) {
		this.description = value;
	}

	@Override
	public ExternalDocumentation getExternalDocs() {
		return externalDocs;
	}

	@Override
	public void setExternalDocs(ExternalDocumentation value) {
		this.externalDocs = value;
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("externalDocs");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.standard);
		}
	}

	@Override
	public OpenApi32ExternalDocumentation createExternalDocumentation() {
		OpenApi32ExternalDocumentationImpl node = new OpenApi32ExternalDocumentationImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public String getOperationId() {
		return operationId;
	}

	@Override
	public void setOperationId(String value) {
		this.operationId = value;
	}

	@Override
	public OpenApi32Parameter createParameter() {
		OpenApi32ParameterImpl node = new OpenApi32ParameterImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public List<OpenApiParameter> getParameters() {
		return parameters;
	}

	@Override
	public void addParameter(OpenApiParameter value) {
		if (this.parameters == null) {
			this.parameters = new ArrayList<>();
		}
		this.parameters.add(value);
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("parameters");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.array);
		}
	}

	@Override
	public void clearParameters() {
		if (this.parameters != null) {
			this.parameters.clear();
		}
	}

	@Override
	public void removeParameter(OpenApiParameter value) {
		if (this.parameters != null) {
			this.parameters.remove(value);
		}
	}

	@Override
	public void insertParameter(OpenApiParameter value, int atIndex) {
		if (this.parameters == null) {
			this.parameters = new ArrayList<>();
			this.parameters.add(value);
		} else {
			this.parameters = DataModelUtil.insertListEntry(this.parameters, value, atIndex);
		}
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("parameters");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.array);
		}
	}

	@Override
	public OpenApi3xRequestBody getRequestBody() {
		return requestBody;
	}

	@Override
	public void setRequestBody(OpenApi3xRequestBody value) {
		this.requestBody = value;
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("requestBody");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.standard);
		}
	}

	@Override
	public OpenApi32RequestBody createRequestBody() {
		OpenApi32RequestBodyImpl node = new OpenApi32RequestBodyImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public OpenApiResponses getResponses() {
		return responses;
	}

	@Override
	public void setResponses(OpenApiResponses value) {
		this.responses = value;
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("responses");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.standard);
		}
	}

	@Override
	public OpenApi32Responses createResponses() {
		OpenApi32ResponsesImpl node = new OpenApi32ResponsesImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public OpenApi32Callback createCallback() {
		OpenApi32CallbackImpl node = new OpenApi32CallbackImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public Map<String, OpenApi3xCallback> getCallbacks() {
		return callbacks;
	}

	@Override
	public void addCallback(String name, OpenApi3xCallback value) {
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
	public void insertCallback(String name, OpenApi3xCallback value, int atIndex) {
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
	public Boolean isDeprecated() {
		return deprecated;
	}

	@Override
	public void setDeprecated(Boolean value) {
		this.deprecated = value;
	}

	@Override
	public OpenApi32SecurityRequirement createSecurityRequirement() {
		OpenApi32SecurityRequirementImpl node = new OpenApi32SecurityRequirementImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public List<SecurityRequirement> getSecurity() {
		return security;
	}

	@Override
	public void addSecurity(SecurityRequirement value) {
		if (this.security == null) {
			this.security = new ArrayList<>();
		}
		this.security.add(value);
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("security");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.array);
		}
	}

	@Override
	public void clearSecurity() {
		if (this.security != null) {
			this.security.clear();
		}
	}

	@Override
	public void removeSecurity(SecurityRequirement value) {
		if (this.security != null) {
			this.security.remove(value);
		}
	}

	@Override
	public void insertSecurity(SecurityRequirement value, int atIndex) {
		if (this.security == null) {
			this.security = new ArrayList<>();
			this.security.add(value);
		} else {
			this.security = DataModelUtil.insertListEntry(this.security, value, atIndex);
		}
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("security");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.array);
		}
	}

	@Override
	public OpenApi32Server createServer() {
		OpenApi32ServerImpl node = new OpenApi32ServerImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public List<Server> getServers() {
		return servers;
	}

	@Override
	public void addServer(Server value) {
		if (this.servers == null) {
			this.servers = new ArrayList<>();
		}
		this.servers.add(value);
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("servers");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.array);
		}
	}

	@Override
	public void clearServers() {
		if (this.servers != null) {
			this.servers.clear();
		}
	}

	@Override
	public void removeServer(Server value) {
		if (this.servers != null) {
			this.servers.remove(value);
		}
	}

	@Override
	public void insertServer(Server value, int atIndex) {
		if (this.servers == null) {
			this.servers = new ArrayList<>();
			this.servers.add(value);
		} else {
			this.servers = DataModelUtil.insertListEntry(this.servers, value, atIndex);
		}
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("servers");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.array);
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
		OpenApi32Visitor viz = (OpenApi32Visitor) visitor;
		viz.visitOperation(this);
	}

	@Override
	public Node emptyClone() {
		return new OpenApi32OperationImpl();
	}
}