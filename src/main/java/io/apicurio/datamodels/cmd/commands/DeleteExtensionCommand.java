package io.apicurio.datamodels.cmd.commands;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.models.Document;
import io.apicurio.datamodels.models.Extensible;
import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.paths.NodePath;
import io.apicurio.datamodels.paths.NodePathUtil;
import io.apicurio.datamodels.util.LoggerUtil;

import java.util.Map;

/**
 * A command used to delete a single extension.
 * @author eric.wittmann@gmail.com
 */
public class DeleteExtensionCommand extends AbstractCommand {

    public NodePath _parentPath;
    public String _name;

    public boolean _hasOldValue;
    public JsonNode _oldValue;
    public int _oldValueIndex;

    public DeleteExtensionCommand() {
    }

    public DeleteExtensionCommand(Extensible parent, String extensionName) {
        this._parentPath = Library.createNodePath((Node) parent);
        this._name = extensionName;
    }
    
    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(Document)
     */
    @Override
    public void execute(Document document) {
        LoggerUtil.info("[DeleteExtensionCommand] Executing.");
        this._oldValue = null;
        this._hasOldValue = false;

        Node parent = NodePathUtil.resolveNodePath(this._parentPath, document);
        if (this.isNullOrUndefined(parent)) {
            return;
        }
        Extensible extensible = (Extensible) parent;
        Map<String, JsonNode> extensions = extensible.getExtensions();
        if (this.isNullOrUndefined(extensions)) {
            return;
        }
        if (extensions.containsKey(this._name)) {
            this._hasOldValue = true;
            this._oldValue = extensions.get(this._name);
            this._oldValueIndex = indexOf(extensions.keySet(), this._name);
            extensible.removeExtension(this._name);
        } else {
            this._oldValue = null;
            this._hasOldValue = false;
            this._oldValueIndex = -1;
        }
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(Document)
     */
    @Override
    public void undo(Document document) {
        LoggerUtil.info("[DeleteExtensionCommand] Reverting.");
        if (!this._hasOldValue) {
            return;
        }

        Node parent = NodePathUtil.resolveNodePath(this._parentPath, document);
        if (this.isNullOrUndefined(parent)) {
            return;
        }
        Extensible extensible = (Extensible) parent;
        if (this._oldValueIndex > -1) {
            extensible.insertExtension(this._name, this._oldValue, this._oldValueIndex);
        } else {
            extensible.addExtension(this._name, this._oldValue);
        }
    }

}
