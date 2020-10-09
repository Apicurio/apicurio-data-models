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

package io.apicurio.datamodels.core.validation.rules.invalid.name;

import java.util.List;

import io.apicurio.datamodels.compat.NodeCompat;
import io.apicurio.datamodels.core.validation.ValidationRuleMetaData;
import io.apicurio.datamodels.openapi.models.OasPathItem;

/**
 * Implements the Duplicate Path Segment Rule.
 * @author eric.wittmann@gmail.com
 */
public class OasDuplicatePathSegmentRule extends InvalidPropertyNameRule {

    /**
     * Constructor.
     * @param ruleInfo
     */
    public OasDuplicatePathSegmentRule(ValidationRuleMetaData ruleInfo) {
        super(ruleInfo);
    }
    
    /**
     * @see io.apicurio.datamodels.combined.visitors.CombinedAllNodeVisitor#visitPathItem(io.apicurio.datamodels.openapi.models.OasPathItem)
     */
    @Override
    public void visitPathItem(OasPathItem node) {
        String pathTemplate = node.getPath();
        if (isPathWellFormed(pathTemplate)) {
            List<PathSegment> pathSegments = getPathSegments(pathTemplate);
            List<String> duplicateParameters = findDuplicateParametersInPath(pathSegments);
            if (duplicateParameters.size() > 0) {
                this.reportPathError(node, map("path", node.getPath(), "duplicates", NodeCompat.join(", ", duplicateParameters)));
            }
        }
    }

}
