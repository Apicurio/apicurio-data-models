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

package io.apicurio.umg;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import io.apicurio.umg.logging.Logger;
import io.apicurio.umg.spec.Entity;
import io.apicurio.umg.spec.EntityProperty;
import io.apicurio.umg.spec.Specification;

/**
 * @author eric.wittmann@gmail.com
 */
public class UnifiedModel {

    /**
     * Creates a model entity from an Entity read from one of the specification YAML files.
     * @param entity
     * @param fromSpec
     */
    public static UnifiedModel create(Entity entity, Specification fromSpec) {
        SpecVersion specVer = fromSpec.specVersion();
        Logger.debug("    Creating a model entity: %s  from spec  %s", entity.getName(), specVer);

        UnifiedModel modelEntity = new UnifiedModel();
        modelEntity.name = entity.getName();
        modelEntity.specVersions.add(specVer);
        modelEntity.specifications.add(specVer.getSpec());
        modelEntity.propertiesBySpecVersion.put(specVer, entity.getProperties());
        modelEntity.sourceEntities.put(specVer, entity);
        return modelEntity;
    }

    private String name;
    private Set<SpecVersion> specVersions = new HashSet<SpecVersion>();
    private Set<Spec> specifications = new HashSet<>();
    private Map<SpecVersion, Map<String, EntityProperty>> propertiesBySpecVersion = new HashMap<>();
    
    private Map<SpecVersion, Map<String, EntityProperty>> normalizedPropertiesBySpecVersion = new HashMap<>();
    private Map<Spec, Map<String, EntityProperty>> normalizedPropertiesBySpec = new HashMap<>();
    private Map<String, EntityProperty> normalizedProperties = new HashMap<>();
    
    private Map<SpecVersion, Entity> sourceEntities = new HashMap<>();
    
    /**
     * Constructor.
     */
    private UnifiedModel() {
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Merge the given entity into this model entity.
     * @param entity
     * @param fromSpec
     */
    public void merge(Entity entity, Specification fromSpec) {
        SpecVersion specVer = fromSpec.specVersion();
        Logger.debug("    Merging a model entity: %s  from spec  %s", entity.getName(), specVer);
        this.specVersions.add(specVer);
        this.propertiesBySpecVersion.put(specVer, entity.getProperties());
        this.sourceEntities.put(specVer, entity);
    }

    /**
     * Normalize the entity by 
     */
    public void normalize() {
        if (isSingleSpecVersion()) {
            // This entity is unique to a single version of a single specification.
            Entry<SpecVersion, Map<String, EntityProperty>> first = propertiesBySpecVersion.entrySet().iterator().next();
            SpecVersion specVer = first.getKey();
            Map<String, EntityProperty> properties = first.getValue();
            normalizedPropertiesBySpecVersion.put(specVer, properties);
        } else if (isSingleSpecification()) {
            // This entity is shared across multiple versions of a single specification.  This means we 
            // need to figure out the set of properties common across all versions, and then we need to
            // figure out the properties unique to each SpecVersion
            
            // Determine common properties
            Spec spec = getSingleSpec();
            Map<String, EntityProperty> specSharedProperties = null;
            for (Map<String, EntityProperty> props : propertiesBySpecVersion.values()) {
                specSharedProperties = intersect(specSharedProperties, props);
            }
            normalizedPropertiesBySpec.put(spec, specSharedProperties);

            // Determine unique properties
            for (Entry<SpecVersion, Map<String, EntityProperty>> entry : propertiesBySpecVersion.entrySet()) {
                SpecVersion specVer = entry.getKey();
                Map<String, EntityProperty> specVerProperties = entry.getValue();
                specVerProperties = relativeComplement(specVerProperties, specSharedProperties);
                normalizedPropertiesBySpecVersion.put(specVer, specVerProperties);
            }
        } else {
            // This entity is shared across multiple specifications (and potentially multiple versions
            // of those specifications).  This means we need to figure out the set of properties common
            // across all spec versions, then we need to identify the set of properties common at each
            // specification level, and finally we need to identify the set of properties unique to each
            // SpecVersion
            
            // Determine common properties first
            Map<String, EntityProperty> rootSharedProperties = null;
            for (Map<String, EntityProperty> props : propertiesBySpecVersion.values()) {
                rootSharedProperties = intersect(rootSharedProperties, props);
            }
            normalizedProperties = rootSharedProperties;
            
            // Determine properties at each specification level
            this.specifications.forEach(spec -> {
                Map<String, EntityProperty> specSharedProperties = null;
                List<Map<String, EntityProperty>> plist = this.propertiesBySpecVersion.entrySet().stream()
                    .filter(e -> e.getKey().getSpec() == spec)
                    .map(e -> e.getValue())
                    .collect(Collectors.toList());
                for (Map<String, EntityProperty> props : plist) {
                    specSharedProperties = intersect(specSharedProperties, props);
                }
                if (specSharedProperties != null) {
                    // At this point, we have the set of properties common to just the versions of
                    // this one specification.  Now we have to remove the properties common to all specs.
                    specSharedProperties = relativeComplement(specSharedProperties, normalizedProperties);
                    // TODO if there is only ONE version of the specification, what do we want?
                    this.normalizedPropertiesBySpec.put(spec, specSharedProperties);
                }
            });
            
            // Lastly, determine the set of properties at the SpecVersion level
            this.propertiesBySpecVersion.entrySet().forEach(e -> {
                SpecVersion specVersion = e.getKey();
                Map<String, EntityProperty> props = e.getValue();
                Map<String, EntityProperty> specProps = this.getPropertiesFor(specVersion.getSpec());
                Map<String, EntityProperty> rootProps = this.normalizedProperties;
                
                props = relativeComplement(props, specProps);
                props = relativeComplement(props, rootProps);
                
                this.normalizedPropertiesBySpecVersion.put(specVersion, props);
            });
            
        }
    }

    /**
     * Returns true if this entity exists only within a single specification, even if it is used by
     * multiple versions of that specification.
     */
    public boolean isSingleSpecification() {
        Set<Spec> allSpecs = getAllSpecs();
        return allSpecs.size() == 1;
    }

    /**
     * Returns all of the specifications that this entity belongs to.
     */
    public Set<Spec> getAllSpecs() {
        return this.specVersions.stream().map(sv -> sv.getSpec()).collect(Collectors.toSet());
    }
    
    /**
     * Returns all of the specification versions that this entity belongs to.
     */
    public Set<SpecVersion> getAllSpecVersions() {
        return this.specVersions;
    }

    /**
     * Returns the single specification that this entity is associated with.  Only valid when the
     * "isSingleSpec()" method returns true, otherwise should not be used.
     */
    public Spec getSingleSpec() {
        return propertiesBySpecVersion.keySet().iterator().next().getSpec();
    }
    
    /**
     * Returns the single specification+version that this entity is found in.  Only valid when
     * the "is
     */
    public SpecVersion getSingleSpecVersion() {
        return propertiesBySpecVersion.keySet().iterator().next();
    }

    /**
     * Returns true if this entity exists only in a single specification+version.
     */
    public boolean isSingleSpecVersion() {
        return this.specVersions.size() == 1;
    }

    /**
     * Standard "intersect" set operation.
     * @param propertiesA
     * @param propertiesB
     */
    private static Map<String, EntityProperty> intersect(Map<String, EntityProperty> propertiesA,
            Map<String, EntityProperty> propertiesB) {
        Map<String, EntityProperty> i = new HashMap<>();
        if (propertiesA == null && propertiesB != null) {
            i.putAll(propertiesB);
        } else if (propertiesB == null && propertiesA != null) {
            i.putAll(propertiesA);
        } else if (propertiesA == null && propertiesB == null) {
            i = null;
        } else {
            Set<String> keys = new HashSet<>(propertiesA.keySet());
            keys.retainAll(propertiesB.keySet());
            for (String key : keys) {
                EntityProperty propA = propertiesA.get(key);
                EntityProperty propB = propertiesB.get(key);
                if (propA.equals(propB)) {
                    i.put(key, propA);
                }
            }
        }
        return i;
    }

    /**
     * Implements the "relative complement" set operation.  The relative complement is also known as
     * difference or subtraction.  This will subtract the items in set A from set B, returning a copy
     * of set B without those items.
     * @param propertiesB
     * @param propertiesA
     */
    private Map<String, EntityProperty> relativeComplement(Map<String, EntityProperty> propertiesB,
            Map<String, EntityProperty> propertiesA) {
        Map<String, EntityProperty> c = new HashMap<>();
        propertiesB.entrySet().forEach(entryB -> {
            if (!contains(propertiesA, entryB)) {
                c.put(entryB.getKey(), entryB.getValue());
            }
        });
        return c;
    }

    /**
     * Returns true if the given entry exists in the provided properties map.
     * @param properties
     * @param entry
     */
    private boolean contains(Map<String, EntityProperty> properties, Entry<String, EntityProperty> entry) {
        if (properties == null) {
            return false;
        }
        EntityProperty property = properties.get(entry.getKey());
        if (property == null) {
            return false;
        }
        return property.equals(entry.getValue());
    }

    /**
     * Returns the normalized properties for the given specification+version;
     * @param specVer
     * @return 
     */
    public Map<String, EntityProperty> getPropertiesFor(SpecVersion specVer) {
        return this.normalizedPropertiesBySpecVersion.get(specVer);
    }

    /**
     * Returns the normalized properties for the given spec.
     * @param spec
     */
    public Map<String, EntityProperty> getPropertiesFor(Spec spec) {
        return this.normalizedPropertiesBySpec.get(spec);
    }

    /**
     * Returns the normalized properties for the shared root model.
     */
    public Map<String, EntityProperty> getPropertiesForRoot() {
        return this.normalizedProperties;
    }


}
