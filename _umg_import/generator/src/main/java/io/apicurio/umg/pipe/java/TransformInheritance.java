package io.apicurio.umg.pipe.java;

import io.apicurio.umg.models.java.JavaClassModel;
import io.apicurio.umg.models.java.JavaInterfaceModel;
import io.apicurio.umg.pipe.AbstractStage;

public class TransformInheritance extends AbstractStage {
    @Override
    protected void doProcess() {

        getState().getJavaIndex().getAllJavaEntitiesWithCopy().forEach(je -> {
            je.ifClass(_class -> {
                var entity = je.getEntityModel();
                // Extends
                if (entity.getParent() != null) {
                    var parentClass = (JavaClassModel) getState().getJavaIndex().getTypes().get(entity.getParent().fullyQualifiedName());
                    _class.set_extends(parentClass);
                }
                // Implements
                entity.getTraits().forEach(trait -> {
                    var _interface = (JavaInterfaceModel) getState().getJavaIndex().getTypes().get(trait.fullyQualifiedName());
                    _class.get_implements().add(_interface);
                    // Add fields from trait
                    _class.getFields().addAll(_interface.getFields());
                    // Ensure fields from trait parents are also included
                    var parent = trait.getParent();
                    while(parent != null) {
                        var parentInterface = (JavaInterfaceModel) getState().getJavaIndex().getTypes().get(parent.fullyQualifiedName());
                        _class.getFields().addAll(parentInterface.getFields());
                        parent = parent.getParent();
                    }
                });
            });

            je.ifInterface(_interface -> {
                // Extends
                var trait = je.getTraitModel();
                if (trait.getParent() != null) {
                    var parentInterface = (JavaInterfaceModel) getState().getJavaIndex().getTypes().get(trait.getParent().fullyQualifiedName());
                    _interface.get_extends().add(parentInterface);
                }
            });
        });
    }
}
