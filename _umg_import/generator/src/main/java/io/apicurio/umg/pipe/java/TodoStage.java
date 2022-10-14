package io.apicurio.umg.pipe.java;

import io.apicurio.umg.pipe.AbstractStage;

public class TodoStage extends AbstractStage {
    @Override
    protected void doProcess() {
        getState().getJavaIndex().getTypes().values().forEach(t -> {
            var field = t.getFields().stream().filter(f -> "/x-.+/".equals(f.getName())).findAny();
            field.ifPresent(f -> {
                t.getFields().remove(f);
                f.setName("todo_regexp");
                t.getFields().add(f);
            });
            field = t.getFields().stream().filter(f -> "*".equals(f.getName())).findAny();
            field.ifPresent(f -> {
                t.getFields().remove(f);
                f.setName("todo_star");
                t.getFields().add(f);
            });
            // TODO Maybe go back to the map
        });
    }
}
