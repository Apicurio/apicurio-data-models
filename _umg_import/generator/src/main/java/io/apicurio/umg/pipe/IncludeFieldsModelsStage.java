package io.apicurio.umg.pipe;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import io.apicurio.umg.beans.beans.Entity;
import io.apicurio.umg.beans.beans.Trait;
import io.apicurio.umg.models.EntityModel;
import io.apicurio.umg.models.EntityModelId;
import io.apicurio.umg.models.TraitModel;
import io.apicurio.umg.models.TraitModelId;

public class IncludeFieldsModelsStage implements Stage {

    private GenState state;

    @Override
    public void process(GenState state) {
        this.state = state;

        Deque<EntityModelId> stack = new ArrayDeque<>(state.getSpecIndex().getEntityIndex().keySet());
        Set<EntityModelId> ready = new HashSet<>();
        Set<EntityModelId> done = new HashSet<>();
        var i = 1;
        while(!stack.isEmpty()) {
            System.out.println("" + i + " > " + stack.size());i++;
            EntityModelId entityId = stack.pop();
            if(done.contains(entityId)) {
                continue;
            }
            EntityModel entityModel = state.getSpecIndex().getEntityIndex().get(entityId);
            Entity entity = entityModel.getEntity();

            List<String> traits = entity.getTraits();
            if(traits != null && traits.size() > 0) {
                stack.push(entityId);

                traits.forEach(incl -> {
                    // get the trait
                    TraitModelId inclTraitId = TraitModelId.builder()
                            .specificationId(entityId.getSpecificationId())
                            .specificationVersion(entityId.getSpecificationVersion())
                            .traitName(incl)
                            .build();
                    TraitModel inclTraitModel = state.getSpecIndex().getTraitIndex().get(inclTraitId);
                    if (inclTraitModel == null) {
                        throw new RuntimeException("Included trait " + inclTraitId + " not found");
                    }
                    inclTraitModel.getClassModel().set_interface(true);

                    Trait inclTrait = inclTraitModel.getTrait();
                    if(ready.contains(entityId)) {
//                        if(!nullableBoolean(inclTraitModel.getTrait().getTransparent())) {
//                            entityModel.getClassModel().get_implements().add(inclTraitModel.getClassModel());
//                        }
                        entity.getProperties().addAll(inclTrait.getProperties());
                        done.add(entityId);
                    } else {
//                        stack.push(inclTraitId);
                    }
                });
            }
            ready.add(entityId);
        }
    }
}
