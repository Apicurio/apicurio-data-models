package io.apicurio.datamodels.util;

import io.apicurio.datamodels.models.ModelType;
import io.apicurio.datamodels.models.Node;

public class ModelTypeUtil {

    public static ModelType fromString(String type) {
        return ModelType.valueOf(type);
    }

    public static String getVersion(ModelType type) {
        switch (type) {
            case ASYNCAPI20:
                return "2.0.0";
            case ASYNCAPI21:
                return "2.1.0";
            case ASYNCAPI22:
                return "2.2.0";
            case ASYNCAPI23:
                return "2.3.0";
            case ASYNCAPI24:
                return "2.4.0";
            case ASYNCAPI25:
                return "2.5.0";
            case ASYNCAPI26:
                return "2.6.0";
            case OPENAPI20:
                return "2.0";
            case OPENAPI30:
                return "3.0.3";
            case OPENAPI31:
                return "3.1.0";
            default:
                return null;
        }
    }

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
            case ASYNCAPI26:
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

    public static boolean isOpenApi30Model(Node node) {
        switch (node.root().modelType()) {
            case OPENAPI30:
                return true;
            default:
                return false;
        }
    }

    public static boolean isOpenApi31Model(Node node) {
        switch (node.root().modelType()) {
            case OPENAPI31:
                return true;
            default:
                return false;
        }
    }

}
