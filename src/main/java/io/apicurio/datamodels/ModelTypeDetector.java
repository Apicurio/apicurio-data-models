package io.apicurio.datamodels;

import com.fasterxml.jackson.databind.node.ObjectNode;

import io.apicurio.datamodels.models.ModelType;
import io.apicurio.datamodels.models.util.JsonUtil;

public class ModelTypeDetector {

    /**
     * Called to discover what type of model the given JSON data represents.  This method
     * interrogates the following appropriate top level properties:
     *
     * - asyncapi
     * - openapi
     * - swagger
     *
     * Based on which property is defined, and the value of that property, the correct model
     * type is determined and returned.
     *
     * @param json
     */
    public static ModelType discoverModelType(ObjectNode json) {
        String asyncapi = JsonUtil.getStringProperty(json, "asyncapi");
        String openapi = JsonUtil.getStringProperty(json, "openapi");
        String swagger = JsonUtil.getStringProperty(json, "swagger");
        if (asyncapi != null) {
            if (asyncapi.startsWith("2.0")) {
                return ModelType.ASYNCAPI20;
            } else if (asyncapi.startsWith("2.1")) {
                return ModelType.ASYNCAPI21;
            } else if (asyncapi.startsWith("2.2")) {
                return ModelType.ASYNCAPI22;
            } else if (asyncapi.startsWith("2.3")) {
                return ModelType.ASYNCAPI23;
            } else if (asyncapi.startsWith("2.4")) {
                return ModelType.ASYNCAPI24;
            } else if (asyncapi.startsWith("2.5")) {
                return ModelType.ASYNCAPI25;
            } else if (asyncapi.startsWith("2.6")) {
                return ModelType.ASYNCAPI26;
            } else {
                throw new RuntimeException("Unknown/unsupported AsyncAPI version: " + asyncapi);
            }
        }
        if (openapi != null) {
            if (openapi.startsWith("2.")) {
                return ModelType.OPENAPI20;
            } else if (openapi.startsWith("3.0")) {
                return ModelType.OPENAPI30;
            } else if (openapi.startsWith("3.1")) {
                return ModelType.OPENAPI31;
            } else {
                throw new RuntimeException("Unknown/unsupported OpenAPI version: " + openapi);
            }
        }
        if (swagger != null) {
            if (swagger.startsWith("2.")) {
                return ModelType.OPENAPI20;
            } else {
                throw new RuntimeException("Unknown/unsupported OpenAPI/Swagger version: " + swagger);
            }
        }

        throw new RuntimeException("Unknown/unsupported data model type or version.");
    }
}
