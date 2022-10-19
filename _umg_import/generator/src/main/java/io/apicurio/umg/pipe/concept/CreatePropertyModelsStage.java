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
        getState().getSpecIndex().getAllSpecificationVersions().forEach(specVersion -> {
            // Create property models for traits
            specVersion.getTraits().forEach(trait -> {
                String fqTraitName = specVersion.getNamespace() + "." + trait.getName();
                TraitModel traitModel = getState().getConceptIndex().lookupTrait(fqTraitName);
                trait.getProperties().forEach(property -> {
                    PropertyModel propertyModel = PropertyModel.builder()
                            .name(property.getName())
                            .rawType(property.getType())
                            .type(PropertyType.parse(property.getType()))
                            .build();
                    Logger.info("Created trait property model: %s/%s", traitModel.fullyQualifiedName(), propertyModel.getName());
                    traitModel.getProperties().put(property.getName(), propertyModel);
                });
            });

            // Create property models for entities
            specVersion.getEntities().forEach(entity -> {
                String fqEntityName = specVersion.getNamespace() + "." + entity.getName();
                EntityModel entityModel = getState().getConceptIndex().lookupEntity(fqEntityName);
                entity.getProperties().forEach(property -> {
                    PropertyModel propertyModel = PropertyModel.builder()
                            .name(property.getName())
                            .rawType(property.getType())
                            .type(PropertyType.parse(property.getType()))
                            .build();
                    Logger.info("Created entity property model: %s/%s", entityModel.fullyQualifiedName(), propertyModel.getName());
                    entityModel.getProperties().put(property.getName(), propertyModel);
                });
            });

        });
    }
}
