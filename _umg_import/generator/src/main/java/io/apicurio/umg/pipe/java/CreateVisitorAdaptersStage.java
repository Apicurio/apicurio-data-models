package io.apicurio.umg.pipe.java;

import java.util.ArrayList;
import java.util.List;

import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.jboss.forge.roaster.model.source.MethodSource;
import org.jboss.forge.roaster.model.source.ParameterSource;

import io.apicurio.umg.logging.Logger;
import io.apicurio.umg.models.concept.VisitorModel;
import io.apicurio.umg.models.java.JavaClassModel;
import io.apicurio.umg.models.java.JavaInterfaceModel;
import io.apicurio.umg.models.java.JavaPackageModel;
import io.apicurio.umg.pipe.AbstractStage;

/**
 * Creates an adapter for each visitor interface.  An adapter is just a class that implements
 * the visitor interface with empty implementations for each visit method.
 * @author eric.wittmann@gmail.com
 */
public class CreateVisitorAdaptersStage extends AbstractStage {

    @Override
    protected void doProcess() {
        getState().getConceptIndex().findVisitors("").stream().filter(v -> v.getChildren().isEmpty()).forEach(visitor -> {
            createVisitorAdapter(visitor);
        });
    }

    private void createVisitorAdapter(VisitorModel visitor) {
        String visitorPackageName = visitor.getNamespace().fullName() + ".visitors";
        String visitorPrefix = (visitor.getParent() == null) ? null : getState().getSpecIndex().prefixForNS(visitor.getNamespace().fullName());
        String visitorInterfaceName = (visitorPrefix != null ? visitorPrefix : "") + "Visitor";
        String visitorAdapterName = visitorInterfaceName + "Adapter";
        Logger.debug("Creating visitor adapter: " + visitorAdapterName);

        // Lookup the package for the NS
        JavaPackageModel visitorPackage = getState().getJavaIndex().lookupAndIndexPackage(() -> {
            JavaPackageModel parentPackage = getState().getJavaIndex().lookupPackage(visitor.getNamespace().fullName());
            JavaPackageModel packageModel = JavaPackageModel.builder()
                    .name(visitorPackageName)
                    .parent(parentPackage)
                    .build();
            return packageModel;
        });

        // Lookup the visitor interface
        String visitorInterfaceFQN = visitorPackageName + "." + visitorInterfaceName;
        JavaInterfaceModel visitorInterface = getState().getJavaIndex().lookupInterface(visitorInterfaceFQN);
        if (visitorInterface == null) {
            Logger.warn("[CreateVisitorAdaptersStage] Visitor interface not found: " + visitorInterfaceFQN);
        }

        // Create the visitor adapter class
        JavaClassModel visitorAdapterClass= JavaClassModel.builder()
                ._package(visitorPackage)
                .name(visitorAdapterName)
                .build();
        visitorAdapterClass.get_implements().add(visitorInterface);

        // Create java source code for the visitor adapter
        JavaClassSource visitorAdapterSource = Roaster.create(JavaClassSource.class)
                .setPackage(visitorPackageName)
                .setName(visitorAdapterClass.getName())
                .setPublic();
        visitorAdapterSource.implementInterface(visitorInterface.getInterfaceSource());
        visitorAdapterSource.getJavaDoc().setText(
                "Visitor adapter for the '" + visitorInterface.getName() + "' visitor.  This provides empty implementations of every visit method in the visitor.  Useful when you only need to visit a few types of nodes in a data model.");
        visitorAdapterClass.setClassSource(visitorAdapterSource);

        List<MethodSource<?>> allMethods = findAllMethods(visitor);
        allMethods.forEach(method -> {
            MethodSource<JavaClassSource> methodSource = visitorAdapterSource.addMethod()
                    .setName(method.getName())
                    .setReturnTypeVoid()
                    .setPublic();
            // We know each visit method will have a single parameter.
            ParameterSource<?> param = method.getParameters().get(0);
            visitorAdapterSource.addImport(param.getType());
            methodSource.addParameter(param.getType().getName(), param.getName());
            methodSource.setBody("");
        });

        // Index the new class
        getState().getJavaIndex().addClass(visitorAdapterClass);
    }

    /**
     * Finds all visitXyz() methods defined for the visitor for the given model.  This
     * is done by walking up the hierarchy of visitors, looking up the generated Java
     * interface for each one, and collecting the methods defined on each visitor.
     * @param visitor
     */
    private List<MethodSource<?>> findAllMethods(VisitorModel visitor) {
        List<MethodSource<?>> rval = new ArrayList<>();
        VisitorModel viz = visitor;
        while (viz != null) {
            String visitorPackageName = viz.getNamespace().fullName() + ".visitors";
            String visitorPrefix = (viz.getParent() == null) ? null : getState().getSpecIndex().prefixForNS(viz.getNamespace().fullName());
            String visitorInterfaceName = (visitorPrefix != null ? visitorPrefix : "") + "Visitor";
            String visitorInterfaceFQN = visitorPackageName + "." + visitorInterfaceName;

            JavaInterfaceModel visitorInterface = getState().getJavaIndex().lookupInterface(visitorInterfaceFQN);
            if (visitorInterface == null) {
                Logger.warn("Visitor java interface not found for: " + visitorInterfaceFQN);
            }
            rval.addAll(visitorInterface.getInterfaceSource().getMethods());

            viz = viz.getParent();
        }
        return rval;
    }

}
