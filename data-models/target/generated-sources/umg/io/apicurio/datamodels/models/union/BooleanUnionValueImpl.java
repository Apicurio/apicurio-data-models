package io.apicurio.datamodels.models.union;

import io.apicurio.datamodels.models.Schema;
import io.apicurio.datamodels.models.jsonschema.JsonSchemaJSchema;
import java.util.List;

public class BooleanUnionValueImpl extends PrimitiveUnionValueImpl<Boolean> implements BooleanUnionValue {

	public BooleanUnionValueImpl(Boolean value) {
		super(value);
	}

	@Override
	public boolean isBoolean() {
		return true;
	}

	@Override
	public Boolean asBoolean() {
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

	@Override
	public boolean isJSchema() {
		return false;
	}

	@Override
	public JsonSchemaJSchema asJSchema() {
		throw new ClassCastException();
	}

	@Override
	public boolean isJSchemaList() {
		return false;
	}

	@Override
	public List<JsonSchemaJSchema> asJSchemaList() {
		throw new ClassCastException();
	}

}
