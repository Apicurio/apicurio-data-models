package io.apicurio.datamodels.models.openapi.v3x.v30;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.NodeImpl;
import io.apicurio.datamodels.models.ParentPropertyType;
import io.apicurio.datamodels.models.openapi.OpenApiMediaType;
import io.apicurio.datamodels.models.openapi.v3x.v30.visitors.OpenApi30Visitor;
import io.apicurio.datamodels.models.util.DataModelUtil;
import io.apicurio.datamodels.models.visitors.Visitor;
import java.util.LinkedHashMap;
import java.util.Map;

public class OpenApi30RequestBodyImpl extends NodeImpl implements OpenApi30RequestBody {

	private String $ref;
	private String description;
	private Map<String, OpenApiMediaType> content;
	private Boolean required;
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
	public OpenApi30MediaType createMediaType() {
		OpenApi30MediaTypeImpl node = new OpenApi30MediaTypeImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public Map<String, OpenApiMediaType> getContent() {
		return content;
	}

	@Override
	public void addContent(String name, OpenApiMediaType value) {
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
	public void insertContent(String name, OpenApiMediaType value, int atIndex) {
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
	public Boolean isRequired() {
		return required;
	}

	@Override
	public void setRequired(Boolean value) {
		this.required = value;
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
		viz.visitRequestBody(this);
	}

	@Override
	public Node emptyClone() {
		return new OpenApi30RequestBodyImpl();
	}
}