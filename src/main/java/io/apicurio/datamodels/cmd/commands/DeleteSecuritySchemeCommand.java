package io.apicurio.datamodels.cmd.commands;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.models.Document;
import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.SecurityScheme;
import io.apicurio.datamodels.models.openapi.v20.OpenApi20Document;
import io.apicurio.datamodels.models.openapi.v20.OpenApi20SecurityDefinitions;
import io.apicurio.datamodels.models.openapi.v20.OpenApi20SecurityScheme;
import io.apicurio.datamodels.models.openapi.v30.OpenApi30Components;
import io.apicurio.datamodels.models.openapi.v30.OpenApi30Document;
import io.apicurio.datamodels.models.openapi.v31.OpenApi31Components;
import io.apicurio.datamodels.models.openapi.v31.OpenApi31Document;
import io.apicurio.datamodels.util.LoggerUtil;
import io.apicurio.datamodels.util.ModelTypeUtil;

/**
 * A command used to delete a single security scheme from a document.
 * @author eric.wittmann@gmail.com
 */
public class DeleteSecuritySchemeCommand extends AbstractCommand {

    public String _schemeName;

    public ObjectNode _oldScheme;

    public DeleteSecuritySchemeCommand() {
    }

    public DeleteSecuritySchemeCommand(String schemeName) {
        this._schemeName = schemeName;
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(Document)
     */
    @Override
    public void execute(Document document) {
        LoggerUtil.info("[DeleteSecuritySchemeCommand] Executing.");
        this._oldScheme = null;

        if (ModelTypeUtil.isOpenApi2Model(document)) {
            OpenApi20SecurityDefinitions defs = ((OpenApi20Document) document).getSecurityDefinitions();
            if (this.isNullOrUndefined(defs)) {
                return;
            }
            OpenApi20SecurityScheme scheme = defs.getItem(this._schemeName);
            if (!this.isNullOrUndefined(scheme)) {
                this._oldScheme = Library.writeNode(scheme);
                defs.removeItem(this._schemeName);
            }
        } else if (ModelTypeUtil.isOpenApi30Model(document)) {
            OpenApi30Components components = ((OpenApi30Document) document).getComponents();
            if (this.isNullOrUndefined(components) || this.isNullOrUndefined(components.getSecuritySchemes())) {
                return;
            }
            SecurityScheme scheme = components.getSecuritySchemes().get(this._schemeName);
            if (!this.isNullOrUndefined(scheme)) {
                this._oldScheme = Library.writeNode((Node) scheme);
                components.removeSecurityScheme(this._schemeName);
            }
        } else if (ModelTypeUtil.isOpenApi31Model(document)) {
            OpenApi31Components components = ((OpenApi31Document) document).getComponents();
            if (this.isNullOrUndefined(components) || this.isNullOrUndefined(components.getSecuritySchemes())) {
                return;
            }
            SecurityScheme scheme = components.getSecuritySchemes().get(this._schemeName);
            if (!this.isNullOrUndefined(scheme)) {
                this._oldScheme = Library.writeNode((Node) scheme);
                components.removeSecurityScheme(this._schemeName);
            }
        }
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(Document)
     */
    @Override
    public void undo(Document document) {
        LoggerUtil.info("[DeleteSecuritySchemeCommand] Reverting.");
        if (this.isNullOrUndefined(this._oldScheme)) {
            return;
        }

        if (ModelTypeUtil.isOpenApi2Model(document)) {
            OpenApi20SecurityDefinitions defs = ((OpenApi20Document) document).getSecurityDefinitions();
            if (this.isNullOrUndefined(defs)) {
                defs = ((OpenApi20Document) document).createSecurityDefinitions();
                ((OpenApi20Document) document).setSecurityDefinitions(defs);
            }
            OpenApi20SecurityScheme scheme = defs.createSecurityScheme();
            Library.readNode(this._oldScheme, scheme);
            defs.addItem(this._schemeName, scheme);
        } else if (ModelTypeUtil.isOpenApi30Model(document)) {
            OpenApi30Components components = ((OpenApi30Document) document).getComponents();
            if (this.isNullOrUndefined(components)) {
                components = ((OpenApi30Document) document).createComponents();
                ((OpenApi30Document) document).setComponents(components);
            }
            SecurityScheme scheme = components.createSecurityScheme();
            Library.readNode(this._oldScheme, scheme);
            components.addSecurityScheme(this._schemeName, scheme);
        } else if (ModelTypeUtil.isOpenApi31Model(document)) {
            OpenApi31Components components = ((OpenApi31Document) document).getComponents();
            if (this.isNullOrUndefined(components)) {
                components = ((OpenApi31Document) document).createComponents();
                ((OpenApi31Document) document).setComponents(components);
            }
            SecurityScheme scheme = components.createSecurityScheme();
            Library.readNode(this._oldScheme, scheme);
            components.addSecurityScheme(this._schemeName, scheme);
        }
    }

}
