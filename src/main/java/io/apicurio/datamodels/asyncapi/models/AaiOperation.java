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

package io.apicurio.datamodels.asyncapi.models;

import java.util.LinkedList;
import java.util.List;

import io.apicurio.datamodels.core.models.Node;

/**
 * @author eric.wittmann@gmail.com
 * @author Jakub Senko <jsenko@redhat.com>
 */
public abstract class AaiOperation extends AaiOperationBase {

    public List<AaiOperationTrait> traits;
    public AaiMessage message;
    
    /**
     * Constructor.
     */
    public AaiOperation(String opType) {
        super(opType);
    }

    /**
     * Constructor.
     * @param parent
     */
    public AaiOperation(Node parent) {
        super(parent);
    }

    /**
     * Constructor.
     * @param parent
     * @param opType
     */
    public AaiOperation(Node parent, String opType) {
        super(parent, opType);
    }

    /**
     * Adds a tag.
     * @param tag
     */
    public void addTrait(AaiOperationTrait trait) {
        if(traits == null)
            traits = new LinkedList<>();
        traits.add(trait);
    }

}
