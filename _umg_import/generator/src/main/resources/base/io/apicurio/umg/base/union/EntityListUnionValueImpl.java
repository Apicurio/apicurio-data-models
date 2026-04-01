package io.apicurio.umg.base.union;

import java.util.List;

import io.apicurio.umg.base.Node;

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
