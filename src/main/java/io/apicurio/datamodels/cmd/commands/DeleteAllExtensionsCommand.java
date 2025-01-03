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

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * A command used to delete all extensions from a node.
 * @author eric.wittmann@gmail.com
 */
public class DeleteAllExtensionsCommand extends AbstractCommand {

    public NodePath _parentPath;

    public Map<String, JsonNode> _oldExtensions;

    public DeleteAllExtensionsCommand() {
    }

    public DeleteAllExtensionsCommand(Extensible parent) {
        this._parentPath = Library.createNodePath((Node) parent);
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(Document)
     */
    @Override
    public void execute(Document document) {
        LoggerUtil.info("[DeleteAllExtensionsCommand] Executing.");
        this._oldExtensions = new LinkedHashMap<>();

        Extensible parent = (Extensible) NodePathUtil.resolveNodePath(this._parentPath, document);

        Map<String, JsonNode> extensions = parent.getExtensions();

        // Save the old extensions (if any)
        if (!this.isNullOrUndefined(extensions)) {
            extensions.keySet().forEach(k -> {
                JsonNode value = extensions.get(k);
                this._oldExtensions.put(k, value);
            });
        }

        // Remove all extensions
        parent.clearExtensions();
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(Document)
     */
    @Override
    public void undo(Document document) {
        LoggerUtil.info("[DeleteAllExtensionsCommand] Reverting.");
        if (this._oldExtensions.size() == 0) {
            return;
        }

        Extensible parent = (Extensible) NodePathUtil.resolveNodePath(this._parentPath, document);

        this._oldExtensions.keySet().forEach(k -> {
            JsonNode value = _oldExtensions.get(k);
            parent.addExtension(k, value);
        });
    }
}
