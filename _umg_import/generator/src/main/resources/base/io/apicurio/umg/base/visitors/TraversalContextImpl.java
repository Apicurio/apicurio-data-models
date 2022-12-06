package io.apicurio.umg.base.visitors;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * Context used during traversal of a model. This gives insight into where in
 * the traversal of a model a visitor/traverser might be. The traverser
 * maintains this context and optionally makes it available to the visitor (only
 * if the visitor implements the TraversingVisitor interface.
 *
 * @author eric.wittmann@gmail.com
 */
public class TraversalContextImpl implements TraversalContext {

    private final Stack<TraversalStep> stack = new Stack<>();

    public void push(String propertyName) {
        this.stack.push(new TraversalStep(propertyName));
    }
    public void push(String propertyName, int index) {
        this.stack.push(new TraversalStep(propertyName, index));
    }
    public void push(String propertyName, String key) {
        this.stack.push(new TraversalStep(propertyName, key));
    }

    public void pop() {
        this.stack.pop();
    }

    public TraversalStep peek() {
        return this.stack.peek();
    }

    @Override
    public TraversalStep getMostRecentStep() {
        return this.stack.peek();
    }

    @Override
    public List<TraversalStep> getAllSteps() {
        TraversalStep[] steps = this.stack.toArray(new TraversalStep[this.stack.size()]);
        return Collections.unmodifiableList(Arrays.asList(steps));
    }

}
