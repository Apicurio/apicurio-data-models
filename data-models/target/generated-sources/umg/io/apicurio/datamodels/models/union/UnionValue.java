package io.apicurio.datamodels.models.union;

public interface UnionValue<T> {

	T getValue();

	void setValue(T value);

	boolean isList();

	boolean isMap();

}
