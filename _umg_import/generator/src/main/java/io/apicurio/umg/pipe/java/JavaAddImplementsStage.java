package io.apicurio.umg.pipe.java;

import io.apicurio.umg.models.java.JavaClassModel;
import io.apicurio.umg.pipe.AbstractStage;

public class JavaAddImplementsStage extends AbstractStage {
    @Override
    protected void doProcess() {
        getState().getJavaIndex().getTypes().values().forEach(t -> {
            if (!t.isExternal()) {
                if (t instanceof JavaClassModel) {
                    var _class = (JavaClassModel) t;
                    _class.get_implements().forEach(im -> {
                        // TODO assert it's an interface
                        _class.getClassSource().addInterface(im.getInterfaceSource());
                    });
                }
            }
        });
    }
}
