/*
 * Copyright 2019 Red Hat
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.apicurio.datamodels.core.validation.rules.invalid.value;

import java.util.ArrayList;
import java.util.List;

import io.apicurio.datamodels.core.models.common.Operation;
import io.apicurio.datamodels.core.validation.ValidationRuleMetaData;
import io.apicurio.datamodels.openapi.models.OasOperation;
import io.apicurio.datamodels.openapi.models.OasParameter;
import io.apicurio.datamodels.openapi.models.OasPathItem;
import io.apicurio.datamodels.openapi.v3.models.Oas30PathItem;

/**
 * @author eric.wittmann@gmail.com
 */
public class OasMissingPathParamDefinitionRule extends OasInvalidPropertyValueRule {

    private List<String> pathItemsWithError = new ArrayList<>();

    /**
     * Constructor.
     * @param ruleInfo
     */
    public OasMissingPathParamDefinitionRule(ValidationRuleMetaData ruleInfo) {
        super(ruleInfo);
    }

    /**
     * Returns true if the list of parameters includes a path param with the given name.
     * @param paramName
     * @param parameters
     */
    private boolean hasPathParameter(String paramName, List<OasParameter> parameters) {
        for (OasParameter parameter : parameters) {
            if (equals(parameter.in, "path") && equals(parameter.name, paramName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @see io.apicurio.datamodels.combined.visitors.CombinedAllNodeVisitor#visitPathItem(io.apicurio.datamodels.openapi.models.OasPathItem)
     */
    @Override
    public void visitPathItem(OasPathItem node) {
        if (!isPathWellFormed(node.getPath())) {
            this.pathItemsWithError.add(node.getPath());
        }
    }
    
    /**
     * @see io.apicurio.datamodels.combined.visitors.CombinedAllNodeVisitor#visitOperation(io.apicurio.datamodels.core.models.common.Operation)
     */
    @Override
    public void visitOperation(Operation node) {
        // Perform operation level checks only if there are no issues at the pathItem level.
        Oas30PathItem pathItem = (Oas30PathItem) node.parent();
        String path = pathItem.getPath();
        if (this.pathItemsWithError.indexOf(path) != -1) {
            return;
        }
        
        // Check parameters are unique within operation
        List<OasParameter> mergedParameters = mergeParameters((OasOperation) node);
        List<PathSegment> pathSegs = getPathSegments(path);
        // Report all the path segments that don't have an associated parameter definition
        for (PathSegment pathSeg : pathSegs) {
            if (!hasValue(pathSeg.formalName)) { continue; }
            boolean valid = hasPathParameter(pathSeg.formalName, mergedParameters);
            this.reportIfInvalid(valid, node,null, map(
                "param", pathSeg.formalName,
                "path", path,
                "method", node.getType().toUpperCase()
            ));
        }
    }

}