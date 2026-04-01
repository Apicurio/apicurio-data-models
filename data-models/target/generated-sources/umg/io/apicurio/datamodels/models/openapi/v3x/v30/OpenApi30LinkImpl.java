package io.apicurio.datamodels.models.openapi.v3x.v30;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.NodeImpl;
import io.apicurio.datamodels.models.ParentPropertyType;
import io.apicurio.datamodels.models.Server;
import io.apicurio.datamodels.models.openapi.v3x.v30.visitors.OpenApi30Visitor;
import io.apicurio.datamodels.models.util.DataModelUtil;
import io.apicurio.datamodels.models.visitors.Visitor;
import java.util.LinkedHashMap;
import java.util.Map;

public class OpenApi30LinkImpl extends NodeImpl implements OpenApi30Link {

	private String $ref;
	private String operationRef;
	private String operationId;
	private Map<String, JsonNode> parameters;
	private JsonNode requestBody;
	private String description;
	private Server server;
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
	public String getOperationRef() {
		return operationRef;
	}

	@Override
	public void setOperationRef(String value) {
		this.operationRef = value;
	}

	@Override
	public String getOperationId() {
		return operationId;
	}

	@Override
	public void setOperationId(String value) {
		this.operationId = value;
	}

	@Override
	public Map<String, JsonNode> getParameters() {
		return parameters;
	}

	@Override
	public void setParameters(Map<String, JsonNode> value) {
		this.parameters = value;
	}

	@Override
	public JsonNode getRequestBody() {
		return requestBody;
	}

	@Override
	public void setRequestBody(JsonNode value) {
		this.requestBody = value;
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
	public Server getServer() {
		return server;
	}

	@Override
	public void setServer(Server value) {
		this.server = value;
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("server");
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
		viz.visitLink(this);
	}

	@Override
	public Node emptyClone() {
		return new OpenApi30LinkImpl();
	}
}