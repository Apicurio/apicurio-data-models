package io.apicurio.umg.pipe.java;

import java.io.IOException;
import java.net.URL;

import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.JavaType;
import org.jboss.forge.roaster.model.source.Importer;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.jboss.forge.roaster.model.source.JavaEnumSource;
import org.jboss.forge.roaster.model.source.JavaInterfaceSource;
import org.jboss.forge.roaster.model.source.PackagedSource;

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
            loadBaseEnums(
                    "io.apicurio.umg.base.visitors.TraversalStepType"
                    );
            loadBaseClasses(
                    "io.apicurio.umg.base.NodeImpl",
                    "io.apicurio.umg.base.RootNodeImpl",
                    "io.apicurio.umg.base.util.JsonUtil",
                    "io.apicurio.umg.base.util.ReaderUtil",
                    "io.apicurio.umg.base.util.WriterUtil",
                    "io.apicurio.umg.base.visitors.AbstractTraverser",
                    "io.apicurio.umg.base.visitors.TraversalStep",
                    "io.apicurio.umg.base.visitors.TraversalContextImpl",
                    "io.apicurio.umg.base.visitors.ReverseTraverser",
                    "io.apicurio.umg.base.union.BooleanUnionValueImpl",
                    "io.apicurio.umg.base.union.ListUnionValueImpl",
                    "io.apicurio.umg.base.union.MapUnionValueImpl",
                    "io.apicurio.umg.base.union.EntityListUnionValueImpl",
                    "io.apicurio.umg.base.union.EntityMapUnionValueImpl",
                    "io.apicurio.umg.base.union.PrimitiveUnionValueImpl",
                    "io.apicurio.umg.base.union.StringListUnionValueImpl",
                    "io.apicurio.umg.base.union.StringUnionValueImpl",
                    "io.apicurio.umg.base.union.UnionValueImpl"
                    );
            loadBaseInterfaces(
                    "io.apicurio.umg.base.Node",
                    "io.apicurio.umg.base.MappedNode",
                    "io.apicurio.umg.base.RootNode",
                    "io.apicurio.umg.base.Visitable",
                    "io.apicurio.umg.base.visitors.Traverser",
                    "io.apicurio.umg.base.visitors.TraversalContext",
                    "io.apicurio.umg.base.visitors.TraversingVisitor",
                    "io.apicurio.umg.base.io.ModelReader",
                    "io.apicurio.umg.base.io.ModelWriter",
                    "io.apicurio.umg.base.union.BooleanUnionValue",
                    "io.apicurio.umg.base.union.EntityListUnionValue",
                    "io.apicurio.umg.base.union.EntityMapUnionValue",
                    "io.apicurio.umg.base.union.ListUnionValue",
                    "io.apicurio.umg.base.union.MapUnionValue",
                    "io.apicurio.umg.base.union.PrimitiveUnionValue",
                    "io.apicurio.umg.base.union.StringListUnionValue",
                    "io.apicurio.umg.base.union.StringUnionValue",
                    "io.apicurio.umg.base.union.Union",
                    "io.apicurio.umg.base.union.UnionValue"
                    );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadBaseEnums(String... enums) throws IOException {
        loadBaseSources(JavaEnumSource.class, enums);
    }

    private void loadBaseClasses(String... classes) throws IOException {
        loadBaseSources(JavaClassSource.class, classes);
    }

    private void loadBaseInterfaces(String... interfaces) throws IOException {
        loadBaseSources(JavaInterfaceSource.class, interfaces);
    }

    private <T extends JavaType<?>> void loadBaseSources(final Class<T> type, String... sources) throws IOException {
        for (String _source : sources) {
            debug("Including base source: " + _source);
            URL sourceUrl = getBaseSourceURL(_source);
            T source = Roaster.parse(type, sourceUrl);
            String targetPackageName = source.getPackage().replace("io.apicurio.umg.base", getState().getConfig().getRootNamespace());
            ((PackagedSource<?>) source).setPackage(targetPackageName);
            ((Importer<?>) source).getImports().forEach(_import -> {
                if (_import.getPackage().contains("io.apicurio.umg.base")) {
                    String newPackage = _import.getPackage().replace("io.apicurio.umg.base", getState().getConfig().getRootNamespace());
                    _import.setName(newPackage + "." + _import.getSimpleName());
                }
            });
            if (type.equals(JavaClassSource.class)) {
                getState().getJavaIndex().index((JavaClassSource) source);
            } else if (type.equals(JavaInterfaceSource.class)) {
                getState().getJavaIndex().index((JavaInterfaceSource) source);
            } else if (type.equals(JavaEnumSource.class)) {
                getState().getJavaIndex().index((JavaEnumSource) source);
            }
        }
    }

    private URL getBaseSourceURL(String _class) {
        return getClass().getClassLoader().getResource("base/" + _class.replace('.', '/') + ".java");
    }

}
