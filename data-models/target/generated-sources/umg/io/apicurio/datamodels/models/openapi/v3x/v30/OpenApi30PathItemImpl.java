package io.apicurio.datamodels.models.openapi.v3x.v30;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.NodeImpl;
import io.apicurio.datamodels.models.ParentPropertyType;
import io.apicurio.datamodels.models.Server;
import io.apicurio.datamodels.models.openapi.OpenApiOperation;
import io.apicurio.datamodels.models.openapi.OpenApiParameter;
import io.apicurio.datamodels.models.openapi.v3x.OpenApi3xOperation;
import io.apicurio.datamodels.models.openapi.v3x.v30.visitors.OpenApi30Visitor;
import io.apicurio.datamodels.models.util.DataModelUtil;
import io.apicurio.datamodels.models.visitors.Visitor;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class OpenApi30PathItemImpl extends NodeImpl implements OpenApi30PathItem {

	private String $ref;
	private String summary;
	private String description;
	private OpenApiOperation get;
	private OpenApiOperation put;
	private OpenApiOperation post;
	private OpenApiOperation delete;
	private OpenApiOperation options;
	private OpenApiOperation head;
	private OpenApiOperation patch;
	private OpenApi3xOperation trace;
	private List<Server> servers;
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
	public OpenApi30Operation createOperation() {
		OpenApi30OperationImpl node = new OpenApi30OperationImpl();
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
	public OpenApi3xOperation getTrace() {
		return trace;
	}

	@Override
	public void setTrace(OpenApi3xOperation value) {
		this.trace = value;
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("trace");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.standard);
		}
	}

	@Override
	public OpenApi30Server createServer() {
		OpenApi30ServerImpl node = new OpenApi30ServerImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public List<Server> getServers() {
		return servers;
	}

	@Override
	public void addServer(Server value) {
		if (this.servers == null) {
			this.servers = new ArrayList<>();
		}
		this.servers.add(value);
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("servers");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.array);
		}
	}

	@Override
	public void clearServers() {
		if (this.servers != null) {
			this.servers.clear();
		}
	}

	@Override
	public void removeServer(Server value) {
		if (this.servers != null) {
			this.servers.remove(value);
		}
	}

	@Override
	public void insertServer(Server value, int atIndex) {
		if (this.servers == null) {
			this.servers = new ArrayList<>();
			this.servers.add(value);
		} else {
			this.servers = DataModelUtil.insertListEntry(this.servers, value, atIndex);
		}
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("servers");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.array);
		}
	}

	@Override
	public OpenApi30Parameter createParameter() {
		OpenApi30ParameterImpl node = new OpenApi30ParameterImpl();
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
		OpenApi30Visitor viz = (OpenApi30Visitor) visitor;
		viz.visitPathItem(this);
	}

	@Override
	public Node emptyClone() {
		return new OpenApi30PathItemImpl();
	}
}