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

package io.apicurio.datamodels.validation.rules.invalid.value;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.apicurio.datamodels.models.openapi.OpenApiOperation;
import io.apicurio.datamodels.models.openapi.OpenApiParameter;
import io.apicurio.datamodels.models.openapi.OpenApiPathItem;
import io.apicurio.datamodels.refs.ReferenceUtil;
import io.apicurio.datamodels.validation.ValidationRule;
import io.apicurio.datamodels.validation.ValidationRuleMetaData;

/**
 * Base class for all Invalid Property Value rules.
 * @author eric.wittmann@gmail.com
 */
public abstract class AbstractInvalidPropertyValueRule extends ValidationRule {

    /**
     * Constructor.
     * @param ruleInfo
     */
    public AbstractInvalidPropertyValueRule(ValidationRuleMetaData ruleInfo) {
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
    protected List<OpenApiParameter> mergeParameters(OpenApiOperation node) {
        Map<String, OpenApiParameter> indexedParams = new HashMap<>();
        OpenApiPathItem parentNode = (OpenApiPathItem) node.parent();
        // Get the parameters from pathItem
        if (hasValue(parentNode.getParameters())) {
            for (OpenApiParameter param : parentNode.getParameters()) {
                OpenApiParameter resolutionResult = (OpenApiParameter) ReferenceUtil.resolveNodeRef(param);
                if (hasValue(resolutionResult)) {
                    String key = resolutionResult.getIn() + "-" + resolutionResult.getName();
                    indexedParams.put(key, resolutionResult);
                }
            }
        }
        // Overwrite parameters from parent
        if (hasValue(node.getParameters())) {
            for (OpenApiParameter param : node.getParameters()) {
                OpenApiParameter resolutionResult = (OpenApiParameter) ReferenceUtil.resolveNodeRef(param);
                if (hasValue(resolutionResult)) {
                    String key = resolutionResult.getIn() + "-" + resolutionResult.getName();
                    indexedParams.put(key, resolutionResult);
                }
            }
        }
        List<OpenApiParameter> mergedParameters = new ArrayList<>();
        mergedParameters.addAll(indexedParams.values());
        return mergedParameters;
    }

}
