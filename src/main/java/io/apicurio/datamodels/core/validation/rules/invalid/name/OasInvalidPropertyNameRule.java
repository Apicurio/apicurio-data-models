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

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import io.apicurio.datamodels.compat.RegexCompat;
import io.apicurio.datamodels.core.validation.ValidationRule;
import io.apicurio.datamodels.core.validation.ValidationRuleMetaData;

/**
 * Base class for all Invalid Property Name rules.
 * @author eric.wittmann@gmail.com
 */
public abstract class OasInvalidPropertyNameRule extends ValidationRule {

    private static final String DEFINITION_NAME_MATCH_REGEX = "^[a-zA-Z0-9\\.\\-_]+$";
    
    /**
     * Constructor.
     * @param ruleInfo
     */
    public OasInvalidPropertyNameRule(ValidationRuleMetaData ruleInfo) {
        super(ruleInfo);
    }

    /**
     * Returns true if the definition name is valid.
     * @param name
     */
    protected boolean isValidDefinitionName(String name) {
        // TODO should this be different for OAS 2.0 vs. 3.x??  Only 3.x dictates the format to some extent (I think).
        return RegexCompat.matches(name, DEFINITION_NAME_MATCH_REGEX);
    }

    /**
     * Returns true if the scope name is valid.
     * @param scope
     */
    protected boolean isValidScopeName(String scope) {
        // TODO implement some reasonable rules for this
        return true;
    }

    /**
     * Finds all occurences of path segments that are empty.
     * i.e. they neither have a prefix nor a path variable within curly braces.
     *
     * @param pathSegments
     */
    protected List<PathSegment> findEmptySegmentsInPath(List<PathSegment> pathSegments) {
        List<PathSegment> emptySegments = new ArrayList<>();
        for (PathSegment pathSegment : pathSegments) {
            if (equals(pathSegment.prefix, "") && !hasValue(pathSegment.formalName)) {
                emptySegments.add(pathSegment);
            }
        }
        return emptySegments;
    }

    /**
     * Finds path segments that are duplicates i.e. they have the same formal name used across multiple segments.
     * For example, in a path like /prefix/{var1}/{var1}, var1 is used in multiple segments.
     *
     * @param pathSegments
     */
    protected List<String> findDuplicateParametersInPath(List<PathSegment> pathSegments) {
        Map<String, Integer> counts = new LinkedHashMap<>();
        pathSegments.forEach(pathSegment -> {
            if (hasValue(pathSegment.formalName)) {
                int count = 0;
                if (counts.containsKey(pathSegment.formalName)) {
                    count = counts.get(pathSegment.formalName);
                }
                counts.put(pathSegment.formalName, count + 1);
            }
        });
        
        List<String> rval = new ArrayList<>();
        for (Entry<String, Integer> entry : counts.entrySet()) {
            if (entry.getValue() > 1) {
                rval.add(entry.getKey());
            }
        }
        return rval;
    }

}
