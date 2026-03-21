package io.apicurio.datamodels.cmd.commands;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.models.Document;
import io.apicurio.datamodels.models.SecurityScheme;
import io.apicurio.datamodels.models.asyncapi.AsyncApiSecurityScheme;
import io.apicurio.datamodels.models.openapi.OpenApiSecurityScheme;
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

/**
 * A command used to add a new security scheme to a document.
 * Takes a serialized security scheme as input, which allows the caller
 * to fully configure the scheme before adding it.
 * @author eric.wittmann@gmail.com
 */
public class AddSecuritySchemeCommand extends AbstractCommand {

    public String _schemeName;
    public ObjectNode _schemeObj;

    public boolean _schemeCreated;
    public boolean _nullParent;

    public AddSecuritySchemeCommand() {
    }

    public AddSecuritySchemeCommand(String schemeName, ObjectNode schemeObj) {
        this._schemeName = schemeName;
        this._schemeObj = schemeObj;
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(Document)
     */
    @Override
    public void execute(Document document) {
        LoggerUtil.info("[AddSecuritySchemeCommand] Executing.");
        this._schemeCreated = false;
        this._nullParent = false;

        if (ModelTypeUtil.isOpenApi2Model(document)) {
            executeForOpenApi20((OpenApi20Document) document);
        } else if (ModelTypeUtil.isOpenApi30Model(document)) {
            executeForOpenApi30((OpenApi30Document) document);
        } else if (ModelTypeUtil.isOpenApi31Model(document)) {
            executeForOpenApi31((OpenApi31Document) document);
        } else if (ModelTypeUtil.isAsyncApiModel(document)) {
            executeForAsyncApi((AsyncApiDocument) document);
        }
    }

    private void executeForOpenApi20(OpenApi20Document doc) {
        OpenApi20SecurityDefinitions definitions = doc.getSecurityDefinitions();
        if (this.isNullOrUndefined(definitions)) {
            definitions = doc.createSecurityDefinitions();
            doc.setSecurityDefinitions(definitions);
            this._nullParent = true;
        }

        // Check if scheme already exists
        if (!this.isNullOrUndefined(definitions.getItem(this._schemeName))) {
            return;
        }

        OpenApi20SecurityScheme newScheme = definitions.createSecurityScheme();
        Library.readNode(this._schemeObj, newScheme);
        definitions.addItem(this._schemeName, newScheme);
        this._schemeCreated = true;
    }

    private void executeForOpenApi30(OpenApi30Document doc) {
        OpenApi30Components components = doc.getComponents();
        if (this.isNullOrUndefined(components)) {
            components = doc.createComponents();
            doc.setComponents(components);
            this._nullParent = true;
        }

        // Check if scheme already exists
        if (!this.isNullOrUndefined(components.getSecuritySchemes()) &&
                components.getSecuritySchemes().containsKey(this._schemeName)) {
            return;
        }

        OpenApiSecurityScheme newScheme = components.createSecurityScheme();
        Library.readNode(this._schemeObj, newScheme);
        components.addSecurityScheme(this._schemeName, newScheme);
        this._schemeCreated = true;
    }

    private void executeForOpenApi31(OpenApi31Document doc) {
        OpenApi31Components components = doc.getComponents();
        if (this.isNullOrUndefined(components)) {
            components = doc.createComponents();
            doc.setComponents(components);
            this._nullParent = true;
        }

        // Check if scheme already exists
        if (!this.isNullOrUndefined(components.getSecuritySchemes()) &&
                components.getSecuritySchemes().containsKey(this._schemeName)) {
            return;
        }

        OpenApiSecurityScheme newScheme = components.createSecurityScheme();
        Library.readNode(this._schemeObj, newScheme);
        components.addSecurityScheme(this._schemeName, newScheme);
        this._schemeCreated = true;
    }

    private void executeForAsyncApi(AsyncApiDocument doc) {
        AsyncApiComponents components = doc.getComponents();
        if (this.isNullOrUndefined(components)) {
            components = doc.createComponents();
            doc.setComponents(components);
            this._nullParent = true;
        }

        // Check if scheme already exists
        if (!this.isNullOrUndefined(components.getSecuritySchemes()) &&
                components.getSecuritySchemes().containsKey(this._schemeName)) {
            return;
        }

        AsyncApiSecurityScheme newScheme = components.createSecurityScheme();
        Library.readNode(this._schemeObj, newScheme);
        components.addSecurityScheme(this._schemeName, newScheme);
        this._schemeCreated = true;
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(Document)
     */
    @Override
    public void undo(Document document) {
        LoggerUtil.info("[AddSecuritySchemeCommand] Reverting.");
        if (!this._schemeCreated) {
            return;
        }

        if (ModelTypeUtil.isOpenApi2Model(document)) {
            OpenApi20Document doc = (OpenApi20Document) document;
            if (this._nullParent) {
                doc.setSecurityDefinitions(null);
            } else {
                OpenApi20SecurityDefinitions definitions = doc.getSecurityDefinitions();
                if (!this.isNullOrUndefined(definitions)) {
                    definitions.removeItem(this._schemeName);
                }
            }
        } else if (ModelTypeUtil.isOpenApi30Model(document)) {
            OpenApi30Document doc = (OpenApi30Document) document;
            if (this._nullParent) {
                doc.setComponents(null);
            } else {
                OpenApi30Components components = doc.getComponents();
                if (!this.isNullOrUndefined(components)) {
                    components.removeSecurityScheme(this._schemeName);
                }
            }
        } else if (ModelTypeUtil.isOpenApi31Model(document)) {
            OpenApi31Document doc = (OpenApi31Document) document;
            if (this._nullParent) {
                doc.setComponents(null);
            } else {
                OpenApi31Components components = doc.getComponents();
                if (!this.isNullOrUndefined(components)) {
                    components.removeSecurityScheme(this._schemeName);
                }
            }
        } else if (ModelTypeUtil.isAsyncApiModel(document)) {
            AsyncApiDocument doc = (AsyncApiDocument) document;
            if (this._nullParent) {
                doc.setComponents(null);
            } else {
                AsyncApiComponents components = doc.getComponents();
                if (!this.isNullOrUndefined(components)) {
                    components.removeSecurityScheme(this._schemeName);
                }
            }
        }
    }

}
