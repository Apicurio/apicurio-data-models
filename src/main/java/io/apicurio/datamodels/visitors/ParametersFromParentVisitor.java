package io.apicurio.datamodels.visitors;

import java.util.List;

import io.apicurio.datamodels.models.Operation;
import io.apicurio.datamodels.models.openapi.OpenApiOperation;
import io.apicurio.datamodels.models.openapi.OpenApiParameter;
import io.apicurio.datamodels.models.openapi.OpenApiPathItem;
import io.apicurio.datamodels.models.visitors.CombinedVisitorAdapter;

public class ParametersFromParentVisitor extends CombinedVisitorAdapter {

    private List<OpenApiParameter> parameters;

    public List<OpenApiParameter> getParameters() {
        return parameters;
    }

    @Override
    public void visitPathItem(OpenApiPathItem node) {
        parameters = node.getParameters();
    }

    @Override
    public void visitOperation(Operation node) {
        switch (node.root().modelType()) {
            case OPENAPI20:
            case OPENAPI30:
            case OPENAPI31:
                parameters = ((OpenApiOperation) node).getParameters();
                break;
            default:
                break;
        }
    }

}
