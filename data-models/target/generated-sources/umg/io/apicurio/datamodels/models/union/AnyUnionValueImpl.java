package io.apicurio.datamodels.models.union;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.models.Schema;

public class AnyUnionValueImpl extends PrimitiveUnionValueImpl<JsonNode> implements AnyUnionValue {

	public AnyUnionValueImpl() {
		super();
	}

	public AnyUnionValueImpl(JsonNode value) {
		super(value);
	}

	@Override
	public boolean isAny() {
		return true;
	}

	@Override
	public JsonNode asAny() {
		return getValue();
	}

	@Override
	public boolean isSchema() {
		return false;
	}

	@Override
	public Schema asSchema() {
		throw new ClassCastException();
	}

}
