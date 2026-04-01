package io.apicurio.datamodels.models.asyncapi.v2x.v22;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.NodeImpl;
import io.apicurio.datamodels.models.asyncapi.v2x.v22.visitors.AsyncApi22Visitor;
import io.apicurio.datamodels.models.util.DataModelUtil;
import io.apicurio.datamodels.models.visitors.Visitor;
import java.util.LinkedHashMap;
import java.util.Map;

public class AsyncApi22OAuthFlowImpl extends NodeImpl implements AsyncApi22OAuthFlow {

	private String authorizationUrl;
	private String tokenUrl;
	private String refreshUrl;
	private Map<String, String> scopes;
	private Map<String, JsonNode> extensions;

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
	public String getRefreshUrl() {
		return refreshUrl;
	}

	@Override
	public void setRefreshUrl(String value) {
		this.refreshUrl = value;
	}

	@Override
	public Map<String, String> getScopes() {
		return scopes;
	}

	@Override
	public void setScopes(Map<String, String> value) {
		this.scopes = value;
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
		AsyncApi22Visitor viz = (AsyncApi22Visitor) visitor;
		viz.visitOAuthFlow(this);
	}

	@Override
	public Node emptyClone() {
		return new AsyncApi22OAuthFlowImpl();
	}
}