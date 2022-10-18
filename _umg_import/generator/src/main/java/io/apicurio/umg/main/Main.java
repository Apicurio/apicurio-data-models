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

package io.apicurio.umg.main;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import io.apicurio.umg.UnifiedModelGenerator;
import io.apicurio.umg.io.SpecificationLoader;
import io.apicurio.umg.logging.Logger;
import io.apicurio.umg.models.spec.SpecificationModel;

/**
 * @author eric.wittmann@gmail.com
 */
public class Main {

    /**
     * Main entry point.  Runs the unified model generator.
     *
     * @param args
     */
    public static void main(String[] args) throws Exception {
        Logger.info("Starting Apicurio Unified Model Generator");
        // Clean the output directory
        File outputDir = new File("target/apicurio-data-models/src/main/java");
        if (outputDir.isDirectory()) {
            FileUtils.deleteDirectory(outputDir);
            outputDir.mkdirs();
        }

        // Load the specs
        List<SpecificationModel> specs = loadSpecs();
        // Create a unified model generator
        UnifiedModelGenerator generator = UnifiedModelGenerator.create(specs);
        // Generate the source code into the target output directory.
        generator.generateInto(outputDir);
        Logger.info("Model generated successfully!");
    }

    /**
     * Loads all the specification files (as resources).
     */
    private static List<SpecificationModel> loadSpecs() {
        Logger.info("Loading specifications.");
        List<SpecificationModel> specs = new LinkedList<>();
        specs.add(SpecificationLoader.loadSpec("specifications/asyncapi.yaml", Main.class.getClassLoader()));
        specs.add(SpecificationLoader.loadSpec("specifications/openapi.yaml", Main.class.getClassLoader()));
        specs.add(SpecificationLoader.loadSpec("specifications/json-schema.yaml", Main.class.getClassLoader()));
        return specs;
    }

}
