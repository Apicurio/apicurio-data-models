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
import io.apicurio.datamodels.core.models.DocumentType;
import io.apicurio.datamodels.core.models.Extension;
import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.models.common.ISecurityRequirementParent;
import io.apicurio.datamodels.core.models.common.IServerParent;
import io.apicurio.datamodels.core.models.common.Info;
import io.apicurio.datamodels.core.models.common.Parameter;
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
import io.apicurio.datamodels.openapi.v3.models.Oas30Example;
import io.apicurio.datamodels.openapi.v3.models.Oas30MediaType;
import io.apicurio.datamodels.openapi.v3.models.Oas30Operation;

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
            
            /** Replace Commands **/

            case "ReplaceOperationCommand_20": 
            case "ReplaceOperationCommand_30":
            case "ReplaceOperationCommand":
            { return new ReplaceOperationCommand(); }
            
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
        }
        return null;
    }
    
    public static final AggregateCommand createAggregateCommand(String name, Object info, List<ICommand> commands) {
        return new AggregateCommand(name, info, commands);
    }

    /* ***  Add Commands  *** */
    
    public static final AddExampleCommand_30 createAddExampleCommand(Oas30MediaType mediaType, Object example, 
            String exampleName, String exampleSummary, String exampleDescription) {
        return new AddExampleCommand_30(mediaType, example, exampleName, exampleSummary, exampleDescription);
    }

    public static final AddSchemaDefinitionCommand createAddSchemaDefinitionCommand(DocumentType docType, 
            String definitionName, Object from) {
        if (docType == DocumentType.openapi2) {
            return new AddSchemaDefinitionCommand_20(definitionName, from);
        }
        if (docType == DocumentType.openapi3) {
            return new AddSchemaDefinitionCommand_30(definitionName, from);
        }
        throw new RuntimeException("Document type not supported by this command.");
    }

    public static final AddPathItemCommand createAddPathItemCommand(String pathItemName, Object from) {
        return new AddPathItemCommand(pathItemName, from);
    }

    public static final AddSecurityRequirementCommand createAddSecurityRequirementCommand(
            ISecurityRequirementParent parent, SecurityRequirement requirement) {
        return new AddSecurityRequirementCommand(parent, requirement);
    }

    
    /* ***  Change Commands  *** */

    public static <T> ChangePropertyCommand<T> createChangePropertyCommand(Node node, String property, T newValue) {
        return new ChangePropertyCommand<T>(node, property, newValue);
    }

    public static final ChangeTitleCommand createChangeTitleCommand(String newTitle) {
        return new ChangeTitleCommand(newTitle);
    }
    
    public static final ChangeDescriptionCommand createChangeDescriptionCommand(String newDescription) {
        return new ChangeDescriptionCommand(newDescription);
    }

    public static final ChangeVersionCommand createChangeVersionCommand(String newVersion) {
        return new ChangeVersionCommand(newVersion);
    }
    
    public static final ChangeContactCommand createChangeContactCommand(String name, String email, String url) {
        return new ChangeContactCommand(name, email, url);
    }
    
    public static final ChangeMediaTypeTypeCommand createChangeMediaTypeTypeCommand(Oas30MediaType mediaType, 
            SimplifiedType newType) {
        return new ChangeMediaTypeTypeCommand(mediaType, newType);
    }

    public static final ChangeParameterTypeCommand createChangeParameterTypeCommand(DocumentType docType, 
            Parameter parameter, SimplifiedParameterType newType) {
        if (docType == DocumentType.openapi2) {
            return new ChangeParameterTypeCommand_20(parameter, newType);
        }
        if (docType == DocumentType.openapi3) {
            return new ChangeParameterTypeCommand_30(parameter, newType);
        }
        throw new RuntimeException("Document type not supported by this command.");
    }

    public static final ChangeParameterTypeCommand createChangeParameterDefinitionTypeCommand(DocumentType docType, 
            Parameter parameter, SimplifiedParameterType newType) {
        if (docType == DocumentType.openapi2) {
            return new ChangeParameterDefinitionTypeCommand_20(parameter, newType);
        }
        if (docType == DocumentType.openapi3) {
            return new ChangeParameterDefinitionTypeCommand_30(parameter, newType);
        }
        throw new RuntimeException("Document type not supported by this command.");
    }
    
    public static final ChangePropertyTypeCommand createChangePropertyTypeCommand(IOasPropertySchema property, 
            SimplifiedPropertyType newType) {
        return new ChangePropertyTypeCommand(property, newType);
    }
    
    public static final ChangeResponseTypeCommand createChangeResponseTypeCommand(Oas20Response response, 
            SimplifiedType newType) {
        return new ChangeResponseTypeCommand(response, newType);
    }

    public static final ChangeResponseTypeCommand createChangeResponseDefinitionTypeCommand(
            Oas20ResponseDefinition response, SimplifiedType newType) {
        return new ChangeResponseTypeCommand(response, newType);
    }
    
    public static final ChangeSecuritySchemeCommand createChangeSecuritySchemeCommand(DocumentType docType, 
            SecurityScheme scheme) {
        if (docType == DocumentType.openapi2) {
            return new ChangeSecuritySchemeCommand_20(scheme);
        } else if (docType == DocumentType.openapi3) {
            return new ChangeSecuritySchemeCommand_30(scheme);
        }
        throw new RuntimeException("Document type not supported by this command.");
    }
    
    public static final ChangeServerCommand createChangeServerCommand(Server server) {
        return new ChangeServerCommand(server);
    }

    /* ***  Replace Commands  *** */

    public static final ReplaceOperationCommand createReplaceOperationCommand(OasOperation old, 
            OasOperation replacement) {
        return new ReplaceOperationCommand(old, replacement);
    }

    /* ***  Delete Commands  *** */
    
    public static final DeleteContactCommand createDeleteContactCommand(Info info) {
        return new DeleteContactCommand(info);
    }
    
    public static final DeleteAllExamplesCommand createDeleteAllExamplesCommand(Oas30MediaType mediaType) {
        return new DeleteAllExamplesCommand(mediaType);
    }
    
    public static final DeleteAllOperationsCommand createDeleteAllOperationsCommand(OasPathItem pathItem) {
        return new DeleteAllOperationsCommand(pathItem);
    }
    
    public static final DeleteAllParametersCommand createDeleteAllParametersCommand(IOasParameterParent parent, 
            String type) {
        return new DeleteAllParametersCommand(parent, type);
    }

    public static final DeleteAllPropertiesCommand createDeleteAllPropertiesCommand(OasSchema schema) {
        return new DeleteAllPropertiesCommand(schema);
    }
    
    public static final DeleteAllResponsesCommand createDeleteAllResponsesCommand(OasOperation operation) {
        return new DeleteAllResponsesCommand(Constants.PROP_RESPONSES, operation);
    }
    
    public static final DeleteAllSecurityRequirementsCommand createDeleteAllSecurityRequirementsCommand(
            ISecurityRequirementParent parent) {
        return new DeleteAllSecurityRequirementsCommand(parent);
    }
    
    public static final DeleteAllSecuritySchemesCommand createDeleteAllSecuritySchemesCommand() {
        return new DeleteAllSecuritySchemesCommand();
    }
    
    public static final DeleteAllServersCommand createDeleteAllServersCommand(IServerParent parent) {
        return new DeleteAllServersCommand(parent);
    }
    
    public static final DeleteAllTagsCommand createDeleteAllTagsCommand() {
        return new DeleteAllTagsCommand();
    }
    
    public static final DeleteExampleCommand_20 createDelete20ExampleCommand(Oas20Response response, String contentType) {
        return new DeleteExampleCommand_20(response, contentType);
    }

    public static final DeleteExampleCommand_30 createDeleteExampleCommand(Oas30Example example) {
        return new DeleteExampleCommand_30(example);
    }
    
    public static final DeleteExtensionCommand createDeleteExtensionCommand(Extension extension) {
        return new DeleteExtensionCommand(extension);
    }
    
    public static final DeleteLicenseCommand createDeleteLicenseCommand(Info info) {
        return new DeleteLicenseCommand(info);
    }
    
    public static final DeleteMediaTypeCommand createDeleteMediaTypeCommand(Oas30MediaType mediaType) {
        return new DeleteMediaTypeCommand(mediaType);
    }
    
    public static final DeleteOperationCommand createDeleteOperationCommand(String opMethod, OasPathItem pathItem) {
        return new DeleteOperationCommand(opMethod, pathItem);
    }
    
    public static final DeleteParameterCommand createDeleteParameterCommand(OasParameter parameter) {
        return new DeleteParameterCommand(parameter);
    }
    
    public static final DeletePathCommand createDeletePathCommand(String path) {
        return new DeletePathCommand(path);
    }
    
    public static final DeletePropertyCommand createDeletePropertyCommand(IOasPropertySchema property) {
        return new DeletePropertyCommand(property);
    }
    
    public static final DeleteRequestBodyCommand createDeleteRequestBodyCommand(Oas30Operation operation) {
        return new DeleteRequestBodyCommand(operation);
    }
    
    public static final DeleteResponseCommand createDeleteResponseCommand(OasResponse response) {
        return new DeleteResponseCommand(response);
    }
    
    public static final DeleteSchemaDefinitionCommand createDeleteSchemaDefinitionCommand(
            DocumentType docType, String definitionName) {
        if (docType == DocumentType.openapi2) {
            return new DeleteSchemaDefinitionCommand_20(definitionName);
        }
        if (docType == DocumentType.openapi3) {
            return new DeleteSchemaDefinitionCommand_30(definitionName);
        }
        throw new RuntimeException("Document type not supported by this command.");
    }
    
    public static final DeleteSecurityRequirementCommand createDeleteSecurityRequirementCommand(
            ISecurityRequirementParent parent, SecurityRequirement requirement) {
        return new DeleteSecurityRequirementCommand(parent, requirement);
    }
    
    public static final DeleteSecuritySchemeCommand createDeleteSecuritySchemeCommand(DocumentType docType, 
            String schemeName) {
        if (docType == DocumentType.openapi2) {
            return new DeleteSecuritySchemeCommand_20(schemeName);
        }
        if (docType == DocumentType.openapi3) {
            return new DeleteSecuritySchemeCommand_30(schemeName);
        }
        throw new RuntimeException("Document type not supported by this command.");
    }
    
    public static final DeleteServerCommand createDeleteServerCommand(Server server) {
        return new DeleteServerCommand(server);
    }
    
    public static final DeleteTagCommand createDeleteTagCommand(String tagName) {
        return new DeleteTagCommand(tagName);
    }

}
