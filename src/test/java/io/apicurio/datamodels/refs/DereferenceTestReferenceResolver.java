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

package io.apicurio.datamodels.refs;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.node.ObjectNode;

import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.models.Node;

/**
 * Reference resolver used in the {@link DereferenceTest}.
 * @author eric.wittmann@gmail.com
 */
public class DereferenceTestReferenceResolver implements IReferenceResolver {

    public static Map<String, ObjectNode> refs = new HashMap<>();

    @Override
    public Node resolveRef(String reference, Node from) {
        if (refs.containsKey(reference)) {
            return toModel(refs.get(reference), from);
        }
        return null;
    }

    private Node toModel(ObjectNode jsonNode, Node from) {
        Node rval = from.emptyClone();
        rval.attach(from.parent());
        return Library.readNode(jsonNode, rval);
    }

}
