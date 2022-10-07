package io.apicurio.umg.pipe.java;

import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.jboss.forge.roaster.model.source.JavaInterfaceSource;

import io.apicurio.umg.logging.Logger;
import io.apicurio.umg.pipe.AbstractStage;

public class JavaClassStage extends AbstractStage {
    @Override
    protected void doProcess() {
        getState().getClassIndex().findClasses("").forEach(model -> {
            if (!model.isCore()) {

                Logger.info("Generating model for entity '%s'", model.getName());

                if (model.is_interface()) {

                    JavaInterfaceSource modelClass = Roaster.create(JavaInterfaceSource.class)
                            .setPackage(model.getPackage().getName())
                            .setName(model.getName())
                            .setPublic();

                    model.setInterfaceSource(modelClass);

                } else {

                    JavaClassSource modelClass = Roaster.create(JavaClassSource.class)
                            .setPackage(model.getPackage().getName())
                            .setName(model.getName())
                            .setAbstract(model.isAbstract())
                            .setPublic();

                    model.setClassSource(modelClass);
                }
            }
        });
    }
}
