package io.apicurio.umg.pipe;

import io.apicurio.umg.models.EntityModel;

public class IndexEntityModelsStage extends AbstractStage {
	@Override
    protected void doProcess() {
        getState().getSpecifications().forEach(spec -> {
            spec.getEntities().forEach(entity -> {
                getState().getSpecIndex().index(spec, entity, EntityModel.builder().spec(spec).entity(entity).build());
            });
        });
    }
}
