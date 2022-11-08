package io.apicurio.umg.pipe.java;

import io.apicurio.umg.pipe.AbstractStage;

public class JavaMethodStage extends AbstractStage {
    @Override
    protected void doProcess() {
        getState().getJavaIndex().getTypes().values().forEach(t -> {
            if (!t.isExternal()) {
                t.ifClass(_class -> {
                    _class.getClassMethods().forEach(m -> {
                        m.apply(_class.getClassSource());
                    });
                });
                t.ifInterface(_interface -> {
                    _interface.getInterfaceMethods().forEach(m -> {
                        m.apply(_interface.getInterfaceSource());
                    });
                });
            }
        });
    }
}
