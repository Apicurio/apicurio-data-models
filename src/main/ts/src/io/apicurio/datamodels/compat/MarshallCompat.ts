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
import { CommandFactory } from '../cmd/util/CommandFactory';
import { NodePath } from '../core/models/NodePath';

const pathKeys: string[] = [
    "_mediaTypePath", "_responsePath", "_responsesPath", "_parentPath", "_schemaPath", "_parameterPath", "_operationPath",
    "_propPath", "_propertyPath", "_nodePath"
];
const pathListKeys: string[] = [ "_references" ];
const typeKeys: string[] = [ "_newType" ];
const cmdListKeys: string[] = [ "_commands" ];

export class MarshallCompat {

    public static marshallCommand(command: ICommand): any {
        let to: any = {};
        Object.keys(command).forEach(key => {
            let value: any = command[key];
            if (value) {
                if (pathKeys.indexOf(key) != -1) {
                    value = (<NodePath>value).toString();
                } else if (pathListKeys.indexOf(key) != -1) {
                    // TOOD marshal list of node paths
                } else if (typeKeys.indexOf(key) != -1) {
                    // TODO marshal types
                } else if (cmdListKeys.indexOf(key) != -1) {
                    // TODO marshal command list
                }
            }
            to[key] = value;
        });
        return to;
    }

    public static unmarshallCommand(from: any): ICommand {
        let type: string = from["__type"];
        let command: ICommand = CommandFactory.create(type);
        Object.keys(from).forEach(key => {
            let value: any = from[key];
            if (value) {
                if (pathKeys.indexOf(key) != -1) {
                    value = new NodePath(value);
                } else if (pathListKeys.indexOf(key) != -1) {
                    // TOOD unmarshal list of node paths
                } else if (typeKeys.indexOf(key) != -1) {
                    // TODO unmarshal types
                } else if (cmdListKeys.indexOf(key) != -1) {
                    // TODO unmarshal command list
                }
            }
            command[key] = value;
        });
        return command;
    }
}
