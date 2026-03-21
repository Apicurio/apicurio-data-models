package io.apicurio.datamodels.cmd.commands;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.models.Document;
import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.openapi.OpenApiExample;
import io.apicurio.datamodels.models.openrpc.OpenRpcComponents;
import io.apicurio.datamodels.models.openrpc.OpenRpcDocument;
import io.apicurio.datamodels.models.openrpc.OpenRpcExample;
import io.apicurio.datamodels.models.openapi.v30.OpenApi30Components;
import io.apicurio.datamodels.models.openapi.v30.OpenApi30Document;
import io.apicurio.datamodels.models.openapi.v31.OpenApi31Components;
import io.apicurio.datamodels.models.openapi.v31.OpenApi31Document;
import io.apicurio.datamodels.util.LoggerUtil;
import io.apicurio.datamodels.util.ModelTypeUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * A command used to delete a single reusable example definition from a document.
 * @author eric.wittmann@gmail.com
 */
public class DeleteExampleDefinitionCommand extends AbstractCommand {

    public String _definitionName;

    public ObjectNode _oldDefinition;
    public int _oldIndex;

    public DeleteExampleDefinitionCommand() {
    }

    public DeleteExampleDefinitionCommand(String definitionName) {
        this._definitionName = definitionName;
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(Document)
     */
    @Override
    public void execute(Document document) {
        LoggerUtil.info("[DeleteExampleDefinitionCommand] Executing.");
        this._oldDefinition = null;

        if (ModelTypeUtil.isOpenApi30Model(document)) {
            OpenApi30Components components = ((OpenApi30Document) document).getComponents();
            if (this.isNullOrUndefined(components) || this.isNullOrUndefined(components.getExamples())) {
                return;
            }
            OpenApiExample example = components.getExamples().get(this._definitionName);
            if (!this.isNullOrUndefined(example)) {
                this._oldDefinition = Library.writeNode((Node) example);
                List<String> exampleNames = new ArrayList<>(components.getExamples().keySet());
                this._oldIndex = exampleNames.indexOf(this._definitionName);
                components.removeExample(this._definitionName);
            }
        } else if (ModelTypeUtil.isOpenApi31Model(document)) {
            OpenApi31Components components = ((OpenApi31Document) document).getComponents();
            if (this.isNullOrUndefined(components) || this.isNullOrUndefined(components.getExamples())) {
                return;
            }
            OpenApiExample example = components.getExamples().get(this._definitionName);
            if (!this.isNullOrUndefined(example)) {
                this._oldDefinition = Library.writeNode((Node) example);
                List<String> exampleNames = new ArrayList<>(components.getExamples().keySet());
                this._oldIndex = exampleNames.indexOf(this._definitionName);
                components.removeExample(this._definitionName);
            }
        } else if (ModelTypeUtil.isOpenRpcModel(document)) {
            OpenRpcComponents components = ((OpenRpcDocument) document).getComponents();
            if (this.isNullOrUndefined(components) || this.isNullOrUndefined(components.getExamples())) {
                return;
            }
            OpenRpcExample example = components.getExamples().get(this._definitionName);
            if (!this.isNullOrUndefined(example)) {
                this._oldDefinition = Library.writeNode((Node) example);
                List<String> exampleNames = new ArrayList<>(components.getExamples().keySet());
                this._oldIndex = exampleNames.indexOf(this._definitionName);
                components.removeExample(this._definitionName);
            }
        }
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(Document)
     */
    @Override
    public void undo(Document document) {
        LoggerUtil.info("[DeleteExampleDefinitionCommand] Reverting.");
        if (this.isNullOrUndefined(this._oldDefinition)) {
            return;
        }

        if (ModelTypeUtil.isOpenApi30Model(document)) {
            OpenApi30Components components = ((OpenApi30Document) document).getComponents();
            if (this.isNullOrUndefined(components)) {
                components = ((OpenApi30Document) document).createComponents();
                ((OpenApi30Document) document).setComponents(components);
            }
            OpenApiExample example = components.createExample();
            Library.readNode(this._oldDefinition, example);
            components.insertExample(this._definitionName, example, this._oldIndex);
        } else if (ModelTypeUtil.isOpenApi31Model(document)) {
            OpenApi31Components components = ((OpenApi31Document) document).getComponents();
            if (this.isNullOrUndefined(components)) {
                components = ((OpenApi31Document) document).createComponents();
                ((OpenApi31Document) document).setComponents(components);
            }
            OpenApiExample example = components.createExample();
            Library.readNode(this._oldDefinition, example);
            components.insertExample(this._definitionName, example, this._oldIndex);
        } else if (ModelTypeUtil.isOpenRpcModel(document)) {
            OpenRpcDocument doc = (OpenRpcDocument) document;
            OpenRpcComponents components = doc.getComponents();
            if (this.isNullOrUndefined(components)) {
                components = doc.createComponents();
                doc.setComponents(components);
            }
            OpenRpcExample example = components.createExample();
            Library.readNode(this._oldDefinition, example);
            components.insertExample(this._definitionName, example, this._oldIndex);
        }
    }

}
