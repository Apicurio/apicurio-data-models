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
                TraitModel traitModel = TraitModel.builder()
                        .namespace(nsModel)
                        .name(trait.getName())
                        .spec(spec)
                        .transparent(Util.nullableBoolean(trait.getTransparent()))
                        .leaf(true)
                        .build();
                Logger.info("Created trait model: %s", traitModel.fullyQualifiedName());

                // Add trait to namespace
                nsModel.getTraits().put(traitModel.getName(), traitModel);
                // Index the model
                getState().getModelIndex().index(traitModel);
            });
        });
    }

}
