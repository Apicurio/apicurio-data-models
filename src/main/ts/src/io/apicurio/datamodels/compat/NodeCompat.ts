
export class NodeCompat {

    public static property(node: any, propertyName: string): any {
        let rval: any = node[propertyName];
        if (rval === undefined) {
            rval = null;
        }
        return rval;
    }
    
}