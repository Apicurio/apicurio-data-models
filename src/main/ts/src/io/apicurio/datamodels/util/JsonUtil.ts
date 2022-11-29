
export class JsonUtil {

    public static keys(json: object): string[] {
        if (!json) {
            return [];
        }
        return Object.keys(json);
    }    

    public static matchingKeys(regex: string, json: object): string[] {
        const re: RegExp = new RegExp(regex);
        return JsonUtil.keys(json).filter(key => re.test(key));
    }

    public static getProperty(json: object, propertyName: string): any {
        let rval: any = json[propertyName];
        if (rval === undefined) {
            rval = null;
        }
        return rval;
    }
    public static setProperty(json: object, propertyName: string, propertyValue: any): void {
        json[propertyName] = propertyValue;
    }
    public static consumeProperty(json: any, propertyName: string): any {
        let rval: any = JsonUtil.getProperty(json, propertyName);
        if (rval) {
            delete json[propertyName];
        }
        return rval;
    }

    /* Get/Consume a JSON (Object) property. */
    public static getObjectProperty(json: object, propertyName: string): object {
        return JsonUtil.getProperty(json, propertyName);
    }
    public static consumeObjectProperty(json: object, propertyName: string): object {
        return JsonUtil.consumeProperty(json, propertyName);
    }

    /* Get/Consume a JSON (Any) property. */
    public static getAnyProperty(json: object, propertyName: string): any {
        return JsonUtil.getProperty(json, propertyName);
    }
    public static consumeAnyProperty(json: object, propertyName: string): any {
        return JsonUtil.consumeProperty(json, propertyName);
    }
    
    /* Get/consume an array of anys property. */
    public static getAnyArrayProperty(json: object, propertyName: string): any[] {
        return JsonUtil.getProperty(json, propertyName);
    }
    public static consumeAnyArrayProperty(json: object, propertyName: string): any[] {
        return JsonUtil.consumeProperty(json, propertyName);
    }

    /* Get/consume an array of objects property. */
    public static getObjectArrayProperty(json: object, propertyName: string): object[] {
        return JsonUtil.getProperty(json, propertyName);
    }
    public static consumeObjectArrayProperty(json: object, propertyName: string): object[] {
        return JsonUtil.consumeProperty(json, propertyName);
    }

    /* Get/consume an array of strings property. */
    public static getStringArrayProperty(json: object, propertyName: string): string[] {
        return JsonUtil.getProperty(json, propertyName);
    }
    public static consumeStringArrayProperty(json: object, propertyName: string): string[] {
        return JsonUtil.consumeProperty(json, propertyName);
    }

    /* Get/consume an array of integers property. */
    public static getIntegerArrayProperty(json: object, propertyName: string): number[] {
        return JsonUtil.getProperty(json, propertyName);
    }
    public static consumeIntegerArrayProperty(json: object, propertyName: string): number[] {
        return JsonUtil.consumeProperty(json, propertyName);
    }

    /* Get/consume an array of numbers property. */
    public static getNumberArrayProperty(json: object, propertyName: string): number[] {
        return JsonUtil.getProperty(json, propertyName);
    }
    public static consumeNumberArrayProperty(json: object, propertyName: string): number[] {
        return JsonUtil.consumeProperty(json, propertyName);
    }
    
    /* Get/consume an array of booleans property. */
    public static getBooleanArrayProperty(json: object, propertyName: string): boolean[] {
        return JsonUtil.getProperty(json, propertyName);
    }
    public static consumeBooleanArrayProperty(json: object, propertyName: string): boolean[] {
        return JsonUtil.consumeProperty(json, propertyName);
    }
    
    /* Get/Consume a string property. */
    public static getStringProperty(json: object, propertyName: string): string {
            return JsonUtil.getProperty(json, propertyName);
    }
    public static consumeStringProperty(json: object, propertyName: string): string {
        return JsonUtil.consumeProperty(json, propertyName);
    }

    /* Get/Consume an Integer property. */
    public static getIntegerProperty(json: object, propertyName: string): number {
        return JsonUtil.getProperty(json, propertyName);
    }
    public static consumeIntegerProperty(json: object, propertyName: string): number {
        return JsonUtil.consumeProperty(json, propertyName);
    }

    /* Get/Consume a Number property. */
    public static getNumberProperty(json: object, propertyName: string): number {
        return JsonUtil.getProperty(json, propertyName);
    }
    public static consumeNumberProperty(json: object, propertyName: string): number {
        return JsonUtil.consumeProperty(json, propertyName);
    }

    /* Get/Consume a Boolean property. */
    public static getBooleanProperty(json: object, propertyName: string): boolean {
        return JsonUtil.getProperty(json, propertyName);
    }
    public static consumeBooleanProperty(json: object, propertyName: string): boolean {
        return JsonUtil.consumeProperty(json, propertyName);
    }

    /* Get/consume a map of anys property. */
    public static getAnyMapProperty(json: object, propertyName: string): any {
        return JsonUtil.getProperty(json, propertyName);
    }
    public static consumeAnyMapProperty(json: object, propertyName: string): any {
        return JsonUtil.consumeProperty(json, propertyName);
    }

    /* Get/consume a map of objects property. */
    public static getObjectMapProperty(json: object, propertyName: string): any {
        return JsonUtil.getProperty(json, propertyName);
    }
    public static consumeObjectMapProperty(json: object, propertyName: string): any {
        return JsonUtil.consumeProperty(json, propertyName);
    }


    /* Get/consume a map of strings property. */
    public static getStringMapProperty(json: object, propertyName: string): any {
        return JsonUtil.getProperty(json, propertyName);
    }
    public static consumeStringMapProperty(json: object, propertyName: string): any {
        return JsonUtil.consumeProperty(json, propertyName);
    }

    /* Get/consume a map of integers property. */
    public static getIntegerMapProperty(json: object, propertyName: string): any {
        return JsonUtil.getProperty(json, propertyName);
    }
    public static consumeIntegerMapProperty(json: object, propertyName: string): any {
        return JsonUtil.consumeProperty(json, propertyName);
    }

    /* Get/consume a map of numbers property. */
    public static getNumberMapProperty(json: object, propertyName: string): any {
        return JsonUtil.getProperty(json, propertyName);
    }
    public static consumeNumberMapProperty(json: object, propertyName: string): any {
        return JsonUtil.consumeProperty(json, propertyName);
    }

    /* Get/consume a map of numbers property. */
    public static getBooleanMapProperty(json: object, propertyName: string): any {
        return JsonUtil.getProperty(json, propertyName);
    }
    public static consumeBooleanMapProperty(json: object, propertyName: string): any {
        return JsonUtil.consumeProperty(json, propertyName);
    }


    /* Set a JSON (Object) property. */
    public static setObjectProperty(json: object, propertyName: string, value: object): void {
        JsonUtil.setProperty(json, propertyName, value);
    }
    /* Set a JSON (Any) property. */
    public static setAnyProperty(json: object, propertyName: string, value: any): void {
        JsonUtil.setProperty(json, propertyName, value);
    }
    /* Set an array of anys property. */
    public static setAnyArrayProperty(json: object, propertyName: string, value: any[]): void {
        JsonUtil.setProperty(json, propertyName, value);
    }
    /* Set an array of objects property. */
    public static setObjectArrayProperty(json: object, propertyName: string, value: object[]): void {
        JsonUtil.setProperty(json, propertyName, value);
    }
    /* Set an array of strings property. */
    public static setStringArrayProperty(json: object, propertyName: string, value: string[]): void {
        JsonUtil.setProperty(json, propertyName, value);
    }
    /* Set an array of integers property. */
    public static setIntegerArrayProperty(json: object, propertyName: string, value: number[]): void {
        JsonUtil.setProperty(json, propertyName, value);
    }
    /* Set an array of numbers property. */
    public static setNumberArrayProperty(json: object, propertyName: string, value: number[]): void {
        JsonUtil.setProperty(json, propertyName, value);
    }
    /* Set an array of booleans property. */
    public static setBooleanArrayProperty(json: object, propertyName: string, value: boolean[]): void {
        JsonUtil.setProperty(json, propertyName, value);
    }
    /* Set a string property. */
    public static setStringProperty(json: object, propertyName: string, value: string): void {
        JsonUtil.setProperty(json, propertyName, value);
    }
    /* Set an Integer property. */
    public static setIntegerProperty(json: object, propertyName: string, value: number): void {
        JsonUtil.setProperty(json, propertyName, value);
    }
    /* Set a Number property. */
    public static setNumberProperty(json: object, propertyName: string, value: number): void {
        JsonUtil.setProperty(json, propertyName, value);
    }
    /* Set a Boolean property. */
    public static setBooleanProperty(json: object, propertyName: string, value: boolean): void {
        JsonUtil.setProperty(json, propertyName, value);
    }
    /* Set a map of anys property. */
    public static setAnyMapProperty(json: object, propertyName: string, value: any): void {
        JsonUtil.setProperty(json, propertyName, value);
    }
    /* Set a map of objects property. */
    public static setObjectMapProperty(json: object, propertyName: string, value: any): void {
        JsonUtil.setProperty(json, propertyName, value);
    }
    /* Set a map of strings property. */
    public static setStringMapProperty(json: object, propertyName: string, value: any): void {
        JsonUtil.setProperty(json, propertyName, value);
    }
    /* Set a map of integers property. */
    public static setIntegerMapProperty(json: object, propertyName: string, value: any): void {
        JsonUtil.setProperty(json, propertyName, value);
    }
    /* Set a map of numbers property. */
    public static setNumberMapProperty(json: object, propertyName: string, value: any): void {
        JsonUtil.setProperty(json, propertyName, value);
    }
    /* Set a map of numbers property. */
    public static setBooleanMapProperty(json: object, propertyName: string, value: any): void {
        JsonUtil.setProperty(json, propertyName, value);
    }

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

    public static addToArray(array: Array<any>, value: any): void {
        array.push(value);
    }

    public static isPropertyDefined(json: any, propertyName: string): boolean {
        return json && json[propertyName] != null && json[propertyName] != undefined;
    }

}
