package io.apicurio.datamodels.models.asyncapi.v2x.v25;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.NodeImpl;
import io.apicurio.datamodels.models.ParentPropertyType;
import io.apicurio.datamodels.models.SecurityRequirement;
import io.apicurio.datamodels.models.ServerVariable;
import io.apicurio.datamodels.models.asyncapi.AsyncApiServerBindings;
import io.apicurio.datamodels.models.asyncapi.v2x.v25.visitors.AsyncApi25Visitor;
import io.apicurio.datamodels.models.util.DataModelUtil;
import io.apicurio.datamodels.models.visitors.Visitor;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AsyncApi25ServerImpl extends NodeImpl implements AsyncApi25Server {

	private String $ref;
	private String url;
	private String protocol;
	private String protocolVersion;
	private String description;
	private Map<String, ServerVariable> variables;
	private List<SecurityRequirement> security;
	private List<AsyncApi25Tag> tags;
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
	public String getUrl() {
		return url;
	}

	@Override
	public void setUrl(String value) {
		this.url = value;
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
	public String getDescription() {
		return description;
	}

	@Override
	public void setDescription(String value) {
		this.description = value;
	}

	@Override
	public AsyncApi25ServerVariable createServerVariable() {
		AsyncApi25ServerVariableImpl node = new AsyncApi25ServerVariableImpl();
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
	public AsyncApi25SecurityRequirement createSecurityRequirement() {
		AsyncApi25SecurityRequirementImpl node = new AsyncApi25SecurityRequirementImpl();
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
	public AsyncApi25Tag createTag() {
		AsyncApi25TagImpl node = new AsyncApi25TagImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public List<AsyncApi25Tag> getTags() {
		return tags;
	}

	@Override
	public void addTag(AsyncApi25Tag value) {
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
	public void removeTag(AsyncApi25Tag value) {
		if (this.tags != null) {
			this.tags.remove(value);
		}
	}

	@Override
	public void insertTag(AsyncApi25Tag value, int atIndex) {
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
	public AsyncApi25ServerBindings createServerBindings() {
		AsyncApi25ServerBindingsImpl node = new AsyncApi25ServerBindingsImpl();
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
		AsyncApi25Visitor viz = (AsyncApi25Visitor) visitor;
		viz.visitServer(this);
	}

	@Override
	public Node emptyClone() {
		return new AsyncApi25ServerImpl();
	}
}