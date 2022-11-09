package io.apicurio.umg.pipe.java;

import java.util.ArrayDeque;
import java.util.HashSet;

import org.apache.commons.lang3.StringUtils;

import io.apicurio.umg.logging.Logger;
import io.apicurio.umg.models.java.JavaClassModel;
import io.apicurio.umg.models.java.JavaInterfaceModel;
import io.apicurio.umg.pipe.AbstractStage;

public class TransformToInterfaces extends AbstractStage {
    @Override
    protected void doProcess() {
        var skip = new HashSet<String>();
        // For all entities, if they are a leaf entity, create an impl class
        getState().getJavaIndex().getAllJavaEntitiesWithCopy().forEach(je -> {
            if (!skip.contains(je.fullyQualifiedName())) {

                je.ifClass(originalClass -> {
                    var originalEntity = originalClass.getEntityModel();
                    if (!originalClass.isExternal()) {
                        if (originalEntity.isLeaf()) {

                            Logger.debug("Processing leaf class %s", je.fullyQualifiedName());
                            // Remove the original from the index
                            getState().getJavaIndex().removeType(originalClass);

                            // Create an interface for the impl
                            var leafInterface = (JavaInterfaceModel) getState().getJavaIndex().lookupAndIndexType(() -> {
                                return JavaInterfaceModel.builder()
                                        ._package(originalClass.get_package())
                                        .name(originalClass.getName())
                                        .build();
                            });
                            leafInterface.setEntityModel(originalEntity);
                            leafInterface.get_extends().addAll(originalClass.get_implements());
                            // Skip fields that are in parent interfaces:
                            var fieldsToAdd = new HashSet<>(originalClass.getFields());
                            var queue = new ArrayDeque<JavaInterfaceModel>(); // TODO when/if we do abstract classes, add these here?
                            queue.addAll(leafInterface.get_extends());
                            var item = queue.poll();
                            while (item != null) {
                                fieldsToAdd.removeAll(item.getFields());
                                queue.addAll(item.get_extends());
                                item = queue.poll();
                            }
                            leafInterface.getFields().addAll(fieldsToAdd);

                            // Create an impl version
                            var leafClass = (JavaClassModel) getState().getJavaIndex().lookupAndIndexType(() -> {
                                String nodeImplFQCN = getState().getConfig().getRootNamespace() + ".NodeImpl";
                                JavaClassModel nodeImplClassModel = getState().getJavaIndex().lookupClass(nodeImplFQCN);
                                if (nodeImplClassModel == null) {
                                    Logger.warn("[TransformToInterfaces] NodeImpl class not found!");
                                }
                                return JavaClassModel.builder()
                                        ._package(originalClass.get_package())
                                        .name(StringUtils.capitalize(originalClass.getName()) + "Impl")
                                        ._extends(nodeImplClassModel)
                                        .build();
                            });
                            leafClass.setEntityModel(originalEntity);
                            leafClass.getFields().addAll(originalClass.getFields());
                            // Note: the Impl classes should always extend `NodeImpl`
                            //leafClass.set_extends(originalClass.get_extends());
                            // The things below already come from the leaf interface
                            //leafClass.get_implements().addAll(originalClass.get_implements());
                            leafClass.get_implements().add(leafInterface);

                            skip.add(leafClass.fullyQualifiedName());
                            skip.add(leafInterface.fullyQualifiedName());

                            // TODO Tranform properties to use interface instead of the original type
                            // This should still work now because of the same name

                            // Process parents
                            JavaInterfaceModel childInterface = leafInterface;
                            var parentEntity = originalEntity.getParent();
                            while (parentEntity != null) {
                                var grandParentEntity = parentEntity.getParent();
                                Logger.debug("Parent of %s is %s", childInterface.fullyQualifiedName(), parentEntity.fullyQualifiedName());

                                var parentJavaEntity = getState().getJavaIndex().getTypes().get(parentEntity.fullyQualifiedName());

                                JavaInterfaceModel parentInterface;
                                if (parentJavaEntity instanceof JavaClassModel) {
                                    var parentClass = (JavaClassModel) parentJavaEntity;

                                    // Remove the parent class from the index
                                    getState().getJavaIndex().removeType(parentClass);
                                    skip.add(parentClass.fullyQualifiedName());

                                    // Create an interface for the parent
                                    parentInterface = (JavaInterfaceModel) getState().getJavaIndex().lookupAndIndexType(() -> {
                                        return JavaInterfaceModel.builder()
                                                ._package(parentClass.get_package())
                                                .name(parentClass.getName())
                                                .build();
                                    });
                                    parentInterface.getFields().addAll(parentClass.getFields());
                                    parentInterface.get_extends().addAll(parentClass.get_implements());

                                } else {
                                    // If the parent type is an interface, assume we've already processed it from a different child
                                    parentInterface = (JavaInterfaceModel) parentJavaEntity;
                                }
                                parentInterface.setEntityModel(parentEntity);
                                // "DENORMALIZE" - Pull fields from the parent to the impl class
                                // so concrete impls can be created
                                leafClass.getFields().addAll(parentInterface.getFields());
                                // Make sure the interface extends the child
                                childInterface.get_extends().add(parentInterface);

                                // NEXT cycle
                                childInterface = parentInterface;
                                parentEntity = grandParentEntity;
                                // skips
                                //skip.add(parentInterface.fullyQualifiedName());
                            }

                        } else {
                            Logger.debug("Skipping non-leaf class %s", je.fullyQualifiedName());
                        }
                    } else {
                        Logger.debug("Skipping external class %s", je.fullyQualifiedName());
                    }
                });
                je.ifInterface(_ignored -> {
                    Logger.debug("Skipping interface %s", je.fullyQualifiedName());
                });
            } else {
                Logger.debug("Skipping already processed %s", je.fullyQualifiedName());
            }
        });
    }
}
