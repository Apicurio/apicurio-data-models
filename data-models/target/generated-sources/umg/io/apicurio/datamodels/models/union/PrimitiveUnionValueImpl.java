package io.apicurio.datamodels.models.union;

public abstract class PrimitiveUnionValueImpl<T> extends UnionValueImpl<T> implements PrimitiveUnionValue<T> {

	public PrimitiveUnionValueImpl() {
	}

	public PrimitiveUnionValueImpl(T value) {
		super(value);
	}

}
