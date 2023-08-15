import {DefinitionDetectionVisitor} from "../visitors/DefinitionDetectionVisitor";
import {Node} from "../models/Node";

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

    public static getNodeProperty(node: Node, propertyName: string): any {
        return NodeUtil.getProperty(node, propertyName);
    }

    public static setProperty(node: any, propertyName: string, newValue: any): void {
        node[propertyName] = newValue;
    }

    public static isNode(object: any): boolean {
        return object !== null && object !== undefined && typeof object === "object" && object.hasOwnProperty("_parent");
    }

    public static isUnion(object: any): boolean {
        return object !== null && object !== undefined 
                && typeof object === "object" 
                && object.constructor && object.constructor.hasOwnProperty("__interfaces")
                && object.constructor["__interfaces"].includes("io.apicurio.datamodels.models.union.Union");
    }

    public static isDefinition(node: Node): boolean {
        const detector: DefinitionDetectionVisitor = new DefinitionDetectionVisitor();
        if (node.parent() != null) {
            node.parent().accept(detector);
        }
        return detector.isDefinitionParentDetected();
    }

    public static isList(object: any): boolean {
        return object !== null && object !== undefined && Array.isArray(object);
    }

    public static isMap(object: any): boolean {
        return object !== null && object !== undefined && typeof object === "object" && !object["_parent"];
    }

    public static getMapItem(map: object, key: string): any {
        const value: any = map[key];
        if (value) {
            return value;
        }
        return null;
    }
    
    public static getMapKeys(map: object): string[] {
        if (map === null || map === undefined) {
            return [];
        }
        return Object.getOwnPropertyNames(map);
    }

    public static getMapValues<T>(map: object): T[] {
        if (map === null || map === undefined) {
            return [];
        }
        return Object.values(map);
    }

    public static toInteger(value: string): number {
        return parseInt(value);
    }

    public static isNullOrUndefined(value: any): boolean {
        return value === null || value === undefined;
    }
    
    public static isDefined(value: any): boolean {
        return value !== null && value !== undefined;
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

    public static equals(value1: string, value2: string): boolean {
        return value1 == value2;
    }

    public static join(delim: string, values: string[]): string {
        return values.join(delim);
    }

    public static joinArray(delim: string, values: string[]): string {
        return NodeUtil.join(delim, values);
    }

}