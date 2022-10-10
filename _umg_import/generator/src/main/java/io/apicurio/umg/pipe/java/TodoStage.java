package io.apicurio.umg.pipe.java;

import io.apicurio.umg.pipe.AbstractStage;

public class TodoStage extends AbstractStage {
    @Override
    protected void doProcess() {
        getState().getJavaIndex().getTypes().values().forEach(t -> {
            t.getFields().values().removeIf(f -> "/x-.+/".equals(f.getName()));
        });
    }
}
