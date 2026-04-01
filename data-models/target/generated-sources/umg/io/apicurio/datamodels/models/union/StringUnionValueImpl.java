package io.apicurio.datamodels.models.union;

import java.util.List;

public class StringUnionValueImpl extends PrimitiveUnionValueImpl<String> implements StringUnionValue {

	public StringUnionValueImpl() {
		super();
	}

	public StringUnionValueImpl(String value) {
		super(value);
	}

	@Override
	public boolean isString() {
		return true;
	}

	@Override
	public String asString() {
		return getValue();
	}

	@Override
	public boolean isStringList() {
		return false;
	}

	@Override
	public List<String> asStringList() {
		throw new ClassCastException();
	}

}
