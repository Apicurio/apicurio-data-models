package io.apicurio.datamodels.models.openapi.v2x.v20;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.NodeImpl;
import io.apicurio.datamodels.models.ParentPropertyType;
import io.apicurio.datamodels.models.openapi.v2x.OpenApi2xScopes;
import io.apicurio.datamodels.models.openapi.v2x.v20.visitors.OpenApi20Visitor;
import io.apicurio.datamodels.models.util.DataModelUtil;
import io.apicurio.datamodels.models.visitors.Visitor;
import java.util.LinkedHashMap;
import java.util.Map;

public class OpenApi20SecuritySchemeImpl extends NodeImpl implements OpenApi20SecurityScheme {

	private String type;
	private String description;
	private String name;
	private String in;
	private String flow;
	private String authorizationUrl;
	private String tokenUrl;
	private OpenApi2xScopes scopes;
	private Map<String, JsonNode> extensions;

	@Override
	public String getType() {
		return type;
	}

	@Override
	public void setType(String value) {
		this.type = value;
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
	public String getFlow() {
		return flow;
	}

	@Override
	public void setFlow(String value) {
		this.flow = value;
	}

	@Override
	public String getAuthorizationUrl() {
		return authorizationUrl;
	}

	@Override
	public void setAuthorizationUrl(String value) {
		this.authorizationUrl = value;
	}

	@Override
	public String getTokenUrl() {
		return tokenUrl;
	}

	@Override
	public void setTokenUrl(String value) {
		this.tokenUrl = value;
	}

	@Override
	public OpenApi2xScopes getScopes() {
		return scopes;
	}

	@Override
	public void setScopes(OpenApi2xScopes value) {
		this.scopes = value;
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("scopes");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.standard);
		}
	}

	@Override
	public OpenApi20Scopes createScopes() {
		OpenApi20ScopesImpl node = new OpenApi20ScopesImpl();
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
		viz.visitSecurityScheme(this);
	}

	@Override
	public Node emptyClone() {
		return new OpenApi20SecuritySchemeImpl();
	}
}