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

package io.apicurio.umg.index.java;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Supplier;

import io.apicurio.umg.models.concept.EntityModel;
import io.apicurio.umg.models.concept.TraitModel;
import io.apicurio.umg.models.java.JavaClassModel;
import io.apicurio.umg.models.java.JavaEntityModel;
import io.apicurio.umg.models.java.JavaInterfaceModel;
import io.apicurio.umg.models.java.JavaPackageModel;
import lombok.Getter;

/**
 * @author eric.wittmann@gmail.com
 */
public class JavaIndex {

    @Getter
    private Map<String, JavaPackageModel> packages = new HashMap<>();

    @Getter
    private Map<String, JavaEntityModel> types = new HashMap<>();

    @Getter
    private Map<String, JavaClassModel> classes = new HashMap<>();

    @Getter
    private Map<String, JavaInterfaceModel> interfaces = new HashMap<>();

    public JavaPackageModel lookupPackage(String namespace) {
        return this.packages.get(namespace);
    }

    public JavaPackageModel lookupAndIndexPackage(Supplier<JavaPackageModel> factory) {
        var _new = factory.get();
        Objects.requireNonNull(_new.getName());
        return packages.computeIfAbsent(_new.getName(), _unused -> _new);
    }

    public JavaEntityModel lookupAndIndexType(Supplier<JavaEntityModel> factory) {
        var _new = factory.get();
        var _package = _new.get_package();
        Objects.requireNonNull(_package);
        Objects.requireNonNull(_new.getName());
        return types.computeIfAbsent(_new.fullyQualifiedName(), _unused -> {
            _package.addClass(_new);
            return _new;
        });
    }

    public JavaEntityModel lookupType(String fullyQualifiedName) {
        return this.types.get(fullyQualifiedName);
    }

    public JavaEntityModel lookupType(EntityModel entity) {
        return lookupType(entity.fullyQualifiedName());
    }

    public JavaEntityModel lookupType(TraitModel trait) {
        return lookupType(trait.fullyQualifiedName());
    }

    public void removeType(JavaEntityModel type) {
        var _package = type.get_package();
        _package.getTypes().remove(type.fullyQualifiedName());
        types.remove(type.fullyQualifiedName());
    }

    public Set<JavaEntityModel> getAllJavaEntitiesWithCopy() {
        return new HashSet<>(types.values());
    }

    public void addClass(JavaClassModel classModel) {
        this.classes.put(classModel.fullyQualifiedName(), classModel);
    }

    public void addInterface(JavaInterfaceModel interfaceModel) {
        this.interfaces.put(interfaceModel.fullyQualifiedName(), interfaceModel);
    }

    public JavaClassModel lookupClass(String fullyQualifiedName) {
        return this.classes.get(fullyQualifiedName);
    }

    public JavaInterfaceModel lookupInterface(String fullyQualifiedName) {
        return this.interfaces.get(fullyQualifiedName);
    }

}
