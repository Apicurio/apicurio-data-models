package io.apicurio.umg.pipe;

import io.apicurio.umg.models.EntityModel;

public class IndexEntityModelsStage implements Stage {

    private GenState state;

    @Override
    public void process(GenState state) {
        this.state = state;
        state.getSpecifications().forEach(spec -> {
            spec.getEntities().forEach(entity -> {
                state.getSpecIndex().index(spec, entity, EntityModel.builder().spec(spec).entity(entity).build());
            });
        });
    }
}
