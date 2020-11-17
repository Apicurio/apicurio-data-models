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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.apicurio.umg.index.SpecificationIndex;
import io.apicurio.umg.logging.Logger;
import io.apicurio.umg.spec.Specification;

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
    private SpecificationIndex index;

    private Map<String, UnifiedModel> models = new HashMap<>();

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
        
        // Index the specifications for easy lookup when needed
        index = new SpecificationIndex();
        getSpecifications().forEach(specification -> index.index(specification));

        // Build the set of unified models from the entities described in the specifications
        this.buildUnifiedModels();
        
        // Normalize the models (this e.g. detects property commonalities across specversions)
        this.normalizeUnifiedModels();

        // Generate unified model interfaces
        this.generateModelInterfaces(outputDirectory);
        
        // Generate model impl classes
        
        // Generate visitor interfaces
        
    }

    /**
     * Analyze the specifications to produce a collection of all entities across all versions of every
     * specification.  If an entity exists in multiple specifications, this phase is responsible for 
     * figuring that out and including it only once in the collection.
     */
    private void buildUnifiedModels() {
        this.specifications.forEach(spec -> {
            if (spec.getEntities() != null) {
                spec.getEntities().forEach(entity -> {
                    Logger.info("  Analyzing entity %s :: %s", spec.getName(), entity.getName());
                    if (!this.models.containsKey(entity.getName())) {
                        UnifiedModel modelEntity = UnifiedModel.create(entity, spec);
                        this.models.put(entity.getName(), modelEntity);
                    } else {
                        this.models.get(entity.getName()).merge(entity, spec);
                    }
                });
            }
        });
    }

    /**
     * Normalizes the models.
     */
    private void normalizeUnifiedModels() {
        this.models.values().forEach(model -> model.normalize());
    }

    /**
     * Generates the model interfaces.  To do this we go through all of the model entities we've 
     * built and generate appropriate interfaces.  Depending on the entity, multiple interfaces
     * may be created.
     * @param outputDirectory 
     */
    private void generateModelInterfaces(File outputDirectory) {
        this.models.values().forEach(entity -> {
            ModelInterfaceGenerator miGenerator = new ModelInterfaceGenerator(index, entity);
            miGenerator.generateInto(outputDirectory);
        });
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
