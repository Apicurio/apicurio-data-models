package io.apicurio.umg.pipe;

import java.util.HashMap;

public class RemoveTransparentClassModelsStage implements Stage {

    private GenState state;

    @Override
    public void process(GenState state) {
        this.state = state;

        var copy = new HashMap<>(state.getSpecIndex().getEntityIndex());
        copy.forEach((id, em) -> {
//            var entity = em.getEntity();
//            if(nullableBoolean(entity.getTransparent())) {
//                //state.getSpecIndex().getEntityIndex().remove(id);
//                state.getIndex().remove(em.getClassModel());
//            }
        });
    }
}
