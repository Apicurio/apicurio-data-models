package io.apicurio.umg.pipe;

import io.apicurio.umg.models.TraitModel;

public class IndexTraitModelsStage implements Stage {

    private GenState state;

    @Override
    public void process(GenState state) {
        this.state = state;
        state.getSpecifications().forEach(spec -> {
            spec.getTraits().forEach(trait -> {
                state.getSpecIndex().index(spec, trait, TraitModel.builder().spec(spec).trait(trait).build());
            });
        });
    }
}
