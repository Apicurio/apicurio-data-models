/*
 * Copyright 2019 Red Hat
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.apicurio.datamodels.cmd.commands;

import java.util.List;

import io.apicurio.datamodels.cmd.ICommand;
import io.apicurio.datamodels.cmd.models.SimplifiedParameterType;
import io.apicurio.datamodels.cmd.models.SimplifiedPropertyType;
import io.apicurio.datamodels.cmd.models.SimplifiedType;
import io.apicurio.datamodels.core.Constants;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.DocumentType;
import io.apicurio.datamodels.core.models.ExtensibleNode;
import io.apicurio.datamodels.core.models.Extension;
import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.models.common.IExampleParent;
import io.apicurio.datamodels.core.models.common.ISchemaDefinition;
import io.apicurio.datamodels.core.models.common.ISecurityRequirementParent;
import io.apicurio.datamodels.core.models.common.IServerParent;
import io.apicurio.datamodels.core.models.common.Info;
import io.apicurio.datamodels.core.models.common.Parameter;
import io.apicurio.datamodels.core.models.common.Schema;
import io.apicurio.datamodels.core.models.common.SecurityRequirement;
import io.apicurio.datamodels.core.models.common.SecurityScheme;
import io.apicurio.datamodels.core.models.common.Server;
import io.apicurio.datamodels.openapi.models.IOasParameterParent;
import io.apicurio.datamodels.openapi.models.IOasPropertySchema;
import io.apicurio.datamodels.openapi.models.OasOperation;
import io.apicurio.datamodels.openapi.models.OasParameter;
import io.apicurio.datamodels.openapi.models.OasPathItem;
import io.apicurio.datamodels.openapi.models.OasResponse;
import io.apicurio.datamodels.openapi.models.OasSchema;
import io.apicurio.datamodels.openapi.v2.models.Oas20Response;
import io.apicurio.datamodels.openapi.v2.models.Oas20ResponseDefinition;
import io.apicurio.datamodels.openapi.v2.models.Oas20SchemaDefinition;
import io.apicurio.datamodels.openapi.v3.models.IOas30MediaTypeParent;
import io.apicurio.datamodels.openapi.v3.models.Oas30Example;
import io.apicurio.datamodels.openapi.v3.models.Oas30MediaType;
import io.apicurio.datamodels.openapi.v3.models.Oas30Operation;
import io.apicurio.datamodels.openapi.v3.models.Oas30SchemaDefinition;

/**
 * This factory is used to create an instance of {@link ICommand} given a "type" that was previously
 * serialized as part of the marshalling of a command to a JS object (and ultimately a JSON string).
 * The type of command is typically its short classname, but not always.
 * @author eric.wittmann@gmail.com
 */
public class CommandFactory {

    /**
     * Called to create a command of a given type.
     * @param cmdType
     */
    @SuppressWarnings("rawtypes")
    public static ICommand create(String cmdType) {
        switch (cmdType) {

            case "AggregateCommand": 
            { return new AggregateCommand(); }

            /** Add Commands **/
            
            case "AddSchemaDefinitionCommand_20": 
            { return new AddSchemaDefinitionCommand_20(); }
            case "AddSchemaDefinitionCommand_30":
            { return new AddSchemaDefinitionCommand_30(); }
            case "AddExampleCommand_30":
            { return new AddExampleCommand_30(); }
            case "AddPathItemCommand":
            case "AddPathItemCommand_20":
            case "AddPathItemCommand_30":
            { return new AddPathItemCommand(); }
            case "AddSecurityRequirementCommand":
            { return new AddSecurityRequirementCommand(); }
            
            /** Change Commands **/
            
            case "ChangePropertyCommand_20": 
            case "ChangePropertyCommand_30":
            case "ChangePropertyCommand":
            { return new ChangePropertyCommand(); }
            case "ChangeDescriptionCommand_20": 
            case "ChangeDescriptionCommand_30":
            case "ChangeDescriptionCommand":
            { return new ChangeDescriptionCommand(); }
            case "ChangeTitleCommand_20": 
            case "ChangeTitleCommand_30":
            case "ChangeTitleCommand":
            { return new ChangeTitleCommand(); }
            case "ChangeVersionCommand_20": 
            case "ChangeVersionCommand_30":
            case "ChangeVersionCommand":
            { return new ChangeVersionCommand(); }
            case "ChangeContactCommand_20": 
            case "ChangeContactCommand_30":
            case "ChangeContactCommand":
            { return new ChangeContactCommand(); }
            case "ChangeLicenseCommand_20": 
            case "ChangeLicenseCommand_30":
            case "ChangeLicenseCommand":
            { return new ChangeLicenseCommand(); }
            case "ChangeMediaTypeTypeCommand":
            { return new ChangeMediaTypeTypeCommand(); }
            case "ChangeParameterTypeCommand_20": 
            { return new ChangeParameterTypeCommand_20(); }
            case "ChangeParameterDefinitionTypeCommand_20": 
            { return new ChangeParameterDefinitionTypeCommand_20(); }
            case "ChangeParameterTypeCommand_30":
            { return new ChangeParameterTypeCommand_30(); }
            case "ChangeParameterDefinitionTypeCommand_30":
            { return new ChangeParameterDefinitionTypeCommand_30(); }
            case "ChangePropertyTypeCommand":
            case "ChangePropertyTypeCommand_20":
            case "ChangePropertyTypeCommand_30":
            { return new ChangePropertyTypeCommand(); }
            case "ChangeResponseTypeCommand":
            case "ChangeResponseTypeCommand_20":
            case "ChangeResponseDefinitionTypeCommand_20":
            { return new ChangeResponseTypeCommand(); }
            case "ChangeSecuritySchemeCommand_20":
            { return new ChangeSecuritySchemeCommand_20(); }
            case "ChangeSecuritySchemeCommand_30":
            { return new ChangeSecuritySchemeCommand_30(); }
            case "ChangeServerCommand":
            { return new ChangeServerCommand(); }
            
            /** Delete Commands **/
            case "DeleteContactCommand_20": 
            case "DeleteContactCommand_30":
            case "DeleteContactCommand":
            { return new DeleteContactCommand(); }
            case "DeleteAllExamplesCommand_30":
            case "DeleteAllExamplesCommand":
            { return new DeleteAllExamplesCommand(); }
            case "DeleteAllOperationsCommand":
            { return new DeleteAllOperationsCommand(); }
            case "DeleteAllParametersCommand_20":
            case "DeleteAllParametersCommand_30":
            case "DeleteAllParametersCommand":
            { return new DeleteAllParametersCommand(); }
            case "DeleteAllPropertiesCommand_20":
            case "DeleteAllPropertiesCommand_30":
            case "DeleteAllPropertiesCommand":
            { return new DeleteAllPropertiesCommand(); }
            case "DeleteAllResponsesCommand_20":
            case "DeleteAllResponsesCommand_30":
            case "DeleteAllResponsesCommand":
            { return new DeleteAllResponsesCommand(); }
            case "DeleteAllSecurityRequirementsCommand":
            { return new DeleteAllSecurityRequirementsCommand(); }
            case "DeleteAllSecuritySchemesCommand":
            { return new DeleteAllSecuritySchemesCommand(); }
            case "DeleteAllServersCommand":
            { return new DeleteAllServersCommand(); }
            case "DeleteAllTagsCommand":
            { return new DeleteAllTagsCommand(); }
            case "DeleteExampleCommand_20":
            { return new DeleteExampleCommand_20(); }
            case "DeleteExampleCommand_30":
            { return new DeleteExampleCommand_30(); }
            case "DeleteExtensionCommand":
            { return new DeleteExtensionCommand(); }
            case "DeleteLicenseCommand":
            case "DeleteLicenseCommand_20":
            case "DeleteLicenseCommand_30":
            { return new DeleteLicenseCommand(); }
            case "DeleteMediaTypeCommand":
            { return new DeleteMediaTypeCommand(); }
            case "DeleteOperationCommand":
            case "DeleteOperationCommand_20":
            case "DeleteOperationCommand_30":
            { return new DeleteOperationCommand(); }
            case "DeleteParameterCommand":
            case "DeleteParameterCommand_20":
            case "DeleteParameterCommand_30":
            { return new DeleteParameterCommand(); }
            case "DeletePathCommand":
            case "DeletePathCommand_20":
            case "DeletePathCommand_30":
            { return new DeletePathCommand(); }
            case "DeletePropertyCommand":
            case "DeletePropertyCommand_20":
            case "DeletePropertyCommand_30":
            { return new DeletePropertyCommand(); }
            case "DeleteRequestBodyCommand":
            case "DeleteRequestBodyCommand_30":
            { return new DeleteRequestBodyCommand(); }
            case "DeleteResponseCommand":
            case "DeleteResponseCommand_20":
            case "DeleteResponseCommand_30":
            { return new DeleteResponseCommand(); }
            case "DeleteSchemaDefinitionCommand_20":
            { return new DeleteSchemaDefinitionCommand_20(); }
            case "DeleteSchemaDefinitionCommand_30":
            { return new DeleteSchemaDefinitionCommand_30(); }
            case "DeleteSecurityRequirementCommand":
            { return new DeleteSecurityRequirementCommand(); }
            case "DeleteSecuritySchemeCommand_20":
            { return new DeleteSecuritySchemeCommand_20(); }
            case "DeleteSecuritySchemeCommand_30":
            { return new DeleteSecuritySchemeCommand_30(); }
            case "DeleteServerCommand":
            { return new DeleteServerCommand(); }
            case "DeleteTagCommand":
            case "DeleteTagCommand_20":
            case "DeleteTagCommand_30":
            { return new DeleteTagCommand(); }
            
            /** New Commands **/
            
            case "NewMediaTypeCommand":
            { return new NewMediaTypeCommand(); }
            case "NewOperationCommand":
            case "NewOperationCommand_20":
            case "NewOperationCommand_30":
            { return new NewOperationCommand(); }
            case "NewParamCommand":
            case "NewParamCommand_20":
            case "NewParamCommand_30":
            { return new NewParamCommand(); }
            case "NewPathCommand":
            case "NewPathCommand_20":
            case "NewPathCommand_30":
            { return new NewPathCommand(); }
            case "NewRequestBodyCommand_20":
            { return new NewRequestBodyCommand_20(); }
            case "NewRequestBodyCommand_30":
            { return new NewRequestBodyCommand_30(); }
            case "NewResponseCommand":
            case "NewResponseCommand_20":
            case "NewResponseCommand_30":
            { return new NewResponseCommand(); }
            case "NewSchemaDefinitionCommand_20":
            { return new NewSchemaDefinitionCommand_20(); }
            case "NewSchemaDefinitionCommand_30":
            { return new NewSchemaDefinitionCommand_30(); }
            case "NewSchemaPropertyCommand":
            case "NewSchemaPropertyCommand_20":
            case "NewSchemaPropertyCommand_30":
            { return new NewSchemaPropertyCommand(); }
            case "NewSecuritySchemeCommand_20":
            { return new NewSecuritySchemeCommand_20(); }
            case "NewSecuritySchemeCommand_30":
            { return new NewSecuritySchemeCommand_30(); }
            case "NewServerCommand":
            { return new NewServerCommand(); }
            case "NewTagCommand":
            case "NewTagCommand_20":
            case "NewTagCommand_30":
            { return new NewTagCommand(); }
            
            /** Rename Commands **/
            
            case "RenameParameterCommand":
            { return new RenameParameterCommand(); }
            case "RenamePathItemCommand":
            { return new RenamePathItemCommand(); }
            case "RenamePropertyCommand":
            { return new RenamePropertyCommand(); }
            case "RenameSchemaDefinitionCommand_20":
            { return new RenameSchemaDefinitionCommand_20(); }
            case "RenameSchemaDefinitionCommand_30":
            { return new RenameSchemaDefinitionCommand_30(); }
            case "RenameSecuritySchemeCommand":
            { return new RenameSecuritySchemeCommand(); }
            case "RenameTagDefinitionCommand":
            { return new RenameTagDefinitionCommand(); }
            
            /** Replace Commands **/

            case "ReplaceOperationCommand_20": 
            case "ReplaceOperationCommand_30":
            case "ReplaceOperationCommand":
            { return new ReplaceOperationCommand(); }
            case "ReplaceDocumentCommand":
            { return new ReplaceDocumentCommand(); }
            case "ReplacePathItemCommand_20": 
            case "ReplacePathItemCommand_30":
            case "ReplacePathItemCommand":
            { return new ReplacePathItemCommand(); }
            case "ReplaceSchemaDefinitionCommand_20":
            { return new ReplaceSchemaDefinitionCommand_20(); }
            case "ReplaceSchemaDefinitionCommand_30":
            { return new ReplaceSchemaDefinitionCommand_30(); }
            case "ReplaceSecurityRequirementCommand":
            { return new ReplaceSecurityRequirementCommand(); }
            
            /** Set Commands **/
            
            case "SetExampleCommand_20":
            { return new SetExampleCommand_20(); }
            case "SetExampleCommand_30":
            { return new SetExampleCommand_30(); }
            case "SetExtensionCommand":
            { return new SetExtensionCommand(); }
            
        }
        return null;
    }
    
    public static final ICommand createAggregateCommand(String name, Object info, List<ICommand> commands) {
        return new AggregateCommand(name, info, commands);
    }

    /* ***  Add Commands  *** */
    
    public static final ICommand createAddExampleCommand(Oas30MediaType mediaType, Object example, 
            String exampleName, String exampleSummary, String exampleDescription) {
        return new AddExampleCommand_30(mediaType, example, exampleName, exampleSummary, exampleDescription);
    }

    public static final ICommand createAddSchemaDefinitionCommand(DocumentType docType, 
            String definitionName, Object from) {
        if (docType == DocumentType.openapi2) {
            return new AddSchemaDefinitionCommand_20(definitionName, from);
        }
        if (docType == DocumentType.openapi3) {
            return new AddSchemaDefinitionCommand_30(definitionName, from);
        }
        throw new RuntimeException("Document type not supported by this command.");
    }

    public static final ICommand createAddPathItemCommand(String pathItemName, Object from) {
        return new AddPathItemCommand(pathItemName, from);
    }

    public static final ICommand createAddSecurityRequirementCommand(
            ISecurityRequirementParent parent, SecurityRequirement requirement) {
        return new AddSecurityRequirementCommand(parent, requirement);
    }

    
    /* ***  Change Commands  *** */

    public static <T> ICommand createChangePropertyCommand(Node node, String property, T newValue) {
        return new ChangePropertyCommand<T>(node, property, newValue);
    }

    public static final ICommand createChangeTitleCommand(String newTitle) {
        return new ChangeTitleCommand(newTitle);
    }
    
    public static final ICommand createChangeDescriptionCommand(String newDescription) {
        return new ChangeDescriptionCommand(newDescription);
    }

    public static final ICommand createChangeVersionCommand(String newVersion) {
        return new ChangeVersionCommand(newVersion);
    }
    
    public static final ICommand createChangeLicenseCommand(String name, String url) {
        return new ChangeLicenseCommand(name, url);
    }
    
    public static final ICommand createChangeContactCommand(String name, String email, String url) {
        return new ChangeContactCommand(name, email, url);
    }
    
    public static final ICommand createChangeMediaTypeTypeCommand(Oas30MediaType mediaType, 
            SimplifiedType newType) {
        return new ChangeMediaTypeTypeCommand(mediaType, newType);
    }

    public static final ICommand createChangeParameterTypeCommand(DocumentType docType, 
            Parameter parameter, SimplifiedParameterType newType) {
        if (docType == DocumentType.openapi2) {
            return new ChangeParameterTypeCommand_20(parameter, newType);
        }
        if (docType == DocumentType.openapi3) {
            return new ChangeParameterTypeCommand_30(parameter, newType);
        }
        throw new RuntimeException("Document type not supported by this command.");
    }

    public static final ICommand createChangeParameterDefinitionTypeCommand(DocumentType docType, 
            Parameter parameter, SimplifiedParameterType newType) {
        if (docType == DocumentType.openapi2) {
            return new ChangeParameterDefinitionTypeCommand_20(parameter, newType);
        }
        if (docType == DocumentType.openapi3) {
            return new ChangeParameterDefinitionTypeCommand_30(parameter, newType);
        }
        throw new RuntimeException("Document type not supported by this command.");
    }
    
    public static final ICommand createChangePropertyTypeCommand(IOasPropertySchema property, 
            SimplifiedPropertyType newType) {
        return new ChangePropertyTypeCommand(property, newType);
    }
    
    public static final ICommand createChangeResponseTypeCommand(Oas20Response response, 
            SimplifiedType newType) {
        return new ChangeResponseTypeCommand(response, newType);
    }

    public static final ICommand createChangeResponseDefinitionTypeCommand(
            Oas20ResponseDefinition response, SimplifiedType newType) {
        return new ChangeResponseTypeCommand(response, newType);
    }
    
    public static final ICommand createChangeSecuritySchemeCommand(DocumentType docType, 
            SecurityScheme scheme) {
        if (docType == DocumentType.openapi2) {
            return new ChangeSecuritySchemeCommand_20(scheme);
        } else if (docType == DocumentType.openapi3) {
            return new ChangeSecuritySchemeCommand_30(scheme);
        }
        throw new RuntimeException("Document type not supported by this command.");
    }
    
    public static final ICommand createChangeServerCommand(Server server) {
        return new ChangeServerCommand(server);
    }

    /* ***  Delete Commands  *** */
    
    public static final ICommand createDeleteContactCommand(Info info) {
        return new DeleteContactCommand(info);
    }
    
    public static final ICommand createDeleteAllExamplesCommand(Oas30MediaType mediaType) {
        return new DeleteAllExamplesCommand(mediaType);
    }
    
    public static final ICommand createDeleteAllOperationsCommand(OasPathItem pathItem) {
        return new DeleteAllOperationsCommand(pathItem);
    }
    
    public static final ICommand createDeleteAllParametersCommand(IOasParameterParent parent, 
            String type) {
        return new DeleteAllParametersCommand(parent, type);
    }

    public static final ICommand createDeleteAllPropertiesCommand(OasSchema schema) {
        return new DeleteAllPropertiesCommand(schema);
    }
    
    public static final ICommand createDeleteAllResponsesCommand(OasOperation operation) {
        return new DeleteAllResponsesCommand(Constants.PROP_RESPONSES, operation);
    }
    
    public static final ICommand createDeleteAllSecurityRequirementsCommand(
            ISecurityRequirementParent parent) {
        return new DeleteAllSecurityRequirementsCommand(parent);
    }
    
    public static final ICommand createDeleteAllSecuritySchemesCommand() {
        return new DeleteAllSecuritySchemesCommand();
    }
    
    public static final ICommand createDeleteAllServersCommand(IServerParent parent) {
        return new DeleteAllServersCommand(parent);
    }
    
    public static final ICommand createDeleteAllTagsCommand() {
        return new DeleteAllTagsCommand();
    }
    
    public static final ICommand createDelete20ExampleCommand(Oas20Response response, String contentType) {
        return new DeleteExampleCommand_20(response, contentType);
    }

    public static final ICommand createDeleteExampleCommand(Oas30Example example) {
        return new DeleteExampleCommand_30(example);
    }
    
    public static final ICommand createDeleteExtensionCommand(Extension extension) {
        return new DeleteExtensionCommand(extension);
    }
    
    public static final ICommand createDeleteLicenseCommand(Info info) {
        return new DeleteLicenseCommand(info);
    }
    
    public static final ICommand createDeleteMediaTypeCommand(Oas30MediaType mediaType) {
        return new DeleteMediaTypeCommand(mediaType);
    }
    
    public static final ICommand createDeleteOperationCommand(String opMethod, OasPathItem pathItem) {
        return new DeleteOperationCommand(opMethod, pathItem);
    }
    
    public static final ICommand createDeleteParameterCommand(OasParameter parameter) {
        return new DeleteParameterCommand(parameter);
    }
    
    public static final ICommand createDeletePathCommand(String path) {
        return new DeletePathCommand(path);
    }
    
    public static final ICommand createDeletePropertyCommand(IOasPropertySchema property) {
        return new DeletePropertyCommand(property);
    }
    
    public static final ICommand createDeleteRequestBodyCommand(Oas30Operation operation) {
        return new DeleteRequestBodyCommand(operation);
    }
    
    public static final ICommand createDeleteResponseCommand(OasResponse response) {
        return new DeleteResponseCommand(response);
    }
    
    public static final ICommand createDeleteSchemaDefinitionCommand(
            DocumentType docType, String definitionName) {
        if (docType == DocumentType.openapi2) {
            return new DeleteSchemaDefinitionCommand_20(definitionName);
        }
        if (docType == DocumentType.openapi3) {
            return new DeleteSchemaDefinitionCommand_30(definitionName);
        }
        throw new RuntimeException("Document type not supported by this command.");
    }
    
    public static final ICommand createDeleteSecurityRequirementCommand(
            ISecurityRequirementParent parent, SecurityRequirement requirement) {
        return new DeleteSecurityRequirementCommand(parent, requirement);
    }
    
    public static final ICommand createDeleteSecuritySchemeCommand(DocumentType docType, 
            String schemeName) {
        if (docType == DocumentType.openapi2) {
            return new DeleteSecuritySchemeCommand_20(schemeName);
        }
        if (docType == DocumentType.openapi3) {
            return new DeleteSecuritySchemeCommand_30(schemeName);
        }
        throw new RuntimeException("Document type not supported by this command.");
    }
    
    public static final ICommand createDeleteServerCommand(Server server) {
        return new DeleteServerCommand(server);
    }
    
    public static final ICommand createDeleteTagCommand(String tagName) {
        return new DeleteTagCommand(tagName);
    }
    
    /** New Commands **/
    
    public static final ICommand createNewMediaTypeCommand(IOas30MediaTypeParent parent, String newMediaType) {
        return new NewMediaTypeCommand(parent, newMediaType);
    }
    
    public static final ICommand createNewOperationCommand(String path, String method) {
        return new NewOperationCommand(path, method);
    }
    
    public static final ICommand createNewParamCommand(IOasParameterParent parent, String paramName, 
            String paramType, String description, SimplifiedParameterType newType, boolean override) {
        return new NewParamCommand(parent, paramName, paramType, description, newType, override);
    }
    
    public static final ICommand createNewPathCommand(String newPath) {
        return new NewPathCommand(newPath);
    }
    
    public static final ICommand createNewRequestBodyCommand(DocumentType docType, OasOperation operation) {
        if (docType == DocumentType.openapi2) {
            return new NewRequestBodyCommand_20(operation);
        }
        if (docType == DocumentType.openapi3) {
            return new NewRequestBodyCommand_30(operation);
        }
        throw new RuntimeException("Document type not supported by this command.");
    }
    
    public static final ICommand createNewResponseCommand(OasOperation operation, String statusCode, 
            OasResponse sourceResponse) {
        return new NewResponseCommand(operation, statusCode, sourceResponse);
    }
    
    public static final ICommand createNewSchemaDefinitionCommand(DocumentType docType, 
            String definitionName, Object example, String description) {
        if (docType == DocumentType.openapi2) {
            return new NewSchemaDefinitionCommand_20(definitionName, example, description);
        }
        if (docType == DocumentType.openapi3) {
            return new NewSchemaDefinitionCommand_30(definitionName, example, description);
        }
        throw new RuntimeException("Document type not supported by this command.");
    }
    
    public static final ICommand createNewSchemaPropertyCommand(Schema schema, String propertyName, 
            String description, SimplifiedPropertyType newType) {
        return new NewSchemaPropertyCommand(schema, propertyName, description, newType);
    }

    public static final ICommand createNewSecuritySchemeCommand(DocumentType docType, 
            SecurityScheme scheme) {
        if (docType == DocumentType.openapi2) {
            return new NewSecuritySchemeCommand_20(scheme);
        }
        if (docType == DocumentType.openapi3) {
            return new NewSecuritySchemeCommand_30(scheme);
        }
        throw new RuntimeException("Document type not supported by this command.");
    }
    
    public static final ICommand createNewServerCommand(IServerParent parent, Server server) {
        return new NewServerCommand(parent, server);
    }
    
    public static final ICommand createNewTagCommand(String name, String description) {
        return new NewTagCommand(name, description);
    }
    
    /** Rename Commands **/
    
    public static final ICommand createRenameParameterCommand(IOasParameterParent parent, 
            String oldParamName, String newParamName, String paramIn) {
        return new RenameParameterCommand(parent, oldParamName, newParamName, paramIn);
    }
    
    public static final ICommand createRenamePathItemCommand(String oldPath, String newPath, 
            boolean alsoRenameSubpaths) {
        return new RenamePathItemCommand(oldPath, newPath, alsoRenameSubpaths);
    }
    
    public static final ICommand createRenamePropertyCommand(OasSchema parent, String oldPropertyName, 
            String newPropertyName) {
        return new RenamePropertyCommand(parent, oldPropertyName, newPropertyName);
    }
    
    public static final ICommand createRenameSchemaDefinitionCommand(DocumentType docType, 
            String oldName, String newName) {
        if (docType == DocumentType.openapi2) {
            return new RenameSchemaDefinitionCommand_20(oldName, newName);
        }
        if (docType == DocumentType.openapi3) {
            return new RenameSchemaDefinitionCommand_30(oldName, newName);
        }
        throw new RuntimeException("Document type not supported by this command.");
    }
    
    public static final ICommand createRenameSecuritySchemeCommand(String oldSchemeName, 
            String newSchemeName) {
        return new RenameSecuritySchemeCommand(oldSchemeName, newSchemeName);
    }

    public static final ICommand createRenameTagDefinitionCommand(String oldTag, String newTag) {
        return new RenameTagDefinitionCommand(oldTag, newTag);
    }

    /* ***  Replace Commands  *** */

    public static final ICommand createReplaceOperationCommand(OasOperation old, 
            OasOperation replacement) {
        return new ReplaceOperationCommand(old, replacement);
    }
    
    public static final ICommand createReplaceDocumentCommand(Document old, Document replacement) {
        return new ReplaceDocumentCommand(old, replacement);
    }
    
    public static final ICommand createReplacePathItemCommand(OasPathItem old, OasPathItem replacement) {
        return new ReplacePathItemCommand(old, replacement);
    }
    
    public static final ICommand createReplaceSchemaDefinitionCommand(DocumentType docType,
            ISchemaDefinition old, ISchemaDefinition replacement) {
        if (docType == DocumentType.openapi2) {
            return new ReplaceSchemaDefinitionCommand_20((Oas20SchemaDefinition) old, (Oas20SchemaDefinition) replacement);
        }
        if (docType == DocumentType.openapi3) {
            return new ReplaceSchemaDefinitionCommand_30((Oas30SchemaDefinition) old, (Oas30SchemaDefinition) replacement);
        }
        throw new RuntimeException("Document type not supported by this command.");
    }
    
    public static final ICommand createReplaceSecurityRequirementCommand(SecurityRequirement old, 
            SecurityRequirement replacement) {
        return new ReplaceSecurityRequirementCommand(old, replacement);
    }
    
    /** Set Commands **/
    
    public static final ICommand createSetExampleCommand(DocumentType docType, IExampleParent parent, Object example, 
            String nameOrContentType) {
        if (docType == DocumentType.openapi2) {
            return new SetExampleCommand_20((Oas20Response) parent, example, nameOrContentType);
        }
        if (docType == DocumentType.openapi3) {
            return new SetExampleCommand_30((Oas30MediaType) parent, example, nameOrContentType);
        }
        throw new RuntimeException("Document type not supported by this command.");
    }

    public static final ICommand createSetExtensionCommand(ExtensibleNode parent, String name, Object value) {
        return new SetExtensionCommand(parent, name, value);
    }

}