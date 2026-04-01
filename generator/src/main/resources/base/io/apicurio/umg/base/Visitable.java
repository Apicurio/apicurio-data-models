package io.apicurio.umg.base;

import io.apicurio.umg.base.visitors.Visitor;

public interface Visitable {

    public void accept(Visitor visitor);

}
