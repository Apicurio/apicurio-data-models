package io.apicurio.umg.pipe.concept;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import io.apicurio.umg.models.concept.EntityModel;
import io.apicurio.umg.models.concept.VisitorModel;
import io.apicurio.umg.pipe.AbstractStage;

/**
 * When the visitors were created and then normalized, the entities added to them were clones of the
 * original entities.  We need to declone them here by replacing the clones with the originals (which
 * by now would have been normalized).
 * @author eric.wittmann@gmail.com
 */
public class ResolveVisitorEntityStage extends AbstractStage {

    @Override
    protected void doProcess() {
        Collection<VisitorModel> allVisitors = getState().getConceptIndex().findVisitors("");
        allVisitors.forEach(visitor -> {
            resolveVisitorEntities(visitor);
        });
    }

    private void resolveVisitorEntities(VisitorModel visitor) {
        List<EntityModel> resolvedEntities = visitor.getEntities().stream().map(entity -> resolveEntity(visitor, entity)).collect(Collectors.toUnmodifiableList());
        visitor.getEntities().clear();
        visitor.getEntities().addAll(resolvedEntities);
    }

    private EntityModel resolveEntity(VisitorModel visitor, EntityModel entityClone) {
        EntityModel lookupEntity = getState().getConceptIndex().lookupEntity(visitor.getNamespace(), entityClone.getName());
        if (lookupEntity == null) {
            warn("Failed to resolve visitor entity: " + entityClone.getName() + " from visitor: " + visitor);
        }
        return lookupEntity;
    }

}
