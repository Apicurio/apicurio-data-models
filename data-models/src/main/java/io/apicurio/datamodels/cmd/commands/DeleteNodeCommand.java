package io.apicurio.datamodels.cmd.commands;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.models.Document;
import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.paths.NodePath;
import io.apicurio.datamodels.paths.NodePathUtil;
import io.apicurio.datamodels.util.LoggerUtil;
import io.apicurio.datamodels.util.NodeUtil;

/**
 * A command used to delete a child node.
 * @author eric.wittmann@gmail.com
 */
public abstract class DeleteNodeCommand<T extends Node> extends AbstractCommand {

    public String _property;
    public NodePath _parentPath;

    public ObjectNode _oldValue;
    
    public DeleteNodeCommand() {
    }

    public DeleteNodeCommand(String property, Node from) {
        this._property = property;
        this._parentPath = NodePathUtil.createNodePath(from);
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(Document)
     */
    @Override
    public void execute(Document document) {
        LoggerUtil.info("[" + this.type() + "] Executing.");
        Node parent = NodePathUtil.resolveNodePath(this._parentPath, document);
        if (this.isNullOrUndefined(parent)) {
            return;
        }
        @SuppressWarnings("unchecked")
        T propertyNode = (T) NodeUtil.getProperty(parent, _property);
        if (this.isNullOrUndefined(propertyNode)) {
            this._oldValue = null;
            return;
        } else {
            this._oldValue = Library.writeNode(propertyNode);
        }

        NodeUtil.setProperty(parent, _property, null);
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(Document)
     */
    @Override
    public void undo(Document document) {
        LoggerUtil.info("[" + this.type() + "] Reverting.");
        Node parent = NodePathUtil.resolveNodePath(this._parentPath, document);
        if (this.isNullOrUndefined(parent) || this.isNullOrUndefined(this._oldValue)) {
            return;
        }

        T restoredNode = this.readNode(document, this._oldValue);

        NodeUtil.setProperty(parent, _property, restoredNode);
    }
    
    protected abstract T readNode(Document doc, ObjectNode node);


}
