package io.apicurio.umg.pipe;

import io.apicurio.umg.models.ClassModel;
import io.apicurio.umg.models.PackageModel;

public class ProcessExtendsStage implements Stage {

    private GenState state;

    @Override
    public void process(GenState state) {
        this.state = state;

        state.getSpecIndex().getEntityIndex().forEach((id, em) -> {
            var entity = em.getEntity();
            if(entity.getExtends() != null) {
                // TODO A "common" package/namespace?
                String fqn = null;
                if(!entity.getExtends().contains(".")) {
                    fqn = em.getSpec().getPackage() + "." + entity.getExtends();
                }
                if(fqn == null){
                    throw new RuntimeException("TODO");
                }
                var parent = state.getIndex().lookupClass(fqn);
                em.getClassModel().getParents().add(parent);
            }
        });
    }
}
