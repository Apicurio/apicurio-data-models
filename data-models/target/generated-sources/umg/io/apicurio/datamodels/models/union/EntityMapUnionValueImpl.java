package io.apicurio.datamodels.models.union;

import io.apicurio.datamodels.models.Node;
import java.util.Map;

public class EntityMapUnionValueImpl<T extends Node> extends MapUnionValueImpl<T> implements EntityMapUnionValue<T> {

	public EntityMapUnionValueImpl() {
		super();
	}

	public EntityMapUnionValueImpl(Map<String, T> value) {
		super(value);
	}

	@Override
	public boolean isEntityMap() {
		return true;
	}

}
