package io.apicurio.datamodels.models.asyncapi.v3x.v31;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.NodeImpl;
import io.apicurio.datamodels.models.asyncapi.v3x.v31.visitors.AsyncApi31Visitor;
import io.apicurio.datamodels.models.util.DataModelUtil;
import io.apicurio.datamodels.models.visitors.Visitor;
import java.util.LinkedHashMap;
import java.util.Map;

public class AsyncApi31ContactImpl extends NodeImpl implements AsyncApi31Contact {

	private String name;
	private String url;
	private String email;
	private Map<String, JsonNode> extensions;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String value) {
		this.name = value;
	}

	@Override
	public String getUrl() {
		return url;
	}

	@Override
	public void setUrl(String value) {
		this.url = value;
	}

	@Override
	public String getEmail() {
		return email;
	}

	@Override
	public void setEmail(String value) {
		this.email = value;
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
		viz.visitContact(this);
	}

	@Override
	public Node emptyClone() {
		return new AsyncApi31ContactImpl();
	}
}