package io.apicurio.datamodels.cmd.commands;

import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.models.Document;
import io.apicurio.datamodels.models.openapi.OpenApiDocument;
import io.apicurio.datamodels.models.openapi.OpenApiPathItem;
import io.apicurio.datamodels.models.openapi.OpenApiPaths;
import io.apicurio.datamodels.util.LoggerUtil;

/**
 * A command used to create a new empty path item in a document.
 * @author eric.wittmann@gmail.com
 */
public class CreatePathCommand extends AbstractCommand {

    public String _pathName;

    public boolean _pathCreated;
    public boolean _pathsCreated;

    public CreatePathCommand() {
    }

    public CreatePathCommand(String pathName) {
        this._pathName = pathName.startsWith("/") ? pathName : "/" + pathName;
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(Document)
     */
    @Override
    public void execute(Document document) {
        LoggerUtil.info("[CreatePathCommand] Executing.");
        this._pathCreated = false;
        this._pathsCreated = false;

        OpenApiDocument doc = (OpenApiDocument) document;
        OpenApiPaths paths = doc.getPaths();

        // Create paths object if it doesn't exist
        if (this.isNullOrUndefined(paths)) {
            paths = doc.createPaths();
            doc.setPaths(paths);
            this._pathsCreated = true;
        }

        // Check if path already exists
        if (!this.isNullOrUndefined(paths.getItem(this._pathName))) {
            return;
        }

        // Create the new path item
        OpenApiPathItem newPathItem = paths.createPathItem();
        paths.addItem(this._pathName, newPathItem);
        this._pathCreated = true;
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(Document)
     */
    @Override
    public void undo(Document document) {
        LoggerUtil.info("[CreatePathCommand] Reverting.");
        if (!this._pathCreated) {
            return;
        }

        OpenApiDocument doc = (OpenApiDocument) document;

        if (this._pathsCreated) {
            doc.setPaths(null);
        } else {
            OpenApiPaths paths = doc.getPaths();
            if (!this.isNullOrUndefined(paths)) {
                paths.removeItem(this._pathName);
            }
        }
    }

}
