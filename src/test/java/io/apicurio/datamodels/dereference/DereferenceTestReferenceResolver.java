/*
 * Copyright 2020 Red Hat
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

package io.apicurio.datamodels.dereference;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;

import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.cloning.ModelCloner;
import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.util.IReferenceResolver;

/**
 * Reference resolver used in the {@link DereferenceTest}.
 * @author eric.wittmann@gmail.com
 */
public class DereferenceTestReferenceResolver implements IReferenceResolver {
    
    public static Map<String, JsonNode> refs = new HashMap<>();

    /**
     * @see io.apicurio.datamodels.core.util.IReferenceResolver#resolveRef(java.lang.String, io.apicurio.datamodels.core.models.Node)
     */
    @Override
    public Node resolveRef(String reference, Node from) {
        if (refs.containsKey(reference)) {
            return toModel(refs.get(reference), from);
        }
        return null;
    }

    private Node toModel(JsonNode jsonNode, Node from) {
        Node rval = ModelCloner.createEmptyClone(from);
        return Library.readNode(jsonNode, rval);
    }

}
