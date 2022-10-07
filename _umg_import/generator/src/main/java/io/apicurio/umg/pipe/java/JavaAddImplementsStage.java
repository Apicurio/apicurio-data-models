package io.apicurio.umg.pipe.java;

import io.apicurio.umg.pipe.AbstractStage;

public class JavaAddImplementsStage extends AbstractStage {
    @Override
    protected void doProcess() {
        getState().getIndex().findClasses("").forEach(model -> {
            if (!model.isCore()) {
                if(!model.is_interface()) {
                    model.get_implements().forEach(im -> {
                        // TODO assert it's an interface
                        model.getClassSource().addInterface(im.getInterfaceSource());
                    });
                }
            }
        });
    }
}
