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
import java.net.URL;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import io.apicurio.umg.UnifiedModelGenerator;
import io.apicurio.umg.logging.Logger;
import io.apicurio.umg.spec.Specification;

/**
 * @author eric.wittmann@gmail.com
 */
public class Main {

    private static final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

    /**
     * Main entry point.  Runs the unified model generator.
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
        File baseSrc = new File("src/main/base");
        FileUtils.copyDirectory(baseSrc, outputDir);

        List<Specification> specs = loadSpecs();
        UnifiedModelGenerator generator = UnifiedModelGenerator.create(specs);
        generator.generateInto(outputDir);
        Logger.info("Model generated successfully!");
    }

    private static List<Specification> loadSpecs() {
        Logger.info("Loading specifications.");
        List<Specification> specs = new LinkedList<>();
        specs.add(loadSpec("specifications/openapi/openapi-2.0.x.yaml"));
        specs.add(loadSpec("specifications/openapi/openapi-3.0.x.yaml"));
        specs.add(loadSpec("specifications/openapi/openapi-3.1.x.yaml"));
        specs.add(loadSpec("specifications/asyncapi/asyncapi-2.0.x.yaml"));
        return specs;
    }

    private static Specification loadSpec(String specPath) {
        Logger.info("Loading specification from: %s", specPath);
        try {
            URL resource = Main.class.getClassLoader().getResource(specPath);
            if (resource == null) {
                throw new NullPointerException("Specification not found: " + specPath);
            }
            Specification spec = mapper.readValue(resource, Specification.class);
            Logger.info("Specification '%s' loaded with %d entities.", spec.getName(), 
                    Optional.ofNullable(spec.getEntities()).orElse(Collections.emptyList()).size());
            return spec;
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
    }
    
}
