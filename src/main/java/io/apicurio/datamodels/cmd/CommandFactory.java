package io.apicurio.datamodels.cmd;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.apicurio.datamodels.cmd.commands.AddChannelItemCommand;
import io.apicurio.datamodels.cmd.commands.ChangeContactCommand;
import io.apicurio.datamodels.cmd.commands.ChangeDescriptionCommand;
import io.apicurio.datamodels.cmd.commands.ChangeLicenseCommand;
import io.apicurio.datamodels.cmd.commands.ChangePropertyCommand;
import io.apicurio.datamodels.cmd.commands.ChangeTitleCommand;
import io.apicurio.datamodels.cmd.commands.ChangeVersionCommand;
import io.apicurio.datamodels.cmd.commands.DeleteAllChildSchemasCommand;
import io.apicurio.datamodels.cmd.commands.DeleteAllExamplesCommand;
import io.apicurio.datamodels.cmd.commands.DeleteAllHeadersCommand;
import io.apicurio.datamodels.cmd.commands.DeleteAllOperationsCommand;
import io.apicurio.datamodels.cmd.commands.DeleteContactCommand;
import io.apicurio.datamodels.cmd.commands.DeleteExtensionCommand;
import io.apicurio.datamodels.cmd.commands.DeleteLicenseCommand;
import io.apicurio.datamodels.cmd.commands.DeleteMediaTypeCommand;
import io.apicurio.datamodels.models.Extensible;
import io.apicurio.datamodels.models.Info;
import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.Schema;
import io.apicurio.datamodels.models.openapi.OpenApiHeader;
import io.apicurio.datamodels.models.openapi.OpenApiMediaType;
import io.apicurio.datamodels.models.openapi.OpenApiParameter;
import io.apicurio.datamodels.models.openapi.OpenApiPathItem;
import io.apicurio.datamodels.models.openapi.OpenApiResponse;
import io.apicurio.datamodels.util.CommandUtil;

public class CommandFactory {

    public static ICommand create(String cmdType) {
        return CommandUtil.create(cmdType);
    }

    public static ICommand unmarshall(ObjectNode from) {
        return CommandUtil.unmarshall(from);
    }

    public static <T> ICommand createChangePropertyCommand(Node node, String property, T newValue) {
        return new ChangePropertyCommand<T>(node, property, newValue);
    }

    public static ICommand createAddChannelItemCommand(String channelItemName, ObjectNode from) {
        return new AddChannelItemCommand(channelItemName, from);
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
        return new DeleteAllExamplesCommand(mediaType);
    }

    public static ICommand createDeleteAllParameterExamplesCommand(OpenApiParameter parameter) {
        return new DeleteAllExamplesCommand(parameter);
    }

    public static ICommand createDeleteAllHeaderExamplesCommand(OpenApiHeader header) {
        return new DeleteAllExamplesCommand(header);
    }

    public static ICommand createDeleteAllHeadersCommand(OpenApiResponse header) {
        return new DeleteAllHeadersCommand(header);
    }

    public static ICommand createDeleteAllPathItemOperationsCommand(OpenApiPathItem pathItem) {
        return new DeleteAllOperationsCommand(pathItem);
    }

}
