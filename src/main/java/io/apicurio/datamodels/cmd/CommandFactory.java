package io.apicurio.datamodels.cmd;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.apicurio.datamodels.cmd.commands.AddChannelItemCommand;
import io.apicurio.datamodels.cmd.commands.ChangeContactCommand;
import io.apicurio.datamodels.cmd.commands.ChangeDescriptionCommand;
import io.apicurio.datamodels.cmd.commands.ChangeLicenseCommand;
import io.apicurio.datamodels.cmd.commands.ChangePropertyCommand;
import io.apicurio.datamodels.cmd.commands.ChangeTitleCommand;
import io.apicurio.datamodels.cmd.commands.ChangeVersionCommand;
import io.apicurio.datamodels.cmd.commands.DeleteContactCommand;
import io.apicurio.datamodels.cmd.commands.DeleteLicenseCommand;
import io.apicurio.datamodels.models.Info;
import io.apicurio.datamodels.models.Node;
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

    public static final ICommand createChangeContactCommand(String name, String email, String url) {
        return new ChangeContactCommand(name, email, url);
    }

    public static final ICommand createChangeLicenseCommand(String name, String url) {
        return new ChangeLicenseCommand(name, url);
    }

    public static final ICommand createDeleteLicenseCommand(Info info) {
        return new DeleteLicenseCommand(info);
    }

    public static final ICommand createDeleteContactCommand(Info info) {
        return new DeleteContactCommand(info);
    }

}
