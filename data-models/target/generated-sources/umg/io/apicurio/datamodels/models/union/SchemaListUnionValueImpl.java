package io.apicurio.datamodels.models.union;

import io.apicurio.datamodels.models.Schema;
import java.util.List;

public class SchemaListUnionValueImpl extends EntityListUnionValueImpl<Schema> implements SchemaListUnionValue {

	public SchemaListUnionValueImpl() {
		super();
	}

	public SchemaListUnionValueImpl(List<Schema> value) {
		super(value);
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
	public boolean isSchemaList() {
		return true;
	}

	@Override
	public List<Schema> asSchemaList() {
		return getValue();
	}
}