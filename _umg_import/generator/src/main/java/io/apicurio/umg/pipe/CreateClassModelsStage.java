package io.apicurio.umg.pipe;

import io.apicurio.umg.models.ClassModel;
import io.apicurio.umg.models.PackageModel;

public class CreateClassModelsStage extends AbstractStage {

    @Override
    protected void doProcess() {
        getState().getSpecIndex().getTraitIndex().forEach((id, tm) -> {
            var trait = tm.getTrait();

            String specPackageName = tm.getSpec().getPackage();
            PackageModel specPackage = PackageModel.builder().name(specPackageName).build();

            ClassModel model = ClassModel.builder().build();
            model.setName(trait.getName());
            model.setPackage(specPackage);
            model.setAbstract(false);
//            if (entity.getExtensible() != null && entity.getExtensible()) {
//                model.getParents().add(getState().getExtensibleNodeClass());
//            } else {
//                model.getParents().add(getState().getNodeClass());
//            }
            specPackage.getClasses().put(model.getName(), model);

            getState().getIndex().indexClass(model);

            tm.setClassModel(model);
        });

        
        getState().getSpecIndex().getEntityIndex().forEach((id, em) -> {
            var entity = em.getEntity();

            String specPackageName = em.getSpec().getPackage();
            PackageModel specPackage = PackageModel.builder().name(specPackageName).build();

            ClassModel model = ClassModel.builder().build();
            model.setName(entity.getName());
            model.setPackage(specPackage);
            model.setAbstract(false);
//            if (entity.getExtensible() != null && entity.getExtensible()) {
//                model.getParents().add(getState().getExtensibleNodeClass());
//            } else {
//                model.getParents().add(getState().getNodeClass());
//            }
            specPackage.getClasses().put(model.getName(), model);

            getState().getIndex().indexClass(model);

            em.setClassModel(model);
        });
    }
}
