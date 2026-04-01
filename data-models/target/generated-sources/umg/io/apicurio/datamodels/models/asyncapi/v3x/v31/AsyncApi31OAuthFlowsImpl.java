package io.apicurio.datamodels.models.asyncapi.v3x.v31;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.NodeImpl;
import io.apicurio.datamodels.models.OAuthFlow;
import io.apicurio.datamodels.models.ParentPropertyType;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.visitors.AsyncApi31Visitor;
import io.apicurio.datamodels.models.util.DataModelUtil;
import io.apicurio.datamodels.models.visitors.Visitor;
import java.util.LinkedHashMap;
import java.util.Map;

public class AsyncApi31OAuthFlowsImpl extends NodeImpl implements AsyncApi31OAuthFlows {

	private OAuthFlow implicit;
	private OAuthFlow password;
	private OAuthFlow clientCredentials;
	private OAuthFlow authorizationCode;
	private Map<String, JsonNode> extensions;

	@Override
	public OAuthFlow getImplicit() {
		return implicit;
	}

	@Override
	public void setImplicit(OAuthFlow value) {
		this.implicit = value;
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("implicit");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.standard);
		}
	}

	@Override
	public AsyncApi31OAuthFlow createOAuthFlow() {
		AsyncApi31OAuthFlowImpl node = new AsyncApi31OAuthFlowImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public OAuthFlow getPassword() {
		return password;
	}

	@Override
	public void setPassword(OAuthFlow value) {
		this.password = value;
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("password");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.standard);
		}
	}

	@Override
	public OAuthFlow getClientCredentials() {
		return clientCredentials;
	}

	@Override
	public void setClientCredentials(OAuthFlow value) {
		this.clientCredentials = value;
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("clientCredentials");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.standard);
		}
	}

	@Override
	public OAuthFlow getAuthorizationCode() {
		return authorizationCode;
	}

	@Override
	public void setAuthorizationCode(OAuthFlow value) {
		this.authorizationCode = value;
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("authorizationCode");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.standard);
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
		AsyncApi31Visitor viz = (AsyncApi31Visitor) visitor;
		viz.visitOAuthFlows(this);
	}

	@Override
	public Node emptyClone() {
		return new AsyncApi31OAuthFlowsImpl();
	}
}