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

export class JsonCompat {
    
    /****** Utility ******/
    

    public static stringify(json: any): string {
        return JSON.stringify(json);
    }
    
    public static parseJSON(jsonString: string): any {
        return JSON.parse(jsonString);
    }
        
    public static clone(json: any): any {
        return JSON.parse(JSON.stringify(json));
    }
    
    public static objectNode(): any {
        return {};
    }
    
    public static arrayNode(): any {
        return [];
    }
    
    public static isPropertyDefined(json: any, propertyName: string): boolean {
        return json && json[propertyName] != null && json[propertyName] != undefined;
    }
    
    public static parseObject(json: any): any {
        return json;
    }

    public static removeNullProperties(object: any): any {
        if (object instanceof Array) {
            for (let item of <Array<any>> object) {
                this.removeNullProperties(item);
            }
        } else if (object instanceof Object) {
            for (let key in <Object> object) {
                if (object[key] == null) {
                    delete object[key];
                } else {
                    this.removeNullProperties(object[key]);
                }
            }
        }
        return object;
    }
    
    public static isString(json: any): boolean {
        return typeof json === "string";
    }
    
    public static isNumber(json: any): boolean {
        return typeof json === "number";
    }

    public static isObject(json: any): boolean {
        return typeof json === "object";
    }

    public static isBoolean(json: any): boolean {
        return typeof json === "boolean";
    }
    
    public static toBoolean(json: any): boolean {
        return <boolean>json;
    }

    public static toString(json: any): boolean {
        return <string>json;
    }
    
    public static toNumber(json: any): number {
        return <number>json;
    }
    
    public static isArray(json: any): boolean {
        if (!json) {
            return false;
        }
        return Array.isArray(json);
    }

    public static toList(json: any): any[] {
        return <any[]> json;
    }
    
    /****** Getters ******/

    public static keys(json: any): string[] {
        return Object.keys(json);
    }
    
    public static getProperty(json: any, propertyName: string): any {
        let rval: any = json[propertyName];
        if (rval === undefined) {
            rval = null;
        }
        return rval;
    }
    public static consumeProperty(json: any, propertyName: string): any {
        let rval: any = JsonCompat.getProperty(json, propertyName);
        delete json[propertyName];
        return rval;
    }
    
    public static getPropertyObject(json: any, propertyName: string): any {
        return JsonCompat.getProperty(json, propertyName);
    }
    public static consumePropertyObject(json: any, propertyName: string): any {
        return JsonCompat.consumeProperty(json, propertyName);
    }

    public static getPropertyArray(json: any, propertyName: string): any[] {
        return JsonCompat.getProperty(json, propertyName);
    }
    public static consumePropertyArray(json: any, propertyName: string): any[] {
        return JsonCompat.consumeProperty(json, propertyName);
    }

    public static getPropertyString(json: any, propertyName: string): string {
        return JsonCompat.getProperty(json, propertyName);
    }
    public static consumePropertyString(json: any, propertyName: string): string {
        return JsonCompat.consumeProperty(json, propertyName);
    }

    public static getPropertyBoolean(json: any, propertyName: string): boolean {
        return JsonCompat.getProperty(json, propertyName);
    }
    public static consumePropertyBoolean(json: any, propertyName: string): boolean {
        return JsonCompat.consumeProperty(json, propertyName);
    }

    public static getPropertyNumber(json: any, propertyName: string): number {
        return JsonCompat.getProperty(json, propertyName);
    }
    public static consumePropertyNumber(json: any, propertyName: string): number {
        return JsonCompat.consumeProperty(json, propertyName);
    }

    public static getPropertyStringArray(json: any, propertyName: string): string[] {
        return JsonCompat.getProperty(json, propertyName);
    }
    public static consumePropertyStringArray(json: any, propertyName: string): string[] {
        return JsonCompat.consumeProperty(json, propertyName);
    }
    
    /****** Setters ******/
    
    public static setPropertyNull(json: any, propertyName: string): void {
        json[propertyName] = null;
    }

    public static setProperty(json: any, propertyName: string, propertyValue: any): void {
        json[propertyName] = propertyValue;
    }
    
    public static setPropertyString(json: any, propertyName: string, propertyValue: string): void {
        JsonCompat.setProperty(json, propertyName, propertyValue);
    }

    public static setPropertyBoolean(json: any, propertyName: string, propertyValue: boolean): void {
        JsonCompat.setProperty(json, propertyName, propertyValue);
    }

    public static setPropertyNumber(json: any, propertyName: string, propertyValue: number): void {
        JsonCompat.setProperty(json, propertyName, propertyValue);
    }

    public static setPropertyStringArray(json: any, propertyName: string, propertyValue: string[]): void {
        JsonCompat.setProperty(json, propertyName, propertyValue);
    }

    public static appendToArrayProperty(json: any, arrayPropertyName: string, propertyValue: any): void {
        if (!JsonCompat.isPropertyDefined(json, arrayPropertyName)) {
            JsonCompat.setProperty(json, arrayPropertyName, [ propertyValue ]);
        } else {
            let array: any[] = json[arrayPropertyName];
            array.push(propertyValue);
        }
    }

}
