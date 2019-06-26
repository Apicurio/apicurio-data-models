package io.apicurio.datamodels.asyncapi.v2.io;

import io.apicurio.datamodels.asyncapi.io.AaiDataModelReader;
import io.apicurio.datamodels.asyncapi.io.AaiDataModelReaderDispatcher;

/**
 * @author Jakub Senko <jsenko@redhat.com>
 */
public class Aai20DataModelReaderDispatcher extends AaiDataModelReaderDispatcher {

    public Aai20DataModelReaderDispatcher(Object json, AaiDataModelReader reader) {
        super(json, reader);
    }
}
