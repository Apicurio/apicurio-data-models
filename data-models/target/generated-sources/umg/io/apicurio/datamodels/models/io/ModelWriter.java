package io.apicurio.datamodels.models.io;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.apicurio.datamodels.models.RootNode;

public interface ModelWriter {

	public ObjectNode writeRoot(RootNode node);

}
