package io.apicurio.datamodels;

import com.fasterxml.jackson.databind.node.ObjectNode;

import io.apicurio.datamodels.util.JsonUtil;

public class DocumentTypeDetector {

    /**
     * Called to discover what type of document the given JSON data represents.  This method
     * interrogates the following appropriate top level properties:
     *
     * - asyncapi
     * - openapi
     * - swagger
     *
     * Based on which property is defined, and the value of that property, the correct document
     * type is determined and returned.
     *
     * @param json
     */
    public static DocumentType discoverDocumentType(ObjectNode json) {
        String asyncapi = JsonUtil.getStringProperty(json, "asyncapi");
        String openapi = JsonUtil.getStringProperty(json, "openapi");
        String swagger = JsonUtil.getStringProperty(json, "swagger");
        if (asyncapi != null) {
            if (asyncapi.startsWith("2.0")) {
                return DocumentType.ASYNCAPI_20;
            } else if (asyncapi.startsWith("2.1")) {
                return DocumentType.ASYNCAPI_21;
            } else if (asyncapi.startsWith("2.2")) {
                return DocumentType.ASYNCAPI_22;
            } else if (asyncapi.startsWith("2.3")) {
                return DocumentType.ASYNCAPI_23;
            } else if (asyncapi.startsWith("2.4")) {
                return DocumentType.ASYNCAPI_24;
            } else if (asyncapi.startsWith("2.5")) {
                return DocumentType.ASYNCAPI_25;
            } else {
                throw new RuntimeException("Unknown/unsupported AsyncAPI version: " + asyncapi);
            }
        }
        if (openapi != null) {
            if (openapi.startsWith("2.")) {
                return DocumentType.OPENAPI_2;
            } else if (openapi.startsWith("3.0")) {
                return DocumentType.OPENAPI_30;
            } else if (openapi.startsWith("3.1")) {
                return DocumentType.OPENAPI_31;
            } else {
                throw new RuntimeException("Unknown/unsupported OpenAPI version: " + openapi);
            }
        }
        if (swagger != null) {
            if (swagger.startsWith("2.")) {
                return DocumentType.OPENAPI_2;
            } else {
                throw new RuntimeException("Unknown/unsupported OpenAPI/Swagger version: " + swagger);
            }
        }

        throw new RuntimeException("Unknown/unsupported data model type or version.");
    }
}
