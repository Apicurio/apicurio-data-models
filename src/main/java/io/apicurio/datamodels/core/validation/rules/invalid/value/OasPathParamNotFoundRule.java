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

import java.util.List;

import io.apicurio.datamodels.core.Constants;
import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.models.common.Parameter;
import io.apicurio.datamodels.core.util.ReferenceUtil;
import io.apicurio.datamodels.core.util.VisitorUtil;
import io.apicurio.datamodels.core.validation.ValidationRuleMetaData;
import io.apicurio.datamodels.core.validation.rules.PathItemFinder;
import io.apicurio.datamodels.core.visitors.TraverserDirection;
import io.apicurio.datamodels.openapi.models.OasParameter;
import io.apicurio.datamodels.openapi.models.OasPathItem;

/**
 * Implements the Path Parameter Not Found rule.
 * @author eric.wittmann@gmail.com
 */
public class OasPathParamNotFoundRule extends OasInvalidPropertyValueRule {

    /**
     * Constructor.
     * @param ruleInfo
     */
    public OasPathParamNotFoundRule(ValidationRuleMetaData ruleInfo) {
        super(ruleInfo);
    }
    
    protected static OasPathItem findPathItem(Node node) {
        PathItemFinder finder = new PathItemFinder();
        VisitorUtil.visitTree(node, finder, TraverserDirection.up);
        return finder.found;
    }
    
    protected boolean hasPathParamSegment(List<PathSegment> segments, String paramName) {
        for (PathSegment seg : segments) {
            if (equals(seg.formalName, paramName)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * @see io.apicurio.datamodels.combined.visitors.CombinedAllNodeVisitor#visitParameter(io.apicurio.datamodels.core.models.common.Parameter)
     */
    @Override
    public void visitParameter(Parameter node) {
        OasParameter resolvedParam = (OasParameter) ReferenceUtil.resolveNodeRef(node);
        if (hasValue(resolvedParam) && equals(resolvedParam.in, "path")) {
            // Note: parent may be an operation *or* a path-item.
            OasPathItem pathItem = findPathItem(node);
            String path = pathItem.getPath();
            List<PathSegment> pathSegs = getPathSegments(path);
            this.reportIf(!hasPathParamSegment(pathSegs, resolvedParam.name), node, Constants.PROP_NAME, map("name", resolvedParam.name));
        }
    }

}
