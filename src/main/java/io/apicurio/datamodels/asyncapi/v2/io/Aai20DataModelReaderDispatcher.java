package io.apicurio.datamodels.asyncapi.v2.io;

import io.apicurio.datamodels.asyncapi.io.AaiDataModelReader;
import io.apicurio.datamodels.asyncapi.io.AaiDataModelReaderDispatcher;

public class Aai20DataModelReaderDispatcher extends AaiDataModelReaderDispatcher {
    /**
     * Constructor.
     *
     * @param json
     * @param reader
     */
    public Aai20DataModelReaderDispatcher(Object json, AaiDataModelReader reader) {
        super(json, reader);
    }
}
