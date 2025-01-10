package io.apicurio.datamodels.cmd.commands;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.cmd.AbstractCommand;
import io.apicurio.datamodels.models.Document;
import io.apicurio.datamodels.models.openapi.OpenApiDocument;
import io.apicurio.datamodels.models.openapi.OpenApiSchema;
import io.apicurio.datamodels.models.openapi.v20.OpenApi20Document;
import io.apicurio.datamodels.models.openapi.v20.OpenApi20Schema;
import io.apicurio.datamodels.models.openapi.v30.OpenApi30Document;
import io.apicurio.datamodels.models.openapi.v30.OpenApi30Schema;
import io.apicurio.datamodels.models.openapi.v31.OpenApi31Document;
import io.apicurio.datamodels.models.openapi.v31.OpenApi31Schema;
import io.apicurio.datamodels.util.LoggerUtil;
import io.apicurio.datamodels.util.ModelTypeUtil;

/**
 * A command used to add a new definition in a document.  Source for the new
 * definition must be provided.  This source will be converted to an OAS
 * definition object and then added to the data model.
 * @author eric.wittmann@gmail.com
 */
public class AddSchemaDefinitionCommand extends AbstractCommand {

    public boolean _defExisted;
    public String _newDefinitionName;
    public ObjectNode _newDefinitionObj;
    public boolean _nullDefinitionsParent;

    private transient AddSchemaDefinitionCommandHelper _helper;

    public AddSchemaDefinitionCommand() {
    }

    public AddSchemaDefinitionCommand(String definitionName, ObjectNode obj) {
        this._newDefinitionName = definitionName;
        this._newDefinitionObj = obj;
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#execute(Document)
     */
    @Override
    public void execute(Document document) {
        LoggerUtil.info("[AddSchemaDefinitionCommand] Executing.");
        this._helper = createHelper(document);

        OpenApiDocument doc = (OpenApiDocument) document;

        // Do nothing if the definition already exists.
        if (this._helper.defExists(doc)) {
            LoggerUtil.info("[AddSchemaDefinitionCommand] Definition with name %s already exists.", this._newDefinitionName);
            this._defExisted = true;
            return;
        }

        this._nullDefinitionsParent = this._helper.prepareDocumentForDef(doc);

        OpenApiSchema definition = this._helper.createSchemaDefinition(doc);
        this._helper.addDefinition(doc, definition);
    }

    /**
     * @see io.apicurio.datamodels.cmd.ICommand#undo(Document)
     */
    @Override
    public void undo(Document document) {
        LoggerUtil.info("[AddSchemaDefinitionCommand] Reverting.");
        if (this._defExisted) {
            return;
        }

        OpenApiDocument doc = (OpenApiDocument) document;

        this._helper.removeDefinition(doc);
    }

    private AddSchemaDefinitionCommandHelper createHelper(Document document) {
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

    private interface AddSchemaDefinitionCommandHelper {
        boolean defExists(OpenApiDocument document);

        boolean prepareDocumentForDef(OpenApiDocument document);

        OpenApiSchema createSchemaDefinition(OpenApiDocument document);

        void addDefinition(OpenApiDocument document, OpenApiSchema definition);

        void removeDefinition(OpenApiDocument document);
    }

    private class OpenApi20Helper implements AddSchemaDefinitionCommandHelper {

        @Override
        public boolean defExists(OpenApiDocument document) {
            OpenApi20Document doc20 = (OpenApi20Document) document;
            if (isNullOrUndefined(doc20.getDefinitions())) {
                return false;
            }
            return !isNullOrUndefined(doc20.getDefinitions().getItem(_newDefinitionName));
        }

        @Override
        public boolean prepareDocumentForDef(OpenApiDocument document) {
            OpenApi20Document doc20 = (OpenApi20Document) document;
            if (isNullOrUndefined(doc20.getDefinitions())) {
                doc20.setDefinitions(doc20.createDefinitions());
                return true;
            }
            return false;
        }

        @Override
        public OpenApiSchema createSchemaDefinition(OpenApiDocument document) {
            OpenApi20Document doc20 = (OpenApi20Document) document;
            OpenApi20Schema definition = doc20.getDefinitions().createSchema();
            Library.readNode(_newDefinitionObj, definition);
            return definition;
        }

        @Override
        public void addDefinition(OpenApiDocument document, OpenApiSchema definition) {
            OpenApi20Document doc20 = (OpenApi20Document) document;
            OpenApi20Schema def20 = (OpenApi20Schema) definition;
            doc20.getDefinitions().addItem(_newDefinitionName, def20);
        }

        @Override
        public void removeDefinition(OpenApiDocument document) {
            OpenApi20Document doc20 = (OpenApi20Document) document;
            if (_nullDefinitionsParent) {
                doc20.setDefinitions(null);
            } else {
                doc20.getDefinitions().removeItem(_newDefinitionName);
            }
        }
    }

    private class OpenApi30Helper implements AddSchemaDefinitionCommandHelper {
        @Override
        public boolean defExists(OpenApiDocument document) {
            OpenApi30Document doc30 = (OpenApi30Document) document;
            if (isNullOrUndefined(doc30.getComponents())) {
                return false;
            }
            return !isNullOrUndefined(doc30.getComponents().getSchemas().get(_newDefinitionName));
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
        public OpenApiSchema createSchemaDefinition(OpenApiDocument document) {
            OpenApi30Document doc30 = (OpenApi30Document) document;
            OpenApi30Schema definition = (OpenApi30Schema) doc30.getComponents().createSchema();
            Library.readNode(_newDefinitionObj, definition);
            return definition;
        }

        @Override
        public void addDefinition(OpenApiDocument document, OpenApiSchema definition) {
            OpenApi30Document doc30 = (OpenApi30Document) document;
            doc30.getComponents().addSchema(_newDefinitionName, definition);
        }

        @Override
        public void removeDefinition(OpenApiDocument document) {
            OpenApi30Document doc30 = (OpenApi30Document) document;
            if (_nullDefinitionsParent) {
                doc30.setComponents(null);
            } else {
                doc30.getComponents().removeSchema(_newDefinitionName);
            }
        }
    }

    private class OpenApi31Helper implements AddSchemaDefinitionCommandHelper {
        @Override
        public boolean defExists(OpenApiDocument document) {
            OpenApi31Document doc31 = (OpenApi31Document) document;
            if (isNullOrUndefined(doc31.getComponents())) {
                return false;
            }
            return !isNullOrUndefined(doc31.getComponents().getSchemas().get(_newDefinitionName));
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
        public OpenApiSchema createSchemaDefinition(OpenApiDocument document) {
            OpenApi31Document doc31 = (OpenApi31Document) document;
            OpenApi31Schema definition = (OpenApi31Schema) doc31.getComponents().createSchema();
            Library.readNode(_newDefinitionObj, definition);
            return definition;
        }

        @Override
        public void addDefinition(OpenApiDocument document, OpenApiSchema definition) {
            OpenApi31Document doc31 = (OpenApi31Document) document;
            doc31.getComponents().addSchema(_newDefinitionName, definition);
        }

        @Override
        public void removeDefinition(OpenApiDocument document) {
            OpenApi31Document doc31 = (OpenApi31Document) document;
            if (_nullDefinitionsParent) {
                doc31.setComponents(null);
            } else {
                doc31.getComponents().removeSchema(_newDefinitionName);
            }
        }
    }

}