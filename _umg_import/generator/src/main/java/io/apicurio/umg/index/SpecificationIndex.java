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

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import io.apicurio.umg.beans.Entity;
import io.apicurio.umg.beans.Specification;
import io.apicurio.umg.beans.Trait;
import io.apicurio.umg.models.EntityId;
import io.apicurio.umg.models.TraitId;
import lombok.Getter;

/**
 * Maintains an index of all specifications.
 *
 * @author eric.wittmann@gmail.com
 */
public class SpecificationIndex {

    @Getter
    private Collection<Specification> specifications = new HashSet<>();
    @Getter
    private Map<TraitId, Trait> traitIndex = new HashMap<>();
    @Getter
    private Map<EntityId, Entity> entityIndex = new HashMap<>();

    public void index(Specification spec) {
        specifications.add(spec);
        spec.getTraits().forEach(trait -> indexTrait(spec, trait));
        spec.getEntities().forEach(entity -> indexEntity(spec, entity));
    }

    public void indexEntity(Specification spec, Entity model) {
        var key = EntityId.create(spec, model);
        entityIndex.put(key, model);
    }

    public void indexTrait(Specification spec, Trait model) {
        var key = TraitId.create(spec, model);
        traitIndex.put(key, model);
    }
}
