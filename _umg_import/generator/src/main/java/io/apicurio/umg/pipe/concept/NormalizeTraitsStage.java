package io.apicurio.umg.pipe.concept;

import java.util.Collection;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;

import io.apicurio.umg.models.concept.NamespaceModel;
import io.apicurio.umg.models.concept.TraitModel;
import io.apicurio.umg.pipe.AbstractStage;

public class NormalizeTraitsStage extends AbstractStage {

    @Override
    protected void doProcess() {
        // Process every trait model we've created thus far
        Queue<TraitModel> modelsToProcess = new ConcurrentLinkedQueue<>();
        modelsToProcess.addAll(getState().getConceptIndex().findTraits(""));
        Set<String> modelsProcessed = new HashSet<>();

        // Keep working until we've processed every model (including any new models we
        // might create during processing).
        while (!modelsToProcess.isEmpty()) {
            TraitModel classModel = modelsToProcess.remove();
            if (modelsProcessed.contains(classModel.fullyQualifiedName())) {
                continue;
            }

            // Check if we need to create a parent trait for this model in any parent scope
            NamespaceModel ancestorNamespaceModel = classModel.getNamespace().getParent();
            while (ancestorNamespaceModel != null) {
                if (needsParentTrait(ancestorNamespaceModel, classModel.getName())) {
                    TraitModel ancestorTrait = TraitModel.builder()
                            .name(classModel.getName())
                            .parent(classModel.getParent())
                            .namespace(ancestorNamespaceModel)
                            .build();
                    ancestorNamespaceModel.getTraits().put(ancestorTrait.getName(), ancestorTrait);
                    modelsToProcess.add(ancestorTrait);
                    getState().getConceptIndex().index(ancestorTrait);

                    Collection<TraitModel> childTraits = getState().findChildTraitsFor(ancestorTrait);
                    // Make the new parent trait the actual parent of each child trait
                    childTraits.forEach(childTrait -> {
                        childTrait.setParent(ancestorTrait);
                        // Skip processing this model if its turn comes up in the queue.
                        modelsProcessed.add(childTrait.fullyQualifiedName());
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
     * A trait needs a parent trait if there are multiple traits with the same name in the
     * namespace hierarchy.
     *
     * @param namespaceModel
     * @param traitName
     */
    private boolean needsParentTrait(NamespaceModel namespaceModel, String traitName) {
        int count = 0;
        for (NamespaceModel childNamespace : namespaceModel.getChildren().values()) {
            if (childNamespace.containsTrait(traitName)) {
                count++;
            }
        }
        return count > 1;
    }

}
