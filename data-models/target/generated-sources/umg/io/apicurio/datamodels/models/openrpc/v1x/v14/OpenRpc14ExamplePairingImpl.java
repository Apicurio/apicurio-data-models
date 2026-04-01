package io.apicurio.datamodels.models.openrpc.v1x.v14;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.NodeImpl;
import io.apicurio.datamodels.models.ParentPropertyType;
import io.apicurio.datamodels.models.openrpc.OpenRpcExample;
import io.apicurio.datamodels.models.openrpc.v1x.v14.visitors.OpenRpc14Visitor;
import io.apicurio.datamodels.models.util.DataModelUtil;
import io.apicurio.datamodels.models.visitors.Visitor;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class OpenRpc14ExamplePairingImpl extends NodeImpl implements OpenRpc14ExamplePairing {

	private String $ref;
	private String name;
	private String description;
	private String summary;
	private List<OpenRpcExample> params;
	private OpenRpcExample result;
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
	public String getSummary() {
		return summary;
	}

	@Override
	public void setSummary(String value) {
		this.summary = value;
	}

	@Override
	public OpenRpc14Example createExample() {
		OpenRpc14ExampleImpl node = new OpenRpc14ExampleImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public List<OpenRpcExample> getParams() {
		return params;
	}

	@Override
	public void addParam(OpenRpcExample value) {
		if (this.params == null) {
			this.params = new ArrayList<>();
		}
		this.params.add(value);
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("params");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.array);
		}
	}

	@Override
	public void clearParams() {
		if (this.params != null) {
			this.params.clear();
		}
	}

	@Override
	public void removeParam(OpenRpcExample value) {
		if (this.params != null) {
			this.params.remove(value);
		}
	}

	@Override
	public void insertParam(OpenRpcExample value, int atIndex) {
		if (this.params == null) {
			this.params = new ArrayList<>();
			this.params.add(value);
		} else {
			this.params = DataModelUtil.insertListEntry(this.params, value, atIndex);
		}
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("params");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.array);
		}
	}

	@Override
	public OpenRpcExample getResult() {
		return result;
	}

	@Override
	public void setResult(OpenRpcExample value) {
		this.result = value;
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("result");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.standard);
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
		OpenRpc14Visitor viz = (OpenRpc14Visitor) visitor;
		viz.visitExamplePairing(this);
	}

	@Override
	public Node emptyClone() {
		return new OpenRpc14ExamplePairingImpl();
	}
}