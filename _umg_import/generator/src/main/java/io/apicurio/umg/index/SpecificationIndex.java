/*
 * Copyright 2020 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.apicurio.umg.index;

import java.util.HashMap;
import java.util.Map;

import io.apicurio.umg.models.EntityModel;
import io.apicurio.umg.models.EntityModelId;
import io.apicurio.umg.models.TraitModel;
import io.apicurio.umg.models.TraitModelId;
import lombok.Getter;

/**
 * Maintains an index of all specifications.
 *
 * @author eric.wittmann@gmail.com
 */
public class SpecificationIndex {

    @Getter
    private Map<TraitModelId, TraitModel> traitIndex = new HashMap<>();
    @Getter
    private Map<EntityModelId, EntityModel> entityIndex = new HashMap<>();

    public void index(EntityModel model) {
        var key = EntityModelId.create(model.getSpec(), model);
        entityIndex.put(key, model);
    }

    public void index(TraitModel model) {
        var key = TraitModelId.create(model.getSpec(), model);
        traitIndex.put(key, model);
    }
}
