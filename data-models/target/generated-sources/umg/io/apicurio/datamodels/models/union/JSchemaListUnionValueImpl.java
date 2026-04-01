package io.apicurio.datamodels.models.union;

import io.apicurio.datamodels.models.jsonschema.JsonSchemaJSchema;
import java.util.List;

public class JSchemaListUnionValueImpl extends EntityListUnionValueImpl<JsonSchemaJSchema>
		implements
			JSchemaListUnionValue {

	public JSchemaListUnionValueImpl() {
		super();
	}

	public JSchemaListUnionValueImpl(List<JsonSchemaJSchema> value) {
		super(value);
	}

	@Override
	public boolean isBoolean() {
		return false;
	}

	@Override
	public Boolean asBoolean() {
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
		return true;
	}

	@Override
	public List<JsonSchemaJSchema> asJSchemaList() {
		return getValue();
	}
}