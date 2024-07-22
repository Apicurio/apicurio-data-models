package io.apicurio.datamodels.transform;

import io.apicurio.datamodels.models.Document;
import io.apicurio.datamodels.models.Operation;
import io.apicurio.datamodels.models.SecurityRequirement;
import io.apicurio.datamodels.models.Server;
import io.apicurio.datamodels.models.asyncapi.AsyncApiSecurityRequirement;
import io.apicurio.datamodels.models.asyncapi.AsyncApiServer;
import io.apicurio.datamodels.models.asyncapi.v20.AsyncApi20SecurityRequirement;
import io.apicurio.datamodels.models.asyncapi.v20.AsyncApi20Server;
import io.apicurio.datamodels.models.asyncapi.v21.AsyncApi21SecurityRequirement;
import io.apicurio.datamodels.models.asyncapi.v21.AsyncApi21Server;
import io.apicurio.datamodels.models.asyncapi.v22.AsyncApi22SecurityRequirement;
import io.apicurio.datamodels.models.asyncapi.v22.AsyncApi22Server;
import io.apicurio.datamodels.models.asyncapi.v23.AsyncApi23SecurityRequirement;
import io.apicurio.datamodels.models.asyncapi.v23.AsyncApi23Server;
import io.apicurio.datamodels.models.asyncapi.v24.AsyncApi24SecurityRequirement;
import io.apicurio.datamodels.models.asyncapi.v24.AsyncApi24Server;
import io.apicurio.datamodels.models.asyncapi.v25.AsyncApi25SecurityRequirement;
import io.apicurio.datamodels.models.asyncapi.v25.AsyncApi25Server;
import io.apicurio.datamodels.models.asyncapi.v26.AsyncApi26SecurityRequirement;
import io.apicurio.datamodels.models.asyncapi.v26.AsyncApi26Server;
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
        AsyncApiSecurityRequirementParent srp = new AsyncApiSecurityRequirementParent(server);
        securityRequirement = srp.createSecurityRequirement();
        srp.addSecurity((AsyncApiSecurityRequirement) securityRequirement);
    }

    @Override
    public void visitOperation(Operation node) {
        OpenApiOperation op = (OpenApiOperation) node;
        securityRequirement = op.createSecurityRequirement();
        op.addSecurity((OpenApiSecurityRequirement) securityRequirement);
    }

    /**
     * Note: this is ugly and needed because AsyncApi 3 removed security requirements.  The generated
     * data model is not very good when commonalities between the versions of specs are not present.  I
     * think we need improvements in the generated data model.  Perhaps we can generate XyzParent
     * interfaces for properties?  Maybe only when necessary?  I'm open to other ideas.
     */
    private class AsyncApiSecurityRequirementParent {
        AsyncApiServer server;

        private AsyncApiSecurityRequirementParent(AsyncApiServer server) {
            this.server = server;
        }

        public AsyncApiSecurityRequirement createSecurityRequirement() {
            if (server instanceof AsyncApi20Server) {
                return ((AsyncApi20Server) server).createSecurityRequirement();
            }
            if (server instanceof AsyncApi21Server) {
                return ((AsyncApi21Server) server).createSecurityRequirement();
            }
            if (server instanceof AsyncApi22Server) {
                return ((AsyncApi22Server) server).createSecurityRequirement();
            }
            if (server instanceof AsyncApi23Server) {
                return ((AsyncApi23Server) server).createSecurityRequirement();
            }
            if (server instanceof AsyncApi24Server) {
                return ((AsyncApi24Server) server).createSecurityRequirement();
            }
            if (server instanceof AsyncApi25Server) {
                return ((AsyncApi25Server) server).createSecurityRequirement();
            }
            if (server instanceof AsyncApi26Server) {
                return ((AsyncApi26Server) server).createSecurityRequirement();
            }
            return null;
        }

        public void addSecurity(AsyncApiSecurityRequirement securityRequirement) {
            if (server instanceof AsyncApi20Server) {
                ((AsyncApi20Server) server).addSecurity((AsyncApi20SecurityRequirement) securityRequirement);
            }
            if (server instanceof AsyncApi21Server) {
                ((AsyncApi21Server) server).addSecurity((AsyncApi21SecurityRequirement) securityRequirement);
            }
            if (server instanceof AsyncApi22Server) {
                ((AsyncApi22Server) server).addSecurity((AsyncApi22SecurityRequirement) securityRequirement);
            }
            if (server instanceof AsyncApi23Server) {
                ((AsyncApi23Server) server).addSecurity((AsyncApi23SecurityRequirement) securityRequirement);
            }
            if (server instanceof AsyncApi24Server) {
                ((AsyncApi24Server) server).addSecurity((AsyncApi24SecurityRequirement) securityRequirement);
            }
            if (server instanceof AsyncApi25Server) {
                ((AsyncApi25Server) server).addSecurity((AsyncApi25SecurityRequirement) securityRequirement);
            }
            if (server instanceof AsyncApi26Server) {
                ((AsyncApi26Server) server).addSecurity((AsyncApi26SecurityRequirement) securityRequirement);
            }
        }

    }

}
