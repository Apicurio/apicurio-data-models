package io.apicurio.datamodels.models.openapi.v3x.v32;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.NodeImpl;
import io.apicurio.datamodels.models.ParentPropertyType;
import io.apicurio.datamodels.models.openapi.OpenApiHeader;
import io.apicurio.datamodels.models.openapi.v3x.OpenApi3xLink;
import io.apicurio.datamodels.models.openapi.v3x.OpenApi3xMediaType;
import io.apicurio.datamodels.models.openapi.v3x.v32.visitors.OpenApi32Visitor;
import io.apicurio.datamodels.models.util.DataModelUtil;
import io.apicurio.datamodels.models.visitors.Visitor;
import java.util.LinkedHashMap;
import java.util.Map;

public class OpenApi32ResponseImpl extends NodeImpl implements OpenApi32Response {

	private String $ref;
	private String summary;
	private String description;
	private Map<String, OpenApiHeader> headers;
	private Map<String, OpenApi3xMediaType> content;
	private Map<String, OpenApi3xLink> links;
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
	public OpenApi32Header createHeader() {
		OpenApi32HeaderImpl node = new OpenApi32HeaderImpl();
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
	public OpenApi32MediaType createMediaType() {
		OpenApi32MediaTypeImpl node = new OpenApi32MediaTypeImpl();
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
	public OpenApi32Link createLink() {
		OpenApi32LinkImpl node = new OpenApi32LinkImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public Map<String, OpenApi3xLink> getLinks() {
		return links;
	}

	@Override
	public void addLink(String name, OpenApi3xLink value) {
		if (this.links == null) {
			this.links = new LinkedHashMap<>();
		}
		this.links.put(name, value);
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("links");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.map);
			((NodeImpl) value)._setMapPropertyName(name);
		}
	}

	@Override
	public void clearLinks() {
		if (this.links != null) {
			this.links.clear();
		}
	}

	@Override
	public void removeLink(String name) {
		if (this.links != null) {
			this.links.remove(name);
		}
	}

	@Override
	public void insertLink(String name, OpenApi3xLink value, int atIndex) {
		if (this.links == null) {
			this.links = new LinkedHashMap<>();
			this.links.put(name, value);
		} else {
			this.links = DataModelUtil.insertMapEntry(this.links, name, value, atIndex);
		}
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("links");
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
		viz.visitResponse(this);
	}

	@Override
	public Node emptyClone() {
		return new OpenApi32ResponseImpl();
	}
}