
export class JsonCompat {
    
    public static clone(json: any): any {
        return JSON.parse(JSON.stringify(json));
    }
    
    public static objectNode(): any {
        return {};
    }
    public static arrayNode(): any {
        return [];
    }
    
    public static parseObject(json: any): any {
        return json;
    }

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

    public static getPropertyString(json: any, propertyName: string): string {
        return JsonCompat.getProperty(json, propertyName);
    }
    public static consumePropertyString(json: any, propertyName: string): string {
        return JsonCompat.consumeProperty(json, propertyName);
    }

    public static setProperty(json: any, propertyName: string, propertyValue: any): void {
        json[propertyName] = propertyValue;
    }
    
    public static setPropertyString(json: any, propertyName: string, propertyValue: string): void {
        if (propertyValue != null) {
            JsonCompat.setProperty(json, propertyName, propertyValue);
        }
    }

    
}