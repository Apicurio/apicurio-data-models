package io.apicurio.datamodels.models.openapi.v2x.v20;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.ModelType;
import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.NodeImpl;
import io.apicurio.datamodels.models.ParentPropertyType;
import io.apicurio.datamodels.models.RootNodeImpl;
import io.apicurio.datamodels.models.SecurityRequirement;
import io.apicurio.datamodels.models.openapi.OpenApiExternalDocumentation;
import io.apicurio.datamodels.models.openapi.OpenApiInfo;
import io.apicurio.datamodels.models.openapi.OpenApiPaths;
import io.apicurio.datamodels.models.openapi.OpenApiTag;
import io.apicurio.datamodels.models.openapi.v2x.OpenApi2xDefinitions;
import io.apicurio.datamodels.models.openapi.v2x.OpenApi2xParameterDefinitions;
import io.apicurio.datamodels.models.openapi.v2x.OpenApi2xResponseDefinitions;
import io.apicurio.datamodels.models.openapi.v2x.OpenApi2xSecurityDefinitions;
import io.apicurio.datamodels.models.openapi.v2x.v20.visitors.OpenApi20Visitor;
import io.apicurio.datamodels.models.util.DataModelUtil;
import io.apicurio.datamodels.models.visitors.Visitor;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class OpenApi20DocumentImpl extends RootNodeImpl implements OpenApi20Document {

	private String swagger;
	private OpenApiInfo info;
	private String host;
	private String basePath;
	private List<String> schemes;
	private List<String> consumes;
	private List<String> produces;
	private OpenApiPaths paths;
	private OpenApi2xDefinitions definitions;
	private OpenApi2xParameterDefinitions parameters;
	private OpenApi2xResponseDefinitions responses;
	private OpenApi2xSecurityDefinitions securityDefinitions;
	private List<SecurityRequirement> security;
	private List<OpenApiTag> tags;
	private OpenApiExternalDocumentation externalDocs;
	private Map<String, JsonNode> extensions;

	public OpenApi20DocumentImpl() {
		super(ModelType.OPENAPI20);
	}

	@Override
	public String getSwagger() {
		return swagger;
	}

	@Override
	public void setSwagger(String value) {
		this.swagger = value;
	}

	@Override
	public OpenApiInfo getInfo() {
		return info;
	}

	@Override
	public void setInfo(OpenApiInfo value) {
		this.info = value;
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("info");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.standard);
		}
	}

	@Override
	public OpenApi20Info createInfo() {
		OpenApi20InfoImpl node = new OpenApi20InfoImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public String getHost() {
		return host;
	}

	@Override
	public void setHost(String value) {
		this.host = value;
	}

	@Override
	public String getBasePath() {
		return basePath;
	}

	@Override
	public void setBasePath(String value) {
		this.basePath = value;
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
	public OpenApiPaths getPaths() {
		return paths;
	}

	@Override
	public void setPaths(OpenApiPaths value) {
		this.paths = value;
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("paths");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.standard);
		}
	}

	@Override
	public OpenApi20Paths createPaths() {
		OpenApi20PathsImpl node = new OpenApi20PathsImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public OpenApi2xDefinitions getDefinitions() {
		return definitions;
	}

	@Override
	public void setDefinitions(OpenApi2xDefinitions value) {
		this.definitions = value;
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("definitions");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.standard);
		}
	}

	@Override
	public OpenApi20Definitions createDefinitions() {
		OpenApi20DefinitionsImpl node = new OpenApi20DefinitionsImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public OpenApi2xParameterDefinitions getParameters() {
		return parameters;
	}

	@Override
	public void setParameters(OpenApi2xParameterDefinitions value) {
		this.parameters = value;
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("parameters");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.standard);
		}
	}

	@Override
	public OpenApi20ParameterDefinitions createParameterDefinitions() {
		OpenApi20ParameterDefinitionsImpl node = new OpenApi20ParameterDefinitionsImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public OpenApi2xResponseDefinitions getResponses() {
		return responses;
	}

	@Override
	public void setResponses(OpenApi2xResponseDefinitions value) {
		this.responses = value;
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("responses");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.standard);
		}
	}

	@Override
	public OpenApi20ResponseDefinitions createResponseDefinitions() {
		OpenApi20ResponseDefinitionsImpl node = new OpenApi20ResponseDefinitionsImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public OpenApi2xSecurityDefinitions getSecurityDefinitions() {
		return securityDefinitions;
	}

	@Override
	public void setSecurityDefinitions(OpenApi2xSecurityDefinitions value) {
		this.securityDefinitions = value;
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("securityDefinitions");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.standard);
		}
	}

	@Override
	public OpenApi20SecurityDefinitions createSecurityDefinitions() {
		OpenApi20SecurityDefinitionsImpl node = new OpenApi20SecurityDefinitionsImpl();
		node.setParent(this);
		return node;
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
	public OpenApi20Tag createTag() {
		OpenApi20TagImpl node = new OpenApi20TagImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public List<OpenApiTag> getTags() {
		return tags;
	}

	@Override
	public void addTag(OpenApiTag value) {
		if (this.tags == null) {
			this.tags = new ArrayList<>();
		}
		this.tags.add(value);
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("tags");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.array);
		}
	}

	@Override
	public void clearTags() {
		if (this.tags != null) {
			this.tags.clear();
		}
	}

	@Override
	public void removeTag(OpenApiTag value) {
		if (this.tags != null) {
			this.tags.remove(value);
		}
	}

	@Override
	public void insertTag(OpenApiTag value, int atIndex) {
		if (this.tags == null) {
			this.tags = new ArrayList<>();
			this.tags.add(value);
		} else {
			this.tags = DataModelUtil.insertListEntry(this.tags, value, atIndex);
		}
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("tags");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.array);
		}
	}

	@Override
	public OpenApiExternalDocumentation getExternalDocs() {
		return externalDocs;
	}

	@Override
	public void setExternalDocs(OpenApiExternalDocumentation value) {
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
		viz.visitDocument(this);
	}

	@Override
	public Node emptyClone() {
		return new OpenApi20DocumentImpl();
	}
}