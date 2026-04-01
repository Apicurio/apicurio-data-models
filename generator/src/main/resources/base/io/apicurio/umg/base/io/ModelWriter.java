package io.apicurio.umg.base.io;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.apicurio.umg.base.RootNode;

public interface ModelWriter {

    public ObjectNode writeRoot(RootNode node);

}
