package io.apicurio.umg.pipe.java;

import io.apicurio.umg.logging.Logger;
import io.apicurio.umg.pipe.GenState;
import io.apicurio.umg.pipe.Stage;
import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.jboss.forge.roaster.model.source.JavaInterfaceSource;

public class JavaAddImplementsStage implements Stage {

    private GenState state;

    @Override
    public void process(GenState state) {
        this.state = state;

        state.getIndex().findClasses("").forEach(model -> {
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
