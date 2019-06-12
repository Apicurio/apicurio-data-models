/*
 * Copyright 2019 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.apicurio.datamodels.openapi.visitors.transform;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.runner.Description;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.ParentRunner;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;
import org.skyscreamer.jsonassert.JSONAssert;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.apicurio.datamodels.Library;
import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.DocumentType;
import io.apicurio.datamodels.core.visitors.TraverserDirection;
import io.apicurio.datamodels.openapi.v3.models.Oas30Document;

/**
 * @author eric.wittmann@gmail.com
 */
public class TransformTestRunner extends ParentRunner<TransformTestCase> {

    private Class<?> testClass;
    private List<TransformTestCase> children;
    private ObjectMapper mapper = new ObjectMapper();

    /**
     * Constructor.
     * @param testClass
     * @throws InitializationError
     */
    public TransformTestRunner(Class<?> testClass) throws InitializationError {
        super(testClass);
        this.testClass = testClass;
        this.children = loadTests();
    }

    private List<TransformTestCase> loadTests() throws InitializationError {
        try {
            URL testsJsonUrl = Thread.currentThread().getContextClassLoader().getResource("fixtures/transformation/tests.json");
            List<TransformTestCase> allTests = mapper.readValue(testsJsonUrl, mapper.getTypeFactory().constructCollectionType(List.class, TransformTestCase.class));
            return allTests;
        } catch (IOException e) {
            throw new InitializationError(e);
        }
    }

    /**
     * @see org.junit.runners.ParentRunner#getChildren()
     */
    @Override
    protected List<TransformTestCase> getChildren() {
        return children;
    }

    /**
     * @see org.junit.runners.ParentRunner#describeChild(java.lang.Object)
     */
    @Override
    protected Description describeChild(TransformTestCase child) {
        return Description.createTestDescription(this.testClass, child.getName());
    }

    /**
     * @see org.junit.runners.ParentRunner#runChild(java.lang.Object, org.junit.runner.notification.RunNotifier)
     */
    @Override
    protected void runChild(TransformTestCase child, RunNotifier notifier) {
        Description description = this.describeChild(child);
        Statement statement = new Statement() {
            @Override
            public void evaluate() throws Throwable {
                String testCP = "fixtures/transformation/" + child.getInput();
                URL testUrl = Thread.currentThread().getContextClassLoader().getResource(testCP);
                Assert.assertNotNull("Could not load test resource: " + testCP, testUrl);

                // Read the test source
                String original = loadResource(testUrl);
                Assert.assertNotNull(original);
                // Parse into a Json object
                Object originalParsed = mapper.readTree(original);
                
                // Read into a data model
                Document doc20 = Library.readDocument(originalParsed);
                Assert.assertEquals(DocumentType.openapi2, doc20.getDocumentType());
                
                // Transform the document
                Oas20to30TransformationVisitor transformer = new Oas20to30TransformationVisitor();
                Library.visitTree(doc20, transformer, TraverserDirection.down);
                Oas30Document doc30 = transformer.getResult();
                Assert.assertNotNull(doc30);
                
                // Now compare with expected
                String actual = Library.writeDocumentToJSONString(doc30);
                String expectedCP = "fixtures/transformation/" + child.getExpected();
                URL expectedUrl = Thread.currentThread().getContextClassLoader().getResource(expectedCP);
                Assert.assertNotNull("Could not load test resource: " + expectedCP, expectedUrl);
                String expected = loadResource(expectedUrl);
                
                assertJsonEquals(expected, actual);
            }
        };
        runLeaf(statement, description, notifier);
    }

    /**
     * Loads a resource as a string (reads the content at the URL).
     * @param testResource
     * @throws IOException
     */
    static String loadResource(URL testResource) throws IOException {
        return IOUtils.toString(testResource, "UTF-8");
    }

    /**
     * Compares two JSON strings.
     * @param expected
     * @param actual
     * @throws JSONException
     */
    static void assertJsonEquals(String expected, String actual) throws JSONException {
        JSONAssert.assertEquals(expected, actual, true);
    }

}
