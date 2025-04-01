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

import io.apicurio.datamodels.core.Constants;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.validation.ValidationRuleMetaData;
import io.apicurio.datamodels.openapi.v3.models.Oas30Document;

/**
 * Reports an error if openapi 3 spec version is not supported (>= 3.1). 
 */
public class OasInvalidSpecVersionRule extends OasInvalidPropertyValueRule {
    
    public OasInvalidSpecVersionRule(ValidationRuleMetaData ruleInfo) {
        super(ruleInfo);
    }

    @Override
    public void visitDocument(Document node) {
        if (node instanceof Oas30Document) {
            String version = ((Oas30Document) node).openapi;
            this.reportIfInvalid(isSupportedOas30Version(version), node, Constants.PROP_OPENAPI, map(Constants.PROP_OPENAPI, version));
        }
    }
    
    private boolean isSupportedOas30Version(String version) {
        return version.startsWith("3.0");
    }
}
