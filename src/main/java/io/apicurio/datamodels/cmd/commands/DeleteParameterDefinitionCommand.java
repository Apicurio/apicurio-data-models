package io.apicurio.datamodels.cmd.commands;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.models.Document;
import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.openapi.v20.OpenApi20Document;
import io.apicurio.datamodels.models.openapi.v20.OpenApi20Parameter;
import io.apicurio.datamodels.models.openapi.v30.OpenApi30Components;
import io.apicurio.datamodels.models.openapi.v30.OpenApi30Document;
import io.apicurio.datamodels.models.openapi.v30.OpenApi30Parameter;
import io.apicurio.datamodels.models.openapi.v31.OpenApi31Components;
import io.apicurio.datamodels.models.openapi.v31.OpenApi31Document;
import io.apicurio.datamodels.models.openapi.v31.OpenApi31Parameter;
import io.apicurio.datamodels.util.LoggerUtil;
import io.apicurio.datamodels.util.ModelTypeUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * A command used to delete a single reusable parameter definition from a document.
 * @author eric.wittmann@gmail.com
 */
public class DeleteParameterDefinitionCommand extends AbstractCommand {

    public String _definitionName;

    public ObjectNode _oldDefinition;
    public int _oldIndex;

    public DeleteParameterDefinitionCommand() {
    }

    public DeleteParameterDefinitionCommand(String definitionName) {
        this._definitionName = definitionName;
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(Document)
     */
    @Override
    public void execute(Document document) {
        LoggerUtil.info("[DeleteParameterDefinitionCommand] Executing.");
        this._oldDefinition = null;

        if (ModelTypeUtil.isOpenApi2Model(document)) {
            OpenApi20Document doc = (OpenApi20Document) document;
            if (this.isNullOrUndefined(doc.getParameters())) {
                return;
            }
            OpenApi20Parameter param = doc.getParameters().getItem(this._definitionName);
            if (!this.isNullOrUndefined(param)) {
                this._oldDefinition = Library.writeNode(param);
                this._oldIndex = doc.getParameters().getItemNames().indexOf(this._definitionName);
                doc.getParameters().removeItem(this._definitionName);
            }
        } else if (ModelTypeUtil.isOpenApi30Model(document)) {
            OpenApi30Components components = ((OpenApi30Document) document).getComponents();
            if (this.isNullOrUndefined(components) || this.isNullOrUndefined(components.getParameters())) {
                return;
            }
            OpenApi30Parameter param = (OpenApi30Parameter) components.getParameters().get(this._definitionName);
            if (!this.isNullOrUndefined(param)) {
                this._oldDefinition = Library.writeNode((Node) param);
                List<String> paramNames = new ArrayList<>(components.getParameters().keySet());
                this._oldIndex = paramNames.indexOf(this._definitionName);
                components.removeParameter(this._definitionName);
            }
        } else if (ModelTypeUtil.isOpenApi31Model(document)) {
            OpenApi31Components components = ((OpenApi31Document) document).getComponents();
            if (this.isNullOrUndefined(components) || this.isNullOrUndefined(components.getParameters())) {
                return;
            }
            OpenApi31Parameter param = (OpenApi31Parameter) components.getParameters().get(this._definitionName);
            if (!this.isNullOrUndefined(param)) {
                this._oldDefinition = Library.writeNode((Node) param);
                List<String> paramNames = new ArrayList<>(components.getParameters().keySet());
                this._oldIndex = paramNames.indexOf(this._definitionName);
                components.removeParameter(this._definitionName);
            }
        }
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(Document)
     */
    @Override
    public void undo(Document document) {
        LoggerUtil.info("[DeleteParameterDefinitionCommand] Reverting.");
        if (this.isNullOrUndefined(this._oldDefinition)) {
            return;
        }

        if (ModelTypeUtil.isOpenApi2Model(document)) {
            OpenApi20Document doc = (OpenApi20Document) document;
            if (this.isNullOrUndefined(doc.getParameters())) {
                doc.setParameters(doc.createParameterDefinitions());
            }
            OpenApi20Parameter param = doc.getParameters().createParameter();
            Library.readNode(this._oldDefinition, param);
            doc.getParameters().insertItem(this._definitionName, param, this._oldIndex);
        } else if (ModelTypeUtil.isOpenApi30Model(document)) {
            OpenApi30Components components = ((OpenApi30Document) document).getComponents();
            if (this.isNullOrUndefined(components)) {
                components = ((OpenApi30Document) document).createComponents();
                ((OpenApi30Document) document).setComponents(components);
            }
            OpenApi30Parameter param = (OpenApi30Parameter) components.createParameter();
            Library.readNode(this._oldDefinition, param);
            components.insertParameter(this._definitionName, param, this._oldIndex);
        } else if (ModelTypeUtil.isOpenApi31Model(document)) {
            OpenApi31Components components = ((OpenApi31Document) document).getComponents();
            if (this.isNullOrUndefined(components)) {
                components = ((OpenApi31Document) document).createComponents();
                ((OpenApi31Document) document).setComponents(components);
            }
            OpenApi31Parameter param = (OpenApi31Parameter) components.createParameter();
            Library.readNode(this._oldDefinition, param);
            components.insertParameter(this._definitionName, param, this._oldIndex);
        }
    }

}
