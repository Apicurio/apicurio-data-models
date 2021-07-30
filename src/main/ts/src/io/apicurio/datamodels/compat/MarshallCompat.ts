/**
 * @license
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

import { ICommand } from '../cmd/ICommand';
import { CommandFactory } from '../cmd/commands/CommandFactory';
import { NodePath } from '../core/models/NodePath';
import { SimplifiedType } from '../cmd/models/SimplifiedType';

const pathKeys: string[] = [
    "_mediaTypePath", "_responsePath", "_responsesPath", "_parentPath", "_schemaPath", "_parameterPath", "_operationPath",
    "_propPath", "_propertyPath", "_paramPath", "_nodePath", "_headerPath"
];
const pathListKeys: string[] = [ "_references" ];
const typeKeys: string[] = [ "_newType" ];
const cmdListKeys: string[] = [ "_commands" ];

export class MarshallCompat {

    public static marshallCommand(command: ICommand): any {
        let to: any = {};
        to["__type"] = command.type();
        Object.keys(command).filter(key => key != "__type").forEach(key => {
            let value: any = command[key];
            if (value) {
                if (pathKeys.indexOf(key) != -1) {
                    value = (<NodePath>value).toString();
                } else if (pathListKeys.indexOf(key) != -1 && value) {
                    let newValue: string[] = [];
                    (<NodePath[]>value).forEach(nodePath => {
                        newValue.push(nodePath.toString());
                    });
                    value = newValue;
                } else if (typeKeys.indexOf(key) != -1 && value) {
                    value = MarshallCompat.marshallSimplifiedType(<SimplifiedType>value);
                } else if (cmdListKeys.indexOf(key) != -1 && value) {
                    let newValue: any[] = [];
                    (<ICommand[]>value).forEach(scmd => {
                        newValue.push(MarshallCompat.marshallCommand(scmd));
                    });
                    value = newValue;
                }
            }
            to[key] = value;
        });
        return to;
    }

    public static unmarshallCommand(from: any): ICommand {
        let type: string = from["__type"];
        if (!type) {
            throw Error("Missing __type from source data.");
        }
        let command: ICommand = CommandFactory.create(type);
        Object.keys(from).filter(key => key != "__type").forEach(key => {
            let value: any = from[key];
            if (value) {
                if (pathKeys.indexOf(key) != -1) {
                    value = new NodePath(value);
                } else if (pathListKeys.indexOf(key) != -1 && value) {
                    let newValue: NodePath[] = [];
                    (<string[]>value).forEach(np => {
                        newValue.push(new NodePath(np));
                    });
                    value = newValue;
                } else if (typeKeys.indexOf(key) != -1 && value) {
                    value = MarshallCompat.unmarshallSimplifiedType(value);
                } else if (cmdListKeys.indexOf(key) != -1 && value) {
                    let newValue: ICommand[] = [];
                    (<any[]>value).forEach(mcmd => {
                        newValue.push(MarshallCompat.unmarshallCommand(mcmd));
                    });
                    value = newValue;
                }
            }
            command[key] = value;
        });
        return command;
    }

    /**
     * Marshalls the given simple type into a JS object.
     * @param {SimplifiedType} sType
     * @return {any}
     */
    public static marshallSimplifiedType(sType: SimplifiedType): any {
        if (sType == null || sType == undefined) {
            return null;
        }
        let obj: any = {
            type: sType.type,
            enum: sType.enum_,
            of: MarshallCompat.marshallSimplifiedType(sType.of),
            as: sType.as,
            required: sType["required"]
        };
        return obj;
    }

    /**
     * Unmarshalls a simple type back into a JS object.
     * @param object
     * @return {SimplifiedType}
     */
    public static unmarshallSimplifiedType(object: any): SimplifiedType {
        if (object == undefined || object == null) {
            return null;
        }
        let type: SimplifiedType = new SimplifiedType();
        type.type = object.type;
        type.enum_ = object.enum;
        type.of = MarshallCompat.unmarshallSimplifiedType(object.of);
        type.as = object.as;
        type["required"] = object["required"];
        return type;
    }

}
