package io.apicurio.umg.pipe;

import io.apicurio.umg.logging.Logger;
import io.apicurio.umg.models.EntityModel;
import io.apicurio.umg.models.NamespaceModel;
import io.apicurio.umg.models.TraitModel;

/**
 * Create models for all of the entities described in all the specifications.
 */
public class CreateEntityModelsStage extends AbstractStage {
	@Override
	protected void doProcess() {
		Logger.info("-- Creating Entity Models --");
		getState().getSpecifications().forEach(spec -> {
			spec.getEntities().forEach(entity -> {
				NamespaceModel nsModel = getState().getModelIndex().lookupNamespace(spec.getNamespace());
				if (nsModel == null) {
					throw new RuntimeException("Namespace '" + spec.getNamespace() + "' for entity '" + entity.getName() + "' not found.");
				}
				EntityModel entityModel = EntityModel.builder().namespace(nsModel).name(entity.getName()).spec(spec).build();
				Logger.info("Created entity model: %s", entityModel.fullyQualifiedName());

				// Add traits to the model
				entity.getTraits().forEach(trait -> {
					String fqTraitName = spec.getNamespace() + "." + trait;
					TraitModel traitModel = getState().getModelIndex().lookupTrait(fqTraitName);
					if (traitModel == null) {
						throw new RuntimeException("Trait '" + fqTraitName + "' referenced by entity '" + entityModel.fullyQualifiedName() + "' not found.");
					}
					entityModel.getTraits().add(traitModel);
				});

				// Add entity to namespace
				nsModel.getEntities().put(entityModel.getName(), entityModel);
				// Index the model
				getState().getModelIndex().index(entityModel);
			});
		});
	}
}
