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

package io.apicurio.umg.index;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.apache.commons.collections4.Trie;
import org.apache.commons.collections4.trie.PatriciaTrie;

import io.apicurio.umg.models.ClassModel;
import io.apicurio.umg.models.PackageModel;

/**
 * @author eric.wittmann@gmail.com
 */
public class ModelIndex {

    private Trie<String, PackageModel> packageIndex = new PatriciaTrie<>();
    private Trie<String, ClassModel> classIndex = new PatriciaTrie<>();
    private Map<String, ClassModel> classnameIndex = new HashMap<>();

    public void remove(ClassModel classModel) {
        classIndex.remove(classModel.getFullyQualifiedName());
        // ClassName index, shared values?
        classnameIndex.remove(classModel.getName());
    }

    public void remove(PackageModel packageModel) {
        packageIndex.remove(packageModel.getName());
    }

    public boolean hasPackage(String name) {
        return packageIndex.containsKey(name);
    }
    public boolean hasClass(String fullyQualifiedClassName) {
        return classIndex.containsKey(fullyQualifiedClassName);
    }

    public void indexPackage(PackageModel model) {
        packageIndex.put(model.getName(), model);
    }

    public void indexClass(ClassModel model) {
        classIndex.put(model.getFullyQualifiedName(), model);
        classnameIndex.put(model.getName(), model);
    }

    public PackageModel lookupPackage(String packageName) {
        return packageIndex.get(packageName);
    }

    public PackageModel lookupPackage(String packageName, Function<Void, PackageModel> factory) {
        return packageIndex.computeIfAbsent(packageName, (key) -> factory.apply(null));
    }

    public ClassModel lookupClass(String fullyQualifiedClassName) {
        return classIndex.get(fullyQualifiedClassName);
    }

    public Collection<PackageModel> findPackages(String prefix) {
        return packageIndex.prefixMap(prefix).values();
    }

    public Collection<ClassModel> findClasses(String prefix) {
        return classIndex.prefixMap(prefix).values();
    }

}
