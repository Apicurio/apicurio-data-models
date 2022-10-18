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

package io.apicurio.umg.index.concept;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import io.apicurio.umg.beans.Entity;
import io.apicurio.umg.beans.SpecificationVersion;
import io.apicurio.umg.beans.Trait;
import io.apicurio.umg.models.concept.EntityId;
import io.apicurio.umg.models.concept.SpecificationVersionId;
import io.apicurio.umg.models.concept.TraitId;
import io.apicurio.umg.models.spec.SpecificationModel;
import lombok.Getter;

/**
 * Maintains an index of all specifications.
 *
 * @author eric.wittmann@gmail.com
 */
public class SpecificationIndex {

    @Getter
    private Collection<SpecificationModel> specifications = new HashSet<>();
    @Getter
    private Map<SpecificationVersionId, SpecificationModel> specIndex = new HashMap<>();
    @Getter
    private Collection<SpecificationVersion> specificationVersions = new HashSet<>();
    @Getter
    private Map<TraitId, Trait> traitIndex = new HashMap<>();
    @Getter
    private Map<EntityId, Entity> entityIndex = new HashMap<>();

    public void index(SpecificationModel spec) {
        specifications.add(spec);
        spec.getVersions().forEach(specVer -> {
            specificationVersions.add(specVer);
            specIndex.put(SpecificationVersionId.create(specVer), spec);
            specVer.getTraits().forEach(trait -> indexTrait(specVer, trait));
            specVer.getEntities().forEach(entity -> indexEntity(specVer, entity));
        });
    }

    public void indexEntity(SpecificationVersion specVersion, Entity model) {
        var key = EntityId.create(specVersion, model);
        entityIndex.put(key, model);
    }

    public void indexTrait(SpecificationVersion specVersion, Trait model) {
        var key = TraitId.create(specVersion, model);
        traitIndex.put(key, model);
    }

    public Collection<SpecificationVersion> getAllSpecificationVersions() {
        return Collections.unmodifiableCollection(specificationVersions);
    }
}
