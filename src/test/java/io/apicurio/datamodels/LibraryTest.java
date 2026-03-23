package io.apicurio.datamodels;

import io.apicurio.datamodels.models.ModelType;
import io.apicurio.datamodels.models.jsonschema.v202012.JS202012Document;
import io.apicurio.datamodels.models.openapi.OpenApiDocument;
import io.apicurio.datamodels.models.union.StringUnionValueImpl;
import org.junit.Assert;
import org.junit.Test;

import io.apicurio.datamodels.models.openapi.v30.OpenApi30Document;

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
    "info": {
        "title": "My API",
        "version": "1.0"
    }
}""";

        String actual = Library.writeDocumentToJSONString(document);
        Assert.assertEquals(expected, actual);
    }

}
