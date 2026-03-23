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
            case ASYNCAPI30:
                return "3.0.0";
            case JSDRAFT4:
                return "draft-4";
            case JSDRAFT6:
                return "draft-6";
            case JSDRAFT7:
                return "draft-7";
            case JS201909:
                return "2019-09";
            case JS202012:
                return "2020-12";
            case OPENAPI20:
                return "2.0";
            case OPENAPI30:
                return "3.0.3";
            case OPENAPI31:
                return "3.1.0";
            case OPENRPC13:
                return "1.3.2";
            case OPENRPC14:
                return "1.4.0";
        }
        return "unknown";
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
            case ASYNCAPI30:
                return true;
            default:
                return false;
        }
    }

    public static boolean isAsyncApi2Model(Node node) {
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

    public static boolean isAsyncApi3Model(Node node) {
        switch (node.root().modelType()) {
            case ASYNCAPI30:
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

    public static boolean isJsonSchemaModel(Node node) {
        switch (node.root().modelType()) {
            case JSDRAFT4:
            case JSDRAFT6:
            case JSDRAFT7:
            case JS201909:
            case JS202012:
                return true;
            default:
                return false;
        }
    }

    public static boolean isOpenRpcModel(Node node) {
        switch (node.root().modelType()) {
            case OPENRPC13:
            case OPENRPC14:
                return true;
            default:
                return false;
        }
    }

}
