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

package io.apicurio.datamodels.core.models;

import java.util.ArrayList;
import java.util.List;

import io.apicurio.datamodels.compat.NodeCompat;
import io.apicurio.datamodels.core.visitors.IVisitor;

/**
 * Represents a path to a node within the data model.  Any node in a data model can be 
 * represented by its path.  The node path can be used to identify and locate a single node 
 * in the document tree.
 * @author eric.wittmann@gmail.com
 */
public class NodePath {
    
    private static final int SCAN_TYPE_PATH = 0;
    private static final int SCAN_TYPE_INDEX = 1;
    
    private List<NodePathSegment> segments = new ArrayList<>();
    
    /**
     * Constructor.
     */
    public NodePath() {
    }
    
    /**
     * Construct a node path from a string representation.
     * @param path
     */
    public NodePath(String path) {
        if (path != null && path.indexOf("/") == 0 && !path.equals("/")) {
            int currentScanType = SCAN_TYPE_PATH;
            int currentIdx = 1;
            while (currentIdx < path.length()) {
                int segStart = currentIdx;
                int segEnd;
                if (currentScanType == SCAN_TYPE_PATH) {
                    int nextPathSep = path.indexOf("/", segStart);
                    int nextBrace = path.indexOf("[", segStart);
                    if (nextPathSep == -1) { nextPathSep = path.length(); }
                    if (nextBrace == -1) { nextBrace = path.length(); }
                    if (nextPathSep <= nextBrace) {
                        segEnd = nextPathSep;
                    } else {
                        segEnd = nextBrace;
                    }
                } else {
                    int nextCloseBrace = path.indexOf("]", segStart);
                    if (nextCloseBrace == -1) { nextCloseBrace = path.length(); }
                    segEnd = nextCloseBrace + 1;
                }

                String seg = path.substring(segStart, segEnd);
                NodePathSegment segment = NodePathSegment.fromString(seg);
                this.segments.add(segment);

                // Default next values.
                currentScanType = SCAN_TYPE_PATH;
                currentIdx = segEnd + 1;

                // Find real next values.
                if (segEnd >= path.length()) {
                    // No further scanning - end of input.
                } else if (path.charAt(segEnd) == '/') {
                    currentScanType = SCAN_TYPE_PATH;
                    currentIdx = segEnd + 1;
                } else if (path.charAt(segEnd) == '[') {
                    currentScanType = SCAN_TYPE_INDEX;
                    currentIdx = segEnd;
                } else if (path.charAt(segEnd) == ']') {
                    if (path.charAt(segEnd+1) == '[') {
                        currentScanType = SCAN_TYPE_INDEX;
                        currentIdx = segEnd + 1;
                    } else if (path.charAt(segEnd+1) == '/') {
                        currentScanType = SCAN_TYPE_PATH;
                        currentIdx = segEnd + 1;
                    }
                }
            }
        }
    }
    
    /**
     * Adds a segment to the beginning of the path.
     * @param value
     * @param index
     */
    public void prependSegment(String value, boolean index) {
        this.segments.add(0, new NodePathSegment(value, index));
    }

    /**
     * Adds a segment to the end of the path.
     * @param value
     * @param index
     */
    public void appendSegment(String value, boolean index) {
        this.segments.add(new NodePathSegment(value, index));
    }

    /**
     * Resolves a path to its target node within the document model.  This basically
     * walks the tree according to the path segments until it reaches the node being
     * referenced.  If the path does not point to a valid node, then this method
     * returns undefined.
     * @param document the document to resolve the path relative to
     * @param visitor an optional visitor to invoke for each node in the path (can be null)
     */
    public Node resolve(Document document, IVisitor visitor) {
        Node node = document;
        if (visitor != null) {
            node.accept(visitor);
        }
        
        Object oNode = node;
        for (NodePathSegment segment : this.segments) {
            oNode = segment.resolve(oNode);
            if (visitor != null && oNode != null && oNode instanceof IVisitable) {
                ((IVisitable) oNode).accept(visitor);
            }
        }

        return (Node) oNode;
    }

    /**
     * Returns true if this path "contains" the given node.  The path is said to contain
     * a node if the node is visited while resolving it.  In other words, if one of the
     * segments of the path represents the node, then this will return true, otherwise it
     * will return false.
     * @param node
     */
    public boolean contains(Node node) {
        Object tnode = node.ownerDocument();
        // Of course the root document is always a match.
        if (tnode == node) {
            return true;
        }
        
        for (NodePathSegment segment : this.segments) {
            tnode = segment.resolve(tnode);
            if (tnode == node) {
                return true;
            }
        }
        return false;
    }

    public List<String> toSegments() {
        List<String> rval = new ArrayList<>();
        this.segments.forEach(segment -> {
            rval.add(segment.getValue());
        });
        return rval;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        if (this.segments.size() == 0) {
            return "/";
        }
        String rval = "";
        for (NodePathSegment segment : this.segments) {
            if (segment.isIndex()) {
                rval += '[' + segment.getValue() + ']';
            } else {
                rval += '/' + segment.getValue();
            }
        }
        return rval;
    }

    /**
     * Represents a single segment in a model node path.
     * @author eric.wittmann@gmail.com
     */
    private static class NodePathSegment {
        private String value;
        private boolean index;
        
        /**
         * Constructor.
         */
        public NodePathSegment(String value) {
            this.value = value;
            this.index = false;
        }
        
        /**
         * Constructor.
         */
        public NodePathSegment(String value, boolean index) {
            this(value);
            this.index = index;
        }
        
        public String getValue() {
            return this.value;
        }
        
        public boolean isIndex() {
            return this.index;
        }
        
        /**
         * Resolves a single segment.
         * @param from
         */
        @SuppressWarnings("rawtypes")
        public Object resolve(Object from) {
            if (from == null) {
                return null;
            }
            Object childNode = null; // Type is:  Node || List
            if (this.isIndex() && from instanceof IIndexedNode) {
                childNode = ((IIndexedNode) from).getItem(this.getValue());
            } else if (this.getValue().indexOf("x-") == 0) {
                Node fromNode = (Node) from;
                if (fromNode.isExtensible()) {
                    childNode = ((ExtensibleNode) from).getExtension(this.getValue());
                } else {
                    childNode = null;
                }
            } else {
                childNode = NodeCompat.getProperty(from, this.getValue());
            }
            return childNode;
        }
        
        /**
         * Creates a segment from a string.
         * @param segment
         */
        public static NodePathSegment fromString(String segment) {
            if (segment == null) {
                return new NodePathSegment(null);
            }
            if (segment.indexOf("[") == 0) {
                int bStart = segment.indexOf("[");
                int bEnd = segment.indexOf("]", bStart);
                String value = segment.substring(bStart + 1, bEnd);
                return new NodePathSegment(value, true);
            }
            return new NodePathSegment(segment);
        }
    }
    
}
