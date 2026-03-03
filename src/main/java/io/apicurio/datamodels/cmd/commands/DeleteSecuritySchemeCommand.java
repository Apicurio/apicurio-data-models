package io.apicurio.datamodels.cmd.commands;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.models.Document;
import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.SecurityScheme;
import io.apicurio.datamodels.models.asyncapi.AsyncApiComponents;
import io.apicurio.datamodels.models.asyncapi.AsyncApiDocument;
import io.apicurio.datamodels.models.openapi.v20.OpenApi20Document;
import io.apicurio.datamodels.models.openapi.v20.OpenApi20SecurityDefinitions;
import io.apicurio.datamodels.models.openapi.v20.OpenApi20SecurityScheme;
import io.apicurio.datamodels.models.openapi.v30.OpenApi30Components;
import io.apicurio.datamodels.models.openapi.v30.OpenApi30Document;
import io.apicurio.datamodels.models.openapi.v31.OpenApi31Components;
import io.apicurio.datamodels.models.openapi.v31.OpenApi31Document;
import io.apicurio.datamodels.util.LoggerUtil;
import io.apicurio.datamodels.util.ModelTypeUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * A command used to delete a single security scheme from a document.
 * @author eric.wittmann@gmail.com
 */
public class DeleteSecuritySchemeCommand extends AbstractCommand {

    public String _schemeName;

    public ObjectNode _oldScheme;
    public int _oldIndex;

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
                this._oldIndex = defs.getItemNames().indexOf(this._schemeName);
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
                List<String> schemeNames = new ArrayList<>(components.getSecuritySchemes().keySet());
                this._oldIndex = schemeNames.indexOf(this._schemeName);
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
                List<String> schemeNames = new ArrayList<>(components.getSecuritySchemes().keySet());
                this._oldIndex = schemeNames.indexOf(this._schemeName);
                components.removeSecurityScheme(this._schemeName);
            }
        } else if (ModelTypeUtil.isAsyncApiModel(document)) {
            AsyncApiComponents components = ((AsyncApiDocument) document).getComponents();
            if (this.isNullOrUndefined(components) || this.isNullOrUndefined(components.getSecuritySchemes())) {
                return;
            }
            SecurityScheme scheme = components.getSecuritySchemes().get(this._schemeName);
            if (!this.isNullOrUndefined(scheme)) {
                this._oldScheme = Library.writeNode((Node) scheme);
                List<String> schemeNames = new ArrayList<>(components.getSecuritySchemes().keySet());
                this._oldIndex = schemeNames.indexOf(this._schemeName);
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
            defs.insertItem(this._schemeName, scheme, this._oldIndex);
        } else if (ModelTypeUtil.isOpenApi30Model(document)) {
            OpenApi30Components components = ((OpenApi30Document) document).getComponents();
            if (this.isNullOrUndefined(components)) {
                components = ((OpenApi30Document) document).createComponents();
                ((OpenApi30Document) document).setComponents(components);
            }
            SecurityScheme scheme = components.createSecurityScheme();
            Library.readNode(this._oldScheme, scheme);
            components.insertSecurityScheme(this._schemeName, scheme, this._oldIndex);
        } else if (ModelTypeUtil.isOpenApi31Model(document)) {
            OpenApi31Components components = ((OpenApi31Document) document).getComponents();
            if (this.isNullOrUndefined(components)) {
                components = ((OpenApi31Document) document).createComponents();
                ((OpenApi31Document) document).setComponents(components);
            }
            SecurityScheme scheme = components.createSecurityScheme();
            Library.readNode(this._oldScheme, scheme);
            components.insertSecurityScheme(this._schemeName, scheme, this._oldIndex);
        } else if (ModelTypeUtil.isAsyncApiModel(document)) {
            AsyncApiComponents components = ((AsyncApiDocument) document).getComponents();
            if (this.isNullOrUndefined(components)) {
                components = ((AsyncApiDocument) document).createComponents();
                ((AsyncApiDocument) document).setComponents(components);
            }
            SecurityScheme scheme = components.createSecurityScheme();
            Library.readNode(this._oldScheme, scheme);
            components.insertSecurityScheme(this._schemeName, scheme, this._oldIndex);
        }
    }

}
