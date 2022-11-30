package io.apicurio.datamodels;

import org.junit.Assert;
import org.junit.Test;

import io.apicurio.datamodels.openapi.v30.OpenApi30Document;

public class LibraryTest {

    private static final String EMPTY_OPENAPI = "{"
            + "  \"openapi\": \"3.0.1\""
            + "}";

    @Test
    public void testReadDocumentFromJSONString() {
        OpenApi30Document document = (OpenApi30Document) Library.readDocumentFromJSONString(EMPTY_OPENAPI);
        Assert.assertEquals("3.0.1", document.getOpenapi());
    }

}
