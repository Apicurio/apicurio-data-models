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

package io.apicurio.datamodels.validation.rules.invalid.name;

import io.apicurio.datamodels.models.Components;
import io.apicurio.datamodels.models.asyncapi.AsyncApiComponents;
import io.apicurio.datamodels.models.asyncapi.v20.AsyncApi20Components;
import io.apicurio.datamodels.models.asyncapi.v21.AsyncApi21Components;
import io.apicurio.datamodels.models.asyncapi.v22.AsyncApi22Components;
import io.apicurio.datamodels.models.asyncapi.v23.AsyncApi23Components;
import io.apicurio.datamodels.models.asyncapi.v24.AsyncApi24Components;
import io.apicurio.datamodels.models.asyncapi.v25.AsyncApi25Components;
import io.apicurio.datamodels.models.asyncapi.v26.AsyncApi26Components;
import io.apicurio.datamodels.models.asyncapi.v30.AsyncApi30Components;
import io.apicurio.datamodels.validation.ValidationRuleMetaData;

import java.util.Map;

/**
 * Implements the Invalid Component Key Name Pattern rule for AsyncAPI.
 * Rule: COMP-001
 * Component keys must match the regex pattern ^[a-zA-Z0-9\.\-_]+$
 *
 * @author eric.wittmann@gmail.com
 */
public class AaInvalidComponentKeyNameRule extends OasInvalidPropertyNameRule {

    /**
     * Constructor.
     * @param ruleInfo
     */
    public AaInvalidComponentKeyNameRule(ValidationRuleMetaData ruleInfo) {
        super(ruleInfo);
    }

    /**
     * @see io.apicurio.datamodels.models.visitors.AllNodeVisitor#visitComponents(io.apicurio.datamodels.models.Components)
     */
    @Override
    public void visitComponents(Components node) {
        // Validate common component keys
        validateMapKeys(node.getParameters(), node, "parameters");
        validateMapKeys(node.getSecuritySchemes(), node, "securitySchemes");

        // Validate AsyncAPI-specific component keys
        if (node instanceof AsyncApiComponents) {
            AsyncApiComponents asyncApiComponents = (AsyncApiComponents) node;
            validateMapKeys(asyncApiComponents.getMessages(), node, "messages");
            validateMapKeys(asyncApiComponents.getCorrelationIds(), node, "correlationIds");
            validateMapKeys(asyncApiComponents.getMessageTraits(), node, "messageTraits");
            validateMapKeys(asyncApiComponents.getOperationTraits(), node, "operationTraits");
            validateMapKeys(asyncApiComponents.getServerBindings(), node, "serverBindings");
            validateMapKeys(asyncApiComponents.getChannelBindings(), node, "channelBindings");
            validateMapKeys(asyncApiComponents.getOperationBindings(), node, "operationBindings");
            validateMapKeys(asyncApiComponents.getMessageBindings(), node, "messageBindings");
        }

        // Validate AsyncAPI 2.x-specific component keys
        if (node instanceof AsyncApi20Components) {
            validateMapKeys(((AsyncApi20Components) node).getSchemas(), node, "schemas");
        } else if (node instanceof AsyncApi21Components) {
            validateMapKeys(((AsyncApi21Components) node).getSchemas(), node, "schemas");
        } else if (node instanceof AsyncApi22Components) {
            validateMapKeys(((AsyncApi22Components) node).getSchemas(), node, "schemas");
        } else if (node instanceof AsyncApi23Components) {
            validateMapKeys(((AsyncApi23Components) node).getSchemas(), node, "schemas");
        } else if (node instanceof AsyncApi24Components) {
            validateMapKeys(((AsyncApi24Components) node).getSchemas(), node, "schemas");
        } else if (node instanceof AsyncApi25Components) {
            validateMapKeys(((AsyncApi25Components) node).getSchemas(), node, "schemas");
        } else if (node instanceof AsyncApi26Components) {
            validateMapKeys(((AsyncApi26Components) node).getSchemas(), node, "schemas");
        }

        // Validate AsyncAPI 3.0-specific component keys
        if (node instanceof AsyncApi30Components) {
            AsyncApi30Components asyncApi30Components = (AsyncApi30Components) node;
            validateMapKeys(asyncApi30Components.getSchemas(), node, "schemas");
            validateMapKeys(asyncApi30Components.getServers(), node, "servers");
            validateMapKeys(asyncApi30Components.getChannels(), node, "channels");
            validateMapKeys(asyncApi30Components.getOperations(), node, "operations");
            validateMapKeys(asyncApi30Components.getServerVariables(), node, "serverVariables");
            validateMapKeys(asyncApi30Components.getReplies(), node, "replies");
            validateMapKeys(asyncApi30Components.getReplyAddresses(), node, "replyAddresses");
            validateMapKeys(asyncApi30Components.getExternalDocs(), node, "externalDocs");
            validateMapKeys(asyncApi30Components.getTags(), node, "tags");
        }
    }

    /**
     * Validates all keys in a map against the component name pattern.
     *
     * @param map the map to validate
     * @param node the Components node (for error reporting)
     * @param propertyName the name of the property containing this map
     */
    private void validateMapKeys(Map<String, ?> map, Components node, String propertyName) {
        if (map != null) {
            for (String key : map.keySet()) {
                if (!isValidDefinitionName(key)) {
                    this.report(node, propertyName + "[" + key + "]", map("key", key));
                }
            }
        }
    }

}
