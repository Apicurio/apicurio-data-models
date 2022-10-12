package io.apicurio.umg.pipe.java;

import io.apicurio.umg.logging.Logger;
import io.apicurio.umg.models.java.JavaClassModel;
import io.apicurio.umg.models.java.JavaInterfaceModel;
import io.apicurio.umg.pipe.AbstractStage;
import org.apache.commons.lang3.StringUtils;

import java.util.HashSet;

public class TransformToInterfaces extends AbstractStage {
    @Override
    protected void doProcess() {
        var skip = new HashSet<String>();
        // For all entities, if they are a leaf entity, create an impl class
        getState().getJavaIndex().getAllTypesWithCopy().forEach(t -> {
            if (!skip.contains(t.fullyQualifiedName())) {

                if (t instanceof JavaClassModel) {
                    var _class = (JavaClassModel) t;
                    var entity = getState().getConceptIndex().lookupEntity(_class.fullyQualifiedName());
                    if (!_class.isExternal()) {
                        if (entity.isLeaf()) {
                            Logger.debug("Processing leaf class %s", t.fullyQualifiedName());
                            // Remove the original from the index
                            getState().getJavaIndex().removeType(_class);

                            // Create an impl version
                            var prefix = entity.getSpec().getPrefix();
                            var implClass = (JavaClassModel) getState().getJavaIndex().lookupAndIndexType(() -> {
                                return JavaClassModel.builder()
                                        ._package(_class.get_package())
                                        .name(/*prefix +*/ StringUtils.capitalize(_class.getName()) + "Impl")
                                        .build();
                            });
                            skip.add(implClass.fullyQualifiedName());
                            implClass.getFields().putAll(_class.getFields());

                            // Create an interface for the impl
                            var _interface = (JavaInterfaceModel) getState().getJavaIndex().lookupAndIndexType(() -> {
                                return JavaInterfaceModel.builder()
                                        ._package(_class.get_package())
                                        .name(_class.getName())
                                        .build();
                            });
                            skip.add(_interface.fullyQualifiedName());
                            _interface.getFields().putAll(_class.getFields());
                            implClass.get_implements().add(_interface);

                            // Process parents
                            JavaInterfaceModel childInterface = _interface;
                            var parent = entity.getParent();
                            while (parent != null) {
                                var grandParent = parent.getParent();
                                Logger.debug("Parent of %s is %s", parent.fullyQualifiedName(), grandParent == null ? "null" : grandParent.fullyQualifiedName());

                                var parentType = getState().getJavaIndex().getTypes().get(parent.fullyQualifiedName());
                                // If the parent type is an interface, assume we've already processed it from a different child

                                JavaInterfaceModel parentInterface;
                                if (parentType instanceof JavaClassModel) {
                                    var parentClass = (JavaClassModel) parentType;

                                    // Remove the parent class from the index
                                    getState().getJavaIndex().removeType(parentClass);

                                    // Create an interface for the parent
                                    parentInterface = (JavaInterfaceModel) getState().getJavaIndex().lookupAndIndexType(() -> {
                                        return JavaInterfaceModel.builder()
                                                ._package(parentClass.get_package())
                                                .name(parentClass.getName())
                                                .build();
                                    });
                                    parentInterface.getFields().putAll(parentClass.getFields());
                                    skip.add(parentClass.fullyQualifiedName());
                                } else {
                                    parentInterface = (JavaInterfaceModel) parentType;
                                }
                                // Add fields from the parent to the impl class
                                implClass.getFields().putAll(parentType.getFields());
                                // Also add the fields to the leaf
                                _class.getFields().putAll(parentType.getFields());
                                // Make sure the interface extends the child
                                childInterface.getParents().add(parentInterface);

                                childInterface = parentInterface;
                                parent = grandParent;
                                // skips
                                skip.add(parentInterface.fullyQualifiedName());
                            }

                        } else {
                            Logger.debug("Skipping non-leaf class %s", t.fullyQualifiedName());
                        }
                    } else {
                        Logger.debug("Skipping external class %s", t.fullyQualifiedName());
                    }
                } else {
                    Logger.debug("Skipping interface %s", t.fullyQualifiedName());
                }
            } else {
                Logger.debug("Skipping created %s", t.fullyQualifiedName());
            }
        });
    }
}
