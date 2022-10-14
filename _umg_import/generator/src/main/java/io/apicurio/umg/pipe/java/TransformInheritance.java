package io.apicurio.umg.pipe.java;

import io.apicurio.umg.models.java.JavaClassModel;
import io.apicurio.umg.models.java.JavaInterfaceModel;
import io.apicurio.umg.pipe.AbstractStage;

public class TransformInheritance extends AbstractStage {
    @Override
    protected void doProcess() {

        getState().getJavaIndex().getAllJavaEntitiesWithCopy().forEach(je -> {
            je.ifClass(_class -> {
                var e = je.getEntityModel();
                // Extends
                if (e.getParent() != null) {
                    var parentClass = (JavaClassModel) getState().getJavaIndex().getTypes().get(e.getParent().fullyQualifiedName());
                    _class.set_extends(parentClass);
                }
                // Implements
                e.getTraits().forEach(t -> {
                    var _interface = (JavaInterfaceModel) getState().getJavaIndex().getTypes().get(t.fullyQualifiedName());
                    _class.get_implements().add(_interface);
                    // Add fields from trait
                    _class.getFields().addAll(_interface.getFields());
                    // Ensure fields from trait parents are also included
                    var parent = t.getParent();
                    while(parent != null) {
                        var parentInterface = (JavaInterfaceModel) getState().getJavaIndex().getTypes().get(parent.fullyQualifiedName());
                        _class.getFields().addAll(parentInterface.getFields());
                        parent = parent.getParent();
                    }
                });
            });

            je.ifInterface(_interface -> {
                // Extends
                var t = je.getTraitModel();
                if (t.getParent() != null) {
                    var parentInterface = (JavaInterfaceModel) getState().getJavaIndex().getTypes().get(t.getParent().fullyQualifiedName());
                    _interface.get_extends().add(parentInterface);
                }
            });
        });
    }
}
