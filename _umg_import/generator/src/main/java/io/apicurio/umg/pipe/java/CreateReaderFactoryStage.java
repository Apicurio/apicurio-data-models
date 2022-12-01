package io.apicurio.umg.pipe.java;

import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.jboss.forge.roaster.model.source.JavaEnumSource;
import org.jboss.forge.roaster.model.source.JavaInterfaceSource;
import org.jboss.forge.roaster.model.source.MethodSource;

import io.apicurio.umg.pipe.java.method.BodyBuilder;

/**
 * Creates a reader factory that knows how to create the correct reader for
 * a given model type.
 *
 * @author eric.wittmann@gmail.com
 */
public class CreateReaderFactoryStage extends AbstractJavaStage {

    @Override
    protected void doProcess() {
        String readerFactoryPackageName = getState().getConfig().getRootNamespace() + ".io";
        String readerFactoryClassName = "ReaderFactory";

        // Create java source code for the reader
        JavaClassSource readerClassSource = Roaster.create(JavaClassSource.class)
                .setPackage(readerFactoryPackageName)
                .setName(readerFactoryClassName)
                .setPublic();

        // Some imports (model type, model reader interface, etc)
        JavaEnumSource modelTypeSource = getState().getJavaIndex().lookupEnum(getModelTypeEnumFQN());
        readerClassSource.addImport(modelTypeSource);
        JavaInterfaceSource modelReaderSource = getState().getJavaIndex().lookupInterface(getModelReaderInterfaceFQN());
        readerClassSource.addImport(modelReaderSource);

        // Create the factory method.
        MethodSource<JavaClassSource> factoryMethodSource = readerClassSource.addMethod().setName("createModelReader").setPublic();
        factoryMethodSource.setReturnType(modelReaderSource);
        factoryMethodSource.addParameter(modelTypeSource.getName(), "modelType");

        BodyBuilder body = new BodyBuilder();
        body.append("ModelReader reader = null;");
        body.append("switch (modelType) {");
        getState().getSpecIndex().getAllSpecificationVersions().forEach(specVersion -> {
            String specModelReaderFQN = getReaderPackageName(specVersion) + "." + getReaderClassName(specVersion);
            JavaClassSource specModelReaderSource = getState().getJavaIndex().lookupClass(specModelReaderFQN);
            readerClassSource.addImport(specModelReaderSource);

            String modelTypeValue = prefixToModelType(specVersion.getPrefix());
            body.addContext("modelTypeValue", modelTypeValue);
            body.addContext("modelReaderClassName", specModelReaderSource.getName());

            body.append("    case ${modelTypeValue}:");
            body.append("        reader = new ${modelReaderClassName}();");
            body.append("        break;");
        });
        body.append("}");
        body.append("return reader;");
        factoryMethodSource.setBody(body.toString());

        getState().getJavaIndex().index(readerClassSource);
    }
}
