export default {
    input: "dist/index.js",
    onwarn (warning, warn) {
    	if (warning.code === "THIS_IS_UNDEFINED" || warning.code === "CIRCULAR_DEPENDENCY") {
    		return;
    	}
    	warn(warning);
    },
    output: {
    	file: "dist/bundles/apicurio-data-models.umd.js",
    	format: "umd",
    	name: "apicurio-data-models"
    }
};
