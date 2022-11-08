package io.apicurio.umg.pipe.java;

import io.apicurio.umg.logging.Logger;
import io.apicurio.umg.pipe.AbstractStage;
import io.apicurio.umg.pipe.java.method.FactoryMethod;

import java.util.ArrayDeque;
import java.util.stream.Collectors;

public class CreateFactoryMethodsStage extends AbstractStage {
    @Override
    protected void doProcess() {

        getState().getJavaIndex().getAllJavaEntitiesWithCopy().forEach(je -> {
            je.ifClass(_class -> {
                // Create the implementations for leaf entities
                if (_class.getEntityModel().isLeaf()) {

                    _class.getFields().forEach(f -> {
                        var fieldEntity = f.getEntityType();
                        final FactoryMethod[] method = new FactoryMethod[1];
                        if (fieldEntity != null) {
                            // Add a "create" method
                            // TODO Also decide based on flavor?

                            method[0] = FactoryMethod.builder()
                                    .state(getState())
                                    .javaEntity(_class)
                                    .targetField(f)
                                    .build();
                            _class.getMethods().add(method[0]);
                        }

                        if (method[0] != null) {
                            // Process interfaces
                            var process = new ArrayDeque<>(_class.get_implements());
                            while (!process.isEmpty()) {
                                var _interface = process.removeFirst();
                                // but only if the entity is present at the given level
                                if (_interface.get_package().getTypes().containsKey(f.getEntityType().getName())) {
                                    // Add only direct parent interface
                                    process.addAll(_interface.get_extends().stream()
                                            .filter(i -> i.getFields().contains(f))
                                            .collect(Collectors.toList())
                                    );
                                    _interface.getMethods().add(method[0]);
                                } else {
                                    Logger.debug("Return entity '%s' for factory method '%s' not found in package '%s'",
                                            f.getEntityType().getName(), method[0].getName(), _interface.get_package().getName());
                                }
                            }

                            // Process extends, TODO do we need this?
                            var extendingClass = _class.get_extends();
                            while (extendingClass != null) {
                                if (extendingClass.get_package().getTypes().containsKey(f.getEntityType().getName())) {
                                    extendingClass.getMethods().add(method[0]);
                                } else {
                                    Logger.debug("Return entity '%s' for factory method '%s' not found in package '%s'",
                                            f.getEntityType().getName(), method[0].getName(), extendingClass.get_package().getName());
                                }
                                extendingClass = extendingClass.get_extends();
                            }
                        }
                    });
                }
            });
        });
    }
}
