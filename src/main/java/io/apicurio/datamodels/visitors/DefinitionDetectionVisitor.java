package io.apicurio.datamodels.visitors;

import io.apicurio.datamodels.models.Components;
import io.apicurio.datamodels.models.openapi.v20.OpenApi20Definitions;
import io.apicurio.datamodels.models.openapi.v20.OpenApi20ParameterDefinitions;
import io.apicurio.datamodels.models.openapi.v20.OpenApi20ResponseDefinitions;
import io.apicurio.datamodels.models.openapi.v20.OpenApi20SecurityDefinitions;
import io.apicurio.datamodels.models.visitors.CombinedVisitorAdapter;

public class DefinitionDetectionVisitor extends CombinedVisitorAdapter {

    private boolean isDefinitionParent = false;

    public boolean isDefinitionParentDetected() {
        return isDefinitionParent;
    }

    @Override
    public void visitDefinitions(OpenApi20Definitions node) {
        this.isDefinitionParent = true;
    }

    @Override
    public void visitParameterDefinitions(OpenApi20ParameterDefinitions node) {
        this.isDefinitionParent = true;
    }

    @Override
    public void visitResponseDefinitions(OpenApi20ResponseDefinitions node) {
        this.isDefinitionParent = true;
    }

    @Override
    public void visitSecurityDefinitions(OpenApi20SecurityDefinitions node) {
        this.isDefinitionParent = true;
    }

    @Override
    public void visitComponents(Components node) {
        this.isDefinitionParent = true;
    }

}
