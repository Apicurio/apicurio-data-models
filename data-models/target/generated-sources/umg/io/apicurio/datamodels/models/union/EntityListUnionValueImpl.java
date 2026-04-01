package io.apicurio.datamodels.models.union;

import io.apicurio.datamodels.models.Node;
import java.util.List;

public class EntityListUnionValueImpl<T extends Node> extends ListUnionValueImpl<T> implements EntityListUnionValue<T> {

	public EntityListUnionValueImpl() {
		super();
	}

	public EntityListUnionValueImpl(List<T> value) {
		super(value);
	}

	@Override
	public boolean isEntityList() {
		return true;
	}

}
