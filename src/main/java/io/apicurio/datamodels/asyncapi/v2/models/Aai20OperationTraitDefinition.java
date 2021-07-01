/*
 * Copyright 2019 JBoss Inc
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

package io.apicurio.datamodels.asyncapi.v2.models;

import io.apicurio.datamodels.asyncapi.models.AaiOperationTraitDefinition;
import io.apicurio.datamodels.asyncapi.models.AaiTag;
import io.apicurio.datamodels.core.models.Node;

/**
 * @author eric.wittmann@gmail.com
 */
public class Aai20OperationTraitDefinition extends AaiOperationTraitDefinition {

    /**
     * Constructor.
     * @param name
     */
    public Aai20OperationTraitDefinition(String name) {
        super(name);
    }

    /**
     * Constructor.
     * @param parent
     */
    public Aai20OperationTraitDefinition(Node parent) {
        super(parent);
    }

    /**
     * Constructor.
     * @param parent
     * @param name
     */
    public Aai20OperationTraitDefinition(Node parent, String name) {
        super(parent, name);
    }

    /**
     * @see io.apicurio.datamodels.asyncapi.models.AaiOperationBase#createTag()
     */
    @Override
    public AaiTag createTag() {
        return new Aai20Tag(this);
    }

    /**
     * @see io.apicurio.datamodels.core.models.common.Operation#createExternalDocumentation()
     */
    @Override
    public Aai20ExternalDocumentation createExternalDocumentation() {
        return new Aai20ExternalDocumentation(this);
    }

}
