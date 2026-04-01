package io.apicurio.datamodels.models.openapi.v2x.v20;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.NodeImpl;
import io.apicurio.datamodels.models.ParentPropertyType;
import io.apicurio.datamodels.models.openapi.OpenApiOperation;
import io.apicurio.datamodels.models.openapi.OpenApiParameter;
import io.apicurio.datamodels.models.openapi.v2x.v20.visitors.OpenApi20Visitor;
import io.apicurio.datamodels.models.util.DataModelUtil;
import io.apicurio.datamodels.models.visitors.Visitor;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class OpenApi20PathItemImpl extends NodeImpl implements OpenApi20PathItem {

	private String $ref;
	private OpenApiOperation get;
	private OpenApiOperation put;
	private OpenApiOperation post;
	private OpenApiOperation delete;
	private OpenApiOperation options;
	private OpenApiOperation head;
	private OpenApiOperation patch;
	private List<OpenApiParameter> parameters;
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
	public OpenApiOperation getGet() {
		return get;
	}

	@Override
	public void setGet(OpenApiOperation value) {
		this.get = value;
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("get");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.standard);
		}
	}

	@Override
	public OpenApi20Operation createOperation() {
		OpenApi20OperationImpl node = new OpenApi20OperationImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public OpenApiOperation getPut() {
		return put;
	}

	@Override
	public void setPut(OpenApiOperation value) {
		this.put = value;
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("put");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.standard);
		}
	}

	@Override
	public OpenApiOperation getPost() {
		return post;
	}

	@Override
	public void setPost(OpenApiOperation value) {
		this.post = value;
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("post");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.standard);
		}
	}

	@Override
	public OpenApiOperation getDelete() {
		return delete;
	}

	@Override
	public void setDelete(OpenApiOperation value) {
		this.delete = value;
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("delete");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.standard);
		}
	}

	@Override
	public OpenApiOperation getOptions() {
		return options;
	}

	@Override
	public void setOptions(OpenApiOperation value) {
		this.options = value;
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("options");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.standard);
		}
	}

	@Override
	public OpenApiOperation getHead() {
		return head;
	}

	@Override
	public void setHead(OpenApiOperation value) {
		this.head = value;
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("head");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.standard);
		}
	}

	@Override
	public OpenApiOperation getPatch() {
		return patch;
	}

	@Override
	public void setPatch(OpenApiOperation value) {
		this.patch = value;
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("patch");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.standard);
		}
	}

	@Override
	public OpenApi20Parameter createParameter() {
		OpenApi20ParameterImpl node = new OpenApi20ParameterImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public List<OpenApiParameter> getParameters() {
		return parameters;
	}

	@Override
	public void addParameter(OpenApiParameter value) {
		if (this.parameters == null) {
			this.parameters = new ArrayList<>();
		}
		this.parameters.add(value);
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("parameters");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.array);
		}
	}

	@Override
	public void clearParameters() {
		if (this.parameters != null) {
			this.parameters.clear();
		}
	}

	@Override
	public void removeParameter(OpenApiParameter value) {
		if (this.parameters != null) {
			this.parameters.remove(value);
		}
	}

	@Override
	public void insertParameter(OpenApiParameter value, int atIndex) {
		if (this.parameters == null) {
			this.parameters = new ArrayList<>();
			this.parameters.add(value);
		} else {
			this.parameters = DataModelUtil.insertListEntry(this.parameters, value, atIndex);
		}
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("parameters");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.array);
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
		OpenApi20Visitor viz = (OpenApi20Visitor) visitor;
		viz.visitPathItem(this);
	}

	@Override
	public Node emptyClone() {
		return new OpenApi20PathItemImpl();
	}
}