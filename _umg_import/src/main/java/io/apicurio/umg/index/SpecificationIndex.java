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

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import io.apicurio.umg.Spec;
import io.apicurio.umg.SpecVersion;
import io.apicurio.umg.spec.Entity;
import io.apicurio.umg.spec.Specification;

/**
 * Maintains an index of all specifications.
 * 
 * @author eric.wittmann@gmail.com
 */
public class SpecificationIndex {

    private List<Specification> specifications = new LinkedList<>();
    private Map<Spec, List<Specification>> specificationsByType = new HashMap<>();
    private Map<SpecVersion, Specification> specificationBySpecVersion = new HashMap<>();
    private Map<Spec, Set<String>> entitiesBySpecType = new HashMap<>();
    private Map<SpecVersion, Set<Entity>> entitiesBySpecVersion = new HashMap<>();

    /**
     * Called to index a single specification.
     * @param specification
     */
    public void index(Specification specification) {
        specifications.add(specification);
        specificationsByType.compute(specification.getSpecification(), (k,v) -> {
            if (v == null) {
                v = new LinkedList<Specification>();
            }
            v.add(specification);
            return v;
        });
        specificationBySpecVersion.put(specification.specVersion(), specification);
        
        Optional.ofNullable(specification.getEntities()).orElse(Collections.emptyList()).forEach(entity -> indexEntity(entity, specification));
    }

    /**
     * Indexes the given entity.
     * @param entity
     * @param specification
     */
    private void indexEntity(Entity entity, Specification specification) {
        entitiesBySpecType.compute(specification.getSpecification(), (k,v) -> {
            if (v == null) {
                v = new HashSet<String>();
                v.add(entity.getName());
            }
            return v;
        });
        entitiesBySpecVersion.compute(specification.specVersion(), (k,v) -> {
            if (v == null) {
                v = new HashSet<Entity>();
                v.add(entity);
            }
            return v;
        });
    }
    
    public Specification getSpecification(SpecVersion specVer) {
        return this.specificationBySpecVersion.get(specVer);
    }
    
    public Set<Spec> getSpecificationTypes() {
        return this.specificationsByType.keySet();
    }
    
    public Set<SpecVersion> getSpecificationVersions() {
        return this.specificationBySpecVersion.keySet();
    }
    
    public Set<Entity> getEntities(SpecVersion specVer) {
        return this.entitiesBySpecVersion.get(specVer);
    }
    
    public Set<String> getEntityNames(Spec spec) {
        return this.entitiesBySpecType.get(spec);
    }

}
