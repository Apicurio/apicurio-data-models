package io.apicurio.datamodels.models.openrpc.v1x.v13;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.NodeImpl;
import io.apicurio.datamodels.models.ParentPropertyType;
import io.apicurio.datamodels.models.ServerVariable;
import io.apicurio.datamodels.models.openrpc.v1x.v13.visitors.OpenRpc13Visitor;
import io.apicurio.datamodels.models.util.DataModelUtil;
import io.apicurio.datamodels.models.visitors.Visitor;
import java.util.LinkedHashMap;
import java.util.Map;

public class OpenRpc13ServerImpl extends NodeImpl implements OpenRpc13Server {

	private String name;
	private String url;
	private String summary;
	private String description;
	private Map<String, ServerVariable> variables;
	private Map<String, JsonNode> extensions;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String value) {
		this.name = value;
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
	public OpenRpc13ServerVariable createServerVariable() {
		OpenRpc13ServerVariableImpl node = new OpenRpc13ServerVariableImpl();
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
		OpenRpc13Visitor viz = (OpenRpc13Visitor) visitor;
		viz.visitServer(this);
	}

	@Override
	public Node emptyClone() {
		return new OpenRpc13ServerImpl();
	}
}