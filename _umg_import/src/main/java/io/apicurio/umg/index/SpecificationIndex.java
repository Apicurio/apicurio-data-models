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
import java.util.List;
import java.util.Map;

import io.apicurio.umg.beans.beans.Entity;
import io.apicurio.umg.beans.beans.Specification;
import io.apicurio.umg.models.EntityModelId;
import io.apicurio.umg.models.EntityModel;
import lombok.Getter;

/**
 * Maintains an index of all specifications.
 *
 * @author eric.wittmann@gmail.com
 */
public class SpecificationIndex {


    private List<Specification> specifications;

    @Getter
    private Map<EntityModelId, EntityModel> entityIndex = new HashMap<>();


    public void index(Specification spec, Entity entity, EntityModel model) {
        var key = EntityModelId.create(spec, entity);
        entityIndex.put(key, model);
    }
}
