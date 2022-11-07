package io.apicurio.umg.pipe.java;

import java.util.List;
import java.util.stream.Collectors;

import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.source.JavaInterfaceSource;
import org.jboss.forge.roaster.model.source.MethodSource;

import io.apicurio.umg.logging.Logger;
import io.apicurio.umg.models.concept.EntityModel;
import io.apicurio.umg.models.concept.VisitorModel;
import io.apicurio.umg.models.java.JavaEntityModel;
import io.apicurio.umg.models.java.JavaInterfaceModel;
import io.apicurio.umg.models.java.JavaPackageModel;
import io.apicurio.umg.pipe.AbstractStage;

/**
 * Creates the visitor interfaces.  There is hierarchy of visitors that is similar to the
 * hierarchy of data models but slightly different.  The visitor hieararchy is determined
 * as part of the concept phase.  This class simply generates Java interfaces from that
 * visitor concept hierarchy.
 * @author eric.wittmann@gmail.com
 */
public class CreateVisitorInterfacesStage extends AbstractStage {

    @Override
    protected void doProcess() {
        List<VisitorModel> rootVisitors = getState().getConceptIndex().findVisitors("").stream()
                .filter(visitor -> visitor.getParent() == null).collect(Collectors.toList());
        rootVisitors.forEach(visitor -> {
            createVisitorInterfaces(visitor, null);
        });
    }

    /**
     * Creates visitor interfaces for all visitors in a hierarchy, starting with the
     * given root visitor model.
     * @param visitor
     * @param parentVisitorInterface
     */
    private void createVisitorInterfaces(VisitorModel visitor, JavaInterfaceModel parentVisitorInterface) {
        Logger.debug("Creating interface for: "  + visitor.toString());
        String visitorPackageName = visitor.getNamespace().fullName() + ".visitors";
        String visitorPrefix = (visitor.getParent() == null) ? null : getState().getSpecIndex().prefixForNS(visitor.getNamespace().fullName());
        String visitorInterfaceName = (visitorPrefix != null ? visitorPrefix : "") + "Visitor";

        // Lookup the package for the NS
        JavaPackageModel visitorPackage = getState().getJavaIndex().lookupAndIndexPackage(() -> {
            JavaPackageModel parentPackage = getState().getJavaIndex().lookupPackage(visitor.getNamespace().fullName());
            JavaPackageModel packageModel = JavaPackageModel.builder()
                    .name(visitorPackageName)
                    .parent(parentPackage)
                    .build();
            return packageModel;
        });

        // Create the reader interface model
        JavaInterfaceModel visitorInterface = JavaInterfaceModel.builder()
                ._package(visitorPackage)
                .name(visitorInterfaceName)
                .build();

        // Create java source code for the visitor
        JavaInterfaceSource visitorInterfaceSource = Roaster.create(JavaInterfaceSource.class)
                .setPackage(visitorPackageName)
                .setName(visitorInterface.getName())
                .setPublic();
        if (parentVisitorInterface != null) {
            visitorInterfaceSource.addImport(parentVisitorInterface.fullyQualifiedName());
            visitorInterfaceSource.addInterface(parentVisitorInterface.getName());
        }
        visitorInterface.setInterfaceSource(visitorInterfaceSource);

        // Now create visitXyz methods for each entity in the visitor
        visitor.getEntities().forEach(entity -> {
            createVisitMethodFor(visitorInterfaceSource, entity);
        });

        getState().getJavaIndex().addInterface(visitorInterface);

        // Now recursively process all the children of this visitor.
        visitor.getChildren().forEach(childVisitor -> {
            createVisitorInterfaces(childVisitor, visitorInterface);
        });
    }

    /**
     * Creates the readXyz() method for the given entity.
     * @param visitorInterfaceSource
     * @param entity
     */
    private void createVisitMethodFor(JavaInterfaceSource visitorInterfaceSource, EntityModel entity) {
        String visitMethodName = "visit" + entity.getName();

        JavaEntityModel javaEntityModel = findRootEntity(entity.fullyQualifiedName());
        if (javaEntityModel == null) {
            Logger.warn("[CreateVisitorInterfacesStage] Java entity not found for: " + entity.fullyQualifiedName());
            return;
        }

        visitorInterfaceSource.addImport(javaEntityModel.getJavaSource().getQualifiedName());

        MethodSource<JavaInterfaceSource> methodSource = visitorInterfaceSource.addMethod()
                .setName(visitMethodName)
                .setReturnTypeVoid()
                .setPublic();
        methodSource.addParameter(javaEntityModel.getJavaSource().getName(), "node");
    }

    private JavaEntityModel findRootEntity(String fullyQualifiedName) {
        EntityModel entity = getState().getConceptIndex().lookupEntity(fullyQualifiedName);
        while (entity.getParent() != null) {
            entity = entity.getParent();
        }
        return getState().getJavaIndex().lookupType(entity);
    }
}
