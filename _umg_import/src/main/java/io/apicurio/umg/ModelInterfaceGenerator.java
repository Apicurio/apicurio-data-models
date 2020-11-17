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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.JavaInterfaceSource;

import io.apicurio.umg.index.SpecificationIndex;
import io.apicurio.umg.logging.Logger;
import io.apicurio.umg.spec.EntityProperty;
import io.apicurio.umg.spec.Specification;

/**
 * @author eric.wittmann@gmail.com
 */
public class ModelInterfaceGenerator {

    private final SpecificationIndex index;
    private final UnifiedModel entity;

    /**
     * Constructor.
     * @param index
     * @param entity
     */
    public ModelInterfaceGenerator(SpecificationIndex index, UnifiedModel entity) {
        this.index = index;
        this.entity = entity;
    }

    /**
     * @param outputDirectory
     */
    public void generateInto(File outputDirectory) {
        Logger.info("Generating model interfaces for entity '%s'", entity.getName());
        
        // Always generate a core model interface for every logical entity
        JavaInterfaceSource baseEntityInterface = createModelInterface(
                baseEntityModelName(entity), 
                baseEntityPackage(),
                "io.apicurio.datamodels.core.models.INode",
                entity.getPropertiesForRoot());
        writeToFile(baseEntityInterface, outputDirectory);
        
        // Then create the spec-level model interfaces
        Map<Spec, JavaInterfaceSource> specModelInterfaces = new HashMap<>();
        for (Spec spec : entity.getAllSpecs()) {
            String name = specEntityModelName(entity, spec);
            String pkg = specEntityPackage(spec);
            String parent = baseEntityInterface.getQualifiedName();
            Map<String, EntityProperty> specProperties = entity.getPropertiesFor(spec);
            JavaInterfaceSource specEntityInterface = createModelInterface(name, pkg, parent, specProperties);
            writeToFile(specEntityInterface, outputDirectory);
            
            specModelInterfaces.put(spec, specEntityInterface);
        }

        // Finally, create SpecVersion-level model interfaces only where they exist
        Set<SpecVersion> allSpecVersions = entity.getAllSpecVersions();
        for (SpecVersion specVer : allSpecVersions) {
            String name = specVersionEntityModelName(entity, specVer);
            String pkg = specVersionEntityPackage(specVer);
            JavaInterfaceSource parentIface = specModelInterfaces.get(specVer.getSpec());
            String parent = parentIface.getQualifiedName();
            Map<String, EntityProperty> specProperties = entity.getPropertiesFor(specVer);
            JavaInterfaceSource specEntityInterface = createModelInterface(name, pkg, parent, specProperties);
            writeToFile(specEntityInterface, outputDirectory);
        }
    }

    private JavaInterfaceSource createModelInterface(String name, String _package, String parent, Map<String, EntityProperty> properties) {
        Logger.info("  Creating entity model interface: %s.%s", _package, name);

        JavaInterfaceSource baseEntityInterface = Roaster.create(JavaInterfaceSource.class);
        baseEntityInterface.setPackage(_package);
        baseEntityInterface.setName(name);
        baseEntityInterface.addInterface(parent);
        if (properties != null) {
            properties.entrySet().forEach(entry -> {
                addPropertyToInterface(entry.getKey(), entry.getValue(), baseEntityInterface);
            });
        }
        return baseEntityInterface;
    }

    /**
     * Adds the given property to the interface.
     * @param propertyName
     * @param property
     * @param entityInterface
     */
    private void addPropertyToInterface(String propertyName, EntityProperty property,
            JavaInterfaceSource entityInterface) {
        Logger.info("    Adding property '%s' to interface %s", propertyName, entityInterface.getQualifiedName());
        String getterName = propertyGetter(propertyName, property);
        String setterName = propertySetter(propertyName, property);
        
        entityInterface.addMethod()
            .setPublic()
            .setReturnType(String.class)
            .setName(getterName);
        
        entityInterface.addMethod()
            .setPublic()
            .setReturnTypeVoid()
            .setName(setterName)
            .addParameter(String.class, propertyName);
    }

    private static String propertyGetter(String propertyName, EntityProperty property) {
        return "get" + StringUtils.capitalize(propertyName);
    }

    private static String propertySetter(String propertyName, EntityProperty property) {
        return "set" + StringUtils.capitalize(propertyName);
    }

    /**
     * Figures out where to put the base interface for the entity.
     */
    private String baseEntityPackage() {
        return "io.apicurio.datamodels.core.models";
    }

    /**
     * Figures out where to put the spec level interface for the entity.
     */
    private String specEntityPackage(Spec spec) {
        return "io.apicurio.datamodels.core.models." + spec.getPackage();
    }

    /**
     * Figures out where to put the specVersion level interface for the entity.
     */
    private String specVersionEntityPackage(SpecVersion specVer) {
        String verPkg = versionToPackage(specVer.getVersion());
        return "io.apicurio.datamodels.core.models." + specVer.getSpec().getPackage() + "." + verPkg;
    }

    private String baseEntityModelName(UnifiedModel entity) {
        return"I" + entity.getName();
    }

    private String specEntityModelName(UnifiedModel entity, Spec spec) {
        return"I" + spec.getPrefix() + entity.getName();
    }

    private String specVersionEntityModelName(UnifiedModel entity, SpecVersion specVer) {
        Specification specification = index.getSpecification(specVer);
        return"I" + specification.getPrefix() + entity.getName();
    }

    /**
     * Sanitize a specification version number into a java package designation.
     * @param version
     */
    private String versionToPackage(String version) {
        return "v" + version.replaceAll("[\\.\\-]", "");
    }

    /**
     * Writes the given interface out to a file.
     * @param modelInterface
     * @param outputDirectory
     */
    private void writeToFile(JavaInterfaceSource modelInterface, File outputDirectory) {
        String pkg = modelInterface.getPackage();
        String fpath = pkg.replaceAll("[\\.]", "/");
        File dir = new File(outputDirectory, fpath);
        dir.mkdirs();
        File file = new File(dir, modelInterface.getName() + ".java");
        try (PrintWriter writer = new PrintWriter(file)) {
            writer.write(modelInterface.toString());
            writer.flush();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
