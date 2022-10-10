package io.apicurio.umg.pipe.java;

import io.apicurio.umg.models.java.JavaClassModel;
import io.apicurio.umg.models.java.JavaInterfaceModel;
import io.apicurio.umg.pipe.AbstractStage;

public class JavaSuperTypesStage extends AbstractStage {
    @Override
    protected void doProcess() {
        getState().getJavaIndex().getTypes().values().forEach(t -> {
            if (!t.isExternal()) {
                if (t instanceof JavaInterfaceModel) {
                    var _interface = (JavaInterfaceModel) t;
                    _interface.getParents().forEach(p -> {
                        _interface.getInterfaceSource().addInterface(p.getInterfaceSource());
                    });

                } else {
                    var _class = (JavaClassModel) t;
                    if (_class.getParent() != null) {
                        _class.getClassSource().setSuperType(_class.getParent().getClassSource());
                    }
                }
            }
        });
    }
}
