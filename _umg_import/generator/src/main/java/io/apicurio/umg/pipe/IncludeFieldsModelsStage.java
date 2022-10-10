package io.apicurio.umg.pipe;

public class IncludeFieldsModelsStage extends AbstractStage {
    @Override
    protected void doProcess() {
//        Deque<EntityModelId> stack = new ArrayDeque<>(getState().getSpecIndex().getEntityIndex().keySet());
//        Set<EntityModelId> ready = new HashSet<>();
//        Set<EntityModelId> done = new HashSet<>();
//        var i = 1;
//        while(!stack.isEmpty()) {
//            System.out.println("" + i + " > " + stack.size());i++;
//            EntityModelId entityId = stack.pop();
//            if(done.contains(entityId)) {
//                continue;
//            }
//            EntityModel entityModel = getState().getSpecIndex().getEntityIndex().get(entityId);
//            Entity entity = entityModel.getEntity();
//
//            List<String> traits = entity.getTraits();
//            if(traits != null && traits.size() > 0) {
//                stack.push(entityId);
//
//                traits.forEach(incl -> {
//                    // get the trait
//                    TraitModelId inclTraitId = TraitModelId.builder()
//                            .specificationId(entityId.getSpecificationId())
//                            .specificationVersion(entityId.getSpecificationVersion())
//                            .traitName(incl)
//                            .build();
//                    TraitModel inclTraitModel = getState().getSpecIndex().getTraitIndex().get(inclTraitId);
//                    if (inclTraitModel == null) {
//                        throw new RuntimeException("Included trait " + inclTraitId + " not found");
//                    }
//                    inclTraitModel.getClassModel().set_interface(true);
//
//                    Trait inclTrait = inclTraitModel.getTrait();
//                    if(ready.contains(entityId)) {
////                        if(!nullableBoolean(inclTraitModel.getTrait().getTransparent())) {
////                            entityModel.getClassModel().get_implements().add(inclTraitModel.getClassModel());
////                        }
//                        entity.getProperties().addAll(inclTrait.getProperties());
//                        done.add(entityId);
//                    } else {
////                        stack.push(inclTraitId);
//                    }
//                });
//            }
//            ready.add(entityId);
//        }
    }
}
