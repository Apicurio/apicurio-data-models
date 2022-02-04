package io.apicurio.datamodels.core.diff;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.apicurio.datamodels.core.diff.change.Change;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.runner.Description;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.ParentRunner;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;

import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.util.IReferenceResolver;

public class DiffTestRunner extends ParentRunner<DiffTestCase> implements IReferenceResolver {

    private final Class<?> testClass;
    private final List<DiffTestCase> children;
    private final ObjectMapper mapper = new ObjectMapper();

    public DiffTestRunner(Class<?> testClass) throws InitializationError {
        super(testClass);
        this.testClass = testClass;
        this.children = loadTests();
    }

    @Override
    public void run(RunNotifier notifier) {
        Library.addReferenceResolver(this);
        try {
            super.run(notifier);
        } finally {
            Library.removeReferenceResolver(this);
        }
    }

    /**
     * Loads the tests.
     * @throws InitializationError
     */
    private List<DiffTestCase> loadTests() throws InitializationError {
        try {
            URL testsJsonUrl = Thread.currentThread().getContextClassLoader().getResource("fixtures/diff/tests.json");
            return mapper.readValue(testsJsonUrl, mapper.getTypeFactory().constructCollectionType(List.class, DiffTestCase.class));
        } catch (IOException e) {
            throw new InitializationError(e);
        }
    }

    @Override
    public Node resolveRef(String reference, Node from) {
        return null;
    }

    @Override
    protected List<DiffTestCase> getChildren() {
        return children;
    }

    @Override
    protected Description describeChild(DiffTestCase child) {
        return Description.createTestDescription(this.testClass, child.getName());
    }

    @Override
    protected void runChild(DiffTestCase child, RunNotifier notifier) {
        Description description = this.describeChild(child);
        Statement statement = new Statement() {
            @Override
            public void evaluate() throws Throwable {
                String testOriginalCP = "fixtures/diff/"+child.getOriginal();
                URL testUrl = Thread.currentThread().getContextClassLoader().getResource(testOriginalCP);
                Assert.assertNotNull("Could not load test resource: " + testOriginalCP, testUrl);

                // Read the test source
                String original = loadResource(testUrl);
                Assert.assertNotNull(original);
                // Parse into a Json object
                Object originalParsed = mapper.readTree(original);

                String testUpdatedCP = "fixtures/diff/" + child.getUpdated();
                URL testUpdatedUrl = Thread.currentThread().getContextClassLoader().getResource(testUpdatedCP);
                Assert.assertNotNull("Could not load test resource: " + testUpdatedCP, testUpdatedUrl);

                // Read the test source
                String updated = loadResource(testUpdatedUrl);
                Assert.assertNotNull(original);
                // Parse into a Json object
                Object updatedParsed = mapper.readTree(updated);

                // Parse into a data model
                Document originalDoc = Library.readDocument(originalParsed);
                Document updatedDoc = Library.readDocument(updatedParsed);

                DiffContext diffContext = Library.diff(originalDoc, updatedDoc);
                String actual = formatDiffContext(diffContext).trim();
                String expectedCP = "fixtures/diff/" + child.getOriginal() + ".expected";
                URL expectedUrl = Thread.currentThread().getContextClassLoader().getResource(expectedCP);
                String expected = loadResource(expectedUrl).trim();

                Assert.assertEquals(expected, actual);
            }
        };
        runLeaf(statement, description, notifier);
    }

    /**
     * Format the list of problems as a string.
     * @param diffContext
     */
    protected String formatDiffContext(DiffContext diffContext) {
        StringBuilder builder = new StringBuilder();
        diffContext.getDifferences().forEach(diff -> {
            builder.append("[")
                    .append(diff.getCode())
                    .append("] |")
                    .append(diff.getChangeType())
                    .append("| :: ")
                    .append(diff.getMessage())
                    .append(" | ")
                    .append(diff.getPath().toString())
                    .append("\n");
        });
        return builder.toString();
    }

    /**
     * Loads a resource as a string (reads the content at the URL).
     * @param testResource
     * @throws IOException
     */
    static String loadResource(URL testResource) throws IOException {
        return IOUtils.toString(testResource, StandardCharsets.UTF_8);
    }
}
