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

import io.apicurio.umg.beans.beans.Specification;
import io.apicurio.umg.logging.Logger;
import io.apicurio.umg.pipe.CreateClassModelsStage;
import io.apicurio.umg.pipe.CreateFieldModelsStage;
import io.apicurio.umg.pipe.DebugStage;
import io.apicurio.umg.pipe.ExpandPackageModelsStage;
import io.apicurio.umg.pipe.GenState;
import io.apicurio.umg.pipe.IncludeFieldsModelsStage;
import io.apicurio.umg.pipe.IndexEntityModelsStage;
import io.apicurio.umg.pipe.NormalizeFieldsStage;
import io.apicurio.umg.pipe.NormalizeModelsStage;
import io.apicurio.umg.pipe.Pipeline;
import io.apicurio.umg.pipe.PrepareJsonSchemaStage;
import io.apicurio.umg.pipe.ProcessExtendsStage;
import io.apicurio.umg.pipe.RemoveTransparentClassModelsStage;
import io.apicurio.umg.pipe.UnionTypeMapperStage;
import io.apicurio.umg.pipe.java.JavaAddImplementsStage;
import io.apicurio.umg.pipe.java.JavaClassStage;
import io.apicurio.umg.pipe.java.JavaFieldStage;
import io.apicurio.umg.pipe.java.JavaGetterStage;
import io.apicurio.umg.pipe.java.JavaSetterStage;
import io.apicurio.umg.pipe.java.JavaSuperTypesStage;
import io.apicurio.umg.pipe.java.JavaWriteStage;

import java.io.File;
import java.util.List;

/**
 * @author eric.wittmann@gmail.com
 */
public class UnifiedModelGenerator2 {

    private List<Specification> specifications;

    public static UnifiedModelGenerator2 create(List<Specification> specifications) {
        UnifiedModelGenerator2 generator = new UnifiedModelGenerator2(specifications);
        return generator;
    }

    private UnifiedModelGenerator2(List<Specification> specifications) {
        this.specifications = specifications;
    }

    /**
     * Generates the output from the given list of specifications.
     */
    public void generateInto(File outputDirectory) throws Exception {
        Logger.info("Output directory: %s", outputDirectory.getAbsolutePath());

        var state = new GenState();
        state.setSpecifications(specifications);
        var pipe = new Pipeline();
        pipe.addStage(new IndexEntityModelsStage());
        pipe.addStage(new PrepareJsonSchemaStage());

        pipe.addStage(new CreateClassModelsStage());
        pipe.addStage(new ProcessExtendsStage());
        pipe.addStage(new ExpandPackageModelsStage());

        pipe.addStage(new IncludeFieldsModelsStage());
        pipe.addStage(new CreateFieldModelsStage());

        pipe.addStage(new UnionTypeMapperStage());

        pipe.addStage(new RemoveTransparentClassModelsStage());

        pipe.addStage(new NormalizeModelsStage());
        pipe.addStage(new NormalizeFieldsStage());

        pipe.addStage(new JavaClassStage());
        pipe.addStage(new JavaAddImplementsStage());
        pipe.addStage(new JavaSuperTypesStage());
        pipe.addStage(new JavaFieldStage());
        pipe.addStage(new JavaGetterStage());
        pipe.addStage(new JavaSetterStage());
        pipe.addStage(new JavaWriteStage(outputDirectory));

        pipe.addStage(new DebugStage());
        pipe.run(state);
    }
}
