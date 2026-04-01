package io.apicurio.datamodels.models.union;

import java.util.List;

public class StringListUnionValueImpl extends ListUnionValueImpl<String> implements StringListUnionValue {

	public StringListUnionValueImpl() {
		super();
	}

	public StringListUnionValueImpl(List<String> value) {
		super(value);
	}

	@Override
	public boolean isString() {
		return false;
	}

	@Override
	public String asString() {
		throw new ClassCastException();
	}

	@Override
	public boolean isStringList() {
		return true;
	}

	@Override
	public List<String> asStringList() {
		return getValue();
	}

}
