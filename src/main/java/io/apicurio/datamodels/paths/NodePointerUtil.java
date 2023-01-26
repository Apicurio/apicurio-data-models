package io.apicurio.datamodels.paths;

import java.util.List;
import java.util.Map;

import io.apicurio.datamodels.TraverserDirection;
import io.apicurio.datamodels.VisitorUtil;
import io.apicurio.datamodels.models.MappedNode;
import io.apicurio.datamodels.models.Node;
import io.apicurio.datamodels.models.union.Union;
import io.apicurio.datamodels.util.NodeUtil;

public class NodePointerUtil {

    public static NodePointer create(Node node) {
        NodePointerVisitor visitor = new NodePointerVisitor(node);
        VisitorUtil.visitTree(node.root(), visitor, TraverserDirection.down);
        return visitor.getNodePointer();
    }

    public static NodePointer parse(String path) {
        return NodePointer.parse(path);
    }

    @SuppressWarnings("rawtypes")
    public static Node resolve(NodePointer nodePointer, Node from) {
        List<NodePointerSegment> segments = nodePointer.getSegments();
        Object current = from;
        for (NodePointerSegment segment : segments) {
            if (current == null) {
                break;
            }

            // First, unwrap a union if necessary
            if (NodeUtil.isUnion(current)) {
                Union union = (Union) current;
                current = union.unionValue();
            }

            if (NodeUtil.isMappedNode(current)) {
                MappedNode mappedNode = (MappedNode) current;
                Object potentialCurrent = mappedNode.getItem(segment.getValue());
                if (potentialCurrent != null) {
                    current = potentialCurrent;
                } else {
                    // In some cases mapped nodes can *also* have regular properties
                    current = NodeUtil.getProperty(current, segment.getValue());
                }
            } else if (NodeUtil.isList(current)) {
                int index = NodeUtil.toInteger(segment.getValue());
                List list = (List) current;
                current = list.get(index);
            } else if (NodeUtil.isMap(current)) {
                Map map = (Map) current;
                current = NodeUtil.getMapItem(map, segment.getValue());
            } else if (NodeUtil.isNode(current)) {
                current = NodeUtil.getProperty(current, segment.getValue());
            }
        }

        if (NodeUtil.isNode(current)) {
            return (Node) current;
        } else {
            return null;
        }
    }

}
