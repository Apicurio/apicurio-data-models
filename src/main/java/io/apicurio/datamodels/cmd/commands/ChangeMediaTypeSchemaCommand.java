package io.apicurio.datamodels.cmd.commands;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.models.Document;
import io.apicurio.datamodels.models.openapi.OpenApiMediaType;
import io.apicurio.datamodels.models.openapi.OpenApiSchema;
import io.apicurio.datamodels.paths.NodePath;
import io.apicurio.datamodels.paths.NodePathUtil;
import io.apicurio.datamodels.util.LoggerUtil;

/**
 * A command used to set or change the schema on a media type entry.
 * @author eric.wittmann@gmail.com
 */
public class ChangeMediaTypeSchemaCommand extends AbstractCommand {

    public NodePath _mediaTypePath;
    public String _schemaRef;
    public String _schemaType;

    public ObjectNode _oldSchema;
    public boolean _hadSchema;

    public ChangeMediaTypeSchemaCommand() {
    }

    public ChangeMediaTypeSchemaCommand(OpenApiMediaType mediaType, String schemaRef, String schemaType) {
        this._mediaTypePath = NodePathUtil.createNodePath(mediaType);
        this._schemaRef = schemaRef;
        this._schemaType = schemaType;
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(Document)
     */
    @Override
    public void execute(Document document) {
        LoggerUtil.info("[ChangeMediaTypeSchemaCommand] Executing.");
        this._oldSchema = null;
        this._hadSchema = false;

        OpenApiMediaType mediaType = (OpenApiMediaType) NodePathUtil.resolveNodePath(this._mediaTypePath, document);
        if (this.isNullOrUndefined(mediaType)) {
            return;
        }

        // Save old schema for undo
        OpenApiSchema oldSchema = mediaType.getSchema();
        if (!this.isNullOrUndefined(oldSchema)) {
            this._oldSchema = Library.writeNode(oldSchema);
            this._hadSchema = true;
        }

        // Create new schema and set properties
        if (!this.isNullOrUndefined(this._schemaRef) || !this.isNullOrUndefined(this._schemaType)) {
            OpenApiSchema newSchema = mediaType.createSchema();
            if (!this.isNullOrUndefined(this._schemaRef)) {
                newSchema.set$ref(this._schemaRef);
            } else if (!this.isNullOrUndefined(this._schemaType)) {
                newSchema.setType(this._schemaType);
            }
            mediaType.setSchema(newSchema);
        } else {
            // Clear schema
            mediaType.setSchema(null);
        }
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(Document)
     */
    @Override
    public void undo(Document document) {
        LoggerUtil.info("[ChangeMediaTypeSchemaCommand] Reverting.");

        OpenApiMediaType mediaType = (OpenApiMediaType) NodePathUtil.resolveNodePath(this._mediaTypePath, document);
        if (this.isNullOrUndefined(mediaType)) {
            return;
        }

        if (this._hadSchema && !this.isNullOrUndefined(this._oldSchema)) {
            OpenApiSchema restoredSchema = mediaType.createSchema();
            Library.readNode(this._oldSchema, restoredSchema);
            mediaType.setSchema(restoredSchema);
        } else {
            mediaType.setSchema(null);
        }
    }

}
