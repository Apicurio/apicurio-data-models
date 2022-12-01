package io.apicurio.umg.pipe.java;

import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.jboss.forge.roaster.model.source.JavaEnumSource;
import org.jboss.forge.roaster.model.source.JavaInterfaceSource;
import org.jboss.forge.roaster.model.source.MethodSource;

import io.apicurio.umg.pipe.java.method.BodyBuilder;

/**
 * Creates a writer factory that knows how to create the correct writer for
 * a given model type.
 *
 * @author eric.wittmann@gmail.com
 */
public class CreateWriterFactoryStage extends AbstractJavaStage {

    @Override
    protected void doProcess() {
        String writerFactoryPackageName = getState().getConfig().getRootNamespace() + ".io";
        String writerFactoryClassName = "WriterFactory";

        // Create java source code for the writer
        JavaClassSource writerClassSource = Roaster.create(JavaClassSource.class)
                .setPackage(writerFactoryPackageName)
                .setName(writerFactoryClassName)
                .setPublic();

        // Some imports (model type, model writer interface, etc)
        JavaEnumSource modelTypeSource = getState().getJavaIndex().lookupEnum(getModelTypeEnumFQN());
        writerClassSource.addImport(modelTypeSource);
        JavaInterfaceSource modelWriterSource = getState().getJavaIndex().lookupInterface(getModelWriterInterfaceFQN());
        writerClassSource.addImport(modelWriterSource);

        // Create the factory method.
        MethodSource<JavaClassSource> factoryMethodSource = writerClassSource.addMethod().setName("createModelWriter").setPublic();
        factoryMethodSource.setReturnType(modelWriterSource);
        factoryMethodSource.addParameter(modelTypeSource.getName(), "modelType");

        BodyBuilder body = new BodyBuilder();
        body.append("ModelWriter writer = null;");
        body.append("switch (modelType) {");
        getState().getSpecIndex().getAllSpecificationVersions().forEach(specVersion -> {
            String specModelWriterFQN = getWriterPackageName(specVersion) + "." + getWriterClassName(specVersion);
            JavaClassSource specModelWriterSource = getState().getJavaIndex().lookupClass(specModelWriterFQN);
            writerClassSource.addImport(specModelWriterSource);

            String modelTypeValue = prefixToModelType(specVersion.getPrefix());
            body.addContext("modelTypeValue", modelTypeValue);
            body.addContext("modelWriterClassName", specModelWriterSource.getName());

            body.append("    case ${modelTypeValue}:");
            body.append("        writer = new ${modelWriterClassName}();");
            body.append("        break;");
        });
        body.append("}");
        body.append("return writer;");
        factoryMethodSource.setBody(body.toString());

        getState().getJavaIndex().index(writerClassSource);
    }
}
