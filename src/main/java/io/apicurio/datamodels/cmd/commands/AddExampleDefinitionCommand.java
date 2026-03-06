package io.apicurio.datamodels.cmd.commands;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.models.Document;
import io.apicurio.datamodels.models.openapi.OpenApiDocument;
import io.apicurio.datamodels.models.openapi.OpenApiExample;
import io.apicurio.datamodels.models.openapi.v30.OpenApi30Document;
import io.apicurio.datamodels.models.openapi.v30.OpenApi30Example;
import io.apicurio.datamodels.models.openapi.v31.OpenApi31Document;
import io.apicurio.datamodels.models.openapi.v31.OpenApi31Example;
import io.apicurio.datamodels.util.LoggerUtil;
import io.apicurio.datamodels.util.ModelTypeUtil;

/**
 * A command used to add a new example definition in a document.  Source for the new
 * definition must be provided.  This source will be converted to an openapi
 * example definition object and then added to the data model.
 * @author eric.wittmann@gmail.com
 */
public class AddExampleDefinitionCommand extends AbstractCommand {

    public boolean _defExisted;
    public String _newDefinitionName;
    public ObjectNode _newDefinitionObj;
    public boolean _nullDefinitionsParent;

    private transient AddExampleDefinitionCommandHelper _helper;

    public AddExampleDefinitionCommand() {
    }

    public AddExampleDefinitionCommand(String definitionName, ObjectNode obj) {
        this._newDefinitionName = definitionName;
        this._newDefinitionObj = obj;
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(Document)
     */
    @Override
    public void execute(Document document) {
        LoggerUtil.info("[AddExampleDefinitionCommand] Executing.");
        this._helper = createHelper(document);

        OpenApiDocument doc = (OpenApiDocument) document;

        // Do nothing if the definition already exists.
        if (this._helper.defExists(doc)) {
            LoggerUtil.info("[AddExampleDefinitionCommand] Definition with name %s already exists.", this._newDefinitionName);
            this._defExisted = true;
            return;
        }

        this._nullDefinitionsParent = this._helper.prepareDocumentForDef(doc);

        OpenApiExample definition = this._helper.createExampleDefinition(doc);
        this._helper.addDefinition(doc, definition);
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(Document)
     */
    @Override
    public void undo(Document document) {
        LoggerUtil.info("[AddExampleDefinitionCommand] Reverting.");
        if (this._defExisted) {
            return;
        }

        this._helper = createHelper(document);

        OpenApiDocument doc = (OpenApiDocument) document;
        this._helper.removeDefinition(doc);
    }

    private AddExampleDefinitionCommandHelper createHelper(Document document) {
        if (ModelTypeUtil.isOpenApi30Model(document)) {
            return new OpenApi30Helper();
        }
        if (ModelTypeUtil.isOpenApi31Model(document)) {
            return new OpenApi31Helper();
        }
        throw new RuntimeException("Unsupported model type: " + document.root().modelType());
    }

    private interface AddExampleDefinitionCommandHelper {

        boolean defExists(OpenApiDocument document);
        boolean prepareDocumentForDef(OpenApiDocument document);
        OpenApiExample createExampleDefinition(OpenApiDocument document);
        void addDefinition(OpenApiDocument document, OpenApiExample definition);
        void removeDefinition(OpenApiDocument document);

    }

    private class OpenApi30Helper implements AddExampleDefinitionCommandHelper {
        @Override
        public boolean defExists(OpenApiDocument document) {
            OpenApi30Document doc30 = (OpenApi30Document) document;
            if (isNullOrUndefined(doc30.getComponents())) {
                return false;
            }
            return !isNullOrUndefined(doc30.getComponents().getExamples().get(_newDefinitionName));
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
        public OpenApiExample createExampleDefinition(OpenApiDocument document) {
            OpenApi30Document doc30 = (OpenApi30Document) document;
            OpenApi30Example definition = (OpenApi30Example) doc30.getComponents().createExample();
            Library.readNode(_newDefinitionObj, definition);
            return definition;
        }

        @Override
        public void addDefinition(OpenApiDocument document, OpenApiExample definition) {
            OpenApi30Document doc30 = (OpenApi30Document) document;
            doc30.getComponents().addExample(_newDefinitionName, definition);
        }

        @Override
        public void removeDefinition(OpenApiDocument document) {
            OpenApi30Document doc30 = (OpenApi30Document) document;
            if (_nullDefinitionsParent) {
                doc30.setComponents(null);
            } else {
                doc30.getComponents().removeExample(_newDefinitionName);
            }
        }
    }

    private class OpenApi31Helper implements AddExampleDefinitionCommandHelper {
        @Override
        public boolean defExists(OpenApiDocument document) {
            OpenApi31Document doc31 = (OpenApi31Document) document;
            if (isNullOrUndefined(doc31.getComponents())) {
                return false;
            }
            return !isNullOrUndefined(doc31.getComponents().getExamples().get(_newDefinitionName));
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
        public OpenApiExample createExampleDefinition(OpenApiDocument document) {
            OpenApi31Document doc31 = (OpenApi31Document) document;
            OpenApi31Example definition = (OpenApi31Example) doc31.getComponents().createExample();
            Library.readNode(_newDefinitionObj, definition);
            return definition;
        }

        @Override
        public void addDefinition(OpenApiDocument document, OpenApiExample definition) {
            OpenApi31Document doc31 = (OpenApi31Document) document;
            doc31.getComponents().addExample(_newDefinitionName, definition);
        }

        @Override
        public void removeDefinition(OpenApiDocument document) {
            OpenApi31Document doc31 = (OpenApi31Document) document;
            if (_nullDefinitionsParent) {
                doc31.setComponents(null);
            } else {
                doc31.getComponents().removeExample(_newDefinitionName);
            }
        }
    }

}
