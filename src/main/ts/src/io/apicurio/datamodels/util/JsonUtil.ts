
export class JsonUtil {

    public static getProperty(json: any, propertyName: string): any {
        let rval: any = json[propertyName];
        if (rval === undefined) {
            rval = null;
        }
        return rval;
    }
    public static consumeProperty(json: any, propertyName: string): any {
        const rval: any = JsonUtil.getProperty(json, propertyName);
        delete json[propertyName];
        return rval;
    }

    public static getStringProperty(json: object, propertyName: string): string {
        return JsonUtil.getProperty(json, propertyName);
    }
    public static consumeStringProperty(json: object, propertyName: string): string {
        return JsonUtil.consumeProperty(json, propertyName);
    }

}
