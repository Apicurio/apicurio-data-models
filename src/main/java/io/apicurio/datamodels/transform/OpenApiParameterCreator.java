package io.apicurio.datamodels.transform;

import io.apicurio.datamodels.models.Operation;
import io.apicurio.datamodels.models.openapi.OpenApiOperation;
import io.apicurio.datamodels.models.openapi.OpenApiParameter;
import io.apicurio.datamodels.models.openapi.OpenApiPathItem;
import io.apicurio.datamodels.models.openapi.visitors.CombinedOpenApiVisitorAdapter;

public class OpenApiParameterCreator extends CombinedOpenApiVisitorAdapter {

    OpenApiParameter parameter;

    @Override
    public void visitOperation(Operation node) {
        OpenApiOperation operation = (OpenApiOperation) node;
        parameter = operation.createParameter();
        operation.addParameter(parameter);
    }

    @Override
    public void visitPathItem(OpenApiPathItem node) {
        parameter = node.createParameter();
        node.addParameter(parameter);
    }

}
