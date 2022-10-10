/*
 * Copyright 2021 JBoss Inc
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

import io.apicurio.umg.models.VisitorModel;
import io.apicurio.umg.models.concept.EntityModel;
import io.apicurio.umg.models.concept.NamespaceModel;
import io.apicurio.umg.models.concept.TraitModel;
import org.apache.commons.collections4.Trie;
import org.apache.commons.collections4.trie.PatriciaTrie;

import java.util.Collection;
import java.util.function.Function;

/**
 * @author eric.wittmann@gmail.com
 */
public class ModelIndex {

    private Trie<String, NamespaceModel> namespaceIndex = new PatriciaTrie<>();
    private Trie<String, TraitModel> traitIndex = new PatriciaTrie<>();
    private Trie<String, EntityModel> entityIndex = new PatriciaTrie<>();
    private Trie<String, VisitorModel> visitorIndex = new PatriciaTrie<>();


    public void remove(TraitModel traitModel) {
        traitIndex.remove(traitModel.fullyQualifiedName());
    }

    public void remove(EntityModel entityModel) {
        entityIndex.remove(entityModel.fullyQualifiedName());
    }

    public void remove(NamespaceModel namespaceModel) {
        namespaceIndex.remove(namespaceModel.getName());
    }

    public void remove(VisitorModel visitorModel) {
        entityIndex.remove(visitorModel.getNamespace().fullName());
    }


    public boolean hasNamespace(String name) {
        return namespaceIndex.containsKey(name);
    }

    public boolean hasTrait(String fullyQualifiedTraitName) {
        return traitIndex.containsKey(fullyQualifiedTraitName);
    }

    public boolean hasEntity(String fullyQualifiedEntityName) {
        return entityIndex.containsKey(fullyQualifiedEntityName);
    }

    public boolean hasVisitor(String namespace) {
        return namespaceIndex.containsKey(namespace);
    }


    public void index(NamespaceModel model) {
        namespaceIndex.put(model.getName(), model);
    }

    public void index(TraitModel model) {
        traitIndex.put(model.fullyQualifiedName(), model);
    }

    public void index(EntityModel model) {
        entityIndex.put(model.fullyQualifiedName(), model);
    }

    public void index(VisitorModel model) {
        visitorIndex.put(model.getNamespace().fullName(), model);
    }


    public NamespaceModel lookupNamespace(String namespace) {
        return namespaceIndex.get(namespace);
    }

    public NamespaceModel lookupNamespace(String namespace, Function<String, NamespaceModel> factory) {
        return namespaceIndex.computeIfAbsent(namespace, (key) -> factory.apply(key));
    }

    public TraitModel lookupTrait(String fullyQualifiedTraitName) {
        return traitIndex.get(fullyQualifiedTraitName);
    }

    public EntityModel lookupEntity(String fullyQualifiedEntityName) {
        return entityIndex.get(fullyQualifiedEntityName);
    }

    public VisitorModel lookupVisitor(String namespace) {
        return visitorIndex.get(namespace);
    }


    public Collection<NamespaceModel> findNamespaces(String prefix) {
        return namespaceIndex.prefixMap(prefix).values();
    }

    public Collection<TraitModel> findTraits(String prefix) {
        return traitIndex.prefixMap(prefix).values();
    }

    public Collection<EntityModel> findEntities(String prefix) {
        return entityIndex.prefixMap(prefix).values();
    }

    public Collection<VisitorModel> findVisitors(String prefix) {
        return visitorIndex.prefixMap(prefix).values();
    }

}
