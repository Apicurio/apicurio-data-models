package io.apicurio.datamodels.models.asyncapi.v2x.v20;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.NodeImpl;
import io.apicurio.datamodels.models.ParentPropertyType;
import io.apicurio.datamodels.models.asyncapi.v2x.AsyncApi2xSchema;
import io.apicurio.datamodels.models.asyncapi.v2x.v20.visitors.AsyncApi20Visitor;
import io.apicurio.datamodels.models.util.DataModelUtil;
import io.apicurio.datamodels.models.visitors.Visitor;
import java.util.LinkedHashMap;
import java.util.Map;

public class AsyncApi20ParameterImpl extends NodeImpl implements AsyncApi20Parameter {

	private String $ref;
	private String description;
	private AsyncApi2xSchema schema;
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
	public String getDescription() {
		return description;
	}

	@Override
	public void setDescription(String value) {
		this.description = value;
	}

	@Override
	public AsyncApi2xSchema getSchema() {
		return schema;
	}

	@Override
	public void setSchema(AsyncApi2xSchema value) {
		this.schema = value;
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("schema");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.standard);
		}
	}

	@Override
	public AsyncApi20Schema createSchema() {
		AsyncApi20SchemaImpl node = new AsyncApi20SchemaImpl();
		node.setParent(this);
		return node;
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
		AsyncApi20Visitor viz = (AsyncApi20Visitor) visitor;
		viz.visitParameter(this);
	}

	@Override
	public Node emptyClone() {
		return new AsyncApi20ParameterImpl();
	}
}