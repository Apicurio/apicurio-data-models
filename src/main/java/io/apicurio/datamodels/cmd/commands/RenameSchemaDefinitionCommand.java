package io.apicurio.datamodels.cmd.commands;

import io.apicurio.datamodels.TraverserDirection;
import io.apicurio.datamodels.VisitorUtil;
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.deref.AllReferenceableNodeVisitor;
import io.apicurio.datamodels.models.Document;
import io.apicurio.datamodels.models.Referenceable;
import io.apicurio.datamodels.models.openapi.OpenApiSchema;
import io.apicurio.datamodels.models.openapi.v20.OpenApi20Document;
import io.apicurio.datamodels.models.openapi.v20.OpenApi20Schema;
import io.apicurio.datamodels.models.openapi.v30.OpenApi30Document;
import io.apicurio.datamodels.models.openapi.v31.OpenApi31Document;
import io.apicurio.datamodels.util.LoggerUtil;
import io.apicurio.datamodels.util.ModelTypeUtil;

/**
 * A command used to rename a schema definition and update all $ref references.
 * @author eric.wittmann@gmail.com
 */
public class RenameSchemaDefinitionCommand extends AbstractCommand {

    public String _oldName;
    public String _newName;

    public boolean _schemaRenamed;

    public RenameSchemaDefinitionCommand() {
    }

    public RenameSchemaDefinitionCommand(String oldName, String newName) {
        this._oldName = oldName;
        this._newName = newName;
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(Document)
     */
    @Override
    public void execute(Document document) {
        LoggerUtil.info("[RenameSchemaDefinitionCommand] Executing.");
        this._schemaRenamed = false;

        if (ModelTypeUtil.isOpenApi2Model(document)) {
            renameInOas20((OpenApi20Document) document, _oldName, _newName);
        } else if (ModelTypeUtil.isOpenApi30Model(document)) {
            renameInOas30((OpenApi30Document) document, _oldName, _newName);
        } else if (ModelTypeUtil.isOpenApi31Model(document)) {
            renameInOas31((OpenApi31Document) document, _oldName, _newName);
        } else {
            return;
        }

        if (this._schemaRenamed) {
            String oldRef = getRefPrefix(document) + _oldName;
            String newRef = getRefPrefix(document) + _newName;
            updateRefs(document, oldRef, newRef);
        }
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(Document)
     */
    @Override
    public void undo(Document document) {
        LoggerUtil.info("[RenameSchemaDefinitionCommand] Reverting.");
        if (!this._schemaRenamed) {
            return;
        }

        if (ModelTypeUtil.isOpenApi2Model(document)) {
            renameInOas20((OpenApi20Document) document, _newName, _oldName);
        } else if (ModelTypeUtil.isOpenApi30Model(document)) {
            renameInOas30((OpenApi30Document) document, _newName, _oldName);
        } else if (ModelTypeUtil.isOpenApi31Model(document)) {
            renameInOas31((OpenApi31Document) document, _newName, _oldName);
        }

        String oldRef = getRefPrefix(document) + _oldName;
        String newRef = getRefPrefix(document) + _newName;
        updateRefs(document, newRef, oldRef);
    }

    private void renameInOas20(OpenApi20Document doc, String fromName, String toName) {
        if (isNullOrUndefined(doc.getDefinitions())) {
            return;
        }
        OpenApi20Schema schema = (OpenApi20Schema) doc.getDefinitions().getItem(fromName);
        if (isNullOrUndefined(schema)) {
            return;
        }
        if (!isNullOrUndefined(doc.getDefinitions().getItem(toName))) {
            return;
        }
        doc.getDefinitions().removeItem(fromName);
        doc.getDefinitions().addItem(toName, schema);
        this._schemaRenamed = true;
    }

    private void renameInOas30(OpenApi30Document doc, String fromName, String toName) {
        if (isNullOrUndefined(doc.getComponents())) {
            return;
        }
        OpenApiSchema schema = (OpenApiSchema) doc.getComponents().getSchemas().get(fromName);
        if (isNullOrUndefined(schema)) {
            return;
        }
        if (!isNullOrUndefined(doc.getComponents().getSchemas().get(toName))) {
            return;
        }
        doc.getComponents().removeSchema(fromName);
        doc.getComponents().addSchema(toName, schema);
        this._schemaRenamed = true;
    }

    private void renameInOas31(OpenApi31Document doc, String fromName, String toName) {
        if (isNullOrUndefined(doc.getComponents())) {
            return;
        }
        OpenApiSchema schema = (OpenApiSchema) doc.getComponents().getSchemas().get(fromName);
        if (isNullOrUndefined(schema)) {
            return;
        }
        if (!isNullOrUndefined(doc.getComponents().getSchemas().get(toName))) {
            return;
        }
        doc.getComponents().removeSchema(fromName);
        doc.getComponents().addSchema(toName, schema);
        this._schemaRenamed = true;
    }

    private String getRefPrefix(Document document) {
        if (ModelTypeUtil.isOpenApi2Model(document)) {
            return "#/definitions/";
        }
        return "#/components/schemas/";
    }

    private void updateRefs(Document document, String oldRef, String newRef) {
        VisitorUtil.visitTree(document, new AllReferenceableNodeVisitor() {
            @Override
            protected void visitReferenceableNode(Referenceable refNode) {
                String ref = refNode.get$ref();
                if (oldRef.equals(ref)) {
                    refNode.set$ref(newRef);
                }
            }
        }, TraverserDirection.down);
    }

}
