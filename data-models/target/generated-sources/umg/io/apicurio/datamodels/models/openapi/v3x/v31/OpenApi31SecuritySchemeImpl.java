package io.apicurio.datamodels.models.openapi.v3x.v31;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.NodeImpl;
import io.apicurio.datamodels.models.ParentPropertyType;
import io.apicurio.datamodels.models.openapi.v3x.OpenApi3xOAuthFlows;
import io.apicurio.datamodels.models.openapi.v3x.v31.visitors.OpenApi31Visitor;
import io.apicurio.datamodels.models.util.DataModelUtil;
import io.apicurio.datamodels.models.visitors.Visitor;
import java.util.LinkedHashMap;
import java.util.Map;

public class OpenApi31SecuritySchemeImpl extends NodeImpl implements OpenApi31SecurityScheme {

	private String $ref;
	private String summary;
	private String type;
	private String description;
	private String name;
	private String in;
	private String scheme;
	private String bearerFormat;
	private OpenApi3xOAuthFlows flows;
	private String openIdConnectUrl;
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
	public OpenApi3xOAuthFlows getFlows() {
		return flows;
	}

	@Override
	public void setFlows(OpenApi3xOAuthFlows value) {
		this.flows = value;
		if (value != null) {
			((NodeImpl) value)._setParentPropertyName("flows");
			((NodeImpl) value)._setParentPropertyType(ParentPropertyType.standard);
		}
	}

	@Override
	public OpenApi31OAuthFlows createOAuthFlows() {
		OpenApi31OAuthFlowsImpl node = new OpenApi31OAuthFlowsImpl();
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
		OpenApi31Visitor viz = (OpenApi31Visitor) visitor;
		viz.visitSecurityScheme(this);
	}

	@Override
	public Node emptyClone() {
		return new OpenApi31SecuritySchemeImpl();
	}
}