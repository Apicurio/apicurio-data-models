package io.apicurio.datamodels.cmd.commands;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.models.Document;
import io.apicurio.datamodels.models.openapi.OpenApiDocument;
import io.apicurio.datamodels.models.openapi.OpenApiPathItem;
import io.apicurio.datamodels.models.openapi.OpenApiPaths;
import io.apicurio.datamodels.util.LoggerUtil;

/**
 * A command used to delete a path item from a document.
 * @author eric.wittmann@gmail.com
 */
public class DeletePathCommand extends AbstractCommand {

    public String _pathName;

    public ObjectNode _oldPathItem;

    public DeletePathCommand() {
    }

    public DeletePathCommand(String pathName) {
        this._pathName = pathName;
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(Document)
     */
    @Override
    public void execute(Document document) {
        LoggerUtil.info("[DeletePathCommand] Executing.");
        this._oldPathItem = null;

        OpenApiDocument doc = (OpenApiDocument) document;
        OpenApiPaths paths = doc.getPaths();
        if (this.isNullOrUndefined(paths)) {
            return;
        }

        OpenApiPathItem pathItem = paths.getItem(this._pathName);
        if (this.isNullOrUndefined(pathItem)) {
            return;
        }

        // Save the path item for undo
        this._oldPathItem = Library.writeNode(pathItem);

        // Remove the path item
        paths.removeItem(this._pathName);
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(Document)
     */
    @Override
    public void undo(Document document) {
        LoggerUtil.info("[DeletePathCommand] Reverting.");
        if (this.isNullOrUndefined(this._oldPathItem)) {
            return;
        }

        OpenApiDocument doc = (OpenApiDocument) document;
        OpenApiPaths paths = doc.getPaths();
        if (this.isNullOrUndefined(paths)) {
            paths = doc.createPaths();
            doc.setPaths(paths);
        }

        OpenApiPathItem newPathItem = paths.createPathItem();
        Library.readNode(this._oldPathItem, newPathItem);
        paths.addItem(this._pathName, newPathItem);
    }

}
