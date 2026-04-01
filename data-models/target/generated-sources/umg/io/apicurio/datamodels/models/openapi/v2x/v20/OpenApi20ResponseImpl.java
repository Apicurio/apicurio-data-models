package io.apicurio.datamodels.models.openapi.v2x.v20;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.NodeImpl;
import io.apicurio.datamodels.models.ParentPropertyType;
import io.apicurio.datamodels.models.openapi.OpenApiHeader;
import io.apicurio.datamodels.models.openapi.v2x.OpenApi2xExample;
import io.apicurio.datamodels.models.openapi.v2x.OpenApi2xSchema;
import io.apicurio.datamodels.models.openapi.v2x.v20.visitors.OpenApi20Visitor;
import io.apicurio.datamodels.models.util.DataModelUtil;
import io.apicurio.datamodels.models.visitors.Visitor;
import java.util.LinkedHashMap;
import java.util.Map;

public class OpenApi20ResponseImpl extends NodeImpl implements OpenApi20Response {

	private String $ref;
	private String description;
	private OpenApi2xSchema schema;
	private Map<String, OpenApiHeader> headers;
	private OpenApi2xExample examples;
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
	public OpenApi2xSchema getSchema() {
		return schema;
	}

	@Override
	public void setSchema(OpenApi2xSchema value) {
		this.schema = value;
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("schema");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.standard);
		}
	}

	@Override
	public OpenApi20Schema createSchema() {
		OpenApi20SchemaImpl node = new OpenApi20SchemaImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public OpenApi20Header createHeader() {
		OpenApi20HeaderImpl node = new OpenApi20HeaderImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public Map<String, OpenApiHeader> getHeaders() {
		return headers;
	}

	@Override
	public void addHeader(String name, OpenApiHeader value) {
		if (this.headers == null) {
			this.headers = new LinkedHashMap<>();
		}
		this.headers.put(name, value);
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("headers");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.map);
			((NodeImpl) value)._setMapPropertyName(name);
		}
	}

	@Override
	public void clearHeaders() {
		if (this.headers != null) {
			this.headers.clear();
		}
	}

	@Override
	public void removeHeader(String name) {
		if (this.headers != null) {
			this.headers.remove(name);
		}
	}

	@Override
	public void insertHeader(String name, OpenApiHeader value, int atIndex) {
		if (this.headers == null) {
			this.headers = new LinkedHashMap<>();
			this.headers.put(name, value);
		} else {
			this.headers = DataModelUtil.insertMapEntry(this.headers, name, value, atIndex);
		}
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("headers");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.map);
			((NodeImpl) value)._setMapPropertyName(name);
		}
	}

	@Override
	public OpenApi2xExample getExamples() {
		return examples;
	}

	@Override
	public void setExamples(OpenApi2xExample value) {
		this.examples = value;
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("examples");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.standard);
		}
	}

	@Override
	public OpenApi20Example createExample() {
		OpenApi20ExampleImpl node = new OpenApi20ExampleImpl();
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
		viz.visitResponse(this);
	}

	@Override
	public Node emptyClone() {
		return new OpenApi20ResponseImpl();
	}
}