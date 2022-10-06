package io.apicurio.umg.pipe;

import io.apicurio.umg.models.FieldModel;

public class CreateFieldModelsStage implements Stage {

    private GenState state;

    @Override
    public void process(GenState state) {
        this.state = state;

        state.getSpecIndex().getEntityIndex().forEach((id, em) -> {
            em.getEntity().getProperties().forEach(property -> {
                FieldModel field = FieldModel.builder().build();
                field.setName(property.getName());
                field.setType(property.getType());
                em.getClassModel().getFields().put(property.getName(), field);
            });
        });
    }
}
