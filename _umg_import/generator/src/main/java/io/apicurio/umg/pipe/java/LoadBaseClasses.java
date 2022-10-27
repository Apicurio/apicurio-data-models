package io.apicurio.umg.pipe.java;

import java.io.IOException;
import java.net.URL;

import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.jboss.forge.roaster.model.source.JavaInterfaceSource;

import io.apicurio.umg.logging.Logger;
import io.apicurio.umg.models.java.JavaClassModel;
import io.apicurio.umg.models.java.JavaInterfaceModel;
import io.apicurio.umg.models.java.JavaPackageModel;
import io.apicurio.umg.pipe.AbstractStage;

/**
 * Creates the i/o reader classes. There is a bespoke reader for each specification version.
 *
 * @author eric.wittmann@gmail.com
 */
public class LoadBaseClasses extends AbstractStage {

    @Override
    protected void doProcess() {
        try {
            loadBaseClasses("io.apicurio.umg.base.util.JsonUtil", "io.apicurio.umg.base.NodeImpl");
            loadBaseInterfaces("io.apicurio.umg.base.Node", "io.apicurio.umg.base.Visitor",
                    "io.apicurio.umg.base.Visitable");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadBaseClasses(String... classes) throws IOException {
        for (String _class : classes) {
            Logger.debug("Including base class: " + _class);
            URL classSource = getBaseClassURL(_class);
            JavaClassSource source = Roaster.parse(JavaClassSource.class, classSource);
            String targetPackageName = source.getPackage().replace("io.apicurio.umg.base", getState().getConfig().getRootNamespace());
            JavaPackageModel targetPackage = getState().getJavaIndex().lookupAndIndexPackage(() -> {
                return JavaPackageModel.builder().name(targetPackageName).build();
            });
            source.setPackage(targetPackageName);
            JavaClassModel classModel = JavaClassModel.builder()._package(targetPackage)
                    .name(source.getName()).classSource(source).external(true).build();
            getState().getJavaIndex().addClass(classModel);
        }
    }

    private void loadBaseInterfaces(String... interfaces) throws IOException {
        for (String _interface : interfaces) {
            Logger.debug("Including base interface: " + _interface);
            URL interfaceSource = getBaseClassURL(_interface);
            JavaInterfaceSource source = Roaster.parse(JavaInterfaceSource.class, interfaceSource);
            String targetPackageName = source.getPackage().replace("io.apicurio.umg.base", getState().getConfig().getRootNamespace());
            JavaPackageModel targetPackage = getState().getJavaIndex().lookupAndIndexPackage(() -> {
                return JavaPackageModel.builder().name(targetPackageName).build();
            });
            source.setPackage(targetPackageName);
            JavaInterfaceModel interfaceModel = JavaInterfaceModel.builder()._package(targetPackage)
                    .name(source.getName()).interfaceSource(source).external(true).build();
            getState().getJavaIndex().addInterface(interfaceModel);
        }
    }

    private URL getBaseClassURL(String _class) {
        return getClass().getClassLoader().getResource("base/" + _class.replace('.', '/') + ".java");
    }

}
