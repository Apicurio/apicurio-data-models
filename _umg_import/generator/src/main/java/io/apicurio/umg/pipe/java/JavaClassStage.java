package io.apicurio.umg.pipe.java;

import io.apicurio.umg.models.java.JavaClassModel;
import io.apicurio.umg.models.java.JavaInterfaceModel;
import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.jboss.forge.roaster.model.source.JavaInterfaceSource;

import io.apicurio.umg.logging.Logger;
import io.apicurio.umg.pipe.AbstractStage;

public class JavaClassStage extends AbstractStage {
    @Override
    protected void doProcess() {
        getState().getJavaIndex().getTypes().values().forEach(t -> {
            if (!t.isExternal()) {

                Logger.info("Generating model for type '%s'", t.getName());

                if (t instanceof JavaInterfaceModel) {
                    var _interface = (JavaInterfaceModel) t;

                    JavaInterfaceSource modelClass = Roaster.create(JavaInterfaceSource.class)
                            .setPackage(_interface.get_package().getName())
                            .setName(_interface.getName())
                            .setPublic();

                    _interface.setInterfaceSource(modelClass);

                } else {
                    var _class = (JavaClassModel) t;

                    JavaClassSource modelClass = Roaster.create(JavaClassSource.class)
                            .setPackage(_class.get_package().getName())
                            .setName(_class.getName())
                            .setAbstract(_class.is_abstract())
                            .setPublic();

                    _class.setClassSource(modelClass);
                }
            }
        });
    }
}
