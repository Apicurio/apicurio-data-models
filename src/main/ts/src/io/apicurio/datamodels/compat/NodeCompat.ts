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

    public static property(node: any, propertyName: string): any {
        let rval: any = node[propertyName];
        if (rval === undefined) {
            rval = null;
        }
        return rval;
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

}