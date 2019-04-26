
export class JsonCompat {
    
    /****** Utility ******/
    
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