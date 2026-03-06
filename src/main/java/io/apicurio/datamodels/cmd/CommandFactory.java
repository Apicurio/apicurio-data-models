package io.apicurio.datamodels.cmd;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.apicurio.datamodels.cmd.commands.AddChannelItemCommand;
import io.apicurio.datamodels.cmd.commands.AddExampleCommand;
import io.apicurio.datamodels.cmd.commands.AddExtensionCommand;
import io.apicurio.datamodels.cmd.commands.AddMediaTypeCommand;
import io.apicurio.datamodels.cmd.commands.AddOperationSecurityRequirementCommand;
import io.apicurio.datamodels.cmd.commands.AddParameterCommand;
import io.apicurio.datamodels.cmd.commands.AddPathItemCommand;
import io.apicurio.datamodels.cmd.commands.AddRequestBodyCommand;
import io.apicurio.datamodels.cmd.commands.AddResponseCommand;
import io.apicurio.datamodels.cmd.commands.AddResponseDefinitionCommand;
import io.apicurio.datamodels.cmd.commands.AddResponseHeaderCommand;
import io.apicurio.datamodels.cmd.commands.AddSchemaDefinitionCommand;
import io.apicurio.datamodels.cmd.commands.AddSecurityRequirementCommand;
import io.apicurio.datamodels.cmd.commands.AddSecuritySchemeCommand;
import io.apicurio.datamodels.cmd.commands.AddServerCommand;
import io.apicurio.datamodels.models.asyncapi.AsyncApiDocument;
import io.apicurio.datamodels.cmd.commands.AddTagCommand;
import io.apicurio.datamodels.cmd.commands.ChangeContactCommand;
import io.apicurio.datamodels.cmd.commands.ChangeDescriptionCommand;
import io.apicurio.datamodels.cmd.commands.ChangeExtensionCommand;
import io.apicurio.datamodels.cmd.commands.ChangeLicenseCommand;
import io.apicurio.datamodels.cmd.commands.ChangeMediaTypeSchemaCommand;
import io.apicurio.datamodels.cmd.commands.ChangePropertyCommand;
import io.apicurio.datamodels.cmd.commands.ChangeTitleCommand;
import io.apicurio.datamodels.cmd.commands.ChangeVersionCommand;
import io.apicurio.datamodels.cmd.commands.CreateOperationCommand;
import io.apicurio.datamodels.cmd.commands.CreatePathCommand;
import io.apicurio.datamodels.cmd.commands.CreateSchemaCommand;
import io.apicurio.datamodels.cmd.commands.DeleteAllChildSchemasCommand;
import io.apicurio.datamodels.cmd.commands.DeleteAllExamplesCommand;
import io.apicurio.datamodels.cmd.commands.DeleteAllHeadersCommand;
import io.apicurio.datamodels.cmd.commands.DeleteAllOperationsCommand;
import io.apicurio.datamodels.cmd.commands.DeleteAllParametersCommand;
import io.apicurio.datamodels.cmd.commands.DeleteAllPropertiesCommand;
import io.apicurio.datamodels.cmd.commands.DeleteSchemaPropertyCommand;
import io.apicurio.datamodels.cmd.commands.DeleteAllResponsesCommand;
import io.apicurio.datamodels.cmd.commands.DeleteAllSecurityRequirementsCommand;
import io.apicurio.datamodels.cmd.commands.DeleteAllSecuritySchemesCommand;
import io.apicurio.datamodels.cmd.commands.DeleteAllServersCommand;
import io.apicurio.datamodels.cmd.commands.DeleteAllTagsCommand;
import io.apicurio.datamodels.cmd.commands.DeleteContactCommand;
import io.apicurio.datamodels.cmd.commands.DeleteExtensionCommand;
import io.apicurio.datamodels.cmd.commands.DeleteLicenseCommand;
import io.apicurio.datamodels.cmd.commands.DeleteMediaTypeCommand;
import io.apicurio.datamodels.cmd.commands.DeleteOperationCommand;
import io.apicurio.datamodels.cmd.commands.DeleteOperationSecurityRequirementCommand;
import io.apicurio.datamodels.cmd.commands.DeleteParameterCommand;
import io.apicurio.datamodels.cmd.commands.DeletePathCommand;
import io.apicurio.datamodels.cmd.commands.DeleteRequestBodyCommand;
import io.apicurio.datamodels.cmd.commands.DeleteResponseCommand;
import io.apicurio.datamodels.cmd.commands.DeleteResponseHeaderCommand;
import io.apicurio.datamodels.cmd.commands.DeleteSchemaCommand;
import io.apicurio.datamodels.cmd.commands.DeleteSecurityRequirementCommand;
import io.apicurio.datamodels.cmd.commands.DeleteSecuritySchemeCommand;
import io.apicurio.datamodels.cmd.commands.DeleteServerCommand;
import io.apicurio.datamodels.cmd.commands.DeleteTagCommand;
import io.apicurio.datamodels.cmd.commands.EnsureChildNodeCommand;
import io.apicurio.datamodels.cmd.commands.RenameTagCommand;
import io.apicurio.datamodels.cmd.commands.ReplaceOperationCommand;
import io.apicurio.datamodels.cmd.commands.ReplacePathItemCommand;
import io.apicurio.datamodels.cmd.commands.ReplaceResponseDefinitionCommand;
import io.apicurio.datamodels.cmd.commands.ReplaceSchemaDefinitionCommand;
import io.apicurio.datamodels.cmd.commands.ReplaceSecurityRequirementCommand;
import io.apicurio.datamodels.cmd.commands.UpdateNodeCommand;
import io.apicurio.datamodels.cmd.commands.UpdateSecuritySchemeCommand;
import io.apicurio.datamodels.models.Extensible;
import io.apicurio.datamodels.models.Info;
import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.Schema;
import io.apicurio.datamodels.models.SecurityRequirement;
import io.apicurio.datamodels.models.SecurityRequirementsParent;
import io.apicurio.datamodels.models.asyncapi.AsyncApiServer;
import io.apicurio.datamodels.models.openapi.OpenApiDocument;
import io.apicurio.datamodels.models.openapi.OpenApiEncoding;
import io.apicurio.datamodels.models.openapi.OpenApiExamplesParent;
import io.apicurio.datamodels.models.openapi.OpenApiHeader;
import io.apicurio.datamodels.models.openapi.OpenApiHeadersParent;
import io.apicurio.datamodels.models.openapi.OpenApiMediaType;
import io.apicurio.datamodels.models.openapi.OpenApiOperation;
import io.apicurio.datamodels.models.openapi.OpenApiParameter;
import io.apicurio.datamodels.models.openapi.OpenApiParametersParent;
import io.apicurio.datamodels.models.openapi.OpenApiPathItem;
import io.apicurio.datamodels.models.openapi.OpenApiResponse;
import io.apicurio.datamodels.models.openapi.OpenApiSchema;
import io.apicurio.datamodels.models.openapi.OpenApiServersParent;
import io.apicurio.datamodels.models.openapi.v30.OpenApi30Operation;
import io.apicurio.datamodels.models.openapi.v31.OpenApi31Operation;
import io.apicurio.datamodels.paths.NodePath;
import io.apicurio.datamodels.util.CommandUtil;

public class CommandFactory {

    public static ICommand create(String cmdType) {
        return CommandUtil.create(cmdType);
    }

    public static ICommand unmarshall(ObjectNode from) {
        return CommandUtil.unmarshall(from);
    }

    /**
     * Marshalls a command into a JSON object for storage or transmission.
     * @param command the command to serialize
     * @return the JSON representation of the command
     */
    public static ObjectNode marshall(ICommand command) {
        return CommandUtil.marshall(command);
    }

    public static ICommand createAddHeaderExampleCommand(OpenApiHeader header, JsonNode example,
                                                         String exampleName, String exampleSummary, String exampleDescription) {
        return new AddExampleCommand((OpenApiExamplesParent) header, example, exampleName, exampleSummary, exampleDescription);
    }
    public static ICommand createAddParameterExampleCommand(OpenApiParameter parameter, JsonNode example,
                                                            String exampleName, String exampleSummary, String exampleDescription) {
        return new AddExampleCommand((OpenApiExamplesParent) parameter, example, exampleName, exampleSummary, exampleDescription);
    }
    public static ICommand createAddHeaderExampleCommand(OpenApiMediaType mediaType, JsonNode example,
                                                         String exampleName, String exampleSummary, String exampleDescription) {
        return new AddExampleCommand((OpenApiExamplesParent) mediaType, example, exampleName, exampleSummary, exampleDescription);
    }

    public static ICommand createAddResponseDefinitionCommand(String definitionName, ObjectNode from) {
        return new AddResponseDefinitionCommand(definitionName, from);
    }

    public static ICommand createAddSchemaDefinitionCommand(String definitionName, ObjectNode from) {
        return new AddSchemaDefinitionCommand(definitionName, from);
    }

    public static ICommand createAddDocumentSecurityRequirementCommand(
            OpenApiDocument document, SecurityRequirement requirement) {
        return new AddSecurityRequirementCommand((SecurityRequirementsParent) document, requirement);
    }
    public static ICommand createAddOperationSecurityRequirementCommand(
            OpenApiOperation operation, SecurityRequirement requirement) {
        return new AddSecurityRequirementCommand((SecurityRequirementsParent) operation, requirement);
    }
    public static ICommand createAddServerSecurityRequirementCommand(
            AsyncApiServer server, SecurityRequirement requirement) {
        return new AddSecurityRequirementCommand((SecurityRequirementsParent) server, requirement);
    }

    public static <T> ICommand createChangePropertyCommand(Node node, String property, T newValue) {
        return new ChangePropertyCommand<T>(node, property, newValue);
    }

    public static ICommand createAddChannelItemCommand(String channelItemName, ObjectNode from) {
        return new AddChannelItemCommand(channelItemName, from);
    }

    public static ICommand createAddPathItemCommand(String pathItemName, ObjectNode from) {
        return new AddPathItemCommand(pathItemName, from);
    }

    public static ICommand createChangeTitleCommand(String newTitle) {
        return new ChangeTitleCommand(newTitle);
    }

    public static ICommand createChangeDescriptionCommand(String newDescription) {
        return new ChangeDescriptionCommand(newDescription);
    }

    public static ICommand createChangeVersionCommand(String newVersion) {
        return new ChangeVersionCommand(newVersion);
    }

    public static ICommand createChangeContactCommand(String name, String email, String url) {
        return new ChangeContactCommand(name, email, url);
    }

    public static ICommand createChangeLicenseCommand(String name, String url) {
        return new ChangeLicenseCommand(name, url);
    }

    public static ICommand createDeleteLicenseCommand(Info info) {
        return new DeleteLicenseCommand(info);
    }

    public static ICommand createDeleteContactCommand(Info info) {
        return new DeleteContactCommand(info);
    }

    public static ICommand createDeleteExtensionCommand(Extensible parent, String extensionName) {
        return new DeleteExtensionCommand(parent, extensionName);
    }

    public static ICommand createDeleteMediaTypeCommand(OpenApiMediaType mediaType) {
        return new DeleteMediaTypeCommand(mediaType);
    }

    public static ICommand createDeleteAllChildSchemasCommand(Schema parent, String type) {
        return new DeleteAllChildSchemasCommand(parent, type);
    }

    public static ICommand createDeleteAllMediaTypeExamplesCommand(OpenApiMediaType mediaType) {
        return new DeleteAllExamplesCommand((OpenApiExamplesParent) mediaType);
    }

    public static ICommand createDeleteAllParameterExamplesCommand(OpenApiParameter parameter) {
        return new DeleteAllExamplesCommand((OpenApiExamplesParent) parameter);
    }

    public static ICommand createDeleteAllHeaderExamplesCommand(OpenApiHeader header) {
        return new DeleteAllExamplesCommand((OpenApiExamplesParent) header);
    }

    public static ICommand createDeleteAllResponseHeadersCommand(OpenApiResponse header) {
        return new DeleteAllHeadersCommand((OpenApiHeadersParent) header);
    }
    public static ICommand createDeleteAllEncodingHeadersCommand(OpenApiEncoding header) {
        return new DeleteAllHeadersCommand((OpenApiHeadersParent) header);
    }

    public static ICommand createDeleteAllPathItemOperationsCommand(OpenApiPathItem pathItem) {
        return new DeleteAllOperationsCommand(pathItem);
    }

    public static ICommand createDeleteAllPathItemParametersCommand(OpenApiPathItem parent, String type) {
        return new DeleteAllParametersCommand((OpenApiParametersParent) parent, type);
    }

    public static ICommand createDeleteAllOperationParametersCommand(OpenApiOperation parent, String type) {
        return new DeleteAllParametersCommand((OpenApiParametersParent) parent, type);
    }

    public static ICommand createDeleteAllPropertiesCommand(Schema schema) {
        return new DeleteAllPropertiesCommand(schema);
    }

    /**
     * Creates a command to delete a single property from a schema definition.
     * @param schemaDefinitionName the name of the schema definition
     * @param propertyName the name of the property to delete
     * @return the command
     */
    public static ICommand createDeleteSchemaPropertyCommand(String schemaDefinitionName,
            String propertyName) {
        return new DeleteSchemaPropertyCommand(schemaDefinitionName, propertyName);
    }

    public static ICommand createDeleteAllResponsesCommand(OpenApiOperation operation) {
        return new DeleteAllResponsesCommand(operation);
    }

    public static ICommand createDeleteAllServerSecurityRequirementsCommand(AsyncApiServer server) {
        return new DeleteAllSecurityRequirementsCommand(server);
    }
    public static ICommand createDeleteAllOperationSecurityRequirementsCommand(OpenApiOperation operation) {
        return new DeleteAllSecurityRequirementsCommand(operation);
    }
    public static ICommand createDeleteAllDocumentSecurityRequirementsCommand(OpenApiDocument document) {
        return new DeleteAllSecurityRequirementsCommand(document);
    }

    public static ICommand createDeleteAllSecuritySchemesCommand() {
        return new DeleteAllSecuritySchemesCommand();
    }

    public static ICommand createDeleteAllTagsCommand() {
        return new DeleteAllTagsCommand();
    }

    public static ICommand createDeleteAllDocumentServersCommand(OpenApiDocument document) {
        return new DeleteAllServersCommand((OpenApiServersParent) document);
    }
    public static ICommand createDeleteAllPathItemServersCommand(OpenApiPathItem pathItem) {
        return new DeleteAllServersCommand((OpenApiServersParent) pathItem);
    }
    public static ICommand createDeleteAllOperationServersCommand(OpenApiOperation operation) {
        return new DeleteAllServersCommand((OpenApiServersParent) operation);
    }

    public static ICommand createReplacePathItemCommand(OpenApiPathItem old, OpenApiPathItem replacement) {
        return new ReplacePathItemCommand(old, replacement);
    }

    public static ICommand createReplaceOperationCommand(OpenApiOperation old,
                                                               OpenApiOperation replacement) {
        return new ReplaceOperationCommand(old, replacement);
    }

    public static ICommand createReplaceSchemaDefinitionCommand(OpenApiSchema old, OpenApiSchema replacement) {
        return new ReplaceSchemaDefinitionCommand(old, replacement);
    }

    public static ICommand createReplaceResponseDefinitionCommand(OpenApiResponse old, OpenApiResponse replacement) {
        return new ReplaceResponseDefinitionCommand(old, replacement);
    }

    public static ICommand createReplaceSecurityRequirementCommand(SecurityRequirement old,
                                                                         SecurityRequirement replacement) {
        return new ReplaceSecurityRequirementCommand(old, replacement);
    }

    // --- Extension commands ---

    public static ICommand createAddExtensionCommand(Extensible parent, String name, JsonNode value) {
        return new AddExtensionCommand(parent, name, value);
    }

    public static ICommand createChangeExtensionCommand(Extensible parent, String name, JsonNode newValue) {
        return new ChangeExtensionCommand(parent, name, newValue);
    }

    // --- Tag commands ---

    public static ICommand createAddTagCommand(String tagName, String tagDescription) {
        return new AddTagCommand(tagName, tagDescription);
    }

    public static ICommand createDeleteTagCommand(String tagName) {
        return new DeleteTagCommand(tagName);
    }

    public static ICommand createRenameTagCommand(String oldName, String newName) {
        return new RenameTagCommand(oldName, newName);
    }

    // --- Server commands ---

    public static ICommand createAddServerCommand(OpenApiServersParent parent, String serverUrl,
                                                  String serverDescription) {
        return new AddServerCommand(parent, serverUrl, serverDescription);
    }

    public static ICommand createDeleteServerCommand(OpenApiServersParent parent, String serverUrl) {
        return new DeleteServerCommand(parent, serverUrl);
    }

    public static ICommand createAddServerCommand(AsyncApiDocument document, String serverName,
                                                  String serverUrl, String serverDescription) {
        return new AddServerCommand(document, serverName, serverUrl, serverDescription);
    }

    public static ICommand createDeleteServerCommand(AsyncApiDocument document, String serverName) {
        return new DeleteServerCommand(document, serverName);
    }

    public static ICommand createDeleteAllServersCommand(AsyncApiDocument document) {
        return new DeleteAllServersCommand(document);
    }

    // --- Path commands ---

    public static ICommand createCreatePathCommand(String pathName) {
        return new CreatePathCommand(pathName);
    }

    public static ICommand createDeletePathCommand(String pathName) {
        return new DeletePathCommand(pathName);
    }

    // --- Operation commands ---

    public static ICommand createCreateOperationCommand(OpenApiPathItem pathItem, String method) {
        return new CreateOperationCommand(pathItem, method);
    }

    public static ICommand createDeleteOperationCommand(OpenApiPathItem pathItem, String method) {
        return new DeleteOperationCommand(pathItem, method);
    }

    // --- Parameter commands ---

    public static ICommand createAddParameterCommand(OpenApiParametersParent parent, String parameterName,
                                                     String parameterLocation, String parameterDescription,
                                                     boolean parameterRequired, String parameterType) {
        return new AddParameterCommand(parent, parameterName, parameterLocation, parameterDescription,
                parameterRequired, parameterType);
    }

    public static ICommand createDeleteParameterCommand(OpenApiParametersParent parent, String parameterName,
                                                        String parameterLocation) {
        return new DeleteParameterCommand(parent, parameterName, parameterLocation);
    }

    // --- Request body commands ---

    public static ICommand createAddRequestBodyCommand(OpenApi30Operation operation) {
        return new AddRequestBodyCommand(operation);
    }

    public static ICommand createAddRequestBodyCommand(OpenApi31Operation operation) {
        return new AddRequestBodyCommand(operation);
    }

    public static ICommand createDeleteRequestBodyCommand(OpenApi30Operation operation) {
        return new DeleteRequestBodyCommand(operation);
    }

    public static ICommand createDeleteRequestBodyCommand(OpenApi31Operation operation) {
        return new DeleteRequestBodyCommand(operation);
    }

    // --- Response commands ---

    public static ICommand createAddResponseCommand(OpenApiOperation operation, String statusCode,
                                                    String description) {
        return new AddResponseCommand(operation, statusCode, description);
    }

    public static ICommand createDeleteResponseCommand(OpenApiOperation operation, String statusCode) {
        return new DeleteResponseCommand(operation, statusCode);
    }

    // --- Response header commands ---

    public static ICommand createAddResponseHeaderCommand(OpenApiHeadersParent response, String headerName,
                                                          String description, String schemaType,
                                                          String schemaRef) {
        return new AddResponseHeaderCommand(response, headerName, description, schemaType, schemaRef);
    }

    public static ICommand createDeleteResponseHeaderCommand(OpenApiHeadersParent response,
                                                             String headerName) {
        return new DeleteResponseHeaderCommand(response, headerName);
    }

    // --- Media type commands ---

    public static ICommand createAddMediaTypeCommand(Node parent, String mediaTypeName) {
        return new AddMediaTypeCommand(parent, mediaTypeName);
    }

    public static ICommand createChangeMediaTypeSchemaCommand(OpenApiMediaType mediaType, String schemaRef,
                                                              String schemaType) {
        return new ChangeMediaTypeSchemaCommand(mediaType, schemaRef, schemaType);
    }

    // --- Schema commands ---

    public static ICommand createCreateSchemaCommand(String schemaName) {
        return new CreateSchemaCommand(schemaName);
    }

    public static ICommand createDeleteSchemaCommand(String schemaName) {
        return new DeleteSchemaCommand(schemaName);
    }

    // --- Security scheme commands ---

    public static ICommand createAddSecuritySchemeCommand(String schemeName, ObjectNode schemeObj) {
        return new AddSecuritySchemeCommand(schemeName, schemeObj);
    }

    public static ICommand createUpdateSecuritySchemeCommand(String schemeName, ObjectNode newSchemeObj) {
        return new UpdateSecuritySchemeCommand(schemeName, newSchemeObj);
    }

    public static ICommand createDeleteSecuritySchemeCommand(String schemeName) {
        return new DeleteSecuritySchemeCommand(schemeName);
    }

    // --- Security requirement commands ---

    public static ICommand createAddOperationSecurityRequirementCommand2(
            SecurityRequirementsParent operation, SecurityRequirement requirement) {
        return new AddOperationSecurityRequirementCommand(operation, requirement);
    }

    public static ICommand createDeleteOperationSecurityRequirementCommand2(
            SecurityRequirementsParent operation, int index) {
        return new DeleteOperationSecurityRequirementCommand(operation, index);
    }

    public static ICommand createDeleteSecurityRequirementCommand(int index) {
        return new DeleteSecurityRequirementCommand(index);
    }

    // --- Node commands ---

    public static ICommand createUpdateNodeCommand(Node node, ObjectNode newContent) {
        return new UpdateNodeCommand(node, newContent);
    }

    public static ICommand createEnsureChildNodeCommand(NodePath parentPath, String childPropertyName) {
        return new EnsureChildNodeCommand(parentPath, childPropertyName);
    }

}
