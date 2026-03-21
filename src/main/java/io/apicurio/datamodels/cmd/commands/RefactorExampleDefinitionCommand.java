package io.apicurio.datamodels.cmd.commands;

import java.util.ArrayList;
import java.util.Map;

import io.apicurio.datamodels.TraverserDirection;
import io.apicurio.datamodels.VisitorUtil;
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.deref.AllReferenceableNodeVisitor;
import io.apicurio.datamodels.models.Document;
import io.apicurio.datamodels.models.Referenceable;
import io.apicurio.datamodels.models.openapi.OpenApiExample;
import io.apicurio.datamodels.models.openrpc.OpenRpcDocument;
import io.apicurio.datamodels.models.openrpc.OpenRpcExample;
import io.apicurio.datamodels.models.openapi.v30.OpenApi30Document;
import io.apicurio.datamodels.models.openapi.v31.OpenApi31Document;
import io.apicurio.datamodels.util.LoggerUtil;
import io.apicurio.datamodels.util.ModelTypeUtil;

/**
 * A command used to rename an example definition and update all $ref references.
 * @author eric.wittmann@gmail.com
 */
public class RefactorExampleDefinitionCommand extends AbstractCommand {

    public String _oldName;
    public String _newName;

    public boolean _exampleRenamed;

    public RefactorExampleDefinitionCommand() {
    }

    public RefactorExampleDefinitionCommand(String oldName, String newName) {
        this._oldName = oldName;
        this._newName = newName;
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(Document)
     */
    @Override
    public void execute(Document document) {
        LoggerUtil.info("[RefactorExampleDefinitionCommand] Executing.");
        this._exampleRenamed = false;

        if (ModelTypeUtil.isOpenApi30Model(document)) {
            renameInOas30((OpenApi30Document) document, _oldName, _newName);
        } else if (ModelTypeUtil.isOpenApi31Model(document)) {
            renameInOas31((OpenApi31Document) document, _oldName, _newName);
        } else if (ModelTypeUtil.isOpenRpcModel(document)) {
            renameInOpenRpc((OpenRpcDocument) document, _oldName, _newName);
        } else {
            return;
        }

        if (this._exampleRenamed) {
            String oldRef = "#/components/examples/" + _oldName;
            String newRef = "#/components/examples/" + _newName;
            updateRefs(document, oldRef, newRef);
        }
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(Document)
     */
    @Override
    public void undo(Document document) {
        LoggerUtil.info("[RefactorExampleDefinitionCommand] Reverting.");
        if (!this._exampleRenamed) {
            return;
        }

        if (ModelTypeUtil.isOpenApi30Model(document)) {
            renameInOas30((OpenApi30Document) document, _newName, _oldName);
        } else if (ModelTypeUtil.isOpenApi31Model(document)) {
            renameInOas31((OpenApi31Document) document, _newName, _oldName);
        } else if (ModelTypeUtil.isOpenRpcModel(document)) {
            renameInOpenRpc((OpenRpcDocument) document, _newName, _oldName);
        }

        String oldRef = "#/components/examples/" + _oldName;
        String newRef = "#/components/examples/" + _newName;
        updateRefs(document, newRef, oldRef);
    }

    private void renameInOas30(OpenApi30Document doc, String fromName, String toName) {
        if (isNullOrUndefined(doc.getComponents())) {
            return;
        }
        OpenApiExample example = doc.getComponents().getExamples().get(fromName);
        if (isNullOrUndefined(example)) {
            return;
        }
        if (!isNullOrUndefined(doc.getComponents().getExamples().get(toName))) {
            return;
        }
        int index = indexOfKey(doc.getComponents().getExamples(), fromName);
        doc.getComponents().removeExample(fromName);
        doc.getComponents().insertExample(toName, example, index);
        this._exampleRenamed = true;
    }

    private void renameInOas31(OpenApi31Document doc, String fromName, String toName) {
        if (isNullOrUndefined(doc.getComponents())) {
            return;
        }
        OpenApiExample example = doc.getComponents().getExamples().get(fromName);
        if (isNullOrUndefined(example)) {
            return;
        }
        if (!isNullOrUndefined(doc.getComponents().getExamples().get(toName))) {
            return;
        }
        int index = indexOfKey(doc.getComponents().getExamples(), fromName);
        doc.getComponents().removeExample(fromName);
        doc.getComponents().insertExample(toName, example, index);
        this._exampleRenamed = true;
    }

    private void renameInOpenRpc(OpenRpcDocument doc, String fromName, String toName) {
        if (isNullOrUndefined(doc.getComponents())) {
            return;
        }
        OpenRpcExample example = doc.getComponents().getExamples().get(fromName);
        if (isNullOrUndefined(example)) {
            return;
        }
        if (!isNullOrUndefined(doc.getComponents().getExamples().get(toName))) {
            return;
        }
        int index = indexOfKey(doc.getComponents().getExamples(), fromName);
        doc.getComponents().removeExample(fromName);
        doc.getComponents().insertExample(toName, example, index);
        this._exampleRenamed = true;
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
