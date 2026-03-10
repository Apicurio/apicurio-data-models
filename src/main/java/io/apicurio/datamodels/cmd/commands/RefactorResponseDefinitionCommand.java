package io.apicurio.datamodels.cmd.commands;

import java.util.ArrayList;
import java.util.Map;

import io.apicurio.datamodels.TraverserDirection;
import io.apicurio.datamodels.VisitorUtil;
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.deref.AllReferenceableNodeVisitor;
import io.apicurio.datamodels.models.Document;
import io.apicurio.datamodels.models.Referenceable;
import io.apicurio.datamodels.models.openapi.OpenApiResponse;
import io.apicurio.datamodels.models.openapi.v20.OpenApi20Document;
import io.apicurio.datamodels.models.openapi.v20.OpenApi20Response;
import io.apicurio.datamodels.models.openapi.v30.OpenApi30Document;
import io.apicurio.datamodels.models.openapi.v31.OpenApi31Document;
import io.apicurio.datamodels.util.LoggerUtil;
import io.apicurio.datamodels.util.ModelTypeUtil;

/**
 * A command used to rename a response definition and update all $ref references.
 * @author eric.wittmann@gmail.com
 */
public class RefactorResponseDefinitionCommand extends AbstractCommand {

    public String _oldName;
    public String _newName;

    public boolean _responseRenamed;

    public RefactorResponseDefinitionCommand() {
    }

    public RefactorResponseDefinitionCommand(String oldName, String newName) {
        this._oldName = oldName;
        this._newName = newName;
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(Document)
     */
    @Override
    public void execute(Document document) {
        LoggerUtil.info("[RefactorResponseDefinitionCommand] Executing.");
        this._responseRenamed = false;

        if (ModelTypeUtil.isOpenApi2Model(document)) {
            renameInOas20((OpenApi20Document) document, _oldName, _newName);
        } else if (ModelTypeUtil.isOpenApi30Model(document)) {
            renameInOas30((OpenApi30Document) document, _oldName, _newName);
        } else if (ModelTypeUtil.isOpenApi31Model(document)) {
            renameInOas31((OpenApi31Document) document, _oldName, _newName);
        } else {
            return;
        }

        if (this._responseRenamed) {
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
        LoggerUtil.info("[RefactorResponseDefinitionCommand] Reverting.");
        if (!this._responseRenamed) {
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
        if (isNullOrUndefined(doc.getResponses())) {
            return;
        }
        OpenApi20Response response = (OpenApi20Response) doc.getResponses().getItem(fromName);
        if (isNullOrUndefined(response)) {
            return;
        }
        if (!isNullOrUndefined(doc.getResponses().getItem(toName))) {
            return;
        }
        int index = doc.getResponses().getItemNames().indexOf(fromName);
        doc.getResponses().removeItem(fromName);
        doc.getResponses().insertItem(toName, response, index);
        this._responseRenamed = true;
    }

    private void renameInOas30(OpenApi30Document doc, String fromName, String toName) {
        if (isNullOrUndefined(doc.getComponents())) {
            return;
        }
        OpenApiResponse response = doc.getComponents().getResponses().get(fromName);
        if (isNullOrUndefined(response)) {
            return;
        }
        if (!isNullOrUndefined(doc.getComponents().getResponses().get(toName))) {
            return;
        }
        int index = indexOfKey(doc.getComponents().getResponses(), fromName);
        doc.getComponents().removeResponse(fromName);
        doc.getComponents().insertResponse(toName, response, index);
        this._responseRenamed = true;
    }

    private void renameInOas31(OpenApi31Document doc, String fromName, String toName) {
        if (isNullOrUndefined(doc.getComponents())) {
            return;
        }
        OpenApiResponse response = doc.getComponents().getResponses().get(fromName);
        if (isNullOrUndefined(response)) {
            return;
        }
        if (!isNullOrUndefined(doc.getComponents().getResponses().get(toName))) {
            return;
        }
        int index = indexOfKey(doc.getComponents().getResponses(), fromName);
        doc.getComponents().removeResponse(fromName);
        doc.getComponents().insertResponse(toName, response, index);
        this._responseRenamed = true;
    }

    private String getRefPrefix(Document document) {
        if (ModelTypeUtil.isOpenApi2Model(document)) {
            return "#/responses/";
        }
        return "#/components/responses/";
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
