package io.apicurio.umg.pipe.concept;

import io.apicurio.umg.logging.Logger;
import io.apicurio.umg.models.concept.EntityModel;
import io.apicurio.umg.models.concept.PropertyModel;
import io.apicurio.umg.models.concept.PropertyType;
import io.apicurio.umg.models.concept.TraitModel;
import io.apicurio.umg.pipe.AbstractStage;

public class CreatePropertyModelsStage extends AbstractStage {

    @Override
    protected void doProcess() {
        Logger.info("-- Creating Property Models --");
        getState().getSpecIndex().getSpecifications().forEach(spec -> {
            // Create property models for traits
            spec.getTraits().forEach(trait -> {
                String fqTraitName = spec.getNamespace() + "." + trait.getName();
                TraitModel traitModel = getState().getConceptIndex().lookupTrait(fqTraitName);
                trait.getProperties().forEach(property -> {
                    PropertyModel propertyModel = PropertyModel.builder().name(property.getName()).type(PropertyType.create(property.getType())).build();
                    Logger.info("Created trait property model: %s/%s", traitModel.fullyQualifiedName(), propertyModel.getName());
                    traitModel.getProperties().put(property.getName(), propertyModel);
                });
            });

            // Create property models for entities
            spec.getEntities().forEach(entity -> {
                String fqEntityName = spec.getNamespace() + "." + entity.getName();
                EntityModel entityModel = getState().getConceptIndex().lookupEntity(fqEntityName);
                entity.getProperties().forEach(property -> {
                    PropertyModel propertyModel = PropertyModel.builder().name(property.getName()).type(PropertyType.create(property.getType())).build();
                    Logger.info("Created entity property model: %s/%s", entityModel.fullyQualifiedName(), propertyModel.getName());
                    entityModel.getProperties().put(property.getName(), propertyModel);
                });
            });

        });
    }
}
