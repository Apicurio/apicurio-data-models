package io.apicurio.datamodels.cmd;

import io.apicurio.datamodels.util.NodeUtil;

/**
 * A base class for all command implementations.
 * @author eric.wittmann@gmail.com
 */
public abstract class AbstractCommand implements ICommand {
    
    /**
     * Constructor.
     */
    public AbstractCommand() {
    }

    /**
     * Returns true if the argument is either null or undefined.
     * @param object
     */
    protected boolean isNullOrUndefined(Object object) {
        return NodeUtil.isNullOrUndefined(object);
    }

    /**
     * @see ICommand#type()
     */
    @Override
    public final String type() {
        return this.getClass().getSimpleName();
    }

}
