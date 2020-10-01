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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.apicurio.datamodels.core.util.ReferenceUtil;
import io.apicurio.datamodels.core.validation.ValidationRuleMetaData;
import io.apicurio.datamodels.openapi.models.OasOperation;
import io.apicurio.datamodels.openapi.models.OasParameter;
import io.apicurio.datamodels.openapi.models.OasPathItem;

/**
 * Base class for all Invalid Property Value rules.
 * @author eric.wittmann@gmail.com
 */
public class OasInvalidPropertyValueRule extends InvalidPropertyValueRule {

    /**
     * Constructor.
     * @param ruleInfo
     */
    public OasInvalidPropertyValueRule(ValidationRuleMetaData ruleInfo) {
        super(ruleInfo);
    }

    /**
     * Returns true if the given media type name is multipart/* or application/x-www-form-urlencoded
     * @param typeName
     */
    protected boolean isValidMultipartType(String typeName) {
        return equals(typeName, "application/x-www-form-urlencoded") || typeName.indexOf("multipart") == 0;
    }

    /**
     * Merges all parameters applicable for an operation - those defined within the operation and those defined at the pathItem level.
     * Resolves parameters that are not defined inline but are referenced from the components/parameters section.
     */
    protected List<OasParameter> mergeParameters(OasOperation node) {
        Map<String, OasParameter> indexedParams = new HashMap<>();
        OasPathItem parentNode = (OasPathItem) node.parent();
        // Get the parameters from pathItem
        if (hasValue(parentNode.parameters)) {
            for (OasParameter param : parentNode.parameters) {
                OasParameter resolutionResult = (OasParameter) ReferenceUtil.resolveNodeRef(param);
                if (hasValue(resolutionResult)) {
                    String key = resolutionResult.in + "-" + resolutionResult.name;
                    indexedParams.put(key, resolutionResult);
                }
            }
        }
        // Overwrite parameters from parent
        if (hasValue(node.parameters)) {
            for (OasParameter param : node.parameters) {
                OasParameter resolutionResult = (OasParameter) ReferenceUtil.resolveNodeRef(param);
                if (hasValue(resolutionResult)) {
                    String key = resolutionResult.in + "-" + resolutionResult.name;
                    indexedParams.put(key, resolutionResult);
                }
            }
        }
        List<OasParameter> mergedParameters = new ArrayList<>();
        mergedParameters.addAll(indexedParams.values());
        return mergedParameters;
    }

}
