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

package io.apicurio.datamodels.asyncapi.v2.visitors;

import io.apicurio.datamodels.asyncapi.v2.models.Aai20Components;
import io.apicurio.datamodels.asyncapi.visitors.AaiTraverser;
import io.apicurio.datamodels.core.models.common.Components;

/**
 * @author eric.wittmann@gmail.com
 */
public class Aai20Traverser extends AaiTraverser implements IAai20Visitor {

    public Aai20Traverser(IAai20Visitor visitor) {
        super(visitor);
    }

    @Override
    public void visitComponents(Components node) {
        Aai20Components components = (Aai20Components) node;
        node.accept(visitor);
        this.traverseCollection(components.getSchemaDefinitions());
        this.traverseCollection(components.getMessagesList());
        this.traverseCollection(components.getSecuritySchemesList());
        this.traverseCollection(components.getParametersList());
        this.traverseCollection(components.getCorrelationIdsList());
        this.traverseCollection(components.getMessageTraitDefinitionsList());
        this.traverseCollection(components.getOperationTraitDefinitionsList());
        this.traverseCollection(components.getServerBindingsDefinitionList());
        this.traverseCollection(components.getChannelBindingsDefinitionList());
        this.traverseCollection(components.getOperationBindingsDefinitionList());
        this.traverseCollection(components.getMessageBindingsDefinitionList());
        this.traverseExtensions(node);
        this.traverseValidationProblems(node);
    }
}
