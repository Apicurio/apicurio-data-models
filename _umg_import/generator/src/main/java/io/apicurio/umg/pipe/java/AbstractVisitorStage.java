package io.apicurio.umg.pipe.java;

import java.util.Set;
import java.util.stream.Collectors;

import io.apicurio.umg.models.concept.SpecificationVersionId;
import io.apicurio.umg.models.concept.VisitorModel;

public abstract class AbstractVisitorStage extends AbstractJavaStage {

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

}
