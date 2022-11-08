package io.apicurio.umg.pipe.java;

import java.util.Set;

import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.JavaInterfaceSource;

import io.apicurio.umg.logging.Logger;
import io.apicurio.umg.models.concept.VisitorModel;
import io.apicurio.umg.models.java.JavaInterfaceModel;
import io.apicurio.umg.models.java.JavaPackageModel;

/**
 * Creates combined visitor interfaces.  We create a combined visitor interface for the following:
 * <ul>
 *   <li>One for each specification</li>
 *   <li>One for the root</li>
 * </ul>
 *
 * @author eric.wittmann@gmail.com
 */
public class CreateCombinedVisitorInterfacesStage extends AbstractVisitorStage {

    @Override
    protected void doProcess() {
        // Create a combined visitor for each specification
        getState().getSpecIndex().getAllSpecifications().forEach(spec -> {
            VisitorModel specVisitor = getState().getConceptIndex().lookupVisitor(spec.getNamespace());
            if (specVisitor == null) {
                Logger.warn("[CreateCombinedVisitorInterfacesStage] Visitor not found for specification: " + spec.getName());
            } else {
                createCombinedVisitor(specVisitor);
            }
        });

        // Create a combined visitor for the root
        VisitorModel rootVisitor = getState().getConceptIndex().lookupVisitor(getState().getConfig().getRootNamespace());
        if (rootVisitor == null) {
            Logger.warn("[CreateCombinedVisitorInterfacesStage] Root visitor not found!");
        } else {
            createCombinedVisitor(rootVisitor);
        }
    }

    private void createCombinedVisitor(VisitorModel visitor) {
        String visitorInterfaceName = getVisitorInterfaceName(visitor);
        String combinedVisitorInterfaceName = "Combined" + visitorInterfaceName;
        String combinedVisitorPackageName = getVisitorInterfacePackageName(visitor);

        // Lookup the package for the NS
        JavaPackageModel combinedVisitorPackage = getState().getJavaIndex().lookupAndIndexPackage(() -> {
            JavaPackageModel parentPackage = getState().getJavaIndex().lookupPackage(visitor.getNamespace().fullName());
            JavaPackageModel packageModel = JavaPackageModel.builder()
                    .name(combinedVisitorPackageName)
                    .parent(parentPackage)
                    .build();
            return packageModel;
        });

        // Create the conbined visitor interface
        JavaInterfaceModel combinedVisitorInterface = JavaInterfaceModel.builder()
                ._package(combinedVisitorPackage)
                .name(combinedVisitorInterfaceName)
                .build();

        // Create java source code for the combined visitor
        JavaInterfaceSource combinedVisitorInterfaceSource = Roaster.create(JavaInterfaceSource.class)
                .setPackage(combinedVisitorPackageName)
                .setName(combinedVisitorInterface.getName())
                .setPublic();
        combinedVisitorInterface.setInterfaceSource(combinedVisitorInterfaceSource);

        // Extend all of the descendant visitor interfaces
        Set<VisitorModel> descendantVisitors = findDescendantVisitors(visitor);
        for (VisitorModel descendantVisitor : descendantVisitors) {
            String descendantVisitorFQCN = getVisitorInterfaceFullName(descendantVisitor);
            JavaInterfaceModel descendantVisitorInterfaceModel = getState().getJavaIndex().lookupInterface(descendantVisitorFQCN);
            if (descendantVisitorInterfaceModel == null) {
                Logger.warn("[CreateCombinedVisitorInterfacesStage] Could not find visitor java interface for: " + descendantVisitor);
            } else {
                combinedVisitorInterface.get_extends().add(descendantVisitorInterfaceModel);
                combinedVisitorInterfaceSource.addImport(descendantVisitorInterfaceModel.getInterfaceSource().getQualifiedName());
                combinedVisitorInterfaceSource.addInterface(descendantVisitorInterfaceModel.getInterfaceSource());
            }
        }

        // Add the new combined visitor to the index.
        getState().getJavaIndex().addInterface(combinedVisitorInterface);
    }
}
