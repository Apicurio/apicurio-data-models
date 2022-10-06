package io.apicurio.umg.pipe;

import io.apicurio.umg.models.ClassModel;
import io.apicurio.umg.models.PackageModel;

public class PrepareStage implements Stage {

    @Override
    public void process(GenState state) {

        // Create the base packages
        var basePackage = PackageModel.builder().build();
        basePackage.setName("io.apicurio.datamodels");
        state.getIndex().indexPackage(basePackage);
        state.setBasePackage(basePackage);

        var coreModelPackage = PackageModel.builder().build();
        coreModelPackage.setName("io.apicurio.datamodels.core.models");
        state.getIndex().indexPackage(coreModelPackage);
        //state.setCoreModelPackage(coreModelPackage);

        // Create some common base classes
        var nodeClass = ClassModel.builder().build();
        nodeClass.setName("Node");
        nodeClass.setPackage(coreModelPackage);
        nodeClass.setCore(true);
        nodeClass.setAbstract(true);
        coreModelPackage.getClasses().put(nodeClass.getName(), nodeClass);
        state.getIndex().indexClass(nodeClass);
        state.setNodeClass(nodeClass);

        var extensibleNodeClass = ClassModel.builder().build();
        extensibleNodeClass.setName("ExtensibleNode");
        extensibleNodeClass.setPackage(coreModelPackage);
        extensibleNodeClass.setCore(true);
        extensibleNodeClass.setAbstract(true);
        coreModelPackage.getClasses().put(extensibleNodeClass.getName(), extensibleNodeClass);
        state.getIndex().indexClass(extensibleNodeClass);
        state.setExtensibleNodeClass(extensibleNodeClass);

        var documentClass = ClassModel.builder().build();
        documentClass.setName("Document");
        documentClass.setPackage(coreModelPackage);
        documentClass.setAbstract(true);
        documentClass.setCore(true);
        coreModelPackage.getClasses().put(documentClass.getName(), documentClass);
        state.getIndex().indexClass(documentClass);
        //state.setDocumentClass(documentClass);


    }
}
