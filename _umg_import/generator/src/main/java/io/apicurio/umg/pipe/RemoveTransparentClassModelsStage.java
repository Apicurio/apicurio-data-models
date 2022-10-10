package io.apicurio.umg.pipe;

import java.util.HashMap;

public class RemoveTransparentClassModelsStage extends AbstractStage {
    @Override
    protected void doProcess() {
        var copy = new HashMap<>(getState().getSpecIndex().getEntityIndex());
        copy.forEach((id, em) -> {
//            var entity = em.getEntity();
//            if(nullableBoolean(entity.getTransparent())) {
//                //getState().getSpecIndex().getEntityIndex().remove(id);
//                getState().getModelIndex().remove(em.getClassModel());
//            }
        });
    }
}
