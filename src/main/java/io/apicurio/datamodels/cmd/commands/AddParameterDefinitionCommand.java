package io.apicurio.datamodels.cmd.commands;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.models.Document;
import io.apicurio.datamodels.models.openapi.OpenApiDocument;
import io.apicurio.datamodels.models.openapi.OpenApiParameter;
import io.apicurio.datamodels.models.openapi.v20.OpenApi20Document;
import io.apicurio.datamodels.models.openapi.v20.OpenApi20Parameter;
import io.apicurio.datamodels.models.openapi.v30.OpenApi30Document;
import io.apicurio.datamodels.models.openapi.v30.OpenApi30Parameter;
import io.apicurio.datamodels.models.openapi.v31.OpenApi31Document;
import io.apicurio.datamodels.models.openapi.v31.OpenApi31Parameter;
import io.apicurio.datamodels.util.LoggerUtil;
import io.apicurio.datamodels.util.ModelTypeUtil;

/**
 * A command used to add a new parameter definition in a document.  Source for the new
 * definition must be provided.  This source will be converted to an openapi
 * parameter definition object and then added to the data model.
 * @author eric.wittmann@gmail.com
 */
public class AddParameterDefinitionCommand extends AbstractCommand {

    public boolean _defExisted;
    public String _newDefinitionName;
    public ObjectNode _newDefinitionObj;
    public boolean _nullDefinitionsParent;

    private transient AddParameterDefinitionCommandHelper _helper;

    public AddParameterDefinitionCommand() {
    }

    public AddParameterDefinitionCommand(String definitionName, ObjectNode obj) {
        this._newDefinitionName = definitionName;
        this._newDefinitionObj = obj;
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(Document)
     */
    @Override
    public void execute(Document document) {
        LoggerUtil.info("[AddParameterDefinitionCommand] Executing.");
        this._helper = createHelper(document);

        OpenApiDocument doc = (OpenApiDocument) document;

        // Do nothing if the definition already exists.
        if (this._helper.defExists(doc)) {
            LoggerUtil.info("[AddParameterDefinitionCommand] Definition with name %s already exists.", this._newDefinitionName);
            this._defExisted = true;
            return;
        }

        this._nullDefinitionsParent = this._helper.prepareDocumentForDef(doc);

        OpenApiParameter definition = this._helper.createParameterDefinition(doc);
        this._helper.addDefinition(doc, definition);
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(Document)
     */
    @Override
    public void undo(Document document) {
        LoggerUtil.info("[AddParameterDefinitionCommand] Reverting.");
        if (this._defExisted) {
            return;
        }

        this._helper = createHelper(document);

        OpenApiDocument doc = (OpenApiDocument) document;
        this._helper.removeDefinition(doc);
    }

    private AddParameterDefinitionCommandHelper createHelper(Document document) {
        if (ModelTypeUtil.isOpenApi2Model(document)) {
            return new OpenApi20Helper();
        }
        if (ModelTypeUtil.isOpenApi30Model(document)) {
            return new OpenApi30Helper();
        }
        if (ModelTypeUtil.isOpenApi31Model(document)) {
            return new OpenApi31Helper();
        }
        throw new RuntimeException("Unsupported model type: " + document.root().modelType());
    }

    private interface AddParameterDefinitionCommandHelper {

        boolean defExists(OpenApiDocument document);
        boolean prepareDocumentForDef(OpenApiDocument document);
        OpenApiParameter createParameterDefinition(OpenApiDocument document);
        void addDefinition(OpenApiDocument document, OpenApiParameter definition);
        void removeDefinition(OpenApiDocument document);

    }

    private class OpenApi20Helper implements AddParameterDefinitionCommandHelper {
        @Override
        public boolean defExists(OpenApiDocument document) {
            OpenApi20Document doc = (OpenApi20Document) document;
            if (!isNullOrUndefined(doc.getParameters())) {
                return !isNullOrUndefined(doc.getParameters().getItem(_newDefinitionName));
            }
            return false;
        }

        @Override
        public boolean prepareDocumentForDef(OpenApiDocument document) {
            OpenApi20Document doc20 = (OpenApi20Document) document;
            if (isNullOrUndefined(doc20.getParameters())) {
                doc20.setParameters(doc20.createParameterDefinitions());
                return true;
            }
            return false;
        }

        @Override
        public OpenApiParameter createParameterDefinition(OpenApiDocument document) {
            OpenApi20Document doc20 = (OpenApi20Document) document;
            OpenApi20Parameter definition = doc20.getParameters().createParameter();
            Library.readNode(_newDefinitionObj, definition);
            return definition;
        }

        @Override
        public void addDefinition(OpenApiDocument document, OpenApiParameter definition) {
            OpenApi20Document doc20 = (OpenApi20Document) document;
            OpenApi20Parameter def20 = (OpenApi20Parameter) definition;
            doc20.getParameters().addItem(_newDefinitionName, def20);
        }

        @Override
        public void removeDefinition(OpenApiDocument document) {
            OpenApi20Document doc20 = (OpenApi20Document) document;
            if (_nullDefinitionsParent) {
                doc20.setParameters(null);
            } else {
                doc20.getParameters().removeItem(_newDefinitionName);
            }
        }
    }

    private class OpenApi30Helper implements AddParameterDefinitionCommandHelper {
        @Override
        public boolean defExists(OpenApiDocument document) {
            OpenApi30Document doc30 = (OpenApi30Document) document;
            if (isNullOrUndefined(doc30.getComponents())) {
                return false;
            }
            return !isNullOrUndefined(doc30.getComponents().getParameters().get(_newDefinitionName));
        }

        @Override
        public boolean prepareDocumentForDef(OpenApiDocument document) {
            OpenApi30Document doc30 = (OpenApi30Document) document;
            if (isNullOrUndefined(doc30.getComponents())) {
                doc30.setComponents(doc30.createComponents());
                return true;
            }
            return false;
        }

        @Override
        public OpenApiParameter createParameterDefinition(OpenApiDocument document) {
            OpenApi30Document doc30 = (OpenApi30Document) document;
            OpenApi30Parameter definition = (OpenApi30Parameter) doc30.getComponents().createParameter();
            Library.readNode(_newDefinitionObj, definition);
            return definition;
        }

        @Override
        public void addDefinition(OpenApiDocument document, OpenApiParameter definition) {
            OpenApi30Document doc30 = (OpenApi30Document) document;
            doc30.getComponents().addParameter(_newDefinitionName, definition);
        }

        @Override
        public void removeDefinition(OpenApiDocument document) {
            OpenApi30Document doc30 = (OpenApi30Document) document;
            if (_nullDefinitionsParent) {
                doc30.setComponents(null);
            } else {
                doc30.getComponents().removeParameter(_newDefinitionName);
            }
        }
    }

    private class OpenApi31Helper implements AddParameterDefinitionCommandHelper {
        @Override
        public boolean defExists(OpenApiDocument document) {
            OpenApi31Document doc31 = (OpenApi31Document) document;
            if (isNullOrUndefined(doc31.getComponents())) {
                return false;
            }
            return !isNullOrUndefined(doc31.getComponents().getParameters().get(_newDefinitionName));
        }

        @Override
        public boolean prepareDocumentForDef(OpenApiDocument document) {
            OpenApi31Document doc31 = (OpenApi31Document) document;
            if (isNullOrUndefined(doc31.getComponents())) {
                doc31.setComponents(doc31.createComponents());
                return true;
            }
            return false;
        }

        @Override
        public OpenApiParameter createParameterDefinition(OpenApiDocument document) {
            OpenApi31Document doc31 = (OpenApi31Document) document;
            OpenApi31Parameter definition = (OpenApi31Parameter) doc31.getComponents().createParameter();
            Library.readNode(_newDefinitionObj, definition);
            return definition;
        }

        @Override
        public void addDefinition(OpenApiDocument document, OpenApiParameter definition) {
            OpenApi31Document doc31 = (OpenApi31Document) document;
            doc31.getComponents().addParameter(_newDefinitionName, definition);
        }

        @Override
        public void removeDefinition(OpenApiDocument document) {
            OpenApi31Document doc31 = (OpenApi31Document) document;
            if (_nullDefinitionsParent) {
                doc31.setComponents(null);
            } else {
                doc31.getComponents().removeParameter(_newDefinitionName);
            }
        }
    }

}
