package io.apicurio.umg.pipe;

import io.apicurio.umg.models.ClassModel;
import io.apicurio.umg.models.PackageModel;

import java.util.HashMap;

import static io.apicurio.umg.pipe.Util.nullableBoolean;

public class RemoveTransparentClassModelsStage implements Stage {

    private GenState state;

    @Override
    public void process(GenState state) {
        this.state = state;

        var copy = new HashMap<>(state.getSpecIndex().getEntityIndex());
        copy.forEach((id, em) -> {
            var entity = em.getEntity();
            if(nullableBoolean(entity.getTransparent())) {
                //state.getSpecIndex().getEntityIndex().remove(id);
                state.getIndex().remove(em.getClassModel());
            }
        });
    }
}
