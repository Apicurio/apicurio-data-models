import {ICommand} from '../cmd/ICommand';
import {NodePath} from '../paths/NodePath';
import {NodePathUtil} from "../paths/NodePathUtil";

import {AddChannelItemCommand} from "../cmd/commands/AddChannelItemCommand";

import {ChangeDescriptionCommand} from "../cmd/commands/ChangeDescriptionCommand";
import {ChangePropertyCommand} from "../cmd/commands/ChangePropertyCommand";
import {ChangeTitleCommand} from "../cmd/commands/ChangeTitleCommand";
import {ChangeVersionCommand} from "../cmd/commands/ChangeVersionCommand";
import {ChangeContactCommand} from "../cmd/commands/ChangeContactCommand";
import {ChangeLicenseCommand} from "../cmd/commands/ChangeLicenseCommand";

import {DeleteAllChildSchemasCommand} from "../cmd/commands/DeleteAllChildSchemasCommand";
import {DeleteAllExamplesCommand} from "../cmd/commands/DeleteAllExamplesCommand";
import {DeleteAllExtensionsCommand} from "../cmd/commands/DeleteAllExtensionsCommand";
import {DeleteAllHeadersCommand} from "../cmd/commands/DeleteAllHeadersCommand";
import {DeleteAllOperationsCommand} from "../cmd/commands/DeleteAllOperationsCommand";
import {DeleteAllParametersCommand} from "../cmd/commands/DeleteAllParametersCommand";
import {DeleteAllPropertiesCommand} from "../cmd/commands/DeleteAllPropertiesCommand";
import {DeleteAllResponsesCommand} from "../cmd/commands/DeleteAllResponsesCommand";
import {DeleteAllSecurityRequirementsCommand} from "../cmd/commands/DeleteAllSecurityRequirementsCommand";
import {DeleteContactCommand} from "../cmd/commands/DeleteContactCommand";
import {DeleteExtensionCommand} from "../cmd/commands/DeleteExtensionCommand";
import {DeleteLicenseCommand} from "../cmd/commands/DeleteLicenseCommand";
import {DeleteMediaTypeCommand} from "../cmd/commands/DeleteMediaTypeCommand";

import {SetPropertyCommand} from "../cmd/commands/SetPropertyCommand";


const pathKeys: string[] = [
    "_mediaTypePath", "_responsePath", "_responsesPath", "_parentPath", "_schemaPath", "_parameterPath", "_operationPath",
    "_propPath", "_propertyPath", "_paramPath", "_nodePath", "_headerPath", "_messagePath"
];
const pathListKeys: string[] = [ "_references" ];
const cmdListKeys: string[] = [ "_commands" ];

type Supplier = () => ICommand;

const commandSuppliers: { [key: string]: Supplier } = {
    "AddChannelItemCommand": () => { return new AddChannelItemCommand(); },

    "ChangeDescriptionCommand": () => { return new ChangeDescriptionCommand(); },
    "ChangePropertyCommand": () => { return new ChangePropertyCommand(); },
    "ChangeTitleCommand": () => { return new ChangeTitleCommand(); },
    "ChangeVersionCommand": () => { return new ChangeVersionCommand(); },
    "ChangeContactCommand": () => { return new ChangeContactCommand(); },
    "ChangeLicenseCommand": () => { return new ChangeLicenseCommand(); },

    "DeleteAllChildSchemasCommand": () => { return new DeleteAllChildSchemasCommand(); },
    "DeleteAllExamplesCommand": () => { return new DeleteAllExamplesCommand(); },
    "DeleteAllExtensionsCommand": () => { return new DeleteAllExtensionsCommand(); },
    "DeleteAllHeadersCommand": () => { return new DeleteAllHeadersCommand(); },
    "DeleteAllOperationsCommand": () => { return new DeleteAllOperationsCommand(); },
    "DeleteAllParametersCommand": () => { return new DeleteAllParametersCommand(); },
    "DeleteAllPropertiesCommand": () => { return new DeleteAllPropertiesCommand(); },
    "DeleteAllResponsesCommand": () => { return new DeleteAllResponsesCommand(); },
    "DeleteAllSecurityRequirementsCommand": () => { return new DeleteAllSecurityRequirementsCommand(); },
    "DeleteContactCommand": () => { return new DeleteContactCommand(); },
    "DeleteExtensionCommand": () => { return new DeleteExtensionCommand(); },
    "DeleteLicenseCommand": () => { return new DeleteLicenseCommand(); },
    "DeleteMediaTypeCommand": () => { return new DeleteMediaTypeCommand(); },

    "SetPropertyCommand": () => { return new SetPropertyCommand(); },
};

export class CommandUtil {

    public static create(cmdType: string): ICommand {
        const supplier: Supplier = commandSuppliers[cmdType];
        if (supplier === undefined) {
            throw new Error("Command cannot be created, missing supplier (add to CommandUtil): " + cmdType);
        }
        return supplier();
    }

    public static unmarshall(from: object): ICommand {
        let type: string = from["__type"];
        if (!type) {
            throw Error("Missing __type from source data.");
        }
        let command: ICommand = CommandUtil.create(type);
        Object.keys(from).filter(key => key != "__type").forEach(key => {
            let value: any = from[key];
            if (value) {
                if (pathKeys.indexOf(key) != -1) {
                    value = NodePathUtil.parseNodePath(value);
                } else if (pathListKeys.indexOf(key) != -1 && value) {
                    let newValue: NodePath[] = [];
                    (<string[]>value).forEach(np => {
                        newValue.push(NodePathUtil.parseNodePath(np));
                    });
                    value = newValue;
                    // } else if (typeKeys.indexOf(key) != -1 && value) {
                    //     value = MarshallCompat.unmarshallSimplifiedType(value);
                } else if (cmdListKeys.indexOf(key) != -1 && value) {
                    let newValue: ICommand[] = [];
                    (<any[]>value).forEach(mcmd => {
                        newValue.push(CommandUtil.unmarshall(mcmd));
                    });
                    value = newValue;
                }
            }
            command[key] = value;
        });
        return command;
    }
}
