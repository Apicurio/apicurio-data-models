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
     * Adds a non-index segment to the beginning of the path.
     * @param value
     */
    public void prependSegment(String value) {
        this.prependSegment(value, false);
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
     * Adds a non-index segment to the end of the path.
     * @param value
     */
    public void appendSegment(String value) {
        this.appendSegment(value, false);
    }

    /**
     * Resolves a path to its target node within the document model.  This basically
     * walks the tree according to the path segments until it reaches the node being
     * referenced.  If the path does not point to a valid node, then this method
     * returns undefined.
     * @param document the document to resolve the path relative to
     */
    public Node resolve(Document document) {
        return this.resolveWithVisitor(document, null);
    }

    /**
     * Resolves a path to its target node while also visiting all nodes along the way.
     * @param document the document to resolve the path relative to
     * @param visitor an optional visitor to invoke for each node in the path (can be null)
     */
    public Node resolveWithVisitor(Document document, IVisitor visitor) {
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
                rval += '[' + segment.asString() + ']';
            } else {
                rval += '/' + segment.asString();
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
         *
         * @param value RAW (non-escaped) segment value or null
         */
        public NodePathSegment(String value, boolean index) {
            this(value);
            this.index = index;
        }

        /**
         * @return RAW (non-escaped) segment value or null
         */
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
         * This is a reverse operation to {@link #fromString(String)}.
         * Not using "toString" as the method name is intentional,
         * since the format of that methods output should not be assumed
         * to be stable and precisely defined.
         *
         * @return ESCAPED segment value or null
         */
        public String asString() {
            if(getValue() == null)
                return null;
            return escapePathSegmentValue(getValue(), !isIndex());
        }

        /**
         * Creates a segment from an ESCAPED string.
         * @param segment Escaped segment value. If it represents an "indexed"
         *                node, it MUST be surrounded by '[' and ']'.
         */
        public static NodePathSegment fromString(String segment) {
            if (segment == null) {
                return new NodePathSegment(null);
            }
            boolean isIndex = false;
            if (segment.indexOf("[") == 0
                    && segment.indexOf("]") == (segment.length() - 1)) {
                segment = segment.substring(1, segment.length() - 1);
                isIndex = true;
            }
            segment = unescapePathSegmentValue(segment);
            return new NodePathSegment(segment, isIndex);
        }

        /**
         * When a path is represented as a string,
         * we are using three characters with special meaning
         * to encode it's structure.
         * '/' to separate path segments, and '[' with ']' to denote a segment for an "indexed" node.
         * Since these characters can also appear in the segment values themselves (in their non-special meaning),
         * they have to be escaped.
         * The following rules are used, inspired by <a href="https://tools.ietf.org/html/rfc6901">RFC 6901</a>:
         * - The escape character is '~', preceding a number which determines which special character is encoded.
         * - '~1' = '/'
         * - '~2' = '['
         * - '~3' = ']'
         * The escape character is itself encoded as '~0'.
         *
         * @param escapeSlash In the "indexed" segment, '/' can be used unescaped.
         */
        private static String escapePathSegmentValue(String rawValue, boolean escapeSlash) {
            // order is important
            String res = rawValue.replace("~", "~0");
            if(escapeSlash)
                res = res.replace("/", "~1");
            res = res.replace("[", "~2");
            res = res.replace("]", "~3");
            return res;
        }

        /**
         * @see io.apicurio.datamodels.core.models.NodePath.NodePathSegment#escapePathSegmentValue(String, boolean)
         */
        private static String unescapePathSegmentValue(String escapedValue) {
            // order is important
            String res = escapedValue.replace("~3", "]");
            res = res.replace("~2", "[");
            res = res.replace("~1", "/");
            res = res.replace("~0", "~");
            return res;
        }
    }
    
}
