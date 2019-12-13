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

import java.util.ArrayList;
import java.util.List;

import io.apicurio.datamodels.compat.NodeCompat;
import io.apicurio.datamodels.compat.RegexCompat;
import io.apicurio.datamodels.core.Constants;
import io.apicurio.datamodels.core.models.IIndexedNode;
import io.apicurio.datamodels.core.models.Node;

/**
 * A class to help with resolving references.  Handles recursion with loop detection.
 * @author eric.wittmann@gmail.com
 */
public class ReferenceResolver implements IReferenceResolver {

    /**
     * Resolves a reference from a relative position in the data model.  Returns null if the
     * $ref is null or cannot be resolved.
     * @param $ref
     * @param from
     */
    public Node resolveRef(String $ref, Node from) {
        //this.visitedNodes = new ArrayList<>();
        return this.resolveRefInternal($ref, from, new ArrayList<>());
    }
    
    
    @SuppressWarnings("rawtypes")
    private Node resolveRefInternal(String $ref, Node from, List<Node> visitedNodes) {
        if ($ref == null) {
            return null;
        }
        // TODO implement a proper reference resolver including external file resolution: https://github.com/EricWittmann/oai-ts-core/issues/8
        List<String[]> split = RegexCompat.findMatches($ref, "([^/]+)/?");
        Object cnode = null;
        for (String[] mi : split) {
            String seg = mi[1];
            if (NodeCompat.equals(seg, "#")) {
                cnode = from.ownerDocument();
            } else if (cnode != null) {
                if (cnode instanceof IIndexedNode) {
                    cnode = ((IIndexedNode) cnode).getItem(seg);
                } else {
                    cnode = NodeCompat.getProperty(cnode, seg);
                }
            }
        }
        
        try {
            // Not found?  Return null.
            if (cnode == null) {
                return null;
            }
    
            // If we've already seen cnode, then we're in a loop!
            if (visitedNodes.indexOf(cnode) != -1) {
                return null;
            }
            
            // Otherwise, add it to the nodes we've seen.
            visitedNodes.add((Node) cnode);
    
            // If cnode itself has a $ref, then keep looking!
            String another$ref = (String) NodeCompat.getProperty(cnode, Constants.PROP_$REF);
            if (another$ref != null) {
                return this.resolveRefInternal(another$ref, (Node) cnode, visitedNodes);
            } else {
                return (Node) cnode;
            }
        } catch (Throwable t) {
            return null;
        }
    }
}
