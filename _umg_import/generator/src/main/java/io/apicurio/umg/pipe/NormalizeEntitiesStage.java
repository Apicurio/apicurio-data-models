package io.apicurio.umg.pipe;

import java.util.Collection;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;

import io.apicurio.umg.models.EntityModel;
import io.apicurio.umg.models.NamespaceModel;

public class NormalizeEntitiesStage extends AbstractStage {

    @Override
    protected void doProcess() {
        // Process every entity model we've created thus far
        Queue<EntityModel> modelsToProcess = new ConcurrentLinkedQueue<>();
        modelsToProcess.addAll(getState().getModelIndex().findEntities(""));
        Set<String> modelsProcessed = new HashSet<>();

        // Keep working until we've processed every model (including any new models we
        // might create during processing).
        while (!modelsToProcess.isEmpty()) {
            EntityModel classModel = modelsToProcess.remove();
            if (modelsProcessed.contains(classModel.fullyQualifiedName())) {
                continue;
            }

            // Check if we need to create a parent entity for this model in any parent scope
            NamespaceModel ancestorNamespaceModel = classModel.getNamespace().getParent();
            while (ancestorNamespaceModel != null) {
                if (needsParentEntity(ancestorNamespaceModel, classModel.getName())) {
                    EntityModel ancestorEntity = EntityModel.builder()
                            .name(classModel.getName())
                            .parent(classModel.getParent())
                            .namespace(ancestorNamespaceModel)
                            .build();
                    ancestorNamespaceModel.getEntities().put(ancestorEntity.getName(), ancestorEntity);
                    modelsToProcess.add(ancestorEntity);
                    getState().getModelIndex().index(ancestorEntity);

                    Collection<EntityModel> childEntities = getState().findChildEntitiesFor(ancestorEntity);
                    // Make the new parent entity the actual parent of each child entity
                    childEntities.forEach(childEntity -> {
                        childEntity.setParent(ancestorEntity);
                        // Skip processing this model if its turn comes up in the queue.
                        modelsProcessed.add(childEntity.fullyQualifiedName());
                    });
                    // break out of loop - no need to search further up the hierarchy
                    ancestorNamespaceModel = null;
                } else {
                    ancestorNamespaceModel = ancestorNamespaceModel.getParent();
                }
            }
        }
    }

    /**
     * A entity needs a parent entity if there are multiple entities with the same name in the
     * namespace hierarchy.
     *
     * @param namespaceModel
     * @param entityName
     */
    private boolean needsParentEntity(NamespaceModel namespaceModel, String entityName) {
        int count = 0;
        for (NamespaceModel childNamespace : namespaceModel.getChildren().values()) {
            if (childNamespace.containsEntity(entityName)) {
                count++;
            }
        }
        return count > 1;
    }

}
