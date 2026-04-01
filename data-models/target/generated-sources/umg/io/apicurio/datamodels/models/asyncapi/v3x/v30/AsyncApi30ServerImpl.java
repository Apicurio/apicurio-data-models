package io.apicurio.datamodels.models.asyncapi.v3x.v30;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.NodeImpl;
import io.apicurio.datamodels.models.ParentPropertyType;
import io.apicurio.datamodels.models.ServerVariable;
import io.apicurio.datamodels.models.asyncapi.AsyncApiServerBindings;
import io.apicurio.datamodels.models.asyncapi.v3x.AsyncApi3xExternalDocumentation;
import io.apicurio.datamodels.models.asyncapi.v3x.AsyncApi3xSecurityScheme;
import io.apicurio.datamodels.models.asyncapi.v3x.AsyncApi3xTag;
import io.apicurio.datamodels.models.asyncapi.v3x.v30.visitors.AsyncApi30Visitor;
import io.apicurio.datamodels.models.util.DataModelUtil;
import io.apicurio.datamodels.models.visitors.Visitor;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AsyncApi30ServerImpl extends NodeImpl implements AsyncApi30Server {

	private String $ref;
	private String host;
	private String protocol;
	private String protocolVersion;
	private String pathname;
	private String description;
	private String title;
	private String summary;
	private Map<String, ServerVariable> variables;
	private List<AsyncApi3xSecurityScheme> security;
	private List<AsyncApi3xTag> tags;
	private AsyncApi3xExternalDocumentation externalDocs;
	private AsyncApiServerBindings bindings;
	private Map<String, JsonNode> extensions;

	@Override
	public String get$ref() {
		return $ref;
	}

	@Override
	public void set$ref(String value) {
		this.$ref = value;
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
	public String getProtocol() {
		return protocol;
	}

	@Override
	public void setProtocol(String value) {
		this.protocol = value;
	}

	@Override
	public String getProtocolVersion() {
		return protocolVersion;
	}

	@Override
	public void setProtocolVersion(String value) {
		this.protocolVersion = value;
	}

	@Override
	public String getPathname() {
		return pathname;
	}

	@Override
	public void setPathname(String value) {
		this.pathname = value;
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
	public String getTitle() {
		return title;
	}

	@Override
	public void setTitle(String value) {
		this.title = value;
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
	public AsyncApi30ServerVariable createServerVariable() {
		AsyncApi30ServerVariableImpl node = new AsyncApi30ServerVariableImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public Map<String, ServerVariable> getVariables() {
		return variables;
	}

	@Override
	public void addVariable(String name, ServerVariable value) {
		if (this.variables == null) {
			this.variables = new LinkedHashMap<>();
		}
		this.variables.put(name, value);
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("variables");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.map);
			((NodeImpl) value)._setMapPropertyName(name);
		}
	}

	@Override
	public void clearVariables() {
		if (this.variables != null) {
			this.variables.clear();
		}
	}

	@Override
	public void removeVariable(String name) {
		if (this.variables != null) {
			this.variables.remove(name);
		}
	}

	@Override
	public void insertVariable(String name, ServerVariable value, int atIndex) {
		if (this.variables == null) {
			this.variables = new LinkedHashMap<>();
			this.variables.put(name, value);
		} else {
			this.variables = DataModelUtil.insertMapEntry(this.variables, name, value, atIndex);
		}
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("variables");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.map);
			((NodeImpl) value)._setMapPropertyName(name);
		}
	}

	@Override
	public AsyncApi30SecurityScheme createSecurityScheme() {
		AsyncApi30SecuritySchemeImpl node = new AsyncApi30SecuritySchemeImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public List<AsyncApi3xSecurityScheme> getSecurity() {
		return security;
	}

	@Override
	public void addSecurity(AsyncApi3xSecurityScheme value) {
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
	public void removeSecurity(AsyncApi3xSecurityScheme value) {
		if (this.security != null) {
			this.security.remove(value);
		}
	}

	@Override
	public void insertSecurity(AsyncApi3xSecurityScheme value, int atIndex) {
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
	public AsyncApi30Tag createTag() {
		AsyncApi30TagImpl node = new AsyncApi30TagImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public List<AsyncApi3xTag> getTags() {
		return tags;
	}

	@Override
	public void addTag(AsyncApi3xTag value) {
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
	public void removeTag(AsyncApi3xTag value) {
		if (this.tags != null) {
			this.tags.remove(value);
		}
	}

	@Override
	public void insertTag(AsyncApi3xTag value, int atIndex) {
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
	public AsyncApi3xExternalDocumentation getExternalDocs() {
		return externalDocs;
	}

	@Override
	public void setExternalDocs(AsyncApi3xExternalDocumentation value) {
		this.externalDocs = value;
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("externalDocs");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.standard);
		}
	}

	@Override
	public AsyncApi30ExternalDocumentation createExternalDocumentation() {
		AsyncApi30ExternalDocumentationImpl node = new AsyncApi30ExternalDocumentationImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public AsyncApiServerBindings getBindings() {
		return bindings;
	}

	@Override
	public void setBindings(AsyncApiServerBindings value) {
		this.bindings = value;
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("bindings");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.standard);
		}
	}

	@Override
	public AsyncApi30ServerBindings createServerBindings() {
		AsyncApi30ServerBindingsImpl node = new AsyncApi30ServerBindingsImpl();
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
		AsyncApi30Visitor viz = (AsyncApi30Visitor) visitor;
		viz.visitServer(this);
	}

	@Override
	public Node emptyClone() {
		return new AsyncApi30ServerImpl();
	}
}