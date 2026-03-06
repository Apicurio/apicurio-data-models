package io.apicurio.datamodels.cmd.commands;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.models.Document;
import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.openapi.OpenApiRequestBody;
import io.apicurio.datamodels.models.openapi.v30.OpenApi30Components;
import io.apicurio.datamodels.models.openapi.v30.OpenApi30Document;
import io.apicurio.datamodels.models.openapi.v31.OpenApi31Components;
import io.apicurio.datamodels.models.openapi.v31.OpenApi31Document;
import io.apicurio.datamodels.util.LoggerUtil;
import io.apicurio.datamodels.util.ModelTypeUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * A command used to delete a single reusable request body definition from a document.
 * @author eric.wittmann@gmail.com
 */
public class DeleteRequestBodyDefinitionCommand extends AbstractCommand {

    public String _definitionName;

    public ObjectNode _oldDefinition;
    public int _oldIndex;

    public DeleteRequestBodyDefinitionCommand() {
    }

    public DeleteRequestBodyDefinitionCommand(String definitionName) {
        this._definitionName = definitionName;
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(Document)
     */
    @Override
    public void execute(Document document) {
        LoggerUtil.info("[DeleteRequestBodyDefinitionCommand] Executing.");
        this._oldDefinition = null;

        if (ModelTypeUtil.isOpenApi30Model(document)) {
            OpenApi30Components components = ((OpenApi30Document) document).getComponents();
            if (this.isNullOrUndefined(components) || this.isNullOrUndefined(components.getRequestBodies())) {
                return;
            }
            OpenApiRequestBody requestBody = components.getRequestBodies().get(this._definitionName);
            if (!this.isNullOrUndefined(requestBody)) {
                this._oldDefinition = Library.writeNode((Node) requestBody);
                List<String> requestBodyNames = new ArrayList<>(components.getRequestBodies().keySet());
                this._oldIndex = requestBodyNames.indexOf(this._definitionName);
                components.removeRequestBody(this._definitionName);
            }
        } else if (ModelTypeUtil.isOpenApi31Model(document)) {
            OpenApi31Components components = ((OpenApi31Document) document).getComponents();
            if (this.isNullOrUndefined(components) || this.isNullOrUndefined(components.getRequestBodies())) {
                return;
            }
            OpenApiRequestBody requestBody = components.getRequestBodies().get(this._definitionName);
            if (!this.isNullOrUndefined(requestBody)) {
                this._oldDefinition = Library.writeNode((Node) requestBody);
                List<String> requestBodyNames = new ArrayList<>(components.getRequestBodies().keySet());
                this._oldIndex = requestBodyNames.indexOf(this._definitionName);
                components.removeRequestBody(this._definitionName);
            }
        }
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(Document)
     */
    @Override
    public void undo(Document document) {
        LoggerUtil.info("[DeleteRequestBodyDefinitionCommand] Reverting.");
        if (this.isNullOrUndefined(this._oldDefinition)) {
            return;
        }

        if (ModelTypeUtil.isOpenApi30Model(document)) {
            OpenApi30Components components = ((OpenApi30Document) document).getComponents();
            if (this.isNullOrUndefined(components)) {
                components = ((OpenApi30Document) document).createComponents();
                ((OpenApi30Document) document).setComponents(components);
            }
            OpenApiRequestBody requestBody = components.createRequestBody();
            Library.readNode(this._oldDefinition, requestBody);
            components.insertRequestBody(this._definitionName, requestBody, this._oldIndex);
        } else if (ModelTypeUtil.isOpenApi31Model(document)) {
            OpenApi31Components components = ((OpenApi31Document) document).getComponents();
            if (this.isNullOrUndefined(components)) {
                components = ((OpenApi31Document) document).createComponents();
                ((OpenApi31Document) document).setComponents(components);
            }
            OpenApiRequestBody requestBody = components.createRequestBody();
            Library.readNode(this._oldDefinition, requestBody);
            components.insertRequestBody(this._definitionName, requestBody, this._oldIndex);
        }
    }

}
