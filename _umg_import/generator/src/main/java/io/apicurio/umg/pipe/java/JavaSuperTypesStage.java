package io.apicurio.umg.pipe.java;

import io.apicurio.umg.pipe.AbstractStage;

public class JavaSuperTypesStage extends AbstractStage {
    @Override
    protected void doProcess() {
        getState().getIndex().findClasses("").forEach(model -> {
            if (!model.isCore()) {
                if (model.is_interface()) {

                    model.getParents().forEach(p -> {
                        model.getInterfaceSource().addInterface(p.getInterfaceSource());
                    });

                } else {

                    if (model.getParents().size() > 1) {
                        throw new IllegalStateException("TODO");
                    }
                    model.getParents().forEach(p -> {
                        model.getClassSource().setSuperType(p.getClassSource());
                    });
                }
            }
        });
    }
}
