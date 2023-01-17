package io.apicurio.datamodels.visitors;

import java.util.List;

import io.apicurio.datamodels.models.Document;
import io.apicurio.datamodels.models.Operation;
import io.apicurio.datamodels.models.SecurityRequirement;
import io.apicurio.datamodels.models.Server;
import io.apicurio.datamodels.models.asyncapi.AsyncApiServer;
import io.apicurio.datamodels.models.openapi.OpenApiDocument;
import io.apicurio.datamodels.models.openapi.OpenApiOperation;
import io.apicurio.datamodels.models.visitors.CombinedVisitorAdapter;
import io.apicurio.datamodels.util.ModelTypeUtil;

public class SecurityRequirementsFromParentVisitor extends CombinedVisitorAdapter {

    private List<? extends SecurityRequirement> securityRequirements;

    public List<? extends SecurityRequirement> getSecurityRequirements() {
        return securityRequirements;
    }

    @Override
    public void visitServer(Server node) {
        if (ModelTypeUtil.isAsyncApiModel(node)) {
            AsyncApiServer server = (AsyncApiServer) node;
            securityRequirements = server.getSecurity();
        }
    }

    @Override
    public void visitDocument(Document node) {
        OpenApiDocument doc = (OpenApiDocument) node;
        securityRequirements = doc.getSecurity();
    }

    @Override
    public void visitOperation(Operation node) {
        OpenApiOperation op = (OpenApiOperation) node;
        securityRequirements = op.getSecurity();
    }

}
