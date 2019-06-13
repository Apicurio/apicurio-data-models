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

package io.apicurio.datamodels.core.util;

import io.apicurio.datamodels.compat.NodeCompat;
import io.apicurio.datamodels.core.Constants;
import io.apicurio.datamodels.core.models.Node;

/**
 * @author eric.wittmann@gmail.com
 */
public class ReferenceUtil {

    /**
     * Resolves a node reference.  If there is no "$ref" property on the node, then the node itself is
     * returned.  If there is a "$ref" property, then it is resolved (if possible) to another node.
     */
    public static Node resolveNodeRef(Node node) {
        String $ref = (String) NodeCompat.getProperty(node, Constants.PROP_$REF);
        if (hasValue($ref)) {
            return ReferenceUtil.resolveRef($ref, node);
        }
        return node;
    }

    /**
     * Resolves a reference from a relative position in the data model.  Returns null if the
     * $ref is null or cannot be resolved.
     * @param $ref
     * @param from
     */
    public static Node resolveRef(String $ref, Node from) {
        if (!hasValue($ref)) {
            return null;
        }
        ReferenceResolver resolver = new ReferenceResolver();
        return resolver.resolveRef($ref, from);
    }

    /**
     * Returns true only if the given reference can be resolved relative to the given document.  Examples
     * of $ref values include:
     *
     * #/definitions/ExampleDefinition
     * #/parameters/fooId
     * #/responses/NotFoundResponse
     *
     * @param $ref
     * @param from
     */
    public static boolean canResolveRef(String $ref, Node from) {
        // Don't try to resolve e.g. external references.
        if ($ref.indexOf("#/") != 0) { return true; }
        return hasValue(ReferenceUtil.resolveRef($ref, from));
    }

    /**
     * Check if the property value exists (is not undefined and is not null).
     * @param value
     */
    public static boolean hasValue(Object value) {
        return !NodeCompat.isNullOrUndefined(value);
    }

}
