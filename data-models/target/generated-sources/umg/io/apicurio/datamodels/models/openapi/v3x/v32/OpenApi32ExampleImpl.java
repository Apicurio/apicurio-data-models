package io.apicurio.datamodels.models.openapi.v3x.v32;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.NodeImpl;
import io.apicurio.datamodels.models.openapi.v3x.v32.visitors.OpenApi32Visitor;
import io.apicurio.datamodels.models.util.DataModelUtil;
import io.apicurio.datamodels.models.visitors.Visitor;
import java.util.LinkedHashMap;
import java.util.Map;

public class OpenApi32ExampleImpl extends NodeImpl implements OpenApi32Example {

	private String $ref;
	private String summary;
	private String description;
	private JsonNode value;
	private JsonNode dataValue;
	private String serializedValue;
	private String externalValue;
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
	public JsonNode getValue() {
		return value;
	}

	@Override
	public void setValue(JsonNode value) {
		this.value = value;
	}

	@Override
	public JsonNode getDataValue() {
		return dataValue;
	}

	@Override
	public void setDataValue(JsonNode value) {
		this.dataValue = value;
	}

	@Override
	public String getSerializedValue() {
		return serializedValue;
	}

	@Override
	public void setSerializedValue(String value) {
		this.serializedValue = value;
	}

	@Override
	public String getExternalValue() {
		return externalValue;
	}

	@Override
	public void setExternalValue(String value) {
		this.externalValue = value;
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
		viz.visitExample(this);
	}

	@Override
	public Node emptyClone() {
		return new OpenApi32ExampleImpl();
	}
}