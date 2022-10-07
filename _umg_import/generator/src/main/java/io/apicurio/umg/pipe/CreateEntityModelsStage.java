package io.apicurio.umg.pipe;

import io.apicurio.umg.logging.Logger;
import io.apicurio.umg.models.EntityModel;
import io.apicurio.umg.models.NamespaceModel;

public class CreateEntityModelsStage extends AbstractStage {
	@Override
    protected void doProcess() {
		Logger.info("-- Creating Entity Models --");
        getState().getSpecifications().forEach(spec -> {
            spec.getEntities().forEach(entity -> {
            	NamespaceModel nsModel = getState().getModelIndex().lookupNamespace(spec.getNamespace());
            	EntityModel model = EntityModel.builder().namespace(nsModel).name(entity.getName()).spec(spec).build();
		        Logger.info("Created entity model: %s", model.fullyQualifiedName());
		        nsModel.getEntities().put(model.getName(), model);
                getState().getSpecIndex().index(model);
            	getState().getModelIndex().index(model);
            });
        });
    }
}
