/*
 * Copyright 2020 Red Hat
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

import io.apicurio.datamodels.asyncapi.models.AaiChannelItem;
import io.apicurio.datamodels.asyncapi.models.AaiParameter;
import io.apicurio.datamodels.core.Constants;
import io.apicurio.datamodels.core.util.ReferenceUtil;
import io.apicurio.datamodels.core.validation.ValidationRuleMetaData;

/**
 * Implements the Channel Parameter Not Found rule.
 * @author eric.wittmann@gmail.com
 */
public class AaiChannelParamNotFoundRule extends OasInvalidPropertyValueRule {

    /**
     * Constructor.
     * @param ruleInfo
     */
    public AaiChannelParamNotFoundRule(ValidationRuleMetaData ruleInfo) {
        super(ruleInfo);
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
     * @see io.apicurio.datamodels.combined.visitors.CombinedAllNodeVisitor#visitAaiParameter(io.apicurio.datamodels.asyncapi.models.AaiParameter)
     */
    @Override
    public void visitAaiParameter(AaiParameter node) {
        AaiParameter resolvedParam = (AaiParameter) ReferenceUtil.resolveNodeRef(node);
        if (hasValue(resolvedParam)) {
            AaiChannelItem channelItem = (AaiChannelItem) node.parent();
            String name = channelItem.getName();
            List<PathSegment> pathSegs = getPathSegments(name);
            this.reportIf(!hasPathParamSegment(pathSegs, resolvedParam.getName()), node, Constants.PROP_NAME, map("name", resolvedParam.getName()));
        }
    }

}
