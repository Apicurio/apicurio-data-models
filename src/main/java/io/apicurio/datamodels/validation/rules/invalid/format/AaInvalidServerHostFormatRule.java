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

package io.apicurio.datamodels.validation.rules.invalid.format;

import io.apicurio.datamodels.models.Server;
import io.apicurio.datamodels.models.asyncapi.v30.AsyncApi30Server;
import io.apicurio.datamodels.util.ModelTypeUtil;
import io.apicurio.datamodels.validation.ValidationRule;
import io.apicurio.datamodels.validation.ValidationRuleMetaData;

/**
 * Rule: SRV-006
 * Validates that a Server's host property in AsyncAPI 3.0 is a valid hostname or IP address.
 * The host should not contain protocol schemes (http://, https://, etc.).
 *
 * @author eric.wittmann@gmail.com
 */
public class AaInvalidServerHostFormatRule extends ValidationRule {

    /**
     * Constructor.
     *
     * @param ruleInfo
     */
    public AaInvalidServerHostFormatRule(ValidationRuleMetaData ruleInfo) {
        super(ruleInfo);
    }

    /**
     * Returns true only if the given value is a valid host.
     *
     * @param propertyValue
     * @return {boolean}
     */
    public static boolean isValidHost(String propertyValue) {
        // Host should not contain protocol schemes
        if (propertyValue.indexOf("http:") == 0 || propertyValue.indexOf("https:") == 0 ||
            propertyValue.indexOf("ws:") == 0 || propertyValue.indexOf("wss:") == 0 ||
            propertyValue.indexOf("://") >= 0) {
            return false;
        }
        return true;
    }

    @Override
    public void visitServer(Server node) {
        if (ModelTypeUtil.isAsyncApi3Model(node)) {
            String host = ((AsyncApi30Server) node).getHost();
            if (hasValue(host)) {
                this.reportIfInvalid(isValidHost(host), node, "host", map());
            }
        }
    }

}
