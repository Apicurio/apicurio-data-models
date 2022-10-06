package io.apicurio.umg.pipe;

import io.apicurio.umg.models.ClassModel;
import io.apicurio.umg.models.PackageModel;

public class CreateClassModelsStage implements Stage {

    private GenState state;

    @Override
    public void process(GenState state) {
        this.state = state;

        state.getSpecIndex().getTraitIndex().forEach((id, tm) -> {
            var trait = tm.getTrait();

            String specPackageName = tm.getSpec().getPackage();
            PackageModel specPackage = PackageModel.builder().name(specPackageName).build();

            ClassModel model = ClassModel.builder().build();
            model.setName(trait.getName());
            model.setPackage(specPackage);
            model.setAbstract(false);
//            if (entity.getExtensible() != null && entity.getExtensible()) {
//                model.getParents().add(state.getExtensibleNodeClass());
//            } else {
//                model.getParents().add(state.getNodeClass());
//            }
            specPackage.getClasses().put(model.getName(), model);

            state.getIndex().indexClass(model);

            tm.setClassModel(model);
        });

        
        state.getSpecIndex().getEntityIndex().forEach((id, em) -> {
            var entity = em.getEntity();

            String specPackageName = em.getSpec().getPackage();
            PackageModel specPackage = PackageModel.builder().name(specPackageName).build();

            ClassModel model = ClassModel.builder().build();
            model.setName(entity.getName());
            model.setPackage(specPackage);
            model.setAbstract(false);
//            if (entity.getExtensible() != null && entity.getExtensible()) {
//                model.getParents().add(state.getExtensibleNodeClass());
//            } else {
//                model.getParents().add(state.getNodeClass());
//            }
            specPackage.getClasses().put(model.getName(), model);

            state.getIndex().indexClass(model);

            em.setClassModel(model);
        });
    }
}
