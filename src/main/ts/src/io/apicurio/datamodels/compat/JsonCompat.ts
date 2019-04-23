
export class JsonCompat {
    
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

    public static property(json: any, propertyName: string): any {
        if (json[propertyName]) {
            return json[propertyName];
        }
        return null;
    }
    
    public static propertyString(json: any, propertyName: string): string {
        let value: any = JsonCompat.property(json, propertyName);
        if (value == null) {
            return null;
        } else {
            return value;
        }
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