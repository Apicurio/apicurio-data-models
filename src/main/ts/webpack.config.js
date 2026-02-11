const path = require("path");

module.exports = {
    mode: "production",
    entry: "./dist/index.js",
    output: {
        path: path.resolve(__dirname, "dist"),
        filename: "bundles/apicurio-data-models.umd.js",
        library: {
            name: "ApicurioDM",
            type: "umd"
        }
    },
    performance: {
        hints: false
    }
};