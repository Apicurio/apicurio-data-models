package io.apicurio.umg.pipe;

import io.apicurio.umg.models.ClassModel;
import io.apicurio.umg.models.PackageModel;

public class PrepareJsonSchemaStage extends AbstractStage {

	@Override
    protected void doProcess() {
        var basePackage = PackageModel.builder()
                .name("io.apicurio.datamodels")
                .build();
        getState().getIndex().indexPackage(basePackage);

        getState().setBasePackage(basePackage);

        var coreModelPackage = PackageModel.builder()
                .name("io.apicurio.datamodels.core.models")
                .build();
        getState().getIndex().indexPackage(coreModelPackage);

        // Create some common base classes
        var nodeClass = ClassModel.builder()
                .name("JsonSchemaNode")
                ._package(coreModelPackage)
                .core(true)
                ._abstract(true)
                .build();
        coreModelPackage.getClasses().put(nodeClass.getName(), nodeClass);
        getState().getIndex().indexClass(nodeClass);

        getState().setNodeClass(nodeClass);
        getState().getJavaTypeResolver().registerType("entity", nodeClass);

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
        getState().getIndex().indexClass(jsonNodeClass);

        getState().getJavaTypeResolver().registerType("any", jsonNodeClass);

        getState().setNodeClass(null);
    }
}
