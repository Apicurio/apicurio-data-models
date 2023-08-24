package io.apicurio.datamodels.deref;

import io.apicurio.datamodels.models.Components;
import io.apicurio.datamodels.models.openapi.v20.OpenApi20Definitions;
import io.apicurio.datamodels.models.openapi.v20.OpenApi20ParameterDefinitions;
import io.apicurio.datamodels.models.openapi.v20.OpenApi20ResponseDefinitions;
import io.apicurio.datamodels.models.openapi.v20.OpenApi20SecurityDefinitions;
import io.apicurio.datamodels.models.visitors.CombinedVisitorAdapter;

/**
 * Visitor to determine if a Node is a "Components" node.  This includes the actual
 * Components entities from OpenAPI 3 and AsyncAPI 2.  It also includes all of the
 * Definitions entities from OpenAPI 2.
 *
 * @author eric.wittmann@gmail.com
 */
public class IsDefinitionParentVisitor extends CombinedVisitorAdapter {

    private boolean _isDefinitionParent = false;

    public boolean isDefinitionParent() {
        return this._isDefinitionParent;
    }

    @Override
    public void visitComponents(Components node) {
        this._isDefinitionParent = true;
    }

    @Override
    public void visitDefinitions(OpenApi20Definitions node) {
        this._isDefinitionParent = true;
    }

    @Override
    public void visitParameterDefinitions(OpenApi20ParameterDefinitions node) {
        this._isDefinitionParent = true;
    }

    @Override
    public void visitResponseDefinitions(OpenApi20ResponseDefinitions node) {
        this._isDefinitionParent = true;
    }

    @Override
    public void visitSecurityDefinitions(OpenApi20SecurityDefinitions node) {
        this._isDefinitionParent = true;
    }

}
