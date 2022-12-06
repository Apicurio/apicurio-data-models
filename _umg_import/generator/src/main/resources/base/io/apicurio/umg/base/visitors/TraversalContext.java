package io.apicurio.umg.base.visitors;

import java.util.List;

/**
 * Context used during traversal of a model. This gives insight into where in
 * the traversal of a model a visitor/traverser might be. The traverser
 * maintains this context and optionally makes it available to the visitor (only
 * if the visitor implements the TraversingVisitor interface.
 */
public interface TraversalContext {

    public TraversalStep getMostRecentStep();

    public List<TraversalStep> getAllSteps();

}
