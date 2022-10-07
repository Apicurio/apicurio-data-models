package io.apicurio.umg.pipe;

public class ProcessExtendsStage extends AbstractStage {
    @Override
    protected void doProcess() {
        getState().getSpecIndex().getEntityIndex().forEach((id, em) -> {
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
                var parent = getState().getIndex().lookupClass(fqn);
                em.getClassModel().getParents().add(parent);
            }
        });
    }
}
