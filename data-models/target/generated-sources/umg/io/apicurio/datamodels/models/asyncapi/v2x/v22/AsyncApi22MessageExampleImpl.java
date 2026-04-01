package io.apicurio.datamodels.models.asyncapi.v2x.v22;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.NodeImpl;
import io.apicurio.datamodels.models.asyncapi.v2x.v22.visitors.AsyncApi22Visitor;
import io.apicurio.datamodels.models.util.DataModelUtil;
import io.apicurio.datamodels.models.visitors.Visitor;
import java.util.LinkedHashMap;
import java.util.Map;

public class AsyncApi22MessageExampleImpl extends NodeImpl implements AsyncApi22MessageExample {

	private Map<String, JsonNode> headers;
	private JsonNode payload;
	private String name;
	private String summary;
	private Map<String, JsonNode> extensions;

	@Override
	public Map<String, JsonNode> getHeaders() {
		return headers;
	}

	@Override
	public void setHeaders(Map<String, JsonNode> value) {
		this.headers = value;
	}

	@Override
	public JsonNode getPayload() {
		return payload;
	}

	@Override
	public void setPayload(JsonNode value) {
		this.payload = value;
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
	public String getSummary() {
		return summary;
	}

	@Override
	public void setSummary(String value) {
		this.summary = value;
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
		viz.visitMessageExample(this);
	}

	@Override
	public Node emptyClone() {
		return new AsyncApi22MessageExampleImpl();
	}
}