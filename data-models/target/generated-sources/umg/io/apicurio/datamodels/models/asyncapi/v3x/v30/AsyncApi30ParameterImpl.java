package io.apicurio.datamodels.models.asyncapi.v3x.v30;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.NodeImpl;
import io.apicurio.datamodels.models.asyncapi.v3x.v30.visitors.AsyncApi30Visitor;
import io.apicurio.datamodels.models.util.DataModelUtil;
import io.apicurio.datamodels.models.visitors.Visitor;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AsyncApi30ParameterImpl extends NodeImpl implements AsyncApi30Parameter {

	private String $ref;
	private List<String> _enum;
	private String _default;
	private String description;
	private List<String> examples;
	private String location;
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
	public List<String> getEnum() {
		return _enum;
	}

	@Override
	public void setEnum(List<String> value) {
		this._enum = value;
	}

	@Override
	public String getDefault() {
		return _default;
	}

	@Override
	public void setDefault(String value) {
		this._default = value;
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
	public List<String> getExamples() {
		return examples;
	}

	@Override
	public void setExamples(List<String> value) {
		this.examples = value;
	}

	@Override
	public String getLocation() {
		return location;
	}

	@Override
	public void setLocation(String value) {
		this.location = value;
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
		viz.visitParameter(this);
	}

	@Override
	public Node emptyClone() {
		return new AsyncApi30ParameterImpl();
	}
}