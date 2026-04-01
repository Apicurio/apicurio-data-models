package io.apicurio.datamodels.models.openrpc.v1x.v14;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.NodeImpl;
import io.apicurio.datamodels.models.openrpc.v1x.v14.visitors.OpenRpc14Visitor;
import io.apicurio.datamodels.models.visitors.Visitor;

public class OpenRpc14ErrorImpl extends NodeImpl implements OpenRpc14Error {

	private String $ref;
	private Integer code;
	private String message;
	private JsonNode data;

	@Override
	public String get$ref() {
		return $ref;
	}

	@Override
	public void set$ref(String value) {
		this.$ref = value;
	}

	@Override
	public Integer getCode() {
		return code;
	}

	@Override
	public void setCode(Integer value) {
		this.code = value;
	}

	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public void setMessage(String value) {
		this.message = value;
	}

	@Override
	public JsonNode getData() {
		return data;
	}

	@Override
	public void setData(JsonNode value) {
		this.data = value;
	}

	@Override
	public void accept(Visitor visitor) {
		OpenRpc14Visitor viz = (OpenRpc14Visitor) visitor;
		viz.visitError(this);
	}

	@Override
	public Node emptyClone() {
		return new OpenRpc14ErrorImpl();
	}
}