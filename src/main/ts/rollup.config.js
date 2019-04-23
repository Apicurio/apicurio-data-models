export default {
    input: "dist/index.js",
    onwarn (warning, warn) {
    	if (warning.code === "THIS_IS_UNDEFINED") {
    		return;
    	}
    	warn(warning);
    },
    output: {
    	file: "dist/bundles/asyncapi.umd.js",
    	format: "umd",
    	name: "asyncapi"
    }
};
