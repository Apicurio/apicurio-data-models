package io.apicurio.datamodels.paths;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class NodePointer {

    // Example 1: /foo/bar/baz
    // Example 2: /foo/bars/2/baz
    // Example 3: /foo/bars/example/baz

    private final List<NodePointerSegment> segments = new LinkedList<>();

    public NodePointer() {
    }

    public void append(NodePointerSegment segment) {
        this.segments.add(segment);
    }

    public void prepend(NodePointerSegment segment) {
        this.segments.add(0, segment);
    }

    public List<NodePointerSegment> getSegments() {
        return this.segments;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        if (this.segments.size() > 0) {
            this.segments.forEach(segment -> {
                builder.append("/");
                builder.append(segment.toString());
            });
        } else {
            builder.append("/");
        }
        return builder.toString();
    }

    public List<String> toSegments() {
        List<String> rval = new ArrayList<>();
        for (NodePointerSegment segment : segments) {
            rval.add(segment.getValue());
        }
        return rval;
    }

    /**
     * Parses a stringified node pointer and returns an instance.  Returns null if
     * the pointer cannot be parsed.
     * @param pointer
     */
    public static NodePointer parse(String pointer) {
        NodePointer rval = null;
        if ("/".equals(pointer)) {
            rval = new NodePointer();
        } else if (pointer != null && pointer.indexOf("/") == 0) {
            rval = new NodePointer();
            String [] rawSegments = pointer.split("/");
            for (int idx = 1; idx < rawSegments.length; idx++) {
                String rawSegment = rawSegments[idx];
                rval.append(NodePointerSegment.parse(rawSegment));
            }
        }
        return rval;
    }

}
