package io.apicurio.datamodels.models.openapi.v3x.v30;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.NodeImpl;
import io.apicurio.datamodels.models.ParentPropertyType;
import io.apicurio.datamodels.models.openapi.OpenApiEncoding;
import io.apicurio.datamodels.models.openapi.OpenApiExample;
import io.apicurio.datamodels.models.openapi.OpenApiSchema;
import io.apicurio.datamodels.models.openapi.v3x.v30.visitors.OpenApi30Visitor;
import io.apicurio.datamodels.models.util.DataModelUtil;
import io.apicurio.datamodels.models.visitors.Visitor;
import java.util.LinkedHashMap;
import java.util.Map;

public class OpenApi30MediaTypeImpl extends NodeImpl implements OpenApi30MediaType {

	private OpenApiSchema schema;
	private JsonNode example;
	private Map<String, OpenApiExample> examples;
	private Map<String, OpenApiEncoding> encoding;
	private Map<String, JsonNode> extensions;

	@Override
	public OpenApiSchema getSchema() {
		return schema;
	}

	@Override
	public void setSchema(OpenApiSchema value) {
		this.schema = value;
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("schema");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.standard);
		}
	}

	@Override
	public OpenApi30Schema createSchema() {
		OpenApi30SchemaImpl node = new OpenApi30SchemaImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public JsonNode getExample() {
		return example;
	}

	@Override
	public void setExample(JsonNode value) {
		this.example = value;
	}

	@Override
	public OpenApi30Example createExample() {
		OpenApi30ExampleImpl node = new OpenApi30ExampleImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public Map<String, OpenApiExample> getExamples() {
		return examples;
	}

	@Override
	public void addExample(String name, OpenApiExample value) {
		if (this.examples == null) {
			this.examples = new LinkedHashMap<>();
		}
		this.examples.put(name, value);
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("examples");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.map);
			((NodeImpl) value)._setMapPropertyName(name);
		}
	}

	@Override
	public void clearExamples() {
		if (this.examples != null) {
			this.examples.clear();
		}
	}

	@Override
	public void removeExample(String name) {
		if (this.examples != null) {
			this.examples.remove(name);
		}
	}

	@Override
	public void insertExample(String name, OpenApiExample value, int atIndex) {
		if (this.examples == null) {
			this.examples = new LinkedHashMap<>();
			this.examples.put(name, value);
		} else {
			this.examples = DataModelUtil.insertMapEntry(this.examples, name, value, atIndex);
		}
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("examples");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.map);
			((NodeImpl) value)._setMapPropertyName(name);
		}
	}

	@Override
	public OpenApi30Encoding createEncoding() {
		OpenApi30EncodingImpl node = new OpenApi30EncodingImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public Map<String, OpenApiEncoding> getEncoding() {
		return encoding;
	}

	@Override
	public void addEncoding(String name, OpenApiEncoding value) {
		if (this.encoding == null) {
			this.encoding = new LinkedHashMap<>();
		}
		this.encoding.put(name, value);
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("encoding");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.map);
			((NodeImpl) value)._setMapPropertyName(name);
		}
	}

	@Override
	public void clearEncoding() {
		if (this.encoding != null) {
			this.encoding.clear();
		}
	}

	@Override
	public void removeEncoding(String name) {
		if (this.encoding != null) {
			this.encoding.remove(name);
		}
	}

	@Override
	public void insertEncoding(String name, OpenApiEncoding value, int atIndex) {
		if (this.encoding == null) {
			this.encoding = new LinkedHashMap<>();
			this.encoding.put(name, value);
		} else {
			this.encoding = DataModelUtil.insertMapEntry(this.encoding, name, value, atIndex);
		}
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("encoding");
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
		OpenApi30Visitor viz = (OpenApi30Visitor) visitor;
		viz.visitMediaType(this);
	}

	@Override
	public Node emptyClone() {
		return new OpenApi30MediaTypeImpl();
	}
}