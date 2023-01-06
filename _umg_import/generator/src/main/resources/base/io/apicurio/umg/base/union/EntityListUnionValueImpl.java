package io.apicurio.umg.base.union;

import io.apicurio.umg.base.Node;

public class EntityListUnionValueImpl<T extends Node> extends ListUnionValueImpl<T> implements EntityListUnionValue<T> {

    @Override
    public boolean isEntityList() {
        return true;
    }

}
