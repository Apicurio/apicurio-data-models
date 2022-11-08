package io.apicurio.umg.pipe.java;

import io.apicurio.umg.pipe.AbstractStage;
import io.apicurio.umg.pipe.java.method.SetterMethod;

public class JavaSetterStage extends AbstractStage {
    @Override
    protected void doProcess() {
        getState().getJavaIndex().getTypes().values().forEach(t -> {
            if (!t.isExternal()) {
                t.getFields().forEach(fieldModel -> {
                    t.getMethods().add(SetterMethod.builder()
                            .target(fieldModel)
                            .build());
                });
            }
        });
    }
}
