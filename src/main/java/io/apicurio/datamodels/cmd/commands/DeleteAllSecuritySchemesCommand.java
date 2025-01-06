package io.apicurio.datamodels.cmd.commands;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.models.Document;
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
import io.apicurio.datamodels.util.NodeUtil;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * A command used to delete all security schemes from a document or operation.
 * @author eric.wittmann@gmail.com
 */
public class DeleteAllSecuritySchemesCommand extends AbstractCommand {

    public Map<String, ObjectNode> _oldSecuritySchemes;
    
    public DeleteAllSecuritySchemesCommand() {
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(Document)
     */
    @Override
    public void execute(Document document) {
        LoggerUtil.info("[DeleteAllSecuritySchemesCommand] Executing.");
        this._oldSecuritySchemes = new LinkedHashMap<>();

        Map<String, ? extends SecurityScheme> securitySchemes = getSecuritySchemes(document);
        if (this.isNullOrUndefined(securitySchemes) || securitySchemes.isEmpty()) {
            return;
        }
        securitySchemes.keySet().forEach(schemeName -> {
            SecurityScheme securityScheme = securitySchemes.get(schemeName);
            this._oldSecuritySchemes.put(schemeName, Library.writeNode(securityScheme));
        });
        clearSecuritySchemes(document);
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(Document)
     */
    @Override
    public void undo(Document document) {
        LoggerUtil.info("[DeleteAllSecuritySchemesCommand] Reverting.");
        if (this.isNullOrUndefined(_oldSecuritySchemes) || this._oldSecuritySchemes.isEmpty()) {
            return;
        }

        this._oldSecuritySchemes.keySet().forEach( name -> {
            SecurityScheme scheme = createSecurityScheme(document, name);
            Library.readNode(this._oldSecuritySchemes.get(name), scheme);
            addSecurityScheme(document, name, scheme);
        });
    }

    private Map<String, ? extends SecurityScheme> getSecuritySchemes(Document document) {
        if (ModelTypeUtil.isOpenApi2Model(document)) {
            OpenApi20SecurityDefinitions securityDefinitions = ((OpenApi20Document) document).getSecurityDefinitions();
            if (securityDefinitions != null) {
                Map<String, SecurityScheme> rval = new LinkedHashMap<>();
                for (String name : securityDefinitions.getItemNames()) {
                    OpenApi20SecurityScheme scheme = securityDefinitions.getItem(name);
                    rval.put(name, scheme);
                }
                return rval;
            }
        } else if (ModelTypeUtil.isOpenApi30Model(document)) {
            OpenApi30Components components = ((OpenApi30Document) document).getComponents();
            if (components != null) {
                return components.getSecuritySchemes();
            }
        } else if (ModelTypeUtil.isOpenApi31Model(document)) {
            OpenApi31Components components = ((OpenApi31Document) document).getComponents();
            if (components != null) {
                return components.getSecuritySchemes();
            }
        } else if (ModelTypeUtil.isAsyncApiModel(document)) {
            AsyncApiComponents components = ((AsyncApiDocument) document).getComponents();
            if (components != null) {
                return components.getSecuritySchemes();
            }
        }

        return null;
    }

    private void clearSecuritySchemes(Document document) {
        if (ModelTypeUtil.isOpenApi2Model(document)) {
            NodeUtil.setProperty(document, "securityDefinitions", null);
        } else if (ModelTypeUtil.isOpenApi30Model(document)) {
            OpenApi30Components components = ((OpenApi30Document) document).getComponents();
            if (components != null) {
                components.clearSecuritySchemes();
            }
        } else if (ModelTypeUtil.isOpenApi31Model(document)) {
            OpenApi31Components components = ((OpenApi31Document) document).getComponents();
            if (components != null) {
                components.clearSecuritySchemes();
            }
        } else if (ModelTypeUtil.isAsyncApiModel(document)) {
            AsyncApiComponents components = ((AsyncApiDocument) document).getComponents();
            if (components != null) {
                components.clearSecuritySchemes();
            }
        }
    }

    private SecurityScheme createSecurityScheme(Document document, String name) {
        if (ModelTypeUtil.isOpenApi2Model(document)) {
            OpenApi20SecurityDefinitions securityDefinitions = ((OpenApi20Document) document).getSecurityDefinitions();
            if (securityDefinitions == null) {
                securityDefinitions = ((OpenApi20Document) document).createSecurityDefinitions();
                ((OpenApi20Document) document).setSecurityDefinitions(securityDefinitions);
            }
            return securityDefinitions.createSecurityScheme();
        } else if (ModelTypeUtil.isOpenApi30Model(document)) {
            OpenApi30Components components = ((OpenApi30Document) document).getComponents();
            if (components == null) {
                components = ((OpenApi30Document) document).createComponents();
                ((OpenApi30Document) document).setComponents(components);
            }
            return components.createSecurityScheme();
        } else if (ModelTypeUtil.isOpenApi31Model(document)) {
            OpenApi31Components components = ((OpenApi31Document) document).getComponents();
            if (components == null) {
                components = ((OpenApi31Document) document).createComponents();
                ((OpenApi31Document) document).setComponents(components);
            }
            return components.createSecurityScheme();
        } else if (ModelTypeUtil.isAsyncApiModel(document)) {
            AsyncApiComponents components = ((AsyncApiDocument) document).getComponents();
            if (components == null) {
                components = ((AsyncApiDocument) document).createComponents();
                ((AsyncApiDocument) document).setComponents(components);
            }
            return components.createSecurityScheme();
        }

        return null;
    }

    private void addSecurityScheme(Document document, String name, SecurityScheme scheme) {
        if (ModelTypeUtil.isOpenApi2Model(document)) {
            OpenApi20SecurityDefinitions securityDefinitions = ((OpenApi20Document) document).getSecurityDefinitions();
            if (securityDefinitions == null) {
                securityDefinitions = ((OpenApi20Document) document).createSecurityDefinitions();
                ((OpenApi20Document) document).setSecurityDefinitions(securityDefinitions);
            }
            securityDefinitions.addItem(name, (OpenApi20SecurityScheme) scheme);
        } else if (ModelTypeUtil.isOpenApi30Model(document)) {
            OpenApi30Components components = ((OpenApi30Document) document).getComponents();
            if (components == null) {
                components = ((OpenApi30Document) document).createComponents();
                ((OpenApi30Document) document).setComponents(components);
            }
            components.addSecurityScheme(name, scheme);
        } else if (ModelTypeUtil.isOpenApi31Model(document)) {
            OpenApi31Components components = ((OpenApi31Document) document).getComponents();
            if (components == null) {
                components = ((OpenApi31Document) document).createComponents();
                ((OpenApi31Document) document).setComponents(components);
            }
            components.addSecurityScheme(name, scheme);
        } else if (ModelTypeUtil.isAsyncApiModel(document)) {
            AsyncApiComponents components = ((AsyncApiDocument) document).getComponents();
            if (components == null) {
                components = ((AsyncApiDocument) document).createComponents();
                ((AsyncApiDocument) document).setComponents(components);
            }
            components.addSecurityScheme(name, scheme);
        }
    }
}
