package io.apicurio.datamodels.cmd.commands;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.models.Document;
import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.openapi.OpenApiHeader;
import io.apicurio.datamodels.models.openapi.v3x.OpenApi3xComponents;
import io.apicurio.datamodels.models.openapi.v3x.OpenApi3xDocument;
import io.apicurio.datamodels.util.LoggerUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * A command used to delete a single reusable header definition from a document.
 * @author eric.wittmann@gmail.com
 */
public class DeleteHeaderDefinitionCommand extends AbstractCommand {

    public String _definitionName;

    public ObjectNode _oldDefinition;
    public int _oldIndex;

    public DeleteHeaderDefinitionCommand() {
    }

    public DeleteHeaderDefinitionCommand(String definitionName) {
        this._definitionName = definitionName;
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(Document)
     */
    @Override
    public void execute(Document document) {
        LoggerUtil.info("[DeleteHeaderDefinitionCommand] Executing.");
        this._oldDefinition = null;

        OpenApi3xComponents components = ((OpenApi3xDocument) document).getComponents();
        if (this.isNullOrUndefined(components) || this.isNullOrUndefined(components.getHeaders())) {
            return;
        }
        OpenApiHeader header = components.getHeaders().get(this._definitionName);
        if (!this.isNullOrUndefined(header)) {
            this._oldDefinition = Library.writeNode(header);
            List<String> headerNames = new ArrayList<>(components.getHeaders().keySet());
            this._oldIndex = headerNames.indexOf(this._definitionName);
            components.removeHeader(this._definitionName);
        }
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(Document)
     */
    @Override
    public void undo(Document document) {
        LoggerUtil.info("[DeleteHeaderDefinitionCommand] Reverting.");
        if (this.isNullOrUndefined(this._oldDefinition)) {
            return;
        }

        OpenApi3xComponents components = ((OpenApi3xDocument) document).getComponents();
        if (this.isNullOrUndefined(components)) {
            components = ((OpenApi3xDocument) document).createComponents();
            ((OpenApi3xDocument) document).setComponents(components);
        }
        OpenApiHeader header = components.createHeader();
        Library.readNode(this._oldDefinition, header);
        components.insertHeader(this._definitionName, header, this._oldIndex);
    }

}
