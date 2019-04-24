
export class LoggerCompat {

    public static info(message: string, ...args: any[]): void {
        console.info(message, args);
    }
    
    public static warn(message: string, ...args: any[]): void {
        console.warn(message, args);
    }

}