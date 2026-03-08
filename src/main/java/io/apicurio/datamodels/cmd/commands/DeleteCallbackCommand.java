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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A command used to delete a callback from an operation or from components/callbacks.
 * @author eric.wittmann@gmail.com
 */
public class DeleteCallbackCommand extends AbstractCommand {

    public NodePath _parentPath;
    public String _callbackName;

    public ObjectNode _oldCallback;
    public int _oldIndex;

    public DeleteCallbackCommand() {
    }

    public DeleteCallbackCommand(Node parent, String callbackName) {
        this._parentPath = NodePathUtil.createNodePath(parent);
        this._callbackName = callbackName;
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(Document)
     */
    @Override
    public void execute(Document document) {
        LoggerUtil.info("[DeleteCallbackCommand] Executing.");
        this._oldCallback = null;

        Node parent = NodePathUtil.resolveNodePath(this._parentPath, document);
        if (this.isNullOrUndefined(parent)) {
            return;
        }

        if (parent instanceof OpenApi30Operation) {
            OpenApi30Operation operation30 = (OpenApi30Operation) parent;
            Map<String, OpenApi30Callback> callbacks = operation30.getCallbacks();
            if (this.isNullOrUndefined(callbacks) || !callbacks.containsKey(this._callbackName)) {
                return;
            }
            OpenApi30Callback callback = callbacks.get(this._callbackName);
            this._oldCallback = Library.writeNode(callback);
            List<String> callbackNames = new ArrayList<>(callbacks.keySet());
            this._oldIndex = callbackNames.indexOf(this._callbackName);
            operation30.removeCallback(this._callbackName);
        } else if (parent instanceof OpenApi31Operation) {
            OpenApi31Operation operation31 = (OpenApi31Operation) parent;
            Map<String, OpenApi31Callback> callbacks = operation31.getCallbacks();
            if (this.isNullOrUndefined(callbacks) || !callbacks.containsKey(this._callbackName)) {
                return;
            }
            OpenApi31Callback callback = callbacks.get(this._callbackName);
            this._oldCallback = Library.writeNode(callback);
            List<String> callbackNames = new ArrayList<>(callbacks.keySet());
            this._oldIndex = callbackNames.indexOf(this._callbackName);
            operation31.removeCallback(this._callbackName);
        } else if (parent instanceof OpenApi30Components) {
            OpenApi30Components components30 = (OpenApi30Components) parent;
            Map<String, OpenApiCallback> callbacks = components30.getCallbacks();
            if (this.isNullOrUndefined(callbacks) || !callbacks.containsKey(this._callbackName)) {
                return;
            }
            OpenApiCallback callback = callbacks.get(this._callbackName);
            this._oldCallback = Library.writeNode(callback);
            List<String> callbackNames = new ArrayList<>(callbacks.keySet());
            this._oldIndex = callbackNames.indexOf(this._callbackName);
            components30.removeCallback(this._callbackName);
        } else if (parent instanceof OpenApi31Components) {
            OpenApi31Components components31 = (OpenApi31Components) parent;
            Map<String, OpenApiCallback> callbacks = components31.getCallbacks();
            if (this.isNullOrUndefined(callbacks) || !callbacks.containsKey(this._callbackName)) {
                return;
            }
            OpenApiCallback callback = callbacks.get(this._callbackName);
            this._oldCallback = Library.writeNode(callback);
            List<String> callbackNames = new ArrayList<>(callbacks.keySet());
            this._oldIndex = callbackNames.indexOf(this._callbackName);
            components31.removeCallback(this._callbackName);
        }
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(Document)
     */
    @Override
    public void undo(Document document) {
        LoggerUtil.info("[DeleteCallbackCommand] Reverting.");
        if (this.isNullOrUndefined(this._oldCallback)) {
            return;
        }

        Node parent = NodePathUtil.resolveNodePath(this._parentPath, document);
        if (this.isNullOrUndefined(parent)) {
            return;
        }

        if (parent instanceof OpenApi30Operation) {
            OpenApi30Operation operation30 = (OpenApi30Operation) parent;
            OpenApi30Callback callback = operation30.createCallback();
            Library.readNode(this._oldCallback, callback);
            operation30.insertCallback(this._callbackName, callback, this._oldIndex);
        } else if (parent instanceof OpenApi31Operation) {
            OpenApi31Operation operation31 = (OpenApi31Operation) parent;
            OpenApi31Callback callback = operation31.createCallback();
            Library.readNode(this._oldCallback, callback);
            operation31.insertCallback(this._callbackName, callback, this._oldIndex);
        } else if (parent instanceof OpenApi30Components) {
            OpenApi30Components components30 = (OpenApi30Components) parent;
            OpenApiCallback callback = components30.createCallback();
            Library.readNode(this._oldCallback, callback);
            components30.insertCallback(this._callbackName, callback, this._oldIndex);
        } else if (parent instanceof OpenApi31Components) {
            OpenApi31Components components31 = (OpenApi31Components) parent;
            OpenApiCallback callback = components31.createCallback();
            Library.readNode(this._oldCallback, callback);
            components31.insertCallback(this._callbackName, callback, this._oldIndex);
        }
    }

}
