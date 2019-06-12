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

export class NodeCompat {

    public static getProperty(node: any, propertyName: string): any {
        if (propertyName == "default" || propertyName == "enum") {
            propertyName += "_";
        }
        let rval: any = node[propertyName];
        if (rval === undefined) {
            rval = null;
        }
        return rval;
    }
    
    public static setProperty(node: any, propertyName: string, newValue: any): void {
        node[propertyName] = newValue;
    }

    public static indexOf(child: any, parent: any, propertyName: string): number {
        let array: any[] = parent[propertyName];
        if (array && Array.isArray(array)) {
            for (let idx = 0; idx < array.length; idx++) {
                let node: any = array[idx];
                if (node === child) {
                    return idx;
                }
            }
        }
        return -1;
    }
    
    public static equals(value1: string, value2: string): boolean {
        return value1 == value2;
    }

    public static join(delim: string, values: string[]): string {
        return values.join(delim);
    }

    public static joinArray(delim: string, values: string[]): string {
        return NodeCompat.join(delim, values);
    }

    public static isNode(object: any): boolean {
        return object !== null && object !== undefined && !Array.isArray(object) && object["_ownerDocument"];
    }

    public static isList(object: any): boolean {
        return object !== null && object !== undefined && Array.isArray(object);
    }
    
    public static asArray(list: string[]): string[] {
        return list;
    }

    public static asList(...items: string[]): string[] {
        return items;
    }

    public static copyList(list: string[]): string[] {
        return list.slice(0);
    }

    public static isNullOrUndefined(value: any): boolean {
        return value === null || value === undefined;
    }

}