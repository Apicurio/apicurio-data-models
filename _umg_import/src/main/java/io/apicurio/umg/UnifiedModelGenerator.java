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

import io.apicurio.umg.beans.beans.Entity;
import io.apicurio.umg.beans.beans.Specification;
import io.apicurio.umg.index.ModelIndex;
import io.apicurio.umg.index.SpecificationIndex;
import io.apicurio.umg.logging.Logger;
import io.apicurio.umg.models.ClassModel;
import io.apicurio.umg.models.FieldModel;
import io.apicurio.umg.models.PackageModel;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author eric.wittmann@gmail.com
 */
public class UnifiedModelGenerator {

    public static UnifiedModelGenerator create(List<Specification> specifications) {
        UnifiedModelGenerator generator = new UnifiedModelGenerator();
        generator.setSpecifications(specifications);
        return generator;
    }

    private List<Specification> specifications;
    // TODO need this? remove if not
    private SpecificationIndex specIndex = new SpecificationIndex();;
    private ModelIndex modelIndex = new ModelIndex();

    private PackageModel basePackage;
    private PackageModel coreModelPackage;
    private ClassModel nodeClass;
    private ClassModel extensibleNodeClass;
    private ClassModel documentClass;

    /**
     * Constructor.
     */
    private UnifiedModelGenerator() {
    }

    /**
     * Generates the output from the given list of specifications.
     */
    public void generateInto(File outputDirectory) throws Exception {
        Logger.info("Output directory: %s", outputDirectory.getAbsolutePath());

        // Create the base packages
        basePackage = new PackageModel();
        basePackage.setName("io.apicurio.datamodels");
        modelIndex.indexPackage(basePackage);

        coreModelPackage = new PackageModel();
        coreModelPackage.setName("io.apicurio.datamodels.core.models");
        modelIndex.indexPackage(coreModelPackage);

        // Create some common base classes
        nodeClass = new ClassModel();
        nodeClass.setName("Node");
        nodeClass.setPackage(coreModelPackage);
        nodeClass.setCore(true);
        coreModelPackage.getClasses().put(nodeClass.getName(), nodeClass);
        modelIndex.indexClass(nodeClass);

        extensibleNodeClass = new ClassModel();
        extensibleNodeClass.setName("ExtensibleNode");
        extensibleNodeClass.setPackage(coreModelPackage);
        extensibleNodeClass.setCore(true);
        coreModelPackage.getClasses().put(extensibleNodeClass.getName(), extensibleNodeClass);
        modelIndex.indexClass(extensibleNodeClass);

        documentClass = new ClassModel();
        documentClass.setName("Document");
        documentClass.setPackage(coreModelPackage);
        documentClass.setCore(true);
        documentClass.setIncludeAccept(true);
        coreModelPackage.getClasses().put(documentClass.getName(), documentClass);
        modelIndex.indexClass(documentClass);

        // Now process each specification to create their respective models (packages, classes, fields)
        getSpecifications().forEach(specification -> this.createModelsForSpecification(specification));

        // Normalize the models (this e.g. detects property commonalities across specifications and versions)
        this.normalizeModels();

        // Generate model classes
        this.generateModelClassFiles(outputDirectory);

        // Generate visitor interfaces

        // Generate traversers

        // Generate readers/writers


        // Debug output
        // TODO remove this
        if (Boolean.TRUE) {
            return;
        }
        System.out.println("---");
        modelIndex.findPackages("io.apicurio").forEach(pkg -> {
            System.out.println("Package: " + pkg.getName());
            pkg.getClasses().values().forEach(clss -> {
                System.out.println("    Class: " + clss.getName());
                clss.getFields().values().forEach(field -> {
                    System.out.println("        Field: " + field.getName() + " (" + field.getType() + ")");
                });
            });
        });
        System.out.println("---");

    }

    /**
     * Create the package, class, and field models for all elements of the provided specification.
     * Uses the existing basePackage as the default root node.
     * @param specification
     */
    private void createModelsForSpecification(Specification specification) {
        String specPackageName = specification.getPackage();
        PackageModel specPackage = this.mkpkgs(specPackageName);
        this.createClassModels(specification, specPackage);
    }

    /**
     * Creates the package for the given name, and also ensures that packages exist for all parents
     * up until the base/root package.
     * @param specPackageName
     */
    private PackageModel mkpkgs(String specPackageName) {
        PackageModel rval = new PackageModel();
        rval.setName(specPackageName);

        boolean done = false;
        PackageModel _package = rval;
        while (!done) {
            String packageName = _package.getName();
            String parentPackageName = this.parentpkg(packageName);
            PackageModel parentPackage = modelIndex.lookupPackage(parentPackageName, (_t) -> {
                PackageModel model = new PackageModel();
                model.setName(parentPackageName);
                return model;
            });
            parentPackage.getChildren().put(packageName, _package);
            _package.setParent(parentPackage);

            // Only done once we reach the base package
            done = parentPackage == basePackage;
            _package = parentPackage;
        }

        modelIndex.indexPackage(rval);
        return rval;
    }

    /**
     * Determine the parent package name from a package name.
     * @param packageName
     */
    private String parentpkg(String packageName) {
        int idx = packageName.lastIndexOf(".");
        return packageName.substring(0, idx);
    }

    /**
     * Creates class models for all of the entities in a specification.
     * @param specification
     * @param specPackage
     */
    private void createClassModels(Specification specification, PackageModel specPackage) {
        specification.getEntities().forEach(entity -> {
            ClassModel model = new ClassModel();
            model.setName(entity.getName());
            model.setPackage(specPackage);
            model.setAbstract(false);
            model.setIncludeAccept(true);
            if (entity.getExtensible() != null && entity.getExtensible()) {
                model.setParent(extensibleNodeClass);
            } else {
                model.setParent(nodeClass);
            }
            specPackage.getClasses().put(model.getName(), model);

            this.modelIndex.indexClass(model);
            this.createFieldModels(entity, model);
        });
    }

    /**
     * Creates a FieldModel for each property in the entity.
     * @param entity
     * @param model
     */
    private void createFieldModels(Entity entity, ClassModel model) {
        entity.getProperties().forEach(property -> {
            FieldModel field = new FieldModel();
            field.setName(property.getName());
            field.setType(property.getType());
            model.getFields().put(property.getName(), field);
        });
    }

    /**
     * Generate the class files for all data models.
     */
    private void generateModelClassFiles(File outputDirectory) {
        modelIndex.findClasses("").forEach(classModel -> {
            if (!classModel.isCore()) {
                ClassModelGenerator classModelGenerator = new ClassModelGenerator(specIndex, modelIndex, classModel);
                classModelGenerator.generateInto(outputDirectory);
            }
        });
    }

    /**
     * Find common base classes across specifications and versions of specifications.
     */
    private void normalizeModels() {
        // Process every class model we've created thus far
        Queue<ClassModel> modelsToProcess = new ConcurrentLinkedQueue<>();
        modelsToProcess.addAll(modelIndex.findClasses(""));
        Set<String> modelsProcessed = new HashSet<>();

        // Keep working until we've processed every model (including any new models we
        // might create during processing).
        while (!modelsToProcess.isEmpty()) {
            ClassModel classModel = modelsToProcess.remove();
            if (modelsProcessed.contains(classModel.getFullyQualifiedName())) {
                continue;
            }

            // Check if we need to create a parent class for this model in any parent scope
            PackageModel ancestorPackageModel = classModel.getPackage().getParent();
            while (ancestorPackageModel != null) {
                if (needsParentClass(ancestorPackageModel, classModel.getName())) {
                    ClassModel ancestorClass = new ClassModel();
                    ancestorClass.setName(classModel.getName());
                    ancestorClass.setParent(classModel.getParent());
                    ancestorClass.setPackage(ancestorPackageModel);
                    ancestorClass.setAbstract(true);
                    ancestorPackageModel.addClass(ancestorClass);
                    modelsToProcess.add(ancestorClass);
                    modelIndex.indexClass(ancestorClass);

                    Collection<ClassModel> childClasses = findChildClassesFor(ancestorClass);
                    // Make the new parent class the actual parent of each child class
                    childClasses.forEach(childClass -> {
                        childClass.setParent(ancestorClass);
                        // Skip processing this model if its turn comes up in the queue.
                        modelsProcessed.add(childClass.getFullyQualifiedName());
                    });
                    // break out of loop - no need to search further up the hierarchy
                    ancestorPackageModel = null;
                } else {
                    ancestorPackageModel = ancestorPackageModel.getParent();
                }
            }
        }

    }

    /**
     * A class needs a parent class if there are multiple classes with the same name in the
     * package hierarchy.
     * @param packageModel
     * @param className
     */
    private boolean needsParentClass(PackageModel packageModel, String className) {
        int count = 0;
        for (PackageModel childPackage : packageModel.getChildren().values()) {
            if (childPackage.containsClass(className)) {
                count++;
            }
        }
        return count > 1;
    }

    /**
     * Returns all classes with the same name as the given parent class.  Does a search of
     * the package tree to find such classes.
     * @param parentClass
     */
    private Collection<ClassModel> findChildClassesFor(ClassModel parentClass) {
        String className = parentClass.getName();
        PackageModel parentPackage = parentClass.getPackage();
        List<ClassModel> childClasses = new ArrayList<>();
        for (PackageModel packageModel : parentPackage.getChildren().values()) {
            ClassModel childClass = findChildClassIn(packageModel, className);
            if (childClass != null) {
                childClasses.add(childClass);
            }
        }
        return childClasses;
    }

    /**
     * Finds a child class in the given package with the given name.
     * @param packageModel
     * @param className
     */
    private ClassModel findChildClassIn(PackageModel packageModel, String className) {
        if (packageModel.getClasses().containsKey(className)) {
            return packageModel.getClasses().get(className);
        }
        for (PackageModel childPackage : packageModel.getChildren().values()) {
            ClassModel classModel = findChildClassIn(childPackage, className);
            if (classModel != null) {
                return classModel;
            }
        }
        return null;
    }

    /**
     * @return the specifications
     */
    public List<Specification> getSpecifications() {
        return specifications;
    }

    /**
     * @param specifications the specifications to set
     */
    public void setSpecifications(List<Specification> specifications) {
        this.specifications = specifications;
    }

}
