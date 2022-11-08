package io.apicurio.umg.pipe.java;

import io.apicurio.umg.pipe.AbstractStage;
import io.apicurio.umg.pipe.java.method.GetterMethod;

public class JavaGetterStage extends AbstractStage {
    @Override
    protected void doProcess() {
        getState().getJavaIndex().getTypes().values().forEach(t -> {
            if (!t.isExternal()) {
                t.getFields().forEach(fieldModel -> {
                    t.getMethods().add(GetterMethod.builder()
                            .target(fieldModel)
                            .build());

                });
            }
        });
    }
}
