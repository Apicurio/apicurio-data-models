package org.example.union.test;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicInteger;

import org.example.union.Node;
import org.example.union.v10.Utm10Document;
import org.example.union.v10.visitors.Utm10Traverser;
import org.example.union.visitors.AllNodeVisitor;
import org.junit.Assert;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

public class UnionTest {

    private static ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testReaderSimplest() throws Exception {
        String testContent = loadTestResource("simplest.json");
        Utm10Document document = UnionTestLibrary.readDocument(testContent);
        Assert.assertNotNull(document);
        Assert.assertEquals("simplest", document.getId());
    }

    @Test
    public void testReaderSimple() throws Exception {
        String testContent = loadTestResource("simple.json");
        Utm10Document document = UnionTestLibrary.readDocument(testContent);
        Assert.assertNotNull(document);
        Assert.assertEquals("simple", document.getId());
    }

    @Test
    public void testReaderNotSimple() throws Exception {
        String testContent = loadTestResource("not-simple.json");
        Utm10Document document = UnionTestLibrary.readDocument(testContent);
        Assert.assertNotNull(document);
        Assert.assertEquals("not-simple", document.getId());
    }

    @Test
    public void testReaderWithChildren() throws Exception {
        String testContent = loadTestResource("with-children.json");
        Utm10Document document = UnionTestLibrary.readDocument(testContent);
        Assert.assertNotNull(document);
        Assert.assertEquals("with-children", document.getId());
    }

    @Test
    public void testFullSimplest() throws Exception {
        doFullTest("simplest.json");
    }

    @Test
    public void testFullSimple() throws Exception {
        doFullTest("simple.json");
    }


    private void doFullTest(String testFile) throws Exception {
        String originalContent = loadTestResource(testFile);
        Utm10Document inputDocument = UnionTestLibrary.readDocument(originalContent);
        String roundTripContent = UnionTestLibrary.writeDocument(inputDocument);
        assertJsonEquals(originalContent, roundTripContent);
        assertEquals(0, countExtraProperties(inputDocument));
    }

    private long countExtraProperties(Utm10Document doc) {
        final AtomicInteger counter = new AtomicInteger(0);
        Utm10Traverser traverser = new Utm10Traverser(new AllNodeVisitor() {
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
