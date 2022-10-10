package io.apicurio.umg.pipe.concept;

import io.apicurio.umg.models.concept.EntityModel;
import io.apicurio.umg.models.concept.PropertyModel;
import io.apicurio.umg.models.concept.TraitModel;
import io.apicurio.umg.pipe.AbstractStage;

/**
 * Creates "parent" traits for all entities with child properties.  Only creates the parent trait
 * if there are at least two entities that have that child as a property.  For example, the "Contact"
 * entity is always a child of the "Info" entity, so a "ContactParent" trait is not created as it
 * is not needed.
 *
 * The purpose of the Parent trait is to have a common trait used to manage the parent-child
 * relationship of an entity during visitation.
 */
public class CreateParentTraitsStage extends AbstractStage {

    @Override
    protected void doProcess() {
        getState().getConceptIndex().findEntities("").stream().filter(entity -> entity.isLeaf()).forEach(entity -> {
            entity.getProperties().values().stream().filter(property -> needsParent(entity, property)).forEach(property -> {
                if (!property.getType().isList() && !property.getType().isMap()) {
                    String propertyTypeName = property.getType().getName();
                    String traitName = propertyTypeName + "Parent";
                    TraitModel parentTrait;
                    if (entity.getNamespace().containsTrait(traitName)) {
                        parentTrait = entity.getNamespace().getTraits().get(traitName);
                    } else {
                        parentTrait = TraitModel.builder().namespace(entity.getNamespace()).name(traitName).build();
                        PropertyModel traitProperty = PropertyModel.builder().name(property.getName()).type(property.getType()).build();
                        parentTrait.getProperties().put(property.getName(), traitProperty);
                        entity.getNamespace().getTraits().put(traitName, parentTrait);
                        getState().getConceptIndex().index(parentTrait);
                    }
                    entity.getTraits().add(parentTrait);
                }
            });
        });
    }

    /**
     * Returns true if the given entity/property combination is not unique across the models.  If the
     * combination is unique, then no parent trait is needed.  If it is NOT unique (meaning there
     * are other entities with the same property) then a parent IS needed.
     * @param entity
     * @param property
     */
    private boolean needsParent(EntityModel entity, PropertyModel property) {
        if (!property.getType().isEntity()) {
            return false;
        }
        if (property.getName().equals("*")) {
            return false;
        }
        return getState().getConceptIndex().findEntities("")
                .stream()
                .filter(e -> !e.getName().equals(entity.getName()))
                .filter(e -> e.hasProperty(property.getName()))
                .filter(e -> e.getProperties().get(property.getName()).equals(property))
                .count() > 0;
    }

}
