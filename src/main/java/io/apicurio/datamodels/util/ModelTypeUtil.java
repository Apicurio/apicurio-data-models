package io.apicurio.datamodels.util;

import io.apicurio.datamodels.models.Node;

public class ModelTypeUtil {

    public static boolean isOpenApiModel(Node node) {
        switch (node.root().modelType()) {
            case OPENAPI20:
            case OPENAPI30:
            case OPENAPI31:
                return true;
            default:
                return false;
        }
    }

    public static boolean isAsyncApiModel(Node node) {
        switch (node.root().modelType()) {
            case ASYNCAPI20:
            case ASYNCAPI21:
            case ASYNCAPI22:
            case ASYNCAPI23:
            case ASYNCAPI24:
            case ASYNCAPI25:
                return true;
            default:
                return false;
        }
    }

    public static boolean isOpenApi3Model(Node node) {
        switch (node.root().modelType()) {
            case OPENAPI30:
            case OPENAPI31:
                return true;
            default:
                return false;
        }
    }

    public static boolean isOpenApi2Model(Node node) {
        switch (node.root().modelType()) {
            case OPENAPI20:
                return true;
            default:
                return false;
        }
    }

}
