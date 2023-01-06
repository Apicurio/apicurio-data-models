package io.apicurio.umg.base.union;

import io.apicurio.umg.base.Node;

public class EntityMapUnionValueImpl<T extends Node> extends MapUnionValueImpl<T> implements EntityMapUnionValue<T> {

    @Override
    public boolean isEntityMap() {
        return true;
    }

}
