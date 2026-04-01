package io.apicurio.datamodels.models.openapi.v2x.v20;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.ExternalDocumentation;
import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.NodeImpl;
import io.apicurio.datamodels.models.ParentPropertyType;
import io.apicurio.datamodels.models.SecurityRequirement;
import io.apicurio.datamodels.models.openapi.OpenApiParameter;
import io.apicurio.datamodels.models.openapi.OpenApiResponses;
import io.apicurio.datamodels.models.openapi.v2x.v20.visitors.OpenApi20Visitor;
import io.apicurio.datamodels.models.util.DataModelUtil;
import io.apicurio.datamodels.models.visitors.Visitor;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class OpenApi20OperationImpl extends NodeImpl implements OpenApi20Operation {

	private List<String> tags;
	private String summary;
	private String description;
	private ExternalDocumentation externalDocs;
	private String operationId;
	private List<String> consumes;
	private List<String> produces;
	private List<OpenApiParameter> parameters;
	private OpenApiResponses responses;
	private List<String> schemes;
	private Boolean deprecated;
	private List<SecurityRequirement> security;
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
	public OpenApi20ExternalDocumentation createExternalDocumentation() {
		OpenApi20ExternalDocumentationImpl node = new OpenApi20ExternalDocumentationImpl();
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
	public List<String> getConsumes() {
		return consumes;
	}

	@Override
	public void setConsumes(List<String> value) {
		this.consumes = value;
	}

	@Override
	public List<String> getProduces() {
		return produces;
	}

	@Override
	public void setProduces(List<String> value) {
		this.produces = value;
	}

	@Override
	public OpenApi20Parameter createParameter() {
		OpenApi20ParameterImpl node = new OpenApi20ParameterImpl();
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
	public OpenApi20Responses createResponses() {
		OpenApi20ResponsesImpl node = new OpenApi20ResponsesImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public List<String> getSchemes() {
		return schemes;
	}

	@Override
	public void setSchemes(List<String> value) {
		this.schemes = value;
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
	public OpenApi20SecurityRequirement createSecurityRequirement() {
		OpenApi20SecurityRequirementImpl node = new OpenApi20SecurityRequirementImpl();
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
		OpenApi20Visitor viz = (OpenApi20Visitor) visitor;
		viz.visitOperation(this);
	}

	@Override
	public Node emptyClone() {
		return new OpenApi20OperationImpl();
	}
}