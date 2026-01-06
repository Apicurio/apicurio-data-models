package org.example.unionmap.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.example.unionmap.Node;
import org.example.unionmap.union.MultiFormatSchemaStandardSchemaUnion;
import org.example.unionmap.v10.Umtm10Document;
import org.example.unionmap.v10.Umtm10Gadget;
import org.example.unionmap.v10.Umtm10MultiFormatSchema;
import org.example.unionmap.v10.Umtm10StandardSchema;
import org.example.unionmap.v10.Umtm10Widget;
import org.example.unionmap.v10.visitors.Umtm10Traverser;
import org.example.unionmap.visitors.AllNodeVisitor;
import org.junit.Assert;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

public class UnionMapTest {

    private static ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testReaderSimple() throws Exception {
        String testContent = loadTestResource("simple.json");
        Umtm10Document document = UnionMapTestLibrary.readDocument(testContent);
        assertNotNull(document);
        assertEquals("simple", document.getId());
        assertEquals("A simple test document", document.getDescription());
    }

    @Test
    public void testUnionMapWithStandardSchemas() throws Exception {
        Umtm10Document document = doFullTest("union-map-standard.json");
        assertEquals("union-map-standard", document.getId());

        Map<String, ?> schemas = document.getSchemas();
        assertNotNull(schemas);
        assertEquals(2, schemas.size());

        assertTrue(schemas.containsKey("StringSchema"));
        assertTrue(schemas.containsKey("NumberSchema"));
    }

    @Test
    public void testUnionMapWithMultiFormatSchemas() throws Exception {
        Umtm10Document document = doFullTest("union-map-multiformat.json");
        assertEquals("union-map-multiformat", document.getId());

        Map<String, ?> schemas = document.getSchemas();
        assertNotNull(schemas);
        assertEquals(2, schemas.size());

        assertTrue(schemas.containsKey("AsyncAPISchema"));
        assertTrue(schemas.containsKey("ProtobufSchema"));
    }

    @Test
    public void testUnionMapMixed() throws Exception {
        Umtm10Document document = doFullTest("union-map-mixed.json");
        assertEquals("union-map-mixed", document.getId());

        Map<String, MultiFormatSchemaStandardSchemaUnion> schemas = document.getSchemas();
        assertNotNull(schemas);
        assertEquals(3, schemas.size());

        assertTrue(schemas.containsKey("StandardSchema1"));
        assertTrue(schemas.containsKey("MultiFormatSchema1"));
        assertTrue(schemas.containsKey("StandardSchema2"));

        MultiFormatSchemaStandardSchemaUnion s1 = schemas.get("StandardSchema1");
        MultiFormatSchemaStandardSchemaUnion s2 = schemas.get("MultiFormatSchema1");
        MultiFormatSchemaStandardSchemaUnion s3 = schemas.get("StandardSchema2");

        assertNotNull(s1);
        assertNotNull(s2);
        assertNotNull(s3);

        assertTrue("Expected 'StandardSchema1' to be a StandardSchema", s1.isStandardSchema());
        assertTrue("Expected 'MultiFormatSchema1' to be a MultiFormatSchema", s2.isMultiFormatSchema());
        assertTrue("Expected 'StandardSchema2' to be a StandardSchema", s3.isStandardSchema());

        Umtm10StandardSchema ss1 = s1.asStandardSchema();
        Umtm10MultiFormatSchema mfs2 = s2.asMultiFormatSchema();
        Umtm10StandardSchema ss3 = s3.asStandardSchema();

        assertNotNull(ss1);
        assertNotNull(mfs2);
        assertNotNull(ss3);
    }

    @Test
    public void testUnionListWidgets() throws Exception {
        Umtm10Document document = doFullTest("union-list-widgets.json");
        assertEquals("union-list-widgets", document.getId());

        List<?> widgets = document.getWidgets();
        assertNotNull(widgets);
        assertEquals(3, widgets.size());
    }

    @Test
    public void testUnionListMixed() throws Exception {
        Umtm10Document document = doFullTest("union-list-mixed.json");
        assertEquals("union-list-mixed", document.getId());

        List<?> widgets = document.getWidgets();
        assertNotNull(widgets);
        assertEquals(4, widgets.size());
    }

    @Test
    public void testUnionListPrimitives() throws Exception {
        Umtm10Document document = doFullTest("union-list-primitives.json");
        assertEquals("union-list-primitives", document.getId());

        List<?> mixedItems = document.getMixedItems();
        assertNotNull(mixedItems);
        assertEquals(4, mixedItems.size());
    }

    @Test
    public void testFullComplex() throws Exception {
        Umtm10Document document = doFullTest("complex.json");
        assertEquals("complex", document.getId());

        // Verify union map
        Map<String, ?> schemas = document.getSchemas();
        assertNotNull(schemas);
        assertTrue(schemas.size() > 0);

        // Verify union list
        List<?> widgets = document.getWidgets();
        assertNotNull(widgets);
        assertTrue(widgets.size() > 0);
    }

    private Umtm10Document doFullTest(String testFile) throws Exception {
        String originalContent = loadTestResource(testFile);
        Umtm10Document inputDocument = UnionMapTestLibrary.readDocument(originalContent);
        String roundTripContent = UnionMapTestLibrary.writeDocument(inputDocument);
        assertJsonEquals(originalContent, roundTripContent);
        assertEquals(0, countExtraProperties(inputDocument));
        return inputDocument;
    }

    private long countExtraProperties(Umtm10Document doc) {
        final AtomicInteger counter = new AtomicInteger(0);
        Umtm10Traverser traverser = new Umtm10Traverser(new AllNodeVisitor() {
            @Override
            protected void visitNode(Node node) {
                counter.addAndGet(node.getExtraPropertyNames().size());
            }
        });
        traverser.traverse(doc);
        return counter.get();
    }

    private String loadTestResource(String resourceName) throws Exception {
        String resourcePath = "fixtures/" + resourceName;
        try (InputStream res = getClass().getClassLoader().getResourceAsStream(resourcePath)) {
            if (res == null) {
                Assert.fail("Test resource not found: " + resourcePath);
            }
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            baos.write(res.readAllBytes());
            return new String(baos.toByteArray(), StandardCharsets.UTF_8);
        }
    }

    private void assertJsonEquals(String expectedJson, String actualJson) throws Exception {
        Assert.assertEquals(mapper.readTree(expectedJson), mapper.readTree(actualJson));
    }

}
