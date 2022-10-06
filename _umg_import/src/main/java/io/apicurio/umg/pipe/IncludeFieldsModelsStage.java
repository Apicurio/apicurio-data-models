package io.apicurio.umg.pipe;

import io.apicurio.umg.models.EntityModelId;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

import static io.apicurio.umg.pipe.Util.nullableBoolean;

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
            var entityId = stack.pop();
            if(done.contains(entityId)) {
                continue;
            }
            var entityModel = state.getSpecIndex().getEntityIndex().get(entityId);
            var entity = entityModel.getEntity();

            var includes = entity.getTraits();
            if(includes != null && includes.size() > 0) {
                stack.push(entityId);

                includes.forEach(incl -> {
                    // get the entity
                    var inclEntityId = EntityModelId.builder()
                            .specificationId(entityId.getSpecificationId())
                            .specificationVersion(entityId.getSpecificationVersion())
                            .entityName(incl)
                            .build();
                    var inclEntityModel = state.getSpecIndex().getEntityIndex().get(inclEntityId);
                    // Mark as trait
                    inclEntityModel.setTrait(true);
                    inclEntityModel.getClassModel().set_interface(true);

                    var inclEntity = inclEntityModel.getEntity();

                    if(inclEntity == null) {
                        throw new RuntimeException("Included entity " + entityId + " not found");
                    }

                    if(ready.contains(entityId)) {
//                        if(!nullableBoolean(inclEntityModel.getEntity().getTransparent())) {
//                            entityModel.getClassModel().get_implements().add(inclEntityModel.getClassModel());
//                        }
                        entity.getProperties().addAll(inclEntity.getProperties());
                        done.add(entityId);
                    } else {
                        stack.push(inclEntityId);
                    }
                });
            }
            ready.add(entityId);
        }
    }
}
