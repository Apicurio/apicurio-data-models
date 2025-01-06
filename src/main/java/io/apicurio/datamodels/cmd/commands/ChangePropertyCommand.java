package io.apicurio.datamodels.cmd.commands;

import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.models.Document;
import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.paths.NodePath;
import io.apicurio.datamodels.paths.NodePathUtil;
import io.apicurio.datamodels.util.LoggerUtil;
import io.apicurio.datamodels.util.NodeUtil;

/**
 * A command used to modify the simple property of a node.  Should not be used
 * to modify complex (object) properties, only simple property types like
 * string, boolean, number, etc.
 * 
 * @author eric.wittmann@gmail.com
 */
public class ChangePropertyCommand<T> extends AbstractCommand {

    public NodePath _nodePath;
    public String _property;
    public T _newValue;

    public T _oldValue;
    
    /**
     * Constructor.
     */
    public ChangePropertyCommand() {
    }

    /**
     * C'tor.
     */
    public ChangePropertyCommand(Node node, String property, T newValue) {
        super();
        if (NodeUtil.isDefined(node)) {
            this._nodePath = NodePathUtil.createNodePath(node);
        }
        this._property = property;
        this._newValue = newValue;
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(Document)
     */
    @SuppressWarnings("unchecked")
    @Override
    public void execute(Document document) {
        LoggerUtil.info("[ChangePropertyCommand] Executing.");
        Node node = NodePathUtil.resolveNodePath(_nodePath, document);
        if (this.isNullOrUndefined(node)) {
            return;
        }

        this._oldValue = (T) NodeUtil.getProperty(node, this._property);
        NodeUtil.setProperty(node, this._property, this._newValue);
    }

    @Override
    public void undo(Document document) {
        LoggerUtil.info("[ChangePropertyCommand] Reverting.");
        Node node = NodePathUtil.resolveNodePath(_nodePath, document);
        if (this.isNullOrUndefined(node)) {
            return;
        }

        NodeUtil.setProperty(node, this._property, this._oldValue);
        this._oldValue = null;
    }

}
