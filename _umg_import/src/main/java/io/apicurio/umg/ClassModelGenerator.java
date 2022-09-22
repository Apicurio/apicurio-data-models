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

import io.apicurio.umg.index.SpecificationIndex;
import io.apicurio.umg.index.ModelIndex;
import io.apicurio.umg.logging.Logger;
import io.apicurio.umg.models.ClassModel;
import io.apicurio.umg.models.FieldModel;
import org.apache.commons.lang3.StringUtils;
import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.Type;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.jboss.forge.roaster.model.source.MethodSource;
import org.jboss.forge.roaster.model.util.Types;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Used to generate a {@link io.apicurio.umg.models.ClassModel} as a java class file.
 * @author eric.wittmann@gmail.com
 */
public class ClassModelGenerator {

    private final SpecificationIndex specIndex;
    private final ModelIndex modelIndex;
    private final ClassModel model;

    /**
     * Constructor.
     * @param specIndex
     * @param modelIndex
     * @param model
     */
    public ClassModelGenerator(SpecificationIndex specIndex, ModelIndex modelIndex, ClassModel model) {
        this.specIndex = specIndex;
        this.modelIndex = modelIndex;
        this.model = model;
    }

    /**
     * Generates the class file into the given directory.  Will output into a subfolder
     * based on standard Java conventions based on package name.
     * @param outputDirectory
     */
    public void generateInto(File outputDirectory) {
        Logger.info("Generating model for entity '%s'", model.getName());

        // Generate a java class for the model.
        JavaClassSource modelClass = Roaster.create(JavaClassSource.class)
                .setPackage(model.getPackage().getName())
                .setName(model.getName())
                .setAbstract(model.isAbstract())
                .setPublic();

        // Set the super type
        JavaClassSource superTypeClass = Roaster.create(JavaClassSource.class)
                .setPackage(model.getParent().getPackage().getName())
                .setName(model.getParent().getName());
        modelClass.extendSuperType(superTypeClass);
//        modelClass.addInterface(parent);

        // Add fields with getters/setters
        model.getFields().values().forEach(fieldModel -> {
            String fieldName = sanitizeFieldName(fieldModel.getName());
            if (!"*".equals(fieldName)) {
                Type fieldType = resolveType(fieldModel.getType());
                String resolvedType = Types.toResolvedType(fieldType.getQualifiedNameWithGenerics(), modelClass.getOrigin());
                modelClass.addField()
                        .setName(fieldName)
                        .setPrivate()
                        .setType(resolvedType);

                // Add a getter for the field.
                modelClass.addMethod()
                        .setName(fieldGetter(fieldModel))
                        .setReturnType(resolvedType)
                        .setPublic()
                        .setBody("return " + fieldName + ";");

                // Add a setter for the field.
                modelClass.addMethod()
                        .setName(fieldSetter(fieldModel))
                        .setReturnTypeVoid()
                        .setPublic()
                        .setBody("this." + fieldName + " = " + fieldName + ";")
                        .addParameter(resolvedType, fieldName);
            }
        });

        // Add "accept" method
        if (!model.isAbstract()) {
            // TODO cast the visitor to the correct variant (based on the specification)
            modelClass.addMethod()
                    .setName("accept")
                    .setPublic()
                    .setReturnTypeVoid()
                    .setBody("visitor.visit" + model.getName() + "(this);")
                    .addParameter("io.apicurio.datamodels.core.visitors.IVisitor", "visitor");
        }

        writeToFile(modelClass, outputDirectory);
    }

    /**
     * Rename some field names that are not legal in Java, such as "default" and "package".
     * @param name
     */
    private String sanitizeFieldName(String name) {
        if ("default".equals(name)) {
            return "_default";
        }
        if ("enum".equals(name)) {
            return "_enum";
        }
        return name;
    }

    /**
     * Resolves a type defined in a specfile to a Roaster {@link Type}.
     */
    private Type<?> resolveType(String type) {
        boolean isList = false;
        boolean isMap = false;

        if (type.startsWith("[")) {
            isList = true;
            type = type.substring(1, type.length() - 1);
        } else if (type.startsWith("{")) {
            isMap = true;
            type = type.substring(1, type.length() - 1);
        }

        String typeToUse;
        if ("string".equals(type)) {
            typeToUse = String.class.getName();
        } else if ("boolean".equals(type)) {
            typeToUse = Boolean.class.getName();
        } else if ("number".equals(type)) {
            typeToUse = Number.class.getName();
        } else if ("object".equals(type)) {
            typeToUse = Object.class.getName();
        } else if ("integer".equals(type)) {
            typeToUse = Integer.class.getName();
        } else if (type.contains("|")) {
            typeToUse = Object.class.getName();
        } else if (this.modelIndex.hasClass(model.getPackage().getName() + "." + type)) {
            ClassModel cm = modelIndex.lookupClass(model.getPackage().getName() + "." + type);
            typeToUse = cm.getFullyQualifiedName();
        } else {
            throw new RuntimeException("Unknown type: " + type);
        }

        if (isList) {
            typeToUse = "java.util.List<" + typeToUse + ">";
        } else if (isMap) {
            typeToUse = "java.util.Map<String, " + typeToUse + ">";
        }

        String stub = "public class Stub { public " + typeToUse + " method() {} }";
        JavaClassSource temp = (JavaClassSource) Roaster.parse(stub);
        List<MethodSource<JavaClassSource>> methods = temp.getMethods();
        return methods.get(0).getReturnType();
    }

    private static String fieldGetter(FieldModel fieldModel) {
        boolean isBool = fieldModel.getType().equals("boolean");
        return (isBool ? "is" : "get") + StringUtils.capitalize(fieldModel.getName());
    }

    private static String fieldSetter(FieldModel fieldModel) {
        return "set" + StringUtils.capitalize(fieldModel.getName());
    }

    /**
     * Writes the given class out to a file.
     * @param modelClass
     * @param outputDirectory
     */
    private void writeToFile(JavaClassSource modelClass, File outputDirectory) {
        String pkg = modelClass.getPackage();
        String fpath = pkg.replaceAll("[\\.]", "/");
        File dir = new File(outputDirectory, fpath);
        dir.mkdirs();
        File file = new File(dir, modelClass.getName() + ".java");
        try (PrintWriter writer = new PrintWriter(file)) {
            writer.write(modelClass.toString());
            writer.flush();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
