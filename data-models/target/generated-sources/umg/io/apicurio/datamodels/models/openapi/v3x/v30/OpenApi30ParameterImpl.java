package io.apicurio.datamodels.models.openapi.v3x.v30;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.NodeImpl;
import io.apicurio.datamodels.models.ParentPropertyType;
import io.apicurio.datamodels.models.openapi.OpenApiExample;
import io.apicurio.datamodels.models.openapi.OpenApiSchema;
import io.apicurio.datamodels.models.openapi.v3x.OpenApi3xMediaType;
import io.apicurio.datamodels.models.openapi.v3x.v30.visitors.OpenApi30Visitor;
import io.apicurio.datamodels.models.util.DataModelUtil;
import io.apicurio.datamodels.models.visitors.Visitor;
import java.util.LinkedHashMap;
import java.util.Map;

public class OpenApi30ParameterImpl extends NodeImpl implements OpenApi30Parameter {

	private String $ref;
	private String name;
	private String in;
	private String description;
	private Boolean required;
	private Boolean deprecated;
	private Boolean allowEmptyValue;
	private String style;
	private Boolean explode;
	private Boolean allowReserved;
	private OpenApiSchema schema;
	private JsonNode example;
	private Map<String, OpenApiExample> examples;
	private Map<String, OpenApi3xMediaType> content;
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
	public String getIn() {
		return in;
	}

	@Override
	public void setIn(String value) {
		this.in = value;
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
	public Boolean isRequired() {
		return required;
	}

	@Override
	public void setRequired(Boolean value) {
		this.required = value;
	}

	@Override
	public Boolean isDeprecated() {
		return deprecated;
	}

	@Override
	public void setDeprecated(Boolean value) {
		this.deprecated = value;
	}

	@Override
	public Boolean isAllowEmptyValue() {
		return allowEmptyValue;
	}

	@Override
	public void setAllowEmptyValue(Boolean value) {
		this.allowEmptyValue = value;
	}

	@Override
	public String getStyle() {
		return style;
	}

	@Override
	public void setStyle(String value) {
		this.style = value;
	}

	@Override
	public Boolean isExplode() {
		return explode;
	}

	@Override
	public void setExplode(Boolean value) {
		this.explode = value;
	}

	@Override
	public Boolean isAllowReserved() {
		return allowReserved;
	}

	@Override
	public void setAllowReserved(Boolean value) {
		this.allowReserved = value;
	}

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
	public OpenApi30MediaType createMediaType() {
		OpenApi30MediaTypeImpl node = new OpenApi30MediaTypeImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public Map<String, OpenApi3xMediaType> getContent() {
		return content;
	}

	@Override
	public void addContent(String name, OpenApi3xMediaType value) {
		if (this.content == null) {
			this.content = new LinkedHashMap<>();
		}
		this.content.put(name, value);
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("content");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.map);
			((NodeImpl) value)._setMapPropertyName(name);
		}
	}

	@Override
	public void clearContent() {
		if (this.content != null) {
			this.content.clear();
		}
	}

	@Override
	public void removeContent(String name) {
		if (this.content != null) {
			this.content.remove(name);
		}
	}

	@Override
	public void insertContent(String name, OpenApi3xMediaType value, int atIndex) {
		if (this.content == null) {
			this.content = new LinkedHashMap<>();
			this.content.put(name, value);
		} else {
			this.content = DataModelUtil.insertMapEntry(this.content, name, value, atIndex);
		}
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("content");
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
		viz.visitParameter(this);
	}

	@Override
	public Node emptyClone() {
		return new OpenApi30ParameterImpl();
	}
}