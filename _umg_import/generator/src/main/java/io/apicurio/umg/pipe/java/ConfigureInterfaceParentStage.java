package io.apicurio.umg.pipe.java;

import org.jboss.forge.roaster.model.source.JavaInterfaceSource;

import io.apicurio.umg.models.concept.EntityModel;
import io.apicurio.umg.models.concept.TraitModel;

/**
 * Configures the parents of all interfaces (entities and traits).  Uses the concept
 * hierarchy to determine the parents.
 *
 * @author eric.wittmann@gmail.com
 */
public class ConfigureInterfaceParentStage extends AbstractJavaStage {

    @Override
    protected void doProcess() {
        getState().getConceptIndex().findEntities("").forEach(entity -> {
            configureEntityInterfaceParent(entity);
        });
        getState().getConceptIndex().findTraits("").forEach(trait -> {
            configureTraitInterfaceParent(trait);
        });
    }

    private void configureEntityInterfaceParent(EntityModel entity) {
        EntityModel parentEntity = entity.getParent();
        JavaInterfaceSource parentJavaEntity = null;
        if (parentEntity != null) {
            parentJavaEntity = getState().getJavaIndex().lookupInterface(getEntityInterfaceFQN(parentEntity));
        } else {
            parentJavaEntity = getState().getJavaIndex().lookupInterface(getNodeEntityInterfaceFQN());
        }

        JavaInterfaceSource javaEntity = getState().getJavaIndex().lookupInterface(getEntityInterfaceFQN(entity));
        javaEntity.addInterface(parentJavaEntity);
    }

    private void configureTraitInterfaceParent(TraitModel trait) {
        TraitModel parentTrait = trait.getParent();
        if (parentTrait != null) {
            JavaInterfaceSource parentJavaTrait = getState().getJavaIndex().lookupInterface(getTraitInterfaceFQN(parentTrait));
            JavaInterfaceSource javaTrait = getState().getJavaIndex().lookupInterface(getTraitInterfaceFQN(trait));
            javaTrait.addInterface(parentJavaTrait);
        }
    }
}