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

import java.util.Collection;

import io.apicurio.umg.logging.Logger;
import io.apicurio.umg.models.spec.SpecificationModel;
import io.apicurio.umg.pipe.GeneratorState;
import io.apicurio.umg.pipe.Pipeline;
import io.apicurio.umg.pipe.concept.CreateEntityModelsStage;
import io.apicurio.umg.pipe.concept.CreateNamespaceModelsStage;
import io.apicurio.umg.pipe.concept.CreateParentTraitsStage;
import io.apicurio.umg.pipe.concept.CreatePropertyModelsStage;
import io.apicurio.umg.pipe.concept.CreateTraitModelsStage;
import io.apicurio.umg.pipe.concept.CreateVisitorsStage;
import io.apicurio.umg.pipe.concept.IndexSpecificationsStage;
import io.apicurio.umg.pipe.concept.NormalizeEntitiesStage;
import io.apicurio.umg.pipe.concept.NormalizePropertiesStage;
import io.apicurio.umg.pipe.concept.NormalizeTraitsStage;
import io.apicurio.umg.pipe.concept.NormalizeVisitorsStage;
import io.apicurio.umg.pipe.concept.RemoveTransparentTraitsStage;
import io.apicurio.umg.pipe.java.AddPrefixes;
import io.apicurio.umg.pipe.java.CreateReadersStage;
import io.apicurio.umg.pipe.java.CreateVisitorAdaptersStage;
import io.apicurio.umg.pipe.java.CreateVisitorInterfacesStage;
import io.apicurio.umg.pipe.java.CreateWritersStage;
import io.apicurio.umg.pipe.java.JavaAddImplementsStage;
import io.apicurio.umg.pipe.java.JavaClassStage;
import io.apicurio.umg.pipe.java.JavaFieldStage;
import io.apicurio.umg.pipe.java.JavaGetterStage;
import io.apicurio.umg.pipe.java.JavaSetterStage;
import io.apicurio.umg.pipe.java.JavaSuperTypesStage;
import io.apicurio.umg.pipe.java.JavaWriteStage;
import io.apicurio.umg.pipe.java.LoadBaseClassesStage;
import io.apicurio.umg.pipe.java.ResolveFieldSourceTypes;
import io.apicurio.umg.pipe.java.ResolveFieldTypes;
import io.apicurio.umg.pipe.java.TodoStage;
import io.apicurio.umg.pipe.java.TransformComplexTypes;
import io.apicurio.umg.pipe.java.TransformConceptToJavaModelStage;
import io.apicurio.umg.pipe.java.TransformInheritance;
import io.apicurio.umg.pipe.java.TransformToInterfaces;

/**
 * @author eric.wittmann@gmail.com
 */
public class UnifiedModelGenerator {

    private final UnifiedModelGeneratorConfig config;
    private final Collection<SpecificationModel> specifications;

    /**
     * Constructor.
     * @param config
     * @param specifications
     */
    public UnifiedModelGenerator(UnifiedModelGeneratorConfig config, Collection<SpecificationModel> specifications) {
        this.config = config;
        this.specifications = specifications;
    }

    /**
     * Generates the output from the given list of specifications.
     */
    public void generate() throws Exception {
        Logger.info("Output directory: %s", config.getOutputDirectory().getAbsolutePath());

        GeneratorState state = new GeneratorState();
        state.setSpecifications(specifications);
        state.setConfig(config);
        Pipeline pipe = new Pipeline();

        // Index phase
        pipe.addStage(new IndexSpecificationsStage());

        // Model creation phase
        pipe.addStage(new CreateNamespaceModelsStage());
        pipe.addStage(new CreateTraitModelsStage());
        pipe.addStage(new CreateEntityModelsStage());
        pipe.addStage(new CreatePropertyModelsStage());
        pipe.addStage(new CreateParentTraitsStage());
        pipe.addStage(new CreateVisitorsStage());

        // Model optimization phase
        pipe.addStage(new RemoveTransparentTraitsStage());
        pipe.addStage(new NormalizeTraitsStage());
        pipe.addStage(new NormalizeEntitiesStage());
        pipe.addStage(new NormalizePropertiesStage());
        pipe.addStage(new NormalizeVisitorsStage());

        // Debug the models
        //pipe.addStage(new DebugStage());

        // === Java-specific stages

        // Working with Java-specific models
        pipe.addStage(new TransformConceptToJavaModelStage());
        pipe.addStage(new TransformInheritance());
        pipe.addStage(new TransformToInterfaces());
        pipe.addStage(new TodoStage());
        pipe.addStage(new TransformComplexTypes());
        pipe.addStage(new ResolveFieldTypes());
        pipe.addStage(new AddPrefixes());
        pipe.addStage(new LoadBaseClassesStage());

        // Working with Roaster
        pipe.addStage(new ResolveFieldSourceTypes());
        pipe.addStage(new JavaClassStage());
        pipe.addStage(new JavaAddImplementsStage());
        pipe.addStage(new JavaSuperTypesStage());
        pipe.addStage(new JavaFieldStage());
        pipe.addStage(new JavaGetterStage());
        pipe.addStage(new JavaSetterStage());
        pipe.addStage(new CreateReadersStage());
        pipe.addStage(new CreateWritersStage());
        pipe.addStage(new CreateVisitorInterfacesStage());
        pipe.addStage(new CreateVisitorAdaptersStage());
        pipe.addStage(new JavaWriteStage(config.getOutputDirectory()));

        pipe.run(state);
    }
}
