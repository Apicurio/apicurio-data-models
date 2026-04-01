package io.apicurio.datamodels.models.openapi.v3x.v30;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.NodeImpl;
import io.apicurio.datamodels.models.ParentPropertyType;
import io.apicurio.datamodels.models.openapi.OpenApiHeader;
import io.apicurio.datamodels.models.openapi.v3x.v30.visitors.OpenApi30Visitor;
import io.apicurio.datamodels.models.util.DataModelUtil;
import io.apicurio.datamodels.models.visitors.Visitor;
import java.util.LinkedHashMap;
import java.util.Map;

public class OpenApi30EncodingImpl extends NodeImpl implements OpenApi30Encoding {

	private String contentType;
	private Map<String, OpenApiHeader> headers;
	private String style;
	private Boolean explode;
	private Boolean allowReserved;
	private Map<String, JsonNode> extensions;

	@Override
	public String getContentType() {
		return contentType;
	}

	@Override
	public void setContentType(String value) {
		this.contentType = value;
	}

	@Override
	public OpenApi30Header createHeader() {
		OpenApi30HeaderImpl node = new OpenApi30HeaderImpl();
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
		viz.visitEncoding(this);
	}

	@Override
	public Node emptyClone() {
		return new OpenApi30EncodingImpl();
	}
}