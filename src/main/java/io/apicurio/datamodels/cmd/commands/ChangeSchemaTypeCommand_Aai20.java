package io.apicurio.datamodels.cmd.commands;

import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.asyncapi.models.AaiSchema;
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.cmd.models.SimplifiedType;
import io.apicurio.datamodels.cmd.util.SimplifiedTypeUtil;
import io.apicurio.datamodels.compat.LoggerCompat;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.NodePath;

/**
 * Asyncapi equivalent of {@link ChangeSchemaTypeCommand}
 */
public class ChangeSchemaTypeCommand_Aai20 extends AbstractCommand {

    public NodePath _schemaPath;
    public SimplifiedType _newType;
    public SimplifiedType _oldType;

    ChangeSchemaTypeCommand_Aai20() {
    }

    ChangeSchemaTypeCommand_Aai20(AaiSchema schema, SimplifiedType newType) {
        this._schemaPath = Library.createNodePath(schema);
        this._newType = newType;
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void execute(Document document) {
        LoggerCompat.info("[ChangeSchemaTypeCommand_Aai20] Executing: " + this._newType);
        this._oldType = null;

        AaiSchema schema = (AaiSchema) this._schemaPath.resolve(document);
        if (this.isNullOrUndefined(schema)) {
            return;
        }

        // Save the old info (for later undo operation)
        this._oldType = SimplifiedType.fromAaiSchema(schema);

        // Update the schema's type
        SimplifiedTypeUtil.setSimplifiedType(schema, this._newType);
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(io.apicurio.datamodels.core.models.Document)
     */
    @Override
    public void undo(Document document) {
        LoggerCompat.info("[ChangeSchemaTypeCommand_Aai20] Reverting.");
        if (this._oldType == null) {
            return;
        }

        AaiSchema schema = (AaiSchema) this._schemaPath.resolve(document);
        if (this.isNullOrUndefined(schema)) {
            return;
        }

        SimplifiedTypeUtil.setSimplifiedType(schema, this._oldType);
    }
}
