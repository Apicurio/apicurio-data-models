package io.apicurio.umg.pipe;

import io.apicurio.umg.models.TraitModel;

public class IndexTraitModelsStage extends AbstractStage {
    @Override
    protected void doProcess() {
        getState().getSpecifications().forEach(spec -> {
            spec.getTraits().forEach(trait -> {
                getState().getSpecIndex().index(spec, trait, TraitModel.builder().spec(spec).trait(trait).build());
            });
        });
    }
}
