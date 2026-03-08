package io.apicurio.datamodels.cmd.commands;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.models.Document;
import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.openapi.OpenApiCallback;
import io.apicurio.datamodels.models.openapi.v30.OpenApi30Callback;
import io.apicurio.datamodels.models.openapi.v30.OpenApi30Components;
import io.apicurio.datamodels.models.openapi.v30.OpenApi30Operation;
import io.apicurio.datamodels.models.openapi.v31.OpenApi31Callback;
import io.apicurio.datamodels.models.openapi.v31.OpenApi31Components;
import io.apicurio.datamodels.models.openapi.v31.OpenApi31Operation;
import io.apicurio.datamodels.paths.NodePath;
import io.apicurio.datamodels.paths.NodePathUtil;
import io.apicurio.datamodels.util.LoggerUtil;

/**
 * A command used to add a callback to an operation or to components/callbacks.
 * @author eric.wittmann@gmail.com
 */
public class AddCallbackCommand extends AbstractCommand {

    public NodePath _parentPath;
    public String _callbackName;
    public ObjectNode _from;

    public boolean _created;

    public AddCallbackCommand() {
    }

    public AddCallbackCommand(Node parent, String callbackName, ObjectNode from) {
        this._parentPath = NodePathUtil.createNodePath(parent);
        this._callbackName = callbackName;
        this._from = from;
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(Document)
     */
    @Override
    public void execute(Document document) {
        LoggerUtil.info("[AddCallbackCommand] Executing.");
        this._created = false;

        Node parent = NodePathUtil.resolveNodePath(this._parentPath, document);
        if (this.isNullOrUndefined(parent)) {
            return;
        }

        if (parent instanceof OpenApi30Operation) {
            OpenApi30Operation operation30 = (OpenApi30Operation) parent;
            if (!this.isNullOrUndefined(operation30.getCallbacks()) && operation30.getCallbacks().containsKey(this._callbackName)) {
                return;
            }
            OpenApi30Callback callback = operation30.createCallback();
            Library.readNode(this._from, callback);
            operation30.addCallback(this._callbackName, callback);
            this._created = true;
        } else if (parent instanceof OpenApi31Operation) {
            OpenApi31Operation operation31 = (OpenApi31Operation) parent;
            if (!this.isNullOrUndefined(operation31.getCallbacks()) && operation31.getCallbacks().containsKey(this._callbackName)) {
                return;
            }
            OpenApi31Callback callback = operation31.createCallback();
            Library.readNode(this._from, callback);
            operation31.addCallback(this._callbackName, callback);
            this._created = true;
        } else if (parent instanceof OpenApi30Components) {
            OpenApi30Components components30 = (OpenApi30Components) parent;
            if (!this.isNullOrUndefined(components30.getCallbacks()) && components30.getCallbacks().containsKey(this._callbackName)) {
                return;
            }
            OpenApiCallback callback = components30.createCallback();
            Library.readNode(this._from, callback);
            components30.addCallback(this._callbackName, callback);
            this._created = true;
        } else if (parent instanceof OpenApi31Components) {
            OpenApi31Components components31 = (OpenApi31Components) parent;
            if (!this.isNullOrUndefined(components31.getCallbacks()) && components31.getCallbacks().containsKey(this._callbackName)) {
                return;
            }
            OpenApiCallback callback = components31.createCallback();
            Library.readNode(this._from, callback);
            components31.addCallback(this._callbackName, callback);
            this._created = true;
        }
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(Document)
     */
    @Override
    public void undo(Document document) {
        LoggerUtil.info("[AddCallbackCommand] Reverting.");
        if (!this._created) {
            return;
        }

        Node parent = NodePathUtil.resolveNodePath(this._parentPath, document);
        if (this.isNullOrUndefined(parent)) {
            return;
        }

        if (parent instanceof OpenApi30Operation) {
            ((OpenApi30Operation) parent).removeCallback(this._callbackName);
        } else if (parent instanceof OpenApi31Operation) {
            ((OpenApi31Operation) parent).removeCallback(this._callbackName);
        } else if (parent instanceof OpenApi30Components) {
            ((OpenApi30Components) parent).removeCallback(this._callbackName);
        } else if (parent instanceof OpenApi31Components) {
            ((OpenApi31Components) parent).removeCallback(this._callbackName);
        }
    }

}
