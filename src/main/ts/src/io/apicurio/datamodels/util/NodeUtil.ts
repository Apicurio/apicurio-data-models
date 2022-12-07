
export class NodeUtil {

    public static getProperty(node: any, propertyName: string): any {
        if (propertyName == "default" || propertyName == "enum" || propertyName == "if" || propertyName == "else" || propertyName == "const") {
            propertyName = "_" + propertyName;
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

    public static isNode(object: any): boolean {
        return object !== null && object !== undefined && typeof object === "object" && object["_root"];
    }

    public static isList(object: any): boolean {
        return object !== null && object !== undefined && Array.isArray(object);
    }

    public static isMap(object: any): boolean {
        return object !== null && object !== undefined && typeof object === "object" && !object["_root"];
    }

    public static getMapItem(map: any, key: string): any {
        const value: any = map[key];
        if (value) {
            return value;
        }
        return null;
    }

    public static toInteger(value: string): number {
        return parseInt(value);
    }

}