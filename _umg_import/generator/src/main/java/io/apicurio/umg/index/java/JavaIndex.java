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

import io.apicurio.umg.models.java.JavaPackageModel;
import io.apicurio.umg.models.java.JavaType;
import lombok.Getter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Supplier;

/**
 * @author eric.wittmann@gmail.com
 */
public class JavaIndex {

    @Getter
    private Map<String, JavaType> types = new HashMap<>();

    @Getter
    private Map<String, JavaPackageModel> packages = new HashMap<>();

    public JavaPackageModel lookupAndIndexPackage(Supplier<JavaPackageModel> factory) {
        var _new = factory.get();
        Objects.requireNonNull(_new.getName());
        return packages.computeIfAbsent(_new.getName(), _unused -> _new);
    }

    public JavaType lookupAndIndexType(Supplier<JavaType> factory) {
        var _new = factory.get();
        var _package = _new.get_package();
        Objects.requireNonNull(_package);
        Objects.requireNonNull(_new.getName());
        return types.computeIfAbsent(_new.fullyQualifiedName(), _unused -> {
            _package.addClass(_new);
            return _new;
        });
    }

    public void removeType(JavaType type) {
        var _package = type.get_package();
        _package.getTypes().remove(type.fullyQualifiedName());
        types.remove(type.fullyQualifiedName());
    }

    public Set<JavaType> getAllTypesWithCopy() {
        return new HashSet<>(types.values());
    }
}
