package io.apicurio.datamodels.cmd.commands;

import java.util.ArrayList;
import java.util.Map;

import io.apicurio.datamodels.TraverserDirection;
import io.apicurio.datamodels.VisitorUtil;
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.deref.AllReferenceableNodeVisitor;
import io.apicurio.datamodels.models.Document;
import io.apicurio.datamodels.models.Referenceable;
import io.apicurio.datamodels.models.openapi.OpenApiHeader;
import io.apicurio.datamodels.models.openapi.v3x.v30.OpenApi30Document;
import io.apicurio.datamodels.models.openapi.v3x.v31.OpenApi31Document;
import io.apicurio.datamodels.util.LoggerUtil;
import io.apicurio.datamodels.util.ModelTypeUtil;

/**
 * A command used to rename a header definition and update all $ref references.
 * @author eric.wittmann@gmail.com
 */
public class RefactorHeaderDefinitionCommand extends AbstractCommand {

    public String _oldName;
    public String _newName;

    public boolean _headerRenamed;

    public RefactorHeaderDefinitionCommand() {
    }

    public RefactorHeaderDefinitionCommand(String oldName, String newName) {
        this._oldName = oldName;
        this._newName = newName;
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(Document)
     */
    @Override
    public void execute(Document document) {
        LoggerUtil.info("[RefactorHeaderDefinitionCommand] Executing.");
        this._headerRenamed = false;

        if (ModelTypeUtil.isOpenApi30Model(document)) {
            renameInOas30((OpenApi30Document) document, _oldName, _newName);
        } else if (ModelTypeUtil.isOpenApi31Model(document)) {
            renameInOas31((OpenApi31Document) document, _oldName, _newName);
        } else {
            return;
        }

        if (this._headerRenamed) {
            String oldRef = "#/components/headers/" + _oldName;
            String newRef = "#/components/headers/" + _newName;
            updateRefs(document, oldRef, newRef);
        }
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(Document)
     */
    @Override
    public void undo(Document document) {
        LoggerUtil.info("[RefactorHeaderDefinitionCommand] Reverting.");
        if (!this._headerRenamed) {
            return;
        }

        if (ModelTypeUtil.isOpenApi30Model(document)) {
            renameInOas30((OpenApi30Document) document, _newName, _oldName);
        } else if (ModelTypeUtil.isOpenApi31Model(document)) {
            renameInOas31((OpenApi31Document) document, _newName, _oldName);
        }

        String oldRef = "#/components/headers/" + _oldName;
        String newRef = "#/components/headers/" + _newName;
        updateRefs(document, newRef, oldRef);
    }

    private void renameInOas30(OpenApi30Document doc, String fromName, String toName) {
        if (isNullOrUndefined(doc.getComponents())) {
            return;
        }
        OpenApiHeader header = doc.getComponents().getHeaders().get(fromName);
        if (isNullOrUndefined(header)) {
            return;
        }
        if (!isNullOrUndefined(doc.getComponents().getHeaders().get(toName))) {
            return;
        }
        int index = indexOfKey(doc.getComponents().getHeaders(), fromName);
        doc.getComponents().removeHeader(fromName);
        doc.getComponents().insertHeader(toName, header, index);
        this._headerRenamed = true;
    }

    private void renameInOas31(OpenApi31Document doc, String fromName, String toName) {
        if (isNullOrUndefined(doc.getComponents())) {
            return;
        }
        OpenApiHeader header = doc.getComponents().getHeaders().get(fromName);
        if (isNullOrUndefined(header)) {
            return;
        }
        if (!isNullOrUndefined(doc.getComponents().getHeaders().get(toName))) {
            return;
        }
        int index = indexOfKey(doc.getComponents().getHeaders(), fromName);
        doc.getComponents().removeHeader(fromName);
        doc.getComponents().insertHeader(toName, header, index);
        this._headerRenamed = true;
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

    /**
     * Returns the index of the given key in the map's iteration order.
     */
    private static int indexOfKey(Map<String, ?> map, String key) {
        return new ArrayList<>(map.keySet()).indexOf(key);
    }

}
