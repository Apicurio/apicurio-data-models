package io.apicurio.umg.pipe;

import io.apicurio.umg.models.FieldModel;

public class UnionTypeMapperStage implements Stage {

    private GenState state;

    @Override
    public void process(GenState state) {
        this.state = state;

        state.getIndex().findClasses("").forEach(cm -> {
            cm.getFields().forEach((name, fm) -> {
                if(fm.getType().contains("|")) {
                    fm.setType("object");
                }
            });
        });
    }
}
