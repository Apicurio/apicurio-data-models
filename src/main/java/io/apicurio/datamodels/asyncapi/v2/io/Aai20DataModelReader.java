package io.apicurio.datamodels.asyncapi.v2.io;

import io.apicurio.datamodels.asyncapi.io.AaiDataModelReader;
import io.apicurio.datamodels.asyncapi.v2.models.Aai20NodeFactory;

public class Aai20DataModelReader extends AaiDataModelReader {

    public Aai20DataModelReader(Aai20NodeFactory nodeFactory) {
        super(nodeFactory);
    }
}
