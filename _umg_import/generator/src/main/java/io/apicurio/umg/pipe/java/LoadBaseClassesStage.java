package io.apicurio.umg.pipe.java;

import java.io.IOException;
import java.net.URL;

import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.jboss.forge.roaster.model.source.JavaInterfaceSource;

import io.apicurio.umg.pipe.AbstractStage;

/**
 * Creates the i/o reader classes. There is a bespoke reader for each specification version.
 *
 * @author eric.wittmann@gmail.com
 */
public class LoadBaseClassesStage extends AbstractStage {

    @Override
    protected void doProcess() {
        try {
            loadBaseClasses(
                    "io.apicurio.umg.base.NodeImpl",
                    "io.apicurio.umg.base.RootNodeImpl",
                    "io.apicurio.umg.base.util.JsonUtil",
                    "io.apicurio.umg.base.util.ReaderUtil",
                    "io.apicurio.umg.base.util.WriterUtil",
                    "io.apicurio.umg.base.visitors.AbstractTraverser",
                    "io.apicurio.umg.base.visitors.TraversalStep",
                    "io.apicurio.umg.base.visitors.TraversalContextImpl",
                    "io.apicurio.umg.base.visitors.ReverseTraverser");
            loadBaseInterfaces(
                    "io.apicurio.umg.base.Node",
                    "io.apicurio.umg.base.MappedNode",
                    "io.apicurio.umg.base.RootNode",
                    "io.apicurio.umg.base.Visitable",
                    "io.apicurio.umg.base.visitors.Traverser",
                    "io.apicurio.umg.base.visitors.TraversalContext",
                    "io.apicurio.umg.base.visitors.TraversingVisitor",
                    "io.apicurio.umg.base.io.ModelReader",
                    "io.apicurio.umg.base.io.ModelWriter");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadBaseClasses(String... classes) throws IOException {
        for (String _class : classes) {
            debug("Including base class: " + _class);
            URL classSource = getBaseClassURL(_class);
            JavaClassSource source = Roaster.parse(JavaClassSource.class, classSource);
            String targetPackageName = source.getPackage().replace("io.apicurio.umg.base", getState().getConfig().getRootNamespace());
            source.setPackage(targetPackageName);
            source.getImports().forEach(_import -> {
                if (_import.getPackage().contains("io.apicurio.umg.base")) {
                    String newPackage = _import.getPackage().replace("io.apicurio.umg.base", getState().getConfig().getRootNamespace());
                    _import.setName(newPackage + "." + _import.getSimpleName());
                }
            });
            getState().getJavaIndex().index(source);
        }
    }

    private void loadBaseInterfaces(String... interfaces) throws IOException {
        for (String _interface : interfaces) {
            debug("Including base interface: " + _interface);
            URL interfaceSource = getBaseClassURL(_interface);
            JavaInterfaceSource source = Roaster.parse(JavaInterfaceSource.class, interfaceSource);
            String targetPackageName = source.getPackage().replace("io.apicurio.umg.base", getState().getConfig().getRootNamespace());
            source.setPackage(targetPackageName);
            source.getImports().forEach(_import -> {
                if (_import.getPackage().contains("io.apicurio.umg.base")) {
                    String newPackage = _import.getPackage().replace("io.apicurio.umg.base", getState().getConfig().getRootNamespace());
                    _import.setName(newPackage + "." + _import.getSimpleName());
                }
            });
            getState().getJavaIndex().index(source);
        }
    }

    private URL getBaseClassURL(String _class) {
        return getClass().getClassLoader().getResource("base/" + _class.replace('.', '/') + ".java");
    }

}
