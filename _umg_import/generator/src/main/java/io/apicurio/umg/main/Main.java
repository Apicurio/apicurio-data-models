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

import io.apicurio.umg.UnifiedModelGenerator2;
import io.apicurio.umg.beans.beans.Specification;
import io.apicurio.umg.io.SpecificationLoader;
import io.apicurio.umg.logging.Logger;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

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

        // Copy base src code to the output directory
//        File baseSrc = new File("src/main/base");
//        FileUtils.copyDirectory(baseSrc, outputDir);

        // Load the specs
        List<Specification> specs = loadSpecs();
        // Create a unified model generator
        UnifiedModelGenerator2 generator = UnifiedModelGenerator2.create(specs);
        // Generate the source code into the target output directory.
        generator.generateInto(outputDir);
        Logger.info("Model generated successfully!");
    }

    /**
     * Loads all the specification files (as resources).
     */
    private static List<Specification> loadSpecs() {
        Logger.info("Loading specifications.");
        List<Specification> specs = new LinkedList<>();
        specs.add(SpecificationLoader.loadSpec("specifications/openapi/openapi-2.0.x.yaml", Main.class.getClassLoader()));
        specs.add(SpecificationLoader.loadSpec("specifications/openapi/openapi-3.0.x.yaml", Main.class.getClassLoader()));
        specs.add(SpecificationLoader.loadSpec("specifications/openapi/openapi-3.1.x.yaml", Main.class.getClassLoader()));
        specs.add(SpecificationLoader.loadSpec("specifications/asyncapi/asyncapi-2.0.x.yaml", Main.class.getClassLoader()));
        return specs;
    }

}
