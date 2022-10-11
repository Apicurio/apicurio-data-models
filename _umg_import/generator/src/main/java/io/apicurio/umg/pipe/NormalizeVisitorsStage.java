package io.apicurio.umg.pipe;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import io.apicurio.umg.models.VisitorModel;
import io.apicurio.umg.models.concept.EntityModel;

public class NormalizeVisitorsStage extends AbstractStage {

    @Override
    protected void doProcess() {
        // Normalize the visitors
        List<VisitorModel> branchVisitors = getState().getConceptIndex().findVisitors("").stream().filter(model -> model.hasChildren()).collect(Collectors.toList());
        int changesMade;
        do {
            changesMade = 0;
            for (VisitorModel branchVisitor : branchVisitors) {
                // Get all direct children of this parent entity.
                Collection<VisitorModel> childVisitors = branchVisitor.getChildren();

                // Get a collection of all entities for all the children
                Set<EntityModel> allEntities = new HashSet<>();
                childVisitors.forEach(child -> allEntities.addAll(child.getEntities()));

                // Filter the full list of entities - only keep the entities that exist in *all* children.
                List<EntityModel> entitiesToPullup = allEntities.stream()
                        .filter(entity -> existsInAllChildren(entity, childVisitors))
                        .collect(Collectors.toList());

                // Now pull up each of the entities in the above list
                entitiesToPullup.forEach(entity -> {
                    branchVisitor.addEntity(entity);
                    childVisitors.forEach(childVisitor -> childVisitor.removeEntity(entity.getName()));
                });

                // Did we find any entities to pull up?  If yes, increment "changes made".  We're going to keep
                // going through our visitor hierarchy until we've pulled up all the entities we can.
                changesMade += entitiesToPullup.size();
            }
        } while (changesMade > 0);
    }

    /**
     * Returns true if the given entity exists in *all* of the given visitors.
     * @param entity
     * @param childVisitors
     */
    private boolean existsInAllChildren(EntityModel entity, Collection<VisitorModel> childVisitors) {
        return childVisitors.stream().map(visitor -> visitor.containsEntity(entity.getName())).reduce(true, (sub, element) -> sub && element);
    }

}
