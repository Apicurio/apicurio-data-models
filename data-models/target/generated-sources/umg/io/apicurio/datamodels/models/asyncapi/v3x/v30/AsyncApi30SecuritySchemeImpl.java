package io.apicurio.datamodels.models.asyncapi.v3x.v30;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.NodeImpl;
import io.apicurio.datamodels.models.ParentPropertyType;
import io.apicurio.datamodels.models.asyncapi.AsyncApiOAuthFlows;
import io.apicurio.datamodels.models.asyncapi.v3x.v30.visitors.AsyncApi30Visitor;
import io.apicurio.datamodels.models.util.DataModelUtil;
import io.apicurio.datamodels.models.visitors.Visitor;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AsyncApi30SecuritySchemeImpl extends NodeImpl implements AsyncApi30SecurityScheme {

	private String $ref;
	private String type;
	private String description;
	private String name;
	private String in;
	private String scheme;
	private String bearerFormat;
	private AsyncApiOAuthFlows flows;
	private String openIdConnectUrl;
	private List<String> scopes;
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
	public String getScheme() {
		return scheme;
	}

	@Override
	public void setScheme(String value) {
		this.scheme = value;
	}

	@Override
	public String getBearerFormat() {
		return bearerFormat;
	}

	@Override
	public void setBearerFormat(String value) {
		this.bearerFormat = value;
	}

	@Override
	public AsyncApiOAuthFlows getFlows() {
		return flows;
	}

	@Override
	public void setFlows(AsyncApiOAuthFlows value) {
		this.flows = value;
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("flows");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.standard);
		}
	}

	@Override
	public AsyncApi30OAuthFlows createOAuthFlows() {
		AsyncApi30OAuthFlowsImpl node = new AsyncApi30OAuthFlowsImpl();
		node.setParent(this);
		return node;
	}

	@Override
	public String getOpenIdConnectUrl() {
		return openIdConnectUrl;
	}

	@Override
	public void setOpenIdConnectUrl(String value) {
		this.openIdConnectUrl = value;
	}

	@Override
	public List<String> getScopes() {
		return scopes;
	}

	@Override
	public void setScopes(List<String> value) {
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
		AsyncApi30Visitor viz = (AsyncApi30Visitor) visitor;
		viz.visitSecurityScheme(this);
	}

	@Override
	public Node emptyClone() {
		return new AsyncApi30SecuritySchemeImpl();
	}
}