package io.apicurio.umg.pipe.java;

import java.util.Set;
import java.util.stream.Collectors;

import org.jboss.forge.roaster.model.source.JavaInterfaceSource;

import io.apicurio.umg.logging.Logger;
import io.apicurio.umg.models.concept.SpecificationVersionId;
import io.apicurio.umg.models.concept.VisitorModel;

public abstract class AbstractVisitorStage extends AbstractJavaStage {

    /**
     * Determines the package to use for the interface generated for the given visitor.
     * @param visitor
     */
    protected String getVisitorInterfacePackageName(VisitorModel visitor) {
        String packageName = visitor.getNamespace().fullName();
        String visitorPackageName = packageName + ".visitors";
        return visitorPackageName;
    }

    /**
     * Determines the prefix to use for the interface name for the given visitor.
     * @param visitor
     */
    protected String getVisitorInterfacePrefix(VisitorModel visitor) {
        return (visitor.getParent() == null) ? "" : getState().getSpecIndex().prefixForNS(visitor.getNamespace().fullName());
    }

    /**
     * Determines the interface name for the given visitor.
     * @param visitor
     */
    protected String getVisitorInterfaceName(VisitorModel visitor) {
        String visitorPrefix = getVisitorInterfacePrefix(visitor);
        String visitorInterfaceName = visitorPrefix + "Visitor";
        return visitorInterfaceName;
    }

    /**
     * Determines the fully qualified name of the Java interface for a given visitor.
     * @param visitor
     */
    protected String getVisitorInterfaceFullName(VisitorModel visitor) {
        String packageName = visitor.getNamespace().fullName();
        String visitorPackageName = packageName + ".visitors";
        String visitorPrefix = getVisitorInterfacePrefix(visitor);
        String visitorInterfaceName = visitorPrefix + "Visitor";
        return visitorPackageName + "." + visitorInterfaceName;
    }

    /**
     * Finds all of the descendant visitors for the given visitor.  This walks down the
     * visitor hierarchy and finds all of the "leaf" visitor nodes in that tree.
     * @param visitor
     */
    protected Set<VisitorModel> findDescendantVisitors(VisitorModel visitor) {
        return getState().getConceptIndex().findVisitors(visitor.getNamespace().fullName()).stream().filter(v -> isVisitorForSpecVersion(v)).collect(Collectors.toSet());
    }

    /**
     * Returns ture if the given visitor is associated with a specification version.
     * @param visitor
     */
    private boolean isVisitorForSpecVersion(VisitorModel visitor) {
        SpecificationVersionId id = SpecificationVersionId.create(visitor.getNamespace().fullName());
        return getState().getSpecIndex().getSpecIndex().containsKey(id);
    }

    /**
     * Resolves a generated java interface from a visitor model.
     * @param visitor
     */
    protected JavaInterfaceSource resolveJavaInterface(VisitorModel visitor) {
        String interfaceFQN = getVisitorInterfaceFullName(visitor);
        JavaInterfaceSource _interface = getState().getJavaIndex().lookupInterface(interfaceFQN);
        if (_interface == null) {
            Logger.warn("[" + getClass().getSimpleName() + "] Visitor interface not found: " + interfaceFQN);
        }
        return _interface;
    }

}
