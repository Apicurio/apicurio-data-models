package io.apicurio.datamodels;

import io.apicurio.datamodels.models.ModelType;
import io.apicurio.datamodels.models.jsonschema.v202012.JS202012Document;
import io.apicurio.datamodels.models.openapi.OpenApiDocument;
import io.apicurio.datamodels.models.union.StringUnionValueImpl;
import io.apicurio.datamodels.models.openapi.v30.OpenApi30Document;
import io.apicurio.datamodels.models.openrpc.v14.OpenRpc14Document;
import org.junit.Assert;
import org.junit.Test;

public class LibraryTest {

    private static final String EMPTY_OPENAPI = "{"
            + "  \"openapi\": \"3.0.1\""
            + "}";

    @Test
    public void testReadDocumentFromJSONString() {
        OpenApi30Document document = (OpenApi30Document) Library.readDocumentFromJSONString(EMPTY_OPENAPI);
        Assert.assertEquals("3.0.1", document.getOpenapi());
    }

    @Test
    public void testJsonSchema202012() {
        JS202012Document document = (JS202012Document) Library.createDocument(ModelType.JS202012);
        document.set$id("http://example.com/draft2020-12/my-schema.json");
        document.setType(new StringUnionValueImpl("string"));

        String expected = """
{
    "$id": "http://example.com/draft2020-12/my-schema.json",
    "$schema": "https://json-schema.org/draft/2020-12/schema",
    "type": "string"
}""";

        String actual = Library.writeDocumentToJSONString(document);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testOpenApi3() {
        OpenApiDocument document = (OpenApiDocument) Library.createDocument(ModelType.OPENAPI30);
        document.setInfo(document.createInfo());
        document.getInfo().setTitle("My API");
        document.getInfo().setVersion("1.0");

        String expected = """
{
    "openapi": "3.0.3",
    "info": {
        "title": "My API",
        "version": "1.0"
    }
}""";

        String actual = Library.writeDocumentToJSONString(document);
        Assert.assertEquals(expected, actual);
    }

    public void testCreateOpenApi30() {
        OpenApi30Document document = (OpenApi30Document) Library.createDocument(ModelType.OPENAPI30);
        document.setInfo(document.createInfo());
        document.getInfo().setTitle("My API");
        document.getInfo().setVersion("1.0");

        Assert.assertEquals("3.0.3", document.getOpenapi());
        Assert.assertEquals("My API", document.getInfo().getTitle());
        Assert.assertEquals("1.0", document.getInfo().getVersion());
    }

    @Test
    public void testCreateOpenRpc14() {
        OpenRpc14Document document = (OpenRpc14Document) Library.createDocument(ModelType.OPENRPC14);
        document.setInfo(document.createInfo());
        document.getInfo().setTitle("My RPC API");
        document.getInfo().setVersion("1.0");

        Assert.assertEquals("1.4.0", document.getOpenrpc());
        Assert.assertEquals("My RPC API", document.getInfo().getTitle());
        Assert.assertEquals("1.0", document.getInfo().getVersion());
    }

}
