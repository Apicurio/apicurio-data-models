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

export class MarshallCompat {

    public static marshallCommand(command: ICommand): any {
                // _mediaTypePath
        // _responsePath
        // _responsesPath
        // _parentPath
        // _schemaPath
        // _parameterPath
        // _operationPath
        // _propPath
        // _propertyPath
        // _nodePath
        
        // _newType - simplifiedtype
        
        // commands - array of commands  (aggregate command)
        
        // references - list of node paths (rename schema def)
        return null;
    }

    public static unmarshallCommand(from: any): ICommand {
        return null;
    }
}
