package io.apicurio.datamodels.models.openapi.v3x.v32;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.NodeImpl;
import io.apicurio.datamodels.models.openapi.v3x.v32.visitors.OpenApi32Visitor;
import io.apicurio.datamodels.models.util.DataModelUtil;
import io.apicurio.datamodels.models.visitors.Visitor;
import java.util.LinkedHashMap;
import java.util.Map;

public class OpenApi32XMLImpl extends NodeImpl implements OpenApi32XML {

	private String name;
	private String namespace;
	private String prefix;
	private Boolean attribute;
	private Boolean wrapped;
	private String nodeType;
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
	public String getNamespace() {
		return namespace;
	}

	@Override
	public void setNamespace(String value) {
		this.namespace = value;
	}

	@Override
	public String getPrefix() {
		return prefix;
	}

	@Override
	public void setPrefix(String value) {
		this.prefix = value;
	}

	@Override
	public Boolean isAttribute() {
		return attribute;
	}

	@Override
	public void setAttribute(Boolean value) {
		this.attribute = value;
	}

	@Override
	public Boolean isWrapped() {
		return wrapped;
	}

	@Override
	public void setWrapped(Boolean value) {
		this.wrapped = value;
	}

	@Override
	public String getNodeType() {
		return nodeType;
	}

	@Override
	public void setNodeType(String value) {
		this.nodeType = value;
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
		viz.visitXML(this);
	}

	@Override
	public Node emptyClone() {
		return new OpenApi32XMLImpl();
	}
}