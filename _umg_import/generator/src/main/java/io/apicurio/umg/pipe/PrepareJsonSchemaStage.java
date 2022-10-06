package io.apicurio.umg.pipe;

import io.apicurio.umg.models.ClassModel;
import io.apicurio.umg.models.PackageModel;

public class PrepareJsonSchemaStage implements Stage {

    @Override
    public void process(GenState state) {

        var basePackage = PackageModel.builder()
                .name("io.apicurio.datamodels")
                .build();
        state.getIndex().indexPackage(basePackage);

        state.setBasePackage(basePackage);

        var coreModelPackage = PackageModel.builder()
                .name("io.apicurio.datamodels.core.models")
                .build();
        state.getIndex().indexPackage(coreModelPackage);

        // Create some common base classes
        var nodeClass = ClassModel.builder()
                .name("JsonSchemaNode")
                ._package(coreModelPackage)
                .core(true)
                ._abstract(true)
                .build();
        coreModelPackage.getClasses().put(nodeClass.getName(), nodeClass);
        state.getIndex().indexClass(nodeClass);

        state.setNodeClass(nodeClass);
        state.getJavaTypeResolver().registerType("entity", nodeClass);

        var jsonNodePackage = PackageModel.builder()
                .name("com.fasterxml.jackson.databind")
                .build();

        var jsonNodeClass = ClassModel.builder()
                .name("JsonNode")
                ._package(jsonNodePackage)
                .core(true)
                ._abstract(false)
                .build();

        jsonNodePackage.getClasses().put(jsonNodeClass.getName(), jsonNodeClass);
        state.getIndex().indexClass(jsonNodeClass);

        state.getJavaTypeResolver().registerType("any", jsonNodeClass);

        state.setNodeClass(null);
    }
}
