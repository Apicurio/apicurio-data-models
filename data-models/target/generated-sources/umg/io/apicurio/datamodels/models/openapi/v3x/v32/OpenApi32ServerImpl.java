package io.apicurio.datamodels.models.openapi.v3x.v32;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.NodeImpl;
import io.apicurio.datamodels.models.ParentPropertyType;
import io.apicurio.datamodels.models.ServerVariable;
import io.apicurio.datamodels.models.openapi.v3x.v32.visitors.OpenApi32Visitor;
import io.apicurio.datamodels.models.util.DataModelUtil;
import io.apicurio.datamodels.models.visitors.Visitor;
import java.util.LinkedHashMap;
import java.util.Map;

public class OpenApi32ServerImpl extends NodeImpl implements OpenApi32Server {

	private String url;
	private String name;
	private String description;
	private Map<String, ServerVariable> variables;
	private Map<String, JsonNode> extensions;

	@Override
	public String getUrl() {
		return url;
	}

	@Override
	public void setUrl(String value) {
		this.url = value;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String value) {
		this.name = value;
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
	public OpenApi32ServerVariable createServerVariable() {
		OpenApi32ServerVariableImpl node = new OpenApi32ServerVariableImpl();
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
		OpenApi32Visitor viz = (OpenApi32Visitor) visitor;
		viz.visitServer(this);
	}

	@Override
	public Node emptyClone() {
		return new OpenApi32ServerImpl();
	}
}