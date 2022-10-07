package io.apicurio.umg.pipe;

import io.apicurio.umg.logging.Logger;
import io.apicurio.umg.models.NamespaceModel;
import io.apicurio.umg.models.TraitModel;

public class CreateTraitModelsStage extends AbstractStage {

	@Override
	protected void doProcess() {
		Logger.info("-- Creating Trait Models --");
		getState().getSpecifications().forEach(spec -> {
			spec.getTraits().forEach(trait -> {
				NamespaceModel nsModel = getState().getModelIndex().lookupNamespace(spec.getNamespace());
				TraitModel model = TraitModel.builder()
						.namespace(nsModel)
						.name(trait.getName())
						.spec(spec)
						.transparent(Util.nullableBoolean(trait.getTransparent()))
						.build();
				Logger.info("Created trait model: %s", model.fullyQualifiedName());
				getState().getModelIndex().index(model);
			});
		});
	}

}
