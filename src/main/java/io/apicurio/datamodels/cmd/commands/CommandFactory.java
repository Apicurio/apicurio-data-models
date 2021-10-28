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

import io.apicurio.datamodels.asyncapi.models.AaiChannelItem;
import io.apicurio.datamodels.asyncapi.models.AaiMessage;
import io.apicurio.datamodels.asyncapi.models.AaiOperation;
import io.apicurio.datamodels.asyncapi.models.AaiSchema;
import io.apicurio.datamodels.asyncapi.v2.models.Aai20Document;
import io.apicurio.datamodels.asyncapi.v2.models.Aai20SchemaDefinition;
import io.apicurio.datamodels.asyncapi.v2.models.Aai20Server;
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
import io.apicurio.datamodels.core.models.NodePath;
import io.apicurio.datamodels.core.models.common.IDefinition;
import io.apicurio.datamodels.core.models.common.IExampleParent;
import io.apicurio.datamodels.core.models.common.IPropertySchema;
import io.apicurio.datamodels.core.models.common.ISecurityRequirementParent;
import io.apicurio.datamodels.core.models.common.IServerParent;
import io.apicurio.datamodels.core.models.common.Info;
import io.apicurio.datamodels.core.models.common.Parameter;
import io.apicurio.datamodels.core.models.common.Schema;
import io.apicurio.datamodels.core.models.common.SecurityRequirement;
import io.apicurio.datamodels.core.models.common.SecurityScheme;
import io.apicurio.datamodels.core.models.common.Server;
import io.apicurio.datamodels.openapi.models.IOasHeaderParent;
import io.apicurio.datamodels.openapi.models.IOasParameterParent;
import io.apicurio.datamodels.openapi.models.OasHeader;
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
import io.apicurio.datamodels.openapi.v3.models.Oas30Header;
import io.apicurio.datamodels.openapi.v3.models.Oas30MediaType;
import io.apicurio.datamodels.openapi.v3.models.Oas30Operation;
import io.apicurio.datamodels.openapi.v3.models.Oas30Parameter;
import io.apicurio.datamodels.openapi.v3.models.Oas30ResponseDefinition;
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
            case "AddParameterExampleCommand_30":
            { return new AddParameterExampleCommand_30(); }
            case "AddMessageExampleCommand_Aai20":
            { return new AddMessageExampleCommand_Aai20(); }
            case "AddPathItemCommand":
            case "AddPathItemCommand_20":
            case "AddPathItemCommand_30":
            { return new AddPathItemCommand(); }
            case "AddSecurityRequirementCommand":
            { return new AddSecurityRequirementCommand(); }
            case "AddOneOfInMessageCommand":
            { return new AddOneOfInMessageCommand(); }
            case "AddResponseDefinitionCommand_20":
            { return new AddResponseDefinitionCommand_20(); }
            case "AddResponseDefinitionCommand_30":
            { return new AddResponseDefinitionCommand_30(); }
            case "AddChildSchemaCommand":
            { return new AddChildSchemaCommand(); }
            case "AddChannelItemCommand":
            case "AddChannelItemCommand_Aai20":
            { return new AddChannelItemCommand(); }
            case "AddSchemaDefinitionCommand_Aai20":
            { return new AddSchemaDefinitionCommand_Aai20(); }

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
            case "ChangeHeaderTypeCommand":
            { return new ChangeHeaderTypeCommand(); }
            case "ChangeHeaderCommand":
            { return new ChangeHeaderCommand(); }
            case "ChangeParameterTypeCommand_20":
            { return new ChangeParameterTypeCommand_20(); }
            case "ChangeParameterDefinitionTypeCommand_20":
            { return new ChangeParameterDefinitionTypeCommand_20(); }
            case "ChangeParameterTypeCommand_30":
            { return new ChangeParameterTypeCommand_30(); }
            case "ChangeParameterDefinitionTypeCommand_30":
            { return new ChangeParameterDefinitionTypeCommand_30(); }
            case "ChangePropertyTypeCommand":
            case "ChangePropertyTypeCommand_Oas":
            case "ChangePropertyTypeCommand_20":
            case "ChangePropertyTypeCommand_30":
            { return new ChangePropertyTypeCommand_Oas(); }
            case "ChangePropertyTypeCommand_Aai20":
            { return new ChangePropertyTypeCommand_Aai20(); }
            case "ChangeResponseTypeCommand":
            case "ChangeResponseTypeCommand_20":
            case "ChangeResponseDefinitionTypeCommand_20":
            { return new ChangeResponseTypeCommand(); }
            case "ChangeSecuritySchemeCommand_20":
            { return new ChangeSecuritySchemeCommand_20(); }
            case "ChangeSecuritySchemeCommand_30":
            { return new ChangeSecuritySchemeCommand_30(); }
            case "ChangeSecuritySchemeCommand_Aai20":
            { return new ChangeSecuritySchemeCommand_Aai20(); }
            case "ChangeServerCommand":
            { return new ChangeServerCommand(); }
            case "ChangeServerCommand_Aai20":
            { return new ChangeServerCommand_Aai20(); }
            case "ChangeSchemaTypeCommand":
            { return new ChangeSchemaTypeCommand(); }
            case "ChangeSchemaInheritanceCommand":
            { return new ChangeSchemaInheritanceCommand(); }
            case "ChangePayloadRefCommand_Aai20":
            { return new ChangePayloadRefCommand_Aai20(); }
            case "ChangeHeadersRefCommand_Aai20":
            { return new ChangeHeadersRefCommand_Aai20(); }

            /** Delete Commands **/
            case "DeleteContactCommand_20":
            case "DeleteContactCommand_30":
            case "DeleteContactCommand":
            { return new DeleteContactCommand(); }
            case "DeleteAllExamplesCommand_30":
            case "DeleteAllExamplesCommand":
            { return new DeleteAllExamplesCommand(); }
            case "DeleteAllParameterExamplesCommand_30":
            case "DeleteAllParameterExamplesCommand":
            { return new DeleteAllParameterExamplesCommand(); }
            case "DeleteAllMessageExamplesCommand_Aai20":
            { return new DeleteAllMessageExamplesCommand_Aai20(); }
            case "DeleteAllOperationsCommand":
            { return new DeleteAllOperationsCommand(); }
            case "DeleteAllOperationsCommand_Aai20":
            { return new DeleteAllOperationsCommand_Aai20(); }
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
            case "DeleteAllHeadersCommand":
            { return new DeleteAllHeadersCommand(); }
            case "DeleteAllServersCommand_Aai20":
            { return new DeleteAllServersCommand_Aai20(); }
            case "DeleteAllTagsCommand":
            { return new DeleteAllTagsCommand(); }
            case "DeleteAllChildSchemasCommand":
            { return new DeleteAllChildSchemasCommand(); }
            case "DeleteExampleCommand_20":
            { return new DeleteExampleCommand_20(); }
            case "DeleteExampleCommand_30":
            { return new DeleteExampleCommand_30(); }
            case "DeleteParameterExampleCommand_30":
            { return new DeleteParameterExampleCommand_30(); }
            case "DeleteMessageExampleCommand_Aai20":
            { return new DeleteMessageExampleCommand_Aai20(); }
            case "DeleteExtensionCommand":
            { return new DeleteExtensionCommand(); }
            case "DeleteLicenseCommand":
            case "DeleteLicenseCommand_20":
            case "DeleteLicenseCommand_30":
            { return new DeleteLicenseCommand(); }
            case "DeleteMediaTypeCommand":
            { return new DeleteMediaTypeCommand(); }
            case "DeleteHeaderCommand":
            { return new DeleteHeaderCommand(); }
            case "DeleteOperationCommand":
            case "DeleteOperationCommand_20":
            case "DeleteOperationCommand_30":
            { return new DeleteOperationCommand(); }
            case "DeleteOperationCommand_Aai20":
            { return new DeleteOperationCommand_Aai20(); }
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
            case "DeleteSchemaDefinitionCommand_Aai20":
            { return new DeleteSchemaDefinitionCommand_Aai20(); }
            case "DeleteResponseDefinitionCommand_20":
            { return new DeleteResponseDefinitionCommand_20(); }
            case "DeleteResponseDefinitionCommand_30":
            { return new DeleteResponseDefinitionCommand_30(); }
            case "DeleteSecurityRequirementCommand":
            { return new DeleteSecurityRequirementCommand(); }
            case "DeleteSecuritySchemeCommand_20":
            { return new DeleteSecuritySchemeCommand_20(); }
            case "DeleteSecuritySchemeCommand_30":
            { return new DeleteSecuritySchemeCommand_30(); }
            case "DeleteSecuritySchemeCommand_Aai20":
            { return new DeleteSecuritySchemeCommand_Aai20(); }
            case "DeleteServerCommand":
            { return new DeleteServerCommand(); }
            case "DeleteServerCommand_Aai20":
            { return new DeleteServerCommand_Aai20(); }
            case "DeleteTagCommand":
            case "DeleteTagCommand_20":
            case "DeleteTagCommand_30":
            { return new DeleteTagCommand(); }
            case "DeleteChildSchemaCommand":
            { return new DeleteChildSchemaCommand(); }
            case "DeleteChannelCommand":
            case "DeleteChannelCommand_Aai20":
            { return new DeleteChannelCommand(); }
            case "DeleteMessageDefinitionCommand":
            case "DeleteMessageDefinitionCommand_Aai20":
            { return new DeleteMessageDefinitionCommand(); }
            case "DeleteMessageTraitDefinitionCommand":
            case "DeleteMessageTraitDefinitionCommand_Aai20":
            { return new DeleteMessageTraitDefinitionCommand(); }
            case "DeleteOneOfMessageCommand":
            { return new DeleteOneOfMessageCommand(); }
            case "DeleteOperationTraitDefinitionCommand":
            case "DeleteOperationTraitDefinitionCommand_Aai20":
            { return new DeleteOperationTraitDefinitionCommand(); }

            /** New Commands **/

            case "NewMediaTypeCommand":
            { return new NewMediaTypeCommand(); }
            case "NewHeaderCommand":
            { return new NewHeaderCommand(); }
            case "NewOperationCommand":
            case "NewOperationCommand_20":
            case "NewOperationCommand_30":
            { return new NewOperationCommand(); }
            case "NewOperationCommand_Aai20":
            { return new NewOperationCommand_Aai20(); }
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
            case "NewSecuritySchemeCommand_Aai20":
            { return new NewSecuritySchemeCommand_Aai20(); }
            case "NewServerCommand":
            { return new NewServerCommand(); }
            case "NewServerCommand_Aai20":
            { return new NewServerCommand_Aai20(); }
            case "NewTagCommand":
            case "NewTagCommand_20":
            case "NewTagCommand_30":
            { return new NewTagCommand(); }
            case "NewResponseDefinitionCommand_20":
            { return new NewResponseDefinitionCommand_20(); }
            case "NewResponseDefinitionCommand_30":
            { return new NewResponseDefinitionCommand_30(); }
            case "NewChannelCommand":
            case "NewChannelCommand_Aai20":
            { return new NewChannelCommand(); }
            case "NewSchemaDefinitionCommand_Aai20":
            { return new NewSchemaDefinitionCommand_Aai20(); }
            case "NewSchemaPropertyCommand_Aai20":
            { return new NewSchemaPropertyCommand_Aai20(); }
            case "NewMessageDefinitionCommand":
            case "NewMessageDefinitionCommand_Aai20":
            { return new NewMessageDefinitionCommand(); }
            case "NewMessageTraitDefinitionCommand":
            case "NewMessageTraitDefinitionCommand_Aai20":
            { return new NewMessageTraitDefinitionCommand(); }
            case "NewOperationTraitDefinitionCommand":
            case "NewOperationTraitDefinitionCommand_Aai20":
            { return new NewOperationTraitDefinitionCommand(); }

            /** Rename Commands **/

            case "RenameParameterCommand":
            { return new RenameParameterCommand(); }
            case "RenameHeaderCommand":
            { return new RenameHeaderCommand(); }
            case "RenameChannelItemCommand":
            { return new RenameChannelItemCommand(); }
            case "RenamePathItemCommand":
            { return new RenamePathItemCommand(); }
            case "RenamePropertyCommand":
            { return new RenamePropertyCommand(); }
            case "RenameSchemaDefinitionCommand_20":
            { return new RenameSchemaDefinitionCommand_20(); }
            case "RenameSchemaDefinitionCommand_30":
            { return new RenameSchemaDefinitionCommand_30(); }
            case "RenameSchemaDefinitionCommand_Aai20":
            { return new RenameSchemaDefinitionCommand_Aai20(); }
            case "RenameSecuritySchemeCommand":
            { return new RenameSecuritySchemeCommand(); }
            case "RenameServerCommand_Aai20":
            { return new RenameServerCommand_Aai20(); }
            case "RenameTagDefinitionCommand":
            { return new RenameTagDefinitionCommand(); }
            case "RenameResponseDefinitionCommand_20":
            { return new RenameResponseDefinitionCommand_20(); }
            case "RenameResponseDefinitionCommand_30":
            { return new RenameResponseDefinitionCommand_30(); }
            case "RenameMessageDefinitionCommand":
            case "RenameMessageDefinitionCommand_Aai20":
            { return new RenameMessageDefinitionCommand(); }
            case "RenameMessageTraitDefinitionCommand":
            case "RenameMessageTraitDefinitionCommand_Aai20":
            { return new RenameMessageTraitDefinitionCommand(); }
            case "RenameOperationTraitDefinitionCommand":
            case "RenameOperationTraitDefinitionCommand_Aai20":
            { return new RenameOperationTraitDefinitionCommand(); }

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
            case "ReplaceChannelItemCommand":
            { return new ReplaceChannelItemCommand(); }
            case "ReplaceSchemaDefinitionCommand_20":
            { return new ReplaceSchemaDefinitionCommand_20(); }
            case "ReplaceSchemaDefinitionCommand_30":
            { return new ReplaceSchemaDefinitionCommand_30(); }
            case "ReplaceSchemaDefinitionCommand_Aai20":
            { return new ReplaceSchemaDefinitionCommand_Aai20(); }
            case "ReplaceSecurityRequirementCommand":
            { return new ReplaceSecurityRequirementCommand(); }
            case "ReplaceResponseDefinitionCommand_20":
            { return new ReplaceResponseDefinitionCommand_20(); }
            case "ReplaceResponseDefinitionCommand_30":
            { return new ReplaceResponseDefinitionCommand_30(); }

            /** Set Commands **/

            case "SetPropertyCommand":
            { return new SetPropertyCommand(); }
            case "SetExampleCommand_20":
            { return new SetExampleCommand_20(); }
            case "SetExampleCommand_30":
            { return new SetExampleCommand_30(); }
            case "SetParameterExampleCommand_30":
            { return new SetParameterExampleCommand_30(); }
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

    public static final ICommand createAddExampleCommand(Oas30Header header, Object example,
                                                         String exampleName, String exampleSummary, String exampleDescription) {
        return new AddExampleCommand_30(header, example, exampleName, exampleSummary, exampleDescription);
    }

    public static final ICommand createAddOneOfInMessageCommand(AaiMessage message, AaiMessage parentMessage) {
        return new AddOneOfInMessageCommand(message, parentMessage);
    }

    public static final ICommand createAddParameterExampleCommand(Oas30Parameter parameter, Object example,
            String exampleName, String exampleSummary, String exampleDescription) {
        return new AddParameterExampleCommand_30(parameter, example, exampleName, exampleSummary, exampleDescription);
    }

    public static final ICommand createAddMessageExampleCommand_Aai20(AaiOperation operation, Object exampleValue) {
        return new AddMessageExampleCommand_Aai20(operation, exampleValue);
    }

    public static final ICommand createAddSchemaDefinitionCommand(DocumentType docType,
            String definitionName, Object from) {
        if (docType == DocumentType.openapi2) {
            return new AddSchemaDefinitionCommand_20(definitionName, from);
        }
        if (docType == DocumentType.openapi3) {
            return new AddSchemaDefinitionCommand_30(definitionName, from);
        }
        if (docType == DocumentType.asyncapi2) {
            return new AddSchemaDefinitionCommand_Aai20(definitionName, from);
        }
        throw new RuntimeException("Document type not supported by this command.");
    }

    public static final ICommand createAddResponseDefinitionCommand(DocumentType docType,
            String definitionName, Object from) {
        if (docType == DocumentType.openapi2) {
            return new AddResponseDefinitionCommand_20(definitionName, from);
        }
        if (docType == DocumentType.openapi3) {
            return new AddResponseDefinitionCommand_30(definitionName, from);
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

    public static final ICommand createAddChildSchemaCommand(OasSchema schema, OasSchema childSchema, String childType) {
        return new AddChildSchemaCommand(schema, childSchema, childType);
    }

    public static final ICommand createAddChannelItemCommand(String channelItemName, Object from) {
        return new AddChannelItemCommand(channelItemName, from);
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

    public static final ICommand createChangeSchemaInheritanceCommand(OasSchema schema, String inheritanceType) {
        return new ChangeSchemaInheritanceCommand(schema, inheritanceType);
    }

    public static final ICommand createChangeContactCommand(String name, String email, String url) {
        return new ChangeContactCommand(name, email, url);
    }

    public static final ICommand createChangeHeaderCommand(OasHeader header, OasHeader newHeader) {
        return new ChangeHeaderCommand(header, newHeader);
    }

    public static final ICommand createChangeHeaderTypeCommand(Oas30Header header,
                                                               SimplifiedType newType) {
        return new ChangeHeaderTypeCommand(header, newType);
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

    public static final ICommand createChangePropertyTypeCommand(IPropertySchema property,
                                                                 SimplifiedPropertyType newType) {
        DocumentType docType = ((Node) property).ownerDocument().getDocumentType();
        if (docType == DocumentType.openapi2 || docType == DocumentType.openapi3) {
            return new ChangePropertyTypeCommand_Oas(property, newType);
        }
        if (docType == DocumentType.asyncapi2) {
            return new ChangePropertyTypeCommand_Aai20(property, newType);
        }
        throw new RuntimeException("Document type not supported by this command.");
    }

    public static final ICommand createChangeSchemaTypeCommand(OasSchema schema, SimplifiedType newType) {
        return new ChangeSchemaTypeCommand(schema, newType);
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
        } else if (docType == DocumentType.asyncapi2) {
            return new ChangeSecuritySchemeCommand_Aai20(scheme);
        }
        throw new RuntimeException("Document type not supported by this command.");
    }

    public static final ICommand createChangeServerCommand(Server server) {
        return new ChangeServerCommand(server);
    }

    public static final ICommand createChangeServerCommand_Aai20(Aai20Server server) {
        return new ChangeServerCommand_Aai20(server);
    }

    public static final ICommand createChangePayloadRefCommand_Aai20(String payloadRef, AaiOperation operation) {
        return new ChangePayloadRefCommand_Aai20(payloadRef, operation);
    }

    public static final ICommand createChangeHeadersRefCommand_Aai20(String headersRef, AaiOperation operation) {
        return new ChangeHeadersRefCommand_Aai20(headersRef, operation);
    }


    /* ***  Delete Commands  *** */

    public static final ICommand createDeleteContactCommand(Info info) {
        return new DeleteContactCommand(info);
    }

    public static final ICommand createDeleteChildSchemaCommand(OasSchema schema) {
        return new DeleteChildSchemaCommand(schema);
    }

    public static final ICommand createDeleteAllExamplesCommand(Oas30MediaType mediaType) {
        return new DeleteAllExamplesCommand(mediaType);
    }

    public static final ICommand createDeleteAllParameterExamplesCommand(Oas30Parameter parameter) {
        return new DeleteAllParameterExamplesCommand(parameter);
    }

    public static final ICommand createDeleteAllMessageExamplesCommand_Aai20(AaiOperation operation) {
        return new DeleteAllMessageExamplesCommand_Aai20(operation);
    }

    public static final ICommand createDeleteAllOperationsCommand(OasPathItem pathItem) {
        return new DeleteAllOperationsCommand(pathItem);
    }

    public static final ICommand createDeleteAllHeadersCommand(IOasHeaderParent header) {
        return new DeleteAllHeadersCommand(header);
    }

    public static final ICommand createDeleteAllOperationsCommand_Aai20(AaiChannelItem pathItem) {
        return new DeleteAllOperationsCommand_Aai20(pathItem);
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

    public static final ICommand createDeleteAllServersCommand_Aai20() {
        return new DeleteAllServersCommand_Aai20();
    }

    public static final ICommand createDeleteAllTagsCommand() {
        return new DeleteAllTagsCommand();
    }

    public static final ICommand createDeleteAllChildSchemasCommand(OasSchema parent, String type) {
        return new DeleteAllChildSchemasCommand(parent, type);
    }

    public static final ICommand createDelete20ExampleCommand(Oas20Response response, String contentType) {
        return new DeleteExampleCommand_20(response, contentType);
    }

    public static final ICommand createDeleteExampleCommand(Oas30Example example) {
        return new DeleteExampleCommand_30(example);
    }

    public static final ICommand createDeleteParameterExampleCommand(Oas30Example example) {
        return new DeleteParameterExampleCommand_30(example);
    }

    public static final ICommand createDeleteMessageExampleCommand_Aai20(AaiOperation operation, Object exampleValue) {
        return new DeleteMessageExampleCommand_Aai20(operation, exampleValue);
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

    public static final ICommand createDeleteHeaderCommand(OasHeader header) {
        return new DeleteHeaderCommand(header);
    }

    public static final ICommand createDeleteOperationCommand(String opMethod, OasPathItem pathItem) {
        return new DeleteOperationCommand(opMethod, pathItem);
    }

    public static final ICommand createDeleteOperationCommand_Aai20(String opType, AaiChannelItem channelItem) {
        return new DeleteOperationCommand_Aai20(opType, channelItem);
    }

    public static final ICommand createDeleteParameterCommand(OasParameter parameter) {
        return new DeleteParameterCommand(parameter);
    }

    public static final ICommand createDeletePathCommand(String path) {
        return new DeletePathCommand(path);
    }

    public static final ICommand createDeletePropertyCommand(IPropertySchema property) {
        return new DeletePropertyCommand(property);
    }

    public static final ICommand createDeleteOneOfMessageCommand(AaiMessage message, int idx) {
        return new DeleteOneOfMessageCommand(message,idx);
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
        if (docType == DocumentType.asyncapi2) {
            return new DeleteSchemaDefinitionCommand_Aai20(definitionName);
        }
        throw new RuntimeException("Document type not supported by this command.");
    }

    public static final ICommand createDeleteResponseDefinitionCommand(
            DocumentType docType, String definitionName) {
        if (docType == DocumentType.openapi2) {
            return new DeleteResponseDefinitionCommand_20(definitionName);
        }
        if (docType == DocumentType.openapi3) {
            return new DeleteResponseDefinitionCommand_30(definitionName);
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
        if (docType == DocumentType.asyncapi2) {
            return new DeleteSecuritySchemeCommand_Aai20(schemeName);
        }
        throw new RuntimeException("Document type not supported by this command.");
    }

    public static final ICommand createDeleteServerCommand(Server server) {
        return new DeleteServerCommand(server);
    }

    public static final ICommand createDeleteServerCommand_Aai20(Aai20Server server) {
        return new DeleteServerCommand_Aai20(server);
    }

    public static final ICommand createDeleteTagCommand(String tagName) {
        return new DeleteTagCommand(tagName);
    }

    public static final ICommand createDeleteChannelCommand(String channelName) {
        return new DeleteChannelCommand(channelName);
    }

    public static final ICommand createDeleteMessageDefinitionCommand(String name) {
        return new DeleteMessageDefinitionCommand(name);
    }

    public static final ICommand createDeleteMessageTraitDefinitionCommand(String name) {
        return new DeleteMessageTraitDefinitionCommand(name);
    }

    public static final ICommand createDeleteOperationTraitDefinitionCommand(String name) {
        return new DeleteOperationTraitDefinitionCommand(name);
    }

    /** New Commands **/

    public static final ICommand createNewMediaTypeCommand(IOas30MediaTypeParent parent, String newMediaType) {
        return new NewMediaTypeCommand(parent, newMediaType);
    }

    public static final ICommand createNewHeaderCommand(IOasHeaderParent parent, String name) {
        return new NewHeaderCommand(parent, name);
    }

    public static final ICommand createNewOperationCommand(String path, String method) {
        return new NewOperationCommand(path, method);
    }

    public static final ICommand createNewOperationCommand_Aai20(String channel, String opType) {
        return new NewOperationCommand_Aai20(channel, opType);
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
        if (docType == DocumentType.asyncapi2) {
            return new NewSchemaDefinitionCommand_Aai20(definitionName, example, description);
        }
        throw new RuntimeException("Document type not supported by this command.");
    }

    public static final ICommand createNewResponseDefinitionCommand(DocumentType docType,
            String definitionName, String description) {
        if (docType == DocumentType.openapi2) {
            return new NewResponseDefinitionCommand_20(definitionName, description);
        }
        if (docType == DocumentType.openapi3) {
            return new NewResponseDefinitionCommand_30(definitionName, description);
        }
        throw new RuntimeException("Document type not supported by this command.");
    }

    public static final ICommand createNewSchemaPropertyCommand(Schema schema, String propertyName,
            String description, SimplifiedPropertyType newType) {
        if (schema instanceof AaiSchema) {
            return new NewSchemaPropertyCommand_Aai20(schema, propertyName, description, newType);
        }
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
        if (docType == DocumentType.asyncapi2) {
            return new NewSecuritySchemeCommand_Aai20(scheme);
        }
        throw new RuntimeException("Document type not supported by this command.");
    }

    public static final ICommand createNewServerCommand(IServerParent parent, Server server) {
        return new NewServerCommand(parent, server);
    }

    public static final ICommand createNewServerCommand_Aai20(Aai20Document parent, Aai20Server server) {
        return new NewServerCommand_Aai20(parent, server);
    }

    public static final ICommand createNewTagCommand(String name, String description) {
        return new NewTagCommand(name, description);
    }

    public static final ICommand createNewChannelCommand(String name) {
        return new NewChannelCommand(name);
    }

    public static final ICommand createNewSchemaDefinitionCommand_Aai20(String name, Object example, String descriptioon) {
        return new NewSchemaDefinitionCommand_Aai20(name, example, descriptioon);
    }

    public static final ICommand createNewMessageDefinitionCommand(String name, String description) {
        return new NewMessageDefinitionCommand(name, description);
    }

    public static final ICommand createNewMessageTraitDefinitionCommand(String name, String description) {
        return new NewMessageTraitDefinitionCommand(name, description);
    }

    public static final ICommand createNewOperationTraitDefinitionCommand(String name, String description) {
        return new NewOperationTraitDefinitionCommand(name, description);
    }

    /** Rename Commands **/

    public static final ICommand createRenameParameterCommand(IOasParameterParent parent,
            String oldParamName, String newParamName, String paramIn) {
        return new RenameParameterCommand(parent, oldParamName, newParamName, paramIn);
    }

    public static final ICommand createRenameHeaderCommand(OasHeader parent,
                                                              String oldHeaderName, String newHeaderName) {
        return new RenameHeaderCommand(parent, oldHeaderName, newHeaderName);
    }

    public static final ICommand createRenameChannelItemCommand(String oldChannelName, String newChannelName) {
        return new RenameChannelItemCommand(oldChannelName, newChannelName);
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
        if (docType == DocumentType.asyncapi2) {
            return new RenameSchemaDefinitionCommand_Aai20(oldName, newName);
        }
        throw new RuntimeException("Document type not supported by this command.");
    }

    public static final ICommand createRenameResponseDefinitionCommand(DocumentType docType,
            String oldName, String newName) {
        if (docType == DocumentType.openapi2) {
            return new RenameResponseDefinitionCommand_20(oldName, newName);
        }
        if (docType == DocumentType.openapi3) {
            return new RenameResponseDefinitionCommand_30(oldName, newName);
        }
        throw new RuntimeException("Document type not supported by this command.");
    }

    public static final ICommand createRenameSecuritySchemeCommand(String oldSchemeName,
            String newSchemeName) {
        return new RenameSecuritySchemeCommand(oldSchemeName, newSchemeName);
    }

    public static final ICommand createRenameServerCommand(DocumentType docType, String oldServerName, String newServerName) {
        if (docType == DocumentType.asyncapi2) {
            return new RenameServerCommand_Aai20(oldServerName, newServerName);
        }
        throw new RuntimeException("Document type not supported by this command.");
    }

    public static final ICommand createRenameTagDefinitionCommand(String oldTag, String newTag) {
        return new RenameTagDefinitionCommand(oldTag, newTag);
    }

    public static final ICommand createRenameMessageDefinitionCommand(String oldName, String newName) {
        return new RenameMessageDefinitionCommand(oldName, newName);
    }

    public static final ICommand createRenameMessageTraitDefinitionCommand(String oldName, String newName) {
        return new RenameMessageTraitDefinitionCommand(oldName, newName);
    }

    public static final ICommand createRenameOperationTraitDefinitionCommand(String oldName, String newName) {
        return new RenameOperationTraitDefinitionCommand(oldName, newName);
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

    public static final ICommand createReplaceChannelItemCommand(AaiChannelItem old, AaiChannelItem replacement) {
        return new ReplaceChannelItemCommand(old, replacement);
    }

    public static final ICommand createReplaceSchemaDefinitionCommand(DocumentType docType,
            IDefinition old, IDefinition replacement) {
        if (docType == DocumentType.openapi2) {
            return new ReplaceSchemaDefinitionCommand_20((Oas20SchemaDefinition) old, (Oas20SchemaDefinition) replacement);
        }
        if (docType == DocumentType.openapi3) {
            return new ReplaceSchemaDefinitionCommand_30((Oas30SchemaDefinition) old, (Oas30SchemaDefinition) replacement);
        }
        if (docType == DocumentType.asyncapi2) {
            return new ReplaceSchemaDefinitionCommand_Aai20((Aai20SchemaDefinition) old, (Aai20SchemaDefinition) replacement);
        }
        throw new RuntimeException("Document type not supported by this command.");
    }

    public static final ICommand createReplaceResponseDefinitionCommand(DocumentType docType,
            IDefinition old, IDefinition replacement) {
        if (docType == DocumentType.openapi2) {
            return new ReplaceResponseDefinitionCommand_20((Oas20ResponseDefinition) old, (Oas20ResponseDefinition) replacement);
        }
        if (docType == DocumentType.openapi3) {
            return new ReplaceResponseDefinitionCommand_30((Oas30ResponseDefinition) old, (Oas30ResponseDefinition) replacement);
        }
        throw new RuntimeException("Document type not supported by this command.");
    }

    public static final ICommand createReplaceSecurityRequirementCommand(SecurityRequirement old,
            SecurityRequirement replacement) {
        return new ReplaceSecurityRequirementCommand(old, replacement);
    }

    /** Set Commands **/

    public static <T> ICommand createSetPropertyCommand(NodePath path, String property, T newValue) {
        return new SetPropertyCommand<T>(path, property, newValue);
    }

    public static final ICommand createSetExampleCommand(DocumentType docType, IExampleParent parent, Object example,
            String nameOrContentType) {
        if (docType == DocumentType.openapi2) {
            return new SetExampleCommand_20((Oas20Response) parent, example, nameOrContentType);
        }
        if (docType == DocumentType.openapi3) {
            return new SetExampleCommand_30(parent, example, nameOrContentType);
        }
        throw new RuntimeException("Document type not supported by this command.");
    }

    public static final ICommand createSetParameterExampleCommand(IExampleParent parent, Object example, String exampleName) {
        return new SetParameterExampleCommand_30((Oas30Parameter) parent, example, exampleName);
    }

    public static final ICommand createSetExtensionCommand(ExtensibleNode parent, String name, Object value) {
        return new SetExtensionCommand(parent, name, value);
    }

}
