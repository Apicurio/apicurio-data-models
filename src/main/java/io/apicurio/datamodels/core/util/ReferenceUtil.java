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

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import io.apicurio.datamodels.compat.JsonCompat;
import io.apicurio.datamodels.compat.NodeCompat;
import io.apicurio.datamodels.compat.RegexCompat;
import io.apicurio.datamodels.core.Constants;
import io.apicurio.datamodels.core.models.Node;

/**
 * @author eric.wittmann@gmail.com
 */
public class ReferenceUtil {

    /**
     * Resolves a node reference.  If there is no "$ref" property on the node, then the node itself is
     * returned.  If there is a "$ref" property, then it is resolved (if possible) to another node.
     * @param node
     */
    public static Node resolveNodeRef(Node node) {
        return resolveNodeRef(node, ResolverOptions.of(ResolverOptionsType.RECURSIVE));
    }

    /**
     * Resolves a node reference.  If there is no "$ref" property on the node, then the node itself is
     * returned.  If there is a "$ref" property, then it is resolved (if possible) to another node.
     * @param node
     * @param options
     */
    public static Node resolveNodeRef(Node node, ResolverOptions options) {
        if (!hasValue(node)) {
            return null;
        }
        String $ref = (String) NodeCompat.getProperty(node, Constants.PROP_$REF);
        return resolveRef($ref, node, options);
    }

    /**
     * Resolves a reference from a relative position in the data model.  Returns null if the
     * $ref is null or cannot be resolved.  Attemps to recursively resolve nodes.
     * @param $ref
     * @param from
     */
    public static Node resolveRef(String $ref, Node from) {
        if (!hasValue($ref)) {
            return null;
        }
        return resolveRef($ref, from, ResolverOptions.of(ResolverOptionsType.RECURSIVE, ResolverOptionsType.NULL_IF_NOT_FOUND));
    }
    
    /**
     * Resolves a reference from a relative position in the data model.
     * @param $ref
     * @param from
     * @param options
     */
    public static Node resolveRef(String $ref, Node from, ResolverOptions options) {
        IReferenceResolver resolver = ReferenceResolverChain.getInstance();

        Node rval = null;
        if (hasValue($ref)) {
            rval = resolver.resolveRef($ref, from);
        }
        
        // Recursively resolve the reference.  Useful if a $ref refers to another node that is also just a $ref.
        // This tracks $ref values to avoid infinite looping.
        if (rval != null && options.contains(ResolverOptionsType.RECURSIVE)) {
            Set<String> observedRefs = new HashSet<>();
            boolean done = false;
            while (!done) {
                observedRefs.add($ref);
                $ref = (String) NodeCompat.getProperty(rval, Constants.PROP_$REF);
                if (hasValue($ref) && !observedRefs.contains($ref)) {
                    rval = resolver.resolveRef($ref, from);
                } else if (observedRefs.contains($ref)) {
                    rval = null;
                    done = true;
                } else {
                    done = true;
                }
                if (!hasValue(rval)) {
                    done = true;
                }
            }
        }
        
        if (rval != null) {
            return rval;
        }
        if (options.contains(ResolverOptionsType.NULL_IF_NOT_FOUND)) {
            return null;
        } else {
            return from;
        }
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
        return hasValue(ReferenceUtil.resolveRef($ref, from));
    }

    /**
     * Check if the property value exists (is not undefined and is not null).
     * @param value
     */
    public static boolean hasValue(Object value) {
        return !NodeCompat.isNullOrUndefined(value);
    }
    
    /**
     * Resolves the given $ref fragment (e.g. /components/schemas/MySchema) against the
     * given JS Object context.  This operates against raw javascript, not against a 
     * data model.
     * @param contextNode
     * @param fragment
     */
    public static Object resolveFragmentFromJS(Object contextNode, String fragment) {
        List<String[]> split = RegexCompat.findMatches(fragment, "([^/]+)/?");
        Object cnode = contextNode;
        for (String[] mi : split) {
            String seg = mi[1];
            if (NodeCompat.equals(seg, "#")) {
                cnode = contextNode;
            } else if (cnode != null) {
                cnode = JsonCompat.getProperty(cnode, seg);
            }
        }
        return cnode;
    }

}
