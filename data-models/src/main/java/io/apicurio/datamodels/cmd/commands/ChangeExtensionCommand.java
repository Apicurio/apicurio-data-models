package io.apicurio.datamodels.cmd.commands;

import com.fasterxml.jackson.databind.JsonNode;
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.models.Document;
import io.apicurio.datamodels.models.Extensible;
import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.paths.NodePath;
import io.apicurio.datamodels.paths.NodePathUtil;
import io.apicurio.datamodels.util.LoggerUtil;

import java.util.Map;

/**
 * A command used to change the value of a vendor extension on any extensible node.
 * @author eric.wittmann@gmail.com
 */
public class ChangeExtensionCommand extends AbstractCommand {

    public NodePath _parentPath;
    public String _name;
    public JsonNode _newValue;

    public JsonNode _oldValue;

    public ChangeExtensionCommand() {
    }

    public ChangeExtensionCommand(Extensible parent, String name, JsonNode newValue) {
        this._parentPath = NodePathUtil.createNodePath((Node) parent);
        this._name = name;
        this._newValue = newValue;
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(Document)
     */
    @Override
    public void execute(Document document) {
        LoggerUtil.info("[ChangeExtensionCommand] Executing.");
        this._oldValue = null;

        Node parent = NodePathUtil.resolveNodePath(this._parentPath, document);
        if (this.isNullOrUndefined(parent)) {
            return;
        }

        Extensible extensible = (Extensible) parent;
        Map<String, JsonNode> extensions = extensible.getExtensions();
        if (!this.isNullOrUndefined(extensions) && extensions.containsKey(this._name)) {
            this._oldValue = extensions.get(this._name);
        }
        extensible.addExtension(this._name, this._newValue);
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(Document)
     */
    @Override
    public void undo(Document document) {
        LoggerUtil.info("[ChangeExtensionCommand] Reverting.");

        Node parent = NodePathUtil.resolveNodePath(this._parentPath, document);
        if (this.isNullOrUndefined(parent)) {
            return;
        }

        Extensible extensible = (Extensible) parent;
        extensible.addExtension(this._name, this._oldValue);
    }

}
