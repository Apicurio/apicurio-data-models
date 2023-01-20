package io.apicurio.datamodels.transform;

import io.apicurio.datamodels.models.Document;
import io.apicurio.datamodels.models.Operation;
import io.apicurio.datamodels.models.SecurityRequirement;
import io.apicurio.datamodels.models.Server;
import io.apicurio.datamodels.models.asyncapi.AsyncApiSecurityRequirement;
import io.apicurio.datamodels.models.asyncapi.AsyncApiServer;
import io.apicurio.datamodels.models.openapi.OpenApiDocument;
import io.apicurio.datamodels.models.openapi.OpenApiOperation;
import io.apicurio.datamodels.models.openapi.OpenApiSecurityRequirement;
import io.apicurio.datamodels.models.visitors.CombinedVisitorAdapter;

public class SecurityRequirementCreator extends CombinedVisitorAdapter {

    SecurityRequirement securityRequirement;

    @Override
    public void visitDocument(Document node) {
        OpenApiDocument doc = (OpenApiDocument) node;
        securityRequirement = doc.createSecurityRequirement();
        doc.addSecurity((OpenApiSecurityRequirement) securityRequirement);
    }

    @Override
    public void visitServer(Server node) {
        AsyncApiServer server = (AsyncApiServer) node;
        securityRequirement = server.createSecurityRequirement();
        server.addSecurity((AsyncApiSecurityRequirement) securityRequirement);
    }

    @Override
    public void visitOperation(Operation node) {
        OpenApiOperation op = (OpenApiOperation) node;
        securityRequirement = op.createSecurityRequirement();
        op.addSecurity((OpenApiSecurityRequirement) securityRequirement);
    }

}
